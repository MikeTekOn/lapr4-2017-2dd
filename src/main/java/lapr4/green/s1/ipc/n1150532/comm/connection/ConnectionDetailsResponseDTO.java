package lapr4.green.s1.ipc.n1150532.comm.connection;

import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.net.InetAddress;

/**
 * A DTO to respond to a request for details on an UDP server about its TCP
 * connection options.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class ConnectionDetailsResponseDTO implements DTO, Serializable {

    /**
     * The TCP server's IP address in which to send the connection request.
     */
    private final InetAddress serverIP;

    /**
     * The TCP server's port number in which to send the connection request.
     */
    private final int portNumber;

    /**
     * The full reply constructor.
     *
     * @param theServerIP The TCP server's IP address.
     * @param thePortNumber The TCP server's port number.
     */
    public ConnectionDetailsResponseDTO(InetAddress theServerIP, int thePortNumber) {
        serverIP = theServerIP;
        portNumber = thePortNumber;
    }

    /**
     * A getter to the TCP server's IP address.
     *
     * @return It returns the TCP server's address in which to send the TCP
     * connection request.
     */
    public InetAddress getServerIP() {
        return serverIP;
    }

    /**
     * A getter to the TCP server's port number.
     *
     * @return It returns the TCP server's port number in which to send the TCP
     * connection request.
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * It provides a ConnectionID from the data.
     *
     * @return It returns a new ConnectionID with this data.
     */
    public ConnectionID getConnectionID() {
        return new ConnectionIDImpl(serverIP, portNumber);
    }

}
