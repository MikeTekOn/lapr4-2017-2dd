/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.instance;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Spreadsheet;
import csheets.core.SpreadsheetImpl;
import csheets.core.Workbook;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lapr4.black.s1.ipc.n2345678.comm.CommServer;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.HandleReceiveCells;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * A unit test class to test the base functionality of csheets.ext.ipc.comm
 *
 * @author Miguel Silva - 1150901
 */
public class SearchWorkbookTest {

    private CleanSheets c_server, c_client;
    private File f_server, f_client;
    private Workbook w_server, w_client;

    @Before
    public void setUp() throws IOException {
        f_server = new File("server_file.cls");
        f_client = new File("client_file.cls");
        c_server = new CleanSheets();
        c_client = new CleanSheets();
        w_server = new Workbook();
        w_client = new Workbook();

        String[][] server_content = new String[1][1];
        server_content[0][0] = "caralho";
        String[][] client_content = new String[1][1];
        client_content[0][0] = "pila";

        w_server.addSpreadsheet(server_content);
        w_client.addSpreadsheet(client_content);

        c_server.saveAs(w_server, f_server);
        c_client.saveAs(w_client, f_client);
    }

    @Test
    public void verifyWorkbookIsOpenInRemoteInstanceTest() {
        File server = c_server.getFile(c_server.getWorkbooks()[0]);
        File client = c_client.getFile(c_client.getWorkbooks()[0]);

        assertEquals(server, f_server);
        assertEquals(client, f_client);
    }

    @Test
    public void spreadsheetCellsAreNotAllEmptyTest() {
        w_server = c_server.getWorkbooks()[0];
        w_client = c_client.getWorkbooks()[0];

        Address address1 = new Address(0, 0);
        Address address2 = new Address(0, 1);

        assertTrue(w_server.getSpreadsheet(0).getCells(address1, address2) != null);
        assertTrue(w_client.getSpreadsheet(0).getCells(address1, address2) != null);
    }

    @After
    public void cleanUp() {
        f_server.delete();
        f_client.delete();
    }
}
