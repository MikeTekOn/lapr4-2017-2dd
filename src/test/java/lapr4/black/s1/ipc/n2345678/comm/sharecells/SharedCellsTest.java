/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.black.s1.ipc.n2345678.comm.sharecells;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.Extension;
import lapr4.black.s1.ipc.n2345678.comm.CommClientWorker;
import lapr4.black.s1.ipc.n2345678.comm.CommServer;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150838.findworkbooks.ext.ExtensionFindWorkbooks;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author alexandrebraganca
 */
public class SharedCellsTest {
    
    private CommServer commServer;
    private HandleReceiveCells shareCellsHandler;
    
    private Spreadsheet s_server, s_client;
   
    @Before public void setUp() {
        
        // Try to create the CS application object
        CleanSheets.setFlag(true);
        CleanSheets app = new CleanSheets();

        // This will create a workbook with 3 sheets
        app.create();
        
	// create two workbooks, one for the server and one for the client
        s_server=app.getWorkbooks()[0].getSpreadsheet(0);
        
        s_client=app.getWorkbooks()[0].getSpreadsheet(1);
        
        // setup the server
        commServer = CommServer.getCommServer();
        
        // Setup the server handler
        shareCellsHandler = new HandleReceiveCells(app);
        commServer.addHandler(RequestSharedCellsDTO.class, shareCellsHandler);        
    }

    @Test public void testShareCellsBetweenSheets() {
        /*try {
            // Fill the sheet of the client with a set of example values
            s_client.getCell(new Address(0,0)).setContent("10");
            s_client.getCell(new Address(0,1)).setContent("20");
            s_client.getCell(new Address(0,2)).setContent("30");
            s_client.getCell(new Address(1,0)).setContent("11");
            s_client.getCell(new Address(1,1)).setContent("21");
            s_client.getCell(new Address(1,2)).setContent("31");

            // Send the range to the server
            ObjectOutputStream outStream = null;
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), 15000);
            Logger.getLogger(SharedCellsTest.class.getName()).log(Level.INFO, "Porta Local="+clientSocket.getLocalPort());
            
            CommClientWorker client=new CommClientWorker(clientSocket);
            client.start();
            
            // Setup the client handler
//            EchoClientHandler echoClientHandler = new EchoClientHandler();
//            client.addHandler(EchoDTO.class, echoClientHandler);
            
            outStream = client.getObjectOutputStream();
            outStream.writeObject(new RequestSharedCellsDTO(s_server.getTitle(), s_client, new Address(0,0), new Address(1,2)));
            
            Thread.sleep(1000);
            
            // Test the contents in the server
            assertTrue(s_client.getCell(new Address(0,0)).getContent().compareTo(s_server.getCell(new Address(0,0)).getContent())==0);
            assertTrue(s_client.getCell(new Address(0,1)).getContent().compareTo(s_server.getCell(new Address(0,1)).getContent())==0);
            assertTrue(s_client.getCell(new Address(0,2)).getContent().compareTo(s_server.getCell(new Address(0,2)).getContent())==0);
            assertTrue(s_client.getCell(new Address(1,0)).getContent().compareTo(s_server.getCell(new Address(1,0)).getContent())==0);
            assertTrue(s_client.getCell(new Address(1,1)).getContent().compareTo(s_server.getCell(new Address(1,1)).getContent())==0);
            assertTrue(s_client.getCell(new Address(1,2)).getContent().compareTo(s_server.getCell(new Address(1,2)).getContent())==0);
    
        } catch (FormulaCompilationException ex) {
            Logger.getLogger(SharedCellsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(SharedCellsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SharedCellsTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SharedCellsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
        
    }
        
    @After public void cleanUp() {
        
    }
}
