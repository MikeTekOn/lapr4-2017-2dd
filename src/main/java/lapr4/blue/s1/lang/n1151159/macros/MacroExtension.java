package lapr4.blue.s1.lang.n1151159.macros;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s1.lang.n1151159.macros.ui.UIMacroExtension;

/**
 * Represents an extension to run macros.
 * The macros syntax uses the first version of CleanSheet formulas defined at "Macro.g4".
 *
 * @author Ivo Ferro
 */
public class MacroExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Macros";

    /**
     * Creates a new macro extension.
     */
    public MacroExtension() {
        super(NAME);
    }

    /**
     * Returns the user interface for the macro extension.
     *
     * @param uiController the user interface controller
     * @return the UI of the macro extension
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new UIMacroExtension(this, uiController);
    }
}
