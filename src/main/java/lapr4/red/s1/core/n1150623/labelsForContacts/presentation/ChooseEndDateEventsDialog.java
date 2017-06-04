package lapr4.red.s1.core.n1150623.labelsForContacts.presentation;

import eapli.util.DateTime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ChooseEndDateEventsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField dateField;
    private Calendar chosenDate;
    private Calendar chosen;

    public ChooseEndDateEventsDialog(Calendar endDate) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        endDate = chosenDate;
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        int day = 0, month = 0, year = 0;
        boolean validDate = true;

        String date = dateField.getText().trim();

        String[] splitedDate = date.split("-");
        if (splitedDate.length != 3) {
            validDate = false;
        } else {
            try {
                day = Integer.parseInt(splitedDate[0].trim());
                month = Integer.parseInt(splitedDate[1].trim());
                year = Integer.parseInt(splitedDate[2].trim());
            } catch (NumberFormatException e) {
                validDate = false;
            }
        }

        if (validDate) {
            chosenDate = DateTime.newCalendar(year, month, day);
            if (chosenDate == null) {
                JOptionPane.showMessageDialog(null, "Invalid Date!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Date!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ChooseEndDateEventsDialog dialog = new ChooseEndDateEventsDialog(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
