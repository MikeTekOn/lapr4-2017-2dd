/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat;

import java.io.ObjectOutputStream;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;

/**
 *
 * @author Sofia
 */
public class HandlerResponseMessageDTO implements CommHandler{

    private Object lastReceivedDTO;

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        lastReceivedDTO = dto;
    }

    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

}
