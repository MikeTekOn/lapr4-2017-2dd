package lapr4.blue.s1.lang.n1151031.formulastools;

import csheets.core.Cell;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s1.lang.n1151031.formulastools.ui.ConditionalStyleUIExtension;

/**
 * An extension to support conditions on cells. An extension must extend the
 * Extension abstract class. The class that implements the Extension is the
 * "bootstrap" of the extension.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 * @see Extension
 */
public class ConditionalStyleExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Conditional Formatting";
    
     /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "An extension to support conditions on cells.\n"
            + "An extension must extend the Extension abstract class.\n"
            + "The class that implements the Extension is the \"bootstrap\" of the extension.";
    
    /**
     * The first version of the conditional style extension.
     */
    public static final int VERSION = 1;
    public UIController uiController;

    /**
     * Creates a new Example extension.
     * @param uiControlle
     */
    public ConditionalStyleExtension(UIController uiControlle){
        super(NAME, VERSION, DESCRIPTION);
        this.uiController = uiControlle;
    }
    
    //FIXME, it exists for the loading. It neads a constructor without parameters
    public ConditionalStyleExtension(){
        super(NAME, VERSION, DESCRIPTION);
    }

    /**
     * Makes the given cell condition stylable.
     *
     * @param cell the cell to condition style
     * @return a condition stylable cell
     */
    @Override
    public ConditionStylableCell extend(Cell cell) {
        return new ConditionStylableCell(cell,uiController);
    }

    /**
     * Returns the user interface extension of this extension
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new ConditionalStyleUIExtension(this, uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Tiago Correia in Sprint 1 and it is in the package %s",
                getName(), getVersion(), getDescription(),getClass().getName());
    }
}
