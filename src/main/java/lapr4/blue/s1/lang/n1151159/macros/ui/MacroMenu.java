package lapr4.blue.s1.lang.n1151159.macros.ui;

import csheets.ui.ctrl.UIController;

import javax.swing.*;
import java.awt.event.KeyEvent;
import lapr4.red.s2.lang.n1150451.multipleMacros.ui.ModifyMacroAction;

/**
 * Represents the macro menu.
 *
 * @author Ivo Ferro
 */
public class MacroMenu extends JMenu {

    /**
     * Creates an instance of the macro menu. Adds the associated macro action.
     */
    public MacroMenu(UIController uiController) {
        super("Macros");

        setMnemonic(KeyEvent.VK_M);

        add(new MacroAction(uiController));
        add(new ModifyMacroAction(uiController));
    }
}
