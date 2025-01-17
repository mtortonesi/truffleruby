/*
 * Copyright (c) 2015, 2023 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 2.0, or
 * GNU General Public License version 2, or
 * GNU Lesser General Public License version 2.1.
 */
package org.truffleruby.parser;

import java.util.ArrayList;
import java.util.List;

import org.prism.AbstractNodeVisitor;
import org.prism.Nodes;
import org.truffleruby.RubyLanguage;
import org.truffleruby.core.hash.ConcatHashLiteralNode;
import org.truffleruby.core.hash.HashLiteralNode;
import org.truffleruby.language.RubyNode;
import org.truffleruby.language.arguments.MissingArgumentBehavior;
import org.truffleruby.language.arguments.ReadKeywordArgumentNode;
import org.truffleruby.language.arguments.ReadPreArgumentNode;
import org.truffleruby.language.literal.NilLiteralNode;
import org.truffleruby.language.literal.ObjectLiteralNode;


/** Produces code to reload arguments from local variables back into the arguments array. Only works for simple cases.
 * Used for zsuper calls which pass the same arguments, but will pick up modifications made to them in the method so
 * far. */
public final class YARPReloadArgumentsTranslator extends AbstractNodeVisitor<RubyNode> {

    private final RubyLanguage language;
    private final YARPTranslator yarpTranslator;
    private final boolean hasKeywordArguments;

    private int index = 0;
    private int restParameterIndex = -1;

    public YARPReloadArgumentsTranslator(
            RubyLanguage language,
            YARPTranslator yarpTranslator,
            Nodes.ParametersNode parametersNode) {
        this.language = language;
        this.yarpTranslator = yarpTranslator;
        this.hasKeywordArguments = parametersNode.keywords.length > 0 || parametersNode.keyword_rest != null;
    }

    public int getRestParameterIndex() {
        return restParameterIndex;
    }

    public RubyNode[] reload(Nodes.ParametersNode parametersNode) {
        final List<RubyNode> sequence = new ArrayList<>();

        for (var node : parametersNode.requireds) {
            sequence.add(node.accept(this)); // Nodes.RequiredParameterNode is expected here
            index++;
        }

        for (var node : parametersNode.optionals) {
            sequence.add(node.accept(this)); // Nodes.OptionalParameterNode is expected here
            index++;
        }

        if (parametersNode.rest != null) {
            restParameterIndex = index;
            sequence.add(parametersNode.rest.accept(this)); // Nodes.RestParameterNode is expected here
        }

        int postCount = parametersNode.posts.length;
        if (postCount > 0) {
            index = -postCount;
            for (var node : parametersNode.posts) {
                sequence.add(node.accept(this)); // Nodes.RequiredParameterNode is expected here
                index++;
            }
        }

        RubyNode kwArgsNode = null;

        if (parametersNode.keywords.length > 0) {
            final int keywordCount = parametersNode.keywords.length;
            RubyNode[] keyValues = new RubyNode[keywordCount * 2];

            for (int i = 0; i < keywordCount; i++) {
                // Nodes.RequiredKeywordParameterNode/Nodes.OptionalKeywordParameterNode are expected here
                final Nodes.Node kwArg = parametersNode.keywords[i];
                final RubyNode value = kwArg.accept(this);
                var name = ((ReadKeywordArgumentNode) value).getName();
                RubyNode key = new ObjectLiteralNode(name);
                keyValues[2 * i] = key;
                keyValues[2 * i + 1] = value;
            }
            kwArgsNode = HashLiteralNode.create(keyValues, language);
        }

        if (parametersNode.keyword_rest != null) {
            // Nodes.KeywordRestParameterNode/Nodes.NoKeywordsParameterNode are expected here
            final RubyNode keyRest = parametersNode.keyword_rest.accept(this);
            if (kwArgsNode == null) {
                kwArgsNode = keyRest;
            } else {
                kwArgsNode = new ConcatHashLiteralNode(new RubyNode[]{ kwArgsNode, keyRest });
            }

        }

        if (kwArgsNode != null) {
            sequence.add(kwArgsNode);
        }

        return sequence.toArray(RubyNode.EMPTY_ARRAY);
    }

    @Override
    public RubyNode visitRequiredParameterNode(Nodes.RequiredParameterNode node) {
        return yarpTranslator.getEnvironment().findLocalVarNode(node.name, null);
    }

    @Override
    public RubyNode visitOptionalParameterNode(Nodes.OptionalParameterNode node) {
        return yarpTranslator.getEnvironment().findLocalVarNode(node.name, null);
    }

    @Override
    public RubyNode visitMultiTargetNode(Nodes.MultiTargetNode node) {
        return Translator.profileArgument(language,
                new ReadPreArgumentNode(index, hasKeywordArguments, MissingArgumentBehavior.NIL));
    }

    @Override
    public RubyNode visitRestParameterNode(Nodes.RestParameterNode node) {
        return yarpTranslator.getEnvironment().findLocalVarNode(node.name, null);
    }

    @Override
    public RubyNode visitRequiredKeywordParameterNode(Nodes.RequiredKeywordParameterNode node) {
        return yarpTranslator.getEnvironment().findLocalVarNode(node.name, null);
    }

    @Override
    public RubyNode visitOptionalKeywordParameterNode(Nodes.OptionalKeywordParameterNode node) {
        return yarpTranslator.getEnvironment().findLocalVarNode(node.name, null);
    }

    @Override
    public RubyNode visitKeywordRestParameterNode(Nodes.KeywordRestParameterNode node) {
        return yarpTranslator.getEnvironment().findLocalVarNode(node.name, null);
    }

    @Override
    public RubyNode visitNoKeywordsParameterNode(Nodes.NoKeywordsParameterNode node) {
        return defaultVisit(node);
    }

    @Override
    protected RubyNode defaultVisit(Nodes.Node node) {
        var nilNode = new NilLiteralNode();
        return YARPTranslator.assignPositionAndFlags(node, nilNode);
    }

}
