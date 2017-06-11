package lapr4.green.s1.ipc.n1150532.startSharing;

import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

import java.util.SortedSet;

/**
 * An event that contains the shared cells received and the connection.
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
     * The connection ID.
     */
    private final ConnectionID connection;

    /**
     * The full constructor of the event.
     *
     * @param theSpreadsheetName The spreadsheet name from where the Cells are
     *                           original.
     * @param theCells           The shared cells.
     */
    public SharedCellsEvent(String theSpreadsheetName, SortedSet<CellDTO> theCells, ConnectionID theConnection) {
        spreadsheetName = theSpreadsheetName;
        sharedcells = theCells;
        connection = theConnection;
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

    /**
     * Gets the connection ID.
     *
     * @return connection ID
     */
    public ConnectionID getConnection() {
        return connection;
    }
}
