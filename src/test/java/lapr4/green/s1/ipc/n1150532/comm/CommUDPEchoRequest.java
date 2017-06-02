package lapr4.green.s1.ipc.n1150532.comm;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * A broadcast can be sent to the local network. The instance's server must receive the echo request and the instance's client the server's response.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommUDPEchoRequest {

    /**
     * The UDP server
     */
    private CommUDPServer udpServer;
    
    /**
     * The UDP client
     */
    private CommUDPClient udpClient;
    
    /**
     * The port number to use. Attention! It must use a unique port within all tests class.
     */
    private int portNumber;
    
    /**
     * The client timeout. After that time without any reply it shuts down.
     */
    private int waitingTime;
    
    /**
     * A message to send in the request.
     */
    private String message;
    
    /**
     * The message to receive on the reply.
     */
    private String answer;
    
    /**
     * Default constructor.
     */
    public CommUDPEchoRequest() {
    }

    /**
     * It sets up the test. It starts the server and adds the handlers. It also sets the messages.
     */
    @Before
    public void setUp() {
        portNumber = 15360;
        waitingTime = 1000;
        message = "Can you read me?";
        answer = "Yes.";
        udpServer = CommUDPServer.getServer();
        udpServer.addHandler(EchoDTO.class, new EchoUDPServerHandler());
        udpServer.startServer(portNumber);
        udpClient = new CommUDPClient(new EchoDTO(message,answer), portNumber, waitingTime);
        udpClient.addHandler(EchoDTO.class, new EchoUDPClientHandler());
    }

    /**
     * It guarantees that the server is shutdown.
     */
    @After
    public void cleanUp() {
        udpServer.terminateExecution();
    }

    /**
     * It starts a client who sends a broadcast echo request. The server receives it and replies to it. The client receives the response.
     */
    @Test
    public void testEcho() {
        try {
            udpClient.start();
            
            // Wait a little so the communication can take place
            Thread.sleep(2000);
            
            // Check the DTO received at Server side
            EchoDTO dto = (EchoDTO) udpServer.getHandler(EchoDTO.class).getLastReceivedDTO();
            assertTrue(dto != null);
            assertTrue(message.equals(dto.getMessage()));
            assertTrue(answer.equals(dto.getAnswer()));
            
            // Check the DTO received at Client side;
            dto = (EchoDTO) udpClient.getHandler(EchoDTO.class).getLastReceivedDTO();
            assertTrue(dto != null);
            assertTrue(answer.equals(dto.getMessage()));
            assertTrue(null==dto.getAnswer());
        } catch (InterruptedException ex) {
            Logger.getLogger(CommTCPEchoRequest.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

}
