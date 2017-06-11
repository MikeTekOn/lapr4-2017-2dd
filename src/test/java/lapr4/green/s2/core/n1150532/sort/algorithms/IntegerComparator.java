package lapr4.green.s2.core.n1150532.sort.algorithms;

import java.util.Comparator;

/**
 * It provides a comparator for the SortableIntegerDTO, which is a testing DTO.
 * It is a testing comparator.
 *
 * @author Manuel Meireles (1150532)
 */
public class IntegerComparator implements Comparator<SortableIntegerDTO> {

    /**
     * A flag to indicate if its comparing in ascending (false) or descending
     * (true) order.
     */
    private boolean shouldInvert;

    /**
     * The full constructor of the comparator. It is set to ascending order.
     */
    public IntegerComparator() {
        shouldInvert = false;
    }

    /**
     * It changes the sorting order.
     *
     * @param isToDescend The flag to change the sorting order. Set "false" for
     * ascending order or "true" for descending order.
     */
    public void setDescending(boolean isToDescend) {
        shouldInvert = isToDescend;
    }

    /**
     * It compares two integers.
     *
     * @param o1 The one encapsulated integer.
     * @param o2 The other encapsulated integer.
     * @return It returns -1, 0 or 1 depending on if the one type is lesser,
     * equal or greater the the other.
     */
    @Override
    public int compare(SortableIntegerDTO o1, SortableIntegerDTO o2) {
        Integer o1Int = o1.getMyNumber();
        Integer o2Int = o2.getMyNumber();
        int comparison = o1Int.compareTo(o2Int);
        if (shouldInvert) {
            comparison = -(comparison);
        }
        return comparison;
    }

}
