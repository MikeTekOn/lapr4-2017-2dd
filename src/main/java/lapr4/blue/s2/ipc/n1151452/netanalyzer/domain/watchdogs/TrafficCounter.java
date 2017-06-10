package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.*;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A watchdog listening for traffic events and sending them to their listeners
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public class TrafficCounter extends TrafficWatchdog {

    /**
     * Time Interval to flush counters
     */
    private static final long TIME_INTERVAL = 1L;

    /**
     * The singleton instance
     */
    private static final TrafficCounter instance = new TrafficCounter();

    /**
     * Secured incoming byte counter
     */
    private static AtomicLong securedIncoming = new AtomicLong(0);

    /**
     * Unsecured incoming byte counter
     */
    private static AtomicLong unsecuredIncoming = new AtomicLong(0);

    /**
     * Secured outgoing byte counter
     */
    private static AtomicLong securedOutgoing = new AtomicLong(0);

    /**
     * Unsecured outgoing byte counter
     */
    private static AtomicLong unsecuredOutgoing = new AtomicLong(0);

    /**
     * The traffic count listeners
     */
    private static Set<TrafficCounterListener> listeners = new HashSet<>();

    /**
     * The flush scheduler
     */
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10, new TrafficFlusherFactory());

    /**
     * Builds a traffic counter
     */
    private TrafficCounter() {
        super();
        setScheduler(TIME_INTERVAL);
    }

    /**
     * Sets the flush scheduler
     *
     * @param timeInterval the time interval between each flush operation (in seconds)
     */
    private void setScheduler(Long timeInterval) {
        scheduler.scheduleAtFixedRate(new Flusher(), 0, timeInterval, TimeUnit.SECONDS);
    }

    /**
     * Obtains the singletons instance
     *
     * @return the singletons instance
     */
    public static TrafficCounter getInstance() {

        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (!isOn()) return;

        if (o instanceof TrafficEventWorker) {
            if (arg instanceof TrafficEvent) {

                incrementCounter((TrafficEvent) arg);
            }
        }
    }

    /**
     * Increments the related atomic counter
     *
     * @param event the event that contains the byte count
     */
    private void incrementCounter(TrafficEvent event) {

        if (event.type() == TrafficType.INCOMING) {
            if (event.isSecured()) {
                securedIncoming.addAndGet(event.count());
            } else {
                unsecuredIncoming.addAndGet(event.count());
            }
        } else {
            if (event.isSecured()) {
                securedOutgoing.addAndGet(event.count());
            } else {
                unsecuredOutgoing.addAndGet(event.count());
            }
        }
    }

    /**
     * Adds a traffic counter listener
     *
     * @param listener the listener to add
     * @return true if listener was added, false otherwise
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean addTrafficCounterListener(TrafficCounterListener listener) {

        return listeners.add(listener);
    }

    /**
     * Removes a traffic counter listener
     *
     * @param listener the listener to remove
     * @return true if listener was removed, false otherwise
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean removeTrafficCounterListener(TrafficCounterListener listener) {

        return listeners.remove(listener);
    }

    /**
     * Flush the counters and send to listeners
     */
    private static synchronized void flush() {

        Long secIn = securedIncoming.getAndSet(0);
        Long unsecIn = unsecuredIncoming.getAndSet(0);
        Long secOut = securedOutgoing.getAndSet(0);
        Long unsecOut = unsecuredOutgoing.getAndSet(0);

        TrafficCount count = new TrafficCount(null, secIn, unsecIn, secOut, unsecOut);

        for (TrafficCounterListener listener :
                listeners) {
            listener.fireNewTrafficCount(count);
        }
    }

    /**
     * Worker that flushes and send to listeners
     */
    private class Flusher implements Runnable {

        @Override
        public void run() {

            if (!isOn()) return;

            flush();
        }
    }
}
