/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros;

import java.util.ArrayList;
import java.util.List;
import lapr4.blue.s1.lang.n1151159.macros.Macro;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo Santos
 */
public class MacroListTest {
    
    public MacroListTest() {
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
     * Test of addMacro method, of class MacroList.
     */
    @Test
    public void testAddMacroTwice() {
        System.out.println("addMacro");
        Macro m = new Macro(new ArrayList<>(), "macro");
        MacroList instance = new MacroList();
        instance.addMacro(m);
       boolean res =  instance.addMacro(m);
        assertEquals(res, false);
    }

    /**
     * Test of removeMacro method, of class MacroList.
     */
    @Test
    public void testRemoveMacroWithoutExistence() {
        System.out.println("removeMacro");
        Macro m = new Macro(new ArrayList<>(), "macro");
        MacroList instance = new MacroList();
        assertEquals(instance.removeMacro(m), false);
    }

    /**
     * Test of removeMacro method, of class MacroList.
     */
    @Test
    public void testRemoveMacroThatExists() {
        System.out.println("removeMacro");
        Macro m = new Macro(new ArrayList<>(), "macro");
        MacroList instance = new MacroList();
        instance.addMacro(m);
        instance.removeMacro(m);
    }
    
    /**
     * Test of updateMacro method, of class MacroList.
     */
    @Test
    public void testUpdateMacro() {
        System.out.println("updateMacro");
        Macro m1 = new Macro(new ArrayList<>(), "macro");
        Macro m2 = new Macro(new ArrayList<>(), "macro");
        MacroList instance = new MacroList();
        instance.addMacro(m1);
        instance.updateMacro(m1, m2);
        instance.removeMacro(m1);
    }
    
}
