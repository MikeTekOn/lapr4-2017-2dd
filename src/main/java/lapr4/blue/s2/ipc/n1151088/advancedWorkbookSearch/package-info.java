/**
 * Technical documentation regarding the user story IPC02.2: Advanced Workbook Search
 * <p>
 * JIRA issue: LAPR4E17DD-79
 * </p>
* <p>
 * <b>Scrum Master: no</b>
 * <b>Area Leader: no</b>
 *</p>
 * <h2>1. Notes</h2>
 *
 * <p>This functional increment is the continuation of the <b>IPC02.1: Workbook Search</b>
 * 2017/06/05- started analysing the user story
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
 *      <li>The user enters the path rooth</li>
 *      <li>The system asks to insert the regular expression</li>
 *      <li>The user enters the regular expression</li>
 *      <li>The system displays the search results</li>
 *      <li>The user clicks on workbook to preview</li>
 *      <li>The systyem displays the first non-empty cells of the workbook on 
 * preview area</li>
 * </ol>
 * 
 * <img src="ipc02_2_ssd.png" alt="image">
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
 * <h3>Proposed Solution</h3>
 *
 * <b>Class UIPreviewWorkbookExtension:</b>
 * <p>
 * It´s necessary to create a display area at sidebar window. This display area 
 * will be a preview image of cells (JPanel containning Spreadsheet impemented 
 * class). In order to avoid blocking the application the fill preview area actions
 * will be buit-in thread.
 * </p>
 * <p>
 * <b>Class PreviewWorkbookPublisher</b>
 * </p>
 * <p>
 * This class will notify observers about preview action starting. 
 * </p>
 * <b>Class PreviewWorkbookBuilder</b>
 * <p>
 * * This class will be responsible to build and display the first non-empty cells. 
 * </p>
 * <b>FindWorkbooksSideBar</b>
 * <p>In the UI must be included a preview area content with "mini" workbook and the
 * mouse clicked event will be updated to this new functionality.</p>
 * 
 * <h3>Used classes that are already implemented</h3>
 * <b>Class ControllerFindWorkbooks</b>
 * <p> The controller will be updated with the new preview functionality and
 * search by pattern. It must be included the buildPreview thread and the stop building
 * method (p.e. if user clicks on search again, the current preview and search thread
 * must be stopped.</p>
 * <b>Classes Directory and SearchFile</b>
 * <p>
 * The search is now based in a patter instead of file name extension. This class will
 * implement the RegexFileFilter and Pattern java class. The java.nio.file package
 * provides the feature to list files within condition expressions. (p.e.: "%ls *.html"
 * lists all the files in the current directory that end in .html. Each file system 
 * implementation provides a PathMatcher (getPathMatcher(String) in FileSystem class.
 * We also can use regular expressions (regex) syntax.
 * </p>
 * 
 * <h2>4. Design</h2>
 * 
 * <h3>4.1. Tests </h3>
 * <p>Domain Test</p>
 * <ol>
 *    <li>ensureIsRegexValid</li>
 *    <li>testCheckIfMatches</li>
 *    <li>ensurePathIsValidPattern</li>
 *    <li>ensureFindFirstFilledCells</li>
 *    <li>ensurePreviewWithFirstFilledCells</li>
 *    <li>ensureSearchByPattern</li>
 *    <li>testSearchWorkbookByPartialName</li>
 * </ol>
 * <p>Functional Tests</p>
 * <ol>
 *  <li>A new area will be displayed to show a preview of workbook</li>
 *  <li>Fill preview area after click on workbook with the first filled cells of 
 * workbook clicked</li>
 *  <li>Worksheet isn´t open in the user interface</li>
 * </ol>
 * 
 * <h3>4.2. UC Realization</h3>
 * 
 * <img src="ipc02_2_sd.png" alt="image">
 *
 * <h3>4.3. Classes</h3>
 *
 * <img src="ipc02_2_cd.png" alt="image">
 * 
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
 *  <b>Thursday</b>
 * <ol>
 *     <li>Design</li>
 *      <li>Start tests implementation</li>
 * </ol>
 * 
 * @author Diana Silva - 1151088@isep.ipp.pt - 2DD - 2016/17
 */

package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;
