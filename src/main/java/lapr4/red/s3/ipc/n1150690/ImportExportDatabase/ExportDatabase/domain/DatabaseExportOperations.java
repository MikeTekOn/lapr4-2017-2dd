/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.domain;

import csheets.core.Cell;
import eapli.util.Strings;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.presentation.ExportToDatabaseUI;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class DatabaseExportOperations {

    private Connection connection;
    private Statement statement;
    private String tableName;
    private String insertInto;
    private int indexFirstColumn;
    private int indexFirstRow;

    public DatabaseExportOperations(Connection connection, String tableName) throws SQLException {
        if (Strings.isNullOrEmpty(tableName) || Strings.isNullOrWhiteSpace(tableName)) {
            throw new IllegalArgumentException("The table name can not be empty!");
        }
        if (tableName.matches("[0-9]+")) {
            throw new IllegalArgumentException("The table name can not contain only numbers!");
        }
        this.tableName = tableName;
        this.connection = connection;
        this.statement = null;
        this.insertInto = "";
        this.indexFirstColumn = Integer.MAX_VALUE;
        this.indexFirstRow = Integer.MAX_VALUE;
    }

    public void createTable(List<Cell> cellsBetweenRange, int numberOfColumns) throws SQLException {
        String createTable = "CREATE TABLE " + tableName + "(";
        insertInto = "INSERT INTO " + tableName + " (";
        List<String> columnNames = findColumnNames(cellsBetweenRange);
        for (int i = 0; i < columnNames.size(); i++) {
            if (i == columnNames.size() - 1) {
                createTable += columnNames.get(i) + " VARCHAR(255) NOT NULL)";
                insertInto += columnNames.get(i) + ") VALUES (";
            } else {
                createTable += columnNames.get(i) + " VARCHAR(255) NOT NULL, ";
                insertInto += columnNames.get(i) + ", ";
            }
        }
        statement = connection.createStatement();
        boolean deleteTable = false;
        boolean tableSameName = false;
        try {
            statement.executeUpdate(createTable);
            ExportToDatabaseUI.printSucess = true;
        } catch (SQLException e) {
            ExportToDatabaseUI.printSucess = false;
            int op = JOptionPane.showConfirmDialog(null, "A table with the name already exists! Do you want to delete the existing table?", "Warning", JOptionPane.YES_NO_OPTION);
            if (op == 0) {
                deleteTable = true;
            }
            tableSameName = true;
        } finally {
            if (tableSameName) {
                if (deleteTable) {
                    String sql = "DROP TABLE " + tableName;
                    statement.executeUpdate(sql);
                    statement.executeUpdate(createTable);
                } else {
                    ExportToDatabaseUI.printException("Please change the table name");
                }
            }
        }
    }

    public void fillTable(List<Cell> cellsBetweenRange, int numberOfColumns, int numberLines) throws SQLException {
        String line = "";
        int row = indexFirstRow;
        for (int j = 0; j < numberLines; j++) {
            line = insertInto;
            int column = indexFirstColumn;
            int countColumns = 0;
            for (Cell c : cellsBetweenRange) {
                if (c.getAddress().getRow() == row && c.getAddress().getColumn() == column) {
                    if (countColumns == numberOfColumns - 1) {
                        line += "'" + c.getContent() + "')";
                        break;
                    } else {
                        line += "'" + c.getContent() + "', ";
                        column++;
                        countColumns++;
                    }
                }
            }
            row++;
            statement.executeUpdate(line);
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException se2) {
        }// nothing we can do
    }

    private List<String> findColumnNames(List<Cell> cellsBetweenRange) {
        List<String> columnsName = new ArrayList<>();
        List<Integer> columnsIndex = new ArrayList();
        String name = "";
        int column = 0;
        int row = 0;
        for (Cell c : cellsBetweenRange) {
            column = c.getAddress().getColumn();
            row = c.getAddress().getRow();
            if (!columnsIndex.contains(column)) {
                columnsIndex.add(column);
            }
            if (column < indexFirstColumn) {
                indexFirstColumn = column;
            }
            if (row < indexFirstRow) {
                indexFirstRow = row;
            }
        }
        for (Integer i : columnsIndex) {
            if (i < 27) {
                name = "" + (char) ('A' + i);
            } else {
                name = "A" + (char) ('A' + (i - 26));
            }
            if (!columnsName.contains(name)) {
                columnsName.add(name);
            }
        }
        Collections.sort(columnsName);
        return columnsName;
    }

}
