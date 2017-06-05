package lapr4.green.s1.ipc.n1150532.comm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The TCP server. It is a singleton. It accepts the clients and dedicates them
 * a specific server worker.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommTCPServer extends Thread {

    /**
     * The TCP server. It is a singleton.
     */
    private static CommTCPServer server;

    /**
     * The server socket.
     */
    private ServerSocket serverSocket;

    /**
     * The server port number.
     */
    private int portNumber;

    /**
     * The TCP server handlers.
     */
    private static Map<Class, CommHandler> handlers;

    /**
     * It keeps all the server's workers.
     */
    private static List<CommTCPServerWorker> workers;

    /**
     * Private constructor to ensure the singleton.
     */
    private CommTCPServer() {
        handlers = new HashMap<>();
        workers = new ArrayList<>();
    }

    /**
     * A getter of the singleton. It builds it if not yet performed.
     *
     * @return It returns the singleton manager.
     */
    public static CommTCPServer getServer() {
        if (server == null) {
            server = new CommTCPServer();
        }
        return server;
    }

    /**
     * It orders the server to run, i.e. it starts the server's thread. It
     * builds the server if not yet done.
     *
     * @param thePortNumber The port number to use in the server.
     */
    public void startServer(int thePortNumber) {
        getServer();
        portNumber = thePortNumber;
        server.start();
    }

    /**
     * It provides the server's port number.
     *
     * @return It returns the server's port number.
     */
    public int provideConnectionPort() {
        return portNumber;
    }

    /**
     * It adds a new handler to the server. These handlers are used by the
     * server's workers.
     *
     * @param dto The class to be handled.
     * @param handler The handler itself.
     */
    public void addHandler(Class dto, CommHandler handler) {
        handlers.put(dto, handler);
    }

    /**
     * It provides the handler capable of dealing with the matching class.
     *
     * @param dto The class to handle.
     * @return It returns the handler or null if no capable handler is found.
     */
    public CommHandler getHandler(Class dto) {
        return handlers.get(dto);
    }

    /**
     * The server remains in an infinite loop accepting new clients. It creates
     * a new worker for each client and adds them to its workers list.
     */
    @Override
    public void run() {
        serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                CommTCPServerWorker worker = new CommTCPServerWorker(clientSocket, this);
                workers.add(worker);
                worker.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(CommTCPServer.class.getName()).log(Level.INFO, null, ex);
        } finally {
            terminateExecution();
        }

    }

    /**
     * It orders all workers to terminate their execution, closes the server's
     * socket and shuts down the server.
     */
    public synchronized void terminateExecution() {
        for (CommTCPServerWorker worker : workers) {
            worker.terminateExecution();
        }
        try {
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
            }
            server = null;
        } catch (IOException ex) {
            Logger.getLogger(CommTCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public CommTCPServerWorker workerBySocket(Socket s){
//        for(CommTCPServerWorker worker : workers){
//            if(worker.hasSocket(s)){
//                return worker;
//            }
//        }
//        return null;
//    }


    public static List<CommTCPServerWorker> getWorkers() {
        return workers;
    }
}
