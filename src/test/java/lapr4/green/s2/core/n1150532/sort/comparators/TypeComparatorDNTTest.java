package lapr4.green.s2.core.n1150532.sort.comparators;

import csheets.core.Value;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * It tests the TypeComparatorDNT comparator.
 *
 * @author Manuel Meireles (1150532)
 */
public class TypeComparatorDNTTest {

    /**
     * The testing comparator.
     */
    private final TypeComparatorDNT comparator;

    /**
     * The boolean type.
     */
    private final Value.Type bool = Value.Type.BOOLEAN;

    /**
     * The error type.
     */
    private final Value.Type error = Value.Type.ERROR;

    /**
     * The date type.
     */
    private final Value.Type date = Value.Type.DATE;

    /**
     * The matrix type.
     */
    private final Value.Type matrix = Value.Type.MATRIX;

    /**
     * The numeric type.
     */
    private final Value.Type numeric = Value.Type.NUMERIC;

    /**
     * The text type.
     */
    private final Value.Type text = Value.Type.TEXT;

    /**
     * The undefined type.
     */
    private final Value.Type undefined = Value.Type.UNDEFINED;

    /**
     * The full constructor of the testing class. It instantiates the
     * comparator.
     */
    public TypeComparatorDNTTest() {
        comparator = new TypeComparatorDNT();
    }

    /**
     * It ensures that TypeComparatorDNT guarantees that Text is the greater
     * type.
     */
    @Test
    public void testTextType() {
        int result;

        result = comparator.compare(text, text);
        assertTrue(result == TypeComparator.EQUAL);

        result = comparator.compare(text, numeric);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(text, date);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(text, bool);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(text, error);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(text, matrix);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(text, undefined);
        assertTrue(result == TypeComparator.GREATER);

    }

    /**
     * It ensures that TypeComparatorDNT guarantees that Numeric is the greater
     * type except for the Text Type.
     */
    @Test
    public void testNumericType() {
        int result;

        result = comparator.compare(numeric, text);
        assertTrue(result == TypeComparator.LESSER);

        result = comparator.compare(numeric, numeric);
        assertTrue(result == TypeComparator.EQUAL);

        result = comparator.compare(numeric, date);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(numeric, bool);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(numeric, error);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(numeric, matrix);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(numeric, undefined);
        assertTrue(result == TypeComparator.GREATER);

    }

    /**
     * It ensures that TypeComparatorDNT guarantees that Date is the greater
     * type except for the Numeric and Text Types.
     */
    @Test
    public void testDateType() {
        int result;

        result = comparator.compare(date, text);
        assertTrue(result == TypeComparator.LESSER);

        result = comparator.compare(date, numeric);
        assertTrue(result == TypeComparator.LESSER);

        result = comparator.compare(date, date);
        assertTrue(result == TypeComparator.EQUAL);

        result = comparator.compare(date, bool);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(date, error);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(date, matrix);
        assertTrue(result == TypeComparator.GREATER);

        result = comparator.compare(date, undefined);
        assertTrue(result == TypeComparator.GREATER);

    }

}
