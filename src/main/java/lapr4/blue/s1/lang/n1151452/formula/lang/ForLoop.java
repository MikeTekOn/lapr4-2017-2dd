package lapr4.blue.s1.lang.n1151452.formula.lang;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.BinaryOperation;
import csheets.core.formula.Expression;
import lapr4.blue.s1.lang.n1151452.formula.exceptions.IllegalAssignmentException;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperator;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Represents a for loop operation.
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 02/06/17.
 */
public class ForLoop implements NaryOperator {
    @Override
    public Value applyTo(Expression[] operands) throws IllegalValueTypeException {

        LinkedList<Expression> expressions = new LinkedList<>(Arrays.asList(operands));

        // Get init
        Expression init = expressions.poll();
        if (!(init instanceof BinaryOperation && ((BinaryOperation) init).getOperator() instanceof Assigner)) {
            Value wrong = init.evaluate();
            throw new IllegalAssignmentException(wrong, wrong.getType());
        }
        // Get boundary condition
        Expression boundary = expressions.poll();

        // Loop
        Value result = null;
        for (init.evaluate(); boundary.evaluate().toBoolean(); ) {

            for (Expression expression :
                    expressions) {
                result = expression.evaluate();
            }
        }
        return result; // Returns last expression to evaluate
    }

    @Override
    public String getIdentifier() {
        return "for";
    }

    @Override
    public Value.Type getOperandValueType() {
        return Value.Type.UNDEFINED;
    }
}
