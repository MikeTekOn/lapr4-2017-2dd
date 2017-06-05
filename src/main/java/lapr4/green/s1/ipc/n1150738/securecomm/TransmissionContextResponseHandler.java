package lapr4.green.s1.ipc.n1150738.securecomm;

import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150738.securecomm.trash.IllegalDataTransmissionContextEvent;
import lapr4.green.s1.ipc.n1150738.securecomm.trash.SwitchDataTransmissionContextEvent;

import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by henri on 6/4/2017.
 */
public class TransmissionContextResponseHandler extends Observable implements CommHandler {


    /**
     * @param dto       the object received by the socket
     * @param outStream the output stream to be used to send a reply/response to the socket
     */
    @Override
    public void handleDTO(Object dto, ObjectOutputStream outStream) {
        SocketEncapsulatorDTO receivedDTO = (SocketEncapsulatorDTO) dto;
        TransmissionContextResponseDTO response = (TransmissionContextResponseDTO) receivedDTO.getDTO();
        Object event;
        if(response.isSuccess()){
            try {
                DataTransmissionContext ctx = (DataTransmissionContext) Class.forName(response.getTransmissionContext()).newInstance();
                event = new SwitchDataTransmissionContextEvent(receivedDTO.getSocket(), ctx);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exCtx) {
                Logger.getLogger(TransmissionContextRequestHandler.class.getName()).log(Level.FINEST, "Cannot load context", exCtx);
                event = new IllegalDataTransmissionContextEvent(receivedDTO.getSocket());
            }

        } else {
            event = new IllegalDataTransmissionContextEvent(receivedDTO.getSocket());
        }
        setChanged();
        notifyObservers(event);
    }

    @Override
    public Object getLastReceivedDTO() {
        return null;
    }
}
