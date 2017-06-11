package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate;

import csheets.core.Cell;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 * Represents an application controller to share automatics updates.
 *
 * @author Ivo Ferro [1151159]
 */
public class SharingAutomaticUpdateController {

    private final ConnectionID connection;

    /**
     * Creates an instance of sharing automatic update controller.
     *
     * @param theConnection the connection ID
     */
    public SharingAutomaticUpdateController(ConnectionID theConnection) {
        connection = theConnection;
    }

    public void shareCellStyle(Cell cell) {
        CommTCPClientsManager.getManager().shareCellStyle(connection, cell);
    }

    public void shareCellContent(Cell cell) {
        CommTCPClientsManager.getManager().shareCellContent(connection, cell);
    }

}
