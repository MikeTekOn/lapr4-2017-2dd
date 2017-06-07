package lapr4.red.s1.core.n1150623.labelsForContacts;

import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * This class was created to help the exportation process of the labels, grouping them this way, makes it easier to export them all together.
 * Also, this class avoids having the Labels stored directly in a Controller and takes the responsibility of storing Labels for herself.
 *
 * Created by Guilherme Ferreira 1150623 on 01/06/2017.
 */
public class LabelList {

    /**
     * List of labels - stores labels
     */
    ArrayList<Label> wantedLabels;

    /**
     * In charge of exportation
     */
    GenericExport<LabelList> exp;


    public LabelList(){
        wantedLabels = new ArrayList<>();
    }

    /**
     * Adds one more label to the label list
     * @param label - label to add
     * @return true if it was successful added to list
     */
    public boolean addLabel(Label label){
        boolean valid = false;
        if(!wantedLabels.contains(label) || label == null){
            System.err.println("name: " + label.name()
                                + "\nphoto" + label.photo()
                                + "\naddress" + label. address()
                                + "\nphoneNumber" + label.phoneNumber());
            valid = wantedLabels.add(label);
        }
        return valid;
    }

    public List<Label> labels(){
        return wantedLabels;
    }

    /**
     * For Use Case [Core][LAPR4E17DD-48][Labels for Contacts]
     * Exports the label's information to a pdf file according to the 'LabelsToPDF' class
     * @return true if there was no error during export and false otherwise
     */
    public boolean exportPDF(){
        boolean canExport = true;
        if(wantedLabels.isEmpty() || path.trim().length() == 0 || path == null || wantedLabels.contains(null)){
            canExport = false;
        }else{
            exp = new LabelsToPDF();
            exp.export(this);
        }
        return canExport;
    }

    public void limitEvents(Calendar endDate) {

        for (Label lab : wantedLabels) {
            lab.deleteEventsOutsideBoundaries(endDate);
        }
    }

    String path;

    public void choosePath(String path){
        this.path = path;
    }

    public String path(){
        return this.path;
    }

    public void removeEvents() {

        for (Label l : wantedLabels) {
            l.removeEvents();
        }
    }
}
