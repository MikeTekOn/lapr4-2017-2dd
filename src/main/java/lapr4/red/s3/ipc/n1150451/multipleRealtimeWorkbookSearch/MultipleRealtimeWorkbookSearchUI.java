/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150451.multipleRealtimeWorkbookSearch;

import csheets.ui.FileChooser;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui.FindWorkbookSideBar;

/**
 *
 * @author Diogo Santos
 */
public class MultipleRealtimeWorkbookSearchUI extends JPanel {

    private Map<String, FindWorkbookSideBar> panels = new HashMap<>();
    private int auxTabCount = 1;
    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * The file chooser to use when prompting the user for the destination path
     */
    protected JPanel mainPanel;

    protected JTabbedPane tabs;

    public MultipleRealtimeWorkbookSearchUI(UIController uiController) {
        this.uiController = uiController;
        createComponents();
        setFocusable(true);
    }

    private void createComponents() {
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        grid.gridx = 0;
        grid.gridy = 0;

        tabs = new JTabbedPane(JTabbedPane.TOP);

        tabs.addTab("search" + auxTabCount, createMainPanel());
        setLayout(new GridBagLayout());
        add(tabs, grid);
        grid.gridy = 1;
        add(createButtonsPanel(), grid);

    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton add = new JButton("New search");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                auxTabCount++;
                tabs.addTab("search" + auxTabCount, createMainPanel());
            }
        });
        panel.add(add);

        JButton delete = new JButton("Remove search");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxTabCount == 1) {
                    JOptionPane.showMessageDialog(getRootPane(), "It is not possible to delete the only search.");
                } else {
                    tabs.remove(tabs.getSelectedIndex());
                    auxTabCount--;
                }
            }
        });
        panel.add(delete);
        return panel;

    }

    private JPanel createMainPanel() {

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        grid.gridx = 0;
        grid.gridy = 0;
        JPanel panel = new JPanel(new GridBagLayout());
        FindWorkbookSideBar uCi = new FindWorkbookSideBar(uiController);
        panels.put("search" + auxTabCount, uCi);
        panel.add(uCi, grid);
        grid.gridy = 1;
        panel.add(createActiveSearchCheckBox(), grid);
        grid.weighty = 1;
        grid.gridy++;
        panel.add(new JLabel(" "), grid);
        return panel;
    }

    private JCheckBox createActiveSearchCheckBox() {
        JCheckBox cBox = new JCheckBox("Active search");
        cBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindWorkbookSideBar f = panels.get(tabs.getTitleAt(tabs.getSelectedIndex()));
                System.out.println((tabs.getTitleAt(tabs.getSelectedIndex())));
                if (cBox.isSelected()) {

                    if (f.checkFindControllerNull()) {

                        f.searchButton.doClick();
                        if (!f.flagSucessClick) {
                            f.isThreadActive = false;

                            cBox.setSelected(false);
                            return;
                        }
                    }
                    f.isThreadActive = true;
                    tabs.setForegroundAt(tabs.getSelectedIndex(), Color.RED);
                    f.searchButton.setEnabled(false);
                } else {
                    f.findController.stopSearch();
                    f.isThreadActive = false;
                    tabs.setForegroundAt(tabs.getSelectedIndex(), Color.BLACK);
                    f.searchButton.setEnabled(true);
                    if (f.checkFindControllerNull()) {
                        f.searchButton.doClick();
                    }
                }
            }
        });
        return cBox;
    }

}
