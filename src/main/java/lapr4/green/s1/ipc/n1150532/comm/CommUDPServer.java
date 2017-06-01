package lapr4.green.s1.ipc.n1150532.comm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
class CommUDPServer extends Thread {

    private static CommUDPServer theServer;
    private DatagramSocket socket = null;
    private ByteArrayOutputStream bos = null;
    private ObjectOutputStream out = null;
    private ByteArrayInputStream bis = null;
    private ObjectInputStream in = null;
    private final int portNumber;
    private static Map<Class, CommHandler> handlers;

    private CommUDPServer(int thePortNumber) {
        handlers = new HashMap<>();
        portNumber = thePortNumber;
    }

    public static CommUDPServer getServer(int portNumber) {
        if (theServer == null) {
            theServer = new CommUDPServer(portNumber);
            theServer.start();
        }
        return theServer;
    }

    public void addHandler(Class dto, CommHandler handler) {
        handlers.put(dto, handler);
    }

    public CommHandler getHandler(Class dto) {
        return handlers.get(dto);
    }

    public synchronized void terminateExecution() {
        socket.close();
        socket = null;
    }

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
                in = new ObjectInputStream(bis);
                bos = new ByteArrayOutputStream();
                out = new ObjectOutputStream(bos);
                processIncommingDTO(in.readObject());
                data = bos.toByteArray();
                udpPacket.setData(data);
                udpPacket.setLength(data.length);
                socket.send(udpPacket);
            }
        } catch (SocketException ex) {
            Logger.getLogger(CommUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CommUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if(socket!=null)
                socket.close();
        }
    }

    private void processIncommingDTO(Object inDTO) {
        CommHandler handler = getHandler(inDTO.getClass());
        if (handler != null) {
            handler.handleDTO(inDTO, out);
        }
    }

}
