/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1140925.fileShare;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author João
 */
public class ShareFileTest {

    /**
     * Test of toString method, of class ShareFile.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ShareFile instance = new ShareFile("teste1.txt", 300);
        String expResult = "Name: teste1.txt Size: 300KBytes";
        String result = instance.toString();
        assertEquals(expResult, result);
    }


}
