package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.util.CustomUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.util.Observable;
import java.util.Observer;

/**
 * Test if the traffic input stream publishes an event
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
@SuppressWarnings("Duplicates")
public class TrafficInputStreamTest {

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
//    private static final byte[] raw = "cleansheetslapr4".getBytes(); // 16 bytes
//    private TrafficInputStream stream = null;
//    private FileInputStream fis = null;
//    private File testFile;
//    private File testFile2;
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
//        testFile2 = new File("test2.txt");
//        //noinspection ResultOfMethodCallIgnored
//        testFile2.createNewFile();
//
//        // create a new file with an ObjectOutputStream
//        FileOutputStream fos = new FileOutputStream(testFile);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject("tester");
//
//        fis = new FileInputStream(testFile);
//        stream = new TrafficInputStream(new ObjectInputStream(fis), address, port, new OpenTransmission());
//    }
//
//    @After
//    public void tearDown() throws IOException {
//
//        //noinspection ResultOfMethodCallIgnored
//        testFile.delete();
//        //noinspection ResultOfMethodCallIgnored
//        testFile2.delete();
//        fis.close();
//        stream.close();
//    }
//
//    @Test
//    public void ensureTrafficEventIsPublished() throws IOException, ClassNotFoundException {
//
//        TestSubscriber subscriber = new TestSubscriber();
//        stream.addSubscriber(subscriber);
//
//        stream.readObjectOvveride();
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
//    public void ensureTrafficEventPublishesCorrectUnsecuredEvent() throws IOException, ClassNotFoundException {
//
//        TestSubscriber subscriber = new TestSubscriber();
//        stream.addSubscriber(subscriber);
//
//        Object object = stream.readObjectOvveride();
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
//        assert subscriber.event.type() == TrafficType.INCOMING;
//    }
//
////    @Test
////    public void ensureTrafficEventPublishesCorrectSecuredEvent() throws IOException, ClassNotFoundException {
////
////        TestSubscriber subscriber = new TestSubscriber();
////
////        Cipher cipher;
////        try {
////            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
////            cipher = Cipher.getInstance("AES/CTR/NoPadding");
////            IvParameterSpec iv = new IvParameterSpec(raw);
////            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
////        } catch (Exception e) {
////            throw new IOException("Failed to create stream.");
////        }
////
////        // create a new file with an ObjectOutputStream
////        FileOutputStream fos = new FileOutputStream(testFile2);
////        ObjectOutputStream oos = new ObjectOutputStream(new CipherOutputStream(fos, cipher));
////        oos.write("tester");
////
////        FileInputStream fis = new FileInputStream(testFile2);
////        TrafficInputStream stream = new TrafficInputStream(fis, address, port, new AESEncryptedTransmission());
////        stream.addSubscriber(subscriber);
////
////        Object object = stream.readObjectOvveride();
////
////        Long length = CustomUtil.length(object);
////
////        // Wait until worker ends to continue test (for Debugging purposes)
////        Thread threads[] = new Thread[Thread.activeCount()];
////        Thread.enumerate(threads);
////        for (Thread worker :
////                threads) {
////            if (worker.getName().contains("traffic-publisher")) {
////                //noinspection StatementWithEmptyBody
////                while (worker.getState() == Thread.State.RUNNABLE) ;
////            }
////        }
////
////        assert subscriber.event.isSecured();
////        assert subscriber.event.count().equals(length);
////        assert subscriber.event.ipAddress() == address;
////        assert subscriber.event.port() == port;
////        assert subscriber.event.type() == TrafficType.INCOMING;
////    }
}