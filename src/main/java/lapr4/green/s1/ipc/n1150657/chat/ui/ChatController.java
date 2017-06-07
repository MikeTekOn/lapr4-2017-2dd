/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat.ui;

import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 * It represents the controller for the chat.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ChatController {

    private ConnectionID connection;

    /**
     * The constructor for the ChatController.
     *
     * @param connection
     */
    public ChatController(ConnectionID connection) {
        this.connection = connection;
    }

    /**
     * It sets the connection for the new one.
     *
     * @param connection The connection.
     */
    public void setConnection(ConnectionID connection) {
        this.connection = connection;
    }

    /**
     * It gets the connection.
     *
     * @return It returns the connection.
     */
    public ConnectionID getConnection() {
        return connection;
    }

    /**
     * It sends the message.
     *
     * @param message The message to be sent.
     */
    public void sendMessage(String message) {
        CommTCPClientsManager.getManager().sendMessageWith(connection, message);
    }

}
