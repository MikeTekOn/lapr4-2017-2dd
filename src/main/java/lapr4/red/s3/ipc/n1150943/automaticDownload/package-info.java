
/**
 * Technical documentation regarding the user story LANG03.2 - Conditional Formatting of Ranges
 * <p></p>
 *
 * <h2>1. Notes</h2>
 *
 * <p> <b>Scrum Master: -(yes/no)- no</b> </p>
 * <p><b>Area Leader: -(yes/no)- no</b></p>
 *
 * <h2>2. Requirement</h2>
 *
 * In this use case I will extend the functionality to share files between Cleansheets instances.
 * <p></p>
 * The extension will automate the file sharing process. The first time that one file is shared with a user, he must choose wether the file is permanent or a one time file. If the file is a one time file the process will remain as it is now, if the file is permanent it will be automatically updated is the destiny user.
 * <p></p>
 * Once the user chooses that a file will be a permanent one he must also choose if the file will have version control or if it will simply be replaced every time there is an update.
 * This info will be stored as metadata of the file so when there is an update in the file owner he must send a signal for the destiny user to automatically download the updated version of the file.
 *
 * <h2>3. Analysis</h2>
 *
 * <h3>3.1 User Case Tasks</h3>
 *
 * <b>1.</b>As a User I want to be able to download a file that is shared by another Cleansheets instance (already implemented) <br>
 *     Story Acceptance Criteria 1: It should be possible to select a file from the shared list of another instance and download it into a local folder <br>
 * <b>2.</b> As a User I want to be able to select if a file is permanent or one time file<br>
 * <b>3.</b> As a User I want to get automatic updates in the permanent files in two different ways, replacing method or version mode <br>
 *
 * <h3>3.2 Domain Model</h3>
 * <p></p>
 * <img src="" alt="domain model">
 *
 * <h3>3.3 Use Case Scenarios / Functional Tests</h3>
 * <p></p>
 * Normal Behaviour Scenario 1 - The user chooses one file to download, then chooses the PermanentDownload type and finally the version type, for example ReplaceMode.
 * <p></p>
   The download starts and a download dialog appears showing the progress. Then the file owner updates the same file that was downloaded, it notifies the other machine and it starts the download of the new version of the file.
 *
 * <p>Normal Behaviour Scenario 2 -The user chooses one file to download, then chooses the OneTimeDownload type.</p>
 * <p>The download starts and a download dialog appears showing the progress. Then the file owner updates the same file that was downloaded, it notifies the other machine and it changes the state of the file to Out of Date.</p>
 *
 * <h2>Alternative and Exception Scenarios</h2>
 * Exception 1: -
 * <h3>3.4 Acceptance Tests</h3>
 *
 * <b>Exception 1</b><br>
 * <pre>
 * {@code
 * }
 * </pre>
 * <h2>4. Design</h2>
 *
 * <b>Sequence Diagrams</b>
 *
 * <h3>Short Sequence Diagrams</h3>
 *
 * <h4>Selection of the file to download</h4>
 * <img src="ssd1.png" alt="ssd">
 *
 * <h4>Actions caused by an update on a downloaded file</h4>
 * <img src="ssd2.png" alt="ssd">
 *
 * <h3>Detailed Sequence Diagram</h3>
 *
 * <img src="design.png" alt="sd">
 *
 * <b>Class Diagram</b>
 *
 * <img src="class_diag.png" alt="class diagram">
 *
 * <h2>5. Work Log</h2>
 *
 * <p><b>Day 1 - 13/06/2017</b></p>
 * <p>Yesterday I worked on:</p>
 * <p>1. Use case distribution inside the team, started to analyse the problem and study what had been already done in the previous sprint.</p>
 * <p>Today</p>
 * <p>1. Sprint 2 Demonstration and Work on UC analysis</p>
 * <p>Blocking:</p>
 * <p>1. -nothing-</p>
 *
 * <p><b>Day 2 - 14/06/2017</b></p>
 * <p>Yesterday I worked on:</p>
 * <p>1. Sprint 2 Demonstration and started the use case analysis and studying design patterns</p>
 * <p>Today</p>
 * <p>1. Start use case design and implementation</p>
 * <p>Blocking:</p>
 * <p>1. -nothing-</p>
 *
 * <h2>6. Self Assessment</h2>

/**
 * Created by k4rd050 on 12-06-2017.
 */
package lapr4.red.s3.ipc.n1150943.automaticDownload;