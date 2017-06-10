package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.HandlerConnectionDetailsRequestDTO;

/**
 * @FIXME
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class HandlerSearchWorkbookRequestDTO implements CommHandler, Serializable {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. It sends back a
     * SearchWorkbookResponseDTO.
     *
     * @param dto The received DTO. It is suppose to be an
     * SearchWorkbookRequestDTO.
     * @param outStream The output stream to write the reply.
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        lastReceivedDTO = dto;

        //@TODO
        
        SearchWorkbookResponseDTO reply = new SearchWorkbookResponseDTO(null);
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
