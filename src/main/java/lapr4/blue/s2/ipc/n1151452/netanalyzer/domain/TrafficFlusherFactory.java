package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import java.util.concurrent.ThreadFactory;

/**
 * Factory to create typical traffic counter flushers
 */
public class TrafficFlusherFactory implements ThreadFactory {

    private static int ID = 0;

    @Override
    public Thread newThread(Runnable r) {

        Thread flusher = new Thread(r);
        flusher.setPriority(Thread.MAX_PRIORITY);
        ID++;
        flusher.setName("traffic-flusher-"+ID);

        return flusher;
    }
}
