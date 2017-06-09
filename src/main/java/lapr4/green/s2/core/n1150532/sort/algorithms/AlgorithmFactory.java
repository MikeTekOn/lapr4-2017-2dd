package lapr4.green.s2.core.n1150532.sort.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * The factory responsible for building algorithms.
 *
 * @author Manuel Meireles (1150532)
 */
public class AlgorithmFactory {

    /**
     * The full constructor of the factory.
     */
    public AlgorithmFactory() {
    }

    /**
     * It builds all available Sorting Algorithms.
     *
     * @return It provides a list with each available sorting algorithms.
     */
    public List<SortingAlgorithm> buildAllSortingAlgorithms() {
        List<SortingAlgorithm> list = new ArrayList<>();
        list.add(new QuickSort());
        list.add(new BubbleSort());
        return list;
    }

}
