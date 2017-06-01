package lapr4.red.s1.core.n1150943.contacts.ui;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.red.s1.core.n1150943.contacts.application.AddEventController;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddEventDialog extends JDialog {

    private AddEventController controller = null;
    private Contact selectedContact = null;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textAreaDescription;

    private JComboBox comboBoxDay;
    private JComboBox comboBoxMonth;
    private JComboBox comboBoxYear;

    /**
     * Creates a JComboBox filled with year values (1900-2100)
     *
     * @return JComboBox with Years
     */
    private JComboBox createYear() {
        JComboBox year = new JComboBox();
        int curr_year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = curr_year; i <= curr_year + 100; i++) {
            year.addItem("" + i); //$NON-NLS-1$
        }
        return year;
    }

    /**
     * Creates a JComboBox with all months.
     *
     * @return JComboBox filled with months
     */
    private JComboBox createMonth() {
        JComboBox month = new JComboBox();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM");
        Calendar currentCal = Calendar.getInstance();
        currentCal.set(Calendar.DAY_OF_MONTH, 1);

        for (int i = 0; i < 12; i++) {
            currentCal.set(Calendar.MONTH, i);
            String myString = dateFormat.format(currentCal.getTime());
            month.addItem(myString);
        }
        return month;
    }

    /**
     * Creates a JComboBox with all month days.
     *
     * @return JComboBox filled with month days
     */
    private JComboBox createDays() {
        JComboBox days = new JComboBox();

        for (int i = 1; i <= 31; i++) {
            String myString = String.format("%d", i);
            days.addItem(myString);
        }
        return days;
    }

    public AddEventDialog(AddEventController ctrl, Contact c) {

        //initComponents();
        selectedContact = c;
        controller = ctrl;

        comboBoxMonth = createMonth();
        comboBoxYear = createYear();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.pack();
        this.setVisible(true);

        comboBoxDay = createDays();
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        System.exit(0);
    }

    private void onOK() {
        int day, month, year;

        if(comboBoxDay.getSelectedIndex()==-1|comboBoxMonth.getSelectedIndex()==-1|comboBoxYear.getSelectedIndex()==-1){

        }

        day = (int) comboBoxDay.getSelectedItem();
        month = (int) comboBoxMonth.getSelectedItem();
        year = (int) comboBoxYear.getSelectedItem();
        Calendar cal = Calendar.getInstance();
        cal.set(day, month, year);
        String descr = textAreaDescription.getText();
        try {
            controller.addEvent(selectedContact, descr, cal);
        } catch (DataConcurrencyException e) {
            e.printStackTrace();
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }


//    private void initComponents() {
//        contentPane = new JPanel();
//        JPanel panelDescription = new JPanel();
//        JPanel panelDate = new JPanel();
//        buttonOK = new JButton();
//        buttonCancel = new JButton();
//        JPanel panelButtons = new JPanel();
//        JPanel panel = new JPanel();
//        JLabel label1 = new JLabel();
//        textArea1 = new JTextArea();
//        JPanel panel5 = new JPanel();
//        JLabel label2 = new JLabel();
//        comboBoxYear = new JComboBox();
//        comboBoxMonth = new JComboBox();
//        comboBoxDay = new JComboBox();
//
//        buttonOK.setText("OK");
//        panel2.add(buttonOK);
//
//        //---- buttonCancel ----
//        buttonCancel.setText("Cancel");
//        panel2.add(buttonCancel);
//        //---- label1 ----
//        label1.setText("Event Description");
//        panel4.add(label1);
//        panel4.add(textArea1);
//        //---- label2 ----
//        label2.setText("Due Date");
//    }
}