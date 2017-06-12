/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.presentation;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author nunopinto
 */
public class CustomListString  extends AbstractListModel 
{
   
    /**
     * the current type list
     */
    private ArrayList<String> typeList;
    /**
     * the selected file
     */
    private String selected;

    public CustomListString(ArrayList<String> type) {
        this.typeList = type;
    }

    /**
     *
     * @return the wokbooklist current size
     */
    @Override
    public int getSize() {
        return typeList.size();
    }

    /**
     *
     * @param index
     * @return the object contained on the given index
     */
    @Override
    public Object getElementAt(int index) {
        if(typeList.size()==0 | typeList.size()<=index ) return null;
        return typeList.get(index);
    }



    /**
     * adds a new element to the list and fires a event.
     *
     * @param d
     */
    public void addElement(String d) {
        if (!typeList.contains(d) && typeList.add(d)) {
            fireIntervalAdded(d, typeList.size() - 1, typeList.size());
        }
    }

    /**
     * removes a element from the list and fires a event.
     *
     * @param d
     */
    public void removeElement(String  d) {
        int indice = typeList.indexOf(d);
        typeList.remove(d);
        if (!typeList.contains(d) && typeList.remove(d)) {
            fireIntervalRemoved(this, indice, indice);
        }
    }

    /**
     * cleans all the list
     */
    public void removeAll() {
        int min = 0;
        int max = typeList.size();
        typeList.clear();
        fireIntervalRemoved(this, min, max);
    }
    
    public List<String> getValues(){
        return typeList;
    }
    
}
