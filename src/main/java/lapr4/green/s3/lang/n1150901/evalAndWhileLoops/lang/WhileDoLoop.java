/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150901.evalAndWhileLoops.lang;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import java.util.Arrays;
import java.util.LinkedList;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperator;

/**
 * This class represents a WhileDo loop.
 *
 * @author Miguel Silva - 1150901
 */
public class WhileDoLoop implements NaryOperator {

    @Override
    public Value applyTo(Expression[] operands) throws IllegalValueTypeException {

        LinkedList<Expression> expressions = new LinkedList<>(Arrays.asList(operands));

        // Get boundary condition
        Expression boundary = expressions.poll();

        // Get executed condition
        Expression executed = expressions.pollLast();

        // Loop
        Value result = null;
        for (; boundary.evaluate().toBoolean();) {
            result = executed.evaluate();
        }
        return result;
    }

    @Override
    public String getIdentifier() {
        return "whiledo";
    }

    @Override
    public Value.Type getOperandValueType() {
        return Value.Type.UNDEFINED;
    }
}
