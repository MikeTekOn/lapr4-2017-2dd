package lapr4.red.s1.core.n1150943.contacts.alerts;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by k4rd050 on 01-06-2017.
 */
public class ShowAlertAction {

    public static void showAlert(String description, Calendar date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        String eventDate = dateFormat.format(date);
        String text = String.format("Event Description: %s\nDue Date: %s",description,eventDate);
        JOptionPane.showConfirmDialog(null,text,"You have an event in ",JOptionPane.INFORMATION_MESSAGE);
    }

}
