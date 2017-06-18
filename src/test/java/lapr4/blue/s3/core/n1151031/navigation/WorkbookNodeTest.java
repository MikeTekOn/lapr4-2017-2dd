package lapr4.blue.s3.core.n1151031.navigation;

import csheets.CleanSheets;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
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
public class WorkbookNodeTest {

    public WorkbookNodeTest() {
    }

    @Before
    public void setUp() {
    }

//    @Test(expected = IllegalStateException.class)
//    public void ensureWorkbookNodeHasWorkbook() {
//        CleanSheets app = new CleanSheets();
//        UIController uiController = new UIController(app);
//        DefaultTreeModel treeModel = new DefaultTreeModel(new DefaultMutableTreeNode());
//        WorkbookNode node = new WorkbookNode(null, uiController, treeModel);
//    }

//    @Test
//    public void ensureSpreadsheetListIsInCorrectOrder() {
//        CleanSheets app = new CleanSheets();
//        UIController uiController = new UIController(app);
//        DefaultTreeModel treeModel = new DefaultTreeModel(new DefaultMutableTreeNode());
//        Workbook workbook = new Workbook();
//        workbook.addSpreadsheet();
//        workbook.addSpreadsheet();
//        workbook.addSpreadsheet();
//        workbook.getSpreadsheet(0).setTitle("ccc");
//        workbook.getSpreadsheet(1).setTitle("aaa");
//        workbook.getSpreadsheet(2).setTitle("bbb");
//        List<Spreadsheet> expected = new ArrayList();
//        expected.add(workbook.getSpreadsheet(1));
//        expected.add(workbook.getSpreadsheet(2));
//        expected.add(workbook.getSpreadsheet(0));
//        WorkbookNode node = new WorkbookNode(workbook, uiController, treeModel);
//
//        Assert.assertArrayEquals(node.getSpreadsheets().toArray(), expected.toArray());
//    }
}
