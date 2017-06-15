package lapr4.green.s3.lang.n1150532.variables.globalVariablesExtension;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * The global variables extension. It provides a list with the existing global
 * variables and allows their edition.
 *
 * @author Manuel Meireles (1150532)
 */
public class GlobalVariablesExtension extends Extension {

    /**
     * The name of the extension.
     */
    public static final String NAME = "Global Variables";

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
    public GlobalVariablesExtension() {
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
        return new GlobalVariablesExtensionUI(this, uiController);
    }

    /**
     * It provides the extension meta-data.
     *
     * @return It returns the extension's description.
     */
    @Override
    public String metadata() {
        return DESCRIPTION;
    }

}
