/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1151452.formula.compiler;

import csheets.core.Cell;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.*;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.compiler.IllegalFunctionCallException;
import csheets.core.formula.lang.CellReference;
import csheets.core.formula.lang.RangeReference;
import csheets.core.formula.lang.ReferenceOperation;
import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1140822.beanshellwindow.BeanShellInstance;
import lapr4.blue.s1.lang.n1140822.beanshellwindow.BeanShellLoader;
import lapr4.blue.s1.lang.n1151452.formula.lang.Language;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperation;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperator;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;
import org.antlr.v4.runtime.Token;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import lapr4.green.s3.lang.n1150532.variables.Variable;

/**
 * Represents the Formula Visitor (ANTLR4).
 *
 * @author Diana Silva {1151088@isep.ipp.pt]} on 03/06/17
 * @author Daniel Gonçalves [1151452@isep.ipp.pt] on 01/06/17.
 *
 * @author Guilherme Ferreira 1150623 corrected to work with 'Variable' class and VarContentor
 * @author Ricardo Catalão (1150385) on 08/06/2017
 * @author jrt
 * 
 * 
 * @author Manuel Meireles (1150532):
 * <ul>
 * <li>I've changed the Variable class (from
 * {@link lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable} to
 * {@link lapr4.green.s3.lang.n1150532.variables.Variable}) in order to allow
 * indexes.</li>
 * <li>I've updated the methods "visitAtom" and "visitAssignment" in order to
 * handle the new variable class.</li>
 * </ul>
*/
@SuppressWarnings("Duplicates")
public class FormulaEvalVisitor extends BlueFormulaBaseVisitor<Expression> {
    private Cell cell = null;
    private int numberOfErrors;
    private final StringBuilder errorBuffer;
    private final UIController uiController;

    /**The starter lexical rule for temporary variables*/
    private static final char TEMP_VAR_STARTER='_';

    /**The temporary variables manager*/
    private final VarContentor temp_contentor;

    public FormulaEvalVisitor(Cell cell, UIController uiController) {
        this.cell = cell;
        this.uiController = uiController;
        numberOfErrors = 0;
        errorBuffer = new StringBuilder();
        temp_contentor = new VarContentor();
    }

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    public String getErrorsMessage() {
        return errorBuffer.toString();
    }

    @Override
    public Expression visitScript(BlueFormulaParser.ScriptContext ctx) {
        super.visitScript(ctx);

        StringBuilder builder = new StringBuilder();
        for(int i=0; i<ctx.getChildCount(); i++){
            builder.append(ctx.getChild(i).getText());
            builder.append(" "); // WhiteSpace seems to always be ignored so... to not break things up, this was placed
        }

        return new Literal(new Value(builder.toString()));
    }

    /**
     * Handles the behaviour of visiting the ShellScript grammar node. The shellScript node will have the information
     * needed for what code the beanShell should execute, but also how to execute it.
     *
     * The node can start with 2 headers. (1) "&lt;![SHELL[" or (2) "&lt;[SHELL[".
     * If (1) is chosen, the bean shell code should be run asynchronously and the result of visiting this node is
     * null. If (2) is chosen, the code should be run synchronously and the result of visiting this node is the return
     * value of visiting the bean shell code (normally, the value of the last instruction executed).
     */
    @Override
    public Expression visitShellscript(BlueFormulaParser.ShellscriptContext ctx) {
        BeanShellLoader loader = new BeanShellLoader();

        Literal literal = (Literal)visit(ctx.getChild(1));
        String code = literal.toString().substring(1, literal.toString().length() - 1);
        BeanShellInstance shell = loader.create(code, uiController, temp_contentor);

        if(ctx.getChild(0).getText().charAt(1) == '!'){ // It should run asynchronously
            shell.setAsynchronous();
        }

        return shell;
    }

    @Override
    public Expression visitExpression(BlueFormulaParser.ExpressionContext ctx) {
        return visit(ctx.comparison());
    }

    @Override
    public Expression visitComparison(BlueFormulaParser.ComparisonContext ctx) {

        if (ctx.getChildCount() == 3) {
            try {
                BinaryOperator operator = Language.getInstance().getBinaryOperator(ctx.getChild(1).getText());
                return new BinaryOperation(
                        visit(ctx.getChild(0)),
                        operator,
                        visit(ctx.getChild(2))
                );
            } catch (UnknownElementException ex) {
                addVisitError(ex.getMessage());
            }
        }

        if (ctx.getChild(0) instanceof BlueFormulaParser.ConcatenationContext) {
            return visit(ctx.concatenation(0));
        }

        return visit(ctx.for_loop());
    }

