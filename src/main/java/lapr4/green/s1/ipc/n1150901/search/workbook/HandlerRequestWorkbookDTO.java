/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.workbook;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

/**
 * @author Miguel Silva - 1150901
 */
public class HandlerRequestWorkbookDTO extends Observable implements CommHandler, Serializable {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. It sends back a
     * ResponseWorkbookDTO.
     *
     * @param dto       The received DTO. It is suppose to be an RequestWorkbookDTO.
     * @param outStream The output stream to write the reply.
     */
    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        SocketEncapsulatorDTO receivedDTO = (SocketEncapsulatorDTO) dto;
        RequestWorkbookDTO request = (RequestWorkbookDTO) receivedDTO.getDTO();

        lastReceivedDTO = request;
        notifyObservers(new SearchWorkbookEvent(request.getWorkbookName()));
        ResponseWorkbookDTO reply = new ResponseWorkbookDTO(request.getWorkbookName());

        try {
            outStream.writeObject(reply);
            outStream.flush();
        } catch (IOException|ClassNotFoundException ex) {
            Logger.getLogger(HandlerRequestWorkbookDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * A getter of the last received DTO.
     *
     * @return It returns the last received DTO.
     */
    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

}
