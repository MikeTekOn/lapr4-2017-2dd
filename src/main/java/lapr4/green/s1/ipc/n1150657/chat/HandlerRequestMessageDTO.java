/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

/**
 * An handler to deal with the sharing cells request.
 *
 * @author Sofia
 */
public class HandlerRequestMessageDTO extends Observable implements CommHandler {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It receives a RequestMessageDTO with the cells shared. It launches an
     * event with them. It replies with a ResponseMessageDTO.
     *
     * @param dto The received DTO. It is encapsulated within a
     * SocketEncapsulatorDTO.
     * @param outStream The output stream in which to write the reply.
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        SocketEncapsulatorDTO receivedDTO = (SocketEncapsulatorDTO) dto;
        RequestMessageDTO request = (RequestMessageDTO) receivedDTO.getDTO();
        lastReceivedDTO = request;
        setChanged();
        notifyObservers(new MessageEvent(request.sendMessage(), receivedDTO.getSocket()));
        ResponseMessageDTO reply = new ResponseMessageDTO(request.sendMessage());
        try {
            outStream.writeObject(reply);
        } catch (IOException ex) {
            Logger.getLogger(RequestMessageDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * It gets the lastest dto received.
     *
     * @return It returns the latest dto.
     */
    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

}
