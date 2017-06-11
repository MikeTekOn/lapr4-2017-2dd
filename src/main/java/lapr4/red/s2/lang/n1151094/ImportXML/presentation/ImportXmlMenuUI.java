/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1151094.ImportXML.presentation;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 *
 * @author Eduangelo Ferreira -1151094
 */
public class ImportXmlMenuUI extends JMenu {

    /**
     * This is responsible for adding action to import xml file into the system
     *
     * @param controller UIController
     */
    public ImportXmlMenuUI(UIController controller) {
        super("Import to");

        setMnemonic(KeyEvent.VK_I);
        setIcon(new ImageIcon(CleanSheets.class.getResource("res/img/Import.png")));

        add(new ImportXmlActionUI(controller));
    }
}
