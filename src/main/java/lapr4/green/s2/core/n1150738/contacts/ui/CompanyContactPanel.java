/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150738.contacts.ui;

import csheets.ui.ctrl.UIController;
import lapr4.blue.s3.core.n1151159.contactswithtags.presentation.SearchContactsByTagButton;
import lapr4.blue.s3.core.n1151159.contactswithtags.presentation.TagFrequencyPanel;
import lapr4.blue.s3.core.n1151159.contactswithtags.presentation.TagsChangesWatchDog;
import lapr4.green.s2.core.n1150738.contacts.application.CompanyContactController;
import lapr4.green.s2.core.n1150738.contacts.domain.*;
import lapr4.red.s1.core.n1150623.labelsForContacts.application.LabelsForContactsController;
import lapr4.red.s1.core.n1150943.contacts.application.EventController;
import lapr4.white.s1.core.n4567890.contacts.ContactsExtension;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * A panel for adding or editing a company contact for a cell
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
@SuppressWarnings("serial")
public class CompanyContactPanel extends JPanel implements ActionListener {

    // Controller for Contacts
    private CompanyContactController controller = null;

    //Controller for Events
    private EventController eventController = null;

    //Controller for Label Exportation
    private LabelsForContactsController expController = null;

    Iterable<Event> events = null;
    Iterable<Contact> contactsRelated = null;

    /**
     * The text field in which the comment of the cell is displayed.
     */
    private JTextArea commentField = new JTextArea();

    private final UIController uiController;

    // Controls for the contacts panel
    private JLabel labelContacts = null;
    private JTextField contactsFilterField = null;
    private JList<CompanyContact> contactsList = null;
    private JList<Event> eventsList = null;
    private JList<Contact> contactRelatedList = null;
    private DefaultListModel<CompanyContact> contactsModel = null;
    private DefaultListModel<Contact> contactRelatedModel = null;
    private DefaultListModel<Event> eventsModel = null;
    private JButton contactsAddButton = null;
    private JButton contactsRemoveButton = null;
    private JButton contactsEditButton = null;
    private JButton contactsAddEventButton = null;
    private JButton contactsEditEventButton = null;
    private JButton contactsRemoveEventButton = null;
    private JButton contactsViewAllEventsButton = null;
    private JButton contactsViewPastEventsButton = null;
    private JButton contactsViewTodayEventsButton = null;
    private JButton contactsViewFutureEventsButton = null;
    private JButton contactsApplyFilterButton = null;
    private JButton contactsExportToLabelsButton = null;


    private JPanel contactsPane = null;
    private JPanel agendaPane = null;
    private TagFrequencyPanel tagsFrequencyPane=null;
    private JPanel filterPane = null;
    private JPanel contactsButtonPane = null;
    private JPanel eventsPane = null;
    private JPanel agendaButtonPane = null;

    // Action commands
    private final static String addAction = "add";
    private final static String removeAction = "remove";
    private final static String editAction = "edit";
    private final static String viewAllEventsAction = "view_all_events";
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
        contactsApplyFilterButton.setActionCommand(CompanyContactPanel.applyFilterAction);
        contactsApplyFilterButton.addActionListener(this);

        filterPane.add(labelContacts);
        filterPane.add(contactsFilterField);
        filterPane.add(contactsApplyFilterButton);
        filterPane.add(new SearchContactsByTagButton(uiController));

        contactsModel = new DefaultListModel();
        Iterable<CompanyContact> contacts = controller.allCompanyContacts();

        for (CompanyContact c : contacts) {
            contactsModel.addElement(c);
        }

