package lapr4.blue.s1.lang.n1151452.formula.exceptions;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;

/**
 * An exception that is thrown when an illegal assignment is encountered.
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 01/06/17.
 */
public class IllegalAssignmentException extends IllegalValueTypeException {

    /**
     * Creates a new illegal assignment exception.
     *
     * @param value             the value
     * @param expectedValueType the expected type of the value
     */
    public IllegalAssignmentException(Value value, Value.Type expectedValueType) {
        super(value, expectedValueType);
    }
}
