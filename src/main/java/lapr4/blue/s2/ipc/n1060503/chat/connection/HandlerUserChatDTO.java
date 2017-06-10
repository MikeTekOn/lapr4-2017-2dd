/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.connection;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionDetailsResponseDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionIDImpl;
import lapr4.green.s1.ipc.n1150532.comm.connection.HandlerConnectionDetailsResponseDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 *
 * @author Pedro Fernandes
 */
public class HandlerUserChatDTO extends Observable implements CommHandler, Serializable {

    private Object lastReceivedDTO;

    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {

        ConnectionIDImpl connectionID = new ConnectionIDImpl(((PacketEncapsulatorDTO) dto).getPacket().getAddress(), ((UserChatDTO) ((PacketEncapsulatorDTO) dto).getDTO()).tcpPort);
        ((UserChatDTO) ((PacketEncapsulatorDTO) dto).getDTO()).buildConnectionID(connectionID);
        UserChatDTO ucldto = (UserChatDTO) ((PacketEncapsulatorDTO) dto).getDTO();
        lastReceivedDTO = ucldto;
        InetAddress serverIP =  connectionID.getAddress();
        int portNumber =  connectionID.getPortNumber();               
        if (isOutsiderAnnouncement(serverIP)) {
            setChanged();
            ConnectionID connection = new ConnectionIDImpl(serverIP, portNumber);
            notifyObservers(connection);
            Logger.getLogger(HandlerUserChatDTO.class.getName()).log(Level.INFO, dto.getClass().toString());
        }
    }

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
