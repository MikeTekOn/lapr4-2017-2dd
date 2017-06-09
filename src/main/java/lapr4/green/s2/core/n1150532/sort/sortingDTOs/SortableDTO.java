package lapr4.green.s2.core.n1150532.sort.sortingDTOs;

/**
 * A Data Transfer Object interface to be used on sorting algorithms. It
 * provides the necessary interface for them to work.
 *
 * @author Manuel Meireles (1150532)
 * @param <T> Any DTO that is capable of encapsulating the desired data.
 */
public interface SortableDTO<T> {

    /**
     * It exchange the values of the DTO when the sorting algorithm requires.
     *
     * @param theOther The other DTO with whom to exchange data.
     */
    void swap(T theOther);

}
