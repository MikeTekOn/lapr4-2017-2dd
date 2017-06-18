package lapr4.blue.s3.core.n1140822.autoSorting;

import csheets.CleanSheets;
import csheets.core.Address;

import csheets.core.Cell;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import lapr4.green.s2.core.n1150532.*;
import lapr4.green.s2.core.n1150532.sort.algorithms.BubbleSort;
import lapr4.green.s2.core.n1150532.sort.algorithms.IntegerComparator;
import lapr4.green.s2.core.n1150532.sort.algorithms.SortingAlgorithm;
import lapr4.green.s2.core.n1150532.sort.comparators.ComparatorFactory;
import lapr4.green.s2.core.n1150532.sort.comparators.RangeRowDTOComparator;
import lapr4.green.s2.core.n1150532.sort.sortingDTOs.RangeRowDTO;
import org.junit.*;
import org.junit.Before;

import java.io.File;
import java.util.SortedSet;

import static org.junit.Assert.*;

/**
 * Created by PRenato on 18/06/2017.
 */
public class AutoSortingThreadTest {
    private RangeRowDTO[] rowArray;
    private RangeRowDTOComparator comparator;
    private SortingAlgorithm algorithm;
    private int sortingColumnIndex;
    private CleanSheets cleansheet;
    private Workbook workbook;
    private Cell[] cells = new Cell[4];


    private int realSortingColumnIndex;
    int auxSortingIndex;
    @Before
    public void setUp() throws Exception {
        CleanSheets.setFlag(true);
        cleansheet = new CleanSheets();

        String[][] spreadsheetContent = new String[3][2];
        spreadsheetContent[0][0] = "A";
        spreadsheetContent[0][1] = "B";
        spreadsheetContent[1][0] = "C";
        spreadsheetContent[1][1] = "D";
        spreadsheetContent[2][0] = "A";
        workbook = new Workbook(spreadsheetContent);
        SortedSet<Cell> cellSet=workbook.getSpreadsheet(0).getCells(new Address(0,0),new Address(1,1));
        cells  =   cellSet.toArray(cells);
        RangeRowDTO[] dto = new RangeRowDTO[2];
        dto[0] = new RangeRowDTO(cells,0);
        dto[1] = new RangeRowDTO(cells,0);
        rowArray = dto;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void ensureThreadDoesntListenToEventsOutsideTheRange() throws FormulaCompilationException {
        ComparatorFactory fac = new ComparatorFactory();
        AutoSortingThread thread = new AutoSortingThread(rowArray, (RangeRowDTOComparator)fac.buildAllRangeRowDTOComparators().toArray()[0], new BubbleSort(),0,0);
        thread.start();
        Cell outCell = workbook.getSpreadsheet(0).getCell(new Address(0,2));
        outCell.setContent("changed");
        Cell[] cells = new Cell[4];
        cells  = workbook.getSpreadsheet(0).getCells(new Address(0,0),new Address(1,1)).toArray(cells);
        thread.interrupt();
        assertArrayEquals(this.cells,cells); //if it was sorted, should be different -- it shouldn't be sorted
    }
    @Test
    public void ensureThreadListensToItsRange() throws FormulaCompilationException {
        ComparatorFactory fac = new ComparatorFactory();
        AutoSortingThread thread = new AutoSortingThread(rowArray, (RangeRowDTOComparator)fac.buildAllRangeRowDTOComparators().toArray()[0], new BubbleSort(),0,0);
        thread.start();
        Cell outCell = workbook.getSpreadsheet(0).getCell(new Address(0,1));
        outCell.setContent("A");
        Cell[] cells = new Cell[4];
        cells  = workbook.getSpreadsheet(0).getCells(new Address(0,0),new Address(1,1)).toArray(cells);
        thread.interrupt();
        assertNotEquals(this.cells, cells);
    }
}