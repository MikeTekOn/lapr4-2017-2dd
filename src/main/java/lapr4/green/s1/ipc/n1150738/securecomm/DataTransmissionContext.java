package lapr4.green.s1.ipc.n1150738.securecomm;

import java.io.*;

/**
 * Represents the context of data transportation through a connection.
 *
 * In other words it models what happens between the object writing/reading end
 * and the socket byte writing/reading end of the process of sending/receiving
 * objects.
 */
public interface DataTransmissionContext {

    /**
     * Returns the input stream from where the system shall read the
     * objects to be received through the connection.
     *
     * @param socketInStream the InputStream of the socket
     * @return the ObjectInputStream from where we will read objects received
     */
    public ObjectInputStream inputStream(InputStream socketInStream) throws IOException;

    /**
     * Returns the output stream from where the system should write the
     * objects to be sent through the connection.
     *
     * @param socketOutStream the OutputStream of the socket
     * @return the ObjectOutputStream from where we will read objects received
     */
    public ObjectOutputStream outputStream(OutputStream socketOutStream) throws IOException;

//    public WiretappedStream wiretapInput();
//
//    public WiretappedStream wiretapOutput();

    /**
     * Returns the security description of the transmission context. ex. Unsecured, Secured (Algorithmxxx)
     * @return the security description
     */
    public String securityDesc();

    /**
     * Returns if the data transmission context is secured with some encryption or not
     * @return if is secure or not.
     */
    public boolean isSecure();

}
