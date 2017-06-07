/**
 * <p>Technical documentation regarding the user story <b>IPC01.2: Sharing's Automatic Update</b>.</p>
 *
 * <p><b>Scrum Master: no</b></p>
 * <p><b>Team Leader: no</b></p>
 *
 *
 *
 *
 * <h2>1. Notes</h2>
 *
 * <p>This functional increment is the continuation of the <b>IPC01.1</b> functional increment.</p>
 * <p>The communication framework is part of this feature and it was implemented in the first iteration.</p>
 * <p>
 *     Since this FI is an increment to the IPC01.1 FI, so all the documentation presented there is applied here. Any
 *     kind of modification will be referred in this page.
 * </p>
 *
 *
 *
 *
 * <h2>2. Requirements</h2>
 *
 * <p>After a connection is established, the updates made in one side must be seen in the other side.</p>
 * <p>The data shared must now include the style of the cells.</p>
 * <p>At the moment it is not required to share the cells with formulas.</p>
 *
 *
 *
 *
 * <h3>3. Analysis</h3>
 *
 * <h4>Clarifications with the product owner</h4>
 * <p><b>Q: </b>How to deal with the merges?</p>
 * <p><b>A: </b>The instance that starts sharing sends the contents and must be merged in the other instance.</p>
 *
 * <h4>Brainstorming</h4>
 * <p>
 *     There are two main points in this functional increment. One is about sharing the data in real time. The other
 *     is about sending the styles of the cells with the cells itself.
 * </p>
 *
 * <h4>Sharing cells in real time</h4>
 * <p>
 *     After a connection being established between two instances of CleanSheets, the communication must be made in
 *     <b>real time</b>. That is, if on instance changes a shared cell, it must automatically send the update to the
 *     other side and vice versa.
 * </p>
 * <p>
 *     The same logic from IPC01.1 will be used. The TCP singleton will create a worker thread to share the contents.
 *     The instance that starts sharing will send the contents to be initial merged on the other instance. After that,
 *     both instances must send and receive updates of the shared cells in real time. Given that, a <b>synchronization
 *     problem arises</b> and must be dealt.
 * </p>
 * <p>
 *     As both instances are going to send and receive data simultaneously, a server worker thread will be always active
 *     listening for changes. When a cell is edited, the client worker will send the updates to the other instance. The
 *     other instance will updated the cell that was changed. <b>The biggest synchronization problem comes when both
 *     instances are editing the same cell at the same time.</b>
 * </p>
 * <p>
 *     To solve this issue, only one cell can be edited at a given time. Once a cell starts being edited, a lock must be
 *     obtained for that instance. For that, a message must be sent to the other instance informing that the cell is
 *     being edited. Here we can identify the state pattern for the cells.
 * </p>
 * <p>
 *     If two instances start editing the same cell at the same time, both will send the edit state to the other
 *     instance. When one instance is editing a cell and receive a request that the same cell is being editing too, the
 *     cell will back to the initial state before start being edited. In the worst case, both instances will have to
 *     try to acquire a "lock" again.
 * </p>
 *
 * <h4>Send the style of the cells.</h4>
 * <p>
 *     To send the style of the cells, the CellDTO must be refactored to contain a the style of the cell.
 * </p>
 *
 *
 *
 *
 * <h2>4. Tests</h2>
 *
 * <h3>4.1. Unit Tests</h3>
 * <ol>
 *     <li>ensureCellSetsTheStyleOfDTO</li>
 * </ol>
 *
 * <h3>4.2. Functional Tests Plan</h3>
 * <p>5.1.1. Test the real time sharing</p>
 * <ol>
 *     <li>Run two instances of CleanSheets in the same local network</li>
 *     <li>Ensure the instances are reachable</li>
 *     <li>In both instances, go to the Network sidebar and press the "Activate" button</li>
 *     <li>
 *         With one instance, select the address of the other instance and press the "Connect" button
 *         <ul>
 *             <li>Either choose to make a safe connection or not (it's not relevant to test this functionality)</li>
 *         </ul>
 *     </li>
 *     <li>
 *         With one instance, fill the cells from B2 to to D4
 *         <ul>
 *             <li>Give a style to that cells</li>
 *             <li>
 *                 In the list of the connected hosts, select the other instance address and press the "Share Cells"
 *                 button
 *             </li>
 *         </ul>
 *     </li>
 *     <li>On the other instance, check if the shared cells appear in the worksheet</li>
 *     <li>
 *         with both instances, side by side, try to change the cells and make sure that it is updating on the other
 *         side
 *     </li>
 * </ol>
 *
 *
 *
 *
 * <h2>8. Work Log</h2>
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
 *     <li>Nothing</li>
 * </ol>
 *
 *
 *
 * <h3>Wednesday 07/06/2017</h3>
 *
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Presentation of the lang demo</li>
 *     <li>Starts the analysis</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Functional increment analysis.</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing</li>
 * </ol>
 *
 * @author Ivo Ferro [1151159]
 */
package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate;