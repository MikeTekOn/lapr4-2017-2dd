package lapr4.blue.s2.ipc.n1140822.fileShare;

import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class FileSharingController {

    /**
     * The connection id.
     */
    private ConnectionID connection;

    public FileSharingController(ConnectionID connection) {
        this.connection = connection;
    }

    /**
     * Requests a file from another client.
     * 
     * @param fileName the filename from the file that was requested
     * @return true if requested went ok, false otherwise.
     */
    public boolean requestFile(String fileName) {
        CommTCPClientsManager.getManager().requestFile(connection, fileName);
        return true;
    }

}
