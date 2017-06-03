package lapr4.green.s1.ipc.n1150532.comm;

import java.io.ObjectOutputStream;

/**
 * The interface of an handler. It has the required functionalities interprete
 * the received DTO and respond to it.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public interface CommHandler {

    /**
     * It handles the received DTO.
     *
     * @param dto The received DTO to handle.
     * @param outStream The output stream in which to send the replies.
     */
    public void handleDTO(Object dto, ObjectOutputStream outStream);

    /**
     * It gets the last DTO received.
     *
     * @return It returns the last received DTO.
     */
    public Object getLastReceivedDTO();

}
