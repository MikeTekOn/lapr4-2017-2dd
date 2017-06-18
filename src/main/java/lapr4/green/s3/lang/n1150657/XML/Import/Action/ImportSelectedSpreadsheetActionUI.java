/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Import.Action;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import lapr4.green.s3.lang.n1150657.XML.Import.UI.ImportSelectedSpreadsheetUI;

/**
 *
 * @author Sofia
 */
public class ImportSelectedSpreadsheetActionUI extends BaseAction{

    
    private final UIController controller;

    public ImportSelectedSpreadsheetActionUI(UIController controller) {
        this.controller = controller;
    }

    @Override
    protected String getName() {
        return "Selected spreadsheet";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImportSelectedSpreadsheetUI importSelectedSpreadsheetUI = new ImportSelectedSpreadsheetUI(controller);

    }
}
