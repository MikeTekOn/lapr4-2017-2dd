package lapr4.green.s2.core.n1150532.sort.algorithms;

import java.util.Comparator;
import java.util.Observable;
import lapr4.green.s2.core.n1150532.sort.sortingDTOs.SortableDTO;

/**
 * A sorting algorithm that uses a bubble sort algorithm to order SortableDTOs.
 *
 * @author Manuel Meireles (1150532)
 * @param <T> Any class that extends the SortableDTO and can, therefore, be
 * sorted by the algorithm.
 */
public class BubbleSort<T extends SortableDTO>  implements SortingAlgorithm<T>{

    /**
     * The algorithm's name.
     */
    private static final String NAME = "Bubble Sort";

    /**
     * The full constructor of the algorithm.
     */
    public BubbleSort() {
    }

    /**
     * It sorts the array using the comparator.
     *
     * @param array The array with the SortableDTOs to be sorted. It does not
     * change the array's order, it uses the swap method from the SortableDTO.
     * @param comparator The comparator to use when comparing the SortableDTO.
     */
    @Override
    public void sort(T[] array, Comparator<T> comparator) {
        executeSort(array, comparator, array.length);

    }

    /**
     * It executes the sort itself.
     *
     * @param array The array to be sorted.
     * @param comparator The comparator to use when comparing the SortableDTO.
     * @param n The array length.
     */
    private void executeSort(T[] array, Comparator<T> comparator, int n) {
        boolean swap = true;
        for (int i = 0; (i < n - 1 && swap); i++) {
            swap = false;
            for (int j = n - 1; j > i; j--) {
                if (comparator.compare(array[j - 1], array[j]) > 0) {
                    array[j - 1].swap(array[j]);
                    swap = true;
                }
            }
        }
    }

    /**
     * It checks if the other object is a bubble sort algorithm.
     *
     * @param other The object to compare.
     * @return It returns "true" if the other object is an instance of
     * BubbleSort or "false" otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        return (other instanceof BubbleSort);
    }

    /**
     * The hash code generator.
     *
     * @return It returns the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * It provides the algorithm's name.
     *
     * @return It returns the name of the algorithm.
     */
    @Override
    public String toString() {
        return NAME;
    }

    
}
