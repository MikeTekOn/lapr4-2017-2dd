package lapr4.green.s1.ipc.n1150532.comm;

import lapr4.green.s1.ipc.n1150738.securecomm.BasicDataTransmissionContext;
import lapr4.green.s1.ipc.n1150738.securecomm.DataTransmissionContext;

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
public class CommTCPClientWorker extends Thread {

    private final CommTCPClientsManager manager;
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private DataTransmissionContext transmissionContext;

    public CommTCPClientWorker(CommTCPClientsManager theManager, Socket theSocket) {
        manager = theManager;
        socket = theSocket;
        inStream = null;
        outStream = null;
        transmissionContext = new BasicDataTransmissionContext();
    }

    public synchronized ObjectOutputStream getObjectOutputStream() throws IOException {
        if (outStream != null) {
            return outStream;
        } else {
            outStream = transmissionContext.outputStream(socket.getOutputStream());
            return outStream;
        }
    }

    public synchronized ObjectInputStream getObjectInputStream() throws IOException {
        if (inStream != null) {
            return inStream;
        } else {
            inStream = transmissionContext.inputStream(socket.getInputStream());
            return inStream;
        }
    }

    @Override
    public void run() {
        try {
            getObjectOutputStream();
            getObjectInputStream();
            while (true) {
                Object dto = inStream.readObject();
                processIncommingDTO(dto);
            }
        } catch (EOFException | java.net.SocketException ex) {
            Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.WARNING, "The server seems to have closed the connection. Will terminate the worker thread.");
        } catch (IOException ex) {
            Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.WARNING, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.WARNING, null, ex);
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
            Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processIncommingDTO(Object inDTO) {
        CommHandler handler = manager.getHandler(inDTO.getClass());
        if (handler != null) {
            handler.handleDTO(inDTO, outStream);
        }
    }

}
