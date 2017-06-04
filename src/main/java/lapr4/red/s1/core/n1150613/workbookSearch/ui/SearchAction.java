/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;

import java.awt.event.ActionEvent;

/**
 * @author Diogo
 */
public class SearchAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    public SearchAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Workbook Search";
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        WorkbookSearchSideBar bar = new WorkbookSearchSideBar(uiController);
        bar.setVisible(true);
    }

}
