package lapr4.blue.s3.core.n1151088.searchReplace.domain;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.Formula;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.compiler.FormulaCompiler;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.List;
import lapr4.blue.s3.core.n1151088.searchReplace.SearchReplacePublisher;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.CellInfoDTO;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.CellList;

/**
 *
 * A replacer will change the content of cell
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class Previewer implements Runnable {
    
    /**The content that will replace the cell´s content**/
    private String content;
    /**Cells that will be previewed**/
    private CellList cellList;
    /**The controller that manage searchReplace user interface actions**/
    private UIController uiCtrl;
    
    public Previewer(CellList cellList, String content, UIController uiCtrl){
        try{
            validateContent(content);
        }catch(FormulaCompilationException | IllegalValueTypeException ex ){
            ex.getMessage();
        }
        this.content=content;
        
        this.cellList=cellList;
        this.uiCtrl=uiCtrl;
    }
    
   /**
    * Update the list with new cell
    * @param cell cell to replace
    * @return true if added, false if not
    */
    public boolean addCell(CellInfoDTO cell){
        try{
            validateContent(cell.getCell().getContent());
        }catch(FormulaCompilationException | IllegalValueTypeException ex ){
            ex.getMessage();
        }
//        if(cellList.containsElement(cell)){
//            return false;
//        } else {
//            cellList.addElement(cell);
//        }
        return true;
    }
    
    /**
     * Validate if content is compilable by Formula
     * @param content 
     * @return true if content is valid, false if not
     * @throws FormulaCompilationException
     * @throws IllegalValueTypeException 
     */
    public boolean validateContent(String content) throws FormulaCompilationException, IllegalValueTypeException{
        FormulaCompiler instance = FormulaCompiler.getInstance();
        Formula f = instance.compile(null, content, uiCtrl);
        f.evaluate();
        return true;
    }
  

    @Override
    public void run() {
    
        previewContent();
 
    }

    private void previewContent() {
        ArrayList<CellInfoDTO> clone= cellList.clone();
        for(CellInfoDTO cell: clone){
            SearchReplacePublisher.getInstance().notifyObservers(new PreviewCellDTO(cell.getCell(), content));
        }
    }
    
}
