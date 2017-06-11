package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate;

import csheets.core.Cell;
import csheets.core.CellListener;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 * Represents a listener that looks for cell's content changes.
 *
 * @author Ivo Ferro [1151159]
 */
public class ShareContentCellListener implements CellListener {

    /**
     * THe controller to share share the cell content.
     */
    private final SharingAutomaticUpdateController controller;

    /**
     * Creates a new share content cell listener.
     *
     * @param connection the connection ID
     */
    public ShareContentCellListener(ConnectionID connection) {
        controller = new SharingAutomaticUpdateController(connection);
    }

    @Override
    public void valueChanged(Cell cell) {
        controller.shareCellContent(cell);
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
}
