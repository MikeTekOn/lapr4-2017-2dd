package lapr4.red.s1.core.n1150623.labelsForContacts;

import lapr4.white.s1.core.n4567890.contacts.domain.Contact;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guima on 05/06/2017.
 */
public class ContactListModel extends AbstractListModel<Contact>{

    private List<Contact> contacts;


    public ContactListModel(){
        this.contacts = new ArrayList<>();
    }

    public boolean add(Contact c){
        return this.contacts.add(c);
    }


    public ContactListModel(List<Contact> contacts){
        this.contacts = new ArrayList<>();
        this.contacts.addAll(contacts);
    }

    @Override
    public int getSize() {
        return contacts.size();
    }

    @Override
    public Contact getElementAt(int index) {
        return contacts.get(index);
    }
}
