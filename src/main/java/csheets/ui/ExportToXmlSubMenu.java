package csheets.ui;

import csheets.ui.ctrl.ActionManager;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import lapr4.blue.s1.lang.n1141570.XML.JavadocPurposes;

/**
 * Represents a sub menu for the XML exportation.
 *
 * @author Eric
 */
public class ExportToXmlSubMenu extends JMenu {

    /**
     * Creates a new instance of export to xml sub menu.
     *
     * @param actionManager the action manage
     */
    public ExportToXmlSubMenu(ActionManager actionManager) {
        super("XML");

        setIcon(new ImageIcon(JavadocPurposes.class.getResource("res/img/exportXMLblack.gif")));

        setMnemonic(KeyEvent.VK_X);
        add(actionManager.getAction("exportworkbook"));
        add(actionManager.getAction("exportspreadsheet"));
        add(actionManager.getAction("exportselectedrow"));
        add(actionManager.getAction("exportselectedcolumn"));
        add(actionManager.getAction("exportselectedcells"));
    }

}
