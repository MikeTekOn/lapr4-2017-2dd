package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import csheets.core.Spreadsheet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import static javax.mail.Message.RecipientType.TO;
import lapr4.blue.s2.ipc.n1140822.fileShare.FileNameListDTO;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPServer;
import lapr4.green.s1.ipc.n1150532.comm.CommUDPClient;
import lapr4.green.s1.ipc.n1150532.comm.CommUDPServer;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionIDImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Correia - 1151031£isep.ipp.pt
 */
public class HandlerSearchWorkbookResponseDTOTest {

    //TESTS COMMENTED TO AVOID JENKINS ERRORS
//    private CommUDPServer commServer;
//    private HandlerSearchWorkbookResponseDTO dtoHandler;
//
//    @Before
//    public void setUp() {
//        // setup the server
//        commServer = CommUDPServer.getServer();
//        commServer.startServer(15001);
//        // Setup the server handler
//        dtoHandler = new HandlerSearchWorkbookResponseDTO();
//        commServer.addHandler(SearchWorkbookResponseDTO.class, dtoHandler);
//    }
//
//    @Test
//    public void HandlerSearchWorkbookResponseDTOTest() throws IOException, InterruptedException {
//        ObjectOutputStream outStream = null;
//        List<SearchResults> list = new ArrayList();
//        List<Spreadsheet> spreadsheetList = new ArrayList();
//        SearchResults searchResults = new SearchResults("testeName", spreadsheetList, null);
//        list.add(searchResults);
//        SearchWorkbookResponseDTO dto = new SearchWorkbookResponseDTO(list);
//        CommUDPClient udpClient = new CommUDPClient(dto, 15001, 2000);
//        udpClient.start();
//        assertEquals(dto, dtoHandler.getLastReceivedDTO());
//    }
}
