/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;

import lapr4.red.s2.lang.n1150385.beanshell.Instruction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class BeanShellLoaderTest {

   @Rule public TemporaryFolder folder= new TemporaryFolder();
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
        UIController controller = new UIController(new CleanSheets());
        System.out.println(" ensureBeanShellClassInstanceIsBuiltCorrectly");
        String code = "print(\"test\");\n";
        
      
        BeanShellLoader instance = new BeanShellLoader();
        LinkedList<Instruction> list = new LinkedList<>();
        list.add(new Instruction("print(\"test\");", Instruction.Type.BEANSHELL));
        BeanShellInstance expResult = new BeanShellInstance(list, controller, null);
        BeanShellInstance result = instance.create(code, controller, null);

        assertEquals(expResult, result);

    }

    /**
     * Test of create method, of class BeanShellLoader.
     */
    @Test(expected = IllegalStateException.class)
    public void ensureBeanShellClassInstanceIsNotBuiltIfNoCode() throws Exception {
        UIController controller = new UIController(new CleanSheets());
        System.out.println(" ensureBeanShellClassInstanceIsBuiltCorrectly");
        String code = "";
     
        BeanShellLoader instance = new BeanShellLoader();
        LinkedList<Instruction> list = new LinkedList<>();
        list.add(new Instruction("print(\"test\");", Instruction.Type.BEANSHELL));
        BeanShellInstance expResult = new BeanShellInstance(list, controller, null);
        BeanShellInstance result = instance.create(code, controller, null);
        assertEquals(expResult, result);

    }

}
