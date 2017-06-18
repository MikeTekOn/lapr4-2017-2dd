package lapr4.blue.s3.core.n1151159.contactswithtags.ui;

import csheets.ui.ctrl.UIController;
import lapr4.blue.s3.core.n1151159.contactswithtags.application.TagController;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Contactable;
import lapr4.green.s2.core.n1150738.contacts.application.CompanyContactController;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.green.s2.core.n1150738.contacts.ui.CompanyContactDialog;
import lapr4.white.s1.core.n4567890.contacts.application.ContactController;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.ui.ContactDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Dialog to search contacts by tags regular expressions.
 *
 * @author Ivo Ferro [1151159]
 */
public class SearchContactsByTagDialog extends JDialog {

    /**
     * The search by contact controller.
     */
    private final TagController controller;

    /**
     * The tag regex text field.
     */
    private JTextField regexField;

    /**
     * The contacts table.
     */
    private JTable contactsTable;

    /**
     * The contacts.
     */
    private List<Contactable> contacts;

    /**
     * The user interface controller.
     */
    private final UIController uiController;

    /**
     * Creates a new instance of search contacts by tag dialog.
     *
     * @param parentWindow the parent window or null
     * @param uiController the user interface controller
     */
    public SearchContactsByTagDialog(Window parentWindow, UIController uiController) {
        this.uiController = uiController;
        controller = new TagController(uiController.getUserProperties());
        createComponents();
        pack();
        setLocationRelativeTo(parentWindow);
    }

    /**
     * Creates the user interface components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout());
        componentsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        componentsPanel.add(createRegexPanel(), BorderLayout.NORTH);
        componentsPanel.add(createListAndButtonsPanel(), BorderLayout.CENTER);

        add(componentsPanel);
    }

    /**
     * Creates the regex panel.
     *
     * @return regex panel
     */
    private JPanel createRegexPanel() {
        JPanel regexPanel = new JPanel(new BorderLayout());

        JLabel regexLabel = new JLabel("Please enter a regular expression for the tag to search:", SwingConstants.CENTER);

        regexPanel.add(regexLabel, BorderLayout.NORTH);
        regexPanel.add(createSearchPanel(), BorderLayout.CENTER);

        return regexPanel;
    }

    /**
     * Creates the search panel.
     *
     * @return search panel
     */
    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel();

        regexField = new JTextField(10);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                contacts = new ArrayList<>(controller.findContactsByTag(regexField.getText()));
                updateTable();
            }
        });

        searchPanel.add(regexField);
        searchPanel.add(searchButton);

        return searchPanel;
    }

    /**
     * Creates the list and buttons panel.
     *
     * @return list and buttons panel
     */
    private JPanel createListAndButtonsPanel() {
        JPanel listAndButtonsPanel = new JPanel(new BorderLayout());

        listAndButtonsPanel.add(createTablePanel(), BorderLayout.NORTH);
        listAndButtonsPanel.add(createButtonsPanel(), BorderLayout.CENTER);

        return listAndButtonsPanel;
    }

    /**
     * Creates the table panel.
     *
     * @return table panel
     */
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel();

        contactsTable = new JTable(new ContactableTableModel(new ArrayList<>()));
        contactsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Contactable c = contacts.get(contactsTable.getSelectedRow());
                if (c instanceof Contact) {
                    Contact contact = (Contact) c;
                    ContactController ctrl = new ContactController(uiController.getUserProperties());
                    ContactDialog.showDialog(null, null, ctrl,
                            ContactDialog.ContactDialogMode.EDIT, "Edit Contact", contact);
                } else if (c instanceof CompanyContact) {
                    CompanyContact companyContact = (CompanyContact) c;
                    CompanyContactController ctrl = new CompanyContactController(uiController.getUserProperties());
                    CompanyContactDialog.showDialog(null, null, ctrl,
                            CompanyContactDialog.CompanyContactDialogMode.EDIT, "Edit Contact", companyContact);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(contactsTable);
        tablePanel.add(scrollPane);

        return tablePanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        buttonsPanel.add(closeButton);

        return buttonsPanel;
    }

    /**
     * Updates the table.
     */
    private void updateTable() {
        contactsTable.setModel(new ContactableTableModel(new LinkedList<>(contacts)));
    }
}
