/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1151094.columnSort.presentation;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import lapr4.red.s1.core.n1151094.columnSort.presentation.*;

/**
 *
 * @author Eduangelo Ferreira
 */
public class SortMenuUI extends JMenu {

    private final UIController uiController;
    
    public SortMenuUI(UIController uiController) {
        super("Sort");
        this.uiController = uiController;
        setMnemonic(KeyEvent.VK_S);
        setIcon(new ImageIcon(CleanSheets.class.getResource("res/img/sort.gif")));
       
        add(new ColumnSortUI(uiController));
    }

}
