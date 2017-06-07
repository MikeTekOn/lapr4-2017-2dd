package lapr4.green.s1.ipc.n1150738.securecomm;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;

import java.io.*;

/**
 * Created by henri on 6/3/2017.
 */
public class BasicDataTransmissionContext implements DataTransmissionContext {

//    private WiretappedStream inputTap;
//    private WiretappedStream outputTap;

    public BasicDataTransmissionContext(){
//        inputTap = new WiretappedStream();
//        outputTap = new WiretappedStream();
    }

    /**
     * Returns the input stream from where the system shall read the
     * objects to be received through the connection.
     *
     * @param socketInStream the InputStream of the socket
     * @return the ObjectInputStream from where we will read objects received
     */
    @Override
    public ObjectInputStream inputStream(InputStream socketInStream) throws IOException {
        return new ObjectInputStream(socketInStream);
    }

    /**
     * Returns the output stream from where the system should write the
     * objects to be sent through the connection.
     *
     * @param socketOutStream the OutputStream of the socket
     * @return the ObjectOutputStream from where we will read objects received
     */
    @Override
    public ObjectOutputStream outputStream(OutputStream socketOutStream) throws IOException{
        return new ObjectOutputStream(socketOutStream);
    }

//    @Override
//    public WiretappedStream wiretapInput() {
//        return inputTap;
//   }
//
//    @Override
//    public WiretappedStream wiretapOutput() {
//        return outputTap;
//    }


    /**
     * Returns the security description of the transmission context. ex. Unsecured, Secured (Algorithmxxx)
     * @return the security description
     */
    @Override
    public String securityDesc() {
        return "Unsecure";
    }

    /**
     * Returns if the data transmission context is secured with some encryption or not
     * @return if is secure or not.
     */
    @Override
    public boolean isSecure() {
        return false;
    }
}
