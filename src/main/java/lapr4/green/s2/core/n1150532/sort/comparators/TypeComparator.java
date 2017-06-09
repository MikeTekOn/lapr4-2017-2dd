package lapr4.green.s2.core.n1150532.sort.comparators;

import csheets.core.Value.Type;
import java.util.Comparator;

/**
 * An abstract class for a comparator on Value's Type.
 *
 * @author Manuel Meireles (1150532)
 */
public abstract class TypeComparator implements Comparator<Type> {

    /**
     * The value to return when this type is lesser than that type.
     */
    protected static final int LESSER = -1;

    /**
     * The value to return when this type is equal to that type.
     */
    protected static final int EQUAL = 0;

    /**
     * The value to return when this type is greater than that type.
     */
    protected static final int GREATER = 1;

    /**
     * It compares the two types. It calls matching method to compare. These
     * methods must be implemented by all sub-classes.
     *
     * @param o1 The one type to compare.
     * @param o2 The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    @Override
    public final int compare(Type o1, Type o2) {
        int comparison;
        if (o1 == o2) {
            comparison = 0;
        } else {
            switch (o1) {
                case NUMERIC:
                    comparison = numericComparisonTo(o2);
                    break;
                case TEXT:
                    comparison = textComparisonTo(o2);
                    break;
                case BOOLEAN:
                    comparison = booleanComparisonTo(o2);
                    break;
                case DATE:
                    comparison = dateComparisonTo(o2);
                    break;
                case MATRIX:
                    comparison = matrixComparisonTo(o2);
                    break;
                case ERROR:
                    comparison = errorComparisonTo(o2);
                    break;
                default:
                    comparison = undefinedComparisonTo(o2);
                    break;
            }
        }
        return comparison;
    }

    /**
     * It compares a Numeric type to other type. The sub-classes can declare
     * different result values to change hierarchy.
     *
     * @param other The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    protected abstract int numericComparisonTo(Type other);

    /**
     * It compares a Text type to other type. The sub-classes can declare
     * different result values to change hierarchy.
     *
     * @param other The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    protected abstract int textComparisonTo(Type other);

    /**
     * It compares a Date type to other type. The sub-classes can declare
     * different result values to change hierarchy.
     *
     * @param other The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    protected abstract int dateComparisonTo(Type other);

    /**
     * It compares a Boolean type to other type. The sub-classes can declare
     * different result values to change hierarchy.
     *
     * @param other The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    protected int booleanComparisonTo(Type other) {
        int comparison;
        switch (other) {
            case NUMERIC:
                comparison = LESSER;
                break;
            case TEXT:
                comparison = LESSER;
                break;
            case BOOLEAN:
                comparison = EQUAL;
                break;
            case DATE:
                comparison = LESSER;
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
     * It compares a Matrix type to other type. The sub-classes can declare
     * different result values to change hierarchy.
     *
     * @param other The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    protected int matrixComparisonTo(Type other) {
        int comparison;
        switch (other) {
            case NUMERIC:
                comparison = LESSER;
                break;
            case TEXT:
                comparison = LESSER;
                break;
            case BOOLEAN:
                comparison = LESSER;
                break;
            case DATE:
                comparison = LESSER;
                break;
            case MATRIX:
                comparison = EQUAL;
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
     * It compares an Error type to other type. The sub-classes can declare
     * different result values to change hierarchy.
     *
     * @param other The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    protected int errorComparisonTo(Type other) {
        int comparison;
        switch (other) {
            case NUMERIC:
                comparison = LESSER;
                break;
            case TEXT:
                comparison = LESSER;
                break;
            case BOOLEAN:
                comparison = LESSER;
                break;
            case DATE:
                comparison = LESSER;
                break;
            case MATRIX:
                comparison = LESSER;
                break;
            case ERROR:
                comparison = EQUAL;
                break;
            default:
                comparison = GREATER;
                break;
        }
        return comparison;
    }

    /**
     * It compares an Undefined type to other type. The sub-classes can declare
     * different result values to change hierarchy.
     *
     * @param other The other type to compare.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    protected int undefinedComparisonTo(Type other) {
        int comparison;
        switch (other) {
            case NUMERIC:
                comparison = LESSER;
                break;
            case TEXT:
                comparison = LESSER;
                break;
            case BOOLEAN:
                comparison = LESSER;
                break;
            case DATE:
                comparison = LESSER;
                break;
            case MATRIX:
                comparison = LESSER;
                break;
            case ERROR:
                comparison = LESSER;
                break;
            default:
                comparison = EQUAL;
                break;
        }
        return comparison;
    }

}
