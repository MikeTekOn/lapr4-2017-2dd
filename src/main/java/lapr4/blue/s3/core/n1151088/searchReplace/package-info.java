/**
 * Technical documentation regarding the user story Core07.3: Search and Replace
 * <p>
 * JIRA issue: LAPR4E17DD-23
 * </p>
* <p>
 * <b>Scrum Master: no</b>
 * </p>
 * <p>
 * <b>Area Leader: no</b>
 *</p>
 * <h2>1. Notes</h2>
 *
 * <p>This functional increment is the continuation of the <b>Core07.2: Global Search</b>
 * 2017/06/13- started analysing the user story
 * </p>
 * 
 * <h2>2. Requirements</h2>
 * <p>
 * The extension should now include a new option "Search and Replace". This new
 * window should be similar to the search window but with an area to enter the 
 * replacing text. When search and replace is launched, when a match is found, 
 * the window should display "what" and where the match has occurred and how it
 * will become after the replace. The user should then confirm the replacement 
 * or select next (to continue the search). The window should include a button 
 * to apply the replacing to all the matches without asking each time. Similarly 
 * to the search only option, this option should also have parameters to refine
 * the search, for instance, what type of cells to include in the search (or if 
 * it should include formulas or comments).
 * </p>
 *
 * <b>Use Case "Search and Replace":</b>
 * 
 * <img src="core07_3_ssd.png" alt="image">
 * 
 * <b>Open Questions</b>
 * <p>When the window display "what", in case of a sentence/coment, how it
 * should be displayed?
 *   Answer: It doesn´t needed to be included all content but maybe the adjacent
 * text.
 * </p>
 * 
 * <h3>3. Analysis</h3>
 * 
 * The user should select the option to search and replace. The user can 
 * select the cell types or formulas to be included in the search. The system 
 * will ask the regular expression to search on open workbooks. The user should
 * insert the expression to find and replace. Then system will display a list 
 * with the results of the search (name of the workbook, no. spreadcheet and 
 * cell location). The user insert the replace text and system displays how cell
 * will became after the replace. Then the user can select one or all results to 
 * replace.
 * 
 * <b>System Sequence Diagram (SSD)</b>
 * <p>
 * <img src="core_07_03_ssd.png" alt="image">
 * </p>
 * <p>
 * In order no analyse the search and replace feature increment, first we need 
 * to accurate what was done in the previous sprints. So it´s important to read 
 * the documentation created and the classes implemented.
 * </p>
 * <p>
 * In the last sprint the green team complied the proposed requirements and the 
 * extra of search including formulas. 
 * </p>
 * <p>
 * After we study the new requirements, we can consider the next steps:
 * </p>
 * <h3>3.1 Update the window with an area to enter the replacing text</h3>
 * <p>
 * We will use the list results from the previous developed workbook search side
 * bar to be possible to select the results to replace text. It will be 
 * necessary to include an area to preview the cell content with the replace. 
 * </p>
 * <p>
 * To create and to manage the replace text concepts we need a class with the
 * responsability to store the matched cell with the replacement content and
 * allow display how match it will become before the replacing action confirmed.
 * So we can retrieve the cell content with getContent() of cell located. We 
 * already have the location of cell from the last sprint. This class also  will
 * check if the text to replace/value respect the formula compiler/parser using
 * a previous developed validations (validateFormula() in the controller).
 * </p>
 * <p>
 * In order to not block the system with the replace funcionality, a thread must 
 * be implemented to execute replace´s actions.
 * </p>
 * <h3>3.2 Include the option of replace just only one match each time or all
 * </h3>
 * <p>
 * On the one hand we need to allow show the searched results one at a time and
 * its content, on the other hand it will be possible to select all and its 
 * contents. It will be possible to select the result in the list of search 
 * workbook sidebar from the last sprint and replace. Also we have a next button
 * that will show the next search result content.
 * </p>
 * <h3>3.3 Include the possibility to display adjacent information in string</h3>
 * <p>
 * In the replace preview area, in case of a long string, it just appears the 
 * foreigner expressions and not all content. The Replace class will split the
 * expression to show only that information.
 * </p>
 * 
 * <h3>Concepts</h3>
 * <ul>
 *      <li>Cell - the cell in a spreadsheet and it can be interpreted as a 
 * formula (begin with an assignment) or as a value (when formula is evaluated, 
 * produce a value).</li>
 *      <li>Value - typed value that a cell can contain. We have different types
 * (Undefined, Numeric, Text, Boolean, Matrix, Error).</li>
 *      <li>Formula - formula in a cell. The evaluate() method will retrieve the
 * result value.</li>
 *      <li>Expression - an expression in a formula, that can be evaluated and 
 * visited. Examples of expressions: literal, operation, temporary variable,
 * reference, assignment or function call.</li>
 *      <li>CommentableCell - extension of a cell in a spreadsheet with support
 * for comments. It has an history and refer an user.</li>
 *      <li>Spreadsheet: this class is responsible to get the title of the
 * spreadsheet and the content of its cells to present in search results matched</li>
 *      <li>Workbook - a workbook which can contains spreadsheets.</li>
 *      <li>Cleansheet: this class is responsible to get the open workbooks.</li>
 *      <li>Address - an address in a spreadsheet that denotes to the location 
 * of a cell</li>
 * </ul>
 * 
 * <h3>Used classes that are already implemented in the previous iteration</h3>
 * <ol>
 *      <li>RegexUtilExtended- regular expressions used in search filter are
 * represented by Patterns in Java. Using Matcher class we can find Workbook 
 * contents through the expressions</li>
 *      <li>CellInfoDTO - the information that is displayed about cell (workbook,
 * title of spreadsheet, location)</li>
 *      <li>CellList - the list of cells that matched with regular expression in
 * the search</li>
 *      <li>CustomListString - abstract model list to manage the results of 
 * search. Now it will be possible to select one to replace.</li>
 * </ol>
 * 
 * <h2>4. Tests</h2>
 * <h3>Functional tests</h3>
 * <ol>
 *  <li>Open search and replace sidebar and introduce a valid expression to 
 * search and a replace text</li>
 *  <li>After system displays the search results and how first matched cell will 
 * be after the replacement, press the "Replace" button</li>
 *  <li>The cell is modified with new value</li>
 * </ol>
 * 
 * <ol>
 *  <li>Open search and replace sidebar and introduce a valid expression to 
 * search and a replace text</li>
 *  <li>After system displays the search results and how first matched cell will 
 * be after the replacement, press the "Show all" button</li>
 *  <li>The system show the new content of all matched cells</li>
 *  <li>The user press the "Replace All" button</li>
 *  <li>All cells are modified with new value</li>
 * </ol>
 * 
 * <h3>Unit tests</h3>
 * <p>rejectReplaceWithInvalidExpression() - tests if the replace text inserted 
 * is valid</p>
 * <p>replaceWithValidFormula</p>
 * <p>showForeignerContentOfFoundString()</p>
 * 
 * <h2>5. UC Realization</h2>
 * 
 * <ul>
 *     <li>SearchReplaceExtension - the extension used to implement this 
 * functionality in the application</li>
 *      <li>UISearchReplaceExtension - this class is responsible to build the
 * interface user where user can performs the find and replace features.</li>
 *      <li>ControllerSearchReplace - this class is responsible to control the 
 * flow of this user story.</li>
 *      <li>SearchReplaceAction - creates the replace thread to handle the 
 * replace actinos</li>
 *      <li>Search
 * </ul>
 * 
 * 
 * <h2>8. Work Log</h2> 
 *
 * <b>Monday</b>
 * <ol>
 *      <li>Analyzing the Core07.2: Global Search functional (last sprint that 
 * will be incremented)</li>
 * </ol>
 *
 * <b>Tuesday</b>
 *  <ol>
 *     <li>Studying the requirements</li>
 *     <li>New sprint planning</li>
 * </ol> 
 *
 * <b>Wednesday</b>
 * <ol>
 *     <li>Elaborate the anaylsis.</li>
 * </ol>
 * 
 * <b>Thursday</b>
 * <ol>
 *      <li>Update analysis</li>
 *      <li>Tests</li>
 *      <li>Starts design</li>
 * </ol>
 * 
 * @author Diana Silva - 1151088@isep.ipp.pt - 2DD - 2016/17
 */

package lapr4.blue.s3.core.n1151088.searchReplace;
