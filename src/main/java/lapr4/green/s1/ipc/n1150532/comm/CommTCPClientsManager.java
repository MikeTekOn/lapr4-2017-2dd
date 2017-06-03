package lapr4.green.s1.ipc.n1150532.comm;

import csheets.core.Address;
import csheets.core.Spreadsheet;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionRequestDTO;

/**
 * A singleton to manage all the TCP clients created.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommTCPClientsManager implements Serializable {

    /**
     * The singleton object.
     */
    private static CommTCPClientsManager manager;

    /**
     * The existing TCP client workers identified by the InetAddress of their
     * connection.
     */
    private final Map<InetAddress, CommTCPClientWorker> clients;

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
     * It adds a handler to be used by its clients.
     *
     * @param dto The class which the handler will handle.
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
     * @param serverAddress The server InetAddress.
     * @param portNumber The server port number.
     */
    public void requestConnectionTo(InetAddress serverAddress, int portNumber) {
        if (clients.get(serverAddress) == null) {
            CommTCPClientWorker worker = new CommTCPClientWorker(this, serverAddress, portNumber);
            ConnectionRequestDTO request = new ConnectionRequestDTO(CommTCPServer.getServer().provideConnectionPort(), serverAddress, portNumber);
            worker.start();
            try {
                worker.getObjectOutputStream().writeObject(request);
            } catch (IOException ex) {
                Logger.getLogger(CommTCPClientsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            addClient(serverAddress, worker);
        }
    }

    /**
     * It sends a range of cells to the server. if a client does not exist, it
     * does nothing.
     *
     * @param serverAddress The server InetAddress to identify the client to
     * use.
     * @param spreadsheet The spreadsheet from which to extract the cells.
     * @param firstAddress The first address of the range.
     * @param secondAddress The second address of the range.
     */
    public void shareCellsWith(InetAddress serverAddress, Spreadsheet spreadsheet, Address firstAddress, Address secondAddress) {
        if (clients.get(serverAddress) != null) {

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
     * @param serverIPAddress The client identifier.
     * @param worker The client itself.
     */
    private void addClient(InetAddress serverIPAddress, CommTCPClientWorker worker) {
        clients.put(serverIPAddress, worker);
    }

}
