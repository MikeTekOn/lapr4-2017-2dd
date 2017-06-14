/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.application;

import csheets.ui.ctrl.UIController;
import java.sql.SQLException;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.red.s1.core.n1150451.exportPDF.WorkbookHandler;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.domain.ThreadExport;

/**
 * A controller for export a range of cells to database.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ExportToDatabaseController {

    /**
     * The UI Controller
     */
    private UIController uiController;

    /**
     * The range of cells selected by the user
     */
    private CellRange range;

    /**
     * The table name chosen by the user.
     */
    private String tableName;

    /**
     * Creates a new export to database controller.
     *
     * @param uiController the user interface controller
     * @param range the range of cells
     * @param tableName the table name
     */
    public ExportToDatabaseController(UIController uiController, CellRange range, String tableName) {
        this.uiController = uiController;
        this.range = range;
        this.tableName = tableName;
    }
    
 /*public void changeTableName(String newName){
        this.tableName = newName;
    }*/

    /**
     * Exports a range of cells to the database.
     *
     * @throws SQLException
     */
    public void export(boolean tableExistsAndWillBeDeleted) throws SQLException, InterruptedException {
        ThreadExport thread = new ThreadExport(range, new WorkbookHandler(uiController.getActiveWorkbook()), tableName, uiController.getActiveSpreadsheet());
        thread.kill();
    }

}
