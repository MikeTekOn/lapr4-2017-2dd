package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import eapli.framework.domain.DomainEventBase;
import eapli.util.DateTime;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.util.CustomUtil;

import java.net.InetAddress;
import java.util.Calendar;

/**
 * Represents an incoming or outgoing traffic event.
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public class TrafficEvent extends DomainEventBase {

    private final TrafficType type;
    private final InetAddress ip;
    private final int port;
    private final Long count;
    private final boolean secured;


    /**
     * Constructs a traffic event
     *
     * @param occurredAt  the date the event occurred
     * @param trafficType the traffic type
     * @param address     the ip address who sent/received the traffic
     * @param tcpPort     the tcp port
     * @param byteCount   the number of bytes streamed
     * @param isSecured   if it was sent/received from a secure transmission
     */
    public TrafficEvent(Calendar occurredAt, TrafficType trafficType, InetAddress address, int tcpPort, Long byteCount, boolean isSecured) {

        super((occurredAt == null) ? DateTime.now() : occurredAt);

        // Validate parameters
        if (trafficType == null) throw new IllegalStateException("Null type.");
        if (address == null) throw new IllegalStateException("Null IP address");
        if (!CustomUtil.validatePort(tcpPort)) throw new IllegalStateException("Illegal TCP Port.");
        if (!CustomUtil.isNullOrNonNegative(byteCount)) throw new IllegalStateException("Illegal byte count.");

        type = trafficType;
        ip = address;
        port = tcpPort;
        count = byteCount;
        secured = isSecured;
    }

    /**
     * Retrieves the traffic type
     *
     * @return the traffic type
     */
    public TrafficType type() {
        return type;
    }

    /**
     * Retrieves the sender/receptor ip address
     *
     * @return the sender/receptor ip address
     */
    public InetAddress ipAddress() {
        return ip;
    }

    /**
     * Retrieves the TCP port
     *
     * @return the TCP port
     */
    public int port() {
        return port;
    }

    /**
     * Retrieves the number of sent/received bytes
     *
     * @return the number of sent/received bytes
     */
    public Long count() {
        return count;
    }

    /**
     * Checks if it was sent/received with a secure transmission
     *
     * @return true if secure, false otherwise
     */
    public boolean isSecured() {
        return secured;
    }
}
