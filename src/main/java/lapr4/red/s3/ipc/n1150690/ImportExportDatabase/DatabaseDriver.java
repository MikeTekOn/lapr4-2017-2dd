/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase;

/**
 * Enum class to register the drivers to connect to the database
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public enum DatabaseDriver {

    H2("H2 Database Engine", "org.h2.Driver", "jdbc:h2:..\\db\\csheets-crm-extension"),
    JavaDBEmbedded("Java DB (Embedded)", "org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:sampleDB;create=true"),
    MySQL("MySQL (Connector/J driver)", "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test"),
    PostgreSQL("PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql:testdb");

    /**
     * The name of the driver
     */
    private String name;

    /**
     * The value of the driver
     */
    private String value;

    /**
     * The default URL to connect to the database
     */
    private String defaultURL;

    /**
     * Creates a new instance of a database driver
     *
     * @param name name of the driver
     * @param value value of the driver
     * @param defaultURL default URL to connect to the database
     */
    private DatabaseDriver(String name, String value, String defaultURL) {
        this.name = name;
        this.value = value;
        this.defaultURL = defaultURL;
    }

    /**
     * Returns the name of the driver
     *
     * @return the name of the driver
     */
    public String enumName() {
        return this.name;
    }

    /**
     * Returns the value of the driver
     *
     * @return the value of the driver
     */
    public String value() {
        return this.value;
    }

    /**
     * Returns the default URL to connect to the database
     *
     * @return the default URL to connect to the database
     */
    public String defaultURL() {
        return this.defaultURL;
    }
}
