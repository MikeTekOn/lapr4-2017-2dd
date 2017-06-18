/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.domain;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.red.s1.core.n1150451.exportPDF.WorkbookHandler;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.DatabaseConnection;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.presentation.ExportToDatabaseUI;

/**
 * A Thread to perform the exportation to a database.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ThreadExport implements Runnable {

    /**
     * The range of cells.
     */
    private CellRange range;

    /**
     * The workbook handler.
     */
    private WorkbookHandler workbook;

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
    private Thread exportThread;

    /**
     * The database url.
     */
    private String db_url;
    
    /**
     * The database driver.
     */
    private String driver;

    /**
     * Creates a new Thread.
     *
     * @param range the range of cells
     * @param workbook the workbook handler
     * @param tableName the table name
     * @param spreadsheet the current spreadsheet
     */
    public ThreadExport(CellRange range, WorkbookHandler workbook, String tableName, Spreadsheet spreadsheet, String db_url, String driver) {
        this.range = range;
        this.workbook = workbook;
        this.tableName = tableName;
        this.spreadsheet = spreadsheet;
        this.db_url = db_url;
        this.driver = driver;
        this.exportThread = new Thread(this);
        this.exportThread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection(db_url, driver);
            dbConnection.openConnection();
            DatabaseExportOperations dbOperations;
            dbOperations = new DatabaseExportOperations(dbConnection.connection(), tableName);
            int columns = range.getColumns();
            int rows = range.getRows();
            int topLeftRow = range.getFirstCell().getAddress().getRow();
            int bottomRightRow = range.getLastCell().getAddress().getRow();
            int topLeftColumn = range.getFirstCell().getAddress().getColumn();
            int topRightColumn = range.getLastCell().getAddress().getColumn();
            List<Cell> cellsBetweenRange = workbook.getListCellsBetweenRange(spreadsheet, topLeftRow, bottomRightRow, topLeftColumn, topRightColumn);
            dbOperations.createTable(cellsBetweenRange, columns);
            dbOperations.fillTable(cellsBetweenRange, columns, rows);
            dbConnection.closeConnection();
        } catch (Exception ex) {
            ExportToDatabaseUI.printException(ex.getMessage());
        }
   }

    /**
     * Kills the current thread.
     */
    public void kill() throws InterruptedException {
        this.exportThread.join();
    }

}
