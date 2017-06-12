package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.TransmissionStrategy;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficCounter;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficLogger;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.util.CustomUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Represents a traffic input stream (Decorator pattern)
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public class TrafficInputStream extends ObjectInputStream {

    private boolean isSecured;

    private final ExecutorService publisherPool;
    private final Set<Observer> subscribers;
    private final InetAddress address;
    private final int port;

    /**
     * Builds a traffic input stream
     *
     * @param in        the decorated input stream
     * @param ipAddress the ip address that is connected
     * @param tcpPort   the tcp port that is connected
     * @throws IOException I/O exception
     */
    public TrafficInputStream(InputStream in, InetAddress ipAddress, int tcpPort, TransmissionStrategy strategy) throws IOException {
        super(in);
        this.

//        stream = (strategy == null) ? new OpenTransmission().stream(in) : strategy.stream(in);

        isSecured = strategy != null && strategy.isSecured();

        address = ipAddress;
        port = tcpPort;
        publisherPool = Executors.newCachedThreadPool(new TrafficPublisherFactory());
        subscribers = new HashSet<>();

        subscribers.add(TrafficLogger.getInstance());
        subscribers.add(TrafficCounter.getInstance());
    }

    /**
     * Reads an object from the stream & publishes a traffic event
     *
     * @return an object from the stream
     * @throws IOException            I/O Exception
     * @throws ClassNotFoundException Class not found
     */
    public Object readObjectOvveride() throws IOException, ClassNotFoundException {

        Object obj = super.readObject();

        publishTraffic(CustomUtil.length(obj), isSecured);

        return obj;
    }

    @Override
    public int read() throws IOException {
        int integer = super.read();

        publishTraffic(CustomUtil.length(integer), isSecured);

        return integer;
    }

    /**
     * Adds a subscriber to the event publisher
     *
     * @param subscriber the subscriber to add
     * @return true if added, false otherwise
     */
    public boolean addSubscriber(Observer subscriber) {

        return subscribers.add(subscriber);
    }

    /**
     * Removes a subscriber from the event publisher
     *
     * @param subscriber the subscriber to remove
     * @return true if removed, false otherwise
     */
    public boolean removeSubscriber(Observer subscriber) {

        return subscribers.remove(subscriber);
    }

    /**
     * Publishes a traffic event
     *
     * @param byteCount the number of written/read bytes
     * @param isSecured if the transmission is secure
     */
    private void publishTraffic(Long byteCount, boolean isSecured) {

        TrafficEventWorker publisher = new TrafficEventWorker(TrafficType.INCOMING, address, port, byteCount, isSecured);

        for (Observer subscriber :
                subscribers) {
            publisher.addObserver(subscriber);
        }

        publisherPool.submit(publisher);
    }
}
