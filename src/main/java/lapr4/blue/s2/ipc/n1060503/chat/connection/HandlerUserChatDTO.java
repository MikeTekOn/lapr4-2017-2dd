/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.connection;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;


/**
 *
 * @author Pedro Fernandes
 */
public class HandlerUserChatDTO extends Observable implements CommHandler, Serializable {

    private Object lastReceivedDTO;

    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {

        //ConnectionIDImpl connectionID = new ConnectionIDImpl(((PacketEncapsulatorDTO) dto).getPacket().getAddress(), ((UserChatListDTO) ((PacketEncapsulatorDTO) dto).getDTO()).tcpPort);
        //((UserChatListDTO) ((PacketEncapsulatorDTO) dto).getDTO()).buildConnectionID(connectionID);
        UserChatListDTO ucldto = (UserChatListDTO) ((PacketEncapsulatorDTO) dto).getDTO();
        lastReceivedDTO = ucldto;
        //setChanged();
        //notifyObservers(lastReceivedDTO);
        //CommTCPClientsManager.getManager().requestConnectionTo(connectionID, false);
        Logger.getLogger(HandlerUserChatDTO.class.getName()).log(Level.INFO, dto.getClass().toString());
    }

    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

}
