package lapr4.blue.s3.core.n1151031.navigation;

import csheets.core.Cell;
import csheets.core.Value;
import csheets.core.formula.Formula;
import javax.swing.tree.DefaultTreeModel;

/**
 * A tree node for a non empty cell, to which the cell's value and formula are
 * added dynamically when the node is expanded.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class FilledCellNode extends NavigationNode {

    /**
     * The cell's formula.
     */
    private Formula formula;

    /**
     * The cell's value.
     */
    private Value value;

    /**
     * Creates a new non empty cell node.
     *
     * @param cell the received cell
     * @param treeModel the data model to which the node belongs
     */
    public FilledCellNode(Cell cell, DefaultTreeModel treeModel) {
        super(cell, treeModel);

        if (cell.getFormula() != null) {
            formula = cell.getFormula();
        }
        if (cell.getValue() != null) {
            value = cell.getValue();
        }
    }

    @Override
    protected void addChildren() {
        if (formula != null) {
            add(new FormulaNode(formula, treeModel));
        }
        if (value != null) {
            add(new ValueNode(value, treeModel));
        }
    }

    @Override
    public boolean isLeaf() {
        return formula == null && value == null;
    }

}
