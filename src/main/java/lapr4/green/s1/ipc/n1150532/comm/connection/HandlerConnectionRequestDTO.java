package lapr4.green.s1.ipc.n1150532.comm.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;

/**
 * An handler to deal with TCP connection request. It is supposed to be used by
 * the TCP server.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class HandlerConnectionRequestDTO extends Observable implements CommHandler, Serializable {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. It publishes a
     * NewConnectionMadeEvent. It replies with a ConnectionResponseDTO.
     *
     * @param dto The received DTO. It is suppose to be an EchoDTO.
     * @param outStream The output in which to write the object.
     */
    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        lastReceivedDTO = dto;

        SocketEncapsulatorDTO encapsulator = (SocketEncapsulatorDTO) dto;
        ConnectionRequestDTO request = (ConnectionRequestDTO) encapsulator.getDTO();

        InetAddress requesterIP = encapsulator.getSocket().getInetAddress();
        int requesterPortNumber = request.getRequesterServerPortNumber();
        setChanged();
        notifyObservers(new NewConnectionMadeEvent(requesterIP, requesterPortNumber, request.isSecure()));

        ConnectionResponseDTO reply = new ConnectionResponseDTO(true, request.getServerIPAddress(), request.getServerPortNumber());
        try {
            outStream.writeObject(reply);
        } catch (IOException |ClassNotFoundException ex) {
            Logger.getLogger(HandlerConnectionRequestDTO.class.getName()).log(Level.SEVERE, null, ex);
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

}
