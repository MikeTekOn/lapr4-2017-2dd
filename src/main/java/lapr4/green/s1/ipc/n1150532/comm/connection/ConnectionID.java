package lapr4.green.s1.ipc.n1150532.comm.connection;

import java.net.InetAddress;

/**
 * An interface to provide a connection id.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public interface ConnectionID {

    /**
     * The connection address.
     *
     * @return It returns the matching InetAddress.
     */
    InetAddress getAddress();

    /**
     * The connection port number.
     *
     * @return It returns the matching port number.
     */
    int getPortNumber();

}
