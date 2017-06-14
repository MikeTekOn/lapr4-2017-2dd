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
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.red.s1.core.n1150451.exportPDF.WorkbookHandler;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.DatabaseConnection;

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
    private final Thread exportThread;

    /**
     * The current thread id.
     */
    private static long threadId = -2;

    /**
     * The is running boolean.
     */
    private static volatile boolean isRunning;

    /**
     * Creates
     *
     * @param range
     * @param workbook
     * @param tableName
     * @param spreadsheet
     */
    public ThreadExport(CellRange range, WorkbookHandler workbook, String tableName, Spreadsheet spreadsheet) {
        this.range = range;
        this.workbook = workbook;
        this.tableName = tableName;
        this.spreadsheet = spreadsheet;
        isRunning = true;
        this.exportThread = new Thread(this);
        this.exportThread.start();
    }

    /**
     * {@inheritDoc
     */
    @Override
    public void run() {
        threadId = Thread.currentThread().getId();
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            dbConnection.openConnection();
            DatabaseExportOperations dbOperations;
            dbOperations = new DatabaseExportOperations(dbConnection.connection(), tableName);
            int columns = range.getColumns();
            int rows = range.getRows();
            dbOperations.createTable(columns);
            int topLeftRow = range.getFirstCell().getAddress().getRow();
            int bottomRightRow = range.getLastCell().getAddress().getRow();
            int topLeftColumn = range.getFirstCell().getAddress().getColumn();
            int topRightColumn = range.getLastCell().getAddress().getColumn();
            List<Cell> listCellsBetweenRange = workbook.getListCellsBetweenRange(spreadsheet, topLeftRow, bottomRightRow, topLeftColumn, topRightColumn);
            dbOperations.fillTable(listCellsBetweenRange, columns, rows);
            dbConnection.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ThreadExport.class.getName()).log(Level.SEVERE, null, ex);
        }

        threadId = -2;
    }

    /**
     * Kills the current thread.
     */
    public static void kill() {
        isRunning = false;
    }

}
