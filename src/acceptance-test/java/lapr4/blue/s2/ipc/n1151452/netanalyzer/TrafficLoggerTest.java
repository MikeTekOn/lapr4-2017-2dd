package lapr4.blue.s2.ipc.n1151452.netanalyzer;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficEvent;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficInputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficLogListener;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficLogger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Tests a traffic logger
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
@SuppressWarnings("Duplicates")
public class TrafficLoggerTest {

//    /**
//     * A test listener class to log events
//     */
//    private class TestLogListener implements TrafficLogListener {
//
//        @Override
//        public void fireNewTrafficEvent(TrafficEvent event) {
//            log.add(event);
//        }
//    }
//
//    private Set<TrafficEvent> log = new HashSet<>();
//    private FileOutputStream fos = null;
//    private FileInputStream fis = null;
//    private File testFile;
//    private TrafficInputStream in = null;
//    private TrafficOutputStream out = null;
//
//    @Before
//    public void setUp() throws IOException {
//
//        TrafficLogger.getInstance().turnOn();
//        TestLogListener subscriber = new TestLogListener();
//        TrafficLogger.getInstance().addTrafficLogListener(subscriber);
//
//        InetAddress address = InetAddress.getByName("192.1.1.1");
//        int port = 8888;
//
//        testFile = new File("test.txt");
//        //noinspection ResultOfMethodCallIgnored
//        testFile.createNewFile();
//
//        fos = new FileOutputStream(testFile);
//        out = new TrafficOutputStream(new ObjectOutputStream(fos), address, port, new OpenTransmission());
//        fis = new FileInputStream(testFile);
//        in = new TrafficInputStream(new ObjectInputStream(fis), address, port, new OpenTransmission());
//
//        out.addSubscriber(TrafficLogger.getInstance());
//        in.addSubscriber(TrafficLogger.getInstance());
//    }
//
//    @After
//    public void tearDown() throws IOException {
//
//        //noinspection ResultOfMethodCallIgnored
//        testFile.delete();
//        fis.close();
//        in.close();
//        fos.close();
//        out.close();
//    }
//
////    /**
////     * Tests if the logger sends logs to a listener
////     *
////     * @throws IOException I/O Exception
////     * @throws ClassNotFoundException class not found
////     * @throws InterruptedException thread interruption
////     */
////    @Test
////    public void ensureLoggerSendsEventToListener() throws IOException, ClassNotFoundException {
////
////        String tester = "tester";
////        int tester2 = 1;
////
////        out.write(tester);
////        in.readObjectOvveride();
////        out.write(tester2);
////        in.readObjectOvveride();
////
////        // Wait until worker ends to continue test (for Debugging purposes)
////        Thread threads[] = new Thread[Thread.activeCount()];
////        Thread.enumerate(threads);
////        for (Thread worker :
////                threads) {
////            if (worker.getName().contains("traffic-publisher") ||
////                    worker.getName().contains("log-consumer")) {
////                //noinspection StatementWithEmptyBody
////                while (worker.getState() == Thread.State.RUNNABLE) ;
////            }
////        }
////        assertTrue(log.size() == 4);
////    }
}