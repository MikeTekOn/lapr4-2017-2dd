package ui;

import ui.Notification;

import javax.swing.*;
import java.awt.*;

import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;


/**
 * Created by j040_ on 18/06/2017.
 */
public class NotificationRunnable implements Runnable {

    JFrame frame;
    String message;

    public NotificationRunnable(JFrame frame, String message){
        this.frame = frame;
        this.message = message;
    }

    @Override
    public void run() {
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        final boolean isTranslucencySupported =
                gd.isWindowTranslucencySupported(TRANSLUCENT);

        //If shaped windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT)) {
            System.err.println("Shaped windows are not supported");
            System.exit(0);
        }

        //If translucent windows aren't supported,
        //create an opaque window.
        if (!isTranslucencySupported) {
            System.out.println(
                    "Translucency is not supported, creating an opaque window");
        }

        Notification notification = new Notification(frame, message);

        // Set the window to 70% translucency, if supported.
        if (isTranslucencySupported) {
            notification.setOpacity(0.7f);
        }

        // Display the window.
        notification.setVisible(true);
        Toolkit.getDefaultToolkit().beep();
    }
}
