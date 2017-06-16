package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;

import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.ui.SearchWorkbookNetworkUIExtension;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.SearchWorkbookNetworkExtension;

/**
 * The Search workbook in the network Extension.
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class NetworkSearchExtension extends SearchWorkbookNetworkExtension {

    public NetworkSearchExtension() {
        super();
        setVersion(super.getVersion() + 1);
    }

    /**
     * Returns the user interface extension of this extension.
     *
     * @param uiController The user interface controller.
     * @return the user interface extension
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        return new SearchWorkbookNetworkUIExtension(this, uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Diogo Guedes in Sprint 3 and it is in the package %s\n"
                + "This extension was updated for a network extension",
                getName(), getVersion(), getDescription(), getClass().getName());
    }

}
