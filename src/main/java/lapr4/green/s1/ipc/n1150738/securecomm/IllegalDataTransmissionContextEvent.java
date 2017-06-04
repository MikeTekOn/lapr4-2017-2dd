package lapr4.green.s1.ipc.n1150738.securecomm;

import java.net.Socket;

/**
 * Created by henri on 6/4/2017.
 */
public class IllegalDataTransmissionContextEvent {
    private Socket socket;

    public IllegalDataTransmissionContextEvent(Socket socket) {

        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
}
