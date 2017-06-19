package lapr4.green.s1.ipc.n1150532.comm;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficInputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.TransmissionStrategy;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

import java.io.*;
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
    private TrafficInputStream inStream;

    /**
     * The output stream to which the objects are sent through the socket.
     */
    private TrafficOutputStream outStream;
    private TransmissionStrategy transmissionContext;
    private OutputStream socketOut;
    private InputStream socketIn;

    /**
     * The worker constructor. It binds the socket.
     *
     * @param theManager    The manager of the worker.
     * @param theServerIP   The address on which to bind the socket.
     * @param thePortNumber The port number on which to bind the socket.
     */
    public CommTCPClientWorker(CommTCPClientsManager theManager, InetAddress theServerIP, int thePortNumber, boolean secure) {
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

        try {
            socketOut = socket.getOutputStream();
            socketIn = socket.getInputStream();
            //socket.setSoTimeout(150000);
//
//            socketIn.mark(0);
//
//            if (secure) {
//
//                socketOut.write(0xFFFF);
//
//                transmissionContext = new AESEncryptedTransmission();
//                outStream = new TrafficOutputStream(socketOut, theServerIP, thePortNumber, new OpenTransmission());

//            } else {

//                socketOut.write(0x0);

                transmissionContext = new OpenTransmission();
//                outStream = new TrafficOutputStream(socketOut, theServerIP, thePortNumber, new OpenTransmission());
//            }
        } catch (IOException e) {
            Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.SEVERE, null, e);
        }
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
    public synchronized TrafficOutputStream getObjectOutputStream() throws IOException {
        if (outStream != null) {
            return outStream;
        } else {
            outStream = new TrafficOutputStream(socket.getOutputStream(), serverIP, portNumber, transmissionContext);
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
    public synchronized TrafficInputStream getObjectInputStream() throws IOException {
        if (inStream != null) {

            return inStream;
        } else {

//            socketIn.reset();
            inStream = new TrafficInputStream(socket.getInputStream(), serverIP, portNumber, transmissionContext);
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
                Object dto = inStream.readObjectOvveride();
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
        System.out.println(inDTO.getClass());
        CommHandler handler = manager.getHandler(inDTO.getClass());
        if (handler != null) {
            //handler.handleDTO(inDTO, outStream);
            SocketEncapsulatorDTO dto = new SocketEncapsulatorDTO(socket, handler, inDTO);
            handler.handleDTO(dto, outStream);
        }
    }

//    /**
//     * Henrique Oliveira [1150738@isep.ipp.pt]
//     *
//     * @param s
//     * @return
//     */
//    public boolean hasSocket(Socket s) {
//        return socket == s;
//    }
//
////    /**
////     * @author Henrique Oliveira [1150738@isep.ipp.pt]
////     *
////     * @param ctx
////     */
////    public void switchDataTransmissionContext(DataTransmissionContext ctx) {
////        this.transmissionContext.wiretapInput().transferTappers(ctx.wiretapInput());
////        this.transmissionContext.wiretapOutput().transferTappers(ctx.wiretapOutput());
////        this.transmissionContext = ctx;
////        try {
////            outStream.close();
////            inStream.close();
////            outStream = transmissionContext.outputStream(socketOut);
////            inStream = transmissionContext.inputStream(socketIn);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }


    public TransmissionStrategy getTransmissionContext() {
        return transmissionContext;
    }

}
