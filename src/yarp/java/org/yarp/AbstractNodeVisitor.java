/******************************************************************************/
/* This file is generated by the bin/template script and should not be        */
/* modified manually. See                                                     */
/* templates/java/org/yarp/AbstractNodeVisitor.java.erb                       */
/* if you are looking to modify the                                           */
/* template                                                                   */
/******************************************************************************/
package org.yarp;

// GENERATED BY AbstractNodeVisitor.java.erb
public abstract class AbstractNodeVisitor<T> {

    protected abstract T defaultVisit(Nodes.Node node);

    public T visitAliasNode(Nodes.AliasNode node) {
        return defaultVisit(node);
    }

    public T visitAlternationPatternNode(Nodes.AlternationPatternNode node) {
        return defaultVisit(node);
    }

    public T visitAndNode(Nodes.AndNode node) {
        return defaultVisit(node);
    }

    public T visitArgumentsNode(Nodes.ArgumentsNode node) {
        return defaultVisit(node);
    }

    public T visitArrayNode(Nodes.ArrayNode node) {
        return defaultVisit(node);
    }

    public T visitArrayPatternNode(Nodes.ArrayPatternNode node) {
        return defaultVisit(node);
    }

    public T visitAssocNode(Nodes.AssocNode node) {
        return defaultVisit(node);
    }

    public T visitAssocSplatNode(Nodes.AssocSplatNode node) {
        return defaultVisit(node);
    }

    public T visitBackReferenceReadNode(Nodes.BackReferenceReadNode node) {
        return defaultVisit(node);
    }

    public T visitBeginNode(Nodes.BeginNode node) {
        return defaultVisit(node);
    }

    public T visitBlockArgumentNode(Nodes.BlockArgumentNode node) {
        return defaultVisit(node);
    }

    public T visitBlockNode(Nodes.BlockNode node) {
        return defaultVisit(node);
    }

    public T visitBlockParameterNode(Nodes.BlockParameterNode node) {
        return defaultVisit(node);
    }

    public T visitBlockParametersNode(Nodes.BlockParametersNode node) {
        return defaultVisit(node);
    }

    public T visitBreakNode(Nodes.BreakNode node) {
        return defaultVisit(node);
    }

    public T visitCallNode(Nodes.CallNode node) {
        return defaultVisit(node);
    }

    public T visitCallOperatorAndWriteNode(Nodes.CallOperatorAndWriteNode node) {
        return defaultVisit(node);
    }

    public T visitCallOperatorOrWriteNode(Nodes.CallOperatorOrWriteNode node) {
        return defaultVisit(node);
    }

    public T visitCallOperatorWriteNode(Nodes.CallOperatorWriteNode node) {
        return defaultVisit(node);
    }

    public T visitCapturePatternNode(Nodes.CapturePatternNode node) {
        return defaultVisit(node);
    }

    public T visitCaseNode(Nodes.CaseNode node) {
        return defaultVisit(node);
    }

    public T visitClassNode(Nodes.ClassNode node) {
        return defaultVisit(node);
    }

    public T visitClassVariableOperatorAndWriteNode(Nodes.ClassVariableOperatorAndWriteNode node) {
        return defaultVisit(node);
    }

    public T visitClassVariableOperatorOrWriteNode(Nodes.ClassVariableOperatorOrWriteNode node) {
        return defaultVisit(node);
    }

    public T visitClassVariableOperatorWriteNode(Nodes.ClassVariableOperatorWriteNode node) {
        return defaultVisit(node);
    }

    public T visitClassVariableReadNode(Nodes.ClassVariableReadNode node) {
        return defaultVisit(node);
    }

    public T visitClassVariableWriteNode(Nodes.ClassVariableWriteNode node) {
        return defaultVisit(node);
    }

    public T visitConstantOperatorAndWriteNode(Nodes.ConstantOperatorAndWriteNode node) {
        return defaultVisit(node);
    }

