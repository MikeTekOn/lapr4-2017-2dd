/**
 * Technical documentation regarding the user story Lang04.1: Insert Function Basic Wizard.
 * <p>
 * JIRA issue: LAPR4E17DD-148
 * 
 * <p>
 * <b>Scrum Master: no</b>
 * 
 * <p>
 * <b>Area Leader: no</b>
 * 
 * <h2>1. Notes</h2>
 * 
 * 2017/05/30 - started analysing the user story
 * <p>
 * 2017/05/31 - update analysis and started design
 * 
 * <h2>2. Requirement</h2>
 * <p>
 * Cleansheets should have a menu option to launch a wizard to aid the user in calling functions in formulas.
 * This new window should display a list of possible functions.
 * The construction of this list should be made dynamically based on the properties file and self-description of the functions.
 * When a function is selected in the list its syntax should be displayed in a edit box.
 * The "syntax" should include the name of the functions and its parameters.
 * 
 * 
 * <p>
 * For example, for the factorial function, that only has one parameter, the following text should be displayed in the edit box "= FACT(Number)".
 * The window should also contain an area to display a text describing the selected function (i.e., help text).
 * The window should have an "Apply" and a "Cancel" button.
 * If the user selects the "Apply" button the text of the syntax of the function should be written in the formula bar.
 * <p>
 * 
 * <b>Use Case "Insert Function Basic Wizard":</b>
 * 
 * <p>
 * <img src="ssd_insert_function_basic_wizard.png" alt="image">
 * 
 * <ol>
 * <li>  The user selects "Function Wizard" option.</li> 
 * <li>  The system displays a new window that contains a list of functions.</li> 
 * <li>  The user select a function.</li> 
 * <li>  The system display the syntax and description of selected function.</li> 
 * <li>  The user choose apply option.</li> 
 * <li>  The system close the window and write the syntax function in the formula bar.</li> 
 * </ol>

 * 
 * <h2>3. Analysis</h2>
 * 
 * <p>
 * The user interface of the application it was implemented with Actions.
 * To build this functionality we had to respect this pattern.
 * The diagram above demonstrate what was done:
 * <p>
 * - ExtensionFunctionWizard
 * <p>
 * - UIExtensionFunctionWizard
 * <p>
 * - FunctionWizardMenu
 * <p>
 * - FunctionWizardAction
 * 
 * <p>
 * <img src="cd1_insert_function_basic_wizard.png" alt="image">
 * 
 * 
 * 
 * <h2>4. Design</h2>
 * 
 * <h3>4.1. Functional Tests</h3>
 * <ol>
 * <li>  Selects "Function Wizard" option.</li> 
 * <li>  A new window will appear. Choose the function: FACT.</li> 
 * <li>  On the label it will be possible see the description of the function and on edit box the syntax: =FACT()</li> 
 * <li>  Then click "Apply" button.</li> 
 * <li>  The system close the window and write the syntax function in the formula bar.</li> 
 * </ol>
 * 
 * <h3>4.2. UC Realization</h3>
 * 
 * <img src="sd_insert_function_basic_wizard.png" alt="image">
 * 
 * <h3>4.3. Classes</h3>
 * 
 * <p>
 * Class Diagram:
 * <p>
 * <img src="cd2_insert_function_basic_wizard.png" alt="image">
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
 * 
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Update analysis and started design
 * 
 * <h2>9. Self Assessment</h2> 
 * 
 * 
 * 
 * @author Pedro Fernandes 1060503 - 2DD - 2016/17
 */
package lapr4.blue.s1.lang.n1060503.functionWizard;
