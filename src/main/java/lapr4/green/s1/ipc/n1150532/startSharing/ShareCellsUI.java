package lapr4.green.s1.ipc.n1150532.startSharing;

import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Meireles
 */
public class ShareCellsUI extends JDialog {

    private final UIController uiController;
    private final ShareCellsController controller;
    private final String title;
    private JTextField inFirstCellRow;
    private JTextField inFirstCellColumn;
    private JTextField inLastCellRow;
    private JTextField inLastCellColumn;
    private JButton btShare;
    private JButton btCancel;
    private JLabel outInformation;

    public ShareCellsUI(String theTitle, UIController theUIController, ConnectionID connection){
        uiController = theUIController;
        controller = new ShareCellsController(connection);
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
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createCellsRangePanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
        setTitle(title);
        pack();
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JPanel createHeaderPanel(){
        final String headerText = "Write the range cells address:";
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel(headerText));
        return panel;
    }

    private JPanel createCellsRangePanel(){
        final String firstColumnHeader = "Cell";
        final String secondColumnHeader = "Row";
        final String thirdColumnHeader = "Column";
        final String firstCellHeader = "First";
        final String lastCellHeader = "Last";
        final int size = 5;
        final int allignment = FlowLayout.CENTER;
        final JPanel panel = new JPanel(new GridLayout(3,3));
        final JPanel p11 = new JPanel(new FlowLayout(allignment));
        final JPanel p12 = new JPanel(new FlowLayout(allignment));
        final JPanel p13 = new JPanel(new FlowLayout(allignment));
        final JPanel p21 = new JPanel(new FlowLayout(allignment));
        final JPanel p22 = new JPanel(new FlowLayout(allignment));
        final JPanel p23 = new JPanel(new FlowLayout(allignment));
        final JPanel p31 = new JPanel(new FlowLayout(allignment));
        final JPanel p32 = new JPanel(new FlowLayout(allignment));
        final JPanel p33 = new JPanel(new FlowLayout(allignment));
        inFirstCellRow = new JTextField(size);
        inFirstCellColumn = new JTextField(size);
        inLastCellRow = new JTextField(size);
        inLastCellColumn = new JTextField(size);
        p11.add(new JLabel(firstColumnHeader));
        p12.add(new JLabel(secondColumnHeader));
        p13.add(new JLabel(thirdColumnHeader));
        p21.add(new JLabel(firstCellHeader));
        p22.add(inFirstCellRow);
        p23.add(inFirstCellColumn);
        p31.add(new JLabel(lastCellHeader));
        p32.add(inLastCellRow);
        p33.add(inLastCellColumn);
        panel.add(p11);
        panel.add(p12);
        panel.add(p13);
        panel.add(p21);
        panel.add(p22);
        panel.add(p23);
        panel.add(p31);
        panel.add(p32);
        panel.add(p33);
        return panel;
    }

    private JPanel createBottomPanel(){
        final String cancelBtText = "Cancel";
        final String shareBtText = "Share";
        final int allignment = FlowLayout.CENTER;
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel p1 = new JPanel(new FlowLayout(allignment));
        final JPanel p2 = new JPanel(new GridLayout(1,2));
        final JPanel p2left = new JPanel(new FlowLayout(allignment));
        final JPanel p2right = new JPanel(new FlowLayout(allignment));
        outInformation = new JLabel(" ");
        btCancel = new JButton(cancelBtText);
        btShare = new JButton(shareBtText);
        p2left.add(btCancel);
        p2right.add(btShare);
        p1.add(outInformation);
        p2.add(p2left);
        p2.add(p2right);
        panel.add(p1, BorderLayout.CENTER);
        panel.add(p2, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * It sets the UI to its initial state.
     */
    private void setInitialState() {
        
    }

    /**
     * It creates the action listeners.
     */
    private void createInteractions() {
        btCancel.addActionListener(new CancelAction());
        btShare.addActionListener(new ShareAction());
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
    private class ShareAction implements ActionListener {

        /**
         * It saves the changes. Any error found is shown on the window bottom.
         *
         * @param e Not used
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controller.shareCells(uiController.getActiveSpreadsheet(), inFirstCellRow.getText(), inFirstCellColumn.getText(), inLastCellRow.getText(), inLastCellColumn.getText());
                dispose();
            } catch (IllegalArgumentException ex) {
                outInformation.setText(ex.getMessage());
            }
        }

    }

}
