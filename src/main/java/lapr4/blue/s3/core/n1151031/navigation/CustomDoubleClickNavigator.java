package lapr4.blue.s3.core.n1151031.navigation;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * A mouse listener that focus on a element (Workbook, Spreadsheet or Cell) when
 * a node is double-clicked.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class CustomDoubleClickNavigator extends MouseAdapter {

    /**
     * The tree to use when determining the node at a given location
     */
    private JTree tree;

    /**
     * The user interface controller
     */
    private UIController uiController;

    /**
     * Creates a new double click navigator.
     *
     * @param tree the tree to use when determining the node at a given location
     * @param uiController the user interface controller
     */
    public CustomDoubleClickNavigator(JTree tree, UIController uiController) {
        this.tree = tree;
        this.uiController = uiController;
    }

    /**
     * Sets the workbook, spreadsheet or cell of a double-clicked node as the
     * application's active element.
     *
     * @param event the event that was fired
     */
    public void mouseClicked(MouseEvent event) {
        if (SwingUtilities.isLeftMouseButton(event)
                && event.getClickCount() > 1) {
            // Fetches the path that was double-clicked
            int row = tree.getRowForLocation(event.getX(), event.getY());
            TreePath path = tree.getPathForRow(row);
            if (path != null) {
                TreeNode lastNode = (TreeNode) path.getLastPathComponent();
                if (lastNode instanceof WorkbookNode) {
                    //Sets the last node of the path as the active workbook
                    WorkbookNode node = (WorkbookNode) lastNode;
                    uiController.setActiveWorkbook((Workbook) node.getObject());
                } else if (lastNode instanceof SpreadsheetNode) {
                    // Sets the last node of the path as the active spreadsheet
                    SpreadsheetNode node = (SpreadsheetNode) lastNode;
                    uiController.setActiveSpreadsheet((Spreadsheet) node.getObject());
                } else if (lastNode instanceof FilledCellNode) {
                    // Sets the last node of the path as the active cell
                    FilledCellNode node = (FilledCellNode) lastNode;
                    uiController.setActiveCell((Cell) node.getObject());
                }
            }
        }
    }
}
