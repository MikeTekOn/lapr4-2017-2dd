package lapr4.green.s1.ipc.n1150532.startSharing;

import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

import java.io.ObjectOutputStream;

/**
 * An handler to deal with the sharing cells response.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class HandlerResponseSharedCellsDTO implements CommHandler {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stores the response of the shared cells request.
     *
     * @param dto       The response received.
     * @param outStream Not used.
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        SocketEncapsulatorDTO encapsulator = (SocketEncapsulatorDTO) dto;
        lastReceivedDTO = encapsulator.getDTO();
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
