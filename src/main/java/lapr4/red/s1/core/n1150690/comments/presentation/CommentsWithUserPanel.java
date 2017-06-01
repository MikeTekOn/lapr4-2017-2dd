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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.application.AddCommentsWithUserController;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import lapr4.white.s1.core.n1234567.comments.CommentableCellListener;
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
    private JPanel panelComments = new JPanel();

    /**
     * The commentable cell currently being displayed in the panel
     */
    private CommentableCellWithMultipleUsers cell;

    /**
     * The text field in which the comments of the cell are displayed.
     */
    private JTextField commentField;

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

        // Creates comment components
        initComponents();

        //Adds borders
        TitledBorder border = BorderFactory.createTitledBorder("Comment");
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(border);

        // Adds panels
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(panel, BorderLayout.NORTH);
        add(northPanel, BorderLayout.NORTH);
    }

    private void initComponents() {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.gridy = 0;
        grid.anchor = GridBagConstraints.EAST;

        panelComments.setLayout(new GridBagLayout());
        panel.add(panelComments, grid);
        
        
        JPanel panelInsertComment = new JPanel();
        panelInsertComment.setLayout(new GridBagLayout());
        GridBagConstraints grid1 = new GridBagConstraints();
        grid1.gridy = grid1.gridx = 0;
        commentField();
        panelInsertComment.add(commentField, grid1); 
        grid1.gridx = 1;
        panelInsertComment.add(buttonNewComment(), grid1);
        
        panel.add(panelInsertComment);
        super.add(panel);
    }

    private void commentField() {
        commentField = new JTextField();
        commentField.setPreferredSize(new Dimension(100, 20));
    }

    private JButton buttonNewComment() {
        JButton b = new BasicArrowButton(BasicArrowButton.EAST);
        b.addActionListener((ActionEvent e) -> {
            controller.addComment(cell, commentField.getText());
            updatesCommentsField();
            commentField.setText("");
        });
        return b;
    }

    @Override
    public void selectionChanged(SelectionEvent event) {
       this.cell = new CommentableCellWithMultipleUsers(event.getCell());

    }

    @Override
    public void commentChanged(CommentableCellWithMultipleUsers cell) {
        this.cell = cell;

    }
    
    public void updatesCommentsField(){
        panelComments.removeAll();
        GridBagConstraints grid = new GridBagConstraints();
        Map<User, List<String>> comments = controller.comments(cell);
        for(Map.Entry<User, List<String>> entry : comments.entrySet()){
            for(String c : entry.getValue()){
                grid.gridy ++;
                grid.gridx = 0;
                JLabel l = new JLabel(entry.getKey().name() + ":");
                panelComments.add(l, grid);
                JTextField f = new JTextField(c);
                grid.gridx = 1;
                panelComments.add(f, grid);
            }
            
        }
    }

}
