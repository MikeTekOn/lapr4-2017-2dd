package lapr4.green.s1.ipc.n1150532.comm;

import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150738.securecomm.BasicDataTransmissionContext;
import lapr4.green.s1.ipc.n1150738.securecomm.DataTransmissionContext;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A TCP client to communicate with a specific server. It receives objects from
 * the server and can send objects through the output stream.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommTCPClientWorker extends Thread implements Serializable {

    /**
     * The manager of the worker.
     */
    private final CommTCPClientsManager manager;

    /**
     * The socket to be used by the worker.
     */
    private Socket socket;

    /**
     * The address to which the socket is bounded. It matches the server's
     * address with whom to communicate.
     */
    private final InetAddress serverIP;

    /**
     * The port number on which the socket is bounded. It matches the server's
     * port number with whom to communicate.
     */
    private final int portNumber;

    /**
     * The input stream from which the objects received on the socket are read.
     */
    private ObjectInputStream inStream;

    /**
     * The output stream to which the objects are sent through the socket.
     */
    private ObjectOutputStream outStream;
    private DataTransmissionContext transmissionContext;

    /**
     * The worker constructor. It binds the socket.
     *
     * @param theManager The manager of the worker.
     * @param theServerIP The address on which to bind the socket.
     * @param thePortNumber The port number on which to bind the socket.
     */
    public CommTCPClientWorker(CommTCPClientsManager theManager, InetAddress theServerIP, int thePortNumber) {
        serverIP = theServerIP;
        portNumber = thePortNumber;
        try {
            socket = new Socket(serverIP, portNumber);
        } catch (IOException ex) {
            Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("Unable to connect to server.");
        }
        manager = theManager;
        inStream = null;
        outStream = null;
        transmissionContext = new BasicDataTransmissionContext();
    }

    /**
     * It gets the port number bounded by the socket.
     *
     * @return It returns the socket's bounded port number.
     */
    public InetAddress getServerIPAddress() {
        return serverIP;
    }

    /**
     * It gets the address bounded by the socket.
     *
     * @return It returns the socket's InetAddress.
     */
    public int getServerPortNumber() {
        return portNumber;
    }

    /**
     * It provided the worker output stream in order to send objects to the
     * server side.
     *
     * @return It returns the object output stream from the socket output
     * stream.
     * @throws IOException if the output stream encounters an error.
     */
    public synchronized ObjectOutputStream getObjectOutputStream() throws IOException {
        if (outStream != null) {
            return outStream;
        } else {
            outStream = transmissionContext.outputStream(socket.getOutputStream());
            return outStream;
        }
    }

    /**
     * It provided the worker input stream in order to receive objects from the
     * server side.
     *
     * @return It returns the object input stream from the socket input stream.
     * @throws IOException if the input stream encounters an error.
     */
    public synchronized ObjectInputStream getObjectInputStream() throws IOException {
        if (inStream != null) {
            return inStream;
        } else {
            inStream = transmissionContext.inputStream(socket.getInputStream());
            return inStream;
        }
    }

    /**
     * It builds the output and input stream and awaits the server to send
     * objects. In order to send objects from this side, one must get the output
     * stream and use it to write them.
     */
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

    /**
     * It closes the input and output streams, as well as the socket.
     */
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

    /**
     * It handles the received object.
     *
     * @param inDTO The received object.
     */
    private void processIncommingDTO(Object inDTO) {
        CommHandler handler = manager.getHandler(inDTO.getClass());
        if (handler != null) {
            //handler.handleDTO(inDTO, outStream);
            SocketEncapsulatorDTO dto = new SocketEncapsulatorDTO(socket, handler, inDTO);
            handler.handleDTO(dto, outStream);
        }
    }

    /**
     * Henrique Oliveira [1150738@isep.ipp.pt]
     * @param s
     * @return
     */
    public boolean hasSocket(Socket s){
        return socket == s;
    }

    /**
     * @author Henrique Oliveira [1150738@isep.ipp.pt]
     *
     * @param ctx
     */
    public void switchDataTransmissionContext(DataTransmissionContext ctx) {
        this.transmissionContext.wiretapInput().transferTappers(ctx.wiretapInput());
        this.transmissionContext.wiretapOutput().transferTappers(ctx.wiretapOutput());
        this.transmissionContext = ctx;
    }
}
