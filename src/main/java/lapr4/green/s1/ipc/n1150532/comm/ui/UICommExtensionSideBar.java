package lapr4.green.s1.ipc.n1150532.comm.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lapr4.blue.s2.ipc.n1140822.fileShare.FileSharingController;
import lapr4.blue.s2.ipc.n1140822.fileShare.ShareAction;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.startSharing.ShareCellsAction;
import lapr4.green.s1.ipc.n1150657.chat.ControllerConnection;
import lapr4.green.s1.ipc.n1150657.chat.ui.ChatAction;

/**
 * The UI for the CommExtension's side bar.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class UICommExtensionSideBar extends JPanel {

    /**
     * The UI Controller.
     */
    private final UIController theController;

    /**
     * The extension.
     */
    private final CommExtension theExtension;

    /**
     * The button to configure the port numbers.
     */
    private JButton btConfigure;

    /**
     * The button to start the servers.
     */
    private JButton btActivate;

    /**
     * The button to stop the servers.
     */
    private JButton btDeactivate;

    /**
     * The button to search peers.
     */
    private JButton btSearch;
    
    private JButton btShare;

    /**
     * The button to connect to a peer.
     */
    private JButton btConnect;

    /**
     * The button to share cells.
     */
    private JButton btShareCells;

    /**
     * The button to share messages
     */
    private JButton btMessage;

    /**
     * A table with the available instances within the network.
     */
    private ConnectionTable networkTable;

    /**
     * A table with the connect peers.
     */
    private ConnectionTable peersTable;

    /**
     * The full constructor of the side bar.
     *
     * @param theMainController The UI Controller.
     * @param theCommExtension The extension.
     */
    public UICommExtensionSideBar(UIController theMainController, Extension theCommExtension) {
        super();
        theController = theMainController;
        theExtension = (CommExtension) theCommExtension;
        createSideBar();
        createInteractions();
        setInitialChanges();
    }

    /**
     * It adds a new peer to the peers table.
     *
     * @param connection The new connection to add as peer.
     */
    public void addPeerConnection(ConnectionID connection) {
        peersTable.insertRow(connection);
    }

    /**
     * It builds the UI of the side bar.
     */
    private void createSideBar() {
        setLayout(new BorderLayout());
        add(createCompleteNetworkPanel(), BorderLayout.CENTER);
        add(createCompletePeersPanel(), BorderLayout.SOUTH);
    }

    /**
     * It builds the whole network panel.
     *
     * @return It return the panel.
     */
    private JPanel createCompleteNetworkPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(createNetworkTopButtonsPanel(), BorderLayout.NORTH);
        panel.add(createNetworkPanel(), BorderLayout.CENTER);
        panel.add(createNetworkBottomButtonsPanel(), BorderLayout.SOUTH);
        return panel;
    }

    /**
     * It builds the whole peers panel.
     *
     * @return It return the panel.
     */
    private JPanel createCompletePeersPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(createPeersPanel(), BorderLayout.CENTER);
        panel.add(createPeersBottomButtonsPanel(), BorderLayout.SOUTH);
        return panel;
    }

    /**
     * It builds the northern buttons panel from the network panel.
     *
     * @return It return the panel.
     */
    private JPanel createNetworkTopButtonsPanel() {
        final String configureBtText = "Configure";
        final String activateBtText = "Activate";
        final String deactivateBtText = "Deactivate";
        final int allignment = FlowLayout.CENTER;
        final JPanel panel = new JPanel(new GridLayout(1, 3));
        final JPanel p1 = new JPanel(new FlowLayout(allignment));
        final JPanel p2 = new JPanel(new FlowLayout(allignment));
        final JPanel p3 = new JPanel(new FlowLayout(allignment));
        btConfigure = new JButton(configureBtText);
        btActivate = new JButton(activateBtText);
        btDeactivate = new JButton(deactivateBtText);
        p1.add(btConfigure);
        p2.add(btActivate);
        p3.add(btDeactivate);
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        return panel;
    }

    /**
     * It builds the main panel from the network panel.
     *
     * @return It return the panel.
     */
    private JPanel createNetworkPanel() {
        networkTable = new ConnectionTable();
        return networkTable;
    }

    /**
     * It builds the southern buttons panel from the network panel.
     *
     * @return It return the panel.
     */
    private JPanel createNetworkBottomButtonsPanel() {
        final String searchBtText = "Search";
        final String connectBtText = "Connect";
        final int allignment = FlowLayout.CENTER;
        final JPanel panel = new JPanel(new GridLayout(1, 2));
        final JPanel p1 = new JPanel(new FlowLayout(allignment));
        final JPanel p2 = new JPanel(new FlowLayout(allignment));
        btSearch = new JButton(searchBtText);
        btConnect = new JButton(connectBtText);
        p1.add(btSearch);
        p2.add(btConnect);
        panel.add(p1);
        panel.add(p2);
        return panel;
    }

    /**
     * It builds the main panel from the peers panel.
     *
     * @return It return the panel.
     */
    private JPanel createPeersPanel() {
        peersTable = new ConnectionTable();
        return peersTable;
    }

    /**
     * It builds the souther buttons panel from the peers panel.
     *
     * @return It return the panel.
     */
    private JPanel createPeersBottomButtonsPanel() {
        final String shareCellsBtText = "Share Cells";
        final String sharetext ="Share files";
        final int allignment = FlowLayout.CENTER;
        final JPanel panel = new JPanel(new GridLayout(1, 2));
        final JPanel p1 = new JPanel(new FlowLayout(allignment));
        btShareCells = new JButton(shareCellsBtText);
        btShare = new JButton(sharetext);
        p1.add(btShareCells);
        final String message = "New Message";
        final JPanel p2 = new JPanel(new FlowLayout(allignment));
        final JPanel p3 = new JPanel(new FlowLayout(allignment));
        btMessage = new JButton(message);
        p2.add(btMessage);
        p3.add(btShare);
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        return panel;
    }

    /**
     * It adds the action listeners.
     */
    private void createInteractions() {
        btConfigure.addActionListener(new ConfigurePortAction(theController.getUserProperties()));
        btActivate.addActionListener(new StartServersAction());
        //@FIXME The deactivating is launching exceptions.
//        btDeactivate.addActionListener(new StopServersAction());
        btSearch.addActionListener(new SearchAction());
        btConnect.addActionListener(new ConnectAction());
        btShareCells.addActionListener(new ShareCellsWithAction());
        btMessage.addActionListener(new NewMessageAction());
        btShare.addActionListener(new  FileSharingAction());
    }

    /**
     * It sets the UI at its initial state.
     */
    private void setInitialChanges() {
        serversDeactivatedState();
    }

    /**
     * It sets the UI at the deactivated servers state.
     */
    private void serversDeactivatedState() {
        networkTable.clear();
        peersTable.clear();
        btConfigure.setEnabled(true);
        btActivate.setEnabled(true);
        btDeactivate.setEnabled(false);
        btSearch.setEnabled(false);
        btConnect.setEnabled(false);
        btShare.setEnabled(false);
        btShareCells.setEnabled(false);
        btMessage.setEnabled(false);
    }

    /**
     * It sets the UI at the activated servers state.
     */
    private void serversActivatedState() {
        btConfigure.setEnabled(false);
        btActivate.setEnabled(false);
        btDeactivate.setEnabled(true);
        btSearch.setEnabled(true);
        btConnect.setEnabled(true);
        btShareCells.setEnabled(true);
        btMessage.setEnabled(true);
        btShare.setEnabled(true);
    }

    /**
     * The action listener for the activate button.
     */
    private class StartServersAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theExtension.startServers(theController);
            serversActivatedState();
        }

    }

    /**
     * The action listener for the deactivate button.
     */
    private class StopServersAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theExtension.stopServers();
            serversDeactivatedState();
        }

    }

    /**
     * The action listener for the search button.
     */
    private class SearchAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            networkTable.clear();
            (new SearchPeersAction(networkTable, theExtension.getUDPServerPortNumber())).actionPerformed(e);
        }

    }

    /**
     * The action listener for the connect button.
     */
    private class ConnectAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ConnectionID connection = networkTable.getSelectedRowFile();
            if (connection != null) {
                (new ConnectToPeerAction(connection)).actionPerformed(e);
            }
        }

    }

    /**
     * The action listener for the share cells button.
     */
    private class ShareCellsWithAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ConnectionID connection = peersTable.getSelectedRowFile();
            if (connection != null) {
                (new ShareCellsAction(connection, theController)).actionPerformed(e);
            }

        }

    }

    /**
     * The action listener for the new message.
     */
    private class NewMessageAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           ConnectionID connection = peersTable.getSelectedRowFile();
            if (connection != null) {
                ControllerConnection.setChatController(connection);
                (new ChatAction(connection, theController)).actionPerformed(e);
            } else {
                JOptionPane.showMessageDialog(null, "Connect with someone.");
            }
        }
    }

      /**
     *
     */
    private class FileSharingAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ConnectionID connection = peersTable.getSelectedRowFile();
            if (connection != null) {
                FileSharingController controller = new FileSharingController(connection);
                (new ShareAction(theExtension.getUDPServerPortNumber(), theController,connection)).actionPerformed(e);
            } 
        }
    }

}
