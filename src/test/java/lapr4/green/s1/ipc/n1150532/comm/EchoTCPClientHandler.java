package lapr4.green.s1.ipc.n1150532.comm;

import java.io.ObjectOutputStream;

/**
 * A handler for the client TCP to deal with EchoDTO.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class EchoTCPClientHandler implements CommHandler {

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
        lastReceivedDTO = (EchoDTO) dto;
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
