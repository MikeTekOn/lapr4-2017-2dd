/**
 * Technical documentation regarding the user story Core07.1: Workbook Search.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 * No notes to be reported.
 * <p>
 *
 * <h2>2. Requirement</h2>
 *
 * The search option should now include all the open workbooks. The list of search results should be
 * interactive. When the user clicks a line in the search result Cleansheets should change the focus to the
 * respective cell. It should be possible to configure the search by selecting the set of type of cells and
 * formulas to include in the search. For instance, the search could only apply to cells of type date. The
 * search should now include also text in cell comments (if selected).
 * <p>
 *
 *
 * <b>Use Case "Global Search":</b> User selects "Workbook Search" option.
 * System provides a new sidebar window for searching with a textbox for the
 * user to enter a regular expression. User enters the desired regular
 * expression and launches the search. System displays the search results.
 *
 *
 *
 * <p>
 *
 * <h2>3. Analysis</h2>
 * 
 * <h3>Identified Problems(Not solved in the previous iteration):</h3>
 * 1.Include all workbooks in the search<p>
 * 2.Change cell focus<p>
 * 3.Configure the search:<p>
 * 3.1 Search by cell type<p>
 * 3.2 Formulas to include in the search <p>
 * 4.Search in the comments <p>
 * 
 * <h3>Proposed solution</h3>
 * 1. Include a method on the UIController class to return all the current workbooks.<p>
 * 2. Use an existing method on the UIController class to change the cell focus.<p>
 * 3.(3.1 and 3.2) On the side bar will exist a button to configure the search. When pressed that button will
 * open a new window to configure the search. A Filter class will be create to help on this problem.
 * When the user selects the filters (cell type and formulas) that he want to be included in the search
 * the Filter class will save the configuration. After that, the search method will verify if the current cell
 * verifies the desired configuration and if it does the method will check if the cell value respect the given
 * regular expression using a previous devolped RegexUtil class.<p>
 * 4. If the user selects the option to search in the comments the search method used will
 * also verify the comments section.
 * 
 * <h3>Used classes that are already implemented (Implemented in the previous iteration)</h3>
 * 1. RegexUtil (Will be extended in order to change the search method aplying to a multiple workbooks)<p>
 * 
 * <h3>Notes:</h3>
 * 1. Regular expressions used as search filter that were previously inserted by
 * user can be represented by Patterns in Java. Using Matcher class we can find
 * Workbook contents through the expressions.<p>
 * 2. In order to not block the application the searchs will be executing a diferent thread.<p>
 *
 * <img src="core07_02_analysis_ssd.png" alt="image">
 *
 * <p>
 * <h2>4. Design</h2>
 * <p>
 *
 * <h3>4.1. Functional Tests</h3>
 * 
 * testSearchInWorkbooksWithoutFilters() -&gt; Tests if it searches through the whole
 * workbooks and matches it's contents with the inserted regular expression.
 * testSearchInWorkbooksWithFilters() -&gt; Tests if it searches through the whole
 * workbooks and matches it's contents and filters with the inserted regular expression.
 * <p>
 *
 * <h3>4.2. UC Realization</h3>
 *
 * <img src="core07_01_design_sd.png" alt="image">
 *
 * <p>
 *
 * <h3>4.3. Classes</h3>
 *
 * <img src="core07_01_design_cd.png" alt="image">
 *
 * <p>
 *
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * <p>
 *
 * <h2>5. Implementation</h2>
 * 
 * Package lapr4.red.s1.core.n1150613.workbookSearch:<p>
 * {@link lapr4.red.s1.core.n1150613.workbookSearch.RegexUtil}
 * <p>
 * {@link lapr4.red.s1.core.n1150613.workbookSearch.SearchExtension}
 * <p>
 * Package lapr4.red.s1.core.n1150613.workbookSearch.application:<p>
 * {@link lapr4.red.s1.core.n1150613.workbookSearch.application.WorkbookSearchController}
 * <p>
 *  * Package lapr4.red.s1.core.n1150613.workbookSearch.ui:<p>
 * {@link lapr4.red.s1.core.n1150613.workbookSearch.ui.WorkbookSearchSideBar}
 * <p>
 * {@link lapr4.red.s1.core.n1150613.workbookSearch.ui.UIExtensionSearch}
 * <p>
 * {@link lapr4.red.s1.core.n1150613.workbookSearch.ui.SearchMenu}
 * <p>
 * {@link lapr4.red.s1.core.n1150613.workbookSearch.ui.SearchAction}
 * <p>
 * <p>
 *
 * <h2>6. Integration/Demonstration</h2>
 * <p>
 *
 * <h2>7. Final Remarks</h2>
 * <p>
 *
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday 30/05/2017</b>
 * <p>
 * Yesterday: our team distributed the funcionalities to be worked on this
 * sprint.
 * <p>
 * Today: I started the analysis process.
 * <p>
 * Blocking:---
 * <p>
 *
 *
 *
 * @author Nuno Pinto 1150838@isep.ipp.pt
 */
package lapr4.green.s2.core.n1150838.GlobalSearch;
