package lapr4.blue.s2.ipc.n1151031.searchnetwork.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class SearchWorkbookNetworkUIExtension extends UIExtension {

    /**
     * A side bar that provides the search of workbooks in the network.
     */
    private JComponent sideBar;

    public SearchWorkbookNetworkUIExtension(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns an icon to display with the extension's name.
     *
     * @return an icon with style
     */
    public Icon getIcon() {
        return null;
    }

    /**
     * Returns a side bar that provides access to the search workbooks in the
     * network action.
     *
     * @return a side bar
     */
    @Override
    public JComponent getSideBar() {
        if (sideBar == null) {
            sideBar = new SearchWorkbookNetworkPanel(uiController, extension);
        }
        return sideBar;
    }
}
