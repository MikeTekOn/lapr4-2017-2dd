package lapr4.blue.s1.lang.n1141570.XML.ui;

import csheets.core.Spreadsheet;
import csheets.ui.FileChooser;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import lapr4.blue.s1.lang.n1141570.XML.application.ExportXMLController;

/**
 *
 * @author Eric
 */
public class ExportSelectedColumnActionUI extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * Creates a new action.
     *
     * @param uiController the user interface controller
     */
    public ExportSelectedColumnActionUI(UIController uiController) {
        this.uiController = uiController;
    }

    protected String getName() {
        return "Selected column";
    }

    protected void defineProperties() {
    }

    /**
     * A simple action that presents a confirmation dialog. If the user confirms
     * then the selected column is exported to xml
     *
     * @param event the event that was fired
     */
    public void actionPerformed(ActionEvent event) {

        // Lets user select a font
        int result = JOptionPane.showConfirmDialog(null, "You have selected the column export option. Do you want to export?");

        if (result == JOptionPane.YES_OPTION) {
            List<String> chosenTagNames = new LinkedList<>();
            chosenTagNames.add("projectowner");
            chosenTagNames.add("scrumofscrums");
            chosenTagNames.add("scrum");

            Properties properties = new Properties();
            FileChooser fileChooser = new FileChooser(null, properties);
            String selectedPath = fileChooser.getFileToSave().getAbsolutePath();

            ExportXMLController exportXMLController = new ExportXMLController(uiController);
            Spreadsheet spreadsheet = uiController.getActiveSpreadsheet();
            int selectedColumn = uiController.getActiveCell().getAddress().getColumn();

            //Export Selected Column
            exportXMLController.exportSelectedColumn(spreadsheet, chosenTagNames, selectedPath, selectedColumn);
        }
    }
}
