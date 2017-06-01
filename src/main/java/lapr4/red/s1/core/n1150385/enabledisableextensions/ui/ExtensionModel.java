package lapr4.red.s1.core.n1150385.enabledisableextensions.ui;

import csheets.ext.Extension;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

/**
 * Model class to handle the extensions in a JList
 * Created by Ricardo Catalao (1150385) on 6/1/17.
 */
public class ExtensionModel implements ListModel<Extension> {

    private ArrayList<Extension> list = new ArrayList<>();
    private ArrayList<ListDataListener> dataListeners = new ArrayList<>();

    public ExtensionModel(Extension[] startingValues){
        for(Extension extension : startingValues){
            list.add(extension);
        }
    }

    public void addExtension(Extension extension){
        if(extension != null && !list.contains(extension)){
            list.add(extension);
            ListDataEvent event = new ListDataEvent(extension, ListDataEvent.CONTENTS_CHANGED, 0, list.size());
            fireContentsChanged(event);
        }
    }

    public void removeExtension(Extension extension){
        if(extension != null && list.contains(extension)){
            list.remove(extension);
            ListDataEvent event = new ListDataEvent(extension, ListDataEvent.CONTENTS_CHANGED, 0, list.size());
            fireContentsChanged(event);
        }
    }

    public void fireContentsChanged(ListDataEvent event){
        for(ListDataListener listener : dataListeners){
            listener.contentsChanged(event);
        }
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Extension getElementAt(int i) {
        return list.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {
        dataListeners.add(listDataListener);
    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {
        dataListeners.remove(listDataListener);
    }
}
