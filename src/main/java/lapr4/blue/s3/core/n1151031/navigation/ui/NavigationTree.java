package lapr4.blue.s3.core.n1151031.navigation.ui;

import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import lapr4.blue.s3.core.n1151031.navigation.CustomDoubleClickNavigator;
import lapr4.blue.s3.core.n1151031.navigation.NavigationExpansionPopulator;
import lapr4.blue.s3.core.n1151031.navigation.NavigationTreeCellRenderer;
import lapr4.blue.s3.core.n1151031.navigation.RootNode;
import lapr4.blue.s3.core.n1151031.navigation.util.TreeState;

/**
 * A base-class for a navigation window.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class NavigationTree extends JTree implements SelectionListener {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * The data model that defines the tree
     */
    protected DefaultTreeModel treeModel;
    
    private TreeState treeState = TreeState.getInstance();

    /**
     * Creates a mew navigation tree.
     *
     * @param uiController the user interface controller
     */
    public NavigationTree(UIController uiController) {
        super(new DefaultTreeModel(null));

        // Stores members
        this.treeModel = (DefaultTreeModel) super.treeModel;
        this.uiController = uiController;
        uiController.addSelectionListener(this);

        // Creates and configures tree
        getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        setToggleClickCount(Integer.MAX_VALUE);
        addTreeExpansionListener(new NavigationExpansionPopulator());
        addMouseListener(new CustomDoubleClickNavigator(this, uiController));
        setCellRenderer(new NavigationTreeCellRenderer());
        ToolTipManager.sharedInstance().registerComponent(this);

    }

    /**
     * Invoked when the active workbook, spreadsheet and/or cell of the
     * application is changed.
     *
     * @param event the selection event that was fired
     */
    public void selectionChanged(SelectionEvent event) {
        String state = treeState.getExpansionState(this);
        RootNode node = new RootNode(uiController, treeModel);
        node.populate();
        treeModel.setRoot(node);
        treeState.setExpansionState(this, state);
//        for (int i = 0; i < this.getRowCount(); i++) {
//            this.expandRow(i);
//        }
    }
}
