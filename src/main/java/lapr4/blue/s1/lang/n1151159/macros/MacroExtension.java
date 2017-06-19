package lapr4.blue.s1.lang.n1151159.macros;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s1.lang.n1151159.macros.ui.UIMacroExtension;

/**
 * Represents an extension to run macros. The macros syntax uses the first
 * version of CleanSheet formulas defined at "Macro2.g4".
 *
 * @author Ivo Ferro
 */
public class MacroExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Macros";
    
    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "Represents an extension to run macros.\n"
            + "The macros syntax uses the first version of CleanSheet formulas defined at \"Macro2.g4\".";
    
    /**
     * The first version of the macro extension.
     */
    public static final int VERSION = 1;

    /**
     * Creates a new macro extension.
     */
    public MacroExtension() {
        super(NAME, VERSION, DESCRIPTION);
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

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Ivo Ferro in Sprint 1 and it is in the package %s",
                getName(), getVersion(), getDescription(),getClass().getName());
    }
}
