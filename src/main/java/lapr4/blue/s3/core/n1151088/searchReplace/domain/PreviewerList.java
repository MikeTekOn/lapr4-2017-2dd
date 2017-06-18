package lapr4.blue.s3.core.n1151088.searchReplace.domain;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * Represents the list of cell preview´s before the replace
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
        previewList.stream().filter((f) -> (f.getBeforeCell().toString().equals(item))).forEachOrdered((f) -> {
            selected = f;
        });
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
}
