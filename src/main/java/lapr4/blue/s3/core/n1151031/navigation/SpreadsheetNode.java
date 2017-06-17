package lapr4.blue.s3.core.n1151031.navigation;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ui.ctrl.EditEvent;
import csheets.ui.ctrl.EditListener;
import csheets.ui.ctrl.UIController;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.tree.DefaultTreeModel;

/**
 * A tree node for a Spreadsheet, to which the non empty cells are added
 * dynamically when the node is expanded.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
class SpreadsheetNode extends NavigationNode implements EditListener {

    /**
     * The UIController of the current instance of CleanSheets.
     */
    private final UIController uiController;

    /**
     * The non empty cells of the spreadsheet's node.
     */
    private final SortedSet<Cell> nonEmptyCells = new TreeSet<Cell>(new Comparator<Cell>() {
        @Override
        public int compare(Cell c1, Cell c2) {
            return c1.getAddress().toString().compareTo(c2.getAddress().toString());
        }
    });

    /**
     * Creates a new spreadsheet node.
     *
     * @param treeModel the data model to which the node belongs
     */
    public SpreadsheetNode(Spreadsheet spreadsheet, UIController uiController, DefaultTreeModel treeModel) {
        super(spreadsheet, treeModel);
        this.uiController = uiController;
        //fills the list with the cells that have content
        Iterator<Cell> it = spreadsheet.iterator();
        Cell cell;
        while (it.hasNext()) {
            cell = it.next();
            if (!cell.getContent().isEmpty()) {
                nonEmptyCells.add(cell);
            }
        }
        this.uiController.addEditListener(this);

    }

    @Override
    protected void addChildren() {
        for (Cell cell : nonEmptyCells) {
            add(new FilledCellNode(cell, treeModel));
        }
    }

    @Override
    public boolean isLeaf() {
        return nonEmptyCells.isEmpty();
    }

    @Override
    public String toString() {
        Object userObject = this.getUserObject();
        if (userObject instanceof Spreadsheet) {
            return ((Spreadsheet) userObject).getTitle();
        } else {
            return super.toString();
        }
    }

    /**
     * Invoked when a workbook is modified. param event the edit event that was
     * fired
     *
     * @param event event
     */
    public void workbookModified(EditEvent event) {
        populate();
    }

}
