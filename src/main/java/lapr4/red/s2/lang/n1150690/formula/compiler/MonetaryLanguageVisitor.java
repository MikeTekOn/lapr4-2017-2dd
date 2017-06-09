// Generated from MonetaryLanguage.g4 by ANTLR 4.5.3

    package lapr4.red.s2.lang.n1150690.formula.compiler;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MonetaryLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MonetaryLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MonetaryLanguageParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(MonetaryLanguageParser.FormulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link MonetaryLanguageParser#currency}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrency(MonetaryLanguageParser.CurrencyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MonetaryLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MonetaryLanguageParser.ExpressionContext ctx);
}