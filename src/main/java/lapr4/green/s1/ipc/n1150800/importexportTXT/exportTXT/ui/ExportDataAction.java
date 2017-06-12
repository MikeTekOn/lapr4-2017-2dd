/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.exportTXT.ui;

import csheets.ui.FileChooser;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import lapr4.green.s1.ipc.n1150800.importexportTXT.FileData;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Properties;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ReaderThread;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportDataAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    public ExportDataAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Export Data";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (ReaderThread.obtainsThreadId() == -1) {
            Properties properties = new Properties();
            FileChooser fileChooser = new FileChooser(null, properties);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setFileFilter(filter);

            File fileToWrite = fileChooser.getFileToSave();
            if (fileToWrite != null) {
                if (FileData.validateFileExtension(fileToWrite)) {
                    ExportDataUI ui = new ExportDataUI(uiController, fileToWrite);
                } else {
                    JOptionPane.showMessageDialog(fileChooser, "Chosen file is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cannot export data while importing!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
