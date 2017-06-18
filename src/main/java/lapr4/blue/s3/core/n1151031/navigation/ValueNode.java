package lapr4.blue.s3.core.n1151031.navigation;

import csheets.core.Value;
import javax.swing.tree.DefaultTreeModel;

/**
 * A tree node for a Value of a cell.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
class ValueNode extends NavigationNode {

    /**
     * Creates a new value node.
     *
     * @param treeModel the data model to which the node belongs
     */
    public ValueNode(Value value, DefaultTreeModel treeModel) {
        super(value, treeModel);
    }

    @Override
    protected void addChildren() {
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

}
