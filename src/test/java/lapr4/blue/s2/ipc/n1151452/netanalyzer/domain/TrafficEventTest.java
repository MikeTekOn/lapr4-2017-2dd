package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public class TrafficEventTest {

    private InetAddress ip;
    private int tcpPort;
    private Long count;

    @Before
    public void setUp() {

        try {
            ip = InetAddress.getByName("192.168.1.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        tcpPort = 8888;
        count = 1L;
    }

    /**
     * Tests if type is valid
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTrafficEventHasType() {
        new TrafficEvent(null, null, ip, tcpPort, count, true);
    }

    /**
     * Tests if ip is not null
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTrafficEventHasNonNullIP() {
        new TrafficEvent(null, TrafficType.INCOMING, null, tcpPort, count, true);
    }

    /**
     * Test if port is valid
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTrafficEventHasValidPort() {
        new TrafficEvent(null, TrafficType.INCOMING, ip, 1, count, true);
    }

    /**
     * Test if byte count is non negative
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTrafficEventHasNonNegativeByteCount() {
        new TrafficEvent(null, TrafficType.INCOMING, ip, tcpPort, -1L, true);
    }
}