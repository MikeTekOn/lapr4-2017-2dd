package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm;

import csheets.core.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.SortedSet;

import static org.junit.Assert.assertEquals;

/**
 * Tests the class CellContentDTO.
 * @author Guilherme Ferreira 1150623 added spreadsheet to cellDTO
 * @author Ivo Ferro [1151159]
 */
public class CellContentDTOTest {

    /**
     * The instance to be tested.
     */
    private CellContentDTO instance;

    private Spreadsheet spread;

    /**
     * Sets up the instance for the tests.
     */
    @Before
    public void setUp() {
        spread = new Spreadsheet() {
            Workbook w = new Workbook();

            @Override
            public Workbook getWorkbook() {
                return w;
            }

            @Override
            public String getTitle() {
                return "titulo";
            }

            @Override
            public void setTitle(String title) {
                //nothing
            }

            @Override
            public int getColumnCount() {
                return 0;
            }

            @Override
            public int getRowCount() {
                return 0;
            }

            @Override
            public Cell getCell(Address address) {
                return null;
            }

            @Override
            public Cell getCell(int column, int row) {
                return null;
            }

            @Override
            public SortedSet<Cell> getCells(Address address1, Address address2) {
                return null;
            }

            @Override
            public Cell[] getColumn(int index) {
                return new Cell[0];
            }

            @Override
            public Cell[] getRow(int index) {
                return new Cell[0];
            }

            @Override
            public void addCellListener(CellListener listener) {

            }

            @Override
            public void removeCellListener(CellListener listener) {

            }

            @Override
            public CellListener[] getCellListeners() {
                return new CellListener[0];
            }

            @Override
            public Spreadsheet getExtension(String name) {
                return null;
            }

            @Override
            public Iterator<Cell> iterator() {
                return null;
            }
        };

        instance = new CellContentDTO(new Address(0, 0), "abc", spread);
    }

    /**
     * Tests the method getAddress.
     */
    @Test
    public void getAddress() {
        assertEquals(instance.getAddress(), new Address(0, 0));
    }

    /**
     * Tests the method getContent.
     */
    @Test
    public void getContent() {
        assertEquals(instance.getContent(), "abc");
    }

    @Test
    public void getSpreadSheet(){assertEquals(instance.spreadsheet(), spread);}
}