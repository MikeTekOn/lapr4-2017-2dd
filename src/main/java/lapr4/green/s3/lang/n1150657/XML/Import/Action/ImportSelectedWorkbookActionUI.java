/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Import.Action;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import lapr4.green.s3.lang.n1150657.XML.Import.UI.ImportSelectedWorkbookUI;


/**
 *
 * @author Eduangelo Ferreira - 1151094 @author Sofia
 */
public class ImportSelectedWorkbookActionUI extends BaseAction {

    /**
     * A controller for managing the current selection, i.e. the active
     * workbook, spreadsheet and cell, as well as for keeping track of
     * modifications to workbooks and of user interface extensions.
     */
    private final UIController controller;

    public ImportSelectedWorkbookActionUI(UIController controller) {
        this.controller = controller;
    }

    @Override
    protected String getName() {
        return "Selected workbook";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImportSelectedWorkbookUI importXMLUI = new ImportSelectedWorkbookUI(controller);

    }
}
