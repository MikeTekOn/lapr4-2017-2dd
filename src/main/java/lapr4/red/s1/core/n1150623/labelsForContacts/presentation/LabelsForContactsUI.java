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
        ApplyFilterDialog a = new ApplyFilterDialog(regex);
        a.pack();
        a.setVisible(true);

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

        if(selected.size() > 0) {
            Contact c = null;
            for (int i = 0; i < selected.size();i++) {
                c = selected.getElementAt(i);
                try {
                    if(c != null)
                        controller.addLabel(c.name(), c.photo(), c.email(), c.address(), c.phone());
                } catch (Exception e) {
                }
            }

            if (JOptionPane.showConfirmDialog(null, "Export Events?", "Label Exportation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                Calendar endDate = Calendar.getInstance();

                ChooseEndDateEventsDialog ch = new ChooseEndDateEventsDialog(endDate);
                ch.pack();
                ch.setVisible(true);
                System.exit(0);
                /**
                 * Limit Events
                 */
                controller.limitEvents(endDate);
            } else {
                controller.removeEvents();
            }

            choosePath();

            String export = "Success";
            if (!controller.doExport(path)) {
                export = "Failed";
            }
            JOptionPane.showConfirmDialog(null, "Exportation: " + export, "Information", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
            onCancel();
        }else{
            JOptionPane.showMessageDialog(null, "Must select at least one contact", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
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
        contactList = new JList(contacts);
    }

    private void updateSelectedContactsList() {
        selectedContactsList = new JList(selected);
    }

    private void removeFromContacts(List<Contact> toRemove) {
        for (Contact c : toRemove) {
            contacts.removeElement(c);
        }
        updateAllContactsList();
        updateSelectedContactsList();
    }

    private void addToContacts(List<Contact> toAdd) {
        for (Contact c : toAdd) {
            if (!contacts.contains(c))
                contacts.addElement(c);
        }
        updateAllContactsList();
        updateSelectedContactsList();
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
                selected.addElement(c);
        }
        updateAllContactsList();
        updateSelectedContactsList();
    }

    private void removeFromSelectedContacts(List<Contact> toRemove) {
        for (Contact c : toRemove) {
            selected.removeElement(c);
        }
        updateAllContactsList();
        updateSelectedContactsList();
    }


    private JButton button;
    private String path;
    private String name;
    private JFileChooser picker;
    private String pickertitle;

    private void choosePath(){
        name = JOptionPane.showInputDialog("Name of File? \n(ex:\"nome_ficheiro\")");
        name += ".pdf";

        JDialog d = new JDialog();
        JPanel p = new JPanel();
        d.add(p);

        button = new JButton("Path Chooser");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                picker = new JFileChooser();
                picker.setCurrentDirectory(new java.io.File("."));
                picker.setDialogTitle(pickertitle);
                picker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //only directories - cant choose files

                picker.setAcceptAllFileFilterUsed(false);
                if (picker.showOpenDialog(p) == JFileChooser.APPROVE_OPTION) {
                    path = picker.getSelectedFile().getPath();
                    path+="\\";
                    path+=name;
                    d.dispose();
                } else {
                    //nothing
                }
            }
        });
        d.add(button);

    }

}
