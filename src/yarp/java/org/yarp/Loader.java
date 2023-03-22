package org.yarp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

// GENERATED BY Loader.java.erb
// @formatter:off
public class Loader {

    public static Nodes.Node load(byte[] source, byte[] serialized) {
        return new Loader(source, serialized).load();
    }

    private final ByteBuffer buffer;

    private Loader(byte[] source, byte[] serialized) {
        buffer = ByteBuffer.wrap(serialized).order(ByteOrder.nativeOrder());
    }

    private Nodes.Node load() {
        expect((byte) 'Y');
        expect((byte) 'A');
        expect((byte) 'R');
        expect((byte) 'P');

        expect((byte) 0);
        expect((byte) 4);
        expect((byte) 0);

        return loadNode();
    }

    private byte[] loadString() {
        int length = buffer.getInt();
        byte[] string = new byte[length];
        buffer.get(string);
        return string;
    }

    private Nodes.Token loadOptionalToken() {
        if (buffer.get(buffer.position()) != 0) {
            return loadToken();
        } else {
            buffer.position(buffer.position() + 1); // continue after the 0 byte
            return null;
        }
    }

    private Nodes.Node loadOptionalNode() {
        if (buffer.get(buffer.position()) != 0) {
            return loadNode();
        } else {
            buffer.position(buffer.position() + 1); // continue after the 0 byte
            return null;
        }
    }

    private Nodes.Token[] loadTokens() {
        int length = buffer.getInt();
        Nodes.Token[] tokens = new Nodes.Token[length];
        for (int i = 0; i < length; i++) {
            tokens[i] = loadToken();
        }
        return tokens;
    }

    private Nodes.Node[] loadNodes() {
        int length = buffer.getInt();
        Nodes.Node[] nodes = new Nodes.Node[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = loadNode();
        }
        return nodes;
    }

    private Nodes.Token loadToken() {
        int type = buffer.get() & 0xFF;
        int startOffset = buffer.getInt();
        int endOffset = buffer.getInt();

        final Nodes.TokenType tokenType = Nodes.TOKEN_TYPES[type];
        return new Nodes.Token(tokenType, startOffset, endOffset);
    }

    private Nodes.Location loadLocation() {
        int startOffset = buffer.getInt();
        int endOffset = buffer.getInt();
        return new Nodes.Location(startOffset, endOffset);
    }

    private Nodes.Location loadOptionalLocation() {
        if (buffer.get(buffer.position()) != 0) {
            return loadLocation();
        } else {
            buffer.position(buffer.position() + 1); // continue after the 0 byte
            return null;
        }
    }

    private int loadInteger() {
        return buffer.getInt();
    }

