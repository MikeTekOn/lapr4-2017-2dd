package lapr4.green.s1.ipc.n1150532.comm;

import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.RequestSharedCellsDTO;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.ResponseSharedCellsDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.*;
import lapr4.green.s1.ipc.n1150532.comm.ui.UICommExtension;
import lapr4.green.s1.ipc.n1150532.comm.ui.UICommExtensionSideBar;
import lapr4.green.s1.ipc.n1150532.startSharing.HandlerRequestSharedCellsDTO;
import lapr4.green.s1.ipc.n1150532.startSharing.HandlerResponseSharedCellsDTO;
import lapr4.green.s1.ipc.n1150532.startSharing.SharedCellsEvent;
import lapr4.green.s1.ipc.n1150738.securecomm.*;

import java.util.Observable;
import java.util.Observer;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Communication extension. It manages all the servers and clients of the
 * application. It allows for a UDP broadcast to search for other instances in
 * the local network. It allows for TCP connections to be performed and
 * maintained for other uses like cells sharing. This class listens to the
 * events launched by the handlers and deals with them using the UI Controller.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommExtension extends Extension implements Observer {

    /**
     * The property name from which to retrieve the TCP port number for the
     * server.
     */
    private static final String PROPERTY_KEY_TCP_PORT_NUMBER = "comm.tcp.server.portNumber";

    /**
     * The property name from which to retrieve the UDP port number for the
     * server.
     */
    private static final String PROPERTY_KEY_UDP_PORT_NUMBER = "comm.udp.server.portNumber";

    /**
     * A default value to be used as the TCP server port number in case the
     * property value is not found.
     */
    private static final int DEFAULT_TCP_PORT_NUMBER = 15320;

    /**
     * A default value to be used as the UDP server port number in case the
     * property value is not found.
     */
    private static final int DEFAULT_UDP_PORT_NUMBER = 15310;

    /**
     * The name of the extension.
     */
    public static final String NAME = "Network";

    /**
     * The TCP server. It is a singleton.
     */
    private CommTCPServer tcpServer;

    /**
     * The UDP server. It is a singleton.
     */
    private CommUDPServer udpServer;

    /**
     * The TCP clients manager. It is a singleton.
     */
    private CommTCPClientsManager tcpClientsManager;

    /**
     * The UI Controller from which to get the user properties and change data.
     */
    private UIController uiController;

    /**
     * The extension constructor.
     */
    public CommExtension() {
        super(NAME);
    }

    /**
     * It starts the servers. It initializes the singletons, i.e. it starts the
     * threads of the servers, with the defined port numbers. It adds the
     * existing handlers to the singletons.
     *
     * @param theUIController The UI Controller that will be used to deal with
     * the events launched by the handlers.
     */
    public void startServers(UIController theUIController) {
        uiController = theUIController;
        tcpServer = CommTCPServer.getServer();
        addAllAvailableHandlersToTCPServer();
        tcpServer.startServer(getTCPServerPortNumber());
        udpServer = CommUDPServer.getServer();
        addAllAvailableHandlersToUDPServer();
        udpServer.startServer(getUDPServerPortNumber());
        tcpClientsManager = CommTCPClientsManager.getManager();
        addAllAvailableHandlersToTCPClientsManager();
    }

    /**
     * It stops the servers. It ensures that all connections related threads are
     * terminated.
     */
    public void stopServers() {
        tcpServer.terminateExecution();
        udpServer.terminateExecution();
        tcpClientsManager.terminateExecution();
    }

    /**
     * It provides the UI Extension to render.
     *
     * @param uiController The UI Controller.
     * @return The UI Extension.
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        return new UICommExtension(this, uiController);
    }

    /**
     * It adds all the existing handlers to the TCP server. It also observes
     * them.
     */
    private void addAllAvailableHandlersToTCPServer() {
        HandlerConnectionRequestDTO h1 = new HandlerConnectionRequestDTO();
        h1.addObserver(this);
        tcpServer.addHandler(ConnectionRequestDTO.class, h1);
        HandlerRequestSharedCellsDTO h2 = new HandlerRequestSharedCellsDTO();
        h2.addObserver(this);
        tcpServer.addHandler(RequestSharedCellsDTO.class, h2);
        TransmissionContextRequestHandler h3 = new TransmissionContextRequestHandler();
        h3.addObserver(this);
        tcpServer.addHandler(TransmissionContextRequestDTO.class, h3);
        //TODO 
    }

    /**
     * It adds all the existing handlers to the UDP server.
     */
    private void addAllAvailableHandlersToUDPServer() {
        HandlerConnectionDetailsRequestDTO h1 = new HandlerConnectionDetailsRequestDTO();
        udpServer.addHandler(ConnectionDetailsRequestDTO.class, h1);
        //TODO 
    }

    /**
     * It adds all the existing handlers to the TCP clients. It also observes
     * them.
     */
    private void addAllAvailableHandlersToTCPClientsManager() {
        HandlerConnectionResponseDTO h1 = new HandlerConnectionResponseDTO();
        h1.addObserver(this);
        tcpClientsManager.addHandler(ConnectionResponseDTO.class, h1);
        HandlerResponseSharedCellsDTO h2 = new HandlerResponseSharedCellsDTO();
        tcpClientsManager.addHandler(ResponseSharedCellsDTO.class, h2);
        TransmissionContextResponseHandler h3 = new TransmissionContextResponseHandler();
        h3.addObserver(this);
        tcpServer.addHandler(TransmissionContextResponseDTO.class, h3);
        //TODO 
    }

    /**
     * It provides the TCP server port to use. It loads it from the user
     * properties or uses a default if the properties can not be loaded.
     *
     * @return It returns the port number for the TCP server.
     */
    public int getTCPServerPortNumber() {
        int port = getServerPortNumber(PROPERTY_KEY_TCP_PORT_NUMBER);
        if (port == 0) {
            port = DEFAULT_TCP_PORT_NUMBER;
        }
        return port;
    }

    /**
     * It provides the UDP server port to use. It loads it from the user
     * properties or uses a default if the properties can not be loaded.
     *
     * @return It returns the port number for the UDP server.
     */
    public int getUDPServerPortNumber() {
        int port = getServerPortNumber(PROPERTY_KEY_UDP_PORT_NUMBER);
        if (port == 0) {
            port = DEFAULT_UDP_PORT_NUMBER;
        }
        return port;
    }

    /**
     * It loads the port number from a property.
     *
     * @param propertyKey The name of the property to search.
     * @return It returns the Integer read from the property.
     */
    private int getServerPortNumber(String propertyKey) {
        try {
            return Integer.parseInt(uiController.getUserProperties().getProperty(propertyKey));
        } catch (NumberFormatException | NullPointerException ex) {
            Logger.getLogger(CommUDPServer.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * It deals with the events launched by the handlers.
     *
     * @param o The handler who published the event.
     * @param arg The event published.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            if (arg instanceof ConnectionResponseDTO) {
                ConnectionResponseDTO event = (ConnectionResponseDTO) arg;
                for (UIExtension ext : uiController.getExtensions()) {
                    if (ext instanceof UICommExtension) {
                        UICommExtension extension = (UICommExtension) ext;
                        ((UICommExtensionSideBar) extension.getSideBar()).addPeerConnection(event.getConnectionID());
                    }
                }
            }
            if (arg instanceof NewConnectionMadeEvent) {
                NewConnectionMadeEvent event = (NewConnectionMadeEvent) arg;
                tcpClientsManager.requestConnectionTo(event.getConnectionID());
            }
            if (arg instanceof SharedCellsEvent) {
                SharedCellsEvent event = (SharedCellsEvent) arg;
                Spreadsheet ss = uiController.getActiveSpreadsheet();
                SortedSet<CellDTO> cells = event.getCells();
                for (CellDTO cell : cells) {
                    try {
                        ss.getCell(cell.getAddress()).setContent(cell.getContent());
                    } catch (FormulaCompilationException ex) {
                        Logger.getLogger(CommExtension.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if(arg instanceof SwitchDataTransmissionContextEvent) {
                SwitchDataTransmissionContextEvent event = (SwitchDataTransmissionContextEvent) arg;
                CommTCPClientWorker cliWorker = tcpClientsManager.workerBySocket(event.getSocket());
                if(cliWorker != null){
                    cliWorker.switchDataTransmissionContext(event.getTransmissionContext());
                } else {
                    CommTCPServerWorker svWorker = tcpServer.workerBySocket(event.getSocket());
                    if(svWorker != null){
                        svWorker.switchDataTransmissionContext(event.getTransmissionContext());
                    } else {
                        Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.SEVERE, "Non existing socket to switch data transmission context");
                    }
                }
            }

            if(arg instanceof IllegalDataTransmissionContextEvent) {
                IllegalDataTransmissionContextEvent event = (IllegalDataTransmissionContextEvent) arg;
                CommTCPClientWorker cliWorker = tcpClientsManager.workerBySocket(event.getSocket());
                if(cliWorker != null){
                    //TODO
                } else {
                    CommTCPServerWorker svWorker = tcpServer.workerBySocket(event.getSocket());
                    if(svWorker != null){
                        //TODO
                    } else {
                        Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.SEVERE, "Non existing socket to terminate");
                    }
                }
            }
        }
    }

}
