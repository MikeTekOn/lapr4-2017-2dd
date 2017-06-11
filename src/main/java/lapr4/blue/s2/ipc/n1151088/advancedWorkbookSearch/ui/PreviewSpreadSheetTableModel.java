/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui;

import csheets.core.Spreadsheet;
import csheets.ui.ctrl.UIController;
import csheets.ui.sheet.SpreadsheetTableModel;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class PreviewSpreadSheetTableModel extends SpreadsheetTableModel{
    
    Spreadsheet spreadsheet;
    
    public PreviewSpreadSheetTableModel(Spreadsheet spreadsheet, UIController uiController) {
        super(spreadsheet, uiController);
        this.spreadsheet=spreadsheet;
    }
    
    public int getRowCount() {
		return Math.max(2, spreadsheet.getRowCount() + 1);
	}

	public int getColumnCount() {
		return Math.max(2, spreadsheet.getRowCount() + 1);
	}
    
}
