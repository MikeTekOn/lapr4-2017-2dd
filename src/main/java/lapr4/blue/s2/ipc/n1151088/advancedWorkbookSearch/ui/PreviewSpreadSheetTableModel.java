package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui;

import csheets.core.Address;
import csheets.core.Cell;
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
    
    @Override
    public int getRowCount() {
        return 3;
    }

    @Override
    public int getColumnCount() {
            return 3;
    }    
    
    @Override
    public Object getValueAt(int row, int column){
         if(column<spreadsheet.getColumnCount()){
            Cell cell= spreadsheet.getCell(column, row);
            return cell.getValue().toString();
            
        }
         return null;
    }
    
    public void removeAll(){
        int minIndex=0;
        int maxColumn=spreadsheet.getColumnCount();
        int maxRow=spreadsheet.getRowCount();
        Address max=new Address(maxColumn, maxRow);
        Address min=new Address(minIndex,minIndex);
        spreadsheet.getCells(min, max).clear();
        fireTableRowsDeleted(minIndex, maxRow);
      
        
    }
}
