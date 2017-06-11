package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.Iterator;

/**
 * Represents the builder of workbook preview area 
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class PreviewWorkbookBuilder implements Runnable{
    
    /** The workbook to preview*/
    private Workbook originalWorkbook;
    /**The default number of rows previewed*/
    private static final int DEFAULT_NUMBER_ROWS=1;
    /**The default number of columns previewed*/
    private static final int DEFAULT_NUMBER_COLUMNS=4;
    /**The default title for each spreadsheet*/
    private static final String DEFAULT_TITLE="Preview version: ";
    
    public PreviewWorkbookBuilder(Workbook workbook){
        if(!validateWorkbook(workbook)){
            throw new IllegalStateException();
        }
        
        this.originalWorkbook=workbook;
    }
    
  
    /**
     * Build a preview workbook with first non-empty cells
     * @return 
     */
    public PreviewWorkbook buildPreviewArea(){
        int i;
        int nSheets=this.originalWorkbook.getSpreadsheetCount();
        Workbook workbookBuilded=new Workbook();
        
         //verifies each sheet the first row cells
        for(i=0; i<nSheets; i++){
            
            String[][] spreadsheetContent= findContentFirstFilledCells(originalWorkbook.getSpreadsheet(i));
            
            //includes only non-empty spreadsheets 
            if(spreadsheetContent!=null){
                workbookBuilded.addSpreadsheet();
                workbookBuilded.getSpreadsheet(i).setTitle(DEFAULT_TITLE + originalWorkbook.getSpreadsheet(i).getTitle());
            }
        }
        try{
            PreviewWorkbook previewWorkbook= new PreviewWorkbook(workbookBuilded);
            return previewWorkbook; 
        }catch(IllegalStateException e){}
        
        throw new IllegalStateException("It wasnt possible to create workbook "
                + "preview. Verify if there is non-empty cells");
       
    }
    
    private void notityObservers(Object w){
        //TODO
    }   
    
    /**
     * Build a matrix with the content of the first cells
     * @param spreadsheet spreadsheet searched
     * @return matrix with the content of the first cells
     */
    private String[][] findContentFirstFilledCells(Spreadsheet spreadsheet){
        int column, row;
        String[][] firstFilledContent = null;
      
        //verifies the first row cells
        for(row=0; row<DEFAULT_NUMBER_ROWS-1; row++){
            
            //verifies the first columns
            for(column=0;column<DEFAULT_NUMBER_COLUMNS-1; column++) {
                try{                  
                    Cell cell=spreadsheet.getRow(row)[column];
                    
                    //verifies if it´s non-filled cell
                    if(cellIsFilled(cell)){
                        
                        firstFilledContent[row][column]=cell.getContent();
 
                        //FIXME think about update original document at moment
                       // if(firstFilledCells[row][column].getCellListeners()!=null){                
                    }      
                }catch(IllegalArgumentException e) {}
            }
        }
        return firstFilledContent;
    }
    
    
    /**
     * Validate if workbook to preview has content
     * @param workbook original workbook
     * @return true if it´s valid, false if not
     */
    private boolean validateWorkbook(Workbook workbook){
        int nrSheets=workbook.getSpreadsheetCount();
        //verifies if there is filled cells
        for(int i=0; i<nrSheets-1;i++){
            Iterator<Cell> cellsIterator= workbook.getSpreadsheet(i).iterator();
            
            while(cellsIterator.hasNext()){
                Cell cell= cellsIterator.next();
                
                if(cellIsFilled(cell)){
                    return true;
                }
            }
      
        }
        return false;
    }
    
   /**
    * Verifies if cell is non-empty
    * @return true if it´s filled, false if it´s empty
    */
    private boolean cellIsFilled(Cell cell){
        return cell.getFormula()!=null || cell.getValue()!=null || cell.getContent()!=null;
    }

    @Override
    public void run() {
        buildPreviewArea();
    }
}
