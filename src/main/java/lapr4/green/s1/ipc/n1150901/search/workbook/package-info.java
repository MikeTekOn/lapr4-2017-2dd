/**
 * Technical documentation regarding the user story IPC03.1- Search in Another Instance.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 * <p>
 *
 * <h2>1. Notes</h2>
 * No notes to be reported.
 *
 * <h2>2. Requirement</h2>
 * <b>Statement:</b><p>
 * It should be possible to send a request for searching workbooks to another
 * instance of Cleansheets. The search should be based on the name of the
 * workbook (a pattern of the name). The search should only include workbooks
 * that are open in the remote instance of Cleansheets. The reply must inform if
 * the workbook was found or not. If the workbook was found then the reply must
 * also include a summary of the contents of the workbook. This summary should
 * include the name of the worksheets and the values of the first non-empty
 * cells of each worksheet.
 *
 * <h2>3. Analysis</h2>
 * <b>SSD:</b><p>
 * <img src="us081_ssd.png" alt="ssd">
 * <p>
 * <b>Analysis SD:</b><p>
 * <img src="us081_analysis.png" alt="analysis">
 * <p>
 * After the user selecting the option to initiate the user story, a window with
 * the available connected instances will appear. The user will then choose the 
 * desired one and the system will request him for the name of the workbook to 
 * be searched. The user will introduce it and the system will present if the 
 * workbook was found or not. If it was found the system will also present a small
 * summary with the name of the spreadsheets and some of their cells content.
 *
 * <b>Notes:</b><p>
 * Class Cleansheet: This class is responsible for getting the open workbooks.
 * <p>
 * Class Spreadsheet: This class is responsible for getting the title of the
 * spreadsheet and the content of it's cells to present in the summary to the user.
 * <p>
 * Class UIExtensionSearchWorkbook: This class is responsible to build the extensions
 * menu that the user will select when he wants to performed the user story.
 * <p>
 * Class CtrlExtensionSearchWorkbook: This class is responsible for controlling the
 * flow of the user story.
 * <p>
 * Class CommTCPClientsManager: This class is responsible to hold the instances 
 * that are connected.
 * <p>
 * 
 * <h2>4. Open Questions</h2>
 * <ol>
 * <li>Where should it be located the button of this functionally?</li>
 * <li>How many values of cells of each worksheet must be shown? (Columns &
 * Rows)</li>
 * <li>Is it possible to exist two workbooks with the same name? If so, should
 * the user be warned or both workbooks be opened?</li>
 * <li>What should happen if the workbook has no spreadsheets?</li>
 * <li>What should happen if the spreasheet has no content?</li>
 * </ol>
 * 
 * <h2>5. Answers</h2>
 * 2. For each worksheet it should be shown the first 3 non-empty cells on the 
 * summary presented to the user.<p>
 *
 * <h2>6. Assumptions</h2>
 * 1. The button for this functionally should be in the "Extensions" Menu.<p>
 * 3. It is not possible to have two workbooks with the same name.<p>
 * 4. If the workbook has no spreadsheets the user should be warned.<p>
 * 5. If the spreadsheet has no content the user should be warned.<p>
 * </ul>
 *
 * <h2>7. Tests</h2>
 *
 * <b>Test1:</b> verifyWorkbookIsOpenInRemoteInstanceTest<p>
 * There should be a way to verify if the workbook found by the name that the
 * user introduced is opened in the current remote instance of the
 * cleansheets.<p>
 *
 * <b>Test2:</b> spreadsheetCellsAreNotAllEmptyTest<p>
 * There should be at least a spreadsheet with cells not empty.
 * <p>
 * 
 * <h2>8. Design</h2>
 * <img src="us081_design.png" alt="design">
 * <p>
 *
 * @author Miguel Silva (1150901)
 */
package lapr4.green.s1.ipc.n1150901.search.workbook;
