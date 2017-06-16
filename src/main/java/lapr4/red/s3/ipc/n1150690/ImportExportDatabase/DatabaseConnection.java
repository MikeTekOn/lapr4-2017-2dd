package lapr4.red.s3.ipc.n1150690.ImportExportDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to open a session in a database
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class DatabaseConnection {

    /**
     * The h2 database driver.
     */
    private static final String JDBC_DRIVER = "org.h2.Driver";

    /**
     * The connection.
     */
    private Connection connection;

    /**
     * The database url.
     */
    private String db_url;

    /**
     * Creates a new DatabaseConnection.
     *
     * @param db_url the database url
     */
    public DatabaseConnection(String db_url) {
        this.connection = null;
        this.db_url = db_url;
    }

    /**
     * Returns the current connection.
     *
     * @return the current connection
     */
    public Connection connection() {
        return this.connection;
    }

    /**
     * Starts a connection.
     */
    public void openConnection() throws Exception {
        try {
            //STEP 2: Register JDBC driver
            //Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            connection = DriverManager.getConnection(db_url);

        } catch (SQLException se) {
            throw new SQLException("Is not possible to connect to the database!");
        } catch (Exception e) {
            throw new Exception("The driver can not be registered!");
        }
    }

    /**
     * Closes the connection.
     */
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
