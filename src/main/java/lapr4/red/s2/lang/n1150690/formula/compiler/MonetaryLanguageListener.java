// Generated from MonetaryLanguage.g4 by ANTLR 4.5.3

    package lapr4.red.s2.lang.n1150690.formula.compiler;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MonetaryLanguageParser}.
 */
public interface MonetaryLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MonetaryLanguageParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(MonetaryLanguageParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonetaryLanguageParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(MonetaryLanguageParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonetaryLanguageParser#currency}.
	 * @param ctx the parse tree
	 */
	void enterCurrency(MonetaryLanguageParser.CurrencyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonetaryLanguageParser#currency}.
	 * @param ctx the parse tree
	 */
	void exitCurrency(MonetaryLanguageParser.CurrencyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonetaryLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MonetaryLanguageParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonetaryLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MonetaryLanguageParser.ExpressionContext ctx);
}