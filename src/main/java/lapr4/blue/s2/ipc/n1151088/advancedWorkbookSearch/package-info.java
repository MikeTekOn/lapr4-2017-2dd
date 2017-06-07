/**
 * Technical documentation regarding the user story IPC02.2: Advanced Workbook Search
 * <p>
 * JIRA issue: LAPR4E17DD-79
 * </p>

 * <b>Scrum Master: no</b>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 * <p>This functional increment is the continuation of the <b>IPC02.1: Workbook Search</b>
 * 2017/06/06 - started analysing the user story
 * </p>
 * 
 * <h2>2. Requirements</h2>
 * <p>
 * The sidebar window that displays the results of the search should now include an area to display a preview
 * of the contents of a workbook when the user selects it (i.e., clicks on it). The preview should be based on
 * the values of the first non-empty cells of the workbook. This preview should be produced without open
 * the worksheet (at least without the worksheet been opened in the user interface). The search should now
 * be based on a pattern and not only on the file name extension.
 * </p>
 *
 * <b>Use Case "Advanced Workbook Search":</b>
 *
 * <ol>
 *      <li>The user selects "Advanced Workbook Search" option</li>
 *      <li>The system provides a new sidebar window for searching with a textbox for
 * the user enter a regular expression and a preview area of contents.</li>
 *      <li>The user enters the desired regular expression and launches the search</li>
 *      <li>The system displays the search results and displays the first non-empty
 * cells of the workbook on preview area</li>
 * </ol>
 * 
 * <img src="ipc02_2_ssd.png" alt="image"></img>
 * 
 * <b>Open Questions</b>
 * <p>Hom many cells should be displayed on preview area?
 *   Answer: Between 3..5 cells, or a little bit more.
 * </p>
 * <p>Should I preserv the basic search and create another one with advanced search? Or
 * will be there just one search?
 *   Answer: Update the last basic search.
 * </p>
 * 
 * <h3>3. Analysis</h3>
 *
 * <p>
 * It´s necessary to create a display area at sidebar window. This display area 
 * will be a preview image of cells (JPanel with some cells). This is will be an 
 * extension (SearchPreviewExtension).
 * </p>
 * <p>
 * It will be possible to execute this advanced search by an action that will call
 * the uiController of this use case. The class SearchMenu will be updated with 
 * this new action.
 * </p> 
 * <p>
 * The controller will be update with the preview functionality. To open the preview
 * are it´s necessary to create cell´s to include the expressions included in the
 * original one. 
 * </p>
 * 
 * <h2>8. Work Log</h2> 
 *
 * <b>Monday</b>
 * <ol>
 *      <li>Studying the requirements</li>
 *      <li>Analyzing the IPC02.1: Workbook Search functional (last sprint that 
 * will be incremented)</li>
 * </ol>
 *
 * <b>Tuesday</b>
 *  <ol>
 *     <li>New sprint planning</li>
 *     <li>Elaborate the anaylsis.</li>
 * </ol> 
 *
 * <b>Wednesday</b>
 * <ol>
 *     <li>Update analysis</li>
 *      <li>Start funcional/domain tests</li>
 * </ol>
 * 
 * @author Diana Silva - 1151088@isep.ipp.pt - 2DD - 2016/17
 */

package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;
