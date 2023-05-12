/*
 * Copyright (c) 2023 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 2.0, or
 * GNU General Public License version 2, or
 * GNU Lesser General Public License version 2.1.
 */
package org.truffleruby.language;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.GenerateCached;
import com.oracle.truffle.api.dsl.GenerateInline;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.Node;

@GenerateInline(inlineByDefault = true)
@GenerateCached(false)
public abstract class LazyWarnNode extends RubyBaseNode {

    public abstract WarnNode execute(Node node);

    public final WarnNode get(Node node) {
        return execute(node);
    }

    @Specialization
    protected static WarnNode doLazy(
            @Cached(inline = false) WarnNode warnNode) {
        return warnNode;
    }
}
