package lapr4.blue.s2.ipc.n1151031.searchnetwork;

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
import lapr4.green.s1.ipc.n1150532.comm.connection.HandlerConnectionDetailsResponseDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 * Class that handles the response DTO.
 * 
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class HandlerSearchWorkbookResponseDTO extends Observable implements CommHandler, Serializable {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * Stores the received DTO as the last received DTO. If the workbook was
     * found it publishes the results.
     *
     * @param dto The received DTO. It is suppose to be an
     * SearchWorkbookResponseDTO.
     * @param outStream The output stream to write the reply.
     */
    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        lastReceivedDTO = dto;
        PacketEncapsulatorDTO reply = (PacketEncapsulatorDTO) dto;
        InetAddress serverIP = reply.getPacket().getAddress();
        List<SearchResults> results = ((SearchWorkbookResponseDTO) reply.getDTO()).getSearchResultsList();

        for (SearchResults result : results) {
            if (isOutsiderAnnouncement(serverIP)) {
                setChanged();
                SearchResults tableEntry = new SearchResults(result.getWorkbookName(), result.getSpreadsheetList(), serverIP);
                notifyObservers(tableEntry);
            }
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
