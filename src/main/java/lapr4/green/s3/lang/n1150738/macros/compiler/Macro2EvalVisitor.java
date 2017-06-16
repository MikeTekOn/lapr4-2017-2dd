package lapr4.green.s3.lang.n1150738.macros.compiler;

import csheets.core.Spreadsheet;
import csheets.core.Value;
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
import lapr4.blue.s1.lang.n1151159.macros.compiler.*;
import lapr4.blue.s1.lang.n1151452.formula.lang.Language;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperation;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperator;
import lapr4.green.s3.lang.n1150532.variables.Variable;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;
import org.antlr.v4.runtime.Token;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
@SuppressWarnings("Duplicates")
public class Macro2EvalVisitor extends Macro2BaseVisitor<Expression>{

    /**
     * The container of local vars
     */
    private final VarContentor locals;

    /**
     * The spreadsheet.
     */
    private final Spreadsheet spreadsheet;

    /**
     * The UIController
     */
    private final UIController uiController;

    /**
     * A buffer for the errors.
     */
    private final StringBuilder errorBuffer;

    /**
     * The number of errors.
     */
    private int numberOfErrors;

    /**
     * Creates and instance of MacroEvalVisitor.
     *
     * @param spreadsheet the spreadsheet
     */
    public Macro2EvalVisitor(Spreadsheet spreadsheet, UIController uiController) {
        numberOfErrors = 0;
        errorBuffer = new StringBuilder();
        this.spreadsheet = spreadsheet;
        this.uiController = uiController;
        locals = new VarContentor();
    }

