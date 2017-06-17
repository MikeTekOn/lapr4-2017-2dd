package lapr4.red.s3.ipc.n1150451.multipleRealtimeWorkbookSearch;

import lapr4.blue.s2.ipc.n1140822.fileShare.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficInputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 * A UDP client to send a broadcast and wait for replies.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt) and Renato Oliveira
 * (1140822@isep.ipp.pt) and Diogo Santos (1150451@isep.ipp.pt)
 */
public class CommUDPClient extends Thread implements Observer {

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
     * The port number in which to send the broadcast.
     */
    private final int portNumber;

    /**
     * The request DTO.
     */
    private Object dto;


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
    public CommUDPClient(Object dtoToSend, int thePortNumber) {
        dto = dtoToSend;
        portNumber = thePortNumber;
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
     * It sends a broadcast with the request and it doesn't wait for replies.
     */
    @Override
    public void run() {
        try {
                sock = new DatagramSocket();
                sock.setBroadcast(true);
                bos = new ByteArrayOutputStream();
                out = new TrafficOutputStream(bos, InetAddress.getLocalHost(), sock.getLocalPort(), new OpenTransmission());
                out.write(dto);
                byte[] data = bos.toByteArray();
                DatagramPacket udpPacket = new DatagramPacket(data, data.length, InetAddress.getByName(BROADCAST_ADDRESS), portNumber);
                sock.send(udpPacket);
                System.out.println("Enviei broadcast");
        } catch (SocketTimeoutException ex) {
            // There are no more replies, the client should finish its execution
        } catch (IOException ex) {
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

    @Override
    public void update(Observable o, Object o1) {
        this.dto = o1;
    }


}
