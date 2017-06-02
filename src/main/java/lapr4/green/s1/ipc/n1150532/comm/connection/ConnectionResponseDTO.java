package lapr4.green.s1.ipc.n1150532.comm.connection;

import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.net.InetAddress;

/**
 * A DTO to respond to a TCP connection request.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class ConnectionResponseDTO implements DTO, Serializable {

    /**
     * Indicates if the request was accepted or not.
     */
    private final boolean accepted;

    /**
     * The replier IP address.
     */
    private final InetAddress serverIPAddress;

    /**
     * The replier port number,
     */
    private final int serverPortNumber;

    /**
     * The full constructor of the request.
     *
     * @param accept Indicates if the request was accepted.
     * @param theServerIPAddress The server's address.
     * @param theServerPortNumber The server's port number.
     */
    public ConnectionResponseDTO(boolean accept, InetAddress theServerIPAddress, int theServerPortNumber) {
        accepted = accept;
        serverIPAddress = theServerIPAddress;
        serverPortNumber = theServerPortNumber;
    }

    /**
     * A getter to the request acceptance.
     *
     * @return It returns the boolean to indicate if the request was accepted or
     * not.
     */
    public boolean wasAccepted() {
        return accepted;
    }

    /**
     * A getter to the TCP server's IP address.
     *
     * @return It returns the TCP server's address in which to send the TCP
     * connection request.
     */
    public InetAddress getServerIPAddress() {
        return serverIPAddress;
    }

    /**
     * A getter to the TCP server's IP port number.
     *
     * @return It returns the TCP server's port number in which to send the TCP
     * connection request.
     */
    public int getServerPortNumber() {
        return serverPortNumber;
    }

    /**
     * It provides a ConnectionID from the data.
     *
     * @return It returns a new ConnectionID with this data.
     */
    public ConnectionID getConnectionID() {
        return new ConnectionIDImpl(serverIPAddress, serverPortNumber);
    }

}
