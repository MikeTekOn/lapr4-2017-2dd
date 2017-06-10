package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import eapli.util.DateTime;

import java.net.InetAddress;
import java.util.Observable;

/**
 * A traffic event worker (Thread), thread responsible to deliver event
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public class TrafficEventWorker extends Observable implements Runnable {

    private final TrafficEvent event;

    /**
     * Builds a traffic event publisher
     *
     * @param trafficType the traffic type
     * @param address     the ip address who sent/received the traffic
     * @param tcpPort     the tcp port
     * @param byteCount   the number of bytes streamed
     * @param isSecured   if it was sent/received from a secure transmission
     */
    public TrafficEventWorker(TrafficType trafficType, InetAddress address, int tcpPort, Long byteCount, boolean isSecured) {

        event = new TrafficEvent(DateTime.now(), trafficType, address, tcpPort, byteCount, isSecured);
    }

    @Override
    public void run() {

        // Notifies observers of new event
        setChanged();
        notifyObservers(event);
    }
}
