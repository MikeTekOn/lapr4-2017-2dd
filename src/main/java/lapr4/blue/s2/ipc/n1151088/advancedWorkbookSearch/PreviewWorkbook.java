package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import csheets.core.Cell;
import csheets.core.Workbook;
import java.util.Iterator;

/**
 * Represents the workbook preview
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class PreviewWorkbook{
    
    /** The preview workbook version (with first non-empty cells*/
    private Workbook previewWorkbook;
    
    public PreviewWorkbook(Workbook workbook){
        
        //verifies if has non-empty cells
       if(!isFilled(workbook)){
          throw new IllegalStateException();
       }
       this.previewWorkbook=workbook;
  
    }
                   
    /**
     * Verifies if preview workbook have filled cell(s)
     * @param workbook preview workbook version
     * @return true if it´s filled, false if it´s empty
     */
    public boolean isFilled(Workbook workbook){
        int i, nrSheets=workbook.getSpreadsheetCount();
      
        //verifies if there is filled cells
        for(i=0; i<nrSheets-1;i++){
            Iterator<Cell> cellsIterator= workbook.getSpreadsheet(i).iterator();
            
            while(cellsIterator.hasNext()){
                Cell cell= cellsIterator.next();
                
                if(cell.getFormula()!=null || cell.getValue()!=null ||
                        cell.getContent()!=null)
                    if(!cell.getContent().matches("") && !cell.getContent().matches(" ")){
                             return true;
                }          
            }   
        }
        return false;
    }
    
    public int nrSpreadSheetsFilled(){
        return this.previewWorkbook.getSpreadsheetCount();
    }  
}
