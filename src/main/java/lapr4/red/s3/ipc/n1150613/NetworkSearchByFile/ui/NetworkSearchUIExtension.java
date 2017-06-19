package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class NetworkSearchUIExtension extends UIExtension {

    /**
     * A side bar that provides the search of workbooks in the network.
     */
    private JComponent sideBar;

    public NetworkSearchUIExtension(Extension extension, UIController uiController) {
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
            sideBar = new NetworkSearchPanel(uiController, extension);
        }
        return sideBar;
    }
}
