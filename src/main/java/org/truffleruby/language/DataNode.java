/*
 * Copyright (c) 2013, 2019 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 2.0, or
 * GNU General Public License version 2, or
 * GNU Lesser General Public License version 2.1.
 */
package org.truffleruby.language;

import org.jcodings.Encoding;
import org.truffleruby.RubyContext;
import org.truffleruby.core.rope.CodeRange;
import org.truffleruby.core.string.RubyString;
import org.truffleruby.core.string.StringNodes;
import org.truffleruby.language.dispatch.CallDispatchHeadNode;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;

import static org.truffleruby.language.dispatch.DispatchConfiguration.PRIVATE;

public class DataNode extends RubyContextSourceNode {

    @Child private StringNodes.MakeStringNode makeStringNode;
    @Child private CallDispatchHeadNode callHelperNode;

    private final int endPosition;

    public DataNode(int endPosition) {
        this.endPosition = endPosition;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        if (makeStringNode == null) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            makeStringNode = insert(StringNodes.MakeStringNode.create());
        }

        if (callHelperNode == null) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            callHelperNode = insert(CallDispatchHeadNode.create(PRIVATE));
        }

        final String path = getPath();
        final Encoding localeEncoding = getContext().getEncodingManager().getLocaleEncoding();
        final RubyString pathString = makeStringNode.executeMake(path, localeEncoding, CodeRange.CR_UNKNOWN);
        final Object data = callHelperNode
                .call(coreLibrary().truffleInternalModule, "get_data", pathString, endPosition);

        coreLibrary().objectClass.fields.setConstant(getContext(), null, "DATA", data);

        return nil;
    }

    @TruffleBoundary
    private String getPath() {
        return RubyContext.getPath(getEncapsulatingSourceSection().getSource());
    }

}
