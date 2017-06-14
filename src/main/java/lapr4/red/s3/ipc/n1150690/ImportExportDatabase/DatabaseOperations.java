/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase;

import csheets.core.Cell;
import eapli.util.Strings;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class DatabaseOperations {
    
    private Connection connection;
    private Statement statement;
    private String tableName;

    public DatabaseOperations(Connection connection, String tableName) throws SQLException {
        if(Strings.isNullOrEmpty(tableName) || Strings.isNullOrWhiteSpace(tableName)){
            throw new IllegalArgumentException("The table name can not be empty!");
        }
        this.tableName = tableName;
        this.connection = connection;
        this.statement = null;
    }

    public void createTable(int numberOfColumns) throws SQLException {
        String createTable = "CREATE TABLE" + tableName + "(";
        for (int i = 1; i <= numberOfColumns; i++) {
            if (i == numberOfColumns) {
                createTable += "Column" + i + "VARCHAR(255) NOT NULL)";
            } else {
                createTable += "Column" + i + "VARCHAR(255) NOT NULL,";
            }
        }
        statement = connection.createStatement();
        statement.executeUpdate(createTable);
    }
    
    public void fillTable(List<Cell> listCellsBetweenRange){
        String insert = "";
        
        
        
        
        //TODO
        
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException se2) {
        }// nothing we can do
    }
    
    private List<String> findCollumnNames(List<Cell> listCellsBetweenRange){        
        List<String> columnsName = new ArrayList<>();
        String name = "";
        int i = 0;
        for(Cell c : listCellsBetweenRange){
            int column = c.getAddress().getColumn();
            if(column < 27){
                name = "" + (char) ('A' + i);
            }else{
                name = "A" + (char) ('A' + i);
            }
            if(! columnsName.contains(name)){
                columnsName.add(name);
            }
            i++;
        }
        Collections.sort(columnsName);
        return columnsName;
    }
    
}
