package lapr4.green.s1.ipc.n1150532.comm.connection;

import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.net.InetAddress;

/**
 * A DTO to request a TCP connection to a TCP server.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class ConnectionRequestDTO implements DTO, Serializable {

    /**
     * The TCP server's port number of the requester. This value is used so the
     * replier can also perform a request to my server as a client.
     */
    private final int myServerPortNumber;

    /**
     * The server's address.
     */
    private final InetAddress serverIPAddress;

    /**
     * The server's port number.
     */
    private final int serverPortNumber;

    /**
     * The full constructor of the request.
     *
     * @param myServerPortNumber The requester's TCP port number.
     * @param theServerIPAddress The server's address.
     * @param theServerPortNumber The server's port number.
     */
    public ConnectionRequestDTO(int myServerPortNumber, InetAddress theServerIPAddress, int theServerPortNumber) {
        this.myServerPortNumber = myServerPortNumber;
        serverIPAddress = theServerIPAddress;
        serverPortNumber = theServerPortNumber;
    }

    /**
     * A getter to the requester TCP server's port number.
     *
     * @return It returns the requester TCP server's port number in which to
     * send the TCP connection request.
     */
    public int getRequesterServerPortNumber() {
        return myServerPortNumber;
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

}
