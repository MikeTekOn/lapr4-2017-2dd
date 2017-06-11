package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficEvent;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficEventWorker;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A watchdog listening for traffic events and logging them
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public class TrafficLogger extends TrafficWatchdog {

    /**
     * The singleton instance
     */
    private static final TrafficLogger instance = new TrafficLogger();

    /**
     * Queue to temporally store received events
     */
    private static final ConcurrentLinkedQueue<TrafficEvent> queue = new ConcurrentLinkedQueue<>();

    /**
     * The traffic log listeners
     */
    private Set<TrafficLogListener> listeners = new HashSet<>();

    /**
     * Object representing a lock
     */
    private final Object lock = new Object();

    /**
     * Builds a traffic logger
     */
    private TrafficLogger() {
        super();
        launchConsumer();
    }

    /**
     * Launches thw consumer thread
     */
    private void launchConsumer() {

        LogConsumer consumer = new LogConsumer();
        consumer.setPriority(Thread.MAX_PRIORITY);
        consumer.setName("log-consumer");

        consumer.start();
    }

    /**
     * Obtains the singletons instance
     *
     * @return the singletons instance
     */
    public static TrafficLogger getInstance() {
        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (!isOn()) return;

        if (o instanceof TrafficEventWorker) {
            if (arg instanceof TrafficEvent) {

                insertEvent((TrafficEvent) arg);
            }
        }
    }

    /**
     * Insert an event in the queue
     *
     * @param event the traffic event to insert
     * @return true if successfully inserted, false otherwise
     */
    @SuppressWarnings("UnusedReturnValue")
    private boolean insertEvent(TrafficEvent event) {

        boolean inserted = queue.add(event);
        synchronized (lock) {
            lock.notify();
        }
        return inserted;

    }

    /**
     * Adds a traffic log listener
     *
     * @param listener the listener to add
     * @return true if listener was added, false otherwise
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean addTrafficLogListener(TrafficLogListener listener) {

        return listeners.add(listener);
    }

    /**
     * Removes a traffic log listener
     *
     * @param listener the listener to remove
     * @return true if listener was removed, false otherwise
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean removeTrafficLogListener(TrafficLogListener listener) {

        return listeners.remove(listener);
    }

    /**
     * Alerts the listeners of a new consumed event
     *
     * @param event the event to log
     */
    private void alertListeners(TrafficEvent event) {

        if (event == null) return;

        for (TrafficLogListener listener :
                listeners) {
            listener.fireNewTrafficEvent(event);
        }
    }

    /**
     * Worker that consumes events and send them to components that are listening
     */
    private class LogConsumer extends Thread {

        @Override
        public void run() {

            synchronized (lock) {
                //noinspection InfiniteLoopStatement
                while (true) {

                    // Wait if queue is empty
                    while (queue.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Logger.getLogger(LogConsumer.class.getName()).log(Level.WARNING, "consumer synchronization failed.", e);
                        }
                    }
                    // Send to listeners
                    alertListeners(queue.poll());
                }
            }
        }
    }
}
