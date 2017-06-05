/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat;

import java.io.Serializable;

/**
 * A DTO to respond to a TCP connection request.
 *
 * @FIXME class incomplete. It need the serverIPAddress and port for the replier
 * @author Sofia
 */
public class ResponseMessageDTO implements Serializable {

    private String message;

    public ResponseMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
