package lapr4.green.s1.ipc.n1150532.comm;

import java.io.ObjectOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 * A handler for the client UDP to deal with EchoDTO.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class EchoUDPClientHandler implements CommHandler {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO.
     * 
     * @param dto The received DTO. It is suppose to be an EchoDTO.
     * @param outStream Not used.
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        lastReceivedDTO=((PacketEncapsulatorDTO)dto).getDTO();
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
