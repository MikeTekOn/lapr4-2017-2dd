///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package lapr4.green.s3.lang.n1150838.TablesAndFilters;
//
//import csheets.ui.ctrl.UIController;
//
///**
// *
// * @author PC
// */
//public class TableCompiler {
//
//    /**
//     * The character that signals that a cell's content is a expression ('=')
//     */
//    private static final char EXPRESSION_STARTER = '=';
//
//    private static final TableCompiler instance = new TableCompiler();
//
//    private UIController uiController;
//
//    /**
//     * Returns the singleton instance.
//     *
//     * @return the singleton instance
//     */
//    public static TableCompiler getInstance() {
//        return instance;
//    }
//
//    /**
//     * Creates the Excel expression compiler.
//     */
//    private TableCompiler() {
//    }
//
//    public char getStarter() {
//        return EXPRESSION_STARTER;
//    }
//
// 
//    public Expression compile(Cell cell, String source, UIController uiController) throws FormulaCompilationException {
//         Creates the lexer and parser
//        noinspection deprecation
//        ANTLRInputStream input = new ANTLRInputStream(source);
//
//         create the buffer of tokens between the lexer and parser 
//        BlueFormulaLexer lexer = new BlueFormulaLexer(input);
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//
//        BlueFormulaParser parser = new BlueFormulaParser(tokens);
//
//        FormulaErrorListener formulaErrorListener = new FormulaErrorListener();
//        parser.removeErrorListeners(); // remove default ConsoleErrorListener
//        parser.addErrorListener(formulaErrorListener); // add ours
//
//         Attempts to match an expression
//        ParseTree tree = parser.expression();
//        if (parser.getNumberOfSyntaxErrors() > 0) {
//            throw new FormulaCompilationException(formulaErrorListener.getErrorMessage());
//        }
//
//         Visit the expression and returns it
//        FormulaEvalVisitor eval = new FormulaEvalVisitor(cell, uiController);
//        Expression result = eval.visit(tree);
//        if (eval.getNumberOfErrors() > 0) {
//            throw new FormulaCompilationException(eval.getErrorsMessage());
//        }
//
//        return result;
//    }
//
//    public static class FormulaErrorListener extends BaseErrorListener {
//
//        private StringBuilder buf;
//
//        private String getErrorMessage() {
//            return buf.toString();
//        }
//
//        @Override
//        public void syntaxError(Recognizer<?, ?> recognizer,
//                Object offendingSymbol,
//                int line, int charPositionInLine,
//                String msg,
//                RecognitionException e) {
//            List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
//            Collections.reverse(stack);
//
//            buf = new StringBuilder();
//            buf.append("line ").append(line).append(":").append(charPositionInLine).append(": ").append(msg);
//        }
//    }
//}
