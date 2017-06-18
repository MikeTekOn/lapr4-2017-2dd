package lapr4.green.s1.ipc.n1150532.startSharing;

import csheets.core.Address;
import csheets.core.Spreadsheet;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 * @author Guilherme Ferreira 1150623  Added Share Name
 * @author Meireles
 */
public class ShareCellsController {

    private final ConnectionID connection;
    
    public ShareCellsController(ConnectionID theConnection){
        connection = theConnection;
    }

    public void shareCells(Spreadsheet sheet, String theFirstCellRow, String theFirstCellColumn, String theLastCellRow, String theLastCellColumn, String shareName){
        CommTCPClientsManager.getManager().shareCellsWith(connection, sheet, new Address(theFirstCellColumn,theFirstCellRow), new Address(theLastCellColumn,theLastCellRow), shareName);
    }

}
