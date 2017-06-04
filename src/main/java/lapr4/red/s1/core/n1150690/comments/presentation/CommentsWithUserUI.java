/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments.presentation;

import csheets.core.Cell;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.application.AddCommentsWithUserController;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import lapr4.white.s1.core.n1234567.comments.CommentableCell;
import lapr4.white.s1.core.n1234567.comments.CommentableCellListener;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

/**
 * A panel for adding or editing comments for a cell.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class CommentsWithUserUI extends JPanel implements SelectionListener, CommentableCellListener {

    /**
     * The assertion controller
     */
    private AddCommentsWithUserController controller;

    /**
     * Panel with BorderLayout.
     */
    private final JPanel panel = new JPanel();

    /**
     * The list that will contain the comments.
     */
    private JList panelComments;

    /**
     * The list model to save the comments.
     */
    private DefaultListModel model;

    /**
     * The text field in which the comments of the cell are displayed.
     */
    private JTextField commentField;

    /**
     * The comment that the user selected from the list.
     */
    private String selectedComment;

    /**
     * The author of the comment that the user selected from the list.
     */
    private String selectedUser;

    /**
     * Creates a new comment panel.
     *
     * @param uiController the user interface controller
     */
    public CommentsWithUserUI(UIController uiController) {
        // Configures panel
        super(new BorderLayout());
        setName(CommentsExtension.NAME);

        // Creates controller
        controller = new AddCommentsWithUserController(uiController);
        uiController.addSelectionListener(this);

        // Creates comment components
        initComponents();

        //Adds borders
        TitledBorder border = BorderFactory.createTitledBorder("Comment");
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(border);
    }

    /**
     * Initiates the components.
     */
    private void initComponents() {
        panel.setLayout(new BorderLayout());

        //adds to the main panel, the comments list panel
        panel.add(commentsList(), BorderLayout.NORTH);

        //adds to the main panel, the panel that contains the 
        //field to add and change the comments and the buttons
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.gridy = grid.gridx = 0;
        p.add(commentField(), grid);
        grid.gridy = 1;
        p.add(buttonNewComment(), grid);
        grid.gridy = 2;
        p.add(buttonChangeComment(), grid);
        panel.add(p, BorderLayout.CENTER);

        super.add(panel);
    }

    /**
     * Creates and returns the panel containing the comments list.
     *
     * @return the panel containing the comments list
     */
    private JScrollPane commentsList() {
        model = new DefaultListModel();
        panelComments = new JList();
        panelComments.setModel(model);
        panelComments.setBackground(this.getBackground());
        panelComments.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        panelComments.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "User: Comment"));
        panelComments.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                String comment = (String) panelComments.getSelectedValue();
                String data[] = comment.split(":");
                selectedUser = data[0].trim();
                selectedComment = data[1].trim();
                commentField.setText(selectedComment);
            }
        });
        return new JScrollPane(panelComments);
    }

    /**
     * Creates and returns the field where the user can add or change a comment.
     *
     * @return a JTextField
     */
    private JTextField commentField() {
        commentField = new JTextField();
        commentField.setPreferredSize(new Dimension(100, 20));
        return commentField;
    }

    /**
     * Creates and returns a button to add a comment.
     *
     * @return a JButton
     */
    private JButton buttonNewComment() {
        JButton b = new JButton("Add");
        b.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User author = controller.addComment(commentField.getText().trim());
                model.addElement(author.name() + ": " + commentField.getText());
                commentField.setText("");
            }
        }));
        return b;
    }

    /**
     * Creates and returns a button to change a comment.
     *
     * @return a JButton
     */
    private JButton buttonChangeComment() {
        JButton b = new JButton("Change");
        b.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.removeElement(selectedUser + ": " + selectedComment);
                User author = controller.changeComment(selectedComment, commentField.getText().trim(), selectedUser);
                model.addElement(author.name() + ": " + commentField.getText());
                commentField.setText("");
            }
        }));
        b.setToolTipText("<html>To change a comment:"
                + "<br>1.Select the comment from the list to change."
                + "<br>2.Change the comment in the box below the list."
                + "<br>3.Click this button</html>");
        return b;
    }

    /**
     * Updates the comments list.
     *
     * @param event the selection event that was fired
     */
    @Override
    public void selectionChanged(SelectionEvent event) {
        Cell c = event.getCell();
        if (c == null) {
            return;
        }
        if (c.getExtension(CommentsExtension.NAME) == null) {
            return;
        }
        if (c != null) {
            CommentableCellWithMultipleUsers cell
                    = (CommentableCellWithMultipleUsers) c.getExtension(CommentsExtension.NAME);
            controller.changeActiveCell(cell);
            cell.addCommentableCellListener(this);
        }

        // Stops listening to previous active cell
        if (event.getPreviousCell() != null) {
            ((CommentableCellWithMultipleUsers) event.getPreviousCell().getExtension(CommentsExtension.NAME))
                    .removeCommentableCellListener(this);
        }

        updateList();
    }

    /**
     * Updates the comments list when the comments of the active cell are
     * changed.
     *
     * @param cell the cell whose comments changed
     */
    @Override
    public void commentChanged(CommentableCell cell) {
        updateList();
    }

    /**
     * Updates the comments list.
     */
    private void updateList() {
        model.removeAllElements();
        Map<User, List<String>> comments = controller.comments();
        for (Map.Entry<User, List<String>> entry : comments.entrySet()) {
            for (String com : entry.getValue()) {
                model.addElement(entry.getKey().name() + ": " + com);
            }
        }
    }

}
