package lapr4.blue.s3.core.n1151031.navigation;

import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.tree.DefaultTreeModel;

/**
 * A tree node for a CleanSheet app, to which the active Workbooks are added
 * dynamically. Works as a root node.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class RootNode extends NavigationNode {

    /**
     * The uiController of the CleanSheets instance.
     */
    private UIController uiController;

    /**
     * The active workbooks of the cleansheets node.
     */
    private final SortedSet<Workbook> activeWorkbooks = new TreeSet<Workbook>(new Comparator<Workbook>() {
        @Override
        public int compare(Workbook wb1, Workbook wb2) {
            String name1;
            String name2;

            if (uiController.getFile(wb1) != null) {
                name1 = uiController.getFile(wb1).getName();
            } else {
                name1 = "Untitled";
            }

            if (uiController.getFile(wb2) != null) {
                name2 = uiController.getFile(wb2).getName();
            } else {
                name2 = "Untitled2";
            }
            return name1.compareTo(name2);
        }
    });

    /**
     * Creates a new cleansheets node.
     *
     * @param uiController the received uiController
     * @param treeModel the data model to which the node belongs
     */
    public RootNode(UIController uiController, DefaultTreeModel treeModel) {
        super(uiController, treeModel);

        this.uiController = uiController;

        for (Workbook workbook : uiController.getActiveWorkbooks()) {
            activeWorkbooks.add(workbook);
        }
    }
    
    public SortedSet<Workbook> getActiveWorkbooks() {
        return activeWorkbooks;
    }

    @Override
    protected void addChildren() {
        for (Workbook workbook : activeWorkbooks) {
            add(new WorkbookNode(workbook, uiController, treeModel));
        }
    }

    @Override
    public boolean isLeaf() {
        return activeWorkbooks.isEmpty();
    }

    @Override
    public String toString() {
        //navigation window root name
        return "Workbooks";
    }
}
