package lapr4.blue.s1.lang.n1151159.macros;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.util.ExpressionVisitor;

import java.util.List;

/**
 * Represents a macro.
 *
 * @author IvoFerro and edited by Diogo Santos 1150451
 */
public class Macro implements Expression {

    /**
     * The expressions of a macro.
     */
    private List<Expression> expressions;

    /*
    The Macro's name. This should be unique.
    Created by Diogo Santos 1150451.
    */
    private String name;
    
    /**
     * Creates a macro with the given expressions and name.
     *
     * @param expressions expressions of the macro
     */
    public Macro(List<Expression> expressions, String name) {
        this.expressions = expressions;
        this.name=name;
    }
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
}
