package lapr4.blue.s3.core.n1140822.autoSorting;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import csheets.core.Cell;
import csheets.core.CellImpl;
import csheets.core.CellListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import csheets.core.HeaderCellListener;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.FocusOwnerAction;
import lapr4.green.s2.core.n1150532.sort.algorithms.BubbleSort;
import lapr4.green.s2.core.n1150532.sort.algorithms.QuickSort;
import lapr4.green.s2.core.n1150532.sort.algorithms.SortingAlgorithm;
import lapr4.green.s2.core.n1150532.sort.comparators.RangeRowDTOComparator;
import lapr4.green.s2.core.n1150532.sort.sortingDTOs.RangeRowDTO;

import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class AutoSortingThread extends Thread implements CellListener, Observer, HeaderCellListener {

    private RangeRowDTO[] rowArray;
    private RangeRowDTOComparator comparator;
    private SortingAlgorithm algorithm;
    private int sortingColumnIndex;
    private final SortingFocusOwner focusOwner = new SortingFocusOwner();
    private int realSortingColumnIndex;
    int auxSortingIndex;

    public AutoSortingThread(RangeRowDTO[] rowArray, RangeRowDTOComparator comparator, SortingAlgorithm algorithm, int sortingColumnIndex, int realSortingColumnIndex) {
        this.rowArray = rowArray;
        this.comparator = comparator;
        this.algorithm = algorithm;
        this.realSortingColumnIndex = realSortingColumnIndex;
        this.sortingColumnIndex = sortingColumnIndex;

        if (algorithm instanceof BubbleSort) {
            ((BubbleSort) algorithm).addObserver(this);

        } else {
            ((QuickSort) algorithm).addObserver(this);
        }
        auxSortingIndex = sortingColumnIndex;
        runInitial();
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
        if (!o1.equals(algorithm)) {
            algorithm.sort(rowArray, comparator);

        }
    }

    @Override
    public void headerValueChanged(int colIndex) {

        if (colIndex > realSortingColumnIndex && colIndex - realSortingColumnIndex > 1) {
            colIndex = (colIndex - realSortingColumnIndex) + auxSortingIndex;

        } else {
            if (colIndex - realSortingColumnIndex == 1) {
                colIndex = auxSortingIndex + 1;
            } else {
                if (colIndex <= realSortingColumnIndex) {
                    colIndex = Math.abs((colIndex - realSortingColumnIndex) + auxSortingIndex);
                }
            }
        }
        if (colIndex <= rowArray[0].getRow().length && colIndex >= 0) {
            Cell[][] selectedCells = focusOwner.getSelectedCells();
            if (selectedCells.length == rowArray.length) {
                if (this.sortingColumnIndex != colIndex) {
                    for (int i = 0; i < rowArray.length; i++) {
                        if (Arrays.deepEquals(selectedCells[i], rowArray[i].getRow())) {
                            sortingColumnIndex = colIndex;
                            rowArray[i].updateSortingColumn(sortingColumnIndex);
                        }
                    }
                    paintSortingRange(selectedCells, this.sortingColumnIndex, comparator.orderState());
                    algorithm.sort(rowArray, comparator);
                } else if (this.sortingColumnIndex == colIndex) {
                    boolean shouldInvert = true;
                    for (int i = 0; i < rowArray.length; i++) {
                        shouldInvert = Arrays.deepEquals(selectedCells[i], rowArray[i].getRow());
                        if (!shouldInvert) {
                            break;
                        }
                    }
                    if (shouldInvert) {
                        comparator.changeOrder();
                        paintSortingRange(selectedCells, this.sortingColumnIndex, comparator.orderState());
                        algorithm.sort(rowArray, comparator);
                    }
                }

            }
        }
    }

    private void paintSortingColumn(StylableCell stCell, int indexToCheck, boolean isAscending) {
        if (indexToCheck == this.sortingColumnIndex) {
            if (!isAscending)
                stCell.setBackgroundColor(Color.yellow);

            else
                stCell.setBackgroundColor(Color.ORANGE);
        } else {
            stCell.setBackgroundColor(Color.WHITE);
        }
    }

    private void paintSortingRange(Cell[][] selectedCells, int sortingColumnIndex, boolean isAscending) {
        for (int i = 0; i < selectedCells.length; i++) {

            for (int j = 0; j < selectedCells[i].length; j++) {
                StylableCell stylableCell = (StylableCell) selectedCells[i][j].getExtension(StyleExtension.NAME);
                paintSortingColumn(stylableCell, j, isAscending);
                if (i == 0 && j == 0) {
                    stylableCell.setBorder(new MatteBorder(1, 1, 0, 0, Color.BLUE));

                } else if (i == 0 && j == selectedCells[i].length - 1) {
                    stylableCell.setBorder(new MatteBorder(1, 0, 0, 1, Color.BLUE));

                } else if (i == 0) {
                    stylableCell.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLUE));

                }
                if (i == selectedCells.length - 1 && j == 0) {
                    stylableCell.setBorder(new MatteBorder(0, 1, 1, 0, Color.BLUE));

                } else if (i == selectedCells.length - 1 && j == selectedCells[i].length - 1) {
                    stylableCell.setBorder(new MatteBorder(0, 0, 1, 1, Color.BLUE));

                } else if (i == selectedCells.length - 1 && j != 0 && j != selectedCells[i].length - 1) {
                    stylableCell.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLUE));

                }
                if (j == 0 && i > 0 && i < selectedCells.length - 1) {
                    stylableCell.setBorder(new MatteBorder(0, 1, 0, 0, Color.BLUE));

                }
                if (j == selectedCells[i].length - 1 && i > 0 && i < selectedCells.length - 1) {
                    stylableCell.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLUE));

                }

            }
        }

    }

    private void runInitial() {
        Cell[][] cells = new Cell[rowArray.length][rowArray[0].getRow().length];
        for (int i = 0; i < rowArray.length; i++) {
            cells[i] = rowArray[i].getRow();

        }

        paintSortingRange(cells, sortingColumnIndex, comparator.orderState());
        algorithm.sort(rowArray, comparator);
    }

    private class SortingFocusOwner extends FocusOwnerAction implements ActionListener {
        Cell[][] selectedCells;
        int columnIndex;

        public Cell[][] getSelectedCells() {

            return this.focusOwner.getSelectedCells();
        }


        @Override
        protected String getName() {
            return "Sorting Focus Owner";
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            this.selectedCells = this.focusOwner.getSelectedCells();
        }
    }
}


