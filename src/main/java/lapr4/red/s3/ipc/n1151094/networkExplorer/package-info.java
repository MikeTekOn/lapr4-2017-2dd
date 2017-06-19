/**
 * <p>Technical documentation regarding the user story <b>IPC06.3: Network Explorer</b>.</p>
 *
 * <p><b>Scrum Master: no</b></p>
 * <p><b>Team Leader: no</b></p>
 *
 *
 *
 *
 * <h2>1. Notes</h2>
 * 1. nothing
 *<p>
 *
 * <h2>2. Requirements</h2>
 *
 * Cleansheets should now have a new sidebar window to display information about all the instances of Cleansheets in the same local network.
 * <p>
 * The window should be based on a tree control. 
 * <p>
 * The tree should display at the root all instances of Cleansheets.
 * <p>
 * For each instance it should display its resources as branches of the root element corresponding to the instance. 
 * <p>
 * The information should include: workbooks and respective worksheets; loaded extensions (if they are active or inactive) and its description and version.
 * <p>
 * This tree should be updated in real time
 *
 * <b>Use Case "Network Explorer":</b>
 * <p>
 *<ul>
 *      <li>The user starts the CleanSheets Instance Search.</li>
 *      <li>The system sends UDP broadcast.</li>
 *      <li>The other instances answer, sending TCP data.</li>
 * <ul>
 *
 * <h3>3. Analysis</h3>
 *
 * In this 3rd sprint I started by studying the comunication between instances which I confess I didn't really understand how it works.
 * <p>
 * In order to know all CleanSheets instances that are connected in the network I must send an UDP broadcast.
 * <p>
 * This make all the computers in the network know the one that send the broadcast.
 * <p>
 * As the other instances recieve the broadcast request, send a TCP answer with all needed data.
 * <p>
 * To show the existing CleanSheets instances I'll use a JTree.
 *
 *
 *
 *<h2>4. Design</h2>
 * <h3>4.1Functional Tests</h3>
 * <ol>
 *    <li>testGetAdrress()</li>
 *    <li>testGetListExtensionDesactivate()</li>
 *    <li>testGetListExtensionActivate()</li>
 *    <li>testGetWork()</li>
 * </ol>
 * <p>
 * <h3>Short Sequence Diagram</h3>
 * <img src="networkExplorer_ssd.png" alt="image">
 * <p>
 * <h2>9. Work Log</h2>
 *
 * <h3>Tuesday 06/06/2017</h3>
 *
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Analysis of previous IPC use cases implementations.</li>
 *     <li>Division and brainstorming about common points of the functional increments for this sprint.</li>
 *     <li>Studying the requirements of this use case.</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Functional increment analysis.</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 *
 *
 *
 * <h3>Wednesday 07/06/2017</h3>
 *
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Functional increment analysis.</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Functional increment tests.</li>
 *     <li>Functional increment design.</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 *
 *
 *
 * <h3>Thursday 08/06/2017</h3>
 *
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Functional increment tests.</li>
 *     <li>Functional increment design.</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Finish design.</li>
 *     <li>Implementation.</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 *
 *
 *
 * <h3>Friday 09/06/2017</h3>
 *
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Functional increment design.</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Implementation.</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 *
 *
 *
 * <h3>Saturday 10/06/2017</h3>
 *
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Implementation</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Implementation</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 *
 *
 *
 * <h3>Sunday 11/06/2017</h3>
 *
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Implementation</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Testing</li>
 *     <li>Fix bugs</li>
 *     <li>Update documentation</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 *
 *
 *
 * @author Eduângelo Das Neves Ferreira 
 */
package lapr4.red.s3.ipc.n1151094.networkExplorer;

