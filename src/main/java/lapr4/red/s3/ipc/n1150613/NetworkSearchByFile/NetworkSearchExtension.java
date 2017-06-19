package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.red.s3.ipc.n1150613.NetworkSearchByFile.ui.NetworkSearchUIExtension;

/**
 * The Search workbook in the network Extension.
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class NetworkSearchExtension extends Extension {

    /**
     * The name of the extension.
     */
    public static final String NAME = "Network Searches";

    /**
     * The extension description.
     */
    public static final String DESCRIPTION = "The global variables extension. It lists all existing global variables and allows their edition.";

    /**
     * The extension current version.
     */
    public static final int VERSION = 1;

    /**
     * The full constructor of the extension.
     */
    public NetworkSearchExtension() {
        super(NAME, VERSION, DESCRIPTION);
    }

    /**
     * It provides the UI Extension to render.
     *
     * @param uiController The UI Controller.
     * @return The UI Extension.
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        return new NetworkSearchUIExtension(this, uiController);
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
