/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1151094.ImportXML.presentation;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.io.File;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import lapr4.red.s2.lang.n1151094.ImportXML.application.ImportXmlController;

/**
 *
 * @author Eduangelo Ferreira - 1151094
 */
public class ImportXmlActionUI extends BaseAction {

    /**
     * A controller for managing the current selection, i.e. the active
     * workbook, spreadsheet and cell, as well as for keeping track of
     * modifications to workbooks and of user interface extensions.
     */
    private final UIController controller;
    //This controller allows controlling the import actions in the system
    private final ImportXmlController importXmlController;
    private static final String DEFAULT_NAME = "XML";

    public ImportXmlActionUI(UIController controller) {
        this.controller = controller;
        this.importXmlController = new ImportXmlController(controller);
    }

    @Override
    protected String getName() {

        return DEFAULT_NAME;
    }

    @Override
    protected void defineProperties() {
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource("res/img/xml.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyJFileChooser fileChooser = new MyJFileChooser();
        int answer = fileChooser.showOpenDialog(null);

        if (answer == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (importXmlController.validateExtension(file)) {
                this.importXmlController.selectPath(file.getPath());
                this.importXmlController.importXml();
                JOptionPane.showMessageDialog(
                        null,
                        "Uploaded File", "Import",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Unable to read file: " + file.getPath() + " !",
                        "Import",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

    }

}
