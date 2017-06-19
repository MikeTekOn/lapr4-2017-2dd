package lapr4.blue.s3.core.n1151031.navigation.util;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

/**
 * A singleton class that saves and sets the expanded state of a JTree.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class TreeState {

    /**
     * The string builder that builds the node path.
     */
    private StringBuilder stringBuilder;

    /**
     * The singleton instance.
     */
    private static TreeState instance = new TreeState();

    /**
     * A private Constructor that prevents any other class from instantiating
     * the TreeState singleton.
     */
    private TreeState() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Returns the TreeState instance.
     */
    public static TreeState getInstance() {
        return instance;
    }

    /**
     * Returns the expanded state of a JTree.
     *
     * @param tree the tree that we want to save the expanded state.
     * @return the expanded state
     */
    public String getExpansionState(JTree tree) {

        stringBuilder = new StringBuilder();

        for (int i = 0; i < tree.getRowCount(); i++) {
            TreePath tp = tree.getPathForRow(i);
            if (tree.isExpanded(i)) {
                stringBuilder.append(tp.toString());
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Sets the expanded state of the JTree.
     *
     * @param tree the tree that receives the state.
     * @param s the state
     */
    public void setExpansionState(JTree tree, String s) {
        for (int i = 0; i < tree.getRowCount(); i++) {
            TreePath tp = tree.getPathForRow(i);
            if (s.contains(tp.toString())) {
                tree.expandRow(i);
            }
        }
    }
}
