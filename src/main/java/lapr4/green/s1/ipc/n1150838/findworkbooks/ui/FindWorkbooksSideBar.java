/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks.ui;

import csheets.ui.ctrl.UIController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FindWorkbooksPublisher;
import lapr4.green.s1.ipc.n1150838.findworkbooks.WorkbookDTO;
import lapr4.green.s1.ipc.n1150838.findworkbooks.ctrl.ControllerFindWorkbooks;

/**
 *
 * @author nunopinto
 */
public class FindWorkbooksSideBar extends JPanel implements Observer {

    private ControllerFindWorkbooks controller;
    private WorkbookList modeloWorkbook;
    private JList listWorkbook;
    private JTextField listField;
    private UIController extension;

    public FindWorkbooksSideBar(UIController extension) {
        this.extension = extension;
        FindWorkbooksPublisher.getInstance().addObserver(this);
        setUpMainPanel();
        buildPanel();

    }

    private void setUpMainPanel() {
        setLayout(new GridLayout(3, 1));
    }

    private void buildPanel() {
        add(labelInicial());
        add(mainList());
        add(buttonsPanel());
    }

    private JLabel labelInicial() {
        return new JLabel("Find Workbooks");
    }

    private JScrollPane mainList() {
        modeloWorkbook = new WorkbookList(new ArrayList());
        listWorkbook = new JList(modeloWorkbook);
        JScrollPane mainScroll = new JScrollPane(listWorkbook);
        return mainScroll;
    }

    private JPanel buttonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(labelField());
        buttonsPanel.add(fieldTextField());
        buttonsPanel.add(mainButton());
        buttonsPanel.add(openButton());
        return buttonsPanel;
    }

    private JLabel labelField() {
        return new JLabel("Insert path");
    }

    private JTextField fieldTextField() {
        listField = new JTextField(10);
        return listField;
    }

    private JButton mainButton() {
        JButton mainButton = new JButton("Search");
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modeloWorkbook.removeAll();
                    controller = new ControllerFindWorkbooks(listField.getText());
                    controller.searchFiles();
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Insert a valid path!");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FindWorkbooksSideBar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FindWorkbooksSideBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        return mainButton;
    }

    private JButton openButton() {
        JButton openButton = new JButton("Open");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listWorkbook.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Choose a file to open!");
                } else {
                    modeloWorkbook.setSelectedItem((String) listWorkbook.getSelectedValue());
                    WorkbookDTO dto = modeloWorkbook.getSelectedItem();
                    extension.setActiveWorkbook(dto.getWorkbook());
                }
            }
        });
        return openButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        WorkbookDTO workbook = (WorkbookDTO) arg;

        modeloWorkbook.addElement(workbook);
    }

}
