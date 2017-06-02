/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.gray.s1.lang.n3456789.formula;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Operator;

/**
 * @author alexandrebraganca
 */
public interface NaryOperator extends Operator {

    /**
     * Applies the operator to the given operands and returns the result.
     *
     * @param operands operands
     * @return the result of the operation
     * @throws IllegalValueTypeException if the value of an operand is not of the expected type
     */
    Value applyTo(Expression[] operands) throws IllegalValueTypeException;

}
