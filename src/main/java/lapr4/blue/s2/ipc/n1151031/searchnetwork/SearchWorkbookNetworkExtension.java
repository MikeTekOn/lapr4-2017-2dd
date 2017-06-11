package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.ui.SearchWorkbookNetworkUIExtension;

/**
 * The Search workbook in the network Extension.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class SearchWorkbookNetworkExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Workbook Network Search";

    public SearchWorkbookNetworkExtension() {
        super(NAME);
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

}