    public T visitConstantOperatorOrWriteNode(Nodes.ConstantOperatorOrWriteNode node) {
        return defaultVisit(node);
    }

    public T visitConstantOperatorWriteNode(Nodes.ConstantOperatorWriteNode node) {
        return defaultVisit(node);
    }

    public T visitConstantPathNode(Nodes.ConstantPathNode node) {
        return defaultVisit(node);
    }

    public T visitConstantPathOperatorAndWriteNode(Nodes.ConstantPathOperatorAndWriteNode node) {
        return defaultVisit(node);
    }

    public T visitConstantPathOperatorOrWriteNode(Nodes.ConstantPathOperatorOrWriteNode node) {
        return defaultVisit(node);
    }

    public T visitConstantPathOperatorWriteNode(Nodes.ConstantPathOperatorWriteNode node) {
        return defaultVisit(node);
    }

    public T visitConstantPathWriteNode(Nodes.ConstantPathWriteNode node) {
        return defaultVisit(node);
    }

    public T visitConstantReadNode(Nodes.ConstantReadNode node) {
        return defaultVisit(node);
    }

    public T visitDefNode(Nodes.DefNode node) {
        return defaultVisit(node);
    }

    public T visitDefinedNode(Nodes.DefinedNode node) {
        return defaultVisit(node);
    }

    public T visitElseNode(Nodes.ElseNode node) {
        return defaultVisit(node);
    }

    public T visitEmbeddedStatementsNode(Nodes.EmbeddedStatementsNode node) {
        return defaultVisit(node);
    }

    public T visitEmbeddedVariableNode(Nodes.EmbeddedVariableNode node) {
        return defaultVisit(node);
    }

    public T visitEnsureNode(Nodes.EnsureNode node) {
        return defaultVisit(node);
    }

    public T visitFalseNode(Nodes.FalseNode node) {
        return defaultVisit(node);
    }

    public T visitFindPatternNode(Nodes.FindPatternNode node) {
        return defaultVisit(node);
    }

    public T visitFloatNode(Nodes.FloatNode node) {
        return defaultVisit(node);
    }

    public T visitForNode(Nodes.ForNode node) {
        return defaultVisit(node);
    }

    public T visitForwardingArgumentsNode(Nodes.ForwardingArgumentsNode node) {
        return defaultVisit(node);
    }

    public T visitForwardingParameterNode(Nodes.ForwardingParameterNode node) {
        return defaultVisit(node);
    }

    public T visitForwardingSuperNode(Nodes.ForwardingSuperNode node) {
        return defaultVisit(node);
    }

    public T visitGlobalVariableOperatorAndWriteNode(Nodes.GlobalVariableOperatorAndWriteNode node) {
        return defaultVisit(node);
    }

    public T visitGlobalVariableOperatorOrWriteNode(Nodes.GlobalVariableOperatorOrWriteNode node) {
        return defaultVisit(node);
    }

    public T visitGlobalVariableOperatorWriteNode(Nodes.GlobalVariableOperatorWriteNode node) {
        return defaultVisit(node);
    }

    public T visitGlobalVariableReadNode(Nodes.GlobalVariableReadNode node) {
        return defaultVisit(node);
    }

    public T visitGlobalVariableWriteNode(Nodes.GlobalVariableWriteNode node) {
        return defaultVisit(node);
    }

    public T visitHashNode(Nodes.HashNode node) {
        return defaultVisit(node);
    }

    public T visitHashPatternNode(Nodes.HashPatternNode node) {
        return defaultVisit(node);
    }

    public T visitIfNode(Nodes.IfNode node) {
        return defaultVisit(node);
    }

    public T visitImaginaryNode(Nodes.ImaginaryNode node) {
        return defaultVisit(node);
    }

    public T visitInNode(Nodes.InNode node) {
        return defaultVisit(node);
    }

