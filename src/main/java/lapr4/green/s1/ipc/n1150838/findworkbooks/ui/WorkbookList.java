/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks.ui;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FileDTO;

/**
 *
 * @author nunopinto
 */
public class WorkbookList extends AbstractListModel {

    /**
     * the current workbook list
     */
    private ArrayList<FileDTO> workbookList;
    /**
     * the selected file
     */
    private FileDTO selected;

    public WorkbookList(ArrayList<FileDTO> workbookList) {
        this.workbookList = workbookList;
    }

    /**
     * search the file that counts the item parameter
     *
     * @param item
     */
    public void setSelectedItem(String item) {
        for (FileDTO f : workbookList) {

            if (f.getFileName().equals(item)) {

                selected = f;
            }
        }
    }

    /**
     * returns the selected item
     *
     * @return
     */
    public FileDTO getSelectedItem() {

        FileDTO clone = selected.clone();

        return clone;
    }

    /**
     *
     * @return the wokbooklist current size
     */
    @Override
    public int getSize() {
        return workbookList.size();
    }

    /**
     *
     * @param index
     * @return the object contained on the given index
     */
    @Override
    public Object getElementAt(int index) {
        return workbookList.get(index).getFileName();
    }

    /**
     *
     * @return a copy of this object
     */
    public ArrayList<FileDTO> clone() {

        return (ArrayList<FileDTO>) workbookList.clone();
    }

    /**
     * adds a new element to the list and fires a event.
     *
     * @param d
     */
    public void addElement(FileDTO d) {
        if (!workbookList.contains(d) && workbookList.add(d)) {
            fireIntervalAdded(d, workbookList.size() - 1, workbookList.size());
        }
    }

    /**
     * removes a element from the list and fires a event.
     *
     * @param d
     */
    public void removeElement(FileDTO d) {
        int indice = workbookList.indexOf(d);
        workbookList.remove(d);
        if (!workbookList.contains(d) && workbookList.remove(d)) {
            fireIntervalRemoved(this, indice, indice);
        }
    }

    /**
     * cleans all the list
     */
    public void removeAll() {
        int min = 0;
        int max = workbookList.size();
        workbookList.clear();
        fireIntervalRemoved(this, min, max);
    }
}
