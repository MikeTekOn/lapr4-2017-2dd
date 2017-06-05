package lapr4.green.s1.ipc.n1150738.securecomm;

import java.io.Serializable;

/**
 * Created by henri on 6/4/2017.
 */
public class TransmissionContextRequestDTO  implements Serializable {

    private String transmissionContext;

    public TransmissionContextRequestDTO(String transmissionContext){
        this.transmissionContext = transmissionContext;
    }

    public String getTransmissionContext() {
        return transmissionContext;
    }
}
