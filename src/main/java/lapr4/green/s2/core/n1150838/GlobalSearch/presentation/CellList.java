/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.presentation;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author nunopinto
 */
public class CellList extends AbstractListModel {

    /**
     * the current cell dto list
     */
    private ArrayList<CellInfoDTO> cellList;
    /**
     * the selected file
     */
    private CellInfoDTO selected;

    public CellList(ArrayList<CellInfoDTO> cellList) {
        this.cellList = cellList;
    }

    /**
     * search the file that counts the item parameter
     *
     * @param item
     */
    public void setSelectedItem(String item) {
        for (CellInfoDTO f : cellList) {
            if (buildCellDescription(f).equals(item)) {

               selected = f;
            }
        }
    }

    /**
     * returns the selected item
     *
     * @return
     */
    public CellInfoDTO getSelectedItem() throws CloneNotSupportedException {

        CellInfoDTO clone = selected.clone();

        return clone;
    }

    /**
     *
     * @return the wokbooklist current size
     */
    @Override
    public int getSize() {
        return cellList.size();
    }

    /**
     *
     * @param index
     * @return the object contained on the given index
     */
    @Override
    public Object getElementAt(int index) {
        
        return buildCellDescription(cellList.get(index));
    }
        /**
     *
     * @param cell
     * @return the description of the cell
     */
    public String buildCellDescription(CellInfoDTO cell){
        StringBuilder sb = new StringBuilder();
        sb.append("Workbook: ");
        sb.append(cell.getWorkbookName());
        sb.append(" Spreadsheet: ");
        sb.append(cell.getSpreadsheetNumber());
        sb.append(" Cell location: ");
        sb.append(cell.getCell().toString());
        

        return sb.toString();
    }

    /**
     *
     * @return a copy of this object
     */
    public ArrayList<CellInfoDTO> clone() {

        return (ArrayList<CellInfoDTO>) cellList.clone();
    }

    /**
     * adds a new element to the list and fires a event.
     *
     * @param d
     */
    public void addElement(CellInfoDTO d) {
        if (!cellList.contains(d) && cellList.add(d)) {
            fireIntervalAdded(d, cellList.size() - 1, cellList.size());
        }
    }

    /**
     * removes a element from the list and fires a event.
     *
     * @param d
     */
    public void removeElement(CellInfoDTO  d) {
        int indice = cellList.indexOf(d);
        cellList.remove(d);
        if (!cellList.contains(d) && cellList.remove(d)) {
            fireIntervalRemoved(this, indice, indice);
        }
    }

    /**
     * cleans all the list
     */
    public void removeAll() {
        int min = 0;
        int max = cellList.size();
        cellList.clear();
        fireIntervalRemoved(this, min, max);
    }
}
