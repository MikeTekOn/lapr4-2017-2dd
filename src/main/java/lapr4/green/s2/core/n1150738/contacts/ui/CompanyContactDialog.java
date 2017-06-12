/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150738.contacts.ui;

import csheets.CleanSheets;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.green.s2.core.n1150738.contacts.application.CompanyContactController;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.green.s2.core.n1150738.contacts.domain.Profession;
import lapr4.white.s1.core.n4567890.contacts.ui.SpringUtilities;
import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyContactDialog extends JDialog implements ActionListener {
    private static CompanyContactDialog dialog;
    private static String value = "";
    private JList list;

    private static CompanyContact _contact = null;
    private static boolean _success = false;


    public enum CompanyContactDialogMode {
        ADD,
        DELETE,
        EDIT
    }

    public static CompanyContact contact() {
        return _contact;
    }

    public static boolean successResult() {
        return _success;
    }

    private CompanyContactDialogMode mode = CompanyContactDialogMode.ADD;
    private CompanyContactController ctrl = null;

    /**
     * Set up and show the dialog.  The first Component argument
     * determines which frame the dialog depends on; it should be
     * a component in the dialog's controlling frame. The second
     * Component argument should be null if you want the dialog
     * to come up with its left corner in the center of the screen;
     * otherwise, it should be the component on top of which the
     * dialog should appear.
     */
    public static void showDialog(Component frameComp,
                                  Component locationComp,
                                  CompanyContactController ctrl,
                                  CompanyContactDialogMode mode,
                                  String title, CompanyContact contact) {
        _success = false;
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        dialog = new CompanyContactDialog(frame,
                locationComp,
                ctrl,
                mode,
                title, contact);
        dialog.setVisible(true);
    }

    public static void showDialog(Component frameComp,
                                  Component locationComp,
                                  CompanyContactController ctrl,
                                  CompanyContactDialogMode mode,
                                  String title) {

        showDialog(frameComp, locationComp, ctrl, mode, title, null);
    }

    // Widgets
    private JButton confirmButton = null;
    private JButton cancelButton = null;

    private JLabel nameLabel = null;
    //private JLabel addressLabel = null;
    private JLabel emailLabel = null;
    private JLabel phoneLabel = null;

    private JTextField nameField = null;
    //private JTextField addressField = null;
    private JTextField emailField = null;
    private JTextField phoneField = null;

    private JPanel formPanel = null;
    private JPanel buttonPanel = null;

    private JLabel statusLabel = null;

    private void setupContactsWidgets() {
        formPanel = new JPanel(new SpringLayout());

        // FullName
        nameLabel = new JLabel("Name:", JLabel.TRAILING);
        nameField = new JTextField(30);
        nameLabel.setLabelFor(nameField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        // Phone
        phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(10);
        phoneLabel.setLabelFor(phoneField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        // Email
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(10);
        emailLabel.setLabelFor(emailField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        SpringUtilities.makeCompactGrid(formPanel,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        // Last Pane: A row of buttons and the end
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        confirmButton = new JButton(CleanSheets.getString("confirm_button"));
        confirmButton.setActionCommand("confirm");
        confirmButton.addActionListener(this);
        cancelButton = new JButton(CleanSheets.getString("cancel_button"));
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Final Pane: The status label for messages
        statusLabel = new JLabel();


        switch (this.mode) {
            case ADD:
                statusLabel.setText(CleanSheets.getString("status_please_enter_data_for_new_contcat"));
                break;
            case DELETE:
                statusLabel.setText(CleanSheets.getString("status_please_confirm_contact_to_delete"));

                // All fields in read-only mode
                this.nameField.setEditable(false);
                this.emailField.setEditable(false);
                this.phoneField.setEditable(false);
                //this.addressField.setEditable(false);
                break;
            case EDIT:
                statusLabel.setText(CleanSheets.getString("status_please_update_data_of_contcat"));
                break;
        }


        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        //contentPane.add(picPanel, BorderLayout.BEFORE_FIRST_LINE);
        contentPane.add(formPanel, BorderLayout.PAGE_START);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.PAGE_END);
    }

    private void setupData() {
        if (_contact != null) {
            this.nameField.setText(_contact.companyName().toString());
            this.emailField.setText(_contact.mail().toString());
            this.phoneField.setText(_contact.phoneNumber().toString());
            //this.addressField.setText(_contact.address());
        }
    }

    private CompanyContactDialog(Frame frame,
                                 Component locationComp,
                                 CompanyContactController ctrl,
                                 CompanyContactDialogMode mode,
                                 String title, CompanyContact contact) {
        super(frame, title, true);

        this.mode = mode;
        this.ctrl = ctrl;
        _contact = contact;

        setupContactsWidgets();
        setupData();

        pack();
        setLocationRelativeTo(locationComp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("confirm".equals(e.getActionCommand())) {
            switch (this.mode) {
                case ADD: {
                    try {
                        // The User confirms the creation of a Contact
                        if (this.nameField.getText().isEmpty() | this.phoneField.getText().isEmpty() | this.emailField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(rootPane, "All fields must be filled");
                        } else {
                            byte[] image = null;
                            String photo_path = "";
                            int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to add a profile picture?", "Profile Picture", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                JFileChooser chooser = new JFileChooser("Choose profile photo");
                                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                        "JPG & PNG Images", "jpg", "jpeg", "png");
                                chooser.setFileFilter(filter);
                                int returnVal = chooser.showOpenDialog(formPanel);
                                if (returnVal == JFileChooser.APPROVE_OPTION) {
                                    photo_path = chooser.getSelectedFile().getAbsolutePath();
                                }

                                try(FileInputStream fis = new FileInputStream(new File(photo_path))){
                                    image = IOUtils.toByteArray(fis);
                                } catch (IOException ex){
                                    Logger.getLogger(CompanyContactDialog.class.getName()).log(Level.SEVERE, "Cannot read file", ex);
                                }
                            }


                            _contact = this.ctrl.addContact(this.nameField.getText(), this.emailField.getText(), this.phoneField.getText(), image);
                            _success = true;
                            // Exit the dialog
                            CompanyContactDialog.dialog.setVisible(false);
                        }
                    } catch (DataConcurrencyException ex) {
                        Logger.getLogger(CompanyContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                        statusLabel.setForeground(Color.red);
                        statusLabel.setText(CleanSheets.getString("status_data_concurrency_error"));
                    } catch (DataIntegrityViolationException ex) {
                        Logger.getLogger(CompanyContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                        statusLabel.setForeground(Color.red);
                        statusLabel.setText(CleanSheets.getString("status_data_integrity_error"));
                    }
                }
                break;

                case DELETE: {
                    try {
                        boolean r = this.ctrl.removeContact(_contact);

                        _success = true;
                        // Exit the dialog
                        CompanyContactDialog.dialog.setVisible(false);
                    } catch (DataConcurrencyException ex) {
                        Logger.getLogger(CompanyContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                        statusLabel.setForeground(Color.red);
                        statusLabel.setText(CleanSheets.getString("status_data_concurrency_error"));
                    } catch (DataIntegrityViolationException ex) {
                        Logger.getLogger(CompanyContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                        statusLabel.setForeground(Color.red);
                        statusLabel.setText(CleanSheets.getString("status_data_integrity_error"));
                    }
                }
                break;

                case EDIT: {
                    try {
                        if (this.nameField.getText().isEmpty() | this.phoneField.getText().isEmpty() | this.emailField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(rootPane, "All fields must be filled");
                        } else {
                            String photo_path = "";
                            byte[] image = null;
                            int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to add a profile picture?", "Profile Picture", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                JFileChooser chooser = new JFileChooser("Choose profile photo");
                                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                        "JPG & PNG Images", "jpg", "jpeg", "png");
                                chooser.setFileFilter(filter);
                                int returnVal = chooser.showOpenDialog(formPanel);
                                if (returnVal == JFileChooser.APPROVE_OPTION) {
                                    photo_path = chooser.getSelectedFile().getAbsolutePath();
                                }
                                try(FileInputStream fis = new FileInputStream(new File(photo_path))){
                                    image = IOUtils.toByteArray(fis);
                                } catch (IOException ex){
                                    Logger.getLogger(CompanyContactDialog.class.getName()).log(Level.SEVERE, "Cannot read file", ex);
                                }
                            }


                            _contact = this.ctrl.updateContact(_contact, this.nameField.getText(), this.emailField.getText(), this.phoneField.getText(), image);


                            _success = true;
                            // Exit the dialog
                            CompanyContactDialog.dialog.setVisible(false);
                        }
                    } catch (DataConcurrencyException ex) {
                        Logger.getLogger(CompanyContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                        statusLabel.setForeground(Color.red);
                        statusLabel.setText(CleanSheets.getString("status_data_concurrency_error"));
                    } catch (DataIntegrityViolationException ex) {
                        Logger.getLogger(CompanyContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                        statusLabel.setForeground(Color.red);
                        statusLabel.setText(CleanSheets.getString("status_data_integrity_error"));
                    }
                }
                break;
            }
            //ContactDialog.value = (String)(list.getSelectedValue());
        } else {
            _success = false;
            // Exit the dialog
            CompanyContactDialog.dialog.setVisible(false);
        }
    }
}
