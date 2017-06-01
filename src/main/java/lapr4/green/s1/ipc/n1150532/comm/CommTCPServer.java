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
 *
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommTCPServer extends Thread {

    private static CommTCPServer server;
    private ServerSocket serverSocket;
    private final int portNumber;
    private static Map<Class, CommHandler> handlers;
    private static List<CommTCPServerWorker> workers;

    private CommTCPServer(int thePortNumber) {
        portNumber = thePortNumber;
        handlers = new HashMap<>();
        workers = new ArrayList<>();
    }

    public static CommTCPServer getServer(int portNumber) {
        if (server == null) {
            server = new CommTCPServer(portNumber);
            server.start();
        }
        return server;
    }

    public void addHandler(Class dto, CommHandler handler) {
        handlers.put(dto, handler);
    }

    public CommHandler getHandler(Class dto) {
        return handlers.get(dto);
    }

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

    }

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

}
