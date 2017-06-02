/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks.ui;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FindWorkbooksPublisher;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FileDTO;
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
    // set the main panel properties 
    private void setUpMainPanel() {
        setLayout(new BorderLayout());
    }
    /*
    * calls all the methos to build the panel
    */
    private void buildPanel() {
        add(labelInicial(),BorderLayout.NORTH);
        add(mainList(),BorderLayout.CENTER);
        add(buttonsPanel(),BorderLayout.SOUTH);
    }
    /*
    * creates the inicial label 
    */
    private JPanel labelInicial() {
        JLabel labelInicial = new JLabel("<html>Find Workbooks <br>(double click to open)</html>");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100,40));
        panel.add(labelInicial);
        return panel;
    }
    /*
    * creates the list that will contain the files to open and
    * the program behavior when a mouse is double clicked
    */
    private JScrollPane mainList() {
        modeloWorkbook = new WorkbookList(new ArrayList());
        listWorkbook = new JList(modeloWorkbook);
        listWorkbook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 2 && index >= 0) {

                    // Double-click detected
                    modeloWorkbook.setSelectedItem(((String) modeloWorkbook.getElementAt(index)));
                    FileDTO dto = modeloWorkbook.getSelectedItem();

                    try {
                        Workbook b =controller.load(dto.getFilePath());
                        extension.setActiveWorkbook(b);
//               
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "File is corrupted or failed to load!");
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "File is corrupted or failed to load!");
                    }

                }
            }
        });
        JScrollPane mainScroll = new JScrollPane(listWorkbook);
        return mainScroll;
    }
    /**
     * creates the button to search for files
     * @return 
     */
    private JPanel buttonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(labelField());
        buttonsPanel.add(fieldTextField());
        buttonsPanel.add(mainButton());
         buttonsPanel.setPreferredSize(new Dimension(100,85));
        return buttonsPanel;
    }
    /**
     * creates the field inser path
     * @return 
     */
    private JLabel labelField() {
        JLabel label = new JLabel("<html>Insert path</html>");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField fieldTextField() {
        listField = new JTextField(10);
        return listField;
    }
    /**
     * creates the main button and the behavior off the method if a user clicks to search for files.
     * @return 
     */
    private JButton mainButton() {
        JButton mainButton = new JButton("Search");
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(controller!=null)controller.stopSearch();
                    modeloWorkbook.removeAll();
                    controller = new ControllerFindWorkbooks(listField.getText());
                    controller.searchFiles();
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Insert a valid path!");
                }
            }

        });
        return mainButton;
    }
    /**
     * update method use when a new file was found after a search
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        FileDTO workbook = (FileDTO) arg;

        modeloWorkbook.addElement(workbook);
    }

}
