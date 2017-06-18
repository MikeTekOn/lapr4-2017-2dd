/**
 * Technical documentation regarding the user story IPC04.3: Import/Export Database.
 *
 * <p>
 * <b>Scrum Master: no</b>
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 * <p>
 *
 * <h2>2. Requirement</h2>
 * <ul>
 * <li>It should be possible to export and import data to/from a table in a
 * database.</li>
 * <li>Each row in the table corresponds to a row in Cleansheets and each column
 * in the table corresponds to a column in Cleansheets.</li>
 * <li>The user should enter a range of cells to be used as source (export) or
 * destination (import) for the operation.</li>
 * <li>The first row of the range should be treated as a header.</li>
 * <li>Each column in the first row is used as the name of the corresponding
 * database column.</li>
 * <li>This feature should be based in jdbc (Java Database Connectivity). The
 * user should specify a database connection to be used and the name of the
 * table.</li>
 * </ul>
 * <p>
 *
 * <h2>3. Analysis</h2>
 *
 * <h3>3.1. Structure of the table in the database</h3>
 * <p>
 * When the user wishes to export multiple cells to the database, he must select
 * a range of cells in the spreadsheet, choose one database connection and the
 * name of the table. Assuming that the spreadsheet user is working contains
 * these values in cells <b>A1:C3</b>:
 * <table summary="">
 * <tr>
 * <th></th>
 * <th>A</th>
 * <th>B</th>
 * <th>C</th>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>123</td>
 * <td>56</td>
 * <td>aa</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>124</td>
 * <td>78</td>
 * <td>bb</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>125</td>
 * <td>94</td>
 * <td>cc</td>
 * </tr>
 * </table>
 * <p>
 * When the user selects the range A1: C3 and choose the name for the table
 * (example: test1), the table in the database should present the following
 * format (the primary keys may not correspond to reality):
 * <table summary="">
 * <tr>
 * <th>A</th>
 * <th>B</th>
 * <th>C</th>
 * </tr>
 * <tr>
 * <td>123</td>
 * <td>56</td>
 * <td>aa</td>
 * </tr>
 * <tr>
 * <td>124</td>
 * <td>78</td>
 * <td>bb</td>
 * </tr>
 * <tr>
 * <td>125</td>
 * <td>94</td>
 * <td>cc</td>
 * </tr>
 * </table>
 *
 * <h3>3.2. Connection to the database</h3>
 * <p>
 * The connection and operations made in the database should be based on jdbc
 * (Java Database Connectivity). For this we have to take into account some
 * aspects:
 * <ul>
 * <li><b>Register the JDBC driver:</b> Requires that you initialize a driver so
 * you can open a communication channel with the database. Ex:
 * <i>Class.forName(org.h2.Driver)</i></li>
 * <li><b>Open a connection:</b> Requires using the
 * <i>DriverManager.getConnection()</i> method to create a Connection object,
 * which represents a physical connection with the database</li>
 * <li><b>Execute a query:</b> Requires using an object of type Statement for
 * building and submitting an SQL statement to the database.</li>
 * <li><b>Extract data from result set:</b> Requires that you use the
 * appropriate ResultSet.getXXX() method to retrieve the data from the result
 * set.</li>
 * </ul>
 * <p>
 * To import/export data to/from a database, the user should specify the desired
 * database connection. The user should enter a valid url to establish the
 * connection. If the system can not establish a connection, it should return a
 * error to the user and give the option to insert a new url. After the
 * connection is establish, the user should insert a not empty name for the
 * table. At the time of the export, if the user enters a name for the table
 * that is not already in use, the table is updted with the new values. If the
 * table already exists, the user can choose delete the existing table or change
 * the table name. At the time of the import, the user can enter a range of
 * cells to import the data of a table. If the user does not enter a range of
 * cells, the system import the content of the table to the columns presents in
 * the database.
 *
 * <h2>4. Tests</h2>
 * <h3>4.1. Unit Tests</h3>
 * <p>
 * It is necessary to create a unit test to ensure that the name of the table
 * inserted by the user is not empty: ensureTableNameIsNotEmpty
 *
 * <h3>4.2. Functional Tests</h3>
 * <p>
 * <b>Export Data</b>
 * <ul>
 * <li>Insert in the column A, in the first four rows the values: 10, 20, 30,
 * 40</li>
 * <li>Insert in the column B, in the first four rows the values: aa, bb, cc,
 * dd</li>
 * <li>Insert in the column C, in the first four rows the values: 100, 200, 400,
 * 300</li>
 * <li>Export the data to the database by selecting the range A1:C4 and choosing
 * the name "Test1"</li>
 * <li>Verify in the choosen database that the content of the table "Test1"
 * should be:</li>
 * </ul>
 * <table summary="">
 * <tr>
 * <td>A</td>
 * <td>B</td>
 * <td>C</td>
 * </tr>
 * <tr>
 * <td>10</td>
 * <td>aa</td>
 * <td>100</td>
 * </tr>
 * <tr>
 * <td>20</td>
 * <td>bb</td>
 * <td>200</td>
 * </tr>
 * <tr>
 * <td>30</td>
 * <td>cc</td>
 * <td>400</td>
 * </tr>
 * <tr>
 * <td>40</td>
 * <td>dd</td>
 * <td>300</td>
 * </tr>
 * </table>
 * <p>
 * <b>Import Data</b>
 * <ul>
 * <li>Import the data from the database by choosing the name of the table
 * "Test1"</li>
 * <li>Verify in the select spreadsheet that content of the columns A, B and C
 * should be:</li>
 * </ul>
 * <table summary="">
 * <tr>
 * <th>A</th>
 * <th>B</th>
 * <th>C</th>
 * </tr>
 * <tr>
 * <td>10</td>
 * <td>aa</td>
 * <td>100</td>
 * </tr>
 * <tr>
 * <td>20</td>
 * <td>bb</td>
 * <td>200</td>
 * </tr>
 * <tr>
 * <td>30</td>
 * <td>cc</td>
 * <td>400</td>
 * </tr>
 * <tr>
 * <td>40</td>
 * <td>dd</td>
 * <td>300</td>
 * </tr>
 * </table>
 * <p>
 *
 * <h2>5. Design</h2>
 * <h3>5.1. Export Data to Database</h3>
 * <img src="ipc_04_3_export_sd.png" alt="image">
 * <p>
 * <h3>5.2. Import Data from Database</h3>
 * <img src="ipc_04_3_import_sd.png" alt="image">
 * <p>
 *
 * <h3>5.3. Classes</h3>
 *  <img src="ipc_04_3_cd.png" alt="image">
 *
 * <h2>5. Implementation</h2>
 *
 *
 * <h2>6. Integration/Demonstration</h2>
 * <p>
 *
 * <h2>7. Final Remarks</h2>
 * <p>
 *
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday: 13/06/2017</b>
 * <p>
 * Yesterday: our team distributed the funcionalities to be worked on this
 * sprint.
 * <p>
 * Today: I will start the analysis process.
 * <p>
 * Blocking:---
 * <p>
 * <b>Wednesday 14/06/2017</b>
 * <p>
 * Yesterday:I finished the analysis process.
 * <p>
 * Today:I will make a plan to the funcional tests and start the design.
 * <p>
 * Blocking:---
 * <p>
 * <b>Thursday 15/06/2017 </b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Friday 16/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:--
 * <p>
 * <b>Monday 17/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 *
 * <h2>9. Self Assessment</h2>
 *
 * <h3>R3. Rubric Requirements Fulfilment: </h3>
 *
 * <h3>R6. Rubric Requirements Analysis: </h3>
 *
 * <h3>R7. Rubric Design and Implement: </h3>
 *
 *
 * @author Sofia Silva 1150690@isep.ipp.pt
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase;
