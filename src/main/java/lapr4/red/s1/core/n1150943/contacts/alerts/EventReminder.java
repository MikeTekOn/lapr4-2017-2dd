package lapr4.red.s1.core.n1150943.contacts.alerts;

import java.awt.*;
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


    /**
     * For now this class isn't being used because the Event Due Date precision is the day, not hour
     * this makes the timers a bit useless, this can and should be used when the event has an hour so the system
     * can alert the user an hour before the event for example
     * @param date
     * @param eventDescription
     */
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
            }
        }

}
