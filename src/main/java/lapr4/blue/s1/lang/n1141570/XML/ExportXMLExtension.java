package lapr4.blue.s1.lang.n1141570.XML;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s1.lang.n1141570.XML.ui.UIExportXMLExtension;

/**
 *
 * @author Eric
 */
public class ExportXMLExtension extends Extension {
    
    /**
     * The name of the extension
     */
    public static final String NAME = "Export";

    /**
     * Creates a new export xml extension.
     */
    public ExportXMLExtension() {
        super(NAME);
    }

    /**
     * Returns the user interface for the macro extension.
     *
     * @param uiController the user interface controller
     * @return the UI of the macro extension
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new UIExportXMLExtension(this, uiController);
    }
}
