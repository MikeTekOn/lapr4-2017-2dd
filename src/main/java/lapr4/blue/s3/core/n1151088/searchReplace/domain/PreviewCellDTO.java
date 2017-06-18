package lapr4.blue.s3.core.n1151088.searchReplace.domain;

import csheets.core.Cell;
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
    
    private final Cell beforeCell;
    private Cell afterCell;
    private String insertedExp;
    private final String replaceContent;
    
    public PreviewCellDTO(Cell beforeCell, String insertedExp, String to){
        if(beforeCell==null){
            throw new IllegalArgumentException();
        }
        if(beforeCell.getContent().contains(insertedExp)){
            this.beforeCell=beforeCell;
            this.replaceContent=to;  
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
        return replaceContent;
    }
    
    public void previewReplace() throws IllegalValueTypeException, FormulaCompilationException{
        afterCell=beforeCell;
        
        String replacedContent=replaceContent();
        validateReplace(replaceContent);
      
        afterCell.setContent(replacedContent);
        
        if( ((CommentableCellWithMultipleUsers) beforeCell.getExtension(CommentsExtension.NAME)).hasComment()) {
                
            for (List<String> comments : ((CommentableCellWithMultipleUsers) afterCell.getExtension(CommentsExtension.NAME)).comments().values()) {
                for (String comment : comments) {
                    String contain=comment.replace(insertedExp, replaceContent);
                    comment=contain;
                }
            }
        }
    }
    

    public String replaceContent(){
        String text=beforeCell.getContent();
        String replaced=text.replace(insertedExp, replaceContent);
        return replaced;
    }
    
    
    public void replace() throws FormulaCompilationException{
        beforeCell.setContent(afterCell.getContent());
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
        return new PreviewCellDTO(beforeCell, insertedExp, replaceContent);
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

}
