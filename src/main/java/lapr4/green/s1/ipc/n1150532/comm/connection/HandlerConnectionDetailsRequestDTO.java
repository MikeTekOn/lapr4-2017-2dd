package lapr4.green.s1.ipc.n1150532.comm.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPServer;

/**
 * An handler to deal with UDP connection details request. It is supposed to be
 * used by the UDP server.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class HandlerConnectionDetailsRequestDTO implements CommHandler, Serializable {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. It sends back a
     * ConnectionDetailsResponseDTO.
     *
     * @param dto The received DTO. It is suppose to be an
     * ConnectionDetailsRequestDTO.
     * @param outStream The output stream to write the reply.
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        lastReceivedDTO = dto;
        ConnectionDetailsResponseDTO reply = new ConnectionDetailsResponseDTO(null, CommTCPServer.getServer().provideConnectionPort());
        try {
            outStream.writeObject(reply);
            outStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(HandlerConnectionDetailsRequestDTO.class.getName()).log(Level.SEVERE, null, ex);
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
