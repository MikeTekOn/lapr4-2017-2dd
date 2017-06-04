package lapr4.red.s1.core.n1150623.labelsForContacts.presentation;

import lapr4.red.s1.core.n1150623.labelsForContacts.application.LabelsForContactsController;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LabelsForContactsUI extends JDialog {


    private JList selectedContactsList;
    private JList contactList;

    private DefaultListModel<Contact> selected;
    private DefaultListModel<Contact> contacts;

    private JPanel contentPane;

    private JButton buttonOK;
    private JButton addAllContactsButton;
    private JButton buttonCancel;
    private JButton addSelectedContactsButton;
    private JButton removeSelectedContactButton;
    private JButton applyRegexButton;

    private LabelsForContactsController controller;

    public LabelsForContactsUI(LabelsForContactsController controller) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.controller = controller;
        selectedContactsList = new JList();
        contactList = new JList();
        selected = new DefaultListModel<>();
        contacts = new DefaultListModel<>();
        Iterable<Contact> it = controller.allContacts();
        for (Contact c : it) {
            contacts.addElement(c);
        }
        selectedContactsList.setModel(selected);
        contactList.setModel(contacts);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        applyRegexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyRegex();
            }
        });

        addAllContactsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAllContacts();
            }
        });

        addSelectedContactsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSelected();
            }
        });

        removeSelectedContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelected();
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
    }

    private void addSelected() {
        int index[] = contactList.getSelectedIndices();
        List<Contact> list = new ArrayList<>();
        for (int i : index) {
            list.add(contacts.get(i));
        }
        addToSelectedContacts(list);
        removeFromContacts(list);
        updateAllContactsList();
        updateSelectedContactsList();
    }

    private void removeSelected() {
        int index[] = selectedContactsList.getSelectedIndices();
        List<Contact> list = new ArrayList<>();
        for (int i : index) {
            list.add(contacts.get(i));
        }
        removeFromSelectedContacts(list);
        addToContacts(list);
        updateAllContactsList();
        updateSelectedContactsList();
    }

    private void applyRegex() {
        String regex = "";
        new ApllyFilterDialog(regex);

        List<Contact> foundContacts = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Contact c = null;
        for (int i = 0; i < contacts.size(); i++) {
            c = contacts.getElementAt(i);
            Matcher m = p.matcher(c.name());
            if (m.matches()) {
                foundContacts.add(c);
            }
        }
        contacts.removeAllElements();
        addToContacts(foundContacts);
        updateAllContactsList();
    }

    private void onOK() {

        if (JOptionPane.showConfirmDialog(null, "Label Exportation", "Label Exportation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            /**
             * Add events
             * */
            Calendar endDate = Calendar.getInstance();

            new ChooseEndDateEventsDialog(endDate);

            /**
             * Limit Events
             */
            controller.limitEvents(endDate);
        } else {
            controller.removeEvents();
        }


        String export = "Success";
        if (!controller.doExport()) {
            export = "Failed";
        }
        JOptionPane.showConfirmDialog(null, "Exportation: " + export, "Information", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
        onCancel();
    }

    private void onCancel() {
        dispose(); //nothing more to do...
    }

    public static void main(String[] args) {
        LabelsForContactsUI dialog = new LabelsForContactsUI(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void updateAllContactsList() {
        contactList.setModel(contacts);
    }

    private void updateSelectedContactsList() {
        selectedContactsList.setModel(selected);
    }

    private void removeFromContacts(List<Contact> toRemove) {
        for (Contact c : toRemove) {
            contacts.removeElement(c);
        }
    }

    private void addToContacts(List<Contact> toAdd) {
        for (Contact c : toAdd) {
            if (!contacts.contains(c))
                contacts.addElement(c);
        }
    }

    private void addAllContacts() {
        Contact c = null;
        for (int i = 0; i < contacts.size(); i++) {
            c = contacts.getElementAt(i);
            if (!selected.contains(c)) {
                selected.addElement(c);
            }
        }
        contacts.removeAllElements();
        updateAllContactsList();
        updateSelectedContactsList();
    }


    private void addToSelectedContacts(List<Contact> toAdd) {
        for (Contact c : toAdd) {
            if (!selected.contains(c))
                selected.addElement(c);
        }
    }

    private void removeFromSelectedContacts(List<Contact> toRemove) {
        for (Contact c : toRemove) {
            selected.removeElement(c);
        }
    }
}
