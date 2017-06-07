/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.red.s1.core.n1150623.labelsForContacts.application.LabelsForContactsController;
import lapr4.red.s1.core.n1150623.labelsForContacts.presentation.LabelsForContactsUI;
import lapr4.red.s1.core.n1150943.contacts.application.EventController;
import lapr4.red.s1.core.n1150943.contacts.ui.AddEventDialog;
import lapr4.red.s1.core.n1150943.contacts.ui.EditEventDialog;
import lapr4.white.s1.core.n4567890.contacts.ContactsExtension;
import lapr4.white.s1.core.n4567890.contacts.application.ContactController;
import lapr4.white.s1.core.n4567890.contacts.domain.*;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import ui.ImageUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * A panel for adding or editing a comment for a cell
 *
 * @author Alexandre Braganca
 */
@SuppressWarnings("serial")
public class ContactPanel extends JPanel implements ActionListener {

    // Controller for Contacts
    private ContactController controller=null;

    //Controller for Events
    private EventController eventController =null;

    //Controller for Label Exportation
    private LabelsForContactsController expController = null;

    Iterable<Event> events=null;

    /** The text field in which the comment of the cell is displayed.*/
    private JTextArea commentField = new JTextArea();


    // Controls for the contacts panel
    private JLabel labelContacts=null;
    private JTextField contactsFilterField=null;
    private JList<Contact> contactsList=null;
    private JList<Event> eventsList=null;
    private DefaultListModel<Contact> contactsModel=null;
    private DefaultListModel<Event> eventsModel=null;
    private JButton contactsAddButton=null;
    private JButton contactsRemoveButton=null;
    private JButton contactsEditButton=null;
    private JButton contactsAddEventButton=null;
    private JButton contactsEditEventButton=null;
    private JButton contactsRemoveEventButton=null;
    private JButton contactsViewAllEventsButton=null;
    private JButton contactsViewPastEventsButton=null;
    private JButton contactsViewTodayEventsButton=null;
    private JButton contactsViewFutureEventsButton=null;
    private JButton contactsApplyFilterButton = null;
    private JButton contactsExportToLabelsButton = null;




    private JPanel contactsPane= null;
    private JPanel agendaPane=null;
    private JPanel filterPane = null;
    private JPanel contactsButtonPane = null;
    private JPanel eventsPane = null;
    private JPanel agendaButtonPane= null;

    // Action commands
    private final static String addAction="add";
    private final static String removeAction="remove";
    private final static String editAction="edit";
    private final static String addEventAction="add_event";
    private final static String editEventAction="edit_event";
    private final static String removeEventAction="remove_event";
    private final static String viewAllEventsAction="view_all_events";
    private final static String viewPastEventsAction="view_past_events";
    private final static String viewTodayEventsAction="view_today_events";
    private final static String viewFutureEventsAction="view_future_events";
    private final static String applyFilterAction = "apply_filter";
    private final static String exportLabelsAction = "export_labels";

