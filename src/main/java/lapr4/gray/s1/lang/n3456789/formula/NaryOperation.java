/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.gray.s1.lang.n3456789.formula;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Operation;
import csheets.core.formula.util.ExpressionVisitor;

/**
 * @author alexandrebraganca
 */

public class NaryOperation extends Operation<NaryOperator> {

    /**
     * operands
     */
    private Expression[] operands;

    /**
     * Constructs a n-ary operation
     *
     * @param operator the operator
     * @param operands the operands
     */
    public NaryOperation(NaryOperator operator, Expression[] operands) {
        super(operator);

        this.operands = operands;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        return operator.applyTo(operands);
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        return visitor.visitNaryOperation(this);
    }

    /**
     * Obtain the n-ary operation operands
     *
     * @return n-ary operation operands
     */
    public Expression[] getOperands() {
        return this.operands;
    }
}