    public T visitInstanceVariableOperatorAndWriteNode(Nodes.InstanceVariableOperatorAndWriteNode node) {
        return defaultVisit(node);
    }

    public T visitInstanceVariableOperatorOrWriteNode(Nodes.InstanceVariableOperatorOrWriteNode node) {
        return defaultVisit(node);
    }

    public T visitInstanceVariableOperatorWriteNode(Nodes.InstanceVariableOperatorWriteNode node) {
        return defaultVisit(node);
    }

    public T visitInstanceVariableReadNode(Nodes.InstanceVariableReadNode node) {
        return defaultVisit(node);
    }

    public T visitInstanceVariableWriteNode(Nodes.InstanceVariableWriteNode node) {
        return defaultVisit(node);
    }

    public T visitIntegerNode(Nodes.IntegerNode node) {
        return defaultVisit(node);
    }

    public T visitInterpolatedRegularExpressionNode(Nodes.InterpolatedRegularExpressionNode node) {
        return defaultVisit(node);
    }

    public T visitInterpolatedStringNode(Nodes.InterpolatedStringNode node) {
        return defaultVisit(node);
    }

    public T visitInterpolatedSymbolNode(Nodes.InterpolatedSymbolNode node) {
        return defaultVisit(node);
    }

    public T visitInterpolatedXStringNode(Nodes.InterpolatedXStringNode node) {
        return defaultVisit(node);
    }

    public T visitKeywordHashNode(Nodes.KeywordHashNode node) {
        return defaultVisit(node);
    }

    public T visitKeywordParameterNode(Nodes.KeywordParameterNode node) {
        return defaultVisit(node);
    }

    public T visitKeywordRestParameterNode(Nodes.KeywordRestParameterNode node) {
        return defaultVisit(node);
    }

    public T visitLambdaNode(Nodes.LambdaNode node) {
        return defaultVisit(node);
    }

    public T visitLocalVariableOperatorAndWriteNode(Nodes.LocalVariableOperatorAndWriteNode node) {
        return defaultVisit(node);
    }

    public T visitLocalVariableOperatorOrWriteNode(Nodes.LocalVariableOperatorOrWriteNode node) {
        return defaultVisit(node);
    }

    public T visitLocalVariableOperatorWriteNode(Nodes.LocalVariableOperatorWriteNode node) {
        return defaultVisit(node);
    }

    public T visitLocalVariableReadNode(Nodes.LocalVariableReadNode node) {
        return defaultVisit(node);
    }

    public T visitLocalVariableWriteNode(Nodes.LocalVariableWriteNode node) {
        return defaultVisit(node);
    }

    public T visitMatchPredicateNode(Nodes.MatchPredicateNode node) {
        return defaultVisit(node);
    }

    public T visitMatchRequiredNode(Nodes.MatchRequiredNode node) {
        return defaultVisit(node);
    }

    public T visitMissingNode(Nodes.MissingNode node) {
        return defaultVisit(node);
    }

    public T visitModuleNode(Nodes.ModuleNode node) {
        return defaultVisit(node);
    }

    public T visitMultiWriteNode(Nodes.MultiWriteNode node) {
        return defaultVisit(node);
    }

    public T visitNextNode(Nodes.NextNode node) {
        return defaultVisit(node);
    }

    public T visitNilNode(Nodes.NilNode node) {
        return defaultVisit(node);
    }

    public T visitNoKeywordsParameterNode(Nodes.NoKeywordsParameterNode node) {
        return defaultVisit(node);
    }

    public T visitNumberedReferenceReadNode(Nodes.NumberedReferenceReadNode node) {
        return defaultVisit(node);
    }

    public T visitOptionalParameterNode(Nodes.OptionalParameterNode node) {
        return defaultVisit(node);
    }

    public T visitOrNode(Nodes.OrNode node) {
        return defaultVisit(node);
    }

    public T visitParametersNode(Nodes.ParametersNode node) {
        return defaultVisit(node);
    }

