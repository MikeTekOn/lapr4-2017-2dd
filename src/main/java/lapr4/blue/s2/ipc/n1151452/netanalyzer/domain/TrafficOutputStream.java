package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.TransmissionStrategy;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficCounter;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficLogger;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.util.CustomUtil;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Represents a traffic output stream (Decorator pattern)
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public class TrafficOutputStream extends OutputStream {

    private ObjectOutputStream stream;
    private boolean isSecured;

    private final ExecutorService publisherPool;
    private final Set<Observer> subscribers;
    private final InetAddress address;
    private final int port;

    /**
     * Builds a traffic output stream
     *
     * @param out       the decorated output stream
     * @param ipAddress the ip address that is connected
     * @param tcpPort   the tcp port that is connected
     * @throws IOException I/O exception
     */
    public TrafficOutputStream(OutputStream out, InetAddress ipAddress, int tcpPort, TransmissionStrategy strategy) throws IOException {
        super();

        stream = (strategy == null) ? new OpenTransmission().stream(out) : strategy.stream(out);
        isSecured = strategy != null && strategy.isSecured();

        address = ipAddress;
        port = tcpPort;
        publisherPool = Executors.newCachedThreadPool(new TrafficPublisherFactory());
        subscribers = new HashSet<>();

        subscribers.add(TrafficLogger.getInstance());
        subscribers.add(TrafficCounter.getInstance());
    }

    /**
     * Writes an object to the stream & publishes a traffic event
     *
     * @throws IOException            I/O Exception
     * @throws ClassNotFoundException Class not found
     */
    public void writeObject(Object object) throws IOException, ClassNotFoundException {

        stream.writeObject(object);
        publishTraffic(CustomUtil.length(object), isSecured);
    }

    @Override
    public void write(int b) throws IOException {

        stream.write(b);
        publishTraffic(CustomUtil.length(b), isSecured);
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

        TrafficEventWorker publisher = new TrafficEventWorker(TrafficType.OUTGOING, address, port, byteCount, isSecured);

        for (Observer subscriber :
                subscribers) {
            publisher.addObserver(subscriber);
        }

        publisherPool.submit(publisher);
    }
}
