package lapr4.green.s1.ipc.n1150532.comm.ui;

import csheets.ui.ctrl.BaseAction;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;

/**
 * An action to perform a TCP connection with a peer.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class ConnectToPeerAction extends BaseAction {

    /**
     * The name of the action.
     */
    private static final String NAME = "Connect to Peer";

    /**
     * The server's port number to connect.
     */
    private final int portNumber;

    /**
     * The server's address to connect.
     */
    private final InetAddress ipAddress;

    /**
     * The full constructor of the action.
     *
     * @param theIPAddress The server's address to connect.
     * @param thePortNumber The server's port number to connect.
     */
    public ConnectToPeerAction(InetAddress theIPAddress, int thePortNumber) {
        ipAddress = theIPAddress;
        portNumber = thePortNumber;
    }

    /**
     * A getter to the action name.
     *
     * @return It returns the action name.
     */
    @Override
    protected String getName() {
        return NAME;
    }

    /**
     * It performs the connection on action event.
     *
     * @param e The action event. It is not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CommTCPClientsManager.getManager().requestConnectionTo(ipAddress, portNumber);
    }

}
