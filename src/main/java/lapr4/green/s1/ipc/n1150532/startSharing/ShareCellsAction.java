package lapr4.green.s1.ipc.n1150532.startSharing;

import csheets.core.Address;
import csheets.core.Spreadsheet;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Meireles
 */
public class ShareCellsAction extends BaseAction {

    private static final String NAME = "Connect to Peer";
    private final ConnectionID connection;
    private final UIController uiController;
    
    public ShareCellsAction(ConnectionID theConnection, UIController theUIController){
        connection = theConnection;
        uiController = theUIController;
    }

    @Override
    protected String getName() {
        return NAME;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ShareCellsUI(NAME, uiController, connection);
    }
    
}
