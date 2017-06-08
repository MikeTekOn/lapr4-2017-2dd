// Generated from /home/k4rd050/Documents/LAPR4/lapr4-2017-2dd/src/main/antlr4/lapr4/blue/s1/lang/n1151452/formula/compiler/BlueFormula.g4 by ANTLR 4.7
package lapr4.blue.s1.lang.n1151452.formula.compiler;

    package lapr4.blue.s1.lang.n1151452.formula.compiler;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BlueFormulaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BlueFormulaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(BlueFormulaParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(BlueFormulaParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#concatenation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatenation(BlueFormulaParser.ConcatenationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(BlueFormulaParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_loop(BlueFormulaParser.For_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(BlueFormulaParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(BlueFormulaParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(BlueFormulaParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReference(BlueFormulaParser.ReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link BlueFormulaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(BlueFormulaParser.LiteralContext ctx);
}