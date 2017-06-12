package lapr4.green.s1.ipc.n1150532.comm;

import lapr4.blue.s2.ipc.n1060503.chat.connection.UserChatDTO;
import lapr4.blue.s2.ipc.n1060503.chat.connection.HandlerUserChatDTO;
import csheets.core.Cell;
import csheets.core.CellImpl;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.Extension;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.RequestSharedCellsDTO;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.ResponseSharedCellsDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.ShareContentCellListener;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.StyleListener;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.CellContentDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.CellStyleDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.HandlerCellContentDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.HandlerCellStyleDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.util.Styles;
import lapr4.green.s1.ipc.n1150532.comm.connection.*;
import lapr4.green.s1.ipc.n1150532.comm.ui.UICommExtension;
import lapr4.green.s1.ipc.n1150532.comm.ui.UICommExtensionSideBar;
import lapr4.green.s1.ipc.n1150532.startSharing.HandlerRequestSharedCellsDTO;
import lapr4.green.s1.ipc.n1150532.startSharing.HandlerResponseSharedCellsDTO;
import lapr4.green.s1.ipc.n1150532.startSharing.SharedCellsEvent;

import java.util.Observable;
import java.util.Observer;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lapr4.blue.s2.ipc.n1140822.fileShare.FileDTO;
import lapr4.blue.s2.ipc.n1140822.fileShare.FileNameDTO;
import lapr4.blue.s2.ipc.n1140822.fileShare.FileNameListDTO;
import lapr4.blue.s2.ipc.n1140822.fileShare.HandlerFileDTO;
import lapr4.blue.s2.ipc.n1140822.fileShare.HandlerFileNameDTO;
import lapr4.blue.s2.ipc.n1140822.fileShare.HandlerFileNameListDTO;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.HandlerSearchWorkbookRequestDTO;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.SearchWorkbookRequestDTO;
import lapr4.green.s1.ipc.n1150657.chat.ControllerConnection;
import lapr4.green.s1.ipc.n1150657.chat.HandlerRequestMessageDTO;
import lapr4.green.s1.ipc.n1150657.chat.MessageEvent;
import lapr4.green.s1.ipc.n1150657.chat.RequestMessageDTO;
import lapr4.green.s1.ipc.n1150657.chat.ServerMessage;
import lapr4.green.s1.ipc.n1150901.search.workbook.HandlerRequestWorkbookDTO;
import lapr4.green.s1.ipc.n1150901.search.workbook.HandlerResponseWorkbookDTO;
import lapr4.green.s1.ipc.n1150901.search.workbook.RequestWorkbookDTO;
import lapr4.green.s1.ipc.n1150901.search.workbook.ResponseWorkbookDTO;
import lapr4.green.s1.ipc.n1150901.search.workbook.SearchWorkbookEvent;

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
     * The description of the extension
     */
    public static final String DESCRIPTION = "The Communication extension.\n"
            + "It manages all the servers and clients of the application.\n"
            + "It allows for a UDP broadcast to search for other instances in the local network.\n"
            + "It allows for TCP connections to be performed and maintained for other uses like cells sharing.\n"
            + "This class listens to the events launched by the handlers and deals with them using the UI Controller.";
    
    /**
     * The first version of the communication extension.
     */
    public static final int VERSION = 1;
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

    private Map<InetAddress, ServerMessage> threadsList;

    /**
     * The extension constructor.
     */
    public CommExtension() {
        super(NAME, VERSION, DESCRIPTION);
        threadsList = new HashMap<>();
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
     * It stops the threads messages.
     */
    public void stopMessages() {
        for (InetAddress address : threadsList.keySet()) {
            threadsList.get(address).stopThread();
        }
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
        HandlerRequestWorkbookDTO h3 = new HandlerRequestWorkbookDTO();
        h3.addObserver(this);
        tcpServer.addHandler(RequestWorkbookDTO.class, h3);
        HandlerRequestMessageDTO h4 = new HandlerRequestMessageDTO();
        h4.addObserver(this);
        tcpServer.addHandler(RequestMessageDTO.class, h4);
        HandlerFileNameDTO h5 = new HandlerFileNameDTO();
        tcpServer.addHandler(FileNameDTO.class, h5);
        HandlerFileDTO h6 = new HandlerFileDTO();
        tcpServer.addHandler(FileDTO.class, h6);
        HandlerCellStyleDTO h7 = new HandlerCellStyleDTO();
        h7.addObserver(this);
        tcpServer.addHandler(CellStyleDTO.class, h7);
        HandlerCellContentDTO h8 = new HandlerCellContentDTO();
        h8.addObserver(this);
        tcpServer.addHandler(CellContentDTO.class, h8);
        HandlerUserChatDTO hucp = new HandlerUserChatDTO();
        tcpServer.addHandler(UserChatDTO.class, hucp);
        //TODO
    }

    /**
     * It adds all the existing handlers to the UDP server.
     */
    private void addAllAvailableHandlersToUDPServer() {
        HandlerConnectionDetailsRequestDTO h1 = new HandlerConnectionDetailsRequestDTO();
        udpServer.addHandler(ConnectionDetailsRequestDTO.class, h1);
        HandlerFileNameListDTO h2 = new HandlerFileNameListDTO();
        udpServer.addHandler(FileNameListDTO.class, h2);
        HandlerUserChatDTO hucp = new HandlerUserChatDTO();
        udpServer.addHandler(UserChatDTO.class, hucp);
        HandlerSearchWorkbookRequestDTO h3 = new HandlerSearchWorkbookRequestDTO(uiController);
        udpServer.addHandler(SearchWorkbookRequestDTO.class, h3);
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
        HandlerResponseWorkbookDTO h3 = new HandlerResponseWorkbookDTO();
        tcpClientsManager.addHandler(ResponseWorkbookDTO.class, h3);
        HandlerFileDTO h6 = new HandlerFileDTO();
       
        tcpClientsManager.addHandler(FileDTO.class, h6);
        HandlerUserChatDTO hucp = new HandlerUserChatDTO();
        tcpClientsManager.addHandler(UserChatDTO.class, hucp);
        //HandlerResponseMessageDTO h4 = new HandlerResponseMessageDTO();
        //tcpClientsManager.addHandler(ResponseMessageDTO.class,h4);
        //tcpServer.addHandler(RequestMessageDTO.class, h4);
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
                tcpClientsManager.requestConnectionTo(event.getConnectionID(), event.isSecure());
            }
            if (arg instanceof SharedCellsEvent) {
                SharedCellsEvent event = (SharedCellsEvent) arg;
                Spreadsheet aSpreadSheet = uiController.getActiveSpreadsheet();
                SortedSet<CellDTO> cellsDTO = event.getCells();
                ConnectionID connection = event.getConnection();
                for (CellDTO cellDTO : cellsDTO) {
                    try {
                        Cell cell = aSpreadSheet.getCell(cellDTO.getAddress());
                        cell.setContent(cellDTO.getContent());
                        StylableCell stylableCell = (StylableCell) cell.getExtension(StyleExtension.NAME);
                        if (stylableCell != null && cellDTO.getStyleDTO() != null) {
                            Styles.setStyleFromDTO(stylableCell, cellDTO.getStyleDTO());
                        }
                        cell.addCellListener(new ShareContentCellListener(connection));
                        if (cell instanceof CellImpl) {
                            ((CellImpl)cell).addStyleListener(new StyleListener(connection));
                        }
                    } catch (FormulaCompilationException ex) {
                        Logger.getLogger(CommExtension.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (arg instanceof SearchWorkbookEvent) {
                SearchWorkbookEvent event = (SearchWorkbookEvent) arg;
                Workbook w = uiController.getActiveWorkbook();
                Iterator<Spreadsheet> ss = w.iterator();
                while (ss.hasNext()) {
                    try {
                        ss.next().getTitle();
                    } catch (Exception ex) {
                        Logger.getLogger(CommExtension.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (arg instanceof MessageEvent) {

                MessageEvent event = (MessageEvent) arg;
                String m = event.getMessage();
                InetAddress adress = event.getSocket().getInetAddress();

                ConnectionID connection = new ConnectionIDImpl(adress, event.getSocket().getPort());
                //@FIXME quick fix, explained in class Controller Connection
                ControllerConnection.setChatController(connection);
                //@FIXME popup with time
                JOptionPane.showMessageDialog(null, "New message from " + adress.getHostName());
                //creates threads server for each conversation.
                Thread server;
                if (threadsList.containsKey(adress)) {
                    ServerMessage serverMessage = threadsList.get(adress);
                    serverMessage.addMessage(m, adress);
                    //It notifies the server for the new message.
                    //@FIXME, notify ins't doin it well
                    synchronized (serverMessage) {
                        serverMessage.notify();
                    }
                } else {
                    ServerMessage serverMessage = new ServerMessage(m, adress);
                    threadsList.put(adress, serverMessage);
                    server = new Thread(serverMessage);
                    server.start();
                    synchronized (serverMessage) {
                        serverMessage.notify();
                    }
                }

            }
            if (arg instanceof CellStyleDTO) {
                CellStyleDTO dto = (CellStyleDTO) arg;
                Spreadsheet aSpreadSheet = uiController.getActiveSpreadsheet();
                Cell cell = aSpreadSheet.getCell(dto.getAddress());
                StylableCell stylableCell = (StylableCell)cell.getExtension(StyleExtension.NAME);
                if (stylableCell != null && dto.getStyleDTO() != null) {
                    Styles.setStyleFromDTO(stylableCell, dto.getStyleDTO());
                    if (cell instanceof CellImpl) {
                        ((CellImpl) cell).updateCellStyle();
                    }
                }
            }
            if (arg instanceof CellContentDTO) {
                CellContentDTO dto = (CellContentDTO) arg;
                Spreadsheet aSpreadSheet = uiController.getActiveSpreadsheet();
                Cell cell = aSpreadSheet.getCell(dto.getAddress());
                try {
                    cell.setContent(dto.getContent());
                } catch (Exception ex) {
                    Logger.getLogger(CommExtension.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension has also a: \n-- default tcp port number: %d\n"
                + "-- default udp port number: %d\n"
                + "This extension was made by Manuel Meireles in Sprint 1 and it is in the package %s",
                getName(), getVersion(), getDescription(),
                DEFAULT_TCP_PORT_NUMBER, DEFAULT_UDP_PORT_NUMBER,
                getClass().getName());
    }

}
