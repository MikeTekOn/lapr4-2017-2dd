/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.util;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.CellImpl;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.List;
import lapr4.green.s2.core.n1150838.GlobalSearch.domain.Filter;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class RegexUtilExtendedTest {

    private static RegexUtilExtended instance;
    private static UIController ctrl;
    private static CleanSheets app;
    private static Spreadsheet spreadsheet;
    private static List<String> types;
    private static List<String> formulas;

    public RegexUtilExtendedTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        types = new ArrayList();
        formulas = new ArrayList();
        boolean isCommentsToInclude = false;
        app = new CleanSheets();
        app.create();
//        ctrl = new UIController(app);
        Filter f = new Filter(types, formulas, isCommentsToInclude);
        instance = new RegexUtilExtended(f, ".*2.*", ctrl);
        spreadsheet = app.getWorkbooks()[0].getSpreadsheet(0);

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
     * Test of checkIfMatches method, of class RegexUtilExtended.
     */
    @Test
    public void testCheckIfMatches() throws FormulaCompilationException {
        System.out.println("checkIfMatches");
        Cell cell = spreadsheet.getCell(new Address(1, 1));
        cell.setContent("2");
        boolean expResult = true;
        boolean result = instance.checkIfMatches(cell);
        assertEquals(expResult, result);
        //case false
        cell.setContent("333");
        expResult = false;
        result = instance.checkIfMatches(cell);
        //case has formulas
        formulas.add("=2");
        cell.setContent("=2");
        expResult = true;
        result = instance.checkIfMatches(cell);
        //case hasnt the formulas
        cell.setContent("=3");
        expResult = false;
        result = instance.checkIfMatches(cell);

    }

    /**
     * Test of containsFormulas method, of class RegexUtilExtended.
     */
    @Test
    public void testContainsFormulas() throws FormulaCompilationException {
        System.out.println("containsFormulas");
        //case dont contain
        types = new ArrayList();
        formulas = new ArrayList();
        Cell cell = spreadsheet.getCell(new Address(1, 1));
        cell.setContent("=2");
        Filter f = new Filter(types, formulas, false);
        instance = new RegexUtilExtended(f, ".*2.*", ctrl);
        boolean result = instance.containsFormulas(cell, formulas);
        boolean expResult = false;
        assertEquals(expResult, result);
        //case contain
        types = new ArrayList();
        formulas = new ArrayList();
        formulas.add("=2");
        f = new Filter(types, formulas, false);
        instance = new RegexUtilExtended(f, ".*2.*", ctrl);
        result = instance.containsFormulas(cell, formulas);
        expResult = true;
        assertEquals(expResult, result);

    }

    /**
     * Test of validateFilters method, of class RegexUtilExtended.
     */
    @Test
    public void testValidateFilters() throws FormulaCompilationException {
        System.out.println("validateFilters");
        //case true
        types = new ArrayList();
        formulas = new ArrayList();
        Cell cell = spreadsheet.getCell(new Address(1, 1));
        cell.setContent("=2");
        Filter f = new Filter(types, formulas, false);
        instance = new RegexUtilExtended(f, ".*2.*", ctrl);
        boolean result = instance.validateFilters(cell, types, formulas);
        boolean expResult = true;
        assertEquals(expResult, result);
        //case false
        types = new ArrayList();
        types.add("TESTE");
        formulas = new ArrayList();
        cell.setContent("=2");
        f = new Filter(types, formulas, false);
        instance = new RegexUtilExtended(f, ".*2.*", ctrl);
        result = instance.validateFilters(cell, types, formulas);
        expResult = false;
        assertEquals(expResult, result);

    }

    /**
     * Test of validateType method, of class RegexUtilExtended.
     */
    @Test
    public void testValidateType() throws FormulaCompilationException {
        //case false
        System.out.println("validateType");
        types = new ArrayList();
        formulas = new ArrayList();
        Cell cell = spreadsheet.getCell(new Address(1, 1));
        cell.setContent("=2");
        Filter f = new Filter(types, formulas, false);
        instance = new RegexUtilExtended(f, ".*2.*", ctrl);
        boolean expResult = false;
        boolean result = instance.validateType(cell, types);
        types.add("NUMERIC");
        f = new Filter(types, formulas, false);
        instance = new RegexUtilExtended(f, ".*2.*", ctrl);
        expResult = true;
        result = instance.validateType(cell, types);
        assertEquals(expResult, result);

    }

    /**
     * Test of validateFormulas method, of class RegexUtilExtended.
     */
    @Test
    public void testValidateFormulas() throws FormulaCompilationException {
        System.out.println("validateFormulas");
        // case false
        types = new ArrayList();
        formulas = new ArrayList();
        Cell cell = spreadsheet.getCell(new Address(1, 1));
        cell.setContent("=2");
        Filter f = new Filter(types, formulas, false);
        instance = new RegexUtilExtended(f, ".*2.*", ctrl);
        boolean expResult = false;
        boolean result = instance.validateFormulas(cell, formulas);
        assertEquals(expResult, result);
        //case true
        types = new ArrayList();
        formulas = new ArrayList();
        formulas.add("=2");
        cell = spreadsheet.getCell(new Address(1, 1));
        cell.setContent("2");
        f = new Filter(types, formulas, false);
        instance = new RegexUtilExtended(f, ".*2.*", ctrl);
        expResult = false;
        result = instance.validateFormulas(cell, formulas);
        assertEquals(expResult, result);

    }

}
