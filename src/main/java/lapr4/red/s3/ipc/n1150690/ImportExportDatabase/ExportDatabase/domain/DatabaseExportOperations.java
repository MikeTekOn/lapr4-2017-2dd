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

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class DatabaseExportOperations {

    private Connection connection;
    private Statement statement;
    private String tableName;
    private String insertInto;

    public DatabaseExportOperations(Connection connection, String tableName) throws SQLException {
        if (Strings.isNullOrEmpty(tableName) || Strings.isNullOrWhiteSpace(tableName)) {
            throw new IllegalArgumentException("The table name can not be empty!");
        }
        this.tableName = tableName;
        this.connection = connection;
        this.statement = null;
        this.insertInto = "";
    }

    public void createTable(int numberOfColumns) throws SQLException {
        String createTable = "CREATE TABLE " + tableName + "(";
        insertInto = "INSERT INTO " + tableName + " (";
        for (int i = 1; i <= numberOfColumns; i++) {
            if (i == numberOfColumns) {
                createTable += "Column" + i + " VARCHAR(255) NOT NULL)";
                insertInto += "Column" + i + ") VALUES (";
            } else {
                createTable += "Column" + i + " VARCHAR(255) NOT NULL, ";
                insertInto += "Column" + i + ", ";
            }
            
        }
        statement = connection.createStatement();
        statement.executeUpdate(createTable);
    }

    public void fillTable(List<Cell> cellsBetweenRange, int numberColumns, int numberLines) throws SQLException {
        List<String> columnNames = findColumnNames(cellsBetweenRange, numberColumns);
        String firstLine = insertInto;
        for (int i = 0; i < columnNames.size(); i++) {
            if( i == columnNames.size() -1){
                firstLine += "'" + columnNames.get(i) + "')";
            }else{
                firstLine += "'" + columnNames.get(i) + "', ";
            }  
        }
        //statement = connection.createStatement();
        statement.executeUpdate(firstLine);
        String line = "";
        for(int j=0; j<numberLines; j++){
            line = insertInto;
            int w = 0;
            for(Cell c : cellsBetweenRange){
                if(c.getAddress().getRow() == j && c.getAddress().getColumn() == w){
                    if(w == numberColumns -1){
                        line += "'" + c.getContent() + "')";
                        break;
                    }else{
                        line += "'" + c.getContent() + "', ";
                        w++;
                    }
                }
            }
            statement.executeUpdate(line);
        }

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException se2) {
        }// nothing we can do
    }

    private List<String> findColumnNames(List<Cell> cellsBetweenRange, int numberColumns) {
        List<String> columnsName = new ArrayList<>();
        String name = "";
        int column = 0;
        for (int i = 0; i < numberColumns; i++) {
            for (Cell c : cellsBetweenRange) {
                column = c.getAddress().getColumn();
                if(column == i)break;
            }          
            if (column < 27) {
                name = "" + (char) ('A' + i);
            } else {
                name = "A" + (char) ('A' + i);
            }
            if (!columnsName.contains(name)) {
                columnsName.add(name);
            }
        }
        Collections.sort(columnsName);
        return columnsName;
    }

}
