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

/**
 *
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommUDPClient extends Thread {

    private static final String BROADCAST_ADDRESS = "255.255.255.255";
    private DatagramSocket sock = null;
    private ByteArrayOutputStream bos = null;
    private ObjectOutputStream out = null;
    private ByteArrayInputStream bis = null;
    private ObjectInputStream in = null;
    private final int portNumber;
    private final Object dto;
    private final int waitingTime;
    private final Map<Class, CommHandler> handlers;

    public CommUDPClient(Object dtoToSend, int thePortNumber, int theWaitingTime) {
        dto = dtoToSend;
        portNumber = thePortNumber;
        waitingTime = theWaitingTime;
        handlers = new HashMap<>();
    }

    public void addHandler(Class dto, CommHandler handler) {
        handlers.put(dto, handler);
    }

    public CommHandler getHandler(Class dto) {
        return handlers.get(dto);
    }

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
                processIncommingDTO(in.readObject());
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

    private void processIncommingDTO(Object inDTO) {
        CommHandler handler = getHandler(inDTO.getClass());
        if (handler != null) {
            handler.handleDTO(inDTO, null);
        }
    }

}
