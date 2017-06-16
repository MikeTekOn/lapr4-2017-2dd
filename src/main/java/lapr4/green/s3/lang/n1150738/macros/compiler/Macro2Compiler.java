package lapr4.green.s3.lang.n1150738.macros.compiler;

import csheets.core.Spreadsheet;
import csheets.core.formula.Expression;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151159.macros.Macro;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by henri on 6/14/2017.
 */
public class Macro2Compiler extends lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompiler{
    protected Macro2Compiler(){

    }

    /**
     * The singleton instance
     */
    private static final Macro2Compiler instance = new Macro2Compiler();

    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance
     */
    public static Macro2Compiler getInstance() {
        return instance;
    }

    public Macro compile(Spreadsheet spreadsheet, UIController uiController, String source, String macroName, ArrayList<String> macrosUsed) throws MacroCompilationException {
        if (macrosUsed.contains(macroName)) {
            return null;
        }
        macrosUsed.add(macroName);
        return compile(spreadsheet, uiController, source);
    }

    @Override
    public Macro compile(Spreadsheet spreadsheet, UIController uiController, String source) throws MacroCompilationException {
        // Creates the lexer and parser
        // Although the ANTLRInputStream is deprecated, the core is
        // already using it. Mixing both makes the performance worse.
        ANTLRInputStream input = new ANTLRInputStream(source);

        // create the buffer of tokens between the lexer and parser
        Macro2Lexer lexer = new Macro2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Macro2Parser parser = new Macro2Parser(tokens);

        MacroErrorListener macroErrorListener = new MacroErrorListener();
        parser.removeErrorListeners(); // remove default ConsoleErrorListener
        parser.addErrorListener(macroErrorListener); // add ours

        // Attempts to match an expression
        ParseTree tree = parser.macro();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new MacroCompilationException(macroErrorListener.getErrorMessage());
        }

        // Visit the expression and returns it
        Macro2EvalVisitor eval = new Macro2EvalVisitor(spreadsheet, uiController);

        List<Expression> expressions = new LinkedList<>();
        for (int i = 0; i < tree.getChildCount(); i++) {
            // ignores terminal rule
            if (!(tree.getChild(i) instanceof TerminalNode)) {
                expressions.add(eval.visit(tree.getChild(i)));
            }
        }

        // handle errors
        if (eval.getNumberOfErrors() > 0) {
            throw new MacroCompilationException(eval.getErrorsMessage());
        }

        return new Macro(expressions);
    }

}
