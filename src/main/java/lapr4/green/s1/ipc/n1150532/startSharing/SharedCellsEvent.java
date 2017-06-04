package lapr4.green.s1.ipc.n1150532.startSharing;

import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;

import java.util.SortedSet;

/**
 * An event that contains the shared cells received.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class SharedCellsEvent {

    /**
     * The spreadsheet name from where the cells are original.
     */
    private final String spreadsheetName;

    /**
     * The shared cells.
     */
    private final SortedSet<CellDTO> sharedcells;

    /**
     * The full constructor of the event.
     *
     * @param theSpreadsheetName The spreadsheet name from where the Cells are
     *                           original.
     * @param theCells           The shared cells.
     */
    public SharedCellsEvent(String theSpreadsheetName, SortedSet<CellDTO> theCells) {
        spreadsheetName = theSpreadsheetName;
        sharedcells = theCells;
    }

    /**
     * A getter of the spreadsheet name.
     *
     * @return It returns the spreadsheet name.
     */
    public String getSpreadsheetName() {
        return spreadsheetName;
    }

    /**
     * A getter of the shared cells.
     *
     * @return It returns the shared cells.
     */
    public SortedSet<CellDTO> getCells() {
        return sharedcells;
    }

}
