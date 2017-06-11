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
 * <p>
 *     The cell's state just matters for the cells being shared. That way we are going to delegate this responsibility
 *     to the ShareableCell extension, using the delegation design pattern. In the following image we can observe the
 *     relations between the cell and the shareable cell.
 * </p>
 * <img src="shareable_cell_analysis.png" alt="shareable cell analysis placeholder">
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
 * <h2>5. Design</h2>
 *
 *
 * <h3>5.1. Cell's state diagram</h3>
 *
 * <img src="cells_state_diagram.png" alt="cells state diagram placeholder">
 *
 *
 * <h3>5.2. Changes on CellDTO to send style</h3>
 *
 * <img src="start_sharing.png" alt="start sharing placeholder">
 *
 * <h3>5.3. Extension setup</h3>
 *
 * <p>
 *     Refer to the logic of the extension manager presented in the package
 *     {@link lapr4.white.s1.core.n1234567.comments}
 * </p>
 *
 *
 * <h3>5.4. Design Patterns</h3>
 * <p>
 *     It was identified that the cells should behave in different manner depending on the actions that was happening.
 *     To solve this problem, the state diagram was applied.
 * </p>
 * <p>
 *     To decouple the cell state and the responsibility to deal with different states, the delegation design pattern
 *     will be applied. For that a ShareableCell cell extension will be created.
 * </p>
 *
 *
 *
 *
 * <h3>6. Implementation</h3>
 *
 * <h4>6.1. Changed classes</h4>
 * <p>
 *     It was necessary to change some already existing classes instead of creating new ones / making the extend
 *     mechanism.
 * </p>
 * <p>
 *     The class {@link lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO} was modified to add the StyledDTO of the
 *     shared cells. Although the extend mechanism could be used in this case, the cost would be high in terms of
 *     coupling, since there is a lot of usages of this class. A big refactor would be necessary.
 * </p>
 * <p>
 *     The class {@link lapr4.green.s1.ipc.n1150532.comm.CommExtension} was also modified in the update method. If we
 *     would extend this class, the method update would have to be overwritten. Since update method has a lot of logic
 *     depending on the instance of the received object (not a good approach, but for the time being a refactor would bes
 *     expensive), most of the logic would have to be duplicated. Also the usages of this class would have to be
 *     refactored as well.
 * </p>
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
 * @author Ivo Ferro [1151159]
 */
package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate;