package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.ui.SearchWorkbookNetworkUIExtension;
import lapr4.green.s1.ipc.n1150901.search.workbook.SearchWorkbookExtension;

/**
 * The Search workbook in the network Extension.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class SearchWorkbookNetworkExtension extends SearchWorkbookExtension {

    public SearchWorkbookNetworkExtension() {
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
                + "This extension was made by Tiago Correia in Sprint 2 and it is in the package %s\n"
                + "This extension was updated for a network extension",
                getName(), getVersion(), getDescription(), getClass().getName());
    }

}
