/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1140822.fileShare;

import csheets.CleanSheets;
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

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionIDImpl;
import lapr4.green.s1.ipc.n1150532.comm.connection.HandlerConnectionDetailsResponseDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class HandlerFileNameListDTO extends Observable implements CommHandler, Serializable {

    private Object lastReceivedDTO;

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {

        FileNameListDTO fileNamesListDTO = (FileNameListDTO) ((PacketEncapsulatorDTO) dto).getDTO();
        Logger.getLogger(HandlerFileNameListDTO.class.getName()).log(Level.INFO, CleanSheets.getString("received_object"), dto.getClass().toString());
       
        if (isOutsiderAnnouncement(((PacketEncapsulatorDTO) dto).getPacket().getAddress())) {
            fileNamesListDTO.buildConnectionID(((PacketEncapsulatorDTO)dto).getPacket().getAddress());
             lastReceivedDTO = fileNamesListDTO;
            setChanged();
            notifyObservers(fileNamesListDTO);
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
            Logger.getLogger(HandlerFileNameListDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
