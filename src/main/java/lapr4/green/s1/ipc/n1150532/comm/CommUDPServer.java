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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficInputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 * The UDP server. It is a singleton. It replies to the UDP clients.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommUDPServer extends Thread {

    /**
     * The UDP server. It is a singleton.
     */
    private static CommUDPServer theServer;

    /**
     * The server socket.
     */
    private DatagramSocket socket = null;

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
     * The port number used in the server socket.
     */
    private int portNumber;

    /**
     * The UDP server handlers.
     */
    private static Map<Class, CommHandler> handlers;

    /**
     * Private constructor to ensure the singleton.
     */
    private CommUDPServer() {
        handlers = new HashMap<>();
    }

    /**
     * A getter of the singleton. It builds it if not yet performed.
     *
     * @return It returns the singleton manager.
     */
    public static CommUDPServer getServer() {
        if (theServer == null) {
            theServer = new CommUDPServer();
        }
        return theServer;
    }

    /**
     * It orders the server to run, i.e. it starts the server's thread. It
     * builds the server if not yet done.
     *
     * @param thePortNumber The port number to use in the server.
     */
    public void startServer(int thePortNumber) {
        getServer();
        portNumber = thePortNumber;
        theServer.start();
    }

    /**
     * It adds a new handler to the server. These handlers are used by the
     * server's workers.
     *
     * @param dto The class to be handled.
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
     * It keeps receiving DatagramPackets and replies to them through the use of
     * handlers.
     */
    @Override
    public void run() {
        byte[] data;
        DatagramPacket udpPacket;
        try {
            socket = new DatagramSocket(portNumber);
            while (true) {
                data = new byte[socket.getReceiveBufferSize()];
                bis = new ByteArrayInputStream(data);
                 udpPacket = new DatagramPacket(data, data.length);
                socket.receive(udpPacket);
                in = new TrafficInputStream(bis, InetAddress.getLocalHost(), socket.getLocalPort(), new OpenTransmission());
                bos = new ByteArrayOutputStream();
                out = new TrafficOutputStream(bos, InetAddress.getLocalHost(), socket.getLocalPort(), new OpenTransmission());
                Object obj = in.readObjectOvveride();
                processIncommingDTO(obj, udpPacket);
                data = bos.toByteArray();
                udpPacket.setData(data);
                udpPacket.setLength(data.length);
                socket.send(udpPacket);
            }
        } catch (SocketException ex) {
            Logger.getLogger(CommUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CommUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            terminateExecution();
        }
    }

    /**
     * It closes the inputs and outputs streams, as well as the socket.
     */
    public synchronized void terminateExecution() {
        try {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                in.close();
                in = null;
            }
            if (bos != null) {
                bos.close();
                bos = null;
            }
            if (bis != null) {
                bis.close();
                bis = null;
            }
            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (IOException ex) {
            Logger.getLogger(CommUDPServer.class.getName()).log(Level.SEVERE, null, ex);
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
