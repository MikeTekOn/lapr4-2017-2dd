package lapr4.green.s1.ipc.n1150532.comm.connection;

import java.net.InetAddress;
import java.util.Objects;

/**
 * An implementation of the interface ConnectionID. It provides a connection id.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class ConnectionIDImpl implements ConnectionID {

    /**
     * The connection address.
     */
    private final InetAddress address;

    /**
     * The connection port number.
     */
    private final int portNumber;

    /**
     * The full constructor of the ConnectionID.
     *
     * @param theAddress The connection address.
     * @param thePortNumber The connection port number.
     */
    public ConnectionIDImpl(InetAddress theAddress, int thePortNumber) {
        address = theAddress;
        portNumber = thePortNumber;
    }

    /**
     * A getter of the address.
     *
     * @return It returns the connection address.
     */
    @Override
    public InetAddress getAddress() {
        return address;
    }

    /**
     * A getter of the port number.
     *
     * @return It returns the connection port number.
     */
    @Override
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * It compares the two instances. The main comparison is the address.
     *
     * @param o The other object to compare.
     * @return It returns "true" if both are equal or "false" otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ConnectionIDImpl)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        ConnectionIDImpl that = (ConnectionIDImpl) o;
        return address.equals(that.address);
    }

    /**
     * The hash code function based on the address.
     *
     * @return It returns the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.address);
        return hash;
    }
    
    /**
     * Returns a descriptive message
     * 
     * @return 
     */
    @Override
    public String toString() {
        return String.format("%s", address.getHostAddress());
    }

}
