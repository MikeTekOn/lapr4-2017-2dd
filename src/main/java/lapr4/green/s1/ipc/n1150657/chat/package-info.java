/**
 * Technical documentation regarding the user story IPC05.1- Chat Send Message.
 * <p>
 * <b>Scrum Master: - no</b>
 *
 * <p>
 * <b>Area Leader: -no</b>
 *
 * <h2>1. Requirement</h2>
 * <ul>
 * <li>It should be possible to send text messages to another intance of Cleansheets.</li>
 * <li>It should be possible to display in a popup the message. This popup should disappear after some short period of time.</li>
 * <li>It should be possible to reply to a message by double clicking on it.</li>
 * </ul>
 * Notes:
 * <ul>
 * <li>The functionality should be based on a tree control that shows the messages grouped by a thread conversation.</li>
 * </ul>
 *
 * <h2>2. Analysis</h2>
 * <p>
 * <img src="us087_analysis.png" alt="image">
 * <p>
 * The chat sender will choose the chat receiver, establishing a connection with him.<p>
 * The connection will be established, and the sender will send a message.<p>
 * The chat receiver will receive the message and replied if he wants.<p>
 *
 * <b>2.1 Questions</b>
 * <ul>
 * <li>1. How long must the popup message last?
 * <b><i>Assumptions:</i></b> 5 seconds.<p>
 * <b><i>Answer:</i></b></li>
 * <li>1. There's a highlight to stand out an unread message?
 * <b><i>Assumptions:</i></b> yes.<p>
 * <b><i>Answer:</i></b>
 * </li>
 * </ul>
 *
 * <h2>3. Tests</h2><p>
 * This should include the functional tests, applying Test-Driven
 * Development, and unit tests.<p>
 *
 * <h3>3.1 Functional Tests:</h3>
 * <ul>
 * <li><b>Test1 - </b>ConnectionWithAnotherInstanceTest
 * This test will provide information regarding the connection, in other words, if the connection with
 * another instance is truly made.<p>
 * See TODO<p>
 * </li>
 * <li><b>Test2 - </b>MessageSentToAnotherInstanceTest
 * The base of this functionality is to send a message to another instance, wich mean is important to test
 * if a message is sent.<p>
 * See TODO<p>
 * </li>
 * <li><b>Test3 - </b>MessageReceivedTest
 * The other side of sending message is to receive it. This test will comprove that an instance will receive a message.<p>
 * See TODO<p>
 * </li>
 * <li><b>Test4 - </b>MessageRepliedTest
 * The test will demonstrate the possibily to answear to a message.<p>
 * See TODO<p>
 * </li>
 * </ul>
 * <h3>3.2 Unit Tests:</h3>
 * TODO
 *
 * <h2>4. Design</h2>
 * This sequence diagram represents the design for the tcp connection regardless the client.
 * The way the system will interact to established a connection and send a message.<p>
 * <img src="us087_design1.png" alt="image">
 * <p>
 * Another sequence diagram was made to represent the server side.<p>
 * <img src="us087_design2.png" alt="image">
 * <p>
 *
 *
 * <h2>5. Code</h2>
 * TODO
 */
package lapr4.green.s1.ipc.n1150657.chat;
