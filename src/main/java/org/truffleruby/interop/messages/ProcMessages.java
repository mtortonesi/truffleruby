/*
 * Copyright (c) 2020 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 2.0, or
 * GNU General Public License version 2, or
 * GNU Lesser General Public License version 2.1.
 */
package org.truffleruby.interop.messages;

import com.oracle.truffle.api.object.DynamicObject;
import org.truffleruby.core.proc.RubyProc;
import org.truffleruby.interop.ForeignToRubyArgumentsNode;
import org.truffleruby.language.library.RubyLibrary;
import org.truffleruby.language.yield.YieldNode;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

@ExportLibrary(value = InteropLibrary.class, receiverType = DynamicObject.class)
@ExportLibrary(value = RubyLibrary.class, receiverType = DynamicObject.class)
public class ProcMessages extends RubyObjectMessages {

    @ExportMessage
    protected static boolean isExecutable(DynamicObject receiver) {
        return true;
    }

    @ExportMessage
    protected static Object execute(DynamicObject receiver, Object[] arguments,
            @Cached YieldNode yieldNode,
            @Cached ForeignToRubyArgumentsNode foreignToRubyArgumentsNode) {
        return yieldNode.executeDispatch((RubyProc) receiver, foreignToRubyArgumentsNode.executeConvert(arguments));
    }
}
