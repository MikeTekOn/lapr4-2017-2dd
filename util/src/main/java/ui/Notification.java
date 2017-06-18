package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

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
        // Create the GUI on the event-dispatching thread
        SwingUtilities.invokeLater(new NotificationRunnable(downloadFrame, message));
    }
}