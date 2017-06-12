package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

import java.io.ObjectOutputStream;
import java.util.Observable;

/**
 * An handler to deal with cell's content changes.
 *
 * @author Ivo Ferro [1151159]
 */
public class HandlerCellContentDTO extends Observable implements CommHandler {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        SocketEncapsulatorDTO receivedDTO = (SocketEncapsulatorDTO) dto;
        CellContentDTO cellContentDTO = (CellContentDTO) receivedDTO.getDTO();
        lastReceivedDTO = cellContentDTO;
        setChanged();
        notifyObservers(cellContentDTO);

    }

    @Override
    public Object getLastReceivedDTO() {
        return null;
    }
}
