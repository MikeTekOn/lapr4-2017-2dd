package lapr4.green.s3.lang.n1150532.variables;

import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Literal;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * It is meant to replace the class
 * {@link lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable} in order to
 * allow the array function. It is not extending the class since it changes most
 * of the code and has different implements. However, the concept remains the
 * same from previous feature increments.
 *
 * @author Manuel Meireles (1150532)
 * @see lapr4.green.s3.lang.n1150532.variables.package-info
 * @see lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable
 */
public class Variable implements Serializable {

    /**
     * The default index of the variable It is used when no reference to the
     * index is made. It symbolizes the first element in the array and there can
     * not exist any lower indexes than this.
     */
    public static final int DEFAULT_INDEX = 1;

    /**
     * The variable's name.
     */
    private final String name;

    /**
     * The array of the variable. The key is the array index and the value is
     * the expression stored at that index.
     */
    private final Map<Integer, Expression> indexes;

    /**
     * The full constructor of the variable. It sets the name of the variable
     * and initializes a new map of indexes.
     *
     * @param theName The name to be set on the variable.
     */
    public Variable(String theName) {
        name = theName;
        indexes = new HashMap<>();
    }

    /**
     * A getter of the variable's name.
     *
     * @return It returns the name of the variable.
     */
    public String getName() {
        return name;
    }

    /**
     * It adds or replaces the expression at the index.
     *
     * @param theIndex The index in which to place the expression.
     * @param theExpression The expression to store.
     * @see csheets.core.formula.Expression
     */
    public void setExpressionAt(int theIndex, Expression theExpression) {
        if (theIndex < DEFAULT_INDEX) {
            throw new IllegalArgumentException("The index must be equal or higher than " + DEFAULT_INDEX + ".");
        }
        indexes.put(theIndex, theExpression);
    }

    /**
     * It gets the expression stored at the index. If no expression is stored at
     * the stated index, a new Literal with a default Value is returned.
     *
     * @param theIndex The index from which to extract the Expression.
     * @return It returns the stored Expression or a new Literal with the
     * default Value.
     * @see csheets.core.formula.Expression
     * @see csheets.core.formula.Literal
     * @see csheets.core.Value
     */
    public Expression getExpressionAt(int theIndex) {
        Expression theExpression = indexes.get(theIndex);
        if (theExpression == null) {
            theExpression = new Literal(new Value());
        }
        return theExpression;
    }

    /**
     * It checks if the other object is of this class and contains the same
     * name.
     *
     * @param that The other object to check.
     * @return It returns "true" if that object is of this class and contains
     * the same name of this object or "false" otherwise.
     */
    @Override
    public boolean equals(Object that) {
        if (that == null || !(that instanceof Variable)) {
            return false;
        }
        if (that == this) {
            return true;
        }
        Variable thatVariable = (Variable) that;
        return this.name.equalsIgnoreCase(thatVariable.name);
    }

    /**
     * The hash code generator function.
     *
     * @return It returns the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

}
