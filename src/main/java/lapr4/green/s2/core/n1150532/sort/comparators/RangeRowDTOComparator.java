package lapr4.green.s2.core.n1150532.sort.comparators;

import csheets.core.Value;
import csheets.core.Value.Type;
import java.util.Comparator;
import lapr4.green.s2.core.n1150532.sort.sortingDTOs.RangeRowDTO;

/**
 * A comparator for Range Row DTOs. It extracts the value to compare from each
 * RangeRowDTO and checks their types. If they share the same type, the Value's
 * compareTo method is used. If not, the TypeComparator is used.
 *
 * @author Manuel Meireles (1150532)
 */
public class RangeRowDTOComparator implements Comparator<RangeRowDTO> {

    /**
     * The comparator to compare different value types.
     */
    private final Comparator<Type> typeComparator;

    /**
     * A flag to indicate if it should invert the normal result. This is used
     * for descending order sorting.
     */
    private boolean shouldInvert;

    /**
     * The full constructor of the comparator. It sets the type comparator and
     * starts in ascending order mode. It is protected to force the use of the
     * factory.
     *
     * @param theTypeComparator The type comparator to use.
     */
    protected RangeRowDTOComparator(Comparator<Type> theTypeComparator) {
        typeComparator = theTypeComparator;
        shouldInvert = false;
    }

    /**
     * It extracts the value to compare from each RangeRowDTO and checks their
     * types. If they share the same type, the Value's compareTo method is used.
     * If not, the TypeComparator is used.
     *
     * @param o1 The one object to compare.
     * @param o2 The other object to compare to.
     * @return It returns -1, 0 or 1 depending on if the one object is lesser,
     * equal or greater the the other.
     */
    @Override
    public int compare(RangeRowDTO o1, RangeRowDTO o2) {
        Value o1Value = o1.getComparableValue();
        Value o2Value = o2.getComparableValue();
        Type o1Type = o1Value.getType();
        Type o2Type = o2Value.getType();
        int comparison;
        if (o1Type == o2Type) {
            comparison = o1Value.compareTo(o2Value);
        } else {
            comparison = typeComparator.compare(o1Type, o2Type);
        }
        if (shouldInvert) {
            comparison = -(comparison);
        }
        return comparison;
    }

    /**
     * It sets the order flag.
     *
     * @param isToBeDescendant It sets to descendant order if "true" or to
     * ascendant if "false".
     */
    public void setDescendant(boolean isToBeDescendant) {
        shouldInvert = isToBeDescendant;
    }

    /**
     * It provides the type comparator's name.
     *
     * @return It returns the name of the type comparator.
     */
    @Override
    public String toString() {
        return typeComparator.toString();
    }

}
