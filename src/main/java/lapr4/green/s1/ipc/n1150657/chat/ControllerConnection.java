/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat;

import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150657.chat.ui.ChatController;

/**
 * @FIXME, quix fix for the ChatPanel and all the instances have the controller
 * since it beggines first than the parameter "connection" that is needed
 * @author Sofia
 */
public class ControllerConnection {

    public static ChatController chat = null;

    /**
     * It gets the static variable.
     *
     * @return It returns the chat controller.
     */
    public static ChatController getChatController() {
        return chat;
    }

    /**
     * It sets the chat controller with the connection needed.
     *
     * @param connection The connection.
     */
    public static void setChatController(ConnectionID connection) {
        chat = new ChatController(connection);
    }

}
