/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.ui;

import csheets.ui.ctrl.BaseAction;
import java.awt.event.ActionEvent;
import java.util.Observer;
import lapr4.blue.s2.ipc.n1060503.chat.connection.CommUDPClient;
import lapr4.blue.s2.ipc.n1060503.chat.connection.HandlerUserChatDTO;
import lapr4.blue.s2.ipc.n1060503.chat.connection.UserChatListDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionDetailsResponseDTO;

/**
 *
 * @author Pedro Fernandes
 */
public class ChatParticipantsAction extends BaseAction{
    
    /**
     * The name of the action.
     */
    private static final String NAME = "Search Users Chat Profiles";

    /**
     * The waiting time without a reply before ending.
     */
    private static final int TIMEOUT = 2000;

    /**
     * The server's port number to connect.
     */
    private final int portNumber;

    /**
     * The observer who will collect the instances found.
     */
    private final Observer table;
    
    public ChatParticipantsAction(Observer theTable, int thePortNumber) {
        table= theTable;
        portNumber = thePortNumber;      
    }
    
    /**
     * It gets the name
     *
     * @return The String name.
     */
    @Override
    protected String getName() {
        return NAME;
    }
    
    @Override
    protected void defineProperties() {
        //TODO
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserChatListDTO request = new UserChatListDTO();
        CommUDPClient worker = new CommUDPClient( request, portNumber, TIMEOUT);
        HandlerUserChatDTO handler = new HandlerUserChatDTO();
        handler.addObserver(table);
        worker.addHandler(ConnectionDetailsResponseDTO.class, handler);
        worker.start();
    }
    
}
