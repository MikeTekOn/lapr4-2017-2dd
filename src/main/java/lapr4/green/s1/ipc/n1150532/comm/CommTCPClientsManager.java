package lapr4.green.s1.ipc.n1150532.comm;

import csheets.core.*;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.RequestSharedCellsDTO;
import lapr4.blue.s2.ipc.n1140822.fileShare.FileNameDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.ShareContentCellListener;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.StyleListener;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.CellContentDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.CellStyleDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionRequestDTO;
import lapr4.green.s1.ipc.n1150657.chat.RequestMessageDTO;
import lapr4.green.s1.ipc.n1150738.securecomm.NewConnectionOnManagerEvent;
import lapr4.green.s1.ipc.n1150901.search.workbook.RequestWorkbookDTO;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A singleton to manage all the TCP clients created.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommTCPClientsManager extends Observable implements Serializable {

    /**
     * The singleton object.
     */
    private static CommTCPClientsManager manager;

    /**
     * The existing TCP client workers identified by the ConnectionID.
     */
    private final Map<ConnectionID, CommTCPClientWorker> clients;

    /**
     * The handlers available to its clients.
     */
    private Map<Class, CommHandler> handlers;

    /**
     * Private constructor to ensure singleton.
     */
    private CommTCPClientsManager() {
        clients = new HashMap<>();
        handlers = new HashMap<>();
    }

    /**
     * A getter of the singleton. It builds it if not yet performed.
     *
     * @return It returns the singleton manager.
     */
    public static CommTCPClientsManager getManager() {
        if (manager == null) {
            manager = new CommTCPClientsManager();
        }
        return manager;
    }

    /**
     * A getter of the current connections available.
     *
     * @return It returns the map containing the connections.
     */
    public Map<ConnectionID, CommTCPClientWorker> getClients() {
        return clients;
    }

    /**
     * It adds a handler to be used by its clients.
     *
     * @param dto     The class which the handler will handle.
     * @param handler The handler itself.
     */
    public void addHandler(Class dto, CommHandler handler) {
        handlers.put(dto, handler);
    }

    /**
     * It gets a handler for the matching class.
     *
     * @param dto The class to handle.
     * @return It returns the handler or null if no matching handler is found.
     */
    public CommHandler getHandler(Class dto) {
        return handlers.get(dto);
    }

    /**
     * It builds a TCP connection with a server. Afterwards it adds the created
     * worker to the manager's clients.
     *
     * @param theConnection The connection information.
     */
    public void requestConnectionTo(ConnectionID theConnection, boolean secure) {
        if (clients.get(theConnection) == null) {
            CommTCPClientWorker worker = new CommTCPClientWorker(this, theConnection.getAddress(), theConnection.getPortNumber(), secure);
            ConnectionRequestDTO request = new ConnectionRequestDTO(CommTCPServer.getServer().provideConnectionPort(), theConnection.getAddress(), theConnection.getPortNumber(), secure);
            worker.start();
            try {
                worker.getObjectOutputStream().writeObject(request);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CommTCPClientsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            addClient(theConnection, worker);
        }
    }

    /**
     * It sends a range of cells to the server. if a client does not exist, it
     * does nothing.
     *
     * @param connection   The connection id.
     * @param spreadsheet  The spreadsheet from which to extract the cells.
     * @param firstAddress The first address of the range.
     * @param lastAddress  The last address of the range.
     */
    public void shareCellsWith(ConnectionID connection, Spreadsheet spreadsheet, Address firstAddress, Address lastAddress) {
        CommTCPClientWorker worker = clients.get(connection);
        if (worker != null) {
            addSharingListeners(connection, spreadsheet, firstAddress, lastAddress);
            RequestSharedCellsDTO request = new RequestSharedCellsDTO(spreadsheet.getTitle(), spreadsheet, firstAddress, lastAddress);
            try {
                worker.getObjectOutputStream().writeObject(request);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CommTCPClientsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Adds the content change listeners to a range of cells.
     *
     * @param connection   the connection ID
     * @param spreadsheet  the spreadsheet
     * @param firstAddress the first address of the range
     * @param lastAddress  the last address of the range
     */
    private void addSharingListeners(ConnectionID connection, Spreadsheet spreadsheet,
                                     Address firstAddress, Address lastAddress) {
        SortedSet<Cell> cells = spreadsheet.getCells(firstAddress, lastAddress);
        for (Cell aCell : cells) {
            aCell.addCellListener(new ShareContentCellListener(connection));
            if (aCell instanceof CellImpl) {
                ((CellImpl) aCell).addStyleListener(new StyleListener(connection));
            }
        }
    }

    /**
     * Shares the style of a given cell.
     *
     * @param connection the connection ID
     * @param cell       the cell who has the style to be shared
     */
    public void shareCellStyle(ConnectionID connection, Cell cell) {
        CommTCPClientWorker worker = clients.get(connection);
        if (worker != null) {
            CellStyleDTO cellStyleDTO = CellStyleDTO.createFromCell(cell);
            try {
                worker.getObjectOutputStream().writeObject(cellStyleDTO);
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(CommTCPClientsManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /**
     * Shares the content of the given cell.
     *
     * @param connection the connection ID
     * @param cell       the cell who has the style to be shared
     */
    public void shareCellContent(ConnectionID connection, Cell cell) {
        CommTCPClientWorker worker = clients.get(connection);
        if (worker != null) {
            CellContentDTO cellContentDTO = CellContentDTO.createFromCell(cell);
            try {
                worker.getObjectOutputStream().writeObject(cellContentDTO);
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(CommTCPClientsManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    /**
     * Tries to search the workbook in the connection received by parameter.
     *
     * @param connection   Id to search the workbook in.
     * @param workbookName The workbook name to search.
     * @return Returns true if it finds the workbook.
     */
    public Workbook searchWorkbookIn(ConnectionID connection, String workbookName) {
        CommTCPClientWorker worker = clients.get(connection);

        if (worker != null) {
            RequestWorkbookDTO request = new RequestWorkbookDTO(workbookName);
            try {
                worker.getObjectOutputStream().writeObject(request);
                //TODO return workbook object found and replace "return null"
                return null;
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CommTCPClientsManager.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        } else {
            return null;
        }
    }

    /**
     * It orders all clients to terminate their execution and deletes the
     * manager.
     */
    public void terminateExecution() {
        for (CommTCPClientWorker client : clients.values()) {
            client.terminateExecution();
        }
        manager = null;
    }

    /**
     * It adds a client to the manager's clients.
     *
     * @param connection The client identifier.
     * @param worker     The client itself.
     */
    private void addClient(ConnectionID connection, CommTCPClientWorker worker) {
        clients.put(connection, worker);
        setChanged();
        notifyObservers(new NewConnectionOnManagerEvent());
    }

//    public CommTCPClientWorker workerBySocket(Socket s){
//        for(Map.Entry<ConnectionID, CommTCPClientWorker> entry : clients.entrySet()){
//            if(entry.getValue().hasSocket(s)){
//                return entry.getValue();
//            }
//        }
//        return null;
//    }

    /**
     * IT sends the message to the server
     *
     * @param connection The connection id.
     * @param message    The message.
     */
    public void sendMessageWith(ConnectionID connection, String message) {
        CommTCPClientWorker worker = clients.get(connection);
        if (worker != null) {
            RequestMessageDTO dto = new RequestMessageDTO(message);
            try {
                worker.getObjectOutputStream().writeObject(dto);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CommTCPClientsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void requestFile(ConnectionID connection, String fileName) {
        requestConnectionTo(connection, false);
        CommTCPClientWorker worker = clients.get(connection);
        if (worker != null) {
            try {
                FileNameDTO nameDTO = new FileNameDTO(fileName);
                worker.getObjectOutputStream().writeObject(nameDTO);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CommTCPClientsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
