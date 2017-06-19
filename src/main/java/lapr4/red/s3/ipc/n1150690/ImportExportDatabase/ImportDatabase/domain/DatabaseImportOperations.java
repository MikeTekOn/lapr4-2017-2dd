/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ImportDatabase.domain;

import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import eapli.util.Strings;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to perform selection operations to the database.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class DatabaseImportOperations {

    /**
     * A connection (session) with a specific database.
     */
    private Connection connection;

    /**
     * The object used for executing a static SQL statement and returning the
     * results it produces.
     */
    private Statement statement;

    /**
     * The name of the table to import
     */
    private String tableName;

    /**
     * The active spreadsheet.
     */
    private Spreadsheet spreadsheet;

    /**
     * Creates a new import operation.
     *
     * @param connection the connection to the database
     * @param tableName the table name
     * @param spreadsheet the active spreadsheet
     */
    public DatabaseImportOperations(Connection connection, String tableName, Spreadsheet spreadsheet) {
        if (Strings.isNullOrEmpty(tableName) || Strings.isNullOrWhiteSpace(tableName)) {
            throw new IllegalArgumentException("The table name can not be empty!");
        }
        this.tableName = tableName;
        this.connection = connection;
        this.statement = null;
        this.spreadsheet = spreadsheet;
    }

    /**
     * Imports the content of the table to the active spreadsheet
     *
     * @throws SQLException
     * @throws FormulaCompilationException
     */
    public void importTableContent() throws SQLException, FormulaCompilationException {
        String content = "SELECT * FROM " + tableName;
        statement = connection.createStatement();
        ResultSet executeQuery = null;
        try {
            executeQuery = statement.executeQuery(content);
        } catch (SQLException e) {
            throw new SQLException("The table does not exist! Please insert another name.");
        }
        ResultSetMetaData metadata = executeQuery.getMetaData();

        int numberColumns = metadata.getColumnCount();
        List<Integer> columns = new ArrayList();
        for (int i = 1; i <= numberColumns; i++) {
            String c = metadata.getColumnName(i);
            columns.add(convertStringColumnToInteger(c));
        }

        int j = 2;
        while (executeQuery.next()) {
            for (int w = 1; w <= numberColumns; w++) {
                String c = (String) executeQuery.getObject(w);
                int column = columns.get(w - 1);
                int row = j - 2;
                spreadsheet.getCell(column, row).setContent(c);
            }
            j++;
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException se2) {
        }// nothing we can do
    }

    /**
     * Imports the content of the table to the active spreadsheet using a range
     * of cells defined by the user.
     *
     * @param topLeftRow the lop left row of the range
     * @param bottomRightRow the bottom right row of the range
     * @param topLeftColumn the top left column of the range
     * @param topRightColumn the top right column of the range
     * @throws SQLException 
     * @throws FormulaCompilationException
     */
    public void importTableContent(int topLeftRow, int bottomRightRow, int topLeftColumn, int topRightColumn) throws SQLException, FormulaCompilationException {
        String content = "SELECT * FROM " + tableName;
        statement = connection.createStatement();
        ResultSet executeQuery = statement.executeQuery(content);

        int numberColumns = topRightColumn - topLeftColumn + 1;

        while (executeQuery.next()) {
            int atualColumn = topLeftColumn;
            for (int w = 1; w <= numberColumns; w++) {
                String c = (String) executeQuery.getObject(w);
                int column = atualColumn;
                spreadsheet.getCell(column, topLeftRow).setContent(c);
                atualColumn++;
            }
            topLeftRow++;
            if (topLeftRow > bottomRightRow) {
                break;
            }
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException se2) {
        }// nothing we can do

    }

    /**
     * Transforms the column name into index.
     *
     * @param column the column name
     * @return the index of the column
     */
    private int convertStringColumnToInteger(String column) {
        if (column.length() == 1) {
            char c = column.charAt(0);
            return (int) (c - 65);
        } else {
            char c1 = column.charAt(0);
            char c2 = column.charAt(1);
            return (int) (((char) (c1 - 17) + (char) (c2 - 17)) - 48);
        }
    }

}