    @Override
    public Expression visitMacro_invoked(Macro2Parser.Macro_invokedContext ctx) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (i != 0 && i != (ctx.getChildCount() - 1)) {
                s.append(ctx.getChild(i));
            }
        }
        return uiController.getActiveWorkbook().getMacroList().getMacroByName(s.toString());
    }

    @Override
    public Expression visitScript(Macro2Parser.ScriptContext ctx) {
        super.visitScript(ctx);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            builder.append(ctx.getChild(i).getText());
            builder.append(" "); // WhiteSpace seems to always be ignored so... to not break things up, this was placed
        }

        return new Literal(new Value(builder.toString()));
    }

    /**
     * Handles the behaviour of visiting the ShellScript grammar node. The
     * shellScript node will have the information needed for what code the
     * beanShell should execute, but also how to execute it.
     *
     * The node can start with 2 headers. (1) "&lt;![SHELL[" or (2) "&lt;[SHELL[". If
     * (1) is chosen, the bean shell code should be run asynchronously and the
     * result of visiting this node is null. If (2) is chosen, the code should
     * be run synchronously and the result of visiting this node is the return
     * value of visiting the bean shell code (normally, the value of the last
     * instruction executed).
     */
    @Override
    public Expression visitShellscript(Macro2Parser.ShellscriptContext ctx) {
        BeanShellLoader loader = new BeanShellLoader();

        Literal literal = (Literal) visit(ctx.getChild(1));
        String code = literal.toString().substring(1, literal.toString().length() - 1);
        BeanShellInstance shell = loader.create(code, uiController, null);

        if (ctx.getChild(0).getText().charAt(1) == '!') { // It should run asynchronously
            shell.setAsynchronous();
        }

        return shell;
    }

    /**
     * Gets the number of errors.
     *
     * @return number of errors
     */
    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    /**
     * Gets the errors message.
     *
     * @return errors message
     */
    public String getErrorsMessage() {
        return errorBuffer.toString();
    }

    @Override
    public Expression visitMacro(Macro2Parser.MacroContext ctx) {
        Expression lastExpression = null;

        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i) != ctx.EOF()) {
                lastExpression = visit(ctx.getChild(i));
            }
        }

        return lastExpression;
    }

    @Override
    public Expression visitExpression(Macro2Parser.ExpressionContext ctx) {
        return visit(ctx.comparison());
    }

    @Override
    public Expression visitComparison(Macro2Parser.ComparisonContext ctx) {
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

        return visit(ctx.concatenation(0));
    }

    @Override
    public Expression visitConcatenation(Macro2Parser.ConcatenationContext ctx) {
        try {
            if (ctx.getChildCount() == 2) { // Convert unary operation
                int operatorid = 0, operand = 1;  // Assume operator on the left

//                if (ctx.getChild(1).getChildCount() == 0) { // Conclude that operator is on the right
                if (ctx.PERCENT() != null) { // Conclude that operator is on the right
                    operatorid = 1;
                    operand = 0;
                }

                return new UnaryOperation(
                        Language.getInstance().getUnaryOperator(ctx.getChild(operatorid).getText()),
                        visit(ctx.getChild(operand))
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

    @Override
    public Expression visitAtom(Macro2Parser.AtomContext ctx) {
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(1));
        } else if(ctx.LOCAL_VARIABLE() != null){
            String localName = ctx.LOCAL_VARIABLE().getText();

            try {
                return getExpressionOfVariable(localName, locals);
            } catch (IllegalArgumentException ex) {
                addVisitError(ex.getLocalizedMessage());
            }
        }

        return visitChildren(ctx);
    }

    @Override
    public Expression visitFunction_call(Macro2Parser.Function_callContext ctx) {
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
    public Expression visitReference(Macro2Parser.ReferenceContext ctx) {
        try {
            if (ctx.getChildCount() == 3) {
                BinaryOperator operator = Language.getInstance().getBinaryOperator(ctx.getChild(1).getText());
                return new ReferenceOperation(
                        new CellReference(spreadsheet, ctx.getChild(0).getText()),
                        (RangeReference) operator,
                        new CellReference(spreadsheet, ctx.getChild(2).getText())
                );
            } else {
                return new CellReference(spreadsheet, ctx.getText());
            }
            // return visitChildren(ctx); 
        } catch (ParseException | UnknownElementException ex) {
            addVisitError(ex.getMessage());
        }
        return null;
    }

    @Override
    public Expression visitLiteral(Macro2Parser.LiteralContext ctx) {
        Token t = (Token) ctx.getChild(0).getPayload();

        if (t.getType() == Macro2Parser.NUMBER) {
            return new Literal(Value.parseValue(ctx.getText()));
        } else {
            if (t.getType() == Macro2Parser.STRING) {
                String value = ctx.getText().substring(1, ctx.getText().length() - 1);
                return new Literal(Value.parseValue(value, Value.Type.BOOLEAN, Value.Type.DATE));
            }
        }

        return null;
    }

    @Override
    public Expression visitAssignment(Macro2Parser.AssignmentContext ctx) {
        if (ctx.ASSIGN() != null) {
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
            } else if (ctx.LOCAL_VARIABLE() != null) {
                String varName = ctx.LOCAL_VARIABLE().getText();
                return setExpressionOfVariable(varName, visit(ctx.comparison()), this.locals);
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Expression visitBlock(Macro2Parser.BlockContext ctx) {
        // Check if it is a block. Must have at least 3 child nodes
        //if (ctx.getType()==FormulaLexer.L_CURLY_BRACKET) {

        if (ctx.L_CURLY_BRACKET() != null) {
            try {
                // The L_CURLY_BRACKET is the father node
                // All the other nodes of the blcok are children.
                // The last children node is always the R_CURLY_BRACKET
                // Therefore all the other children will be expressions to be also converted and
                // executed by a "block executor"

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

    /**
     * Adds a given message to the visit error.
     *
     * @param msg message to be appended to the error
     */
    private void addVisitError(String msg) {
        errorBuffer.append(msg).append("\n");
        numberOfErrors++;
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
    private Expression setExpressionOfVariable(String visitText, Expression theExpression, VarContentor container) {
        int index;
        String[] variableInfo = visitText.split("\\[");
        if (variableInfo.length == 2) {
            try {
                index = Integer.parseInt(variableInfo[1].substring(0, (variableInfo[1].length() - 1)));
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                throw new IllegalArgumentException("Unknown Index.");
            }
        } else {
            index = Variable.DEFAULT_INDEX;
        }
        container.update(variableInfo[0], index, theExpression);
        return theExpression;
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
    private Expression getExpressionOfVariable(String visitText, VarContentor container) {
        Expression theExpression;
        String[] variableInfo = visitText.split("\\[");
        if (variableInfo.length == 2) {
            try {
                int index = Integer.parseInt(variableInfo[1].substring(0, (variableInfo[1].length() - 1)));
                theExpression = container.getExpressionOfVariable(variableInfo[0], index);
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                throw new IllegalArgumentException("Unknown Index.");
            }
        } else {
            theExpression = container.getExpressionOfVariable(variableInfo[0], Variable.DEFAULT_INDEX);
        }
        return theExpression;
    }
}
