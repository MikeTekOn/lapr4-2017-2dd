/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.presentation;

import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.*;
import csheets.core.Cell;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Table;

/**
 *
 * @author nunopinto
 */
public class TableList extends AbstractListModel {

    /**
     * the current cell dto list
     */
    private ArrayList<Table> tableList;
    /**
     * the selected file
     */
    private Table selected;

    public TableList(ArrayList<Table> tableList) {
        this.tableList = tableList;
    }

    /**
     * search the file that counts the item parameter
     *
     * @param item
     */
    public void setSelectedItem(String item) {
        for (Table f : tableList) {
            if (buildTableDescription(f).equals(item)) {

               selected = f;
            }
        }
    }

    /**
     * returns the selected item
     *
     * @return
     */
    public Table getSelectedItem() {

        return selected;
    }

    /**
     *
     * @return the wokbooklist current size
     */
    @Override
    public int getSize() {
        return  tableList.size();
    }

    /**
     *
     * @param index
     * @return the object contained on the given index
     */
    @Override
    public Object getElementAt(int index) {
        
        return buildTableDescription(tableList.get(index));
    }
        /**
     *
     * @param cell
     * @return the description of the cell
     */
    public String buildTableDescription(Table table){
        StringBuilder sb = new StringBuilder();
        sb.append("Table: ");
        CellRange range = table.getRange();
        sb.append(range.getFirstCell());
        sb.append(":");
        sb.append(range.getLastCell());
        
        return sb.toString();
    }

    /**
     *
     * @return a copy of this object
     */
    public ArrayList<Table> clone() {

        return (ArrayList<Table>) tableList.clone();
    }

    /**
     * adds a new element to the list and fires a event.
     *
     * @param d
     */
    public void addElement(Table d) {
        if (!tableList.contains(d) && tableList.add(d)) {
            fireIntervalAdded(d, tableList.size() - 1, tableList.size());
        }
    }

    /**
     * removes a element from the list and fires a event.
     *
     * @param d
     */
    public void removeElement(Table  d) {
        int indice = tableList.indexOf(d);
        tableList.remove(d);
        if (!tableList.contains(d) && tableList.remove(d)) {
            fireIntervalRemoved(this, indice, indice);
        }
    }

    /**
     * cleans all the list
     */
    public void removeAll() {
        int min = 0;
        int max = tableList.size();
        tableList.clear();
        fireIntervalRemoved(this, min, max);
    }
}
