/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Import.UI;

import csheets.core.Cell;
import csheets.ui.ctrl.UIController;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import lapr4.green.s3.lang.n1150657.XML.Export.Path;
import lapr4.green.s3.lang.n1150657.XML.Import.ImportXML;
import lapr4.green.s3.lang.n1150657.XML.Import.ImportXMLController;
import lapr4.red.s2.lang.n1151094.ImportXML.presentation.MyJFileChooser;

/**
 *
 * @author Sofia
 */
public class ImportSelectedCellsUI {
    private UIController uiController;
    private Cell selectedCells[][];

    public ImportSelectedCellsUI(UIController uiController,Cell selectedCells[][]) {
        this.uiController = uiController;
        this.selectedCells = selectedCells;
        initialize();
    }

    private void initialize() {
        MyJFileChooser fileChooser = new MyJFileChooser();
        int answer = fileChooser.showOpenDialog(null);

        if (answer == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            if (Path.validateExtension(file)) {
                
                Object[] options = {"Replace", "Append"};
                int choice = JOptionPane.showOptionDialog(null,
                        "Choose option:",
                        "Import option",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);

                if (choice == JOptionPane.YES_OPTION) {
                    ImportXMLController importXmlController = new ImportXMLController(uiController, file.getPath(), true);
                    importXmlController.importSelectedXml(selectedCells);
                } else {
                    ImportXMLController importXmlController = new ImportXMLController(uiController, file.getPath(), false);
                    importXmlController.importSelectedXml(selectedCells);
                }
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
