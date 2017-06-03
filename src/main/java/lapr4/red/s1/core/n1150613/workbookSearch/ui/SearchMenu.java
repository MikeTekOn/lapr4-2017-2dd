/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch.ui;

import csheets.ui.ctrl.UIController;
import javax.swing.JMenu;

/**
 *
 * @author Diogo
 */
public class SearchMenu extends JMenu {

    public SearchMenu(UIController uiController) {
        super("Workbook Search");
        add(new SearchAction(uiController));
    }

}
