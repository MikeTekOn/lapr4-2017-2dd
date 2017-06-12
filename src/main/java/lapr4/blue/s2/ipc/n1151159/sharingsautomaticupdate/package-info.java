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
 *     Since this FI is an increment to the IPC01.1 FI, all the documentation presented there takes effect here. Any
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
 * <h4>Start Sharing</h4>
 * <p>
 *     To start sharing the cells, we must modify the CellDTO in order to include the style of the cell.
 *     As well, in the CommExtension, when we are dealing with the received DTO, we must set the received style.
 * </p>
 *
 * <h4>Sharing cells in real time</h4>
 * <p>
 *     After a connection being established between two instances of CleanSheets, the communication must be made in
 *     <b>real time</b>. That is, if on instance changes a shared cell, it must automatically send the update to the
 *     other side and vice versa.
 * </p>
 * <p>
 *     The same logic from IPC01.1 will be used. The worker thread will be responsible to share the contents.
 *     When the update of a shared cell happens, a listener must tell the TCP client manager to tell the correspondent
 *     worker thread to send a DTO with the new content of the changed cell.
 * </p>
 *
 * <h4>Send the style of the cells.</h4>
 * <p>
 *     To send the style of the cells, the same logic described above about sharing content applies. In turn, instead
 *     of sending a DTO with the content, the style will take place.
 * </p>
 *
 *
 *
 *
 * <h2>4. Tests</h2>
 *
 * <h3>4.1. Unit Tests</h3>
 * <p>For all the DTOs, it's necessary to create unit tests for the access methods. Some examples are:</p>
 * <ol>
 *     <li>getBackgroundColor for StyleDTO</li>
 *     <li>getAddress for CellStyleDTO</li>
 *     <li>getContent for CellContentDTO</li>
 *     <li>...</li>
 * </ol>
 * <p>Also, an util class will be created to create styles from StyleDTOs and vice versa. Some tests are:</p>
 * <ol>
 *     <li>setStyleFromDTO</li>
 *     <li>createStyleDtoFromCell</li>
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
 * <h3>5.1. Changes on CellDTO to send style</h3>
 * <img src="start_sharing.png" alt="start sharing placeholder">
 *
 *
 * <h3>5.2. Share cell content</h3>
 * <img src="share_cell_content_sd.png" alt="send cell content placeholder">
 * <p>
 *     The logic of sending the style of the cell is exactly the same, changing the content by style and using the
 *     equivalent classes for style.
 * </p>
 *
 *
 * <h3>5.3. Receive cell content</h3>
 * <img src="receive_cell_content_sd.png" alt="receive cell content placeholder">
 * <p>
 *     The logic of receiving the style of the cell is exactly the same, using the equivalent classes for style.
 * </p>
 *
 *
 * <h3>5.4. Design Patterns</h3>
 * <p>To make the communication possible, the observer pattern was used to make notifications about the updates.</p>
 * <p>Also, all good practices dictated by SOLID and GRASP was followed, to make the software maintainable.</p>
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
 * <h2>7. Integration/Demonstration</h2>
 *
 * <p>
 *     It was necessary to integrate this FI using the communication framework developed in the first iteration.
 * </p>
 * <p>I was very active and I successfully collaborate with the team members.</p>
 *
 *
 *
 *
 * <h2>8. Final Remarks</h2>
 * <p>
 *     As a possible future extra for this feature, when two hosts are sharing cells, it should be possible to
 *     highlight the cell that the other host is editing.
 * </p>
 *
 *
 *
 *
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
 * @author Ivo Ferro [1151159]
 */
package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate;