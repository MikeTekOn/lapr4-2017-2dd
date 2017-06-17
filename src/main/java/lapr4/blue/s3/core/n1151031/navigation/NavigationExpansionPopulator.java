package lapr4.blue.s3.core.n1151031.navigation;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * A tree expansion listener that asks the node being expanded to to add child
 * nodes for all its references.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class NavigationExpansionPopulator implements TreeExpansionListener {

    /**
     * Creates a new expansion populator.
     */
    public NavigationExpansionPopulator() {
    }

    /**
     * Populates the node that was expanded.
     *
     * @param event the event that was fired
     */
    public void treeExpanded(TreeExpansionEvent event) {
        TreePath path = event.getPath();
        TreeNode lastNode = (TreeNode) path.getLastPathComponent();
        if (lastNode instanceof NavigationNode) {
            // Populates the cell node
            NavigationNode node = (NavigationNode) lastNode;
            node.populate();
        }
    }

    /**
     * Does nothing.
     *
     * @param event the event that was fired
     */
    public void treeCollapsed(TreeExpansionEvent event) {
    }
}
