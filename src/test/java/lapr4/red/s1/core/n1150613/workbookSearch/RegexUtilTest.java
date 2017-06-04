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
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo
 */
public class RegexUtilTest {

    String validRegex = "[A-Z]+";
    String invalidRegex = "[A-++w2";
    String cellContent = "";

    @Before
    public void setUp() throws FormulaCompilationException {
        Workbook book = new Workbook();
        book.addSpreadsheet();
        Spreadsheet s = book.getSpreadsheet(0);
        Cell c = s.getCell(1, 1);
        c.setContent("TESTE");
        cellContent = c.getContent();

    }

    /**
     * Test of isRegexValid method, of class RegexUtil.
     */
    @Test
    public void testIsRegexValid() {
        System.out.println("isRegexValid");
        RegexUtil instance = new RegexUtil(validRegex);
        boolean expResult = true;
        boolean result = instance.isRegexValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of isNotRegexValid method, of class RegexUtil.
     */
    @Test
    public void testIsNotRegexValid() {
        System.out.println("isNotRegexValid");
        RegexUtil instance = new RegexUtil(invalidRegex);
        boolean expResult = false;
        boolean result = instance.isRegexValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfMatches method, of class RegexUtil.
     */
    @Test
    public void testCheckIfMatches() {
        System.out.println("checkIfMatches");

        RegexUtil instance = new RegexUtil(validRegex);
        boolean expResult = true;
        boolean result = instance.checkIfMatches(cellContent);
        assertEquals(expResult, result);

    }

    /**
     * Test of checkifRegexMatches method, of class RegexUtil.
     */
    @Test
    public void testSearchInWorkbook() throws FormulaCompilationException {
        System.out.println("checkifRegexMatches");
        Workbook w = new Workbook();
        w.addSpreadsheet();
        Spreadsheet s = w.getSpreadsheet(0);
        Cell c =s.getCell(0, 0);
        c.setContent("TESTADO");
        String regex = "[A-Z]+";
        RegexUtil instance = new RegexUtil(regex);
        String teste = "TESTADO Spreadsheet:1 Adress:A1";
        List<String> expResult = new ArrayList<>();
        expResult.add(teste);
        List<String> result = instance.searchInWorkbook(w);
        assertEquals(expResult, result);

    }

}
