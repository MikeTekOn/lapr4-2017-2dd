package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.Iterator;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.white.s1.core.n4567890.contacts.ui.ContactDialog;

/**
 * Represents the builder of workbook preview area 
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class PreviewWorkbookBuilder{
    
    /** The workbook to preview*/
    private Workbook originalWorkbook;

    /**The default number of rows previewed*/
    private static final int DEFAULT_NUMBER_ROWS=1;
    /**The default number of columns previewed*/
    private static final int DEFAULT_NUMBER_COLUMNS=4;
    /**The default title for each spreadsheet*/
    private static final String DEFAULT_TITLE="Preview version: ";
    
    public PreviewWorkbookBuilder(Workbook workbook){
        if(!validateOriginalWorkbook(workbook)){
            throw new IllegalStateException();
        }
        
        this.originalWorkbook=workbook;
    }
    
    /**
     * Public method to call a private and recursive preview
     * @param cellRange
     * @param column
     * @return preview workbook
     * @throws IllegalValueTypeException workbook not filled
     */
    public PreviewWorkbook previewWorkbook(CellRange cellRange, boolean column) throws IllegalValueTypeException{
        return buildPreviewArea(cellRange, column);
    }
    
  
    /**
     * Build a preview workbook with first non-empty cells
     * @return 
     * @throws csheets.core.IllegalValueTypeException 
     */
    private PreviewWorkbook buildPreviewArea(CellRange cellRange, boolean column) throws IllegalValueTypeException{
        int i;
        int nSheets=this.originalWorkbook.getSpreadsheetCount(), sheetAddedCount=0;
        Workbook workbookBuilded=new Workbook();
        
         //verifies each sheet the first row cells
        for(i=0; i<nSheets; i++){
            
            String[][] spreadsheetContent= findContentFirstFilledCells(originalWorkbook.getSpreadsheet(i));
            
            //includes only non-empty spreadsheets 
            if(spreadsheetContent!=null && haveFilledCells(spreadsheetContent)){
                spreadsheetContent=cleanSpreadsheetContent(spreadsheetContent);
                workbookBuilded.addSpreadsheet(spreadsheetContent);    
                workbookBuilded.addSpreadsheet();
                workbookBuilded.getSpreadsheet(sheetAddedCount).setTitle(DEFAULT_TITLE + originalWorkbook.getSpreadsheet(i).getTitle());
                workbookBuilded.getSpreadsheet(sheetAddedCount+1).setTitle(DEFAULT_TITLE + originalWorkbook.getSpreadsheet(i).getTitle());
               
                sheetAddedCount++;
            }
            
        }
        try{
           PreviewWorkbook previewWorkbook= new PreviewWorkbook(workbookBuilded);
           return previewWorkbook;
        }catch(IllegalStateException e){}
        
        throw new IllegalStateException("It wasnt possible to create workbook "
                + "preview. Verify if there is non-empty cells");
       
    }
    
    /**
     * Build a matrix with the content of the first cells
     * @param spreadsheet spreadsheet searched
     * @return matrix with the content of the first cells
     */
    private String[][] findContentFirstFilledCells(Spreadsheet spreadsheet) throws IllegalValueTypeException{
        int column, row, contentColumn=0, contentRow=0;
        String[][] firstFilledContent= new String[DEFAULT_NUMBER_ROWS][DEFAULT_NUMBER_COLUMNS];
   
        //verifies the first row cells
        for(row=0; row<spreadsheet.getRowCount()-1; row++){
            
            //verifies the first columns
            for(column=0;column<spreadsheet.getColumnCount()-1; column++) {
                try{                  
                    Cell cell=spreadsheet.getRow(row)[column];
                    
                    //verifies if it´s non-filled cell
                    if(cellIsFilled(cell)){
    
                        if(cell.getValue()!=null)
                            firstFilledContent[contentRow][contentColumn]=cell.getValue().toString();
                        else
                            firstFilledContent[contentRow][contentColumn]=cell.getContent();
                        if(contentColumn<DEFAULT_NUMBER_COLUMNS-1){
                             contentColumn++;
                        
                        //reach the last column
                        } else{
                            contentRow++;
                            contentColumn=0;
                        } 
                      
                         //previewWorkbook filled
                        if(contentRow==DEFAULT_NUMBER_ROWS-1 && contentColumn==DEFAULT_NUMBER_COLUMNS)
                            return firstFilledContent;
                        
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
    private boolean validateOriginalWorkbook(Workbook workbook){
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
     * Validate spreadsheet content to be added to workbook
     * @return true if all strings have content, false if not
     */
    private boolean haveFilledCells(String[][] ss){
        
        for (String[] s : ss) {
            for (String item : s) {
                if(item!=null){
                    return true;
                }
            }
        }
        return false;
    }
    
     /**
     * Validate spreadsheet content to be added to workbook
     * @return true if all strings have content, false if not
     */
    private String[][] cleanSpreadsheetContent(String[][] ss){
        String[][] cleanSs=new String[DEFAULT_NUMBER_ROWS][DEFAULT_NUMBER_COLUMNS];
        for (int row=0; row<DEFAULT_NUMBER_ROWS; row++) {
            for(int column=0; column<DEFAULT_NUMBER_COLUMNS; column++){
                if(ss[row][column]==null)
                    cleanSs[row][column]="";
                else
                    cleanSs[row][column]=ss[row][column];
            }
        }
            
        return cleanSs;
    }
    
   /**
    * Verifies if cell is non-empty
    * @return true if it´s filled, false if it´s empty
    */
    private boolean cellIsFilled(Cell cell){
        try{
            return cell.getFormula()!=null || cell.getValue()!=null || cell.getContent()!=null;
        }catch(IllegalStateException ex){}
        throw new IllegalStateException("Cell null");
                
    }
    
    public static int getDefaultColumnsPreview(){
        return DEFAULT_NUMBER_COLUMNS;
    }
    
    public static int getDefaultRowsPreview(){
        return DEFAULT_NUMBER_ROWS;
    }
}