    @Override
    public Expression visitConcatenation(BlueFormulaParser.ConcatenationContext ctx) {
        try {
            if (ctx.getChildCount() == 2) { // Convert unary operation
                int operatorIndex = 0, operandIndex = 1;  // Assume operator on the left

                if (ctx.PERCENT() != null) { // Conclude that operator is on the right
                    operatorIndex = 1;
                    operandIndex = 0;
                }

                return new UnaryOperation(
                        Language.getInstance().getUnaryOperator(ctx.getChild(operatorIndex).getText()),
                        visit(ctx.getChild(operandIndex))
                );

            } else if (ctx.getChildCount() == 3) {
                // Convert binary operation
                BinaryOperator operator = Language.getInstance().getBinaryOperator(ctx.getChild(1).getText());
                return new BinaryOperation(
                        visit(ctx.getChild(0)),
                        operator,
                        visit(ctx.getChild(2))
                );
            }

        } catch (FormulaCompilationException ex) {
            addVisitError(ex.getMessage());
        }

        return visitChildren(ctx);
    }

    /**
     * Updated method to consider temporary variables
     *
     * @author Diana Silva[1151088@isep.ipp.pt]
     * @param ctx tree parse
     * @return the visitor result
     */
    @Override
    public Expression visitAtom(BlueFormulaParser.AtomContext ctx) {
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(1));
        }
        else if (ctx.VARIABLE_NAME() != null) {

            String tempVarName=ctx.VARIABLE_NAME().getText();

            try {
                return getExpressionOfVariable(tempVarName, temp_contentor);
            } catch (IllegalArgumentException ex) {
                addVisitError(ex.getLocalizedMessage());
            }

        }
        else if (ctx.G_VARIABLE_NAME() != null){
            String tempVarName=ctx.G_VARIABLE_NAME().getText();

            try {
                return getExpressionOfVariable(tempVarName, uiController.getActiveWorkbook().globalVariables());
            } catch (IllegalArgumentException ex) {
                addVisitError(ex.getLocalizedMessage());
            }
        }

        return visitChildren(ctx);
    }

    @Override
    public Expression visitFunction_call(BlueFormulaParser.Function_callContext ctx) {
        // Convert function call
        Function function = null;
        try {
            function = Language.getInstance().getFunction(ctx.getChild(0).getText());
        } catch (UnknownElementException ex) {
            addVisitError(ex.getMessage());
        }

        if (function != null) {
            try {
                List<Expression> args = new ArrayList<>();
                if (ctx.getChildCount() > 3) {
                    for (int nChild = 2; nChild < ctx.getChildCount() - 1; nChild += 2) {
                        args.add(visit(ctx.getChild(nChild)));
                    }
                }
                Expression[] argArray = args.toArray(new Expression[args.size()]);
                return new FunctionCall(function, argArray);
            } catch (IllegalFunctionCallException ex) {
                addVisitError(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public Expression visitReference(BlueFormulaParser.ReferenceContext ctx) {
        try {
            if (ctx.getChildCount() == 3) {
                BinaryOperator operator = Language.getInstance().getBinaryOperator(ctx.getChild(1).getText());
                return new ReferenceOperation(
                        new CellReference(cell.getSpreadsheet(), ctx.getChild(0).getText()),
                        (RangeReference) operator,
                        new CellReference(cell.getSpreadsheet(), ctx.getChild(2).getText())
                );
            } else {
                if(ctx.getText().equals("!CELL")){
                    return new CellReference(cell.getSpreadsheet(), cell.getAddress().toString());
                }else{
                    return new CellReference(cell.getSpreadsheet(), ctx.getText());
                }
            }
        } catch (ParseException | UnknownElementException ex) {
            addVisitError(ex.getMessage());
        }
        return null;
    }

    @Override
    public Expression visitLiteral(BlueFormulaParser.LiteralContext ctx) {
        Token t = (Token) ctx.getChild(0).getPayload();

        if (t.getType() == BlueFormulaParser.NUMBER) {
            return new Literal(Value.parseValue(ctx.getText()));
        } else {
            if (t.getType() == BlueFormulaParser.STRING) {

                String value = ctx.getText().substring(1, ctx.getText().length() - 1); // Remove Quotes
                return new Literal(Value.parseValue(value, Value.Type.BOOLEAN, Value.Type.DATE));
            } 
        }

        return null;
    }

    @Override
    /**
     * Updated method to consider a temporay variable.
     *
     * @author Diana Silva [1151088@isep.ipp.pt]
     */
    public Expression visitAssignment(BlueFormulaParser.AssignmentContext ctx) {
        if (ctx.ASSIGN() != null) {

            //it´s a cell reference assignment
            if (ctx.reference() != null) {
                try {
                    // Convert binary operation
                    BinaryOperator operator = Language.getInstance().getBinaryOperator(ctx.getChild(2).getText());
                    return new BinaryOperation(
                            visit(ctx.getChild(1)),
                            operator,
                            visit(ctx.getChild(3))
                    );
                } catch (UnknownElementException ex) {
                    addVisitError(ex.getMessage());
                }


            } else if (ctx.VARIABLE_NAME() != null) {
                //it´s a temporary variable
                String name = ctx.VARIABLE_NAME().getText();
                return setExpressionOfVariable(name, visit(ctx.comparison()),temp_contentor);
            }else if(ctx.G_VARIABLE_NAME() != null){
                //it's a global variable
                String name = ctx.G_VARIABLE_NAME().getText();
                return setExpressionOfVariable(name, visit(ctx.comparison()), uiController.getActiveWorkbook().globalVariables());
            }
        }

        return visitChildren(ctx);
    }

    @Override
    public Expression visitBlock(BlueFormulaParser.BlockContext ctx) {

        if (ctx.L_CURLY_BRACKET() != null) {
            try {
                // The L_CURLY_BRACKET is the father node
                // All the other nodes of the block are children.
                // The last children node is always the R_CURLY_BRACKET
                // Therefore all the other children will be expressions to be also converted and
                // executed by a "block executor" (skip ";")
                Expression expressions[] = new Expression[ctx.getChildCount() / 2];

                // #1 Convert all the child nodes
                for (int nChild = 1; nChild < ctx.getChildCount(); nChild += 2) {

                    expressions[nChild / 2] = visit(ctx.getChild(nChild));
                }

                // #2 return an instance of the new NaryOperation Class
                NaryOperator operator = Language.getInstance().getNaryOperator(ctx.getChild(0).getText());
                return new NaryOperation(operator, expressions);

            } catch (UnknownElementException ex) {
                addVisitError(ex.getMessage());
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Expression visitFor_loop(BlueFormulaParser.For_loopContext ctx) {

        if (ctx.FOR() != null) {
            try {
                // Get n-ary operator that identifies a for loop
                String operatorID = ctx.FOR().getText().toLowerCase();
                NaryOperator operator = Language.getInstance().getNaryOperator(operatorID);

                // Get # of expressions by: dividing by 2 to not count for SEMI-COLON/BRACKETS & - 1 for the "for"
                Expression expressions[] = new Expression[(ctx.getChildCount() / 2) - 1];

                // #1 Convert all the child nodes
                for (int nChild = 2, i = 0; i < expressions.length; nChild += 2, i++) {

                    expressions[i] = visit(ctx.getChild(nChild));
                }

                return new NaryOperation(operator, expressions);

            } catch (UnknownElementException ex) {
                addVisitError(ex.getMessage());
            }
        }
        return visitChildren(ctx);
    }

    /**
     * Adds an error to the error buffer.
     *
     * @param msg the message to append
     */
    private void addVisitError(String msg) {
        errorBuffer.append(msg).append("\n");
        numberOfErrors++;
    }
    

    /**
     * It provides the expression stored in a variable from the container.
     * 
     * @param visitText The text resulting from the visit. It must contain the
     * variable's name and, optionally, the index number within square brackets.
     * @param container The container of the variables. It might be the workbook
     * container for global variables or the local container for temporary
     * variables.
     * @return It returns the expression retrieved from the variable's index.
     */
    private Expression getExpressionOfVariable(String visitText, VarContentor container){
        Expression theExpression;
        String[] variableInfo = visitText.split("\\[");
        if(variableInfo.length==2){
            try{
                int index = Integer.parseInt(variableInfo[1].substring(0, (variableInfo[1].length()-1)));
                theExpression = container.getExpressionOfVariable(variableInfo[0], index);
            } catch(NumberFormatException | IndexOutOfBoundsException ex){
                throw new IllegalArgumentException("Unknown Index.");
            }
        } else {
            theExpression = container.getExpressionOfVariable(variableInfo[0], Variable.DEFAULT_INDEX);
        }
        return theExpression;
    }

    /**
     * It stores the expression in a variable from the container.
     * 
     * @param visitText The text resulting from the visit. It must contain the
     * variable's name and, optionally, the index number within square brackets.
     * @param theExpression The expression to be stored.
     * @param container The container of the variables. It might be the workbook
     * container for global variables or the local container for temporary
     * variables.
     * @return It returns the same expression that is received.
     */
    private Expression setExpressionOfVariable(String visitText, Expression theExpression, VarContentor container){
        int index;
        String[] variableInfo = visitText.split("\\[");
        if(variableInfo.length==2){
            try{
                index = Integer.parseInt(variableInfo[1].substring(0, (variableInfo[1].length()-1)));
            } catch(NumberFormatException | IndexOutOfBoundsException ex){
                throw new IllegalArgumentException("Unknown Index.");
            }
        } else {
            index = Variable.DEFAULT_INDEX;
        }
        container.update(variableInfo[0], index, theExpression);
        return theExpression;
    }

}

