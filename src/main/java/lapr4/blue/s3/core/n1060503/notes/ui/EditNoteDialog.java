/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.ui;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import lapr4.blue.s3.core.n1060503.notes.NotesEditionController;
import lapr4.blue.s3.core.n1060503.notes.domain.Note;

/**
 * Edit note Dialog
 *
 * @author Pedro Fernandes
 */
public class EditNoteDialog extends JDialog {

    /**
     * notes edition controller
     */
    private NotesEditionController controller;

    /**
     * edit button
     */
    private JButton editBtn;

    /**
     * cancel button
     */
    private JButton cancelBtn;

    /**
     * pane for note title and content
     */
    private JTextPane txt;

    /**
     * style document to support pane
     */
    private DefaultStyledDocument doc;

    /**
     * contact name
     */
    private String contactName;

    /**
     * note
     */
    private Note note;

    /**
     * Width and length of the window
     */
    private static final int WIDTHW = 300, LENGTH = 300;

    /**
     * construtor of panel
     *
     * @param controller notes edition controller
     * @param doc style document to support pane
     * @param contactName name of contact
     * @param note note
     */
    public EditNoteDialog(NotesEditionController controller, DefaultStyledDocument doc,
            String contactName, Note note) {
        setModal(true);
        setTitle("Edit Note " + note.title());
        this.doc = doc;
        this.controller = controller;
        this.contactName = contactName;
        this.note = note;

        add(createComponents());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        pack();
        setResizable(true);
        setMinimumSize(new Dimension(WIDTHW, LENGTH));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * create general panel
     *
     * @return general panel
     */
    private JPanel createComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel p = new JPanel();
        JLabel l = new JLabel("Title: " + note.title(), JLabel.CENTER);
        p.add(l);
        panel.add(p, BorderLayout.NORTH);
        panel.add(createPanelCenter(), BorderLayout.CENTER);
        panel.add(createPanelButons(), BorderLayout.SOUTH);

        return panel;
    }

    /**
     * create center panel
     *
     * @return center panel
     */
    private JPanel createPanelCenter() {
        JPanel panel = new JPanel(new BorderLayout());
        txt = new JTextPane(doc);
        panel.add(txt, BorderLayout.CENTER);
        return panel;
    }

    /**
     * create panel of buttons
     *
     * @return panel of buttons
     */
    private JPanel createPanelButons() {

        FlowLayout l = new FlowLayout();

        l.setHgap(10);
        l.setVgap(10);

        JPanel p = new JPanel(l);

        p.setBorder(BorderFactory.createTitledBorder("Options:"));

        JButton bt1 = createButonEdit();
        JButton bt2 = createButonCancel();

        getRootPane().setDefaultButton(bt1);

        p.add(bt1);
        p.add(bt2);

        return p;
    }

    /**
     * create add button
     *
     * @return add button
     */
    private JButton createButonEdit() {
        editBtn = new JButton("Edit");
        editBtn.setToolTipText("Edit the note " + note.title());
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    edit();
                } catch (BadLocationException ex) {
                    Logger.getLogger(EditNoteDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(EditNoteDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(EditNoteDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return editBtn;
    }

    /**
     * create cancel button
     *
     * @return cancel button
     */
    private JButton createButonCancel() {
        cancelBtn = new JButton("Cancel");
        cancelBtn.setToolTipText("Cancel edition of note");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return cancelBtn;
    }

    /**
     * void method to execute edit note
     *
     * @throws DataConcurrencyException DataConcurrencyException
     * @throws DataIntegrityViolationException DataIntegrityViolationException
     * @throws BadLocationException BadLocationException
     */
    private void edit() throws BadLocationException, DataConcurrencyException, DataIntegrityViolationException {
        String content = txt.getText();
        if (controller.editNote(contactName, note, content)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Edition completed successfully!",
                    "Edit: " + note.title(),
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "It was not possible edit this note!",
                    "Edit: " + note.title(),
                    JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

}
