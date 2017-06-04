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
     * Creates a new Example extension.
     */
    public ConditionalStyleExtension() {
        super(NAME);
    }

    /**
     * Makes the given cell condition stylable.
     *
     * @param cell the cell to condition style
     * @return a condition stylable cell
     */
    @Override
    public ConditionStylableCell extend(Cell cell) {
        return new ConditionStylableCell(cell);
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
}
