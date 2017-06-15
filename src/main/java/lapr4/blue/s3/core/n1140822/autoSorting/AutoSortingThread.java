package lapr4.blue.s3.core.n1140822.autoSorting;

import csheets.core.Cell;
import csheets.core.CellListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import csheets.core.HeaderCellListener;
import lapr4.green.s2.core.n1150532.sort.algorithms.BubbleSort;
import lapr4.green.s2.core.n1150532.sort.algorithms.QuickSort;
import lapr4.green.s2.core.n1150532.sort.algorithms.SortingAlgorithm;
import lapr4.green.s2.core.n1150532.sort.comparators.RangeRowDTOComparator;
import lapr4.green.s2.core.n1150532.sort.sortingDTOs.RangeRowDTO;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class AutoSortingThread extends Thread implements CellListener, Observer, HeaderCellListener {

    private RangeRowDTO[] rowArray;
    private RangeRowDTOComparator comparator;
    private SortingAlgorithm algorithm;
    private int sortingColumnIndex;
    private boolean executeOnce;

    public AutoSortingThread(RangeRowDTO[] rowArray, RangeRowDTOComparator comparator, SortingAlgorithm algorithm, int sortingColumnIndex) {
        this.rowArray = rowArray;
        this.comparator = comparator;
        this.algorithm = algorithm;
        this.sortingColumnIndex = sortingColumnIndex;
        if(algorithm instanceof BubbleSort)
        {
            ((BubbleSort) algorithm).addObserver(this);
        }
        else{
             ((QuickSort) algorithm).addObserver(this);
        }
        algorithm.sort(rowArray, comparator);
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoSortingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void valueChanged(Cell cell) {
            algorithm.sort(rowArray, comparator);
    }

    @Override
    public void contentChanged(Cell cell) {

    }

    @Override
    public void dependentsChanged(Cell cell) {

    }

    @Override
    public void cellCleared(Cell cell) {

    }

    @Override
    public void cellCopied(Cell cell, Cell source) {

    }

    @Override
    public void update(Observable o, Object o1) {
         
    }

    @Override
    public void headerValueChanged(int colIndex) {
        if(this.sortingColumnIndex!=colIndex && colIndex < rowArray[0].getRow().length)
        {
            sortingColumnIndex = colIndex;
            for (RangeRowDTO dto:rowArray) {
                dto.updateSortingColumn(sortingColumnIndex);
            }
            algorithm.sort(rowArray, comparator);
        }
    }
}
