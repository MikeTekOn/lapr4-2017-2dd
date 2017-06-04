package lapr4.blue.s1.lang.n1141570.XML.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import lapr4.blue.s1.lang.n1141570.XML.ExportXMLExtension;

/**
 *
 * @author Eric
 */
public class UIExportXMLExtension extends UIExtension {

    /**
     * The icon to display with the extension's name.
     */
    private Icon icon;

    /**
     * The export xml menu.
     */
    private ExportXMLMenuUI menu;

    /**
     * Creates the user interface for the export xml extension.
     *
     * @param extension the extension for which components are provided
     * @param uiController the user interface controller
     */
    public UIExportXMLExtension(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns an icon to display with the extension's name.
     *
     * @return an icon with style
     */
    public Icon getIcon() {
        if (icon == null) {
            icon = new ImageIcon(ExportXMLExtension.class.getResource("res/img/exportXMLblack.gif"));
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
            menu = new ExportXMLMenuUI(uiController);
        }
        return menu;
    }
}
