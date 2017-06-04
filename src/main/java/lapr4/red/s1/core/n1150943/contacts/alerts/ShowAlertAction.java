package lapr4.red.s1.core.n1150943.contacts.alerts;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by João Cardoso - 1150943 on 01-06-2017.
 */
public class ShowAlertAction {

    public static void showAlert(String description, Calendar date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd z yyyy");
        Date d = date.getTime();
        String eventDate = dateFormat.format(d);
        String text = String.format("Event Description: %s\nDue Date: %s",description,eventDate);
        JOptionPane.showMessageDialog(null,text,"You have an event today",JOptionPane.INFORMATION_MESSAGE);
    }

}
