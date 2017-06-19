package lapr4.blue.s1.lang.n1151159.macros;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.util.ExpressionVisitor;
import lapr4.green.s3.lang.n1150738.macros.ParameterDefinition;
import lapr4.green.s3.lang.n1150738.macros.ParameterList;

import java.util.List;

/**
 * Represents a macro.
 *
 * @author IvoFerro
 */
public class Macro implements Expression {

    /**
     * The expressions of a macro.
     */
    private List<Expression> expressions;

    /**
     * Creates a macro with the given expressions.
     *
     * @param expressions expressions of the macro
     */
    public Macro(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        Value value = null;
        for (Expression exp : expressions) {
            value = exp.evaluate();
        }
        return value;
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        return visitor.visitMacro(this);
    }

    public List<Expression> expressions(){
        return expressions;
    }
}
