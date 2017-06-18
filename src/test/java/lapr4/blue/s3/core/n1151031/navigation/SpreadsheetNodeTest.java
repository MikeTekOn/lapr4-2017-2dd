package lapr4.blue.s3.core.n1151031.navigation;

import csheets.CleanSheets;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class SpreadsheetNodeTest {

    public SpreadsheetNodeTest() {
    }

    @Before
    public void setUp() {
    }

//    @Test
//    public void ensureCellListIsInCorrectOrder() throws IOException, FormulaCompilationException {
//        CleanSheets app = new CleanSheets();
//        UIController uiController = new UIController(app);
//        DefaultTreeModel treeModel = new DefaultTreeModel(new DefaultMutableTreeNode());
//        Workbook workbook = new Workbook();
//        workbook.addSpreadsheet();
//        
//        Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
//        //A1
//        spreadsheet.getCell(0, 0).setContent("10");
//        //A2
//        spreadsheet.getCell(0, 1).setContent("12");
//        //A3
//        spreadsheet.getCell(0, 2).setContent("14");
//
//        List<Cell> expected = new ArrayList();
//        expected.add(spreadsheet.getCell(0, 0));
//        expected.add(spreadsheet.getCell(0, 1));
//        expected.add(spreadsheet.getCell(0, 2));
//
//        SpreadsheetNode node = new SpreadsheetNode(spreadsheet, uiController, treeModel);
//
//        Assert.assertArrayEquals(node.getNonEmptyCells().toArray(), expected.toArray());
//    }

}
