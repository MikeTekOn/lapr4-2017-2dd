/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class DatabaseOperationsTest {

    private Connection connection;

    public DatabaseOperationsTest() {
    }

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:..\\db\\csheets-crm-extension");
    }

    /**
     * Test for ensure that the table name can not be null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureTableNameCantBeNullOrEmpty() throws Exception {
        DatabaseOperations op = new DatabaseOperations(connection, null);
    }

    /**
     * Test for ensure that the table name can not contain only white spaces.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureTableNameCantContainSpacesOnly() throws Exception {
        DatabaseOperations op = new DatabaseOperations(connection, "  ");
    }
}
