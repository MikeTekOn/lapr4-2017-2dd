package lapr4.blue.s1.lang.n1151159.macros.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s1.lang.n1151159.macros.MacroExtension;

import javax.swing.*;

/**
 * Represents the user interface for the macro extension.
 *
 * @author Ivo Ferro
 */
public class UIMacroExtension extends UIExtension {

    /**
     * The icon to display with the extension's name.
     */
    private Icon icon;

    /**
     * The macro menu.
     */
    private MacroMenu menu;

    /**
     * Creates the user interface for the macro extension.
     *
     * @param extension    the extension for which components are provided
     * @param uiController the user interface controller
     */
    public UIMacroExtension(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns an icon to display with the extension's name.
     *
     * @return an icon with style
     */
    public Icon getIcon() {
        if (icon == null) {
            icon = new ImageIcon(MacroExtension.class.getResource("res/img/logo.gif"));
        }
        return icon;
    }

    /**
     * Returns the macro menu.
     *
     * @return macro menu
     */
    public JMenu getMenu() {
        if (menu == null) {
            menu = new MacroMenu(uiController);
        }
        return menu;
    }
}
