/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ui;

import csheets.ui.FileChooser;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import lapr4.green.s1.ipc.n1150800.importexportTXT.FileData;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Properties;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ImportDataAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    public ImportDataAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Import Data";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Properties properties = new Properties();
        FileChooser fileChooser = new FileChooser(null, properties);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        
        File fileToRead = fileChooser.getFileToOpen();
        if(fileToRead != null) {
            if (FileData.validateFileExtension(fileToRead) && fileToRead.isFile()) {
                ImportDataUI ui = new ImportDataUI(uiController, fileToRead);
            } else {
                JOptionPane.showMessageDialog(fileChooser, "Chosen file is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