        contactsList = new JList(contactsModel);



        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JDialog dialog = new JDialog();
                    dialog.setUndecorated(true);
                    int index = contactsList.getSelectedIndex();
                    lapr4.green.s2.core.n1150738.contacts.domain.Image photo = contactsModel.getElementAt(index).image();
                    if (photo != null) {
                        ImageIcon profilePhoto = new ImageIcon(photo.image());
                        Image image = profilePhoto.getImage(); // transform it
                        Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                        profilePhoto = new ImageIcon(newimg);  // transform it back
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

        contactRelatedModel = new DefaultListModel<>();
        contactsRelated = new ArrayList();
        contactRelatedList = new JList(contactRelatedModel);
        contactRelatedList.setSelectionModel(new DefaultListSelectionModel(){
            @Override
            public void setSelectionInterval(int index0, int index1) {
                super.setSelectionInterval(-1, -1);
            }
        });
        JScrollPane contactsRelPane = new JScrollPane(contactRelatedList);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(centerPane);
        center.add(new JLabel("Related:"));
        center.add(contactsRelPane);

        // Last Pane: A row of buttons and the end
        contactsButtonPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
        contactsAddButton = new JButton("Add");
        contactsAddButton.setActionCommand(CompanyContactPanel.addAction);
        contactsAddButton.addActionListener(this);
        contactsRemoveButton = new JButton("Remove");
        contactsRemoveButton.setActionCommand(CompanyContactPanel.removeAction);
        contactsRemoveButton.addActionListener(this);
        contactsEditButton = new JButton("Edit");
        contactsEditButton.setActionCommand(CompanyContactPanel.editAction);
        contactsEditButton.addActionListener(this);

        contactsButtonPane.add(contactsAddButton);
        contactsButtonPane.add(contactsRemoveButton);
        contactsButtonPane.add(contactsEditButton);

        // The parent Pane is of type BorderLayout so that the center list occupies all the "empty" canvas
        contactsPane = new JPanel(new BorderLayout());
        contactsPane.add(filterPane, BorderLayout.PAGE_START);
        contactsPane.add(center, BorderLayout.CENTER);
        contactsPane.add(contactsButtonPane, BorderLayout.PAGE_END);



        eventsModel = new DefaultListModel();
        events = new ArrayList<>();
        eventsList = new JList(eventsModel);

        eventsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane eventsPane = new JScrollPane(eventsList);

        //Added by João Cardoso - 1150943
        agendaButtonPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
        contactsViewAllEventsButton = new JButton("All Events");
        contactsViewAllEventsButton.setActionCommand(CompanyContactPanel.viewAllEventsAction);
        contactsViewAllEventsButton.addActionListener(this);

        agendaButtonPane.add(contactsViewAllEventsButton);

        agendaPane = new JPanel(new BorderLayout());
        agendaPane.add(eventsPane, BorderLayout.CENTER);
        agendaPane.add(agendaButtonPane, BorderLayout.PAGE_END);

        tagsFrequencyPane = new TagFrequencyPanel(uiController);
        TagsChangesWatchDog.getInstance().addTagFrequencyListener(tagsFrequencyPane);
    }

    private void updateEventModel() {
        int index = contactsList.getSelectedIndex();
        if(index > -1){
            CompanyContact c;
            c = contactsModel.getElementAt(index);
            eventsModel.clear();
            contactRelatedModel.clear();
            for (Event ev : controller.companyAgenda(c)) {
                eventsModel.addElement(ev);
            }
            for(Contact ct : controller.allContactsRelatedTo(c)){
                contactRelatedModel.addElement(ct);
            }
            eventsList.setModel(eventsModel);
            contactRelatedList.setModel(contactRelatedModel);
        } else {
            eventsModel.clear();
            contactRelatedModel.clear();
            eventsList.setModel(eventsModel);
            contactRelatedList.setModel(contactRelatedModel);
        }
    }

