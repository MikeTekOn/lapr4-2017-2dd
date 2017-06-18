/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.application;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.List;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.DataRow;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Row;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Table;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.exception.InvalidTableException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class TableAndFiltersControllerTest {

//    private CleanSheets app;
//    private UIController ui;
//    private TableAndFiltersController instance;
//
//    public TableAndFiltersControllerTest() {
//        // Try to create the CS application object
//        CleanSheets.setFlag(true);
//        app = new CleanSheets();
//
//        // This will create a workbook with 3 sheets
//        app.create();
//        ui = new UIController(app);
//
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of defineTable method, of class TableAndFiltersController.
//     */
//    @Test
//    public void testDefineTable() {
//        System.out.println("defineTable");
//        instance = new TableAndFiltersController(ui);
//        ArrayList<Cell> cellsToDefine = new ArrayList();
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)));
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)));
//        instance.setSelectedCells(cellsToDefine);
//        ui.setActiveSpreadsheet(app.getWorkbooks()[0].getSpreadsheet(0));
//        boolean expResult = true;
//        boolean result = instance.defineTable();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of isAvailableToDefine method, of class TableAndFiltersController.
//     */
//    @Test(expected = InvalidTableException.class)
//    public void testIsAvailableToDefine() throws InvalidTableException {
//        System.out.println("isAvailableToDefine");
//        instance = new TableAndFiltersController(ui);
//        ArrayList<Cell> cellsToDefine = new ArrayList();
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)));
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)));
//        instance.setSelectedCells(cellsToDefine);
//        ui.setActiveSpreadsheet(app.getWorkbooks()[0].getSpreadsheet(0));
//        boolean expResult = true;
//        boolean result = instance.isAvailableToDefine();
//        assertEquals(expResult, result);
//        instance.defineTable();
//        expResult = false;
//        result = instance.isAvailableToDefine();
//        assertEquals(expResult, result);
//        cellsToDefine = new ArrayList();
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)));
//        instance.setSelectedCells(cellsToDefine);
//        instance.isAvailableToDefine();
//
//    }
//
//    /**
//     * Test of isDefined method, of class TableAndFiltersController.
//     */
//    @Test
//    public void testIsDefined() {
//        System.out.println("isDefined");
//        Cell cell = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1));
//        instance = new TableAndFiltersController(ui);
//        ArrayList<Cell> cellsToDefine = new ArrayList();
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)));
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)));
//        instance.setSelectedCells(cellsToDefine);
//        ui.setActiveSpreadsheet(app.getWorkbooks()[0].getSpreadsheet(0));
//        CellRange expResult = null;
//        CellRange result = instance.isDefined(cell);
//        assertEquals(expResult, result);
//        instance.defineTable();
//        expResult = new CellRange(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)),
//                app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)));
//        result = instance.isDefined(cell);
//        assertEquals(expResult.getFirstCell().getAddress(), result.getFirstCell().getAddress());
//        assertEquals(expResult.getLastCell().getAddress(), result.getLastCell().getAddress());
//
//    }
//
//    /**
//     * Test of activeTables method, of class TableAndFiltersController.
//     */
//    @Test
//    public void testActiveTables() {
//        System.out.println("activeTables");
//        instance = new TableAndFiltersController(ui);
//        ArrayList<Cell> cellsToDefine = new ArrayList();
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)));
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)));
//        instance.setSelectedCells(cellsToDefine);
//        ui.setActiveSpreadsheet(app.getWorkbooks()[0].getSpreadsheet(0));
//        List<Table> expResult = new ArrayList();
//        List<Table> result = instance.activeTables();
//        assertEquals(expResult, result);
//        instance.defineTable();
//        result = instance.activeTables();
//        expResult.add(instance.getTable());
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of removeTable method, of class TableAndFiltersController.
//     */
//    @Test
//    public void testRemoveTable() {
//        System.out.println("removeTable");
//        Table d = null;
//        instance = new TableAndFiltersController(ui);
//        ArrayList<Cell> cellsToDefine = new ArrayList();
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)));
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)));
//        instance.setSelectedCells(cellsToDefine);
//        ui.setActiveSpreadsheet(app.getWorkbooks()[0].getSpreadsheet(0));
//        boolean expResult = false;
//        boolean result = instance.removeTable(d);
//        assertEquals(expResult, result);
//        instance.defineTable();
//        d = instance.getTable();
//        expResult = true;
//        result = instance.removeTable(d);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of verifyFormula method, of class TableAndFiltersController.
//     */
//    @Test(expected=IllegalValueTypeException.class)
//    public void testVerifyFormula() throws Exception {
//        System.out.println("verifyFormula");
//        Table d = null;
//        instance = new TableAndFiltersController(ui);
//        ArrayList<Cell> cellsToDefine = new ArrayList();
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)));
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)));
//        app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)).setContent("3");
//        instance.setSelectedCells(cellsToDefine);
//        ui.setActiveSpreadsheet(app.getWorkbooks()[0].getSpreadsheet(0));
//        instance.defineTable();
//        d = instance.getTable();
//        String formula = "=&COL[1]>2";
//        List<Row> expResult = new ArrayList();
//        List<Row> result = instance.verifyFormula(d, formula);
//        assertEquals(expResult, result);
//        instance.verifyFormula(d, "=2+2");
//    }

}