    public T visitParenthesesNode(Nodes.ParenthesesNode node) {
        return defaultVisit(node);
    }

    public T visitPinnedExpressionNode(Nodes.PinnedExpressionNode node) {
        return defaultVisit(node);
    }

    public T visitPinnedVariableNode(Nodes.PinnedVariableNode node) {
        return defaultVisit(node);
    }

    public T visitPostExecutionNode(Nodes.PostExecutionNode node) {
        return defaultVisit(node);
    }

    public T visitPreExecutionNode(Nodes.PreExecutionNode node) {
        return defaultVisit(node);
    }

    public T visitProgramNode(Nodes.ProgramNode node) {
        return defaultVisit(node);
    }

    public T visitRangeNode(Nodes.RangeNode node) {
        return defaultVisit(node);
    }

    public T visitRationalNode(Nodes.RationalNode node) {
        return defaultVisit(node);
    }

    public T visitRedoNode(Nodes.RedoNode node) {
        return defaultVisit(node);
    }

    public T visitRegularExpressionNode(Nodes.RegularExpressionNode node) {
        return defaultVisit(node);
    }

    public T visitRequiredDestructuredParameterNode(Nodes.RequiredDestructuredParameterNode node) {
        return defaultVisit(node);
    }

    public T visitRequiredParameterNode(Nodes.RequiredParameterNode node) {
        return defaultVisit(node);
    }

    public T visitRescueModifierNode(Nodes.RescueModifierNode node) {
        return defaultVisit(node);
    }

    public T visitRescueNode(Nodes.RescueNode node) {
        return defaultVisit(node);
    }

    public T visitRestParameterNode(Nodes.RestParameterNode node) {
        return defaultVisit(node);
    }

    public T visitRetryNode(Nodes.RetryNode node) {
        return defaultVisit(node);
    }

    public T visitReturnNode(Nodes.ReturnNode node) {
        return defaultVisit(node);
    }

    public T visitSelfNode(Nodes.SelfNode node) {
        return defaultVisit(node);
    }

    public T visitSingletonClassNode(Nodes.SingletonClassNode node) {
        return defaultVisit(node);
    }

    public T visitSourceEncodingNode(Nodes.SourceEncodingNode node) {
        return defaultVisit(node);
    }

    public T visitSourceFileNode(Nodes.SourceFileNode node) {
        return defaultVisit(node);
    }

    public T visitSourceLineNode(Nodes.SourceLineNode node) {
        return defaultVisit(node);
    }

    public T visitSplatNode(Nodes.SplatNode node) {
        return defaultVisit(node);
    }

    public T visitStatementsNode(Nodes.StatementsNode node) {
        return defaultVisit(node);
    }

    public T visitStringConcatNode(Nodes.StringConcatNode node) {
        return defaultVisit(node);
    }

    public T visitStringNode(Nodes.StringNode node) {
        return defaultVisit(node);
    }

    public T visitSuperNode(Nodes.SuperNode node) {
        return defaultVisit(node);
    }

    public T visitSymbolNode(Nodes.SymbolNode node) {
        return defaultVisit(node);
    }

    public T visitTrueNode(Nodes.TrueNode node) {
        return defaultVisit(node);
    }

    public T visitUndefNode(Nodes.UndefNode node) {
        return defaultVisit(node);
    }

    public T visitUnlessNode(Nodes.UnlessNode node) {
        return defaultVisit(node);
    }

    public T visitUntilNode(Nodes.UntilNode node) {
        return defaultVisit(node);
    }

    public T visitWhenNode(Nodes.WhenNode node) {
        return defaultVisit(node);
    }

    public T visitWhileNode(Nodes.WhileNode node) {
        return defaultVisit(node);
    }

    public T visitXStringNode(Nodes.XStringNode node) {
        return defaultVisit(node);
    }

    public T visitYieldNode(Nodes.YieldNode node) {
        return defaultVisit(node);
    }

}
