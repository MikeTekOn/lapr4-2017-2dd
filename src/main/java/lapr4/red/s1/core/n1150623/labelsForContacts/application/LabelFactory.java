package lapr4.red.s1.core.n1150623.labelsForContacts.application;

import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;

/**
 * Created by Guilherme Ferreira 1150623 on 01/06/2017.
 */
public class LabelFactory{

    public LabelFactory(){
        //For ORM
    }

    public Label construct(String name, String photo, String email, String address, String phoneNumber) {
        Label label = new Label();
        try {
            label.fillLabel(name, photo, address, email, phoneNumber);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return label;
    }

}
