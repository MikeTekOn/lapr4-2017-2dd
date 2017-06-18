package lapr4.blue.s3.core.n1141570.insertImage;

import csheets.core.Cell;
import csheets.ext.CellExtension;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s3.core.n1141570.insertImage.ui.UIExtensionImages;

/**
 *
 * @author Eric
 */
public class ImagesExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Images";

    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "An extension to support images on cells.\n"
            + "An extension must extend the Extension abstract class.\n"
            + "The class that implements the Extension is the \"bootstrap\" of the extension.\n";

    /**
     * The first version of the dependency trees extension.
     */
    public static final int VERSION = 1;

    /**
     * Creates a new Images extension.
     */
    public ImagesExtension() {
        super(NAME, VERSION, DESCRIPTION);
    }

    /**
     * Returns the user interface extension of this extension
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new UIExtensionImages(this, uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Eric Amaral and it is in the package %s",
                getName(), getVersion(), getDescription(), getClass().getName());
    }

    @Override
    public CellExtension extend(Cell cell) {
        return new ImageableCell(cell);
    }
    
    

}
