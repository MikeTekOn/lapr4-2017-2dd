package lapr4.red.s1.core.n1150943.contacts.alerts;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by João Cardoso - 1150943 on 01-06-2017.
 */
public class EventReminder {

        Toolkit toolkit;

        Timer timer;

        String eventDescription;

        Calendar date;


        public EventReminder(Calendar date, String eventDescription) {
            toolkit = Toolkit.getDefaultToolkit();
            timer = new Timer();
            this.eventDescription = eventDescription;
            this.date=date;
            Calendar reminderDate = (Calendar) date.clone();
            reminderDate.add(Calendar.HOUR, -1);
            timer.schedule(new RemindTask(), new Date());
        }

        class RemindTask extends TimerTask {
            public void run() {
                toolkit.beep();
                ShowAlertAction.showAlert(eventDescription, date);
                System.exit(0); //Stops the thread
            }
        }

}
