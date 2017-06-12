package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.AESEncryptedTransmission;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.util.CustomUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.Observable;
import java.util.Observer;

/**
 * Test if the traffic output stream publishes an event
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
@SuppressWarnings("Duplicates")
public class TrafficOutputStreamTest {

//    /**
//     * A test subscriber class to catch published events
//     */
//    private class TestSubscriber implements Observer {
//
//        private TrafficEvent event;
//
//        @Override
//        public void update(Observable o, Object arg) {
//
//            if (o instanceof TrafficEventWorker) {
//                if (arg instanceof TrafficEvent) {
//                    event = (TrafficEvent) arg;
//                }
//            }
//        }
//    }
//
//    private TrafficOutputStream stream = null;
//    private FileOutputStream fos = null;
//    private File testFile;
//    private InetAddress address;
//    private int port;
//
//    @Before
//    public void setUp() throws IOException {
//
//        address = InetAddress.getByName("192.1.1.1");
//        port = 8888;
//
//        testFile = new File("test.txt");
//        //noinspection ResultOfMethodCallIgnored
//        testFile.createNewFile();
//
//        // create a new file with an ObjectOutputStream
//        fos = new FileOutputStream(testFile);
//        stream = new TrafficOutputStream(new ObjectOutputStream(fos), address, port, new OpenTransmission());
//    }
//
//    @After
//    public void tearDown() throws IOException {
//
//        //noinspection ResultOfMethodCallIgnored
//        testFile.delete();
//        fos.close();
//        stream.close();
//    }
//
//    @Test
//    public void ensureTrafficEventIsPublished() throws IOException, ClassNotFoundException {
//
//        TestSubscriber subscriber = new TestSubscriber();
//        stream.addSubscriber(subscriber);
//
//        stream.write("Tester");
//
//        // Wait until worker ends to continue test (for Debugging purposes)
//        Thread threads[] = new Thread[Thread.activeCount()];
//        Thread.enumerate(threads);
//        for (Thread worker :
//                threads) {
//            if (worker.getName().contains("traffic-publisher")) {
//                //noinspection StatementWithEmptyBody
//                while (worker.getState() == Thread.State.RUNNABLE) ;
//            }
//        }
//
//        assert subscriber.event != null;
//    }
//
//    @Test
//    public void ensureTrafficEventPublishesTheCorrectUnsecuredEvent() throws IOException, ClassNotFoundException, InterruptedException {
//
//        TestSubscriber subscriber = new TestSubscriber();
//        stream.addSubscriber(subscriber);
//
//        Object object = "Tester";
//
//        stream.write(object);
//
//        Long length = CustomUtil.length(object);
//
//        // Wait until worker ends to continue test (for Debugging purposes)
//        Thread threads[] = new Thread[Thread.activeCount()];
//        Thread.enumerate(threads);
//        for (Thread worker :
//                threads) {
//            if (worker.getName().contains("traffic-publisher")) {
//                //noinspection StatementWithEmptyBody
//                while (worker.getState() == Thread.State.RUNNABLE) ;
//            }
//        }
//
//        assert !subscriber.event.isSecured();
//        assert subscriber.event.count().equals(length);
//        assert subscriber.event.ipAddress() == address;
//        assert subscriber.event.port() == port;
//        assert subscriber.event.type() == TrafficType.OUTGOING;
//    }
//
//    @Test
//    public void ensureTrafficEventPublishesTheCorrectSecuredEvent() throws IOException, ClassNotFoundException, InterruptedException {
//
//        TestSubscriber subscriber = new TestSubscriber();
//
//        // create a new file with an ObjectOutputStream
//        FileOutputStream fos = new FileOutputStream(testFile);
//        TrafficOutputStream stream = new TrafficOutputStream(new ObjectOutputStream(fos), address, port, new AESEncryptedTransmission());
//        stream.addSubscriber(subscriber);
//
//        Object object = "Tester";
//
//        stream.write(object);
//
//        Long length = CustomUtil.length(object);
//
//        // Wait until worker ends to continue test (for Debugging purposes)
//        Thread threads[] = new Thread[Thread.activeCount()];
//        Thread.enumerate(threads);
//        for (Thread worker :
//                threads) {
//            if (worker.getName().contains("traffic-publisher")) {
//                //noinspection StatementWithEmptyBody
//                while (worker.getState() == Thread.State.RUNNABLE) ;
//            }
//        }
//
//        assert subscriber.event.isSecured();
//        assert subscriber.event.count().equals(length);
//        assert subscriber.event.ipAddress() == address;
//        assert subscriber.event.port() == port;
//        assert subscriber.event.type() == TrafficType.OUTGOING;
//    }
}