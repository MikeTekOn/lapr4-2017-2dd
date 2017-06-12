/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl;

import csheets.core.Workbook;
import eapli.framework.application.Controller;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.Previewer;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class ControllerPreviewWorkbook implements Controller {
    private Workbook originalWorkbook;
    private Workbook previewWorkbook;
    private Thread previewThread;
    private Previewer previewer;
    
    public ControllerPreviewWorkbook(CellRange cellRange, boolean column,Workbook workbook){
        this.originalWorkbook=workbook;
        this.previewer=new Previewer(cellRange, column, workbook);
    }
    
    public void previewWorkbook(){
        previewThread=new Thread(previewer);
   
    }
    
    public void stopPreview(){
        if(previewThread!=null)previewThread.stop();
    }
}
