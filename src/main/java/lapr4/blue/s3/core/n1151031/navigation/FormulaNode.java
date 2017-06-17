package lapr4.blue.s3.core.n1151031.navigation;

import csheets.core.formula.Formula;
import javax.swing.tree.DefaultTreeModel;

/**
 * A tree node for a Formula of a cell.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
class FormulaNode extends NavigationNode {

    /**
     * Creates a new formula node.
     *
     * @param treeModel the data model to which the node belongs
     */
    public FormulaNode(Formula formula, DefaultTreeModel treeModel) {
        super(formula, treeModel);
    }

    @Override
    protected void addChildren() {
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

}
