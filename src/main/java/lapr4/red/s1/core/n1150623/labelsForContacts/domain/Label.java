package lapr4.red.s1.core.n1150623.labelsForContacts.domain;

import lapr4.white.s1.core.n4567890.contacts.domain.Agenda;

/**
 * Created by guima on 01/06/2017.
 *
 * Has contact information -> for now, exists only to export it's information to pdf
 */
public class Label {

    String contact_Foto;
    String contact_Name;
    String contact_LastName;
    String contact_label;



    public Label(){
        //For ORM
    }

    public boolean fillLabel(String name, String lastName, String address, String foto){
        //TODO: Fill method with contact info
        return true;
    }

    public boolean addEvents(Agenda ag){
        //TODO: Fill method with contacts agenda info
        return true;
    }
}
