/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ImportDatabase.domain;

import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.DatabaseConnection;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ImportDatabase.presentation.ImportFromDatabaseUI;

/**
 * A Thread to perform the importation from a database.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ThreadImport implements Runnable {

    /**
     * The table name.
     */
    private String tableName;

    /**
     * The current spreadsheet.
     */
    private Spreadsheet spreadsheet;

    /**
     * The writer thread.
     */
    private Thread importThread;

    /**
     * The database url.
     */
    private String db_url;

    /**
     * The database driver;
     */
    private String driver;

    private CellRange range;

    /**
     * Creates a new Thread
     *
     * @param tableName the table name
     * @param spreadsheet the current spreadsheet
     */
    public ThreadImport(String tableName, Spreadsheet spreadsheet, String db_url, CellRange range, String driver) {
        this.tableName = tableName;
        this.spreadsheet = spreadsheet;
        this.db_url = db_url;
        this.range = range;
        this.driver = driver;
        this.importThread = new Thread(this);
        this.importThread.start();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void run() {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection(db_url, driver);
            dbConnection.openConnection();
            DatabaseImportOperations dbOperations = new DatabaseImportOperations(dbConnection.connection(), tableName, spreadsheet);
            if (range != null) {
                int topLeftRow = range.getFirstCell().getAddress().getRow();
                int bottomRightRow = range.getLastCell().getAddress().getRow();
                int topLeftColumn = range.getFirstCell().getAddress().getColumn();
                int topRightColumn = range.getLastCell().getAddress().getColumn();
                dbOperations.importTableContent(topLeftRow, bottomRightRow, topLeftColumn, topRightColumn);
            } else {
                dbOperations.importTableContent();
            }
            dbConnection.closeConnection();
        } catch (Exception ex) {
            ImportFromDatabaseUI.printException(ex.getMessage());
        }
    }

    /**
     * Kills the current thread.
     */
    public void kill() throws InterruptedException {
        this.importThread.join();
    }
}
