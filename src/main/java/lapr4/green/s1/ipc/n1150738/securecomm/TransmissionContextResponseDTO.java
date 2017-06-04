package lapr4.green.s1.ipc.n1150738.securecomm;

import java.io.Serializable;

/**
 * Created by henri on 6/4/2017.
 */
public class TransmissionContextResponseDTO implements Serializable{

    private boolean success;
    private String transmissionContext;

    protected TransmissionContextResponseDTO(){

    }

    public TransmissionContextResponseDTO(boolean success, String transmissionContext){
        this.success = success;
        this.transmissionContext = transmissionContext;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTransmissionContext() {
        return transmissionContext;
    }
}

