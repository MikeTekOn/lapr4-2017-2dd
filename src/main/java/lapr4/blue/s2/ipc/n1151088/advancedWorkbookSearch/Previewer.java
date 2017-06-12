package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import csheets.core.IllegalValueTypeException;
import csheets.core.Workbook;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl.ControllerFindWorkbooks;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl.ControllerPreviewWorkbook;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FindWorkbooksPublisher;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class Previewer implements Runnable {
    private CellRange cellRange;
    private boolean column;
    private Workbook workbook;
    private Workbook previewWk;
    private static final long PREVIEW_MAX_TIME=15000;
    
    public Previewer(CellRange cellRange, boolean column,Workbook workbook){
        this.cellRange=cellRange;
        this.column=column;
        this.workbook=workbook;
    }
    
    public void previewWorkbook() throws IllegalValueTypeException{
        preview();
    }
    
    private void preview() throws IllegalValueTypeException{
    
            //System.out.println("I AM THE SYNCHRONIZATION THREAD!\n") 
            PreviewWorkbookBuilder pwb=new PreviewWorkbookBuilder(this.workbook);
            PreviewWorkbook pw=pwb.previewWorkbook(cellRange, column);
            FindWorkbooksPublisher.getInstance().notifyObservers(pw.getWorkbook());
        }

    @Override
    public void run() {
        try {
            previewWorkbook();
        } catch (IllegalValueTypeException ex) {
            ex.getMessage();
        }
    }
}
