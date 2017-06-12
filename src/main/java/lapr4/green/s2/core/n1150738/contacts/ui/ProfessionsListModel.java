package lapr4.green.s2.core.n1150738.contacts.ui;

import lapr4.green.s2.core.n1150738.contacts.domain.Profession;

import javax.swing.*;
import java.util.List;

/**
 * Represents a list model for professions
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class ProfessionsListModel extends AbstractListModel<Profession> {

    private List<Profession> list;

    public ProfessionsListModel(List<Profession> list){
        this.list = list;
    }


    @Override
    public int getSize() {
        return list.size();
    }


    @Override
    public Profession getElementAt(int index) {
        return list.get(index);
    }

}
