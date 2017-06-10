package lapr4.red.s2.lang.n1150690.formula.compiler;

import csheets.core.Cell;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.ExpressionCompiler;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Collections;
import java.util.List;

/**
 * A compiler that generates Excel-style formulas from strings.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
@SuppressWarnings("Duplicates")
public class ExcelMonetaryExpressionCompiler implements ExpressionCompiler {

    /**
     * The character that signals that a cell's content is a formula to
     * manipulaty Monetary values. ('#')
     */
    private static final char FORMULA_STARTER = '#';

    /**
     * Creates the Excel expression compiler.
     */
    public ExcelMonetaryExpressionCompiler() {
    }

    @Override
    public char getStarter() {
        return FORMULA_STARTER;
    }

    @Override
    public Expression compile(Cell cell, String source) throws FormulaCompilationException {
        // Creates the lexer and parser
        //noinspection deprecation
        ANTLRInputStream input = new ANTLRInputStream(source);

        // create the buffer of tokens between the lexer and parser 
        MonetaryLanguageLexer lexer = new MonetaryLanguageLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MonetaryLanguageParser parser = new MonetaryLanguageParser(tokens);

        MonetaryLanguageErrorListener errorListener = new MonetaryLanguageErrorListener();
        parser.removeErrorListeners(); // remove default ConsoleErrorListener
        parser.addErrorListener(errorListener); // add ours

        // Attempts to match an expression
        ParseTree tree = parser.formula();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new FormulaCompilationException(errorListener.getErrorMessage());
        }

        // Visit the expression and returns it
        MonetaryLanguageBaseVisitorImpl eval = new MonetaryLanguageBaseVisitorImpl(cell);
        Expression result = eval.visit(tree);
        if (eval.getNumberOfErrors() > 0) {
            throw new FormulaCompilationException(eval.getErrorsMessage());
        }

        return result;
    }

    public static class MonetaryLanguageErrorListener extends BaseErrorListener {

        private StringBuilder buf;

        private String getErrorMessage() {
            return buf.toString();
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol,
                int line, int charPositionInLine,
                String msg,
                RecognitionException e) {
            List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
            Collections.reverse(stack);

            buf = new StringBuilder();
            buf.append("line ").append(line).append(":").append(charPositionInLine).append(": ").append(msg);
        }
    }
}
