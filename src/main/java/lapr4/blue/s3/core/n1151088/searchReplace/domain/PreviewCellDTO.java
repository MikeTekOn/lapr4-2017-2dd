package lapr4.blue.s3.core.n1151088.searchReplace.domain;

import csheets.core.Cell;
import csheets.core.CellImpl;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import eapli.framework.dto.DTO;
import java.util.List;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;

/**
 * A preview with "what" cell is before and after replacement
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class PreviewCellDTO implements DTO {
    
    private Cell beforeCell;
    private Cell afterCell;
    private String insertedExp;
    private final String to;
    
    public PreviewCellDTO(Cell beforeCell, String insertedExp, String to){
        if(beforeCell==null){
            throw new IllegalArgumentException();
        }
        if(beforeCell.getContent().contains(insertedExp)){
            this.beforeCell=beforeCell;
            this.afterCell=cloneCell();
            this.to=to;  
            this.insertedExp=insertedExp;
        }else{
            throw new IllegalArgumentException();  
        }
                
                          
    }
    
    public Cell getBeforeCell(){
        return beforeCell;
    }
    
    public Cell getAfterCell(){
        return afterCell;
    }
    
    public String getReplaceContent(){
        return to;
    }
    
    public void previewReplace() throws IllegalValueTypeException, FormulaCompilationException{
        
        afterCell.copyFrom(beforeCell);
      
        String replacedContent=replaceContent();
        validateReplace(replacedContent);
      
        if( ((CommentableCellWithMultipleUsers) beforeCell.getExtension(CommentsExtension.NAME)).hasComment()) {
                
            ((CommentableCellWithMultipleUsers) afterCell.getExtension(CommentsExtension.NAME)).comments().values().forEach((List<String> comments) -> {
                comments.forEach((comment) -> {
                    String contain=comment.replace(insertedExp, to);
                    comment=contain;
                });
            });
        }
    }
    
    private String replaceContent(){
        String text=beforeCell.getContent();
        String replaced=text.replace(insertedExp, to);
        return replaced;
    }
        
    public void validateReplace(String replacedText) throws FormulaCompilationException{
        
        try{
            afterCell.setContent(replacedText);
            
        }catch (FormulaCompilationException ex){
            ex.getMessage();
           throw new FormulaCompilationException();
        }
    }
    
     /**
     * clones the DTO
     *
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public PreviewCellDTO clone() throws CloneNotSupportedException {
        return new PreviewCellDTO(beforeCell, insertedExp, to);
    }
    
    @Override
    public String toString(){
        return "Cell " + this.beforeCell.getAddress().toString() + 
                "| Content: " + this.beforeCell.getContent() + 
                "| Value: " + this.beforeCell.getValue().toString();
    }
    
      public String buildCellPreviewDescription() throws
              IllegalValueTypeException, FormulaCompilationException{
        StringBuilder sb = new StringBuilder();

        previewReplace();
        sb.append(afterCell.getValue());
        sb.append(" | Content" + "\"");
        sb.append(afterCell.getContent());
          sb.append("\"");
        return sb.toString();
    }
      
    private Cell cloneCell(){
 
        return new CellImpl(beforeCell.getSpreadsheet(), beforeCell.getAddress());
    }

}
