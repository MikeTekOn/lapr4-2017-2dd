package lapr4.blue.s1.lang.n1141570.XML.ui;

import csheets.core.Workbook;
import csheets.ui.FileChooser;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1141570.XML.application.ExportXMLController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @author Eric
 */
public class ExportWorkBookActionUI extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * Creates a new action.
     *
     * @param uiController the user interface controller
     */
    public ExportWorkBookActionUI(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Selected workbook";
    }

    @Override
    protected void defineProperties() {
    }

    /**
     * A simple action that presents a confirmation dialog. If the user confirms
     * then the workbook is exported to xml
     *
     * @param event the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        int result = JOptionPane.showConfirmDialog(null, "You have selected the Workbook export option. Do you want to export?");
        boolean exported = false;
        String selectedPath = "D:\\xml.xml";

        if (result == JOptionPane.YES_OPTION) {
            //Cancel option =1 , Approve option = 0
            int returnVal = 0;
            List<String> chosenTagNames = new LinkedList<>();

            TagNamesInputDialogUI exportXMLDialog = new TagNamesInputDialogUI(uiController);
            exportXMLDialog.setModal(true);
            exportXMLDialog.setVisible(true);
            chosenTagNames = exportXMLDialog.tagNamesDefinedByUser();

            Properties properties = new Properties();
            FileChooser fileChooser = new FileChooser(null, properties);
            if (returnVal == fileChooser.APPROVE_OPTION) {

                try {
                    selectedPath = fileChooser.getFileToSave().getAbsolutePath();
                } catch (Exception ex) {
                    System.err.println("Error selecting path");
                }

                ExportXMLController exportXMLController = new ExportXMLController(uiController);
                Workbook workbook = uiController.getActiveWorkbook();
                //Export Selected workbook
                exported = exportXMLController.exportWorkbook(workbook, chosenTagNames, selectedPath);
                JOptionPane.showMessageDialog(null, "Workbook exported successfully.", "Export XML", JOptionPane.PLAIN_MESSAGE);

            }
            if (!exported) {
                JOptionPane.showMessageDialog(null, "Failed to export Workbook.", "Export XML", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
