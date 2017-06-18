package lapr4.blue.s3.core.n1151031.navigation;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * A generic tree node for an Object, to which child nodes are added dynamically
 * when the node is expanded.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public abstract class NavigationNode extends DefaultMutableTreeNode {

    /**
     * The Object of the node.
     */
    private Object object;

    /**
     * If child nodes for the node's references have been added.
     */
    private boolean populated = false;

    /**
     * The data model to which the node belongs.
     */
    protected DefaultTreeModel treeModel;

    /**
     * Creates a new Navigation node.
     *
     * @param object the received object type
     * @param treeModel the data model to which the node belongs
     */
    public NavigationNode(Object object, DefaultTreeModel treeModel) {
        super(object);

        // Stores members
        this.object = object;
        this.treeModel = treeModel;
    }

    /**
     * Returns the Object of the node.
     *
     * @return the Object of the node.
     */
    public Object getObject() {
        return object;
    }

    /**
     * Populates the cell node by adding child nodes for all its references.
     */
    public void populate() {
        if (!populated) {
            populated = true;
            addChildren();
            treeModel.nodeStructureChanged(this);
        }
    }

    /**
     * Adds children to the node. Invoked once, when the node is populated.
     */
    protected abstract void addChildren();
}
