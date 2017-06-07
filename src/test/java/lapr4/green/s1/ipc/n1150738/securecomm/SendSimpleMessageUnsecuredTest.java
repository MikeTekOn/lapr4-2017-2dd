package lapr4.green.s1.ipc.n1150738.securecomm;

import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class SendSimpleMessageUnsecuredTest {

    File f;

    public SendSimpleMessageUnsecuredTest() {

    }


    @Before
    public void setUp() {
        f = new File("SendSimpleMessageUnsecuredTest");
    }


    @After
    public void cleanUp(){
        f.delete();
    }


    @Test
    public void SendSimpleMessageUnsecured()  throws IOException, ClassNotFoundException  {
        FileOutputStream fos = new FileOutputStream(f);
        DataTransmissionContext ctx = new BasicDataTransmissionContext();
        ObjectOutputStream out = ctx.outputStream(fos);

        SimpleMessageDTO d1 = new SimpleMessageDTO("Unsecure TEst");
        out.writeObject(d1);

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream in = ctx.inputStream(fis);
        SimpleMessageDTO d2 = (SimpleMessageDTO) in.readObject();
        assertEquals(d1,d2);
    }

}
