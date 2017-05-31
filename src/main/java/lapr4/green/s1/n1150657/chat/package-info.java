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
 * <li>It should be possible to send text messages to another intance of Cleansheets.<p></li>
 * <li>It should be possible to display in a popup the message. This popup should disappear after some short period of time.<p></li>
 * <li>It should be possible to reply to a message by double clicking on it.
 * </ul>
 * Notes:
 * <ul><li>The functionality should be based on a tree control that shows the messages grouped by a thread conversation.<p></ul></li>
 * 
 * <h2>2. Analysis</h2><p>
 * <img src="us087_analysis.png" alt="image">
 * <p>
 * The chat sender will choose the chat receiver, establishing a connection with him.<p>
 * The connection will be established, and the sender will send a message.<p>
 * The chat receiver will receive the message and replied if he wants.<p>
 * 
 * <b>2.1 Questions</b>
 * <ul>
 * <li>1. How long must the popup message last?</li>
 * <b><i>Assumptions:<b></i> 5 seconds.<p>
 * <b><i>Answer:</b></i>
 * <li>1. There's a highlight to stand out an unread message?</li>
 * <b><i>Assumptions:<b></i> yes.<p>
 * <b><i>Answer:</b></i>
 * </ul>
 * 
 * <h2>3. Tests</h2>
 * This should include the functional tests, applying Test-Driven
 * Development, and unit tests.<p>
 * 
 * <b>Test1</b><p>
 * TODO
 * <h2>4. Design</h2>
 * TODO
 * <h2>5. Code</h2>
 * TODO
 */
package lapr4.green.s1.n1150657.chat;
