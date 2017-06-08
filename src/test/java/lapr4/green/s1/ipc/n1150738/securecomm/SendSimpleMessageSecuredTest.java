package lapr4.green.s1.ipc.n1150738.securecomm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class SendSimpleMessageSecuredTest {

    File f;

    public SendSimpleMessageSecuredTest() {

    }


    @Before
    public void setUp() {
        f = new File("SendSimpleMessageSecuredTest");
    }


    @After
    public void cleanUp(){
        f.delete();
    }


    @Test
    public void SendSimpleMessageUnsecured()  throws IOException, ClassNotFoundException  {
        FileOutputStream fos = new FileOutputStream(f);
        DataTransmissionContext ctx = new SecureAESDataTransmissionContext();
        ObjectOutputStream out = ctx.outputStream(fos);

        SimpleMessageDTO d1 = new SimpleMessageDTO("Secure TEst");
        out.writeObject(d1);

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream in = ctx.inputStream(fis);
        SimpleMessageDTO d2 = (SimpleMessageDTO) in.readObject();
        assertEquals(d1,d2);
    }

}
