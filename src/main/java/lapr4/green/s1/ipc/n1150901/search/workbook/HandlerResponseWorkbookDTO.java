/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.workbook;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class HandlerResponseWorkbookDTO extends Observable implements CommHandler, Serializable {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. If the connection
     * was accepted, it published the ResponseWorkbookDTO read.
     *
     * @param dto The received DTO. It is suppose to be an EchoDTO.
     * @param outStream The output in which to write the object.
     */
    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        lastReceivedDTO = dto;
        ResponseWorkbookDTO reply = (ResponseWorkbookDTO) dto;

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
