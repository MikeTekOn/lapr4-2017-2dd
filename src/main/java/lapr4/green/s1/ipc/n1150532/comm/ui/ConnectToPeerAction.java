package lapr4.green.s1.ipc.n1150532.comm.ui;

import csheets.ui.ctrl.BaseAction;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

import javax.swing.*;

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
     * The connection information.
     */
    private final ConnectionID connection;

    /**
     * The full constructor of the action.
     *
     * @param theConnection The connection information in which to send the
     * request
     */
    public ConnectToPeerAction(ConnectionID theConnection) {
        connection = theConnection;
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
        int res = JOptionPane.showConfirmDialog(null, "Do you wish to establish a secure connection?", "Establish Connection", JOptionPane.YES_NO_OPTION);
        CommTCPClientsManager.getManager().requestConnectionTo(connection, res == JOptionPane.YES_OPTION ? true : false);
    }

}
