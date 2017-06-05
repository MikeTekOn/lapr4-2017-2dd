/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat;

import java.io.Serializable;

/**
 *
 * @author Sofia
 */
public class RequestMessageDTO implements Serializable{
    
    String message;
    
    public RequestMessageDTO(String message){
        this.message = message;
    }
    
    public String sendMessage(){
        return this.message;
    }
    
}
