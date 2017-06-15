package lapr4.green.s2.core.n1150532.sort;

import csheets.core.Cell;
import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import java.util.List;
import lapr4.blue.s3.core.n1140822.autoSorting.AutoSortingThread;
import lapr4.green.s2.core.n1150532.sort.algorithms.AlgorithmFactory;
import lapr4.green.s2.core.n1150532.sort.algorithms.BubbleSort;
import lapr4.green.s2.core.n1150532.sort.algorithms.QuickSort;
import lapr4.green.s2.core.n1150532.sort.algorithms.SortingAlgorithm;
import lapr4.green.s2.core.n1150532.sort.comparators.ComparatorFactory;
import lapr4.green.s2.core.n1150532.sort.comparators.RangeRowDTOComparator;
import lapr4.green.s2.core.n1150532.sort.sortingDTOs.RangeRowDTO;

/**
 * The controller of the SortCellRange use case. It provides the available
 * algorithms and comparators from their respective factory and sorts the
 * selected cells.
 *
 * @author Manuel Meireles (1150532)
 */
class SortCellRangeController implements Controller {

    /**
     * The comparators factory.
     */
    private final ComparatorFactory comparators;

    /**
     * the algorithms factory.
     */
    private final AlgorithmFactory algorithms;

    private UIController uiController;

    /**
     * The controller full constructor. It creates the algorithms and
     * comparators factories.
     */
    public SortCellRangeController(UIController controller) {
        comparators = new ComparatorFactory();
        algorithms = new AlgorithmFactory();
        uiController = controller;
    }

    /**
     * It provides all the range row DTO comparators from the factory.
     *
     * @return It returns a list with all available comparators.
     */
    public List<RangeRowDTOComparator> getComparators() {
        return comparators.buildAllRangeRowDTOComparators();
    }

    /**
     * It provides all the sorting algorithms from the factory.
     *
     * @return It returns a list with all available sorting algorithms.
     */
    public List<SortingAlgorithm> getAlgorithms() {
        return algorithms.buildAllSortingAlgorithms();
    }

    /**
     * It sorts the selected cells by using the chosen algorithm and comparator.
     * Also it takes into consideration the sorting column (the one which will
     * be compared) and the flag to indicate if the order should be descendant.
     *
     * @param selectedCells The cell range to sort.
     * @param sortingColumnIndex The column index. The index is relative to the
     * cell range, i.e. the column number 0 is the first column of the cell
     * range. The number must be from 0 up to cell[0].length-1.
     * @param algorithm The sorting algorithm used for sorting.
     * @param comparator The comparator used within the algorithm to compare
     * both rows.
     * @param isDescendant The descendant order flag ("true" orders the values
     * in a descendant order).
     */
    public void sort(Cell[][] selectedCells, int sortingColumnIndex, SortingAlgorithm algorithm, RangeRowDTOComparator comparator, boolean isDescendant) {

        comparator.setDescendant(isDescendant);
        RangeRowDTO[] dto = createDTOs(selectedCells, sortingColumnIndex);
       
        AutoSortingThread sortingThread = new AutoSortingThread(dto, comparator, algorithm, sortingColumnIndex);
        for (int i = 0; i < selectedCells.length; i++) {
            for (int j = 0; j < selectedCells[i].length; j++) {
                selectedCells[i][j].addCellListener(sortingThread);

            }
        }
        uiController.addHeaderCellListener(sortingThread);
        sortingThread.start();

    }

    /**
     * It builds the matching RangeRowDTOs to be used by the algorithm from the
     * selected cells.
     *
     * @param selectedCells The cells to be encapsulated within the DTO.
     * @param sortingColumnIndex The column which will be used for comparison.
     * @return It returns an array of RangeRowDTOs ready to be sorted.
     */
    private RangeRowDTO[] createDTOs(Cell[][] selectedCells, int sortingColumnIndex) {
        RangeRowDTO[] array = new RangeRowDTO[selectedCells.length];
        for (int i = 0; i < selectedCells.length; i++) {
            RangeRowDTO anElement = new RangeRowDTO(selectedCells[i], sortingColumnIndex);
            array[i] = anElement;
        }
        return array;
    }

}
