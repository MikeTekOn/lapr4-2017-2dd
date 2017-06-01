package lapr4.red.s1.core.n1150623.labelsForContacts;

import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;

import java.util.ArrayList;

/**
 *
 * This class was created to help the exportation process of the labels, grouping them this way, makes it easier to export them all together.
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
        return wantedLabels.add(label);
    }

    /**
     * For Use Case [Core][LAPR4E17DD-48][Labels for Contacts]
     * Exports the label's information to a pdf file according to the 'LabelsToPDF' class
     * @return true if there was no error during export and false otherwise
     */
    public boolean exportPDF(){
        boolean canExport = true;
        if(wantedLabels.isEmpty()){
            canExport = false;
        }else{
            exp = new LabelsToPDF();
            canExport = exportPDF();
        }
        return canExport;
    }
}
