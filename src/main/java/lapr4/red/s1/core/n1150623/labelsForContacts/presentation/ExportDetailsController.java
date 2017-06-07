package lapr4.red.s1.core.n1150623.labelsForContacts.presentation;

import com.itextpdf.text.pdf.PdfName;
import lapr4.red.s1.core.n1150623.labelsForContacts.application.LabelsForContactsController;
import ui.DatePicker;

import javax.swing.*;
import java.util.Calendar;

/**
 *
 * @author Guilherme Ferreira 1150623
 */
public class ExportDetailsController {
    
    
    private String path;
    private String name;
    private Calendar date;
    private LabelsForContactsController controller;
    private LabelsForContactsUI ui;

    public ExportDetailsController(LabelsForContactsController controller, LabelsForContactsUI ui){
        this.controller = controller;
        this.ui = ui;
    }

    public void setPath(String s){
        this.path = s;
    }
    

    
    public String getPath(){
        return this.path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.path += "\\" + name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
    
    
    public void runPathChooser(){
        ChoosePathAndNameUI a = new ChoosePathAndNameUI(this);
        a.setVisible(true);
    }
    
    public void runDateChooser(){

        JFrame j = new JFrame();
        JPanel p = new JPanel();
        j.add(p);
        Calendar pickedDueDate = new DatePicker(p).getPickedDate();;
        this.date = pickedDueDate;
        runPathChooser();
        /*
        ChooseEndDateEventsUI a = new ChooseEndDateEventsUI(this);
        a.setVisible(true);
        */
    }

    public void  finish(){
        controller.setPath(path);
        if(date!=null){
            controller.limitEvents(date);
        }else{
            controller.removeEvents();
        }

        String export = "Success";
        if (!controller.doExport()) {
            export = "Failed";
        }
        JOptionPane.showMessageDialog(null, "Exportation: " + export, "Information", JOptionPane.INFORMATION_MESSAGE);
        ui.dispose();
    }
}
