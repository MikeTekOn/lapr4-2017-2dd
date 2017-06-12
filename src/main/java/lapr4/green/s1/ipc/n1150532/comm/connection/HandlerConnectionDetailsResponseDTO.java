package lapr4.green.s1.ipc.n1150532.comm.connection;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An handler to deal with UDP connection details response. It is supposed to be
 * used by the UDP client.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class HandlerConnectionDetailsResponseDTO extends Observable implements CommHandler {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. If the connection was accepted, it publishes the ConnectionID.
     *
     * @param dto The received DTO. It is suppose to be an ConnectionDetailsResponseDTO.
     * @param outStream The output stream to write the reply.
     */
    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        lastReceivedDTO = dto;
        PacketEncapsulatorDTO reply = (PacketEncapsulatorDTO) dto;
        InetAddress serverIP = reply.getPacket().getAddress();
        int portNumber = ((ConnectionDetailsResponseDTO) reply.getDTO()).getPortNumber();
        if (isOutsiderAnnouncement(serverIP)) {
            setChanged();
            ConnectionID connection = new ConnectionIDImpl(serverIP, portNumber);
            notifyObservers(connection);
        }
    }

    /**
     * A getter of the last received DTO.
     *
     * @return It returns the last received DTO.
     */
    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

    /**
     * It checks if the announcement came from an outsider IP address.
     *
     * @param senderIP The announcement IP address.
     * @return It returns true if the announcement IP address came from an
     * outside source or false otherwise.
     */
    private boolean isOutsiderAnnouncement(InetAddress senderIP) {
        Enumeration<NetworkInterface> interfaces;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                List<InterfaceAddress> list = interfaces.nextElement().getInterfaceAddresses();
                for (InterfaceAddress address : list) {
                    if (address.getAddress().equals(senderIP)) {
                        return false;
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(HandlerConnectionDetailsResponseDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
