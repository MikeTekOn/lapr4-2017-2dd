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
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.application.CommentsWithHistoryController;

/**
 * A panel for adding or editing comments for a cell.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 * @edit Miguel Silva - 1150901 I've made some changes in this class to be able
 * to implemet the comment's history.
 */
public class CommentsWithUserUI extends JPanel implements SelectionListener, CommentableCellListener {

    /**
     * The assertion controller
     */
    protected final CommentsWithHistoryController controller;

    /**
     * Panel with BorderLayout.
     */
    protected final JPanel panel = new JPanel();

    /**
     * The list that will contain the comments.
     */
    protected JList panelComments;

    /**
     * The list model to save the comments.
     */
    protected DefaultListModel model;

    /**
     * The text field in which the comments of the cell are displayed.
     */
    private JTextField commentField;

    /**
     * The comment that the user selected from the list.
     */
    protected String selectedComment;

    /**
     * The author of the comment that the user selected from the list.
     */
    protected String selectedUser;

    /* THIS IS A CHANGE MIGUEL MADE */
    /**
     * The cell where the listeners are applied.
     */
    protected CommentableCellWithMultipleUsers cell;

    /**
     * The color to paint the JList cell with.
     */
    protected Color color;

    /**
     * The font to set on the JList cell.
     */
    protected Font font;

    /**
     * A flag to know if the color or the font of the JList cell is being
     * changed.
     */
    protected int flag = 0;

    /* ----------------------- */

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
        /* THIS IS A CHANGE MIGUEL MADE */
        controller = new CommentsWithHistoryController(uiController);
        /* ----------------------- */
        uiController.addSelectionListener(this);

        // Creates comment components
        initComponents(uiController);

        //Adds borders
        TitledBorder border = BorderFactory.createTitledBorder("Comment");
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(border);
    }

    /**
     * Initiates the components.
     *
     * @edit Miguel Silva (1150901) Sprint 2 - I've edited this method to allow
     * to add another UI to the side bar of the comments.
     */
    private void initComponents(UIController uiController) {
        /* THIS IS A CHANGE MIGUEL MADE */
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        /* ----------------------- */

        //adds to the main panel, the comments list panel
        panel.add(commentsList());

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
        /* THIS IS A CHANGE MIGUEL MADE */
        p.setMaximumSize(p.getPreferredSize());
        panel.add(p);
        /* ----------------------- */

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
        panelComments.setCellRenderer(new ComplexCellRenderer());
        panelComments.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
            cell = (CommentableCellWithMultipleUsers) c.getExtension(CommentsExtension.NAME);
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

    /* THIS IS A CHANGE MIGUEL MADE */
    public class ComplexCellRenderer extends DefaultListCellRenderer{

        public ComplexCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            setText(value.toString());
            if (flag == 1) {
                setFont(font);
            } else if (flag == 2) {
                setForeground(color);
            } else if (flag == 3){
                setBackground(color);
            }

            return this;
        }
    }
    /* ----------------------- */
}
