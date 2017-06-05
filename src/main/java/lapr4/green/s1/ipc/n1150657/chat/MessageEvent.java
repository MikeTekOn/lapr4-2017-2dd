/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat;

import java.net.Socket;

/**
 * An event that contains the message received and the socket.
 *
 * @author Sofia
 */
public class MessageEvent {

    /**
     * The message.
     */
    private final String message;

    /**
     * The socket.
     */
    private final Socket socket;

    /**
     * The constructor.
     *
     * @param message The message.
     * @param socket The socket.
     */
    public MessageEvent(String message, Socket socket) {
        this.message = message;
        this.socket = socket;
    }

    /**
     * It gets the message.
     *
     * @return String with message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * It gets the socket.
     *
     * @return Socket.
     */
    public Socket getSocket() {
        return socket;
    }

}
