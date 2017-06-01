package lapr4.red.s1.core.n1150623.labelsForContacts;

/**
 * Created by Guilherme Ferreira a 1150623 on 01/06/2017.
 */
public class LabelsToPDF implements GenericExport<LabelList>{

    public LabelsToPDF(){
        //For ORM
    }

    @Override
    public boolean export(LabelList list){
        //TODO: generate PDF based on label
        return true;
    }
}
