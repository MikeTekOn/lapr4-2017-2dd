/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 * ATB (April, 2014): Updated to use antlr3 generated parser and lexer
 * JRT (May, 2017): Uptated to use antlr4
 */
package lapr4.blue.s1.lang.n1151159.macros.compiler;

import csheets.core.Spreadsheet;
import csheets.core.formula.Expression;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import lapr4.blue.s1.lang.n1151159.macros.Macro;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A compiler that generates Excel-style formulas from strings.
 *
 * @author Einar Pehrson
 * @author Ivo Ferro
 */
public class MacroCompiler {

    protected MacroCompiler(){

    }

    /**
     * The singleton instance
     */
    private static final MacroCompiler instance = new MacroCompiler();

    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance
     */
    public static MacroCompiler getInstance() {
        return instance;
    }

    public Macro compile(Spreadsheet spreadsheet, UIController uiController, String source, String macroName, ArrayList<String> macrosUsed) throws MacroCompilationException {
        if (macrosUsed.contains(macroName)) {
            return null;
        }
        macrosUsed.add(macroName);
        return compile(spreadsheet, uiController, source);
    }

    public Macro compile(Spreadsheet spreadsheet, UIController uiController, String source) throws MacroCompilationException {

        // Creates the lexer and parser
        // Although the ANTLRInputStream is deprecated, the core is
        // already using it. Mixing both makes the performance worse.
        ANTLRInputStream input = new ANTLRInputStream(source);

        // create the buffer of tokens between the lexer and parser 
        MacroLexer lexer = new MacroLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MacroParser parser = new MacroParser(tokens);

        MacroErrorListener macroErrorListener = new MacroErrorListener();
        parser.removeErrorListeners(); // remove default ConsoleErrorListener
        parser.addErrorListener(macroErrorListener); // add ours

        // Attempts to match an expression
        ParseTree tree = parser.macro();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new MacroCompilationException(macroErrorListener.getErrorMessage());
        }

        // Visit the expression and returns it
        MacroEvalVisitor eval = new MacroEvalVisitor(spreadsheet, uiController);

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

    /**
     * Listener for the macro errors.
     */
    public static class MacroErrorListener extends BaseErrorListener {

        /**
         * Buffer for the macro error message.
         */
        private StringBuilder buf;

        /**
         * Gets the error message.
         *
         * @return error message
         */
        public String getErrorMessage() {
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
