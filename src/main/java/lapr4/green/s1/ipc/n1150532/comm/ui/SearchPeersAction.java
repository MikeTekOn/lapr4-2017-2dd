package lapr4.green.s1.ipc.n1150532.comm.ui;

import csheets.ui.ctrl.BaseAction;
import java.awt.event.ActionEvent;
import java.util.Observer;
import lapr4.green.s1.ipc.n1150532.comm.CommUDPClient;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionDetailsRequestDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionDetailsResponseDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.HandlerConnectionDetailsResponseDTO;

/**
 * An action to perform a UDP broadcast searching for other instances in the local network.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class SearchPeersAction extends BaseAction {

    /**
     * The name of the action.
     */
    private static final String NAME = "Search Peers";

    /**
     * The waiting time without a reply before ending.
     */
    private static final int TIMEOUT = 2000;

    /**
     * The server's port number to connect.
     */
    private final int portNumber;

    /**
     * The observer who will collect the instances found.
     */
    private final Observer table;
    
    /**
     * The full constructor of the action.
     * 
     * @param theTable The observer who will handle all the new found instances.
     * @param thePortNumber The port number to which the broadcast should be sent.
     */
    public SearchPeersAction(Observer theTable, int thePortNumber){
        table= theTable;
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
     * It performs the broadcast search on action event.
     *
     * @param e The action event. It is not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ConnectionDetailsRequestDTO request = new ConnectionDetailsRequestDTO();
        CommUDPClient worker = new CommUDPClient( request, portNumber, TIMEOUT);
        HandlerConnectionDetailsResponseDTO handler = new HandlerConnectionDetailsResponseDTO();
        handler.addObserver(table);
        worker.addHandler(ConnectionDetailsResponseDTO.class, handler);
        worker.start();
    }
    
}
