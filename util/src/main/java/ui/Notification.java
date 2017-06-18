package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

/**
 * Created by João Cardoso - 1150943 on 14-06-2017.
 * Based on http://tinyurl.com/y8jx3lgs
 */
public class Notification extends JFrame {

    JFrame thisFrame = this;
    JFrame downloadFrame;
    Timer timer;

    public Notification(JFrame downloadFrame, String message) {
        super("Downloading Update");
        this.downloadFrame = downloadFrame;
        setLayout(new GridBagLayout());

        // It is best practice to set the window's shape in
        // the componentResized method.  Then, if the window
        // changes size, the shape will be correctly recalculated.
        addComponentListener(new ComponentAdapter() {
            // Give the window an elliptical shape.
            // If the window is resized, the shape is recalculated here.
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
            }
        });

        setUndecorated(true);
        setSize(500,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Toolkit.getDefaultToolkit().beep();
                thisFrame.dispose();
            }
        });
        timer.start();

        MouseListener mouseListener1 = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                timer.stop();
            }
        };

        MouseListener mouseListener2 = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                timer.restart();
            }
        };

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel(message));

        if(downloadFrame!=null) {
            MouseListener mouseListener3 = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    downloadFrame.setVisible(true);
                }
            };
            panel.addMouseListener(mouseListener3);
        }

        panel.addMouseListener(mouseListener1);
        panel.addMouseListener(mouseListener2);

        this.add(panel);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - this.getWidth();
        int y = (int) rect.getMaxY() - this.getHeight();
        this.setLocation(x, y);
    }

    public static void notifyHost(JFrame downloadFrame, String message) {
        // Determine what the GraphicsDevice can support.
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

        // Create the GUI on the event-dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Notification notification = new Notification(downloadFrame, message);

                // Set the window to 70% translucency, if supported.
                if (isTranslucencySupported) {
                    notification.setOpacity(0.7f);
                }

                // Display the window.
                notification.setVisible(true);
            }
        });
    }
}