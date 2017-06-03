package lapr4.green.s1.ipc.n1150532.comm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The UI to change the UDP and TCP server's port numbers within the user
 * properties.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class ConfigurePortUI extends JDialog {

    /**
     * The controller of the UI.
     */
    private final ConfigurePortController controller;

    /**
     * The user properties to be changed.
     */
    private final Properties userProperties;

    /**
     * The title of the window.
     */
    private final String title;

    /**
     * The input field for the UDP port number.
     */
    private JTextField inUDPPortNumber;

    /**
     * The input field for the TCP port number.
     */
    private JTextField inTCPPortNumber;

    /**
     * The button to cancel the operation.
     */
    private JButton btCancel;

    /**
     * The button to save the changes.
     */
    private JButton btConfirm;

    /**
     * A label to exhibit error messages.
     */
    private JLabel outInformation;

    /**
     * The full constructor of the UI.
     *
     * @param theTitle The title of the window.
     * @param theUserProperties The user properties to be changed.
     */
    public ConfigurePortUI(String theTitle, Properties theUserProperties) {
        controller = new ConfigurePortController(theUserProperties);
        userProperties = theUserProperties;
        title = theTitle;
        createUserInterface();
        setInitialState();
        createInteractions();
        setVisible(true);
    }

    /**
     * It builds the window.
     */
    private void createUserInterface() {
        setLayout(new BorderLayout());
        add(createUDPPanel(), BorderLayout.NORTH);
        add(createTCPPanel(), BorderLayout.CENTER);
        add(createButtonsPanel(), BorderLayout.SOUTH);
        setTitle(title);
        pack();
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * It builds the UDP configuration panel.
     *
     * @return It returns the panel.
     */
    private JPanel createUDPPanel() {
        final String label = "UDP port number: ";
        final int size = 5;
        final JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel(label));
        inUDPPortNumber = new JTextField(size);
        panel.add(inUDPPortNumber);
        return panel;
    }

    /**
     * It builds the TCP configuration panel.
     *
     * @return It returns the panel.
     */
    private JPanel createTCPPanel() {
        final String label = "TCP port number: ";
        final int size = 5;
        final JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel(label));
        inTCPPortNumber = new JTextField(size);
        panel.add(inTCPPortNumber);
        return panel;
    }

    /**
     * It builds the bottom panel with the buttons and the label to show errors.
     *
     * @return It returns the panel.
     */
    private JPanel createButtonsPanel() {
        final String cancelBtText = "Cancel";
        final String confirmBtText = "Confirm";
        final int allignment = FlowLayout.CENTER;
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel p1 = new JPanel(new FlowLayout(allignment));
        outInformation = new JLabel(" ");
        final JPanel p2 = new JPanel(new GridLayout(1, 2));
        final JPanel p2left = new JPanel(new FlowLayout(allignment));
        final JPanel p2right = new JPanel(new FlowLayout(allignment));
        btCancel = new JButton(cancelBtText);
        btConfirm = new JButton(confirmBtText);
        p2left.add(btCancel);
        p2right.add(btConfirm);
        p1.add(outInformation);
        p2.add(p2right);
        p2.add(p2left);
        panel.add(p1, BorderLayout.CENTER);
        panel.add(p2, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * It sets the UI to its initial state.
     */
    private void setInitialState() {
        inUDPPortNumber.setText(userProperties.getProperty("comm.udp.server.portNumber"));
        inTCPPortNumber.setText(userProperties.getProperty("comm.tcp.server.portNumber"));
    }

    /**
     * It adds the action listeners.
     */
    private void createInteractions() {
        btCancel.addActionListener(new CancelAction());
        btConfirm.addActionListener(new ConfirmAction());
    }

    /**
     * It parses a String to an Integer.
     *
     * @param txt The String to parse.
     * @return It returns the matching Integer or launches an exception with a
     * message if not able to parse.
     */
    private int parseTextToInt(String txt) {
        try {
            return Integer.parseInt(txt);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Please insert a valid integer number.");
        }
    }

    /**
     * The action listener of the cancel button.
     */
    private class CancelAction implements ActionListener {

        /**
         * It disposes the window.
         *
         * @param e Not used.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    /**
     * The action listener of the confirm button.
     */
    private class ConfirmAction implements ActionListener {

        /**
         * It saves the changes. Any error found is shown on the window bottom.
         *
         * @param e Not used
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controller.save(parseTextToInt(inUDPPortNumber.getText()), parseTextToInt(inTCPPortNumber.getText()));
                dispose();
            } catch (IllegalArgumentException ex) {
                outInformation.setText(ex.getMessage());
            }
        }

    }
}
