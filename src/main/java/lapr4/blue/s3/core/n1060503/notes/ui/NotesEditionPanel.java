/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.Strings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import lapr4.blue.s3.core.n1060503.notes.NotesEditionController;
import lapr4.blue.s3.core.n1060503.notes.domain.Note;
import lapr4.blue.s3.core.n1060503.notes.domain.NoteContent;

/**
 * Represents the panel of notes edition
 *
 * @author Pedro Fernandes
 */
public class NotesEditionPanel extends JPanel {

    /**
     * The name for the border.
     */
    private static final String BORDER_NAME = "Notes Edition";

    /**
     * The UI Controller.
     */
    private final UIController uiController;

    /**
     * the nores edition controller
     */
    private final NotesEditionController controller;

    /**
     * combobox of contacts
     */
    private JComboBox comboContacts;

    /**
     * model to support combobox of contact
     */
    private DefaultComboBoxModel model;

    /**
     * list of notes
     */
    private JList noteList;

    /**
     * list of notes content
     */
    private JList noteContentList;

    /**
     * pane to see the content of notes
     */
    private JTextPane pane;

    /**
     * add note button
     */
    private JButton addButton;

    /**
     * edit note button
     */
    private JButton editButton;

    /**
     * remove note button
     */
    private JButton removeButton;

    /**
     * style document to support pane
     */
    private DefaultStyledDocument doc;

    /**
     * style to support documentstyle
     */
    private Style heading2Style;

    /**
     * stylecontext of document
     */
    private StyleContext sc;

    // LABELS
    private static final String CONTACTS = "Contacts:";
    private static final String NOTES = "Notes:";
    private static final String NOTE_CONTENT = "Note Content:";
    private static final String HISTORY = "History:";
    private static final String OPTIONS = "Options:";

    /**
     * The extension.
     */
    private final NotesEditionExtension extension;

    /**
     * construtor of panel
     *
     * @param uiController ui controller
     * @param extension notes edition extension
     */
    public NotesEditionPanel(UIController uiController, Extension extension) {
        super(new BorderLayout());
        this.uiController = uiController;
        this.extension = (NotesEditionExtension) extension;

        this.controller = new NotesEditionController(uiController.getUserProperties());

        buildPanel();
        changeComponentName(NotesEditionExtension.NAME);
    }

    /**
     * Private method to change the name of the component. It calls a component
     * method to change it.
     *
     * @param name The name to change it
     */
    private void changeComponentName(String name) {
        setName(name);
    }

