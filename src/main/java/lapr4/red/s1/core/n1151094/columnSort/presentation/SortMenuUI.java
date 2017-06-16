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
import lapr4.green.s2.core.n1150532.sort.SortCellRangeAction;
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
        
        /**
         * I've added the option to the CORE 03.2 Feature.
         * It was useless to create a new class just to add one more option.
         * 
         * @author Manuel Meireles (1150532)
         */
        add(new SortCellRangeAction(uiController));
    }

}
