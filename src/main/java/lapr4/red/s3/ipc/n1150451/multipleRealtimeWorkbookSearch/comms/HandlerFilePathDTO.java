/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150451.multipleRealtimeWorkbookSearch.comms;

import java.io.Serializable;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

/**
 *
 * @author Diogo Santos
 */
public class HandlerFilePathDTO implements CommHandler, Serializable {

    /**
     * The last received DTO.
     */
    private Object lastReceivedDTO;

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        FilePathDTO pathDTO = (FilePathDTO) ((PacketEncapsulatorDTO) dto).getDTO();
        lastReceivedDTO = pathDTO;
    }

    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

}
