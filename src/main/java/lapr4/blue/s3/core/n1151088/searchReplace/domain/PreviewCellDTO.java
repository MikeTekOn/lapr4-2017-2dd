package lapr4.blue.s3.core.n1151088.searchReplace.domain;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
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
    private final String replaceContent;
    
    public PreviewCellDTO(Cell beforeCell, String replaceContent){
        this.beforeCell=beforeCell;
        this.replaceContent=replaceContent;  
        this.afterCell=beforeCell;
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

    //    afterCell.copyFrom(beforeCell);
        if(afterCell.getValue().isOfType(Value.Type.TEXT)){
            String text=beforeCell.getValue().toText();
            String[]parts= text.split(" ");
            for(String part: parts){
                if(part.equals(replaceContent)){
                    part=replaceContent;
                }
            }
            
        }if( ((CommentableCellWithMultipleUsers) beforeCell.getExtension(CommentsExtension.NAME)).hasComment()) 
                
            for (List<String> comments : ((CommentableCellWithMultipleUsers) beforeCell.getExtension(CommentsExtension.NAME)).comments().values()) {
                for (String comment : comments) {
                    if(comment.equals(replaceContent)){
                        comment=replaceContent;
                    }
                }
        
        }else{
            afterCell.setContent(replaceContent);
        }
    }
    
     /**
     * clones the DTO
     *
     * @return
     */
    @Override
    public PreviewCellDTO clone() throws CloneNotSupportedException {
        return new PreviewCellDTO(beforeCell, replaceContent);
    }

    

}
