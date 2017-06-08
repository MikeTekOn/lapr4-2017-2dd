/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
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
        MacroWithName m = new MacroWithName("macro", "macro", new Workbook(1).getSpreadsheet(0));
        MacroList instance = new MacroList();
        instance.addMacro(m);
        boolean res = instance.addMacro(m);
        assertEquals(res, false);
    }

    /**
     * Test of removeMacro method, of class MacroList.
     */
    @Test
    public void testRemoveMacroWithoutExistence() {
        System.out.println("removeMacro");
        MacroList instance = new MacroList();
        assertEquals(instance.removeMacro("macroName"), false);
    }

    /**
     * Test of removeMacro method, of class MacroList.
     */
    @Test
    public void testRemoveMacroThatExists() {
        System.out.println("removeMacro");
        MacroWithName m = new MacroWithName("macro", "macro", new Workbook(1).getSpreadsheet(0));
        MacroList instance = new MacroList();
        instance.addMacro(m);
        instance.removeMacro("macro");
    }

    /**
     * Test of updateMacro method, of class MacroList.
     */
    @Test
    public void testUpdateMacro() {
        System.out.println("updateMacro");
        MacroWithName m1 = new MacroWithName("macro", "macro", new Workbook(1).getSpreadsheet(0));
        MacroWithName m2 = new MacroWithName("macro2", "macro", new Workbook(1).getSpreadsheet(0));
        MacroList instance = new MacroList();
        instance.addMacro(m1);
        instance.updateMacro("macro", "macro2", "macro", new Workbook(1).getSpreadsheet(0));
        instance.removeMacro("macro");
    }

}
