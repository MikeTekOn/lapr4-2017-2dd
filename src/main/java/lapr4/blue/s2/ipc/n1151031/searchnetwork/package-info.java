/**
 * Technical documentation regarding the user story ipc03.2: Search in the Network
 *
 * <b>Scrum Master: - no</b>
 * <p>
 *
 * <b>Area Leader: - no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 *
 * 2017/06/06 - started the FI analysis.
 *
 * <h2>2. Requirement</h2>
 * It should be possible to broadcast a workbook search request to all the
 * instances of Cleansheets in the same local network. The search should only
 * include the workbooks that are open. Cleansheets should have a sidebar window
 * to display - in a list - the results of the search. This window should be
 * updated as replies as received. The list of results should include the
 * identification of the instance where the workbook was found, the name of the
 * workbook and a summary of the contents of the workbook.
 *
 * <p>
 * <b>Use Case "Search in the Network":</b>
 * The user should select the option to search in the network and anter the name
 * of the workbook he wants to search for. The system will display a list with
 * the results of the search (instance name, name of the workbook and summary of
 * its content)
 *
 * <p>
 * <img src="ipc03_2_ssd.png" alt="image">
 *
 * <h2>3. Analysis</h2>
 * In order to analyze the search in the network feature increment we need to
 * learn with more detail about what was done in the previous ipc sprint.
 * Particularly, how the network core was implemented, so it's important to read
 * the documentation created by the Green team regarding the network
 * infrastructure.
 *
 * Then, we can analyze the steps needed to develop this feature increment.
 *
 * <h3>3.1 Sending the search request to the Network</h3>
 * The first thing to do is sending a broadcast search request to all the
 * CleanSheets in the same local network. In that request, we should send a DTO
 * object with the name pattern of the Workbook that we want to find. The class
 * RequestWorkbookRequestDTO must be created. It will store the name pattern and
 * will be used in the broadcast.
 *
 * <h3>3.2 Receiving the broadcast message with the Workbook search request</h3>
 * The instances of CleanSheets in the network will receive the search request
 * and after processing the received DTO, we need to get the local open
 * Workbooks. The class CleanSheets has a getWorkbooks() method that returns all
 * the active Workbooks so we can find if there are any Workbooks that match the
 * name received in the broadcast request. To process the DTO, we need to have a
 * HandlerSearchWorkbookRequestDTO.
 *
 * <h3>3.3 Sending the reply to the broadcast request</h3>
 * In case any of the local Workbooks match the name in the search request, the
 * instance needs to reply with:
 * <p>
 * <b>Instance identification:</b> identification of the instance where the
 * workbook was found. The response packet should have this information.
 * <p>
 * <b>Name:</b> the workbook's name. We can access it by using the getFile()
 * method of the Cleansheets class and then using the getName() of the File
 * class.
 *
 * <p>
 * <b>Summary:</b> a summary of the workbook's contents which consists of the
 * number and name of the spreadsheets. The class Spreadsheet has methods to
 * provide this information.
 *
 * To create the reply we need a SearchWorkbookNetworkResponseDTO and the
 * associated handler.
 *
 * <h3>3.4 Presenting the search results to the user</h3>
 * The information of the workbook's found in the network search should be
 * presented in a sidebar, as a list. The list will be updated as replies are
 * received. We will use the previous developed network sidebar to activate the
 * connection and integrate a new sidebar with the network search option, giving
 * the user the possibility to search for a workbook in the network.
 *
 * <h2>4. Design</h2>
 * So far, the design classes are:
 * <ul>
 * <li>SearchWorkbookNetworkExtension - the extension we are adding to
 * CleanSheets</li>
 * <li>SearchWorkbookRequestDTO - data transfer object for the UDP
 * broadcast</li>
 * <li>SearchWorkbookResponseDTO - data transfer object for the reply</li>
 * <li>HandlerSearchWorkbookRequestDTO - processes the request DTO</li>
 * <li>HandlerSearchWorkbookResponseDTO - processes the response DTO</li>
 * <li>SearchWorkbookNetworkAction - creates the UDPClient thread that will
 * handle the broadcast</li>
 * <li>SearchWorkbookNetworkPanel - sidebar panel for the feature</li>
 * </ul>
 *
 * <h3>4.1 How to send the UDP broadcast with the workbook name</h3>
 * <p>
 * <img src="ipc03_2_design1.png" alt="image">
 *
 * <h3>4.2 How to handle the requestDTO</h3>
 * <p>
 * Note: in order to access the instance's active workbooks we need access to
 * the App (Cleansheets class). We only have access to it through the
 * UIController. To solve this problem and be able to handle the search request
 * inside the request handler we have to pass the UIController to the
 * constructor of the handler. The class that creates the handler is the
 * CommExtension and it has a uiController and that should solve the problem.
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
 * <li>in one of the computers, search for a workbook name that is active in the other computer.</li>
 * <li>make sure the workbook is found and the list is updated with the correct information.</li>
 * <li>repeat for other files and using only the "cls" extension as name.</li>
 * <li>all the active workbooks must appear.</li>
 * </ul>
 *
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis of the previous ipc feature increments.
 * <p>
 * Today
 * <p>
 * 1. Client presentation of the Lang feature increments. 2. Started the
 * analysis of the search in the network feature increment.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Client presentation of the Lang feature increments. 2. Started the
 * analysis of the search in the network feature increment.
 * <p>
 * Today
 * <p>
 * 1. Analysis of the search in the network feature increment which was not
 * entirely finished. 2. Started the design of the FI.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis of the search in the network feature increment which was not
 * entirely finished. 2. Started the design of the FI.
 * <p>
 * Today
 * <p>
 * 1. Finish analysis. 2. Finish design.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 * <b>Friday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finished analysis. 2. Started design.
 * <p>
 * Today
 * <p>
 * 1. Finish design. 2. Start tests. 3. Start implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finished design. 2. Started tests. 3. Started implementation.
 * <p>
 * Today
 * <p>
 * 1. Finish tests and implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 * <p>
 *  * <b>Sunday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. ---
 * <p>
 * Today
 * <p>
 * 1. ---
 * <p>
 * Blocking:
 * <p>
 * 1. ---
 * <p>
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
package lapr4.blue.s2.ipc.n1151031.searchnetwork;
