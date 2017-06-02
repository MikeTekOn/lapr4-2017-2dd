/**
 * Technical documentation regarding the user story Core08.1: Basic PDF Export
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
 * The extension should provides a new sidebar window for searching the contents
 * of the active workbook. The window should be composed of two parts. The first
 * part (upper part of the window) should contain a textbox for the user to
 * enter a regular expression to be the basis for the search. This part should
 * also contain a button to launch the search. The second part (lower part of
 * the window) should be used to display the search results (cell coordinates
 * and value or contents). The search should include not only the content of the
 * cell (i.e., the text entered by the user) but also its value (that could have
 * been calculated by a formula).
 *
 * <p>
 *
 *
 * <b>Use Case "WorkBook Search":</b> User selects "Workbook Search" option.
 * System provides a new sidebar window for searching with a textbox for the
 * user to enter a regular expression. User enters the desired regular
 * expression and launches the search. System displays the search results.
 *
 *
 *
 * <p>
 *
 * <h2>3. Analysis</h2>
 * <p>
 *
 * Since workbook search will be supported in a new extension to Cleansheets we
 * need to study extensions implementation on Cleansheets application. * An
 * extension (searchExtension) must be created and it needs to be a subclass of
 * the Extension abstract class. The Extension class has a method called
 * getUIExtension that should be implemented and return an instance of a class
 * that is a subclass of UIExtension. In this subclass of UIExtension there is a
 * method (getSideBar) that returns the sidebar for the extension. A sidebar is
 * a JPanel.
 *
 * <p>
 *
 * Regular expressions used as search filter that were previously inserted by
 * user can be represented by Patterns in Java. Using Matcher class we can find
 * Workbook contents through the expressions.
 *
 * <img src="core07_01_analysis_ssd.png" alt="image">
 *
 * <p>
 * <h2>4. Design</h2>
 * <p>
 *
 * <h3>4.1. Functional Tests</h3>
 *
 *
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
 * <b>Wednesday 31/05/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Thursday 1/06/2017 </b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Friday 2/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Monday 5/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 *
 * <h2>9. Self Assessment</h2>
 *
 * <h3>R3. Rubric Requirements Fulfilment: </h3>
 *
 * <h3>R6. Rubric Requirements Analysis: </h3>
 *
 * <h3>R7. Rubric Design and Implement: </h3>
 *
 *
 * @author Diogo Guedes 1150@isep.ipp.pt
 */
package lapr4.red.s1.core.n1150613.workbookSearch;
