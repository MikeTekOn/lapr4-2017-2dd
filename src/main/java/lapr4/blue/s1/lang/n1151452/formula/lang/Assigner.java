/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1151452.formula.lang;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.lang.CellReference;
import lapr4.blue.s1.lang.n1151452.formula.exceptions.IllegalAssignmentException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents an assign operator.
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 01/06/17.
 * @author alexandrebraganca
 */
public class Assigner implements BinaryOperator {

    @Override
    public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException {

        // Test Logic (If it is false logic is wrong)
        if (!(leftOperand instanceof CellReference)) {
            Value wrongValue = leftOperand.evaluate();
            throw new IllegalAssignmentException(wrongValue, wrongValue.getType());
        }
        // Assign
        CellReference left = (CellReference) leftOperand;
        Value value = rightOperand.evaluate();
        String content;
        try {
            // Need to handle all possible types because the set of a cell only accepts "text" or "formula"
            switch (value.getType()) {
                case NUMERIC:
                    content = value.toString(new DecimalFormat());
                    break;
                case DATE:
                    content = value.toString(new SimpleDateFormat());
                    break;
                default:
                    content = value.toString();
            }
            // Assign to left operand
            left.getCell().setContent(content);

        } catch (FormulaCompilationException ex) {
            Logger.getLogger(Assigner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    @Override
    public String getIdentifier() {
        return ":=";
    }

    @Override
    public Value.Type getOperandValueType() {
        return Value.Type.UNDEFINED;
    }

    @Override
    public String toString() {
        return getIdentifier();
    }

}
