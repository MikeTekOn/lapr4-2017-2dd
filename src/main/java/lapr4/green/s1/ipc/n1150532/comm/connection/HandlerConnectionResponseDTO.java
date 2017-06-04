package lapr4.green.s1.ipc.n1150532.comm.connection;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;

/**
 * An handler to deal with TCP connection response. It is supposed to be used by
 * the TCP client.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class HandlerConnectionResponseDTO extends Observable implements CommHandler, Serializable {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. If the connection
     * was accepted, it published the ConnectionResponseDTO read.
     *
     * @param dto The received DTO. It is suppose to be an EchoDTO.
     * @param outStream The output in which to write the object.
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        SocketEncapsulatorDTO encapsulator = (SocketEncapsulatorDTO) dto;
        lastReceivedDTO = encapsulator.getDTO();
        ConnectionResponseDTO reply = (ConnectionResponseDTO) encapsulator.getDTO();
        if (reply.wasAccepted()) {
            setChanged();
            notifyObservers(reply);
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
