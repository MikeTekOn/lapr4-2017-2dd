package lapr4.green.s1.ipc.n1150532.comm;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommTCPServerWorker extends Thread {

    private Socket socket;
    private CommTCPServer server;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public CommTCPServerWorker(Socket theSocket, CommTCPServer theServer) {
        socket = theSocket;
        server = theServer;
        inStream = null;
        outStream = null;
    }

    @Override
    public void run() {
        try {
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                processIncommingDTO(inStream.readObject());
            }
        } catch (EOFException ex) {
            Logger.getLogger(CommTCPServerWorker.class.getName()).log(Level.WARNING, "The client seems to have closed the connection. Will terminate the worker thread.");
        } catch (IOException ex) {
            Logger.getLogger(CommTCPServerWorker.class.getName()).log(Level.WARNING, "The client seems to have closed the connection. Will terminate the worker thread.", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommTCPServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                    inStream = null;
                }
                if (outStream != null) {
                    outStream.close();
                    outStream = null;
                }
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            } catch (IOException ex) {
                Logger.getLogger(CommTCPServerWorker.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }

    public synchronized void terminateExecution() {
        try {
            if (inStream != null) {
                inStream.close();
                inStream = null;
            }
            if (outStream != null) {
                outStream.close();
                outStream = null;
            }
            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (IOException ex) {
            Logger.getLogger(CommTCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processIncommingDTO(Object inDTO) {
        CommHandler handler = server.getHandler(inDTO.getClass());
        if (handler != null) {
            handler.handleDTO(inDTO, outStream);
        }
    }

}
