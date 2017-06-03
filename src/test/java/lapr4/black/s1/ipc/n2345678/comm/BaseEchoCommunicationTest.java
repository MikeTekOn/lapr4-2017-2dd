/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.black.s1.ipc.n2345678.comm;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * A unit test class to test the base functionality of csheets.ext.ipc.comm
 * 
 * @author alexandrebraganca
 */
public class BaseEchoCommunicationTest {

    private CommServer commServer;
    private CommHandler helloHandler;
    
    @Before public void setUp() {
        // Create the server
        commServer = CommServer.getCommServer();
        
        // Setup the server handler
        // To test the server we are going to create a simple handler
        helloHandler = new EchoServerHandler();
        commServer.addHandler(EchoDTO.class, helloHandler);
        
    }
    
    @After public void cleanUp() {
        // Vai ser necessario parar a thread do servidor!!!!!
        // assim como as worker threads!!!
    }
                      
    @Test public void testEcho() {
        
        ObjectOutputStream outStream = null;
        try {
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), 15370);
            Logger.getLogger(BaseEchoCommunicationTest.class.getName()).log(Level.INFO, "Porta Local="+clientSocket.getLocalPort());
            
            CommClientWorker client=new CommClientWorker(clientSocket);
            client.start();
            
            // Setup the client handler
            EchoClientHandler echoClientHandler = new EchoClientHandler();
            client.addHandler(EchoDTO.class, echoClientHandler);
            
            outStream = client.getObjectOutputStream();
            outStream.writeObject(new EchoDTO("Hello!"));

            // wait a little so the communication can take place...
            Thread.sleep(1000);
            
            EchoDTO dto=(EchoDTO)helloHandler.getLastReceivedDTO();
            assertTrue(dto!=null);
            assertTrue("Hello!".compareTo(dto.getMessage())==0);
                        
            dto=null;
            dto=(EchoDTO)echoClientHandler.getLastReceivedDTO();
            assertTrue(dto!=null);
            assertTrue("Reply to Hello!".compareTo(dto.getMessage())==0);
            
            client.terminateExecution();
        } catch (IOException ex) {
            Logger.getLogger(BaseEchoCommunicationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BaseEchoCommunicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
