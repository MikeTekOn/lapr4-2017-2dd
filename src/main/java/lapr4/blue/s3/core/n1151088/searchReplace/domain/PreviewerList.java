package lapr4.blue.s3.core.n1151088.searchReplace.domain;

import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Diana Silva {1151088 [isep.ipp.pt]
 */
public class PreviewerList extends AbstractListModel {

    /**
     * the current preview list
     */
    private ArrayList<PreviewCellDTO> previewList;
    /**
     * the selected preview
     */
    private PreviewCellDTO selected;
    
    private String replace;

    public PreviewerList(ArrayList<PreviewCellDTO> previewerList, String replace) {
        this.previewList=previewerList;
        this.replace=replace;
    }

    
    @Override
    public int getSize() {
        return previewList.size();
    }
    
     /**
     * search the preview that counts the item parameter
     *
     * @param item
     */
    public void setSelectedItem(String item) {
        for (PreviewCellDTO f : previewList) {
            if (f.getAfterCell().equals(item)) {
               selected = f;
            }
        }
    }
    
    /**
     * returns the selected item
     *
     * @return
     * @throws java.lang.CloneNotSupportedException if clone not builded
     */
    public PreviewCellDTO getSelectedItem() throws CloneNotSupportedException {

        PreviewCellDTO clone = (PreviewCellDTO) selected.clone();

        return clone;
    }

    @Override
    public Object getElementAt(int index) {
        return previewList.get(index);
    }
    
    
    /**
     * adds a new element to the list and fires a event.
     *
     * @param d
     */
    public void addElement(PreviewCellDTO d) {
        if (!previewList.contains(d) && previewList.add(d)) {
            fireIntervalAdded(d, previewList.size() - 1, previewList.size());
        }
    }
    
    public void removeAll(){
        int min=0;
        int max=this.previewList.size();
        previewList.clear();
        fireIntervalRemoved(this,min,max);
    }
    
    public String buildCellPreview(PreviewCellDTO cell) throws IllegalValueTypeException, FormulaCompilationException{
        StringBuilder sb = new StringBuilder();
  
        sb.append("Now: ");
        sb.append(cell.getBeforeCell().getContent());
        sb.append("Value: ");
        sb.append(cell.getBeforeCell().getValue());
        
        cell.previewReplace();
        sb.append("After: ");
        sb.append(cell.getAfterCell().getContent());
        sb.append("Value: ");
        sb.append(cell.getAfterCell().getValue());

        return sb.toString();
    }
        
}