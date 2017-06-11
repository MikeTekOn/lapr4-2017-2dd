package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.Iterator;
import java.util.Objects;

/**
 * Represents the workbook preview
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public final class PreviewWorkbook{
    
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
        for(i=0; i<nrSheets;i++){
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
    
    public Workbook getWorkbook(){
        return this.previewWorkbook;
    }
    
    @Override
    public boolean equals(Object other){
        if (!(other instanceof PreviewWorkbook) || other == null)
            return false;
	PreviewWorkbook otherPreview = (PreviewWorkbook)other;
	if(otherPreview.nrSpreadSheetsFilled()!=this.nrSpreadSheetsFilled()){
            return false;
        }
        for(int i=0; i<this.nrSpreadSheetsFilled(); i++){
            Spreadsheet spreadSheet= this.getWorkbook().getSpreadsheet(i);
            Spreadsheet otherSpreadSheet=otherPreview.getWorkbook().getSpreadsheet(i);
            
           for(int row=0; row<spreadSheet.getColumnCount();row++){ 
                for(int column=0; column<spreadSheet.getColumnCount(); column++){
                    if(spreadSheet.getCell(column, row).getContent() == null ? 
                            otherSpreadSheet.getCell(column, row).getContent() != null : 
                            !spreadSheet.getCell(column, row).getContent().equals(otherSpreadSheet.getCell(column, row).getContent())){
                        return false;
                    }
                }
           }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.previewWorkbook);
        return hash;
    }
}
