package lapr4.green.s1.ipc.n1150532.startSharing;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.RequestSharedCellsDTO;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.ResponseSharedCellsDTO;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

/**
 * An handler to deal with the sharing cells request.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class HandlerRequestSharedCellsDTO extends Observable implements CommHandler {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It receives a RequestSharedCellsDTO with the cells shared. It launches an
     * event with them. It replies with a ResponseSharedCellsDTO.
     *
     * @param dto The received DTO. It is encapsulated within a
     * SocketEncapsulatorDTO.
     * @param outStream The output stream in which to write the reply.
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        SocketEncapsulatorDTO receivedDTO = (SocketEncapsulatorDTO) dto;
        RequestSharedCellsDTO request = (RequestSharedCellsDTO) receivedDTO.getDTO();
        lastReceivedDTO = request;
        setChanged();
        notifyObservers(new SharedCellsEvent(request.getSpreadsheetName(), request.getCells()));
        ResponseSharedCellsDTO reply = new ResponseSharedCellsDTO(request.getSpreadsheetName(), request.getAddress1(), request.getAddress2(), ResponseSharedCellsDTO.SharedCellsStatusResponse.OK);
        try {
            outStream.writeObject(reply);
        } catch (IOException ex) {
            Logger.getLogger(HandlerRequestSharedCellsDTO.class.getName()).log(Level.SEVERE, null, ex);
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
