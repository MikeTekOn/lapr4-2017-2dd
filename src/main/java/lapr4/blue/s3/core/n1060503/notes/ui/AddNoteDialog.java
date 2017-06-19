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
import java.awt.GridLayout;
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
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import lapr4.blue.s3.core.n1060503.notes.NotesEditionController;

/**
 * Add note Dialog
 *
 * @author Pedro Fernandes
 */
public class AddNoteDialog extends JDialog {

    /**
     * notes edition controller
     */
    private NotesEditionController controller;

    /**
     * add button
     */
    private JButton addBtn;

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
     * name of contact
     */
    private String contactName;

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
     */
    public AddNoteDialog(NotesEditionController controller, DefaultStyledDocument doc,
            String contactName) {
        setModal(true);
        setTitle("Add Note to : " + contactName);
        this.doc = doc;
        this.controller = controller;
        this.contactName = contactName;

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
        JLabel l1 = new JLabel("The first line is the TITLE.", JLabel.CENTER);
        JLabel l2 = new JLabel("To write the note content, press Enter after title.", JLabel.CENTER);
        JPanel p = new JPanel(new GridLayout(2, 1));
        buildBorder(p, "Annotations:");
        p.add(l1);
        p.add(l2);

        panel.add(p, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Private method to build the border
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

        JButton bt1 = createButonAdd();
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
    private JButton createButonAdd() {
        addBtn = new JButton("Add");
        addBtn.setToolTipText("Add a note");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    add();
                } catch (BadLocationException ex) {
                    JOptionPane.showMessageDialog(
                    null,
                    "You must insert title in first line and"
                            + " the content on next lines!",
                    "Add note to contact: " + contactName,
                    JOptionPane.ERROR_MESSAGE);
                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(AddNoteDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(AddNoteDialog.class.getName()).log(Level.SEVERE, null, ex);                
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(
                    null,
                    "The content is empty!",
                    "Add note to contact: " + contactName,
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return addBtn;
    }

    /**
     * create cancel button
     *
     * @return cancel button
     */
    private JButton createButonCancel() {
        cancelBtn = new JButton("Cancel");
        cancelBtn.setToolTipText("Cancel addition of note");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return cancelBtn;
    }

    /**
     * void method to execute add note
     *
     * @throws DataConcurrencyException DataConcurrencyException
     * @throws DataIntegrityViolationException DataIntegrityViolationException
     * @throws BadLocationException BadLocationException
     */
    private void add() throws BadLocationException, DataConcurrencyException, DataIntegrityViolationException {
        Element root = txt.getDocument().getDefaultRootElement();
        Element first = root.getElement(0);
        String title = txt.getDocument().getText(first.getStartOffset(), first.getEndOffset());
        title = title.replaceAll("(\\r|\\n|\\t)", "");
        txt.getDocument().remove(first.getStartOffset(), first.getEndOffset());
        String content = txt.getText();
        if (controller.addNote(contactName, title, content)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Addition completed successfully!",
                    "Add note to contact: " + contactName,
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "It was not possible add a new note!",
                    "Add note to contact: " + contactName,
                    JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

}
