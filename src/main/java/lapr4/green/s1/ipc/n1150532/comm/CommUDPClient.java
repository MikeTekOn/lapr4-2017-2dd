package lapr4.green.s1.ipc.n1150532.comm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 * A UDP client to send a broadcast and wait for replies.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommUDPClient extends Thread {

    /**
     * The broadcast address.
     */
    private static final String BROADCAST_ADDRESS = "255.255.255.255";

    /**
     * The client's socket.
     */
    private DatagramSocket sock = null;

    /**
     * An output stream to write bytes to a byte array.
     */
    private ByteArrayOutputStream bos = null;

    /**
     * An output stream to write objects to a byte array output stream.
     */
    private ObjectOutputStream out = null;

    /**
     * An input stream to read bytes from a byte array.
     */
    private ByteArrayInputStream bis = null;

    /**
     * An input stream to read objects from a byte array input stream.
     */
    private ObjectInputStream in = null;

    /**
     * The port number in which to send the broadcast.
     */
    private final int portNumber;

    /**
     * The request DTO.
     */
    private final Object dto;

    /**
     * The timeout for replies. The client will end its service if no replies
     * are received after this time, since there is no way to know how many
     * replies there will exist.
     */
    private final int waitingTime;

    /**
     * The handlers of the client.
     */
    private final Map<Class, CommHandler> handlers;

    /**
     * The UDP client constructor.
     *
     * @param dtoToSend The request DTO.
     * @param thePortNumber The port number in which to send the broadcast.
     * @param theWaitingTime The receiving replies timeout value.
     */
    public CommUDPClient(Object dtoToSend, int thePortNumber, int theWaitingTime) {
        dto = dtoToSend;
        portNumber = thePortNumber;
        waitingTime = theWaitingTime;
        handlers = new HashMap<>();
    }

    /**
     * It adds an handler to the client's handler.
     *
     * @param dto The class to handle.
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
     * It sends a broadcast with the request and then waits for replies. It will
     * remain receiving replies until it times out.
     */
    @Override
    public void run() {
        try {
            sock = new DatagramSocket();
            sock.setBroadcast(true);
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(dto);
            byte[] data = bos.toByteArray();
            DatagramPacket udpPacket = new DatagramPacket(data, data.length, InetAddress.getByName(BROADCAST_ADDRESS), portNumber);
            sock.send(udpPacket);
            sock.setSoTimeout(waitingTime);
            while (true) {
                data = new byte[sock.getReceiveBufferSize()];
                udpPacket = new DatagramPacket(data, data.length);
                sock.receive(udpPacket);
                bis = new ByteArrayInputStream(data);
                in = new ObjectInputStream(bis);
                processIncommingDTO(in.readObject(), udpPacket);
            }
        } catch (SocketTimeoutException ex) {
            // There are no more replies, the client should finish its execution
        } catch (SocketException ex) {
            Logger.getLogger(CommUDPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CommUDPClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            terminateExecution();
        }
    }

    /**
     * It closes the inputs and outputs streams, as well as the socket.
     */
    private void terminateExecution() {
        try {
            if (out != null) {
                out.close();
                out = null;
            }
            if (bos != null) {
                bos.close();
                bos = null;
            }
            if (in != null) {
                in.close();
                in = null;
            }
            if (bis != null) {
                bis.close();
                bis = null;
            }
            if (sock != null) {
                sock.close();
                sock = null;
            }
        } catch (IOException ex) {
            Logger.getLogger(CommUDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * It handles the received DTO. It gets the right handler. It encapsulates
     * the DTO in a PacketEncapsulationDTO in order to provide the
     * DatagramPacket to the handler.
     *
     * @param inDTO The object to handler.
     */
    private void processIncommingDTO(Object inDTO, DatagramPacket inPacket) {
        CommHandler handler = getHandler(inDTO.getClass());
        if (handler != null) {
            PacketEncapsulatorDTO dto = new PacketEncapsulatorDTO(inPacket, handler, inDTO);
            handler.handleDTO(dto, out);
        }
    }

}
