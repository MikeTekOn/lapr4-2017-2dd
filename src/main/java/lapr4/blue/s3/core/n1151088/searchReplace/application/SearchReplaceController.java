package lapr4.blue.s3.core.n1151088.searchReplace.application;

import csheets.core.Cell;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s3.core.n1151088.searchReplace.domain.Previewer;
import lapr4.blue.s3.core.n1151088.searchReplace.domain.PreviewerList;
import lapr4.blue.s3.core.n1151088.searchReplace.domain.Replacer;
import lapr4.green.s2.core.n1150838.GlobalSearch.application.GlobalSearchController;

/**
 * Represents the controller of search and replace feature
 * @author Diana Silva [1151088@isep.ipp.pt]
 */

public class SearchReplaceController extends GlobalSearchController{

    protected Thread threadReplace;
    protected Thread threadPreview;
    private Previewer util;
    
    public SearchReplaceController(UIController ctrl) {
         super(ctrl);
    }
    
    /**
     * Starts the thread to search 
     * @param cell cell to preview replace
     * @param exp inserted expression to replace
     * @param to
     */
     public void  startPreviewThread(Cell cell, String exp,String to){
         threadPreview = new Thread(new Previewer(cell, exp, to, ctrl));
         threadPreview.start();
    }
    /**
     * Stop the search thread
     */
    public void stopPreviewThread(){
        if(threadPreview!=null){
            threadPreview.stop();
        }
    }
    
    public void startReplaceAllThreads(PreviewerList list, String expR, String to, UIController uiCtrl){
         threadReplace = new Thread(new Replacer(list, expR, to, uiCtrl));
         threadReplace.start();
    }
    
     /**
     * Starts the thread to search 
     * @param cellBefore
     * @param expR
     * @param to
     * @param uiCtrl
     */
     public void  startReplaceThread(Cell cellBefore, String expR, String to, UIController uiCtrl){
         threadReplace = new Thread(new Replacer(cellBefore,expR,to,uiCtrl));
         threadReplace.start();
    }
    /**
     * Stop the search thread
     */
    public void stopReplaceThread(){
        if(threadReplace!=null){
            threadReplace.stop();
        }
    }
    
       /**
     * The call to the method to check if regex is valid
     *
     * @param beforeCell before cell content
     * @param exp
     * @param to
     * @param uictrl uicontroller
     * @return previewer builded
     */
    public Previewer checkIfPreviewValid(Cell beforeCell,  String exp, String to,UIController uictrl) {
        try{
            return new Previewer(beforeCell, exp, to, ctrl);
        }catch (IllegalStateException ex){
            return null;
        }
    }
    
}
