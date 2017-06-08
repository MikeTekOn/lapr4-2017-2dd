// Generated from /home/k4rd050/Documents/LAPR4/lapr4-2017-2dd/src/main/antlr4/lapr4/blue/s1/lang/n1151452/formula/compiler/BlueFormula.g4 by ANTLR 4.7
package lapr4.blue.s1.lang.n1151452.formula.compiler;

    package lapr4.blue.s1.lang.n1151452.formula.compiler;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BlueFormulaParser}.
 */
public interface BlueFormulaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(BlueFormulaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(BlueFormulaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(BlueFormulaParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(BlueFormulaParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation(BlueFormulaParser.ConcatenationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation(BlueFormulaParser.ConcatenationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(BlueFormulaParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(BlueFormulaParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterFor_loop(BlueFormulaParser.For_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitFor_loop(BlueFormulaParser.For_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(BlueFormulaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(BlueFormulaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(BlueFormulaParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(BlueFormulaParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(BlueFormulaParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(BlueFormulaParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(BlueFormulaParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(BlueFormulaParser.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link BlueFormulaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(BlueFormulaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BlueFormulaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(BlueFormulaParser.LiteralContext ctx);
}