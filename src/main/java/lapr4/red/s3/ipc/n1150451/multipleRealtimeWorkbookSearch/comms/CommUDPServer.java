package lapr4.red.s3.ipc.n1150451.multipleRealtimeWorkbookSearch.comms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficInputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FindWorkbooksPublisher;

/**
 * A UDP client to send a broadcast and wait for replies.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommUDPServer extends Thread {

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
    private TrafficOutputStream out = null;

    /**
     * An input stream to read bytes from a byte array.
     */
    private ByteArrayInputStream bis = null;

    /**
     * An input stream to read objects from a byte array input stream.
     */
    private TrafficInputStream in = null;

    /**
     * The port number in which to receive the broadcast.
     */
    private static int portNumber = 15309;

    /**
     * The handlers of the client.
     */
    private final Map<Class, CommHandler> handlers;
    private Object lastRcvDTO;

    /**
     * The UDP client constructor.
     *
     */
    public CommUDPServer() {
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
            sock = new DatagramSocket(++portNumber, null);
            sock.setBroadcast(true);
            byte[] data;
            DatagramPacket udpPacket;
            while (true) {
                data = new byte[sock.getReceiveBufferSize()];
                byte[] data1 = new byte[512];
                udpPacket = new DatagramPacket(data1, data1.length);
                udpPacket.setData(data1);
                udpPacket.setLength(data1.length);
                sock.receive(udpPacket);
                data=udpPacket.getData();
                bis = new ByteArrayInputStream(data);
                in = new TrafficInputStream(bis, udpPacket.getAddress(), 15310, new OpenTransmission());
                processIncommingDTO(in.readObjectOvveride(), udpPacket);
            }
        } catch (SocketTimeoutException ex) {
            // There are no more replies, the client should finish its execution
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
            lastRcvDTO = handler.getLastReceivedDTO();
            FindWorkbooksPublisher.getInstance().notifyObservers(lastRcvDTO);
        }
        lastRcvDTO = null;
    }

}