    /**
     * Edited by João Cardoso - 1150943
     */
    private void setupContactsWidgets() {

        labelContacts = new JLabel("Filtro: ");

        // First Pane: The "filter", FlowLayout (from left to right)
        filterPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
        contactsFilterField = new JTextField();
        contactsFilterField.setColumns(5);

        contactsApplyFilterButton = new JButton();
        contactsApplyFilterButton.setText("Apply");
        contactsApplyFilterButton.setActionCommand(ContactPanel.applyFilterAction);
        contactsApplyFilterButton.addActionListener(this);

        contactsExportToLabelsButton = new JButton();
        contactsExportToLabelsButton.setText("Export");
        contactsExportToLabelsButton.setActionCommand(ContactPanel.exportLabelsAction);
        contactsExportToLabelsButton.addActionListener(this);

        filterPane.add(labelContacts);
        filterPane.add(contactsFilterField);
        filterPane.add(contactsApplyFilterButton);
        filterPane.add(contactsExportToLabelsButton);

        contactsModel = new DefaultListModel();
        Iterable<Contact> contacts = controller.allContacts();

        for (Contact c : contacts) {
            contactsModel.addElement(c);
        }

        contactsList = new JList(contactsModel);

        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JDialog dialog = new JDialog();
                    dialog.setUndecorated(true);
                    int index = contactsList.getSelectedIndex();
                    String photoSrc = contactsModel.getElementAt(index).photo();
                    if (!photoSrc.isEmpty()) {
                        File imageFile = new File(photoSrc);
                        ImageIcon profilePhoto = null;
                        try {
                            profilePhoto = ImageUtils.getResizedPhoto(imageFile, 100, 100);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        JLabel label = new JLabel(profilePhoto);

                        dialog.add(label);
                        dialog.pack();
                        dialog.setLocationRelativeTo(contactsPane);
                        dialog.setVisible(true);
                    }
                }
            }
        };

        MouseListener contactSelectedListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                updateEventModel();
            }
        };
        contactsList.addMouseListener(contactSelectedListener);
        contactsList.addMouseListener(mouseListener);

        contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane centerPane = new JScrollPane(contactsList);

        // Last Pane: A row of buttons and the end
        contactsButtonPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
        contactsAddButton = new JButton("Add");
        contactsAddButton.setActionCommand(ContactPanel.addAction);
        contactsAddButton.addActionListener(this);
        contactsRemoveButton = new JButton("Remove");
        contactsRemoveButton.setActionCommand(ContactPanel.removeAction);
        contactsRemoveButton.addActionListener(this);
        contactsEditButton = new JButton("Edit");
        contactsEditButton.setActionCommand(ContactPanel.editAction);
        contactsEditButton.addActionListener(this);

        contactsButtonPane.add(contactsAddButton);
        contactsButtonPane.add(contactsRemoveButton);
        contactsButtonPane.add(contactsEditButton);

        // The parent Pane is of type BorderLayout so that the center list occupies all the "empty" canvas
        contactsPane = new JPanel(new BorderLayout());
        contactsPane.add(filterPane, BorderLayout.PAGE_START);
        contactsPane.add(centerPane, BorderLayout.CENTER);
        contactsPane.add(contactsButtonPane, BorderLayout.PAGE_END);

        eventsModel = new DefaultListModel();
        events = new ArrayList<>();
        eventsList = new JList(eventsModel);

        eventsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane eventsPane = new JScrollPane(eventsList);

        //Added by João Cardoso - 1150943
        agendaButtonPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
        contactsAddEventButton=new JButton("Add Event");
        contactsAddEventButton.setActionCommand(ContactPanel.addEventAction);
        contactsAddEventButton.addActionListener(this);
        contactsEditEventButton=new JButton("Edit Event");
        contactsEditEventButton.setActionCommand(ContactPanel.editEventAction);
        contactsEditEventButton.addActionListener(this);
        contactsRemoveEventButton=new JButton("Remove Event");
        contactsRemoveEventButton.setActionCommand(ContactPanel.removeEventAction);
        contactsRemoveEventButton.addActionListener(this);
        contactsViewAllEventsButton=new JButton("All Events");
        contactsViewAllEventsButton.setActionCommand(ContactPanel.viewAllEventsAction);
        contactsViewAllEventsButton.addActionListener(this);
        contactsViewPastEventsButton=new JButton("Past Events");
        contactsViewPastEventsButton.setActionCommand(ContactPanel.viewPastEventsAction);
        contactsViewPastEventsButton.addActionListener(this);
        contactsViewTodayEventsButton=new JButton("Today's Events");
        contactsViewTodayEventsButton.setActionCommand(ContactPanel.viewTodayEventsAction);
        contactsViewTodayEventsButton.addActionListener(this);
        contactsViewFutureEventsButton=new JButton("Future Events");
        contactsViewFutureEventsButton.setActionCommand(ContactPanel.viewFutureEventsAction);
        contactsViewFutureEventsButton.addActionListener(this);

        agendaButtonPane.add(contactsAddEventButton);
        agendaButtonPane.add(contactsEditEventButton);
        agendaButtonPane.add(contactsRemoveEventButton);
        agendaButtonPane.add(contactsViewAllEventsButton);
        agendaButtonPane.add(contactsViewPastEventsButton);
        agendaButtonPane.add(contactsViewTodayEventsButton);
        agendaButtonPane.add(contactsViewFutureEventsButton);

        agendaPane = new JPanel(new BorderLayout());
        agendaPane.add(eventsPane, BorderLayout.CENTER);
        agendaPane.add(agendaButtonPane, BorderLayout.PAGE_END);
    }

    private void updateEventModel() {
        int index = contactsList.getSelectedIndex();
        Contact c;
        c = contactsModel.getElementAt(index);
        eventsModel.clear();
        for (Event ev : c.agenda().events()) {
            eventsModel.addElement(ev);
        }
        eventsList.setModel(eventsModel);
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
        this.eventController = new EventController(uiController.getUserProperties());
        this.expController = new LabelsForContactsController((uiController.getUserProperties()));
        setupContactsWidgets();

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

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
        int index = -1;

        switch (e.getActionCommand()) {
            case ContactPanel.addAction: {
                Contact c = null;
                ContactDialog.showDialog(this, contactsAddButton, this.controller,
                        ContactDialog.ContactDialogMode.ADD, "New Contact");
                if (ContactDialog.successResult()) {
                    c = ContactDialog.contact();
                    // Update the model of the JList
                    contactsModel.addElement(c);
                }
            }
            break;

            case ContactPanel.removeAction:
                index = contactsList.getSelectedIndex();
                if (index != -1) {
                    Contact c;
                    //c = (Contact)(contactsList.getModel().getElementAt(index));
                    c = contactsModel.getElementAt(index);

                    ContactDialog.showDialog(this, contactsRemoveButton, this.controller,
                            ContactDialog.ContactDialogMode.DELETE, "Delete Contact", c);
                    if (ContactDialog.successResult()) {
                        // Update the model of the JList
                        contactsModel.remove(index);
                    }
                }
                break;

            case ContactPanel.editAction:
                index = contactsList.getSelectedIndex();
                if (index != -1) {
                    Contact c;
                    //c = (Contact)(contactsList.getModel().getElementAt(index));
                    c = contactsModel.getElementAt(index);

                    ContactDialog.showDialog(this, contactsEditButton, this.controller,
                            ContactDialog.ContactDialogMode.EDIT, "Edit Contact", c);
                    if (ContactDialog.successResult()) {
                        // Update the model of the JList
                        contactsModel.set(index, c);
                    } else {
                        // Maybe the user tried to update but failed and canceled. We need to "refresh" the contacts object
                        Contact updatedContact = this.controller.getContactById(c.id());
                        // Update the model of the JList
                        contactsModel.set(index, updatedContact);
                    }

                }
                break;

            //Added by João Cardoso - 1150943
            case ContactPanel.addEventAction:
                index = contactsList.getSelectedIndex();
                if (index != -1) {
                    Contact c;
                    c = contactsModel.getElementAt(index);
                    new AddEventDialog(eventController,c);
                    updateEventModel();
                }else{
                    JOptionPane.showMessageDialog(null,CleanSheets.getString("status_please_choose_contact"));
                }
                break;

            case ContactPanel.editEventAction:
                int indexContact = contactsList.getSelectedIndex();
                index = eventsList.getSelectedIndex();
                Contact c = contactsModel.elementAt(indexContact);
                if (index!=-1) {
                    new EditEventDialog(eventController,c,index);
                    updateEventModel();
                }else{
                    JOptionPane.showMessageDialog(null,CleanSheets.getString("status_please_choose_event"));
                }
                break;

            case ContactPanel.removeEventAction:
                indexContact = contactsList.getSelectedIndex();
                index = eventsList.getSelectedIndex();
                c = contactsModel.elementAt(indexContact);
                if (index != -1) {
                    int op = JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to remove this event?", "Confirm Event Removal", JOptionPane.WARNING_MESSAGE);
                    if (op == JOptionPane.YES_OPTION) {
                        try {
                            eventController.removeEvent(c, index);
                        } catch (DataConcurrencyException e1) {
                            e1.printStackTrace();
                        } catch (DataIntegrityViolationException e1) {
                            e1.printStackTrace();
                        }
                    }
                    updateEventModel();
                }else{
                    JOptionPane.showMessageDialog(null,CleanSheets.getString("status_please_choose_event"));
                }
                break;

            case ContactPanel.viewAllEventsAction:
                updateEventModel();
                break;

            case ContactPanel.viewPastEventsAction:
                indexContact = contactsList.getSelectedIndex();
                c = contactsModel.elementAt(indexContact);
                eventsModel.clear();
                for(Event ev : eventController.pastEvents(c)){
                    eventsModel.addElement(ev);
                }
                break;

            case ContactPanel.viewTodayEventsAction:
                indexContact = contactsList.getSelectedIndex();
                c = contactsModel.elementAt(indexContact);
                eventsModel.clear();
                for(Event ev : eventController.todayEvents(c)){
                    eventsModel.addElement(ev);
                }
                break;

            case ContactPanel.viewFutureEventsAction:
                indexContact = contactsList.getSelectedIndex();
                c = contactsModel.elementAt(indexContact);
                eventsModel.clear();
                for(Event ev : eventController.futureEvents(c)){
                    eventsModel.addElement(ev);
                }
                break;

            case ContactPanel.applyFilterAction: {

                /**
                 * Faciltated choosing contacts
                 *
                 * @author Gulherme Ferreira 1150623 - Filter feature added.
                 *
                 */

                String text = contactsFilterField.getText().trim().toLowerCase();
                contactsModel.clear();
                Iterable<Contact> contacts = controller.allContacts();

                if (text.length() != 0) {
                    for (Contact con : contacts) {
                        if (con != null) {
                            if (con.name().toLowerCase().contains(text) || con.firstName().toLowerCase().contains(text) || con.lastName().toLowerCase().contains(text)) {
                                contactsModel.addElement(con);
                            }
                        }
                    }
                    System.out.println(contactsModel);
                } else {

                    for (Contact con : contacts) {
                        contactsModel.addElement(con);
                    }
                }
                contactsList.setModel(contactsModel);
            }

            break;

            case ContactPanel.exportLabelsAction:
            {
                LabelsForContactsUI dialog = new LabelsForContactsUI(expController);
                dialog.pack();
                dialog.setVisible(true);
            }
            break;
        }
    }
}
