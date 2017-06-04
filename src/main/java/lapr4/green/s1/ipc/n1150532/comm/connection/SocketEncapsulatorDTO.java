package lapr4.green.s1.ipc.n1150532.comm.connection;

import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.net.Socket;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;

/**
 * A encapsulation DTO to provide the received DTO along with the Socket who
 * received it.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class SocketEncapsulatorDTO implements DTO, Serializable {

    /**
     * The socket to encapsulate.
     */
    private final Socket socket;

    /**
     * The handler to deal with the received DTO.
     */
    private final CommHandler handler;

    /**
     * The received DTO.
     */
    private final Object receivedDTO;

    /**
     * The full constructor of the encapsulation.
     *
     * @param theSocket The Socket to encapsulate.
     * @param theHandler The handler to deal with the received DTO.
     * @param theDTO The received DTO.
     */
    public SocketEncapsulatorDTO(Socket theSocket, CommHandler theHandler, Object theDTO) {
        socket = theSocket;
        handler = theHandler;
        receivedDTO = theDTO;
    }

    /**
     * A getter to the encapsulated Socket.
     *
     * @return It returns the encapsulate socket.
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * A getter to the DTO handler.
     *
     * @return It returns the handler capable of dealing with the received DTO.
     */
    public CommHandler getHandler() {
        return handler;
    }

    /**
     * A getter to the received DTO.
     *
     * @return It returns the received DTO.
     */
    public Object getDTO() {
        return receivedDTO;
    }

}
