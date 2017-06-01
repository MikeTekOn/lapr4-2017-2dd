package lapr4.green.s1.ipc.n1150532.comm;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A handler for the server to deal with EchoDTO.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class EchoServerHandler implements CommHandler {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. Then, it builds a new EchoDTO whose message is the answer of the original and its answer is null.
     * 
     * @param dto The received DTO. It is suppose to be an EchoDTO.
     * @param outStream The output in which to write the reply.
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        lastReceivedDTO=dto;
        EchoDTO reply=new EchoDTO(((EchoDTO)dto).getAnswer(),null);
        try {
            outStream.writeObject(reply);
        } catch (IOException ex) {
            Logger.getLogger(EchoServerHandler.class.getName()).log(Level.SEVERE, null, ex);
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
