package lapr4.blue.s3.core.n1151031.navigation;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.tree.DefaultTreeModel;

/**
 * A tree node for a Workbook, to which the workbook's spreadsheets are added
 * dynamically when the node is expanded.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class WorkbookNode extends NavigationNode {

    /**
     * The UIController of the current instance of CleanSheets.
     */
    private final UIController uIController;

    /**
     * The workbook of the node.
     */
    private final Workbook workbook;

    /**
     * The spreadsheets of the node's workbook.
     */
    private final SortedSet<Spreadsheet> spreadsheets = new TreeSet<Spreadsheet>(new Comparator<Spreadsheet>() {
        @Override
        public int compare(Spreadsheet t, Spreadsheet t1) {
            return t.getTitle().compareTo(t1.getTitle());
        }
    });

    /**
     * Creates a new workbook node.
     *
     * @param workbook the received workbook
     * @param uiController the received uiController
     * @param treeModel the data model to which the node belongs
     */
    public WorkbookNode(Workbook workbook, UIController uiController, DefaultTreeModel treeModel) throws IllegalStateException {
        super(workbook, treeModel);
        if (workbook == null) {
            throw new IllegalStateException("WorkbookNode could not be created!");
        }
        this.workbook = workbook;
        this.uIController = uiController;

        for (int i = 0; i < workbook.getSpreadsheetCount(); i++) {
            spreadsheets.add(workbook.getSpreadsheet(i));
        }
    }
    
    /**
     * Returns the spreadsheet list.
     * 
     * @return the spreadsheet list
     */
    public SortedSet<Spreadsheet> getSpreadsheets(){
        return spreadsheets;
    }

    @Override
    protected void addChildren() {
        for (Spreadsheet spreadsheet : spreadsheets) {
            add(new SpreadsheetNode(spreadsheet, uIController, treeModel));
        }
    }

    /**
     * Returns whether the workbook has references.
     *
     * @return true if the workbook has references
     */
    @Override
    public boolean isLeaf() {
        return spreadsheets.isEmpty();
    }

    @Override
    public String toString() {
        if (uIController.getFile(workbook) != null) {
            String name = uIController.getFile(workbook).getName();
            //removes the extension from the name
            return name.substring(0, name.lastIndexOf("."));
        }
        //the default name for a workbook that is not saved
        return "Untitled";
    }
}
