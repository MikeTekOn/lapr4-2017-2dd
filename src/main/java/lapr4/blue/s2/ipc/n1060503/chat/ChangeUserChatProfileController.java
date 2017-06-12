/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat;

import java.io.IOException;
import javax.swing.ImageIcon;
import lapr4.blue.s2.ipc.n1060503.chat.profile.StatusChatProfile;
import lapr4.blue.s2.ipc.n1060503.chat.profile.UserChatProfile;

/**
 *
 * @author Pedro Fernandes
 */
public class ChangeUserChatProfileController {
    
    UserChatProfile ucp;
    
    public ChangeUserChatProfileController(UserChatProfile ucp) throws IOException{
        this.ucp =ucp;
    }
    
    public String getUsername(){
        return ucp.getUsername();
    }
    
    public String getNickname(){
        return ucp.getNickname();
    }
    
    public String getStatus(){
        return ucp.getStatus().toString();
    }
    
    public ImageIcon getIcon(){
        return ucp.getImage();
    }
    
    public boolean changeInfo(String nickname, 
            StatusChatProfile status) throws IOException{
        
        ucp.setNickname(nickname);
        ucp.setStatus(status);
        
        //TODO save repository
        return ucp != null;
    }
    
    public void changeIcon(ImageIcon icon) throws IOException{
        ucp.setIcon(icon);
    }
    
    public boolean persist(){
        //TODO
        return true;
    }
    
}
