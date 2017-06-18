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
     * The connection.
     */
    private Connection connection;

    /**
     * The database url.
     */
    private String db_url;

    /**
     * The database driver;
     */
    private String driver;

    /**
     * Creates a new DatabaseConnection.
     *
     * @param db_url the database url
     */
    public DatabaseConnection(String db_url, String driver) {
        this.connection = null;
        this.db_url = db_url;
        this.driver = driver;
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
            //Register JDBC driver
            Class.forName(driver);

            //Open a connection
            connection = DriverManager.getConnection(db_url);

        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Is not possible to connect to the database!");
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("Unable to load driver class!");
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
