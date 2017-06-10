/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.presentation;

import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import lapr4.green.s2.core.n1150838.GlobalSearch.GlobalSearchExtension;
import lapr4.green.s2.core.n1150838.GlobalSearch.application.GlobalSearchController;
import lapr4.green.s2.core.n1150838.GlobalSearch.domain.CellInfoDTO;
import lapr4.green.s2.core.n1150838.GlobalSearch.domain.Filter;
import lapr4.green.s2.core.n1150838.GlobalSearch.util.GlobalSearchPublisher;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class GlobalSearchSideBar extends JPanel implements Observer {

    private final Semaphore mutex = new Semaphore(1, true);//mutex
    private JTextField searchField;
    private UIController extension;
    private GlobalSearchController ctrl;
    private JList searchList;
    private CellList model;
    private ConfigPane paneFilters;

    public GlobalSearchSideBar(UIController extension) {
        GlobalSearchPublisher.getInstance().addObserver(this);
        setName(GlobalSearchExtension.NAME);
        this.extension = extension;
        ctrl = new GlobalSearchController(extension);
        paneFilters = new ConfigPane(ctrl);
        // Creates search components
        initComponents();
        setVisible(true);
    }

    /**
     *
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));
        JPanel textPanel = new JPanel();
        BoxLayout box = new BoxLayout(textPanel, BoxLayout.X_AXIS);
        textPanel.setLayout(box);
        textPanel.add(new JLabel("Enter Regex here: "));
        textPanel.add(searchField());
        upperPanel.add(textPanel);

        upperPanel.add(createButtons());
        add(upperPanel, BorderLayout.NORTH);

        //creates the panel with the search results list
        add(searchList(), BorderLayout.CENTER);

    }

    /**
     * Creates and returns the panel to show search results
     *
     * @return JScrollPanel
     */
    private JScrollPane searchList() {

        model = new CellList(new ArrayList());
        searchList = new JList(model);
        searchList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        searchList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Results: "));
        searchList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 1 && index >= 0) {
                    model.setSelectedItem((String) model.getElementAt(index));
                    extension.setActiveCell(model.getSelectedItem().getCell());
                }

            }
        });
        JScrollPane scroll = new JScrollPane(searchList);
        scroll.setPreferredSize(new Dimension(100, 200));
        return scroll;
    }

    /**
     * Creates and returns the field where the user can insert regular
     * expression
     *
     * @return a JTextField
     */
    private JTextField searchField() {
        searchField = new JTextField();
        return searchField;
    }

    /**
     * Creates a panel with all the necessary buttons
     *
     * @return a JPanel
     */
    public JPanel createButtons() {
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonSearch());
        panelButtons.add(buttonFilter());
        return panelButtons;
    }

    /**
     * Creates and returns a button to search.
     *
     * @return a JPanel
     */
    private JButton buttonSearch() {
        JButton b = new JButton("Search");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ctrl.stop();
                model.removeAll();
                if (ctrl.checkIfValid(searchField.getText()) && searchField.getText().length() > 0) {
                    List<String> typesToInclude = paneFilters.typesToInclude();
                    List<String> formulasToInclude = paneFilters.formulasToInclude();
                    boolean includeComments = paneFilters.includeComments();
                    ctrl.start(new Filter(typesToInclude, formulasToInclude, includeComments), searchField.getText());
                } else {
                    JOptionPane.showMessageDialog(null,
                            "The inserted regular expression is not valid.",
                            "Invalid regex",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }));

        return b;
    }

    private JButton buttonFilter() {
        JButton b = new JButton("Filters");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paneFilters.setVisible(true);

            }
        }));
        return b;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof CellInfoDTO) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                   model.addElement((CellInfoDTO) arg);  
                   searchList.updateUI(); 
                }
            });
        }

    }

}
