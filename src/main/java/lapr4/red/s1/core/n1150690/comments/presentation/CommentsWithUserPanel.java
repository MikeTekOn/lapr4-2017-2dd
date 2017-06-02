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
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.application.AddCommentsWithUserController;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;
import lapr4.white.s1.core.n1234567.comments.ui.CommentPanel;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class CommentsWithUserPanel extends JPanel implements SelectionListener,
        CommentableCellWithMultipleUsersListener {

    /**
     * The assertion controller
     */
    private AddCommentsWithUserController controller;

    /**
     * Panel with gridbaglayout.
     */
    private final JPanel panel = new JPanel();

    /**
     * Panel with gridbaglayout.
     */
    //private JPanel panelComments = new JPanel();
    private JList panelComments;

    private DefaultListModel model;

    /**
     * The commentable cell currently being displayed in the panel
     */
    private CommentableCellWithMultipleUsers cell;

    /**
     * The text field in which the comments of the cell are displayed.
     */
    private JTextField commentField;
    
    private UIController uiController;

    /**
     * Creates a new comment panel.
     *
     * @param uiController the user interface controller
     */
    public CommentsWithUserPanel(UIController uiController) {
        // Configures panel
        super(new BorderLayout());
        setName(CommentsExtension.NAME);

        // Creates controller
        controller = new AddCommentsWithUserController(uiController);
        uiController.addSelectionListener(this);
        this.uiController = uiController;

        // Creates comment components
        initComponents();

        //Adds borders
        TitledBorder border = BorderFactory.createTitledBorder("Comment");
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(border);

        /*// Adds panels
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(panel, BorderLayout.NORTH);
        add(northPanel, BorderLayout.NORTH);*/
    }

    private void initComponents() {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.gridy = grid.gridx = 0;

        //creates the panel with the comments list
        panel.add(commentsList(), grid);

        grid.gridy = 3;
        panel.add(commentField(), grid);
        grid.gridy = 4;
        panel.add(buttonNewComment(), grid);
        super.add(panel);
    }
    
    /**
     *
     * @return
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
                //TODO
            }
        });
        return new JScrollPane(panelComments);
    }

    /**
     *
     * @return
     */
    private JTextField commentField() {
        commentField = new JTextField();
        commentField.setPreferredSize(new Dimension(100, 20));
        return commentField;
    }

    /**
     *
     * @return
     */
    private JButton buttonNewComment() {
        JButton b = new JButton("Add");
        b.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addComment(cell, commentField.getText());
                model.addElement(commentField.getText());
                commentField.setText("");
            }
        }));
        return b;
    }

    private JButton buttonChangeComment() {
        JButton b = new JButton("Change");
        b.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        }));
        return b;
    }

    @Override
    public void selectionChanged(SelectionEvent event) {
        CommentPanel panel = new CommentPanel(uiController);
        panel.selectionChanged(event);
    }

    @Override
    public void commentChanged(CommentableCellWithMultipleUsers cell) {
        this.cell = cell;
    }

}
