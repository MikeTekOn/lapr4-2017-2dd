package lapr4.green.s1.ipc.n1150532.startSharing;

import lapr4.black.s1.ipc.n2345678.comm.sharecells.RequestSharedCellsDTO;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.ResponseSharedCellsDTO;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionIDImpl;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An handler to deal with the sharing cells request.
 * @author Guilherme Ferreira 1150623 Added Share Name
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
     * @param dto       The received DTO. It is encapsulated within a
     *                  SocketEncapsulatorDTO.
     * @param outStream The output stream in which to write the reply.
     */
    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        SocketEncapsulatorDTO receivedDTO = (SocketEncapsulatorDTO) dto;
        RequestSharedCellsDTO request = (RequestSharedCellsDTO) receivedDTO.getDTO();
        lastReceivedDTO = request;

        Socket socket = receivedDTO.getSocket();
        ConnectionID connection = new ConnectionIDImpl(socket.getInetAddress(), socket.getPort());
        String shareName = request.getShareName();
        setChanged();
        notifyObservers(new SharedCellsEvent(request.getSpreadsheetName(), request.getCells(), connection, shareName));
        ResponseSharedCellsDTO reply = new ResponseSharedCellsDTO(request.getSpreadsheetName(), request.getAddress1(), request.getAddress2(), ResponseSharedCellsDTO.SharedCellsStatusResponse.OK);
        try {
            outStream.write(reply);
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
