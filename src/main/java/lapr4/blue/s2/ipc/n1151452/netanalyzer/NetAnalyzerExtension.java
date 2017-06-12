package lapr4.blue.s2.ipc.n1151452.netanalyzer;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.ui.UIExtensionNetAnalyzer;

/**
 * An extension to visualize the network analyzer.
 *
 * @see Extension
 * @author Einar Pehrson
 * @author Daniel Gonçalves [1151452@isep.ipp.pt] on 10/06/17.
 */
public class NetAnalyzerExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Network Analyzer";

    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "An extension to visualize the network analyzer.";

    /**
     * The first version of the analyzer extension.
     */
    public static final int VERSION = 1;

    /**
     * Creates a new extension.
     */
    public NetAnalyzerExtension() {
        super(NAME, VERSION, DESCRIPTION);
    }

    /**
     * Returns the user interface extension of this extension
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new UIExtensionNetAnalyzer(this, uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Daniel Gonçalves in Sprint 2 and it is in the package %s",
                getName(), getVersion(), getDescription(), getClass().getName());
    }
}
