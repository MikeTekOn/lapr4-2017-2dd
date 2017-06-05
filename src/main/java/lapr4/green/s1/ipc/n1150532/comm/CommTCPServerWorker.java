package lapr4.green.s1.ipc.n1150532.comm;

import lapr4.green.s1.ipc.n1150738.securecomm.BasicDataTransmissionContext;
import lapr4.green.s1.ipc.n1150738.securecomm.DataTransmissionContext;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150738.securecomm.SecureAESDataTransmissionContext;
import lapr4.green.s1.ipc.n1150738.securecomm.trash.streams.NonClosingInputStreamWrapper;
import lapr4.green.s1.ipc.n1150738.securecomm.trash.streams.NonClosingOutputStreamWrapper;

/**
 * A TCP server dedicated to one specific client.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommTCPServerWorker extends Thread {

    /**
     * The socket of the worker.
     */
    private Socket socket;

    /**
     * The server who created the worker.
     */
    private CommTCPServer server;

    /**
     * The worker input stream. It is attached to the socket input stream.
     */
    private ObjectInputStream inStream;

    /**
     * The worker output stream. It is attached to the socket output stream.
     */
    private ObjectOutputStream outStream;
    private DataTransmissionContext transmissionContext;
    private OutputStream socketOut;
    private InputStream socketIn;

    /**
     * The TCP server worker constructor.
     *
     * @param theSocket The socket provided by the server on client acceptance.
     * @param theServer The server who created the worker.
     */
    public CommTCPServerWorker(Socket theSocket, CommTCPServer theServer) {
        socket = theSocket;
        server = theServer;
        inStream = null;
        outStream = null;
        try {
            socketOut = socket.getOutputStream();
            socketIn = socket.getInputStream();
            byte[] sec = new byte[4];
            sec[0] = (byte)socketIn.read();
            sec[1] = (byte)socketIn.read();
            sec[2] = (byte)socketIn.read();
            sec[3] = (byte)socketIn.read();
            if(sec[0] == (byte)0 && sec[1] == (byte)0 && sec[2] == (byte)0 && sec[3] == (byte)0){
                System.out.println("SecureMode");
                transmissionContext = new SecureAESDataTransmissionContext();
            } else {
                System.out.println("Unsecure");
                transmissionContext = new BasicDataTransmissionContext();
            }
        } catch (IOException e) {
            Logger.getLogger(CommTCPClientWorker.class.getName()).log(Level.SEVERE, null, e);
            ;
        }
    }

    /**
     * It remains sending requests and replies to them. It uses the handlers in
     * order to know how to reply. To send requests, the output stream must be
     * used.
     */
    @Override
    public void run() {
        try {
            outStream = transmissionContext.outputStream(socketOut);
            inStream = transmissionContext.inputStream(socketIn);
            while (true) {
                Object dto = inStream.readObject();
                processIncommingDTO(dto);
            }
        } catch (SocketException ex) {
            //@FIXME O cliente fechou a ligação.
            // Deve retirar da UI a ligação.
            Logger.getLogger(CommTCPServerWorker.class.getName()).log(Level.SEVERE, "", ex);
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
            Logger.getLogger(CommTCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * It handles the received DTO. It gets the right handler from the server.
     * It encapsulates the DTO in a SocketEncapsulationDTO in order to provide
     * the socket to the handler.
     *
     * @param inDTO The object to handler.
     */
    private void processIncommingDTO(Object inDTO) {
        System.out.println(inDTO.getClass());
        CommHandler handler = server.getHandler(inDTO.getClass());
        if (handler != null) {
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
//    public void switchDataTransmissionContext(DataTransmissionContext ctx) {
//        this.transmissionContext.wiretapInput().transferTappers(ctx.wiretapInput());
//        this.transmissionContext.wiretapOutput().transferTappers(ctx.wiretapOutput());
//        this.transmissionContext = ctx;
//
//        try {
//            outStream.close();
//            inStream.close();
//            outStream = transmissionContext.outputStream(socketOut);
//            inStream = transmissionContext.inputStream(socketIn);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public SocketAddress getRemoteAdress(){
        return socket.getRemoteSocketAddress();
    }

    public DataTransmissionContext getTransmissionContext() {
        return transmissionContext;
    }
}
