/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class BeanShellLoaderTest {

    public BeanShellLoaderTest() {
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
     * Test of create method, of class BeanShellLoader.
     */
    @Test
    public void ensureBeanShellClassInstanceIsBuiltCorrectly() throws Exception {
        System.out.println(" ensureBeanShellClassInstanceIsBuiltCorrectly");
        String code = "print(\"test\");";
        File file = new File("fileTest.bsh");
        FileOutputStream fileOut = new FileOutputStream(file);
        fileOut.write(code.getBytes());
        fileOut.close();
        String scriptName = "fileTest.bsh";
        BeanShellLoader instance = new BeanShellLoader();
        LinkedList<String> list = new LinkedList<>();
        list.add("print(\"test\");");
        BeanShellInstance expResult = new BeanShellInstance(list);
        BeanShellInstance result = instance.create(scriptName);
        assertEquals(expResult, result);
        file.delete();
        file.deleteOnExit();
    }

    /**
     * Test of create method, of class BeanShellLoader.
     */
    @Test(expected = IllegalStateException.class)
    public void ensureBeanShellClassInstanceIsNotBuiltIfNoCode() throws Exception {
        System.out.println(" ensureBeanShellClassInstanceIsBuiltCorrectly");
        String code = "";
        File file2 = new File("fileTest2.bsh");
        FileOutputStream fileOut = new FileOutputStream(file2);
        fileOut.write(code.getBytes());
        fileOut.close();
        String scriptName = "fileTest2.bsh";
        BeanShellLoader instance = new BeanShellLoader();
        LinkedList<String> list = new LinkedList<>();
        list.add("print(\"test\");");
        BeanShellInstance expResult = new BeanShellInstance(list);
        BeanShellInstance result = instance.create(scriptName);
        assertEquals(expResult, result);
        file2.delete();
         file2.deleteOnExit();
    }

}
