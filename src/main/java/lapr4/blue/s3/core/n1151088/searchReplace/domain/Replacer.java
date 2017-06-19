package lapr4.blue.s3.core.n1151088.searchReplace.domain;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.Formula;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.compiler.FormulaCompiler;
import csheets.ui.ctrl.UIController;
import javax.swing.JOptionPane;

/**
 *
 * A replacer will change the content of cell
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class Replacer implements Runnable {
    
    /**The content that will replace the cell´s content with exp**/
    private String to, exp;
    /**Cells that will be previewed**/
    private Cell cell;
    
    private PreviewerList list;

    /**The controller that manage searchReplace user interface actions**/
    private UIController uiCtrl;
    
    public Replacer(Cell cellBefore, String expR, String to, UIController uiCtrl){

        if(!validateContainsExp(cellBefore, expR)){
           throw new IllegalStateException();
        }
        this.exp=expR;
        this.to=to;      
        this.cell=cellBefore;
        this.uiCtrl=uiCtrl;
    }
    
    public Replacer(PreviewerList list, String expR, String to, UIController uiCtrl){
        this.list=list;
        
        this.exp=expR;
        this.to=to;      
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
        if(list==null){
            try {
                getReplacedCell();
            } catch (IllegalValueTypeException | FormulaCompilationException ex) {
                JOptionPane.showMessageDialog(null,
                                "Please introduce a valid expression with replace.",
                                "FormulaCompilation error",
                                JOptionPane.ERROR_MESSAGE);
            }
        }else{
            try {
                getReplacedList();
            } catch (IllegalValueTypeException | FormulaCompilationException ex) {
                 JOptionPane.showMessageDialog(null,
                                "Please introduce a valid expression with replace.",
                                "FormulaCompilation error",
                                JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean validateContainsExp(Cell cell,String expInserted){
        return cell.getContent().contains(expInserted);
    }
    
    private void getReplacedCell() throws IllegalValueTypeException, FormulaCompilationException{
        PreviewCellDTO prev= new PreviewCellDTO(cell,exp,to);
        prev.previewReplace();
        prev.getBeforeCell().setContent(prev.getAfterCell().getContent());
    }
    
    private void getReplacedList() throws IllegalValueTypeException, FormulaCompilationException{
        for(int i=0; i<list.getSize(); i++){
           PreviewCellDTO before=(PreviewCellDTO) list.getElementAt(i);
           
           if(validateContainsExp(before.getBeforeCell(), this.exp)){
              
                PreviewCellDTO prev= new PreviewCellDTO(before.getBeforeCell(),exp,to);
                prev.previewReplace();
                prev.getBeforeCell().setContent(prev.getAfterCell().getContent());
           }

        }
    }
    
}
