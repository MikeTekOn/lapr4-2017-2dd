/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch.ui;

import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import lapr4.red.s1.core.n1150613.workbookSearch.SearchExtension;
import lapr4.red.s1.core.n1150613.workbookSearch.application.WorkbookSearchController;

/**
 *
 * @author Diogo
 */
public class WorkbookSearchSideBar extends JPanel {

    private WorkbookSearchController controller;
    private JTextField searchField;
    private UIController extension;
    private List<String> info;

    /**
     * Panel with gridbaglayout.
     */
    private JList searchPanel;

    /**
     * Panel with gridbaglayout.
     */
    private final JPanel panel;

    private DefaultListModel model;

    public WorkbookSearchSideBar(UIController extension) {
        // Configures panel
        panel = new JPanel(new BorderLayout());
        setName(SearchExtension.NAME);

        // Creates controller
        this.extension = extension;
        controller = new WorkbookSearchController(extension);

        // Creates search components
        initComponents();

        //Adds borders
        TitledBorder border = BorderFactory.createTitledBorder("Workbook Search");
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(border);
        panel.setVisible(true);

    }

    /**
     *
     */
    private void initComponents() {
        panel.setLayout(new BorderLayout());
        //creates the panel with the search results list
        panel.add(searchList(), BorderLayout.NORTH);

        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.gridy = grid.gridx = 0;
        p.add(new JLabel("Enter Regex here"));

        p.add(searchField(), grid);
        grid.gridy = 1;

        p.add(buttonSearch(), grid);
        panel.add(p, BorderLayout.CENTER);
        super.add(panel);
    }

    /**
     * Creates and returns
     *
     * @return
     */
    private JScrollPane searchList() {
        model = new DefaultListModel();
        searchPanel = new JList();
        searchPanel.setModel(model);
        searchPanel.setBackground(this.getBackground());
        searchPanel.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Results: "));
        searchPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                // String regExp = (String) searchPanel.getSelectedValue();
                searchPanel.removeAll();
                searchPanel.updateUI();

            }
        });
        return new JScrollPane(searchPanel);
    }

    /**
     * Creates and returns the field where the user can insert regular
     * expression
     *
     * @return a JTextField
     */
    private JTextField searchField() {
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(100, 20));
        return searchField;
    }

    /**
     * Creates and returns a button to add a comment.
     *
     * @return a JButton
     */
    private JButton buttonSearch() {
        JButton b = new JButton("Search");
        b.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    model.clear();
                    info = controller.checkifRegexMatches(searchField.getText().trim());

                    for (String s : info) {

                        model.addElement(s);

                    }
                } catch (NullPointerException nll) {
                    JOptionPane.showMessageDialog(null,
                            "The inserted regular expression is not valid.",
                            "Invalid regex",
                            JOptionPane.ERROR_MESSAGE);
                }

                searchField.setText("");
            }
        }));
        return b;
    }

}
