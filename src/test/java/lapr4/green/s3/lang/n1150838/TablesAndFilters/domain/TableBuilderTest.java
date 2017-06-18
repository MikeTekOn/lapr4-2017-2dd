/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.domain;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import java.util.ArrayList;
import java.util.List;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 1150838 Nuno Pinto
 */
public class TableBuilderTest {

//    private CleanSheets app;
//
//    public TableBuilderTest() {
//        // Try to create the CS application object
//        CleanSheets.setFlag(true);
//        app = new CleanSheets();
//
//        // This will create a workbook with 3 sheets
//        app.create();
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
//     * Test of build method, of class TableBuilder.
//     */
//    @Test
//    public void testBuild() {
//        System.out.println("build");
//
//        ArrayList<Cell> cellsToDefine = new ArrayList();
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)));
//        cellsToDefine.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)));
//        TableBuilder instance = new TableBuilder(cellsToDefine);
//        List<Header> row = new ArrayList();
//        Header header = new Header(0, app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1)));
//        row.add(header);
//        HeaderRow headerRow = new HeaderRow(row);
//        List<Cell> listCell = new ArrayList();
//        listCell.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2)));
//        DataRow dataRow = new DataRow(listCell);
//        CellRange cell = new CellRange((app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 1))),
//                app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 2))
//        );
//        List<Row> rows = new ArrayList();
//        rows.add(headerRow);
//        rows.add(dataRow);
//        Table expResult = new Table(cell, rows, "none");
//        Table result = instance.build();
//        assertEquals(expResult.getCells().size(), result.getCells().size());
//        
//        
//    }

}
