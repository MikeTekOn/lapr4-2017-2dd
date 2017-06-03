package lapr4.blue.s1.lang.n1151031.formulastools;

import csheets.core.Cell;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.ExpressionCompiler;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Collections;
import java.util.List;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaLexer;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaParser;
import lapr4.blue.s1.lang.n1151452.formula.compiler.FormulaEvalVisitor;

/**
 * A compiler that generates Excel-style expressions from strings.
 *
 * @author Tiago Correia
 * @author Einar Pehrson
 */
@SuppressWarnings("Duplicates")
public class ConditionalStyleCompiler implements ExpressionCompiler {

    /**
     * The character that signals that a cell's content is a expression ('=')
     */
    private static final char EXPRESSION_STARTER = '=';

    private static final ConditionalStyleCompiler instance = new ConditionalStyleCompiler();

    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance
     */
    public static ConditionalStyleCompiler getInstance() {
        return instance;
    }

    /**
     * Creates the Excel expression compiler.
     */
    private ConditionalStyleCompiler() {
    }

    @Override
    public char getStarter() {
        return EXPRESSION_STARTER;
    }

    @Override
    public Expression compile(Cell cell, String source) throws FormulaCompilationException {
        // Creates the lexer and parser
        //noinspection deprecation
        ANTLRInputStream input = new ANTLRInputStream(source);

        // create the buffer of tokens between the lexer and parser 
        BlueFormulaLexer lexer = new BlueFormulaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        BlueFormulaParser parser = new BlueFormulaParser(tokens);

        FormulaErrorListener formulaErrorListener = new FormulaErrorListener();
        parser.removeErrorListeners(); // remove default ConsoleErrorListener
        parser.addErrorListener(formulaErrorListener); // add ours

        // Attempts to match an expression
        ParseTree tree = parser.expression();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new FormulaCompilationException(formulaErrorListener.getErrorMessage());
        }

        // Visit the expression and returns it
        FormulaEvalVisitor eval = new FormulaEvalVisitor(cell);
        Expression result = eval.visit(tree);
        if (eval.getNumberOfErrors() > 0) {
            throw new FormulaCompilationException(eval.getErrorsMessage());
        }

        return result;
    }

    public static class FormulaErrorListener extends BaseErrorListener {

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
