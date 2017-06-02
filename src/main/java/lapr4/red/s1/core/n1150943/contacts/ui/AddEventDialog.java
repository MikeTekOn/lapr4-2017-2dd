package lapr4.red.s1.core.n1150943.contacts.ui;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.red.s1.core.n1150943.contacts.application.EventController;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import ui.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

/**
 * Created by João Cardoso - 1150943
 */
public class AddEventDialog extends JDialog {

    private EventController controller = null;
    private Contact selectedContact = null;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonSelectDate;

    private JTextField textFieldDescription;

    private Calendar pickedDueDate = null;

    public AddEventDialog(EventController ctrl, Contact c) {

        initComponents();
        selectedContact = c;
        controller = ctrl;


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        this.pack();
        this.setVisible(true);

    }

    private void onOK() {
        if(pickedDueDate!=null){
            if(!textFieldDescription.getText().isEmpty()){
                String descr = textFieldDescription.getText();
                try {
                    controller.addEvent(selectedContact, descr, pickedDueDate);
                } catch (IllegalStateException e) {
                    JOptionPane.showMessageDialog(null,"The date you picked is in the past");
                } catch (DataConcurrencyException e) {
                    e.printStackTrace();
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                }
                dispose();
            }
        }
    }



    private void initComponents() {
        contentPane = new JPanel(new BorderLayout());
        JPanel panelDescription = new JPanel(new FlowLayout());
        JPanel panelDate = new JPanel(new FlowLayout());
        JPanel panelButtons = new JPanel(new FlowLayout());
        JLabel labelDescr = new JLabel();
        JLabel labelDate = new JLabel();
        buttonSelectDate = new JButton();
        buttonSelectDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                pickedDueDate = new DatePicker(contentPane).getPickedDate();
            }
        });
        buttonOK = new JButton();
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel = new JButton();
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        textFieldDescription = new JTextField(50);

        buttonOK.setText("OK");
        panelButtons.add(buttonOK);

        //---- buttonCancel ----
        buttonCancel.setText("Cancel");
        panelButtons.add(buttonCancel);
        //----Description label ----
        labelDescr.setText("Event Description");
        panelDescription.add(labelDescr);
        panelDescription.add(textFieldDescription);
        //----Due Date label ----
        labelDate.setText("Select due date for the event");
        panelDate.add(labelDate);
        buttonSelectDate.setText("Choose Date");
        panelDate.add(buttonSelectDate);

        contentPane.add(panelDescription,BorderLayout.PAGE_START);
        contentPane.add(panelDate,BorderLayout.CENTER);
        contentPane.add(panelButtons,BorderLayout.PAGE_END);
        setLocationRelativeTo(this.getParent());
    }
}