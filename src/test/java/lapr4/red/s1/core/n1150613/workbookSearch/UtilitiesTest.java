/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo
 */
public class UtilitiesTest {

    String validRegex = "";
    String invalidRegex = "";
    String cellContent = "";

    @Before
    public void setUp() throws FormulaCompilationException {
        Workbook book = new Workbook();
        book.addSpreadsheet();
        Spreadsheet s = book.getSpreadsheet(0);
        Cell c = s.getCell(1, 1);
        c.setContent("teste");
        cellContent = c.getContent();

    }

    /**
     * Test of isRegexValid method, of class Utilities.
     */
    @Test
    public void testIsRegexValid() {
        System.out.println("isRegexValid");
        String regex = "";
        Utilities instance = new Utilities();
        boolean expResult = false;
        boolean result = instance.isRegexValid(regex);
        assertEquals(expResult, result);
    }

    /**
     * Test of isRegexValid method, of class Utilities.
     */
    @Test
    public void testIsNotRegexValid() {
        System.out.println("isRegexValid");
        String regex = "";
        Utilities instance = new Utilities();
        boolean expResult = false;
        boolean result = instance.isRegexValid(regex);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfMatches method, of class Utilities.
     */
    @Test
    public void testCheckIfMatches() {
        System.out.println("checkIfMatches");

        Utilities instance = new Utilities();
        boolean expResult = false;
        boolean result = instance.checkIfMatches(cellContent);
        assertEquals(expResult, result);

    }

}
