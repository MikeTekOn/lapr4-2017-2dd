/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1151452.formula.lang;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperator;

/**
 * Represents a sequence of expressions.
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 01/06/17.
 * @author alexandrebraganca
 */
public class SequenceBlock implements NaryOperator {

    @Override
    public Value applyTo(Expression[] operands) throws IllegalValueTypeException {

        Value value = new Value();
        // evaluate each expression and return the last value
        for (Expression expr : operands) {
            value = expr.evaluate();
        }
        return value;
    }

    @Override
    public String getIdentifier() {
        return "{";
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
