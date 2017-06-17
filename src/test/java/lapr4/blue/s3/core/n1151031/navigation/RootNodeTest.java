
package lapr4.blue.s3.core.n1151031.navigation;

import csheets.CleanSheets;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.io.File;
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
public class RootNodeTest {

    public RootNodeTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void ensureActiveWorkbookListIsInCorrectOrder() throws IOException {
        CleanSheets app = new CleanSheets();
        UIController uiController = new UIController(app);
        DefaultTreeModel treeModel = new DefaultTreeModel(new DefaultMutableTreeNode());
        Workbook workbook1 = new Workbook();
        Workbook workbook2 = new Workbook();
        Workbook workbook3 = new Workbook();

        File file1 = new File("aaa");
        File file2 = new File("bbb");
        File file3 = new File("ccc");
        app.saveAs(workbook1, file1);
        app.saveAs(workbook2, file2);
        app.saveAs(workbook3, file3);

        uiController.setActiveWorkbook(workbook3);
        uiController.setActiveWorkbook(workbook2);
        uiController.setActiveWorkbook(workbook1);

        List<Workbook> expected = new ArrayList();
        expected.add(workbook1);
        expected.add(workbook2);
        expected.add(workbook3);

        RootNode node = new RootNode(uiController, treeModel);

        Assert.assertArrayEquals(node.getActiveWorkbooks().toArray(), expected.toArray());
    }

}
