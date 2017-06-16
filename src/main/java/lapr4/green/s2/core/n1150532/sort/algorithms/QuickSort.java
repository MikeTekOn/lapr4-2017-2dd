package lapr4.green.s2.core.n1150532.sort.algorithms;

import java.util.Comparator;
import java.util.Observable;
import lapr4.green.s2.core.n1150532.sort.sortingDTOs.SortableDTO;

/**
 * A sorting algorithm that uses a quick sort algorithm to order SortableDTOs.
 *
 * @author Manuel Meireles (1150532)
 * @param <T> Any class that extends the SortableDTO and can, therefore, be
 * sorted by the algorithm.
 */
public class QuickSort<T extends SortableDTO>  extends Observable implements SortingAlgorithm<T> {

    /**
     * The algorithm's name.
     */
    private static final String NAME = "Quick Sort";

    /**
     * The full constructor of the algorithm.
     */
    public QuickSort() {
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
        if (array.length > 1) {
            executeSort(array, comparator, 0, array.length - 1);
        }
         setChanged();
         notifyObservers();
    }

    /**
     * It executes the sort itself. This is a recursive method.
     *
     * @param array The array to be sorted.
     * @param comparator The comparator to use when comparing the SortableDTO.
     * @param left The lower index being examined. It starts at zero.
     * @param right The higher index being examined. It starts at the array
     * length minus one.
     */
    private void executeSort(T array[], Comparator<T> comparator, int left, int right) {
        T pivot = array[left + ((right - left) / 2)];
        int i = left;
        int j = right;
        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(array[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                array[i].swap(array[j]);
                i++;
                j--;
            }
        }
        if (left < j) {
            executeSort(array, comparator, left, j);
        }
        if (right > i) {
            executeSort(array, comparator, i, right);
        }
         
    }

    /**
     * It checks if the other object is a quick sort algorithm.
     *
     * @param other The object to compare.
     * @return It returns "true" if the other object is an instance of
     * QuickSort or "false" otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        return (other instanceof QuickSort);
    }

    /**
     * The hash code generator.
     *
     * @return It returns the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 5;
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
