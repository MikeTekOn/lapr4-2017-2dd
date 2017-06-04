package lapr4.red.s1.core.n1150623.labelsForContacts.application;

import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;
import lapr4.white.s1.core.n4567890.contacts.persistence.jpa.JpaRepositoryFactory;

import java.util.Set;

/**
 * Created by guima on 01/06/2017.
 */
public class LabelFactory{

    public LabelFactory(){
        //For ORM
    }

    public Label construct(String name, String photo, String email, String address,  String phoneNumber){
        Label label = new Label();
        try {
            label.fillLabel(name, photo, address, email, phoneNumber);
        }catch(Exception e){
            return null;
        }
        return label;
    }

}
