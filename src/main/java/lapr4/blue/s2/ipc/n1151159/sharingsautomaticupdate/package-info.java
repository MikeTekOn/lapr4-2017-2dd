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
 *
 *
 *
 *
 * <h2>2. Requirements</h2>
 *
 * <p>After a connection is established, the updates made in one side must be seen in the other side.</p>
 * <p>The data shared must now include the style of the cells.</p>
 * <p>At the moment it is not required to share the cells with formulas</p>
 * <p>
 *     Since this FI is an increment to the IPC01.1 FI, so all the documentation presented there is applied here. Any
 *     kind of modification will be referred in this page.
 * </p>
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
 *     both instances must send and receive updates in the shared cells in real time. For that, a <b>synchronization
 *     problem arises</b> and must be dealt.
 * </p>
 *
 * <h4>Send the style of the cells.</h4>
 * <p>
 *     To send the style of the cells, the StyleableCell must be sent in over a DTO.
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
 *     <li>Nothing</li>
 * </ol>
 *
 * @author Ivo Ferro [1151159]
 */
package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate;