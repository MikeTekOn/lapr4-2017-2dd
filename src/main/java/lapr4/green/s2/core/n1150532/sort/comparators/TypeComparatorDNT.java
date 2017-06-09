package lapr4.green.s2.core.n1150532.sort.comparators;

import csheets.core.Value.Type;
import static lapr4.green.s2.core.n1150532.sort.comparators.TypeComparator.EQUAL;

/**
 * It extends the TypeComparator abstract class. It implements the necessary
 * methods to ensure that the Text Type is the greater, followed by the Numeric
 * and Date. All others will be lesser.
 *
 * @author Manuel Meireles (1150532)
 */
public class TypeComparatorDNT extends TypeComparator {

    /**
     * The name of the comparator. It describes the values hierarchy.
     */
    private static final String NAME = "Date < Numeric < Text";

    /**
     * The full constructor of the comparator. It is protected to force the use
     * of the factory.
     */
    protected TypeComparatorDNT() {
        super();
    }

    /**
     * It compares a Numeric type to other type. It ensures that it returns
     * greater than all other types, except for Text Type which is greater than
     * Numeric Type, and equal if the other type is also a Numeric Type.
     *
     * @param other The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    @Override
    protected int numericComparisonTo(Type other) {
        int comparison;
        switch (other) {
            case NUMERIC:
                comparison = EQUAL;
                break;
            case TEXT:
                comparison = LESSER;
                break;
            case BOOLEAN:
                comparison = GREATER;
                break;
            case DATE:
                comparison = GREATER;
                break;
            case MATRIX:
                comparison = GREATER;
                break;
            case ERROR:
                comparison = GREATER;
                break;
            default:
                comparison = GREATER;
                break;
        }
        return comparison;
    }

    /**
     * It compares a Text type to other type. It ensures that it returns greater
     * than all other types and equal if the other type is also a Text Type.
     *
     * @param other The other type to compare.
     * @return It returns 0 or 1 depending on if the other type is Text or not.
     */
    @Override
    protected int textComparisonTo(Type other) {
        int comparison;
        switch (other) {
            case NUMERIC:
                comparison = GREATER;
                break;
            case TEXT:
                comparison = EQUAL;
                break;
            case BOOLEAN:
                comparison = GREATER;
                break;
            case DATE:
                comparison = GREATER;
                break;
            case MATRIX:
                comparison = GREATER;
                break;
            case ERROR:
                comparison = GREATER;
                break;
            default:
                comparison = GREATER;
                break;
        }
        return comparison;
    }

    /**
     * It compares a Date type to other type. It ensures that it returns greater
     * than all other types, except for Text and Numeric Types which are greater
     * than Date Type, and equal if the other type is also a Date Type.
     *
     * @param other The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    @Override
    protected int dateComparisonTo(Type other) {
        int comparison;
        switch (other) {
            case NUMERIC:
                comparison = LESSER;
                break;
            case TEXT:
                comparison = LESSER;
                break;
            case BOOLEAN:
                comparison = GREATER;
                break;
            case DATE:
                comparison = EQUAL;
                break;
            case MATRIX:
                comparison = GREATER;
                break;
            case ERROR:
                comparison = GREATER;
                break;
            default:
                comparison = GREATER;
                break;
        }
        return comparison;
    }

    /**
     * It checks if the other object is of the same class.
     *
     * @param other The object to compare.
     * @return It returns "true" if the other object is an instance of
     * TypeComparatorDNT or "false" otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        return (other instanceof TypeComparatorNTD);
    }

    /**
     * The hash code generator.
     *
     * @return It returns the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * It provides the comparator's name.
     *
     * @return It returns the name of the comparator.
     */
    @Override
    public String toString() {
        return NAME;
    }

}
