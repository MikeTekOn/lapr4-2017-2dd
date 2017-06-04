package lapr4.white.s1.core.n4567890.contacts.application;

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

    public Label construct(String name, String photo, Set<String> email, Set<String> addresses,  Set<String> phoneNumbers){
        Label label = new Label();
        try {
            label.fillLabel(name, photo, addresses, email, phoneNumbers);
        }catch(Exception e){
            return null;
        }
        return label;
    }

}
