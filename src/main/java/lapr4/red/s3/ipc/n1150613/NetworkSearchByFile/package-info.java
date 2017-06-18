/**
 * Technical documentation regarding the user story IPC03.3: Network Search by File Contents.
 *
 * <b>Scrum Master: - no</b>
 * <p>
 *
 * <b>Area Leader: - no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 *
 *
 *
 * <h2>2. Requirement</h2>
 * The network search should now be based on a pattern for the name of the
 * workbook (as before) or a pattern of the contents of the workbook (or both).
 * The search should also include not only the open workbooks but also the
 * workbooks that exist in the remote file systems. The window with the list of
 * results should be updated automatically as the replies are received.
 *
 * <p>
 * <b>Use Case "Network Search by File Contents":</b>
 * The user should select the option to search in the network and enter the
 * pattern of the content/name or both of the workbook he wants to search for on
 * the remote system files. The system will display a list with the results of
 * the search (instance name, name of the workbook and summary of its content).
 *
 * <p>
 * <img src="ipc03_2_ssd.png" alt="image">
 *
 * <h2>3. Analysis</h2>
 *
 * <h3>3.1 Insert pattern to search for the name/content of the workbook</h3>
 * For this we will need to create a new class called RegexUtil which will
 * contain boolean methods that return if there are any workbooks name/content
 * or both that match the pattern(s) inserted by the user,such in file systems
 * as in active ones.
 *
 * <h3>3.2 Search in the remote file systems </h3>
 * In this case we will need to create other classes (Directory and FileDTO),
 * which will return all the workbooks in the remote file systems in the
 * network, adding them to the stack of workbooks to be checked,so the RegexUtil
 * class can check if they are the pretended ones or not.
 *
 * Note: The path selected in Directory class will be the user folder,which is
 * almost the root of the file systems,given by system properties from Java.
 * FileDTO will contain the name and the path of the file.
 * <p>
 *
 *
 * <h2>4. Design</h2>
 * So far, the design classes are:
 * <ul>
 * <li>NetworkSearchExtension - the extension we are adding to
 * CleanSheets</li>
 * <li>SearchWorkbookRequestDTO - data transfer object for the UDP
 * broadcast</li>
 * <li>SearchWorkbookResponseDTO - data transfer object for the reply</li>
 * <li>HandlerSearchWorkbookRequestDTO - processes the request DTO</li>
 * <li>HandlerSearchWorkbookResponseDTO - processes the response DTO</li>
 * <li>SearchWorkbookNetworkAction - creates the UDPClient thread that will
 * handle the broadcast</li>
 * <li>SearchWorkbookNetworkPanel - sidebar panel for the feature</li>
 * <li> Directory - Used to search the file systems.</li>
 * <li> FileDTO - contains the file information </li>
 * <li> RegexUtil - to search the given patterns </li>
 * </ul>
 *
 * <h3>4.1 How to send the UDP broadcast with the workbook name</h3>
 * <p>
 * <img src="ipc03_2_design1.png" alt="image">
 *
 * <h3>4.2 How to handle the requestDTO</h3>
 * <p>
 *
 *
 * <img src="ipc03_2_design3.png" alt="image">
 *
 * <h3>4.3 How to handle the responseDTO</h3>
 * <p>
 * <img src="ipc03_2_design2.png" alt="image">
 *
 * <h2>5. Tests</h2>
 *
 * <h3>Functional tests</h3>
 * <ul>
 * <li>Open instances of cleansheets in different computers.</li>
 * <li>open some workbooks in each instance.</li>
 * <li>go to Network sidebar and activate the network in both computers.</li>
 * <li>in one of the computers, search for a workbook name that is active in the
 * other computer.</li>
 * <li>make sure the workbook is found and the list is updated with the correct
 * information.</li>
 * <li>repeat for other files and using only the "cls" extension as name.</li>
 * <li>all the active workbooks must appear.</li>
 * </ul>
 *
 *
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;
