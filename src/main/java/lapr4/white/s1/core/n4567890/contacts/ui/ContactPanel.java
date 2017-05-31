/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import eapli.util.*;
import lapr4.red.s1.core.n1150943.contacts.application.AddEventController;
import lapr4.red.s1.core.n1150943.contacts.ui.AddEventDialog;
import lapr4.white.s1.core.n4567890.contacts.ContactsExtension;
import lapr4.white.s1.core.n4567890.contacts.application.ContactController;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import ui.ImageUtils;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


/**
 * A panel for adding or editing a comment for a cell
 * @author Alexandre Braganca
 */
@SuppressWarnings("serial")
public class ContactPanel extends JPanel implements ActionListener {

        // Controller for Contacts
	private ContactController controller=null;

	//Controller for Events
    private AddEventController addEventController=null;

	/** The text field in which the comment of the cell is displayed.*/
        private JTextArea commentField = new JTextArea();

        
        // Controls for the contacts panel
        private JLabel labelContacts=null;
        private JTextField contactsFilterField=null;
        private JList<Contact> contactsList=null;
        private DefaultListModel<Contact> model=null;
        private JButton contactsAddButton=null;
        private JButton contactsRemoveButton=null;
        private JButton contactsEditButton=null;
        private JButton contactsaddEventButton=null;

        private JPanel contactsPane= null;
        private JPanel filterPane = null;
        private JPanel buttonPane = null;
        
        // Action commands
        private final static String addAction="add";
        private final static String removeAction="remove";
        private final static String editAction="edit";
        private final static String addEventAction="add_event";


    private void setupContactsWidgets() {

            labelContacts=new JLabel("Filtro: ");
            
            // First Pane: The "filter", FlowLayout (from left to right)
            filterPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
            contactsFilterField=new JTextField();
            contactsFilterField.setColumns(10);
            
            filterPane.add(labelContacts);
            filterPane.add(contactsFilterField); 

            model = new DefaultListModel();
            Iterable<Contact> contacts=controller.allContacts();
            for(Contact c: contacts) {
                model.addElement(c);
            }
            contactsList = new JList(model);

            MouseListener mouseListener = new MouseAdapter() {
                public void mouseShiftPressed(MouseEvent e) {
                    JDialog dialog = new JDialog();
                    dialog.setUndecorated(true);
                    int index = contactsList.getSelectedIndex();
                    String photoSrc = model.getElementAt(index).photo();
                    File imageFile = new File(photoSrc);
                    ImageIcon profilePhoto=null;
                    try {
                        profilePhoto = ImageUtils.getResizedPhoto(imageFile, 100, 100);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JLabel label = new JLabel(profilePhoto);

                    dialog.add(label);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            };
            contactsList.addMouseListener(mouseListener);

            contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane centerPane=new JScrollPane(contactsList);

            // Last Pane: A row of buttons and the end
            buttonPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
            contactsAddButton=new JButton("Add");
            contactsAddButton.setActionCommand(ContactPanel.addAction);
            contactsAddButton.addActionListener(this);
            contactsRemoveButton=new JButton("Remove");
            contactsRemoveButton.setActionCommand(ContactPanel.removeAction);
            contactsRemoveButton.addActionListener(this);
            contactsEditButton=new JButton("Edit");
            contactsEditButton.setActionCommand(ContactPanel.editAction);
            contactsEditButton.addActionListener(this);
            contactsaddEventButton=new JButton("Add Event");
            contactsaddEventButton.setActionCommand(ContactPanel.addEventAction);
            contactsaddEventButton.addActionListener(this);
            buttonPane.add(contactsAddButton);
            buttonPane.add(contactsRemoveButton);
            buttonPane.add(contactsEditButton);
            buttonPane.add(contactsaddEventButton);
            
            // The parent Pane is of type BorderLayout so that the center list occupies all the "empty" canvas
            contactsPane = new JPanel(new BorderLayout());
            contactsPane.add(filterPane, BorderLayout.PAGE_START);
            contactsPane.add(centerPane, BorderLayout.CENTER);
            contactsPane.add(buttonPane, BorderLayout.PAGE_END);
        }
        
    /**
     * Creates a new comment panel.
     *
     * @param uiController the user interface controller
     */
    public ContactPanel(UIController uiController) {
        // Configures panel
        super(new BorderLayout());
        setName(ContactsExtension.NAME);

        // Creates controllers
        this.controller = new ContactController(uiController.getUserProperties());
        this.addEventController = new AddEventController(uiController.getUserProperties());

        setupContactsWidgets();

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        JPanel agendaPane = new JPanel(new GridLayout(0, 1));

        // Adds borders
        TitledBorder border = BorderFactory.createTitledBorder("Contacts");
        border.setTitleJustification(TitledBorder.CENTER);
        contactsPane.setBorder(border);

        border = BorderFactory.createTitledBorder("Agenda");
        border.setTitleJustification(TitledBorder.CENTER);
        agendaPane.setBorder(border);

        // Creates side bar
        mainPanel.add(contactsPane);
        mainPanel.add(agendaPane);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index=-1;
        
        switch (e.getActionCommand()) {
            case ContactPanel.addAction:
                {
                    Contact c=null;
                    ContactDialog.showDialog(this, contactsAddButton, this.controller,
                                        ContactDialog.ContactDialogMode.ADD, "New Contact");
                    if (ContactDialog.successResult()) {
                        c=ContactDialog.contact();
                        // Update the model of the JList
                        model.addElement(c);
                    }
                }
                break;

            case ContactPanel.removeAction:
                index=contactsList.getSelectedIndex();
                if (index!=-1) {
                    Contact c;
                    //c = (Contact)(contactsList.getModel().getElementAt(index));
                    c = model.getElementAt(index);

                    ContactDialog.showDialog(this, contactsRemoveButton, this.controller,
                                        ContactDialog.ContactDialogMode.DELETE, "Delete Contact", c);
                    if (ContactDialog.successResult()) {
                        // Update the model of the JList
                        model.remove(index);
                    }
                }
                break;

            case ContactPanel.editAction:
                index=contactsList.getSelectedIndex();
                if (index!=-1) {
                    Contact c;
                    //c = (Contact)(contactsList.getModel().getElementAt(index));
                    c = model.getElementAt(index);
                    
                    ContactDialog.showDialog(this, contactsEditButton, this.controller,
                                        ContactDialog.ContactDialogMode.EDIT, "Edit Contact", c);
                    if (ContactDialog.successResult()) {
                        // Update the model of the JList
                        model.set(index, c);
                    }
                    else {
                        // Maybe the user tried to update but failed and canceled. We need to "refresh" the contacts object
                        Contact updatedContact=this.controller.getContactById(c.id());
                        // Update the model of the JList
                        model.set(index, updatedContact);
                    }
                        
                }
                break;
            case ContactPanel.addEventAction:
                index=contactsList.getSelectedIndex();
                if (index!=-1) {
                    Contact c;
                    c = model.getElementAt(index);
                    new AddEventDialog(addEventController,c);

                    if (ContactDialog.successResult()) {
                        // Update the model of the JList
                        model.set(index, c);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,CleanSheets.getString("status_please_choose_contact"));
                }
                break;

        }
    }
}