    private Nodes.Node loadNode() {
        int type = buffer.get() & 0xFF;
        int length = buffer.getInt();
        int startOffset = buffer.getInt();
        int endOffset = buffer.getInt();

        switch (type) {
            case 0:
                return new Nodes.AliasNode(loadNode(), loadNode(), loadLocation(), startOffset, endOffset);
            case 1:
                return new Nodes.AndNode(loadNode(), loadNode(), loadToken(), startOffset, endOffset);
            case 2:
                return new Nodes.ArgumentsNode(loadNodes(), startOffset, endOffset);
            case 3:
                return new Nodes.ArrayNode(loadNodes(), loadOptionalToken(), loadOptionalToken(), startOffset, endOffset);
            case 4:
                return new Nodes.AssocNode(loadNode(), loadOptionalNode(), loadOptionalToken(), startOffset, endOffset);
            case 5:
                return new Nodes.AssocSplatNode(loadNode(), loadLocation(), startOffset, endOffset);
            case 6:
                return new Nodes.BeginNode(loadOptionalToken(), loadNode(), loadOptionalNode(), loadOptionalNode(), loadOptionalNode(), loadOptionalToken(), startOffset, endOffset);
            case 7:
                return new Nodes.BlockArgumentNode(loadNode(), loadLocation(), startOffset, endOffset);
            case 8:
                return new Nodes.BlockNode(loadOptionalNode(), loadOptionalNode(), loadLocation(), loadLocation(), startOffset, endOffset);
            case 9:
                return new Nodes.BlockParameterNode(loadOptionalToken(), loadLocation(), startOffset, endOffset);
            case 10:
                return new Nodes.BlockParametersNode(loadNode(), loadTokens(), startOffset, endOffset);
            case 11:
                return new Nodes.BreakNode(loadOptionalNode(), loadLocation(), startOffset, endOffset);
            case 12:
                return new Nodes.CallNode(loadOptionalNode(), loadOptionalToken(), loadOptionalToken(), loadOptionalToken(), loadOptionalNode(), loadOptionalToken(), loadOptionalNode(), loadString(), startOffset, endOffset);
            case 13:
                return new Nodes.CaseNode(loadOptionalNode(), loadNodes(), loadOptionalNode(), loadLocation(), loadLocation(), startOffset, endOffset);
            case 14:
                return new Nodes.ClassNode(loadNode(), loadToken(), loadNode(), loadOptionalToken(), loadOptionalNode(), loadNode(), loadToken(), startOffset, endOffset);
            case 15:
                return new Nodes.ClassVariableReadNode(startOffset, endOffset);
            case 16:
                return new Nodes.ClassVariableWriteNode(loadLocation(), loadOptionalNode(), loadOptionalLocation(), startOffset, endOffset);
            case 17:
                return new Nodes.ConstantPathNode(loadOptionalNode(), loadToken(), loadNode(), startOffset, endOffset);
            case 18:
                return new Nodes.ConstantPathWriteNode(loadNode(), loadOptionalToken(), loadOptionalNode(), startOffset, endOffset);
            case 19:
                return new Nodes.ConstantReadNode(startOffset, endOffset);
            case 20:
                return new Nodes.DefNode(loadToken(), loadOptionalNode(), loadNode(), loadNode(), loadNode(), loadLocation(), loadOptionalLocation(), loadOptionalLocation(), loadOptionalLocation(), loadOptionalLocation(), loadOptionalLocation(), startOffset, endOffset);
            case 21:
                return new Nodes.DefinedNode(loadOptionalToken(), loadNode(), loadOptionalToken(), loadLocation(), startOffset, endOffset);
            case 22:
                return new Nodes.ElseNode(loadToken(), loadOptionalNode(), loadToken(), startOffset, endOffset);
            case 23:
                return new Nodes.EnsureNode(loadToken(), loadNode(), loadToken(), startOffset, endOffset);
            case 24:
                return new Nodes.FalseNode(startOffset, endOffset);
            case 25:
                return new Nodes.FloatNode(startOffset, endOffset);
            case 26:
                return new Nodes.ForNode(loadNode(), loadNode(), loadNode(), loadLocation(), loadLocation(), loadOptionalLocation(), loadLocation(), startOffset, endOffset);
            case 27:
                return new Nodes.ForwardingArgumentsNode(startOffset, endOffset);
            case 28:
                return new Nodes.ForwardingParameterNode(startOffset, endOffset);
            case 29:
                return new Nodes.ForwardingSuperNode(loadOptionalNode(), startOffset, endOffset);
            case 30:
                return new Nodes.GlobalVariableReadNode(loadToken(), startOffset, endOffset);
            case 31:
                return new Nodes.GlobalVariableWriteNode(loadToken(), loadOptionalToken(), loadOptionalNode(), startOffset, endOffset);
            case 32:
                return new Nodes.HashNode(loadOptionalToken(), loadNodes(), loadOptionalToken(), startOffset, endOffset);
            case 33:
                return new Nodes.HeredocNode(loadToken(), loadNodes(), loadToken(), loadInteger(), startOffset, endOffset);
            case 34:
                return new Nodes.IfNode(loadToken(), loadNode(), loadNode(), loadOptionalNode(), loadOptionalToken(), startOffset, endOffset);
            case 35:
                return new Nodes.ImaginaryNode(startOffset, endOffset);
            case 36:
                return new Nodes.InstanceVariableReadNode(startOffset, endOffset);
            case 37:
                return new Nodes.InstanceVariableWriteNode(loadLocation(), loadOptionalNode(), loadOptionalLocation(), startOffset, endOffset);
            case 38:
                return new Nodes.IntegerNode(startOffset, endOffset);
            case 39:
                return new Nodes.InterpolatedRegularExpressionNode(loadToken(), loadNodes(), loadToken(), startOffset, endOffset);
            case 40:
                return new Nodes.InterpolatedStringNode(loadOptionalToken(), loadNodes(), loadOptionalToken(), startOffset, endOffset);
            case 41:
                return new Nodes.InterpolatedSymbolNode(loadOptionalToken(), loadNodes(), loadOptionalToken(), startOffset, endOffset);
            case 42:
                return new Nodes.InterpolatedXStringNode(loadToken(), loadNodes(), loadToken(), startOffset, endOffset);
            case 43:
                return new Nodes.KeywordParameterNode(loadToken(), loadOptionalNode(), startOffset, endOffset);
            case 44:
                return new Nodes.KeywordRestParameterNode(loadToken(), loadOptionalToken(), startOffset, endOffset);
            case 45:
                return new Nodes.KeywordStarNode(loadToken(), loadNode(), startOffset, endOffset);
            case 46:
                return new Nodes.LambdaNode(loadNode(), loadOptionalToken(), loadNode(), loadOptionalToken(), loadNode(), startOffset, endOffset);
            case 47:
                return new Nodes.LocalVariableReadNode(loadToken(), startOffset, endOffset);
            case 48:
                return new Nodes.LocalVariableWriteNode(loadToken(), loadOptionalToken(), loadOptionalNode(), startOffset, endOffset);
            case 49:
                return new Nodes.MissingNode(startOffset, endOffset);
            case 50:
                return new Nodes.ModuleNode(loadNode(), loadToken(), loadNode(), loadNode(), loadToken(), startOffset, endOffset);
            case 51:
                return new Nodes.MultiWriteNode(loadNodes(), loadOptionalToken(), loadOptionalNode(), loadOptionalLocation(), loadOptionalLocation(), startOffset, endOffset);
            case 52:
                return new Nodes.NextNode(loadOptionalNode(), loadLocation(), startOffset, endOffset);
            case 53:
                return new Nodes.NilNode(startOffset, endOffset);
            case 54:
                return new Nodes.NoKeywordsParameterNode(loadLocation(), loadLocation(), startOffset, endOffset);
            case 55:
                return new Nodes.OperatorAndAssignmentNode(loadNode(), loadNode(), loadLocation(), startOffset, endOffset);
            case 56:
                return new Nodes.OperatorAssignmentNode(loadNode(), loadToken(), loadNode(), startOffset, endOffset);
            case 57:
                return new Nodes.OperatorOrAssignmentNode(loadNode(), loadNode(), loadLocation(), startOffset, endOffset);
            case 58:
                return new Nodes.OptionalParameterNode(loadToken(), loadToken(), loadNode(), startOffset, endOffset);
            case 59:
                return new Nodes.OrNode(loadNode(), loadNode(), loadLocation(), startOffset, endOffset);
            case 60:
                return new Nodes.ParametersNode(loadNodes(), loadNodes(), loadOptionalNode(), loadNodes(), loadOptionalNode(), loadOptionalNode(), startOffset, endOffset);
            case 61:
                return new Nodes.ParenthesesNode(loadOptionalNode(), loadLocation(), loadLocation(), startOffset, endOffset);
            case 62:
                return new Nodes.PostExecutionNode(loadNode(), loadLocation(), loadLocation(), loadLocation(), startOffset, endOffset);
            case 63:
                return new Nodes.PreExecutionNode(loadNode(), loadLocation(), loadLocation(), loadLocation(), startOffset, endOffset);
            case 64:
                return new Nodes.ProgramNode(loadNode(), loadNode(), startOffset, endOffset);
            case 65:
                return new Nodes.RangeNode(loadOptionalNode(), loadOptionalNode(), loadLocation(), startOffset, endOffset);
            case 66:
                return new Nodes.RationalNode(startOffset, endOffset);
            case 67:
                return new Nodes.RedoNode(startOffset, endOffset);
            case 68:
                return new Nodes.RegularExpressionNode(loadToken(), loadToken(), loadToken(), loadString(), startOffset, endOffset);
            case 69:
                return new Nodes.RequiredDestructuredParameterNode(loadNodes(), loadToken(), loadToken(), startOffset, endOffset);
            case 70:
                return new Nodes.RequiredParameterNode(loadToken(), startOffset, endOffset);
            case 71:
                return new Nodes.RescueModifierNode(loadNode(), loadToken(), loadNode(), startOffset, endOffset);
            case 72:
                return new Nodes.RescueNode(loadToken(), loadNodes(), loadOptionalToken(), loadOptionalNode(), loadNode(), loadOptionalNode(), startOffset, endOffset);
            case 73:
                return new Nodes.RestParameterNode(loadToken(), loadOptionalToken(), startOffset, endOffset);
            case 74:
                return new Nodes.RetryNode(startOffset, endOffset);
            case 75:
                return new Nodes.ReturnNode(loadToken(), loadOptionalNode(), startOffset, endOffset);
            case 76:
                return new Nodes.Scope(loadTokens(), startOffset, endOffset);
            case 77:
                return new Nodes.SelfNode(startOffset, endOffset);
            case 78:
                return new Nodes.SingletonClassNode(loadNode(), loadToken(), loadToken(), loadNode(), loadNode(), loadToken(), startOffset, endOffset);
            case 79:
                return new Nodes.SourceEncodingNode(startOffset, endOffset);
            case 80:
                return new Nodes.SourceFileNode(startOffset, endOffset);
            case 81:
                return new Nodes.SourceLineNode(startOffset, endOffset);
            case 82:
                return new Nodes.SplatNode(loadToken(), loadOptionalNode(), startOffset, endOffset);
            case 83:
                return new Nodes.StatementsNode(loadNodes(), startOffset, endOffset);
            case 84:
                return new Nodes.StringConcatNode(loadNode(), loadNode(), startOffset, endOffset);
            case 85:
                return new Nodes.StringInterpolatedNode(loadToken(), loadNode(), loadToken(), startOffset, endOffset);
            case 86:
                return new Nodes.StringNode(loadOptionalToken(), loadToken(), loadOptionalToken(), loadString(), startOffset, endOffset);
            case 87:
                return new Nodes.SuperNode(loadToken(), loadOptionalToken(), loadOptionalNode(), loadOptionalToken(), loadOptionalNode(), startOffset, endOffset);
            case 88:
                return new Nodes.SymbolNode(loadOptionalToken(), loadToken(), loadOptionalToken(), loadString(), startOffset, endOffset);
            case 89:
                return new Nodes.TernaryNode(loadNode(), loadToken(), loadNode(), loadToken(), loadNode(), startOffset, endOffset);
            case 90:
                return new Nodes.TrueNode(startOffset, endOffset);
            case 91:
                return new Nodes.UndefNode(loadNodes(), loadLocation(), startOffset, endOffset);
            case 92:
                return new Nodes.UnlessNode(loadToken(), loadNode(), loadNode(), loadOptionalNode(), loadOptionalToken(), startOffset, endOffset);
            case 93:
                return new Nodes.UntilNode(loadToken(), loadNode(), loadNode(), startOffset, endOffset);
            case 94:
                return new Nodes.WhenNode(loadToken(), loadNodes(), loadOptionalNode(), startOffset, endOffset);
            case 95:
                return new Nodes.WhileNode(loadToken(), loadNode(), loadNode(), startOffset, endOffset);
            case 96:
                return new Nodes.XStringNode(loadToken(), loadToken(), loadToken(), loadString(), startOffset, endOffset);
            case 97:
                return new Nodes.YieldNode(loadToken(), loadOptionalToken(), loadOptionalNode(), loadOptionalToken(), startOffset, endOffset);
            default:
                throw new Error("Unknown node type: " + type);
        }
    }

    private void expect(byte value) {
        byte b = buffer.get();
        if (b != value) {
            throw new Error("Expected " + value + " but was " + b + " at position " + buffer.position());
        }
    }

}
// @formatter:on
