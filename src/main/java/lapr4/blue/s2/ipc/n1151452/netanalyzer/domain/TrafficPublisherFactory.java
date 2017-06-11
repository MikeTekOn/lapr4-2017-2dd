package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import java.util.concurrent.ThreadFactory;

/**
 * Factory to create typical traffic event publishers.
 */
public class TrafficPublisherFactory implements ThreadFactory {

    private static int ID = 0;

    @Override
    public Thread newThread(Runnable r) {

        Thread publisher = new Thread(r);
        publisher.setPriority(Thread.MIN_PRIORITY);
        ID++;
        publisher.setName("traffic-publisher-"+ID);

        return publisher;
    }
}
