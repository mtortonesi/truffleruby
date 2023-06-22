/*
 * Copyright (c) 2013, 2023 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 2.0, or
 * GNU General Public License version 2, or
 * GNU Lesser General Public License version 2.1.
 */
package org.truffleruby.language.exceptions;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.exception.AbstractTruffleException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.TruffleSafepoint;
import com.oracle.truffle.api.profiles.InlinedBranchProfile;
import com.oracle.truffle.api.profiles.InlinedConditionProfile;
import org.truffleruby.core.exception.ExceptionOperations;
import org.truffleruby.language.RubyContextSourceNode;
import org.truffleruby.language.RubyNode;
import org.truffleruby.language.control.KillException;
import org.truffleruby.language.control.RetryException;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.TruffleStackTrace;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.ExplodeLoop.LoopExplosionKind;
import org.truffleruby.language.methods.TranslateExceptionNode;
import org.truffleruby.language.threadlocal.ThreadLocalGlobals;

public abstract class TryNode extends RubyContextSourceNode {

    @Child private RubyNode tryPart;
    @Children private final RescueNode[] rescueParts;
    @Child private RubyNode elsePart;
    @Child private TranslateExceptionNode translateExceptionNode;
    private final boolean canOmitBacktrace;

    public TryNode(
            RubyNode tryPart,
            RescueNode[] rescueParts,
            RubyNode elsePart,
            boolean canOmitBacktrace) {
        this.tryPart = tryPart;
        this.rescueParts = rescueParts;
        this.elsePart = elsePart;
        this.canOmitBacktrace = canOmitBacktrace;
    }

    /** Based on {@link InteropLibrary#throwException(Object)}'s {@code TryCatchNode} */
    @Specialization
    protected Object doTry(VirtualFrame frame,
            @Cached InlinedBranchProfile noExceptionProfile,
            @Cached InlinedBranchProfile killExceptionProfile,
            @Cached InlinedBranchProfile guestExceptionProfile,
            @Cached InlinedBranchProfile retryProfile,
            @Cached InlinedConditionProfile raiseExceptionProfile) {
        while (true) {
            Object result;

            try {
                result = tryPart.execute(frame);
                noExceptionProfile.enter(this);
            } catch (KillException e) { // an AbstractTruffleException but must not set $! and cannot be rescue'd
                killExceptionProfile.enter(this);
                throw e;
            } catch (AbstractTruffleException exception) {
                guestExceptionProfile.enter(this);
                try {
                    return handleException(frame, exception, raiseExceptionProfile);
                } catch (RetryException e) {
                    retryProfile.enter(this);
                    TruffleSafepoint.poll(this);
                    continue;
                }
            } catch (Throwable t) {
                if (translateExceptionNode == null) {
                    CompilerDirectives.transferToInterpreterAndInvalidate();
                    translateExceptionNode = insert(TranslateExceptionNode.create());
                }
                throw translateExceptionNode.executeTranslation(t);
            }

            if (elsePart != null) {
                result = elsePart.execute(frame);
            }

            return result;
        }
    }

    @ExplodeLoop(kind = LoopExplosionKind.FULL_UNROLL_UNTIL_RETURN)
    private Object handleException(VirtualFrame frame, AbstractTruffleException exception,
            InlinedConditionProfile raiseExceptionProfile) {
        final Object exceptionObject = ExceptionOperations.getExceptionObject(this, exception, raiseExceptionProfile);

        for (RescueNode rescue : rescueParts) {
            if (rescue.canHandle(frame, exceptionObject)) {
                if (getContext().getOptions().BACKTRACE_ON_RESCUE) {
                    printBacktraceOnRescue(rescue, exception);
                }

                if (canOmitBacktrace) {
                    /* If we're in this branch, we've already determined that the rescue body doesn't access `$!`. Thus,
                     * we can safely skip writing that value. Writing to `$!` is quite expensive, so we want to avoid it
                     * wherever we can. */
                    return rescue.execute(frame);
                } else {
                    /* We materialize the backtrace eagerly here, as the exception is being rescued and therefore the
                     * exception is no longer being thrown on the exception path and the lazy stacktrace is no longer
                     * filled. */
                    TruffleStackTrace.fillIn(exception);
                    return setLastExceptionAndRunRescue(frame, exceptionObject, rescue);
                }
            }
        }

        throw exception;
    }

    private Object setLastExceptionAndRunRescue(VirtualFrame frame, Object exceptionObject, RescueNode rescue) {
        final ThreadLocalGlobals threadLocalGlobals = getLanguage().getCurrentThread().threadLocalGlobals;
        final Object previousException = threadLocalGlobals.getLastException();
        threadLocalGlobals.setLastException(exceptionObject);
        try {
            CompilerAsserts.partialEvaluationConstant(rescue);
            return rescue.execute(frame);
        } finally {
            threadLocalGlobals.setLastException(previousException);
        }
    }

    @TruffleBoundary
    private void printBacktraceOnRescue(RescueNode rescue, AbstractTruffleException exception) {
        String info = "rescued at " + getContext().fileLine(
                getContext().getCallStack().getTopMostUserSourceSection(rescue.getEncapsulatingSourceSection())) +
                ":\n";
        getContext().getDefaultBacktraceFormatter().printRubyExceptionOnEnvStderr(info, exception);
    }

    @Override
    public RubyNode cloneUninitialized() {
        var copy = TryNodeGen.create(
                tryPart.cloneUninitialized(),
                cloneUninitialized(rescueParts),
                cloneUninitialized(elsePart),
                canOmitBacktrace);
        return copy.copyFlags(this);
    }

    protected static RescueNode[] cloneUninitialized(RescueNode[] nodes) {
        RescueNode[] copies = new RescueNode[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            copies[i] = (RescueNode) nodes[i].cloneUninitialized();
        }
        return copies;
    }

}
