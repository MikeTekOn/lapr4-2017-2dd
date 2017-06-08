/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1151452.formula.compiler;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.*;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.compiler.IllegalFunctionCallException;
import csheets.core.formula.lang.CellReference;
import csheets.core.formula.lang.RangeReference;
import csheets.core.formula.lang.ReferenceOperation;
import csheets.core.formula.lang.UnknownElementException;
import lapr4.blue.s1.lang.n1151452.formula.lang.Language;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperation;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperator;
import org.antlr.v4.runtime.Token;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.codegen.types.Type;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.TemporaryVariable;

/**
 * Represents the Formula Visitor (ANTLR4).
 *
 * @author Diana Silva {1151088@isep.ipp.pt]} on 03/06/17
 * @author Daniel Gonçalves [1151452@isep.ipp.pt] on 01/06/17.
 * @author jrt
 */
@SuppressWarnings("Duplicates")
public class FormulaEvalVisitor extends BlueFormulaBaseVisitor<Expression> {

    private Cell cell = null;
    private int numberOfErrors;
    private final StringBuilder errorBuffer;
    
    /**The starter lexical rule for temporary variables*/
    private static final char TEMP_VAR_STARTER='_';
    
    /**The temporary variables manager*/
    private final Set<TemporaryVariable> temp_contentor;

    public FormulaEvalVisitor(Cell cell) {
        this.cell = cell;
        numberOfErrors = 0;
        errorBuffer = new StringBuilder();
        temp_contentor = new HashSet<>();
    }

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    public String getErrorsMessage() {
        return errorBuffer.toString();
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
               
                Iterator it=temp_contentor.iterator();
                TemporaryVariable tempVar;
                while(it.hasNext()){
                    tempVar=(TemporaryVariable)it.next();
                    if (tempVar.getName().equalsIgnoreCase(tempVarName)) {
                        return tempVar.getExpression();
                    }
                }
                throw new UnknownElementException("Invalid expression. Variable it wasn´t assigned yet.");

            } catch (UnknownElementException ex) {
                addVisitError(ex.getLocalizedMessage());
            }
         
        }
        else if(ctx.assignment() != null) {
            //it´s a temporary variable
            if (ctx.assignment().VARIABLE_NAME() != null) { 
                TemporaryVariable temp_var = (TemporaryVariable)visit(ctx.assignment());
                //verifies if variable was already used in formula
                if(temp_contentor.contains(temp_var)) {
                    temp_contentor.remove(temp_var);
                    temp_contentor.add(temp_var);
                } else {
                    temp_contentor.add(temp_var);
                }
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
                return new CellReference(cell.getSpreadsheet(), ctx.getText());
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
            
            //it´s a temporary variable
            } else if (ctx.VARIABLE_NAME() != null) {
             
                String name = ctx.VARIABLE_NAME().getText();
                return new TemporaryVariable(name, visit(ctx.comparison()));
                   
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
}
