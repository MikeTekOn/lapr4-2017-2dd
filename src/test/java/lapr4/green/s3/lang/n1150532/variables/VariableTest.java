package lapr4.green.s3.lang.n1150532.variables;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.BinaryOperation;
import csheets.core.formula.Expression;
import csheets.core.formula.Literal;
import csheets.core.formula.lang.Multiplier;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * It tests the most relevant business rules and some unit testing of the
 * variable class.
 *
 * @author Manuel Meireles (1150532)
 */
public class VariableTest {

    /**
     * A variable name.
     */
    private final String aVariableName;

    /**
     * Another variable name.
     */
    private final String anotherVariableName;

    /**
     * The number to be set on the variable.
     */
    private final int theNumber;

    /**
     * The string to be set on the variable.
     */
    private final String theString;

    /**
     * The result of the multiplication.
     */
    private final int theResult;

    /**
     * The expression that holds theNumber to be stored.
     */
    private final Expression aNumber;

    /**
     * The expression that holds theString to be stored.
     */
    private final Expression aString;

    /**
     * The expression that holds theResult to be stored.
     */
    private final Expression aMultiplication;

    /**
     * The full constructor of the test class.
     */
    public VariableTest() {
        aVariableName = "_temp";
        anotherVariableName = "@global";
        theNumber = 5;
        theString = "My variable.";
        theResult = 25;
        aNumber = new Literal(new Value(theNumber));
        aString = new Literal(new Value(theString));
        aMultiplication = new BinaryOperation(aNumber, new Multiplier(), aNumber);
    }

    /**
     * Test of getName method, of class Variable.
     */
    @Test
    public void testGetName() {
        Variable theVariable = new Variable(aVariableName);
        assertEquals(aVariableName, theVariable.getName());
    }

    /**
     * Test of setExpressionAt and getExpressionAt methods, of class Variable.
     */
    @Test
    public void testGetSetExpressionAt() {
        Variable theVariable = new Variable(aVariableName);
        theVariable.setExpressionAt(Variable.DEFAULT_INDEX, aNumber);
        assertEquals(aNumber, theVariable.getExpressionAt(Variable.DEFAULT_INDEX));
    }

    /**
     * It attempts to store data at an index lower than the first element (the
     * default index) of the array. It must throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureLowerIndexesThanTheDefaultAreNotAllowed() {
        final int index = Variable.DEFAULT_INDEX - 1;
        Variable theVariable = new Variable(aVariableName);
        theVariable.setExpressionAt(index, aNumber);
    }

    /**
     * It sets data at different indexes which are higher than the default one
     * and not sequential. It must execute without any issue.
     */
    @Test
    public void ensureNonSequentialIndexesAreAllowed() {
        final int numberIndex = Variable.DEFAULT_INDEX + 2;
        final int stringIndex = Variable.DEFAULT_INDEX + 6;
        Variable theVariable = new Variable(aVariableName);
        theVariable.setExpressionAt(numberIndex, aNumber);
        theVariable.setExpressionAt(stringIndex, aString);
        assertEquals(aNumber, theVariable.getExpressionAt(numberIndex));
        assertEquals(aString, theVariable.getExpressionAt(stringIndex));
    }

    /**
     * It sets data of different types within the variable. It must execute
     * without any issue.
     */
    @Test
    public void ensureVariablesHoldDifferentTypesOfValues() {
        final int numberIndex = Variable.DEFAULT_INDEX;
        final int stringIndex = Variable.DEFAULT_INDEX + 1;
        final int multiplicationIndex = Variable.DEFAULT_INDEX + 2;
        Variable theVariable = new Variable(aVariableName);
        theVariable.setExpressionAt(numberIndex, aNumber);
        theVariable.setExpressionAt(stringIndex, aString);
        theVariable.setExpressionAt(multiplicationIndex, aMultiplication);
        assertEquals(aNumber, theVariable.getExpressionAt(numberIndex));
        assertEquals(aString, theVariable.getExpressionAt(stringIndex));
        assertEquals(aMultiplication, theVariable.getExpressionAt(multiplicationIndex));
    }

    /**
     * It gets the data from an index which was not set yet. It must be equal to
     * the default value.
     *
     * @throws IllegalValueTypeException It re-throws the exception launched by
     * the "evaluate" method from the Expression class.
     */
    @Test
    public void ensureDefaultValueIsReturnedForNonAssignedIndexes() throws IllegalValueTypeException {
        Variable theVariable = new Variable(aVariableName);
        Value result = theVariable.getExpressionAt(Variable.DEFAULT_INDEX).evaluate();
        Value expected = new Value();
        assertEquals(expected, result);
    }

    /**
     * Test of equals method, of class Variable.
     */
    @Test
    public void testEquals() {
        Variable theVariable = new Variable(aVariableName);
        Variable theOtherVariable = new Variable(aVariableName);
        Variable theLastVariable = new Variable(anotherVariableName);
        assertTrue(theVariable.equals(theVariable));
        assertTrue(theVariable.equals(theOtherVariable));
        assertFalse(theVariable.equals(null));
        assertFalse(theVariable.equals(new Object()));
        assertFalse(theVariable.equals(theLastVariable));
    }

    /**
     * Test of hashCode method, of class Variable.
     */
    @Test
    public void testHashCode() {
        Variable theVariable = new Variable(aVariableName);
        Variable theOtherVariable = new Variable(aVariableName);
        Variable theLastVariable = new Variable(anotherVariableName);
        assertEquals(theVariable.hashCode(), theOtherVariable.hashCode());
        assertNotEquals(theVariable.hashCode(), theLastVariable.hashCode());
    }

}
