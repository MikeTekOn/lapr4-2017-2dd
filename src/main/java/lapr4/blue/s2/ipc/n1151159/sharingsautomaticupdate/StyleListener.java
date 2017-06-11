package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate;

import csheets.core.Cell;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 * A listener that receives notifications when a cell style listener is changed.
 *
 * @author Ivo Ferro [1151159]
 */
public class StyleListener implements CellStyleListener {

    /**
     * The controller to share the style of the cell.
     */
    private final SharingAutomaticUpdateController controller;

    /**
     * Creates a new style change listener.
     *
     * @param connection the connection ID
     */
    public StyleListener(ConnectionID connection) {
        controller = new SharingAutomaticUpdateController(connection);
    }

    @Override
    public void styleModified(Cell cell) {
        controller.shareCellStyle(cell);
    }
}
