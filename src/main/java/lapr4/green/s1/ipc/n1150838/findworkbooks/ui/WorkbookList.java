/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks.ui;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import lapr4.green.s1.ipc.n1150838.findworkbooks.WorkbookDTO;

/**
 *
 * @author nunopinto
 */
public class WorkbookList extends AbstractListModel{
    
    private ArrayList<WorkbookDTO> workbookList;
    private WorkbookDTO selected;

    public WorkbookList(ArrayList<WorkbookDTO> workbookList) {
        this.workbookList = workbookList;
    }

    public void setSelectedItem(String item) {
        for (WorkbookDTO f : workbookList) {

            if (f.getFileName().equals(item)) {

                selected = f;
            }
        }
    }

    public WorkbookDTO getSelectedItem() {

       WorkbookDTO clone = selected.clone();

        return clone;
    }
    
    @Override
    public int getSize() {
        return workbookList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return workbookList.get(index).getFileName();
    }

    public ArrayList<WorkbookDTO> clone() {

        return (ArrayList<WorkbookDTO>) workbookList.clone();
    }

    public void addElement(WorkbookDTO d) {
        if (!workbookList.contains(d) && workbookList.add(d)) {
            fireIntervalAdded(d, workbookList.size() - 1, workbookList.size());
        }
    }

    public void removeElement(WorkbookDTO d) {
        int indice = workbookList.indexOf(d);
        workbookList.remove(d);
        if (!workbookList.contains(d) && workbookList.remove(d)) {
            fireIntervalRemoved(this, indice, indice);
        }
    }
    public void removeAll(){
        int min = 0;
        int max = workbookList.size();
        workbookList.clear();
        fireIntervalRemoved(this,min,max);
    }
}
