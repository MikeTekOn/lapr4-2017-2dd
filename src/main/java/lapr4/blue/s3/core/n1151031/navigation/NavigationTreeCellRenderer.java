package lapr4.blue.s3.core.n1151031.navigation;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.Formula;
import csheets.ui.ctrl.UIController;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * The renderer used for nodes in the navigation tree.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class NavigationTreeCellRenderer extends DefaultTreeCellRenderer {

    /**
     * An icon representing a node containing a formula
     */
    private Icon formulaIcon = new ImageIcon(
            NavigationTreeExtension.class.getResource("res/img/formula.png"));

    private Icon valueIcon = new ImageIcon(
            NavigationTreeExtension.class.getResource("res/img/value.png"));

    private Icon workbookIcon = new ImageIcon(
            NavigationTreeExtension.class.getResource("res/img/workbook.png"));

    private Icon spreadsheetIcon = new ImageIcon(
            NavigationTreeExtension.class.getResource("res/img/spreadsheet.png"));

    private Icon rootIcon = new ImageIcon(
            NavigationTreeExtension.class.getResource("res/img/root.gif"));

    private Icon cellIcon = new ImageIcon(
            NavigationTreeExtension.class.getResource("res/img/cell.gif"));

    /**
     * Creates a new dependency tree cell renderer
     */
    public NavigationTreeCellRenderer() {
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {

        if (value instanceof WorkbookNode) {
            // Selects the appropriate icon and tool tip to display
            Workbook workbook = (Workbook) ((WorkbookNode) value).getObject();
            Icon icon;
            if (workbook != null) {
                icon = workbookIcon;
                setOpenIcon(icon);
                setClosedIcon(icon);
                setLeafIcon(icon);
            }

        } else if (value instanceof SpreadsheetNode) {
            // Selects the appropriate icon and tool tip to display
            Spreadsheet spreadsheet = (Spreadsheet) ((SpreadsheetNode) value).getObject();
            Icon icon;
            if (spreadsheet != null) {
                icon = spreadsheetIcon;
                setOpenIcon(icon);
                setClosedIcon(icon);
                setLeafIcon(icon);
            }

        } else if (value instanceof RootNode) {
            // Selects the appropriate icon and tool tip to display
            UIController uiController = (UIController) ((RootNode) value).getObject();
            Icon icon;
            if (uiController != null) {
                icon = rootIcon;
                setOpenIcon(icon);
                setClosedIcon(icon);
                setLeafIcon(icon);
            }
            
        } else if (value instanceof FilledCellNode) {
            // Selects the appropriate icon and tool tip to display
            Cell cell = (Cell) ((FilledCellNode) value).getObject();
            Icon icon;
            if (cell != null) {
                icon = cellIcon;
                setOpenIcon(icon);
                setClosedIcon(icon);
                setLeafIcon(icon);
            }

        } else if (value instanceof FormulaNode) {
            // Selects the appropriate icon and tool tip to display
            Formula formula = (Formula) ((FormulaNode) value).getObject();
            if (formula != null) {
                Icon icon;
                icon = formulaIcon;
                setOpenIcon(icon);
                setClosedIcon(icon);
                setLeafIcon(icon);
            }

        } else if (value instanceof ValueNode) {
            Value valueCell = (Value) ((ValueNode) value).getObject();
            if (valueCell != null) {
                Icon icon;
                icon = valueIcon;
                setOpenIcon(icon);
                setClosedIcon(icon);
                setLeafIcon(icon);
            }

        }

        return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

    }
}
