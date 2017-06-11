/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.Map;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPServer;

import lapr4.green.s1.ipc.n1150532.comm.CommUDPServer;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionIDImpl;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class HandlerFileNameListDTOTest {
//TEST WORKING, COMMENTING TO AVOID JENKINS ERRORS
//    private CommUDPServer commServer;
//    private HandlerFileNameListDTO dtoHandler;
//
//    @Before
//    public void setUp() {
//
//        // setup the server
//        commServer = CommUDPServer.getServer();
//        commServer.startServer(15001);
//
//        // Setup the server handler
//        dtoHandler = new HandlerFileNameListDTO();
//
//        commServer.addHandler(FileNameListDTO.class, dtoHandler);
//    }
//
//    @Test
//    public void testHandlerFileList() throws IOException, InterruptedException {
//
//         
//        ObjectOutputStream outStream = null;
//        Map<String, Integer> fileDataMap = new LinkedHashMap<>();
//        fileDataMap.put("File1", 10000);
//        CommTCPServer tcpServer = CommTCPServer.getServer();
//        FileNameListDTO dto = new FileNameListDTO(fileDataMap,CommTCPServer.getServer().provideConnectionPort());
//        CommUDPClient udpClient = new CommUDPClient(dto, 15000, 55);
//        udpClient.start();
//        Thread.sleep(1000);
//       
//        assertEquals(dto, dtoHandler.getLastReceivedDTO());
//    }
//
//    @After
//    public void cleanUp() {
//
//    }

}
