package lapr4.green.s1.ipc.n1150738.securecomm.trash;

import eapli.framework.domain.DomainEvent;
import lapr4.green.s1.ipc.n1150738.securecomm.DataTransmissionContext;

import java.net.Socket;

/**
 * Created by henri on 6/4/2017.
 */
public class SwitchDataTransmissionContextEvent {

    private Socket socket;
    private DataTransmissionContext transmissionContext;

    public SwitchDataTransmissionContextEvent(Socket socket, DataTransmissionContext transmissionContext) {
        this.socket = socket;
        this.transmissionContext = transmissionContext;
    }

    public Socket getSocket() {
        return socket;
    }

    public DataTransmissionContext getTransmissionContext() {
        return transmissionContext;
    }
}
