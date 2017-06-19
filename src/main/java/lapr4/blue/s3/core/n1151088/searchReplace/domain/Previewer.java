package lapr4.blue.s3.core.n1151088.searchReplace.domain;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.Formula;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.compiler.FormulaCompiler;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s3.core.n1151088.searchReplace.SearchReplacePublisher;

/**
 *
 * A replacer will change the content of cell
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class Previewer implements Runnable {
    
    /**The content that will replace the cell´s content with exp**/
    private String to, exp;
    /**Cells that will be previewed**/
    private Cell cell;
    /**The controller that manage searchReplace user interface actions**/
    private UIController uiCtrl;
    
    public Previewer(Cell cellBefore, String expR, String to, UIController uiCtrl){
//        try{
//            validateContent(content, cellBefore);
//        }catch(FormulaCompilationException | IllegalValueTypeException ex ){
//            ex.getMessage();
//        }
        if(!validateContainsExp(cellBefore, expR)){
           throw new IllegalStateException();
        }
        this.exp=expR;
        this.to=to;      
        this.cell=cellBefore;
        this.uiCtrl=uiCtrl;
    }
    
    /**
     * Validate if content is compilable by Formula
     * @param content 
     * @param cell1 
     * @return true if content is valid, false if not
     * @throws FormulaCompilationException
     * @throws IllegalValueTypeException 
     */
    public boolean validateContent(String content, Cell cell1) throws FormulaCompilationException, IllegalValueTypeException{
        FormulaCompiler instance = FormulaCompiler.getInstance();
        Formula f = instance.compile(cell1, content, uiCtrl);
        f.evaluate();
        return true;
    }
  

    @Override
    public void run() {
    
        previewContent();
 
    }
    
    private boolean validateContainsExp(Cell cell,String expInserted){
        return cell.getContent().contains(expInserted);
    }

    private void previewContent() {
       
       SearchReplacePublisher.getInstance().notifyObservers(new PreviewCellDTO(cell, exp,to));
        
    }
    
}
