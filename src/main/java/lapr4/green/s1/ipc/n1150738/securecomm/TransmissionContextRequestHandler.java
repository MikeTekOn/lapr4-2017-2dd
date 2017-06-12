package lapr4.green.s1.ipc.n1150738.securecomm;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150738.securecomm.trash.IllegalDataTransmissionContextEvent;
import lapr4.green.s1.ipc.n1150738.securecomm.trash.SwitchDataTransmissionContextEvent;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by henri on 6/4/2017.
 */
public class TransmissionContextRequestHandler extends Observable implements CommHandler {

    private TransmissionContextRequestDTO last;

    /**
     * @param dto       the object received by the socket
     * @param outStream the output stream to be used to send a reply/response to the socket
     */
    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        SocketEncapsulatorDTO receivedDTO = (SocketEncapsulatorDTO) dto;
        TransmissionContextRequestDTO request = (TransmissionContextRequestDTO) receivedDTO.getDTO();
        TransmissionContextResponseDTO response;
        Object event;
        try {
            DataTransmissionContext ctx = (DataTransmissionContext) Class.forName(request.getTransmissionContext()).newInstance();
            response = new TransmissionContextResponseDTO(true, request.getTransmissionContext());
            event = new SwitchDataTransmissionContextEvent(receivedDTO.getSocket(), ctx);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exCtx) {
            Logger.getLogger(TransmissionContextRequestHandler.class.getName()).log(Level.FINEST, "Cannot load context", exCtx);
            response = new TransmissionContextResponseDTO(false, request.getTransmissionContext());
            event = new IllegalDataTransmissionContextEvent(receivedDTO.getSocket());
        }

        try {
            outStream.write(response);
            setChanged();
            notifyObservers(event);
        } catch (IOException ex) {
            Logger.getLogger(TransmissionContextRequestHandler.class.getName()).log(Level.FINEST, "Cannot send response", ex);
        }
    }

    @Override
    public Object getLastReceivedDTO() {
        return last;
    }
}
