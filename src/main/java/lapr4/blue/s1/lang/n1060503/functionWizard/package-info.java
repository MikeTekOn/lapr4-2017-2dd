/**
 * Technical documentation regarding the user story Lang04.1: Insert Function Basic Wizard.
 * <p>
 * JIRA issue: LAPR4E17DD-148
 * <p>
 * <p>
 * <b>Scrum Master: no</b>
 * <p>
 * <p>
 * <b>Area Leader: no</b>
 * 
 * <h2>1. Notes</h2>
 * 
 * 2017/05/30 - started analysing the user story
 * 
 * <h2>2. Requirement</h2>
 * <p>
 * Cleansheets should have a menu option to launch a wizard to aid the user in calling functions in formulas.
 * This new window should display a list of possible functions.
 * The construction of this list should be made dynamically based on the properties file and self-description of the functions.
 * When a function is selected in the list its syntax should be displayed in a edit box.
 * The "syntax" should include the name of the functions and its parameters.
 * <p>
 * 
 * <p>
 * For example, for the factorial function, that only has one parameter, the following text should be displayed in the edit box "= FACT(Number)".
 * The window should also contain an area to display a text describing the selected function (i.e., help text).
 * The window should have an "Apply" and a "Cancel" button.
 * If the user selects the "Apply" button the text of the syntax of the function should be written in the formula bar.
 * <p>
 * 
 * <p><b>Use Case "Insert Function Basic Wizard":</b><p>
 * 
 * <p><img src="ssd_insert_function_basic_wizard.png" alt="image"><p>
 * 
 * 
 * <p>  1. The user selects "Function Wizard" option.<p>
 * <p>  2. The system displays a new window that contains a list of functions.<p>
 * <p>  3. The user select a function.<p>
 * <p>  4. The system display the syntax and description of selected function.<p>
 * <p>  5. The user choose apply option.<p>
 * <p>  6. The system close the window and write the syntax function in the formula bar.<p>
 * 
 * 
 * <h2>3. Analysis</h2>
 * 
 * 
 * <h2>4. Design</h2>
 * 
 * <h3>4.1. Functional Tests</h3>
 * 
 * 
 * <h3>4.2. UC Realization</h3>
 * 
 * 
 * <h3>4.3. Classes</h3>
 * 
 * 
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * 
 * 
 * 
 * 
 * <h2>5. Implementation</h2>
 * 
 * 
 * <h2>6. Integration/Demonstration</h2>
 * 
 * 
 * 
 * <h2>7. Final Remarks</h2>
 * 
 * 
 * <h2>8. Work Log</h2> 
 * 
 * <b>Tuesday</b>
 * <p>
 * I worked on Analysis of the use story
 * <p>
 * 
 * 
 * <h2>9. Self Assessment</h2> 
 * 
 * 
 * 
 * <h3>R3. Rubric Requirements Fulfilment: 3</h3>
 * 
 * <h3>R6. Rubric Requirements Analysis: 4</h3>
 * 
 *
 * <h3>R7. Rubric Design and Implement: 2</h3>
 * 
 * 
 * 
 * 
 * 
 * @author Pedro Fernandes 1060503 - 2DD - 2016/17
 */
package lapr4.blue.s1.lang.n1060503.functionWizard;
