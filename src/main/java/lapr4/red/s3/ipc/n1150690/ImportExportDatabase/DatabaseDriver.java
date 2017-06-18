/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public enum DatabaseDriver {
    
    H2 ("H2 Database Engine", "org.h2.Driver", "jdbc:h2:..\\db\\csheets-crm-extension"), 
    JavaDBEmbedded("Java DB (Embedded)", "org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:sampleDB;create=true"),
    MySQL("MySQL (Connector/J driver)", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/test"), 
    Oracle("Oracle", "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521:test"),
    PostgreSQL("PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql:testdb");

    private String name;
    private String value; 
    private String defaultURL;

    private DatabaseDriver(String name, String value, String defaultURL) {
        this.name = name;
        this.value = value;
        this.defaultURL = defaultURL;
    }

    public String enumName(){
        return this.name;
    }
    public String value(){
        return this.value;
    }
    
    public String defaultURL(){
        return this.defaultURL;
    }
}
