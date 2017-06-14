/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.application;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.Header;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.TableAndFiltersExtension;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.TableCellExtension;

/**
 *
 * @author nunopinto
 */
public class TableAndFiltersController implements Controller {
    
    /**
     * The user interface controller
     */
    private UIController uiController;
    /**
     * The table range
     */
    private CellRange range;

        /**
     * 
     * Selected cells at the moment
     */
    private ArrayList<Cell> selectedCells = new ArrayList<>();
    
    public TableAndFiltersController(UIController uiController){
        this.uiController=uiController;
    }
    
        /**
     * Updates the selected cells list
     * @param selectedCells
     */
    public boolean setSelectedCells(ArrayList<Cell> selectedCells) {
        this.selectedCells.clear();
        return this.selectedCells.addAll(selectedCells);
    }
    

    public void defineTable() {
        
       Cell start = selectedCells.get(0);
       int headerRow = start.getAddress().getRow();
       Cell end = selectedCells.get(selectedCells.size()-1);
      range = new CellRange(start,end);
       int index = 0 ;
        for (Cell selectedCell : selectedCells) {
            if(selectedCell.getAddress().getRow()==headerRow){
                String content;
                if(selectedCell.getFormula()!=null){
                    content = selectedCell.getValue().toString();
                }else{
                    content = selectedCell.getContent();
                }
                Header header = new Header(index,content);
                
                index ++;
            }
          
        }
       
    }

    /**
     * @return the range
     */
    public CellRange getRange() {
        return range;
    }
    
    public boolean isAvailableToDefine(){
        for (Cell selectedCell : selectedCells) {
            if(selectedCell!=null){
                return false;
            }
        }
        return true;
    }
    
    public void removeTable(CellRange range){
       Spreadsheet activeSpreadsheet = uiController.getActiveSpreadsheet();
       int rows = range.getRows();
       int columns = range.getColumns();
       Cell firstCell = range.getFirstCell();
       
       int firstRow = firstCell.getAddress().getRow();
       int firstColumn = firstCell.getAddress().getColumn();

       int i, j;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                Cell cell = activeSpreadsheet.getCell(firstColumn + j, firstRow + i);
                TableCellExtension tableCell = (TableCellExtension)cell.getExtension(TableAndFiltersExtension.NAME);
                tableCell.setRange(null);
                tableCell.setHeader(null);
            }
        }
    }

}
