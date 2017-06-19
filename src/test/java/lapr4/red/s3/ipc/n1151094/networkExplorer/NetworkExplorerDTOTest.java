/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1151094.networkExplorer;

import csheets.core.Workbook;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduangelo Ferreira
 */
public class NetworkExplorerDTOTest {

    public NetworkExplorerDTOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAdrress method, of class NetworkExplorerDTO.
     */
    @Test
    public void testGetAdrress() throws UnknownHostException {
        System.out.println("getAdrress");
        Workbook work = new Workbook(3, null);
        String[][] listExtensionActivate = new String[2][2];
        String[][] listExtensionDesactivate = new String[2][2];

        NetworkExplorerDTO instance = new NetworkExplorerDTO(InetAddress.getLocalHost(), work, listExtensionActivate, listExtensionDesactivate);
        InetAddress expResult = InetAddress.getLocalHost();
        InetAddress result = instance.getAdrress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListExtensionDesactivate method, of class NetworkExplorerDTO.
     */
    @Test
    public void testGetListExtensionDesactivate() throws UnknownHostException {
        System.out.println("getListExtensionDesactivate");
      Workbook work = new Workbook(3, null);
        String[][] listExtensionActivate = new String[2][2];
        String[][] listExtensionDesactivate = new String[2][2];

        NetworkExplorerDTO instance = new NetworkExplorerDTO(InetAddress.getLocalHost(), work, listExtensionActivate, listExtensionDesactivate);
        String[][] expResult = listExtensionDesactivate;
        String[][] result = instance.getListExtensionDesactivate();
        assertArrayEquals(expResult, result);
      
    }

    /**
     * Test of getListExtensionActivate method, of class NetworkExplorerDTO.
     */
    @Test
    public void testGetListExtensionActivate() throws UnknownHostException {
        System.out.println("getListExtensionActivate");
         Workbook work = new Workbook(3, null);
        String[][] listExtensionActivate = new String[2][2];
        String[][] listExtensionDesactivate = new String[2][2];

        NetworkExplorerDTO instance = new NetworkExplorerDTO(InetAddress.getLocalHost(), work, listExtensionActivate, listExtensionDesactivate);
        String[][] expResult = listExtensionActivate;
        String[][] result = instance.getListExtensionDesactivate();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getWork method, of class NetworkExplorerDTO.
     */
    @Test
    public void testGetWork() throws UnknownHostException {
        System.out.println("getWork");
        Workbook work = new Workbook(3, null);
        String[][] listExtensionActivate = new String[2][2];
        String[][] listExtensionDesactivate = new String[2][2];

        NetworkExplorerDTO instance = new NetworkExplorerDTO(InetAddress.getLocalHost(), work, listExtensionActivate, listExtensionDesactivate);
    
        Workbook expResult = work;
        Workbook result = instance.getWork();
        assertEquals(expResult, result);

    }

}