    /**
     * Private method to build all the panel.
     */
    private void buildPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createPanelNorth(), BorderLayout.NORTH);
        panel.add(createPanelCenter(), BorderLayout.CENTER);
        panel.add(createPanelButtons(), BorderLayout.SOUTH);
        buildBorder(panel, BORDER_NAME);
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Private method to build the border.
     *
     * @param panel panel
     * @param borderName border name
     */
    private void buildBorder(JPanel panel, String borderName) {
        TitledBorder border = BorderFactory.createTitledBorder(borderName);
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(border);
    }

    /**
     * create the panel north
     *
     * @return panel north
     */
    private JPanel createPanelNorth() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createPanelContacts(), BorderLayout.NORTH);
        panel.add(createPanelNotes(), BorderLayout.CENTER);
        return panel;
    }

    /**
     * create a panel with a combobox
     *
     * @return panel with a combobox
     */
    private JPanel createPanelContacts() {
        JPanel panel = new JPanel();

        model = new DefaultComboBoxModel(controller.getContacts().toArray());
        comboContacts = new JComboBox(model);
        comboContacts.setSelectedIndex(-1);
        comboContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aux = (String) comboContacts.getSelectedItem();
                if (!Strings.isNullOrEmpty(aux)) {
                    Vector v = (Vector) controller.notesOfContact((String) comboContacts.getSelectedItem());
                    noteList.setListData(v);
                    addButton.setEnabled(true);
                } else {
                    noteList.setListData(new LinkedList().toArray());
                    noteContentList.setListData(new LinkedList().toArray());
                    doc = new DefaultStyledDocument(sc);
                    pane.setDocument(doc);
                    editButton.setEnabled(false);
                    removeButton.setEnabled(false);
                }
            }
        });
        comboContacts.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.removeAllElements();
                for (Object o : controller.getContacts().toArray()) {
                    model.addElement(o);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        panel.setBorder(BorderFactory.createTitledBorder(CONTACTS));
        panel.add(comboContacts);
        buildBorder(panel, CONTACTS);

        return panel;
    }

    /**
     * create a panel with to jlist
     *
     * @return a panel with to jlist
     */
    private JPanel createPanelNotes() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));

        JPanel p1 = new JPanel();
        noteList = new JList(new LinkedList().toArray());
        noteList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Note n = (Note) noteList.getSelectedValue();
                Vector v = (Vector) controller.notesContentOfNote(n);
                if (n != null) {
                    noteContentList.setListData(v);
                    editButton.setEnabled(true);
                    removeButton.setEnabled(true);
                } else {
                    noteContentList.setListData(new LinkedList().toArray());
                    editButton.setEnabled(false);
                    removeButton.setEnabled(false);
                }
            }
        });
        JScrollPane listScroller = new JScrollPane(noteList);
        listScroller.setPreferredSize(new Dimension(150, 100));
        p1.add(listScroller, BorderLayout.CENTER);
        buildBorder(p1, NOTES);

        JPanel p2 = new JPanel();
        noteContentList = new JList(new LinkedList().toArray());
        noteContentList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                doc = new DefaultStyledDocument(sc);
                NoteContent nc = (NoteContent) noteContentList.getSelectedValue();
                Note n = (Note) noteList.getSelectedValue();
                if (nc != null) {
                    String newLine = System.getProperty("line.separator");
                    try {
                        // Add the text to the document
                        doc.insertString(0, (n.title() + newLine + nc.getContentNote()), null);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(NotesEditionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Finally, apply the style to the heading
                    doc.setParagraphAttributes(0, 1, heading2Style, false);

                }
                pane.setDocument(doc);
            }
        });
        JScrollPane listScroller2 = new JScrollPane(noteContentList);
        listScroller2.setPreferredSize(new Dimension(150, 100));
        p2.add(listScroller2, BorderLayout.CENTER);
        buildBorder(p2, HISTORY);

        panel.add(p1);
        panel.add(p2);

        return panel;
    }

    /**
     * create panel center with a textarea
     *
     * @return panel center with a textarea
     */
    private JPanel createPanelCenter() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Create the StyleContext, the document and the pane
        sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
        pane = new JTextPane(doc);

        // Create and add the style
        heading2Style = sc.addStyle("Heading2", null);
        heading2Style.addAttribute(StyleConstants.Foreground, Color.DARK_GRAY);
        heading2Style.addAttribute(StyleConstants.FontSize, new Integer(16));
        heading2Style.addAttribute(StyleConstants.FontFamily, "Comic Sans MS");
        heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));

        pane.setEditable(false);

        JScrollPane editorScrollPane = new JScrollPane(pane);
        editorScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        panel.add(pane);

        buildBorder(panel, NOTE_CONTENT);

        return panel;
    }

    /**
     * create panel for the buttons
     *
     * @return panel for the buttons
     */
    private JPanel createPanelButtons() {

        FlowLayout l = new FlowLayout();
        l.setVgap(5);

        JPanel p = new JPanel(l);

        p.setBorder(BorderFactory.createTitledBorder(OPTIONS));
        JButton bt1 = createButtonAdd();
        JButton bt2 = createButtonEdit();
        JButton bt3 = createButtonRemove();

        p.add(bt1);
        p.add(bt2);
        p.add(bt3);

        return p;
    }

    /**
     * create Add Button
     *
     * @return Add Button
     */
    private JButton createButtonAdd() {
        addButton = new JButton("Add");
        addButton.setToolTipText("Add a new note");
        addButton.setEnabled(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    add();
                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(NotesEditionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(NotesEditionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return addButton;
    }

    /**
     * create Edit Button
     *
     * @return Edit Button
     */
    private JButton createButtonEdit() {
        editButton = new JButton("Edit");
        editButton.setToolTipText("Create a new note content");
        editButton.setEnabled(false);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });

        return editButton;
    }

    /**
     * create Remove Button
     *
     * @return Remove Button
     */
    private JButton createButtonRemove() {
        removeButton = new JButton("Remove");
        removeButton.setToolTipText("Change status of note to removed");
        removeButton.setEnabled(false);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remove();
                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(NotesEditionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(NotesEditionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return removeButton;
    }

    /**
     * void method to execute add note
     *
     * @throws DataConcurrencyException DataConcurrencyException
     * @throws DataIntegrityViolationException DataIntegrityViolationException
     */
    private void add() throws DataConcurrencyException, DataIntegrityViolationException {
        String name = (String) comboContacts.getSelectedItem();
        final AddNoteDialog a = new AddNoteDialog(controller, new DefaultStyledDocument(sc), name);
        rebuild(name);
    }

    /**
     * void method to execute edit note
     *
     * @throws DataConcurrencyException DataConcurrencyException
     * @throws DataIntegrityViolationException DataIntegrityViolationException
     */
    private void edit() {
        String name = (String) comboContacts.getSelectedItem();
        Note n = (Note) noteList.getSelectedValue();
        final EditNoteDialog e = new EditNoteDialog(controller, new DefaultStyledDocument(sc), name, n);
        rebuild(name);
    }

    /**
     * void method to execute remove note
     *
     * @throws DataConcurrencyException DataConcurrencyException
     * @throws DataIntegrityViolationException DataIntegrityViolationException
     */
    private void remove() throws DataConcurrencyException, DataIntegrityViolationException {
        String name = (String) comboContacts.getSelectedItem();
        Note n = (Note) noteList.getSelectedValue();
        String[] op = {"YES", "NO"};
        String question = "Do you want to change status to REMOVED of Note " + n.title() + "?";
        int option = JOptionPane.showOptionDialog(NotesEditionPanel.this, question,
                "Remove note: " + n.title(), JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, op, op[0]);
        if (option == JOptionPane.YES_OPTION) {
            if (controller.removeNote(name, n)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Status changed to removed successfully!",
                        "Remove: " + n.title(),
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Note is already in state removed!",
                        "Remove: " + n.title(),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * update combobox (clear related components)
     */
    private void rebuild(String name) {
        comboContacts.setSelectedItem(name);
    }

}
