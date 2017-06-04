/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.ui;

import csheets.CleanSheets;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.white.s1.core.n4567890.contacts.application.ContactController;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import ui.ImageUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandrebraganca
 */
public class ContactDialog extends JDialog implements ActionListener {
    private static ContactDialog dialog;
    private static String value = "";
    private JList list;

    private static Contact _contact=null;
    private static boolean _success=false;

    public enum ContactDialogMode {
        ADD,
        DELETE,
        EDIT
    }

    public static Contact contact() {
        return _contact;
    }

    public static boolean successResult() {
        return _success;
    }

    private ContactDialogMode mode=ContactDialogMode.ADD;
    private ContactController ctrl=null;

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
                                    ContactController ctrl,
                                    ContactDialogMode mode,
                                    String title, Contact contact) {
        _success=false;
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        dialog = new ContactDialog(frame,
                                locationComp,
                                ctrl,
                                mode,
                                title, contact);
        dialog.setVisible(true);
    }

    public static void showDialog(Component frameComp,
                                    Component locationComp,
                                    ContactController ctrl,
                                    ContactDialogMode mode,
                                    String title) {

        showDialog(frameComp, locationComp, ctrl, mode, title, null);
    }

    // Widgets
    private JButton confirmButton=null;
    private JButton cancelButton=null;

    private JLabel fullNameLabel=null;
    private JLabel firstNameLabel=null;
    private JLabel lastNameLabel=null;
    private JLabel addressLabel=null;
    private JLabel emailLabel=null;
    private JLabel phoneLabel=null;


    private JTextField fullNameField=null;
    private JTextField firstNameField=null;
    private JTextField lastNameField=null;
    private JTextField addressField=null;
    private JTextField emailField=null;
    private JTextField phoneField=null;

    private JPanel formPanel=null;
    private JPanel buttonPanel=null;

    private JLabel statusLabel=null;

    private void setupContactsWidgets() {


            formPanel = new JPanel(new SpringLayout());

            // FullName
            fullNameLabel=new JLabel(CleanSheets.getString("full_name_label"), JLabel.TRAILING);
            fullNameField=new JTextField(30);
            fullNameLabel.setLabelFor(fullNameField);
            formPanel.add(fullNameLabel);
            formPanel.add(fullNameField);

            // FirstName
            firstNameLabel=new JLabel(CleanSheets.getString("first_name_label"), JLabel.TRAILING);
            firstNameField=new JTextField(10);
            firstNameLabel.setLabelFor(firstNameField);
            formPanel.add(firstNameLabel);
            formPanel.add(firstNameField);

            // LastName
            lastNameLabel=new JLabel(CleanSheets.getString("last_name_label"), JLabel.TRAILING);
            lastNameField=new JTextField(10);
            lastNameLabel.setLabelFor(lastNameField);
            formPanel.add(lastNameLabel);
            formPanel.add(lastNameField);

            // Phone
            phoneLabel=new JLabel("Phone:");
            phoneField=new JTextField(10);
            phoneLabel.setLabelFor(phoneField);
            formPanel.add(phoneLabel);
            formPanel.add(phoneField);

            // Address
            addressLabel=new JLabel("Address:");
            addressField=new JTextField(10);
            addressLabel.setLabelFor(addressField);
            formPanel.add(addressLabel);
            formPanel.add(addressField);

            // Email
            emailLabel=new JLabel("Email:");
            emailField=new JTextField(10);
            emailLabel.setLabelFor(emailField);
            formPanel.add(emailLabel);
            formPanel.add(emailField);

            SpringUtilities.makeCompactGrid(formPanel,
                                6, 2, //rows, cols
                                6, 6,        //initX, initY
                                6, 6);       //xPad, yPad

            // Last Pane: A row of buttons and the end
            buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            confirmButton=new JButton(CleanSheets.getString("confirm_button"));
            confirmButton.setActionCommand("confirm");
            confirmButton.addActionListener(this);
            cancelButton=new JButton(CleanSheets.getString("cancel_button"));
            cancelButton.setActionCommand("cancel");
            cancelButton.addActionListener(this);
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);
            
            // Final Pane: The status label for messages
            statusLabel=new JLabel();
            
            
            switch (this.mode) {
                case ADD:
                    statusLabel.setText(CleanSheets.getString("status_please_enter_data_for_new_contcat"));
                    break;
                case DELETE:
                    statusLabel.setText(CleanSheets.getString("status_please_confirm_contact_to_delete"));
 
                    // All fields in read-only mode
                    this.fullNameField.setEditable(false);
                    this.firstNameField.setEditable(false);
                    this.lastNameField.setEditable(false);                    
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
        if (_contact!=null) {
            this.fullNameField.setText(_contact.name());
            this.firstNameField.setText(_contact.firstName());
            this.lastNameField.setText(_contact.lastName());
            this.emailField.setText(_contact.email());
            this.phoneField.setText(_contact.phone());
            this.addressField.setText(_contact.address());
        }
    }
 
    private ContactDialog(Frame frame,
                       Component locationComp,
                       ContactController ctrl,
                       ContactDialogMode mode,
                       String title, Contact contact) {
        super(frame, title, true);
        
        this.mode=mode;
        this.ctrl=ctrl;
        _contact=contact;
 
        setupContactsWidgets();
        setupData();
                
        pack();
        setLocationRelativeTo(locationComp);
    }

    /**
     * Edited by João Cardoso - 1150943 (Added option to choose profile photograph for a contact)
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("confirm".equals(e.getActionCommand())) {
            switch (this.mode) {
                case ADD:
                    {
                        try {
                            // The User confirms the creation of a Contact
                            if(this.fullNameField.getText().isEmpty()|this.firstNameField.getText().isEmpty()|this.lastNameField.getText().isEmpty()|this.addressField.getText().isEmpty()|this.phoneField.getText().isEmpty()|this.emailField.getText().isEmpty()){
                                JOptionPane.showMessageDialog(rootPane,"All fields must be filled");
                            }else{
                                String photo_path="";
                                int option=JOptionPane.showConfirmDialog(rootPane,"Do you want to add a profile picture?","Profile Picture",JOptionPane.YES_NO_OPTION);
                                if(option==JOptionPane.YES_OPTION){
                                    JFileChooser chooser = new JFileChooser("Choose profile photo");
                                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                            "JPG & PNG Images", "jpg", "jpeg", "png");
                                    chooser.setFileFilter(filter);
                                    int returnVal = chooser.showOpenDialog(formPanel);
                                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                                        photo_path = chooser.getSelectedFile().getAbsolutePath();
                                    }
                                }
                                _contact=this.ctrl.addContact(this.fullNameField.getText(), this.firstNameField.getText(), this.lastNameField.getText(), photo_path, this.addressField.getText(), this.emailField.getText(), this.phoneField.getText());
                                _success=true;
                                // Exit the dialog
                                ContactDialog.dialog.setVisible(false);
                            }
                        } catch (DataConcurrencyException ex) {
                            Logger.getLogger(ContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                            statusLabel.setForeground(Color.red);
                            statusLabel.setText(CleanSheets.getString("status_data_concurrency_error"));
                        } catch (DataIntegrityViolationException ex) {
                            Logger.getLogger(ContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                            statusLabel.setForeground(Color.red);
                            statusLabel.setText(CleanSheets.getString("status_data_integrity_error"));
                        }
                    }
                    break;
                    
                case DELETE:
                    {
                        try {
                            boolean r;
                            try {
                                r = this.ctrl.removeContact(_contact);
                            }catch(IllegalAccessException ex){
                                JOptionPane.showMessageDialog(getContentPane(),"You can't remove this contact","Warning", JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                            _success=true;
                            // Exit the dialog
                            ContactDialog.dialog.setVisible(false);
                        } catch (DataConcurrencyException ex) {
                            Logger.getLogger(ContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                            statusLabel.setForeground(Color.red);
                            statusLabel.setText(CleanSheets.getString("status_data_concurrency_error"));
                        } catch (DataIntegrityViolationException ex) {
                            Logger.getLogger(ContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                            statusLabel.setForeground(Color.red);
                            statusLabel.setText(CleanSheets.getString("status_data_integrity_error"));                            
                        }                        
                    }
                    break;
                
                case EDIT:
                    {
                        try {
                            if(this.fullNameField.getText().isEmpty()|this.firstNameField.getText().isEmpty()|this.lastNameField.getText().isEmpty()|this.addressField.getText().isEmpty()|this.phoneField.getText().isEmpty()|this.emailField.getText().isEmpty()){
                                JOptionPane.showMessageDialog(rootPane,"All fields must be filled");
                            }else {
                                String photo_path = _contact.photo();
                                int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to edit the profile picture?", "Profile Picture", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    JFileChooser chooser = new JFileChooser("Choose profile photo");
                                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                            "JPG & PNG Images", "jpg", "jpeg", "png");
                                    chooser.setFileFilter(filter);
                                    int returnVal = chooser.showOpenDialog(formPanel);
                                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                                        photo_path = chooser.getSelectedFile().getAbsolutePath();
                                    }
                                }
                                try {
                                    _contact = this.ctrl.updateContact(_contact, this.fullNameField.getText(), this.firstNameField.getText(), this.lastNameField.getText(), photo_path, this.addressField.getText(), this.emailField.getText(), this.phoneField.getText());
                                } catch (IllegalAccessException e1) {
                                    JOptionPane.showMessageDialog(getContentPane(), "You can't edit this contact", "Warning", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }

                                _success = true;
                                // Exit the dialog
                                ContactDialog.dialog.setVisible(false);
                            }
                        } catch (DataConcurrencyException ex) {
                            Logger.getLogger(ContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                            statusLabel.setForeground(Color.red);
                            statusLabel.setText(CleanSheets.getString("status_data_concurrency_error"));
                        } catch (DataIntegrityViolationException ex) {
                            Logger.getLogger(ContactDialog.class.getName()).log(Level.SEVERE, null, ex);
                            statusLabel.setForeground(Color.red);
                            statusLabel.setText(CleanSheets.getString("status_data_integrity_error"));                            
                        }                        
                    }
                    break;
            }
            //ContactDialog.value = (String)(list.getSelectedValue());
        }
        else {
            _success=false;
            // Exit the dialog
            ContactDialog.dialog.setVisible(false);
        }
    }
}
