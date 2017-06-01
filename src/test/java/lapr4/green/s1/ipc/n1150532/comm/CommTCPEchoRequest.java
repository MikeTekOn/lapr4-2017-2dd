package lapr4.green.s1.ipc.n1150532.comm;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A simple echo request can be sent from one instance to another (simulated).
 * The client shall connect to the server and request it an echo. The client
 * must receive the echo response.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommTCPEchoRequest {

    /**
     * The TCP server
     */
    private CommTCPServer tcpServer;
    
    /**
     * The TCP Clients Manager
     */
    private CommTCPClientsManager tcpClientsManager;
    
    /**
     * The port number to use. Attention! It must use a unique port within all tests class.
     */
    private int portNumber;
    
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
    public CommTCPEchoRequest() {
    }

    /**
     * It sets up the test. It starts the server and adds the handlers. It also sets the messages.
     */
    @Before
    public void setUp() {
        portNumber = 15351;
        tcpServer = CommTCPServer.getServer(portNumber);
        tcpServer.addHandler(EchoDTO.class, new EchoServerHandler());
        tcpClientsManager = new CommTCPClientsManager();
        tcpClientsManager.addHandler(EchoDTO.class, new EchoClientHandler());
        message = "Can you read me?";
        answer = "Yes.";
    }

    /**
     * It guarantees that the server is shutdown.
     */
    @After
    public void cleanUp() {
        tcpServer.terminateExecution();
    }

    /**
     * It starts a client who sends an echo request. The server receives it and replies to it. The client receives the response.
     */
    @Test
    public void testEcho() {
        try {
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), portNumber);
            CommTCPClientWorker client = new CommTCPClientWorker(tcpClientsManager, clientSocket);
            client.start();

            // Send the echo
            client.getObjectOutputStream().writeObject(new EchoDTO(message, answer));

            // Wait a little so the communication can take place
            Thread.sleep(2000);

            // Check the DTO received at Server side
            EchoDTO dto = (EchoDTO) tcpServer.getHandler(EchoDTO.class).getLastReceivedDTO();
            assertTrue(dto != null);
            assertTrue(message.equals(dto.getMessage()));
            assertTrue(answer.equals(dto.getAnswer()));

            // Check the DTO received at Client side
            dto = null;
            dto = (EchoDTO) tcpClientsManager.getHandler(EchoDTO.class).getLastReceivedDTO();
            assertTrue(dto != null);
            assertTrue(answer.equals(dto.getMessage()));
            assertTrue(null == dto.getAnswer());

            client.terminateExecution();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(CommTCPEchoRequest.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

}
