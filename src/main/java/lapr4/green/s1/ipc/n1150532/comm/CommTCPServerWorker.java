package lapr4.green.s1.ipc.n1150532.comm;

import lapr4.green.s1.ipc.n1150738.securecomm.BasicDataTransmissionContext;
import lapr4.green.s1.ipc.n1150738.securecomm.DataTransmissionContext;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150738.securecomm.streams.NonClosingInputStreamWrapper;
import lapr4.green.s1.ipc.n1150738.securecomm.streams.NonClosingOutputStreamWrapper;

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
    private NonClosingOutputStreamWrapper socketOut;
    private NonClosingInputStreamWrapper socketIn;

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

        transmissionContext = new BasicDataTransmissionContext();
    }

    /**
     * It remains sending requests and replies to them. It uses the handlers in
     * order to know how to reply. To send requests, the output stream must be
     * used.
     */
    @Override
    public void run() {
        try {
            socketOut = new NonClosingOutputStreamWrapper(socket.getOutputStream());
            socketIn = new NonClosingInputStreamWrapper(socket.getInputStream());

            outStream = transmissionContext.outputStream(socketOut);
            inStream = transmissionContext.inputStream(socketIn);
            while (true) {
                processIncommingDTO(inStream.readObject());
            }
        } catch (SocketException ex) {
            //@FIXME O cliente fechou a ligação.
            // Deve retirar da UI a ligação.
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
    public void switchDataTransmissionContext(DataTransmissionContext ctx) {
        this.transmissionContext.wiretapInput().transferTappers(ctx.wiretapInput());
        this.transmissionContext.wiretapOutput().transferTappers(ctx.wiretapOutput());
        this.transmissionContext = ctx;

        try {
            outStream.close();
            inStream.close();
            outStream = transmissionContext.outputStream(socketOut);
            inStream = transmissionContext.inputStream(socketIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
