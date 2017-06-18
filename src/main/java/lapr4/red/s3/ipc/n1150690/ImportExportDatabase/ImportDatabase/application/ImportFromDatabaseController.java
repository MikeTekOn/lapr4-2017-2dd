/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ImportDatabase.application;

import csheets.ui.ctrl.UIController;
import java.sql.SQLException;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ImportDatabase.domain.ThreadImport;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ImportFromDatabaseController {

    /**
     * The UI Controller
     */
    private UIController uiController;

    /**
     * The table name chosen by the user.
     */
    private String tableName;

    /**
     * The database url.
     */
    private String db_url;
    
    private String driver;
    
    /**
     * The range of cells selected by the user
     */
    private CellRange range;

    /**
     * Creates a new Import from database controller
     *
     * @param uiController the user interface controller
     * @param tableName the table name
     * @param db_url the database url
     */
    public ImportFromDatabaseController(UIController uiController, String tableName, String db_url, CellRange range, String driver) {
        this.uiController = uiController;
        this.tableName = tableName;
        this.db_url = db_url;
        this.range = range;
        this.driver = driver;
    }

    /**
     * Exports a range of cells to the database.
     *
     * @throws SQLException
     */
    public void importFromDatabase() throws Exception {
        ThreadImport thread = null;
        try{
            thread = new ThreadImport(tableName, uiController.getActiveSpreadsheet(), db_url, range, driver);
            thread.start();
        }catch (Exception ex){
             thread.interrupt();
            throw new Exception(ex.getMessage());  
        }
    }
}
