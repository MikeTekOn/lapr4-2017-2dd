package lapr4.green.s3.lang.n1150532.variables.globalVariablesExtension;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO to hold the variable's name, index and expression. It is used to list
 * the variables.
 *
 * @author Manuel Meireles (1150532)
 * @see GlobalVariablesTable
 */
public class GlobalVariableInformationDTO implements DTO, Comparable<GlobalVariableInformationDTO>, Serializable {

    /**
     * The variable's name.
     */
    private final String name;

    /**
     * The variable's index.
     */
    private final int index;

    /**
     * The variable's expression.
     */
    private final Expression expression;

    /**
     * The full constructor of the DTO.
     *
     * @param variableName The name of the variable.
     * @param variableIndex The index of the variable.
     * @param variableExpression The expression of the variable's index.
     */
    public GlobalVariableInformationDTO(String variableName, int variableIndex, Expression variableExpression) {
        name = variableName;
        index = variableIndex;
        expression = variableExpression;
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
     * A getter of the variable's index.
     *
     * @return It returns the index of the variable.
     */
    public int getIndex() {
        return index;
    }

    /**
     * A getter of the variable's value.
     *
     * @return It returns the expression evaluation result or the default value
     * if an IllegalValueTypeException is launched.
     */
    public Value getValue() {
        try {
            return expression.evaluate();
        } catch (IllegalValueTypeException ex) {
            return new Value();
        }
    }

    /**
     * It checks if that object is of the same instance of this and contain
     * equal name and index.
     *
     * @param that The object to compare to.
     * @return I returns "true" if that object is of the same instance of this
     * object and both have the same name and index. It returns "false"
     * otherwise.
     */
    @Override
    public boolean equals(Object that) {
        if (that == null || !(that instanceof GlobalVariableInformationDTO)) {
            return false;
        }
        if (this == that) {
            return true;
        }
        GlobalVariableInformationDTO thatDTO = (GlobalVariableInformationDTO) that;
        return name.equals(thatDTO.name) && index == thatDTO.index;
    }

    /**
     * The hash code generator function.
     *
     * @return It returns the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + this.index;
        return hash;
    }

    /**
     * It implements the comparable interface in order to sort objects of this
     * class.
     *
     * @param o The other DTO to compare to.
     * @return It returns a negative integer, zero, or a positive integer as
     * this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(GlobalVariableInformationDTO o) {
        int comparison = name.compareTo(o.name);
        if (comparison == 0) {
            comparison = index - o.index;
        }
        return comparison;
    }

}
