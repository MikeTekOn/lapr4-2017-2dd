package lapr4.green.s2.core.n1150532.sort.algorithms;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * It tests the quick sort algorithm. It ensures it sorts correctly in both
 * ascending or descending order.
 *
 * @author Manuel Meireles (1150532)
 */
public class QuickSortTest {

    /**
     * The algorithm to be tested.
     */
    private SortingAlgorithm algorithm;

    /**
     * The comparator to be used within the test.
     */
    private IntegerComparator comparator;

    /**
     * The array to be sorted.
     */
    private SortableIntegerDTO[] array;

    /**
     * The first element in ascending order.
     */
    private int first;

    /**
     * The second element in ascending order.
     */
    private int second;

    /**
     * The third element in ascending order.
     */
    private int third;

    /**
     * The fourth element in ascending order.
     */
    private int fourth;

    /**
     * The fifth element in ascending order.
     */
    private int fifth;

    /**
     * The sixth element in ascending order.
     */
    private int sixth;

    /**
     * The seventh element in ascending order.
     */
    private int seventh;

    /**
     * The eighth element in ascending order.
     */
    private int eighth;

    /**
     * The ninth element in ascending order.
     */
    private int ninth;

    /**
     * The constructor of the test class.
     */
    public QuickSortTest() {
    }

    /**
     * It sets the variables for the tests.
     */
    @Before
    public void setUp() {
        algorithm = new QuickSort();
        comparator = new IntegerComparator();
        first = 1;
        second = 2;
        third = 4;
        fourth = 5;
        fifth = 5;
        sixth = 6;
        seventh = 7;
        eighth = 7;
        ninth = 9;
        array = new SortableIntegerDTO[9];
        array[0] = new SortableIntegerDTO(second);
        array[1] = new SortableIntegerDTO(fifth);
        array[2] = new SortableIntegerDTO(first);
        array[3] = new SortableIntegerDTO(ninth);
        array[4] = new SortableIntegerDTO(seventh);
        array[5] = new SortableIntegerDTO(sixth);
        array[6] = new SortableIntegerDTO(fourth);
        array[7] = new SortableIntegerDTO(eighth);
        array[8] = new SortableIntegerDTO(third);
    }

    /**
     * It ensures quick sort orders the array in ascending order.
     */
    @Test
    public void testAscendingSort() {
        comparator.setDescending(false);
        algorithm.sort(array, comparator);
        assertTrue(array[0].getMyNumber() == first);
        assertTrue(array[1].getMyNumber() == second);
        assertTrue(array[2].getMyNumber() == third);
        assertTrue(array[3].getMyNumber() == fourth);
        assertTrue(array[4].getMyNumber() == fifth);
        assertTrue(array[5].getMyNumber() == sixth);
        assertTrue(array[6].getMyNumber() == seventh);
        assertTrue(array[7].getMyNumber() == eighth);
        assertTrue(array[8].getMyNumber() == ninth);
    }

    /**
     * It ensures quick sort orders the array in descending order.
     */
    @Test
    public void testDescendingSort() {
        comparator.setDescending(true);
        algorithm.sort(array, comparator);
        assertTrue(array[0].getMyNumber() == ninth);
        assertTrue(array[1].getMyNumber() == eighth);
        assertTrue(array[2].getMyNumber() == seventh);
        assertTrue(array[3].getMyNumber() == sixth);
        assertTrue(array[4].getMyNumber() == fifth);
        assertTrue(array[5].getMyNumber() == fourth);
        assertTrue(array[6].getMyNumber() == third);
        assertTrue(array[7].getMyNumber() == second);
        assertTrue(array[8].getMyNumber() == first);
    }

}