    /**
     * Creates a new comment panel.
     *
     * @param uiController the user interface controller
     */
    public CompanyContactPanel(UIController uiController) {
        // Configures panel
        super(new BorderLayout());
        setName(ContactsExtension.NAME);

        this.uiController = uiController;

        // Creates controllers
        this.controller = new CompanyContactController(uiController.getUserProperties());
        this.eventController = new EventController(uiController.getUserProperties());
        this.expController = new LabelsForContactsController((uiController.getUserProperties()));
        setupContactsWidgets();

        JPanel mainPanel = new JPanel(new GridLayout(3, 1));

        // Adds borders
        TitledBorder border = BorderFactory.createTitledBorder("Contacts");
        border.setTitleJustification(TitledBorder.CENTER);
        contactsPane.setBorder(border);

        border = BorderFactory.createTitledBorder("Agenda");
        border.setTitleJustification(TitledBorder.CENTER);
        agendaPane.setBorder(border);

        border = BorderFactory.createTitledBorder("Tags Frequency");
        border.setTitleJustification(TitledBorder.CENTER);
        tagsFrequencyPane.setBorder(border);

        // Creates side bar
        mainPanel.add(contactsPane);
        mainPanel.add(agendaPane);
        mainPanel.add(tagsFrequencyPane);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = -1;

        switch (e.getActionCommand()) {
            case CompanyContactPanel.addAction: {
                CompanyContact c = null;
                CompanyContactDialog.showDialog(this, contactsAddButton, this.controller,
                        CompanyContactDialog.CompanyContactDialogMode.ADD, "New Contact");
                if (CompanyContactDialog.successResult()) {
                    c = CompanyContactDialog.contact();
                    // Update the model of the JList
                    contactsModel.addElement(c);

                    TagsChangesWatchDog.getInstance().notifyListeners();
                }
            }
            break;

            case CompanyContactPanel.removeAction:
                index = contactsList.getSelectedIndex();
                if (index != -1) {
                    CompanyContact c;
                    //c = (Contact)(contactsList.getModel().getElementAt(index));
                    c = contactsModel.getElementAt(index);

                    CompanyContactDialog.showDialog(this, contactsRemoveButton, this.controller,
                            CompanyContactDialog.CompanyContactDialogMode.DELETE, "Delete Contact", c);
                    if (CompanyContactDialog.successResult()) {
                        // Update the model of the JList
                        contactsModel.remove(index);

                        TagsChangesWatchDog.getInstance().notifyListeners();
                    }
                }
                break;

            case CompanyContactPanel.editAction:
                index = contactsList.getSelectedIndex();
                if (index != -1) {
                    CompanyContact c;
                    //c = (Contact)(contactsList.getModel().getElementAt(index));
                    c = contactsModel.getElementAt(index);

                    CompanyContactDialog.showDialog(this, contactsEditButton, this.controller,
                            CompanyContactDialog.CompanyContactDialogMode.EDIT, "Edit Contact", c);
                    if (CompanyContactDialog.successResult()) {
                        // Update the model of the JList
                        contactsModel.set(index, c);
                    } else {
                        // Maybe the user tried to update but failed and canceled. We need to "refresh" the contacts object
                        CompanyContact updatedContact = this.controller.getContactById(c.id());
                        // Update the model of the JList
                        contactsModel.set(index, updatedContact);
                    }

                    TagsChangesWatchDog.getInstance().notifyListeners();
                }
                break;

            case CompanyContactPanel.viewAllEventsAction:
                updateEventModel();
                break;

            case CompanyContactPanel.applyFilterAction: {

                /**
                 * Faciltated choosing contacts
                 *
                 * @author Gulherme Ferreira 1150623 - Filter feature added.
                 *
                 */

                String text = contactsFilterField.getText().trim().toLowerCase();
                contactsModel.clear();
                Iterable<CompanyContact> contacts = controller.allCompanyContacts();

                if (text.length() != 0) {
                    for (CompanyContact con : contacts) {
                        if (con != null) {
                            if (con.companyName().name().toLowerCase().contains(text)) {
                                contactsModel.addElement(con);
                            }
                        }
                    }
                } else {

                    for (CompanyContact con : contacts) {
                        contactsModel.addElement(con);
                    }
                }
                contactsList.setModel(contactsModel);
            }

            break;

        }
    }
}
