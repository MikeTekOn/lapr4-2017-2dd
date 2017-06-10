/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.connection;

import eapli.framework.dto.DTO;
import java.io.IOException;
import java.io.Serializable;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.blue.s2.ipc.n1060503.chat.profile.UserChatProfile;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPServer;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Pedro Fernandes
 */
public class UserChatDTO extends Observable implements  DTO, Serializable {
    
    UserChatProfile ucp;
    int tcpPort;
    private ConnectionID connID;
    
    public UserChatDTO(){
        try {
            ucp = new UserChatProfile();
            tcpPort = CommTCPServer.getServer().provideConnectionPort();
        } catch (IOException ex) {
            Logger.getLogger(UserChatDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getUserChatProfileNickname(){
        return ucp.getNickname();
    }
    
    public int getTcoPort()
    {
        return tcpPort;
    }
    
    public void buildConnectionID(ConnectionID connection)
    {
        this.connID = connection;
    }
    
}
