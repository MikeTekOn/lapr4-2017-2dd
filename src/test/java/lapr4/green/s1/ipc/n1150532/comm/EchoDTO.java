package lapr4.green.s1.ipc.n1150532.comm;

import eapli.framework.dto.DTO;
import java.io.Serializable;

/**
 * A data transmission object for Echo messages.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class EchoDTO implements DTO, Serializable {

    /**
     * The message of the echo.
     */
    private final String message;

    /**
     * The message of the expected answer.
     */
    private final String answer;

    /**
     * The full constructor of the DTO. No validations exists. It may be null.
     * 
     * @param theMessage The message of the echo.
     * @param theAnswer The message of the expected answer.
     */
    public EchoDTO(String theMessage, String theAnswer) {
        message = theMessage;
        answer = theAnswer;
    }

    /**
     * A getter method of the message.
     * 
     * @return It returns the message.
     */
    public String getMessage(){
        return message;
    }

    /**
     * A getter method of the answer.
     * 
     * @return It returns the answer.
     */
    public String getAnswer(){
        return answer;
    }

}
