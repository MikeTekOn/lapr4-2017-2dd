package csheets.ext.simple;

import csheets.ext.Extension;
import csheets.ext.simple.ui.UIExtensionExample;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * A simple extension just to show how the extension mechanism works. An
 * extension must extend the Extension abstract class. The class that implements
 * the Extension is the "bootstrap" of the extension.
 *
 * @see Extension
 * @author Alexandre Braganca
 */
public class ExtensionExample extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Example";
    
    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = " A simple extension just to show how the extension mechanism works. "
            + "An extension must extend the Extension abstract class. "
            + "The class that implements the Extension is the \"bootstrap\" of the extension.";
   
    /**
     * The first version of the example extension.
     */
    public static final int VERSION = 1;

    /**
     * Creates a new Example extension.
     */
    public ExtensionExample() {
        super(NAME, VERSION, DESCRIPTION);
    }

    /**
     * Returns the user interface extension of this extension (an instance of
     * the class {@link  csheets.ext.simple.ui.UIExtensionExample}). In this
     * extension example we are only extending the user interface.
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new UIExtensionExample(this, uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Alexandre Braganca and it is in the package %s",
                getName(), getVersion(), getDescription(),getClass().getName());
    }
}
