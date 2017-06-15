package lapr4.green.s2.core.n1150532.sort.algorithms;

import java.util.Comparator;
import java.util.Observable;
 
import lapr4.green.s2.core.n1150532.sort.sortingDTOs.SortableDTO;

/**
 * A sorting algorithm interface to order SortableDTOs.
 *
 * @author Manuel Meireles (1150532)
 * @param <T> Any class that extends the SortableDTO and can, therefore, be
 * sorted by the algorithm.
 */
public interface SortingAlgorithm<T extends SortableDTO>   {

    /**
     * It sorts the array using the comparator.
     *
     * @param array The array with the SortableDTOs to be sorted. It does not
     * change the array's order, it uses the swap method from the SortableDTO.
     * @param comparator The comparator to use when comparing the SortableDTO.
     */
    void sort(T array[], Comparator<T> comparator);

}
