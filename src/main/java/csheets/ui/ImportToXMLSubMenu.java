/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.ActionManager;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 *
 * @author Sofia
 */
public class ImportToXMLSubMenu extends JMenu {
    
    private static final String DEFAULT_NAME = "XML";

    public ImportToXMLSubMenu(ActionManager actionManager) {
        super(DEFAULT_NAME);

        setIcon(new ImageIcon(CleanSheets.class.getResource("res/img/xml.png")));

        setMnemonic(KeyEvent.VK_W);
        add(actionManager.getAction("importworkbook"));
        add(actionManager.getAction("importspreadsheet"));
        add(actionManager.getAction("importselectedcells"));
   }
}
