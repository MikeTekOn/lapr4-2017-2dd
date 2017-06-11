/**
 * Technical documentation regarding the user story Lang04.2 - Insert Function Intermediate Wizard.
 * <p>
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 *
 * <h2>2. Requirement</h2>
 *
 * The wizard window should display an edit box for each parameter of the
 * selected function. The user should use these edit boxes to enter the values
 * for each parameter of the function. As the user enters the values the wizard
 * should display (in a new region of the window) the result of the execution of
 * the formula or a message explaining the problem. The function list should now
 * include also the operators as well as the functions that are dynamically
 * loaded from java.lang.Math. The wizard should be now launched from an icon or
 * button located in the formula bar, between the label of the active cell and
 * the edit box of the formula/value of the current cell. The menu option should
 * be removed.
 *
 * <p>
 *
 * <h2>3. Analysis</h2>
 * <p>
 *
 * This use case is identical to the previous one used in sprint one of this
 * user story, with the differences in the User Interface as well in some new
 * Controller methods.
 *
 * <h3>Identified Problems:</h3>
 * 1.Display an edit box for each parameter of the selected function.<p>
 * 2.Display the result of the execution of the formula or a message explaining
 * the problem as the parameters are inserted.
 * <p>
 * 3.Function list should include also the operators as well as the functions
 * that are dynamically loaded from java.lang.Math
 * <p>
 * 4.The wizard should be now launched from an icon.
 * <p>
 * 5.The menu option should be removed.<p>
 *
 * <h3>Proposed solution</h3>
 * 1. Make defaultTableModel, used in UI to show function pretended parameters,
 * cells editable so they can turn into edit boxes to insert function
 * parameters.
 * <p>
 * 2. Add listener to know when the text box is updated and refresh the result
 * of the formula through a controller method yet to be implemented
 * (calculateResult) that will return the formula result each time one parameter
 * is inserted.
 * <p>
 * 3. Change main method from Language class to update function list by
 * importing operators and functions from java.lang.Math.
 * <p>
 * 4 & 5. Simple changes in UI,changing the Frame class to place the desired
 * button between the CellEditor and the formula bar. Remove the button from the
 * Menu.
 * <p>
 *
 * <img src="ssd_insert_function_intermediate_wizard.png" alt="image">
 *
 * <h2>4. Design</h2>
 * <p>
 *
 *
 * <h3>4.1. Functional Tests</h3>
 * <p>
 * <h3>4.2. UC Realization</h3>
 * Sequence Diagram
 *
 * <img src="sd2_insert_function_intermediate_wizard.png" alt="image">
 * <p>
 *
 * <h3>4.3. Classes</h3>
 *
 * <img src="cd_insert_function.png" alt="image">
 * <p>
 * <img src="cd2_insert_function.png" alt="image">
 * <p>
 *
 *
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * <p>
 * <h2>5. Implementation</h2>
 * <p>
 *
 * Package lapr4.red.s2.lang.n1150613.FunctionWizard:
 * <p>
 * Package lapr4.red.s1.core.n1150613.workbookSearch.application:
 * <p>
 * {@link lapr4.red.s2.lang.n1150613.FunctionWizard.IntermediateFunctionWizardController}
 * <p>
 *  * Package lapr4.red.s1.core.n1150613.workbookSearch.ui:
 * <p>
 * {@link lapr4.red.s2.lang.n1150613.FunctionWizard.ui.IntermediateFunctionWizard}
 * <p>
 * {@link lapr4.red.s2.lang.n1150613.FunctionWizard.ui.IntermediateFunctionWizardUI}
 * <p>
 * {@link lapr4.red.s2.lang.n1150613.FunctionWizard.ui.IntermediateFunctionWizardAction}
 * <p>
 * <p>
 * <h2>6. Integration/Demonstration</h2>
 * <p>
 *
 * <h2>7. Final Remarks</h2>
 * <p>
 *
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday 06/06/2017</b>
 * <p>
 * Yesterday: our team distributed the functionalities to be worked on this
 * sprint.
 * <p>
 * Today: I started the analysis.
 * <p>
 * Blocking:---
 * <p>
 * <b>Wednesday 07/06/2017</b>
 * <p>
 * Yesterday: Started analysis.
 * <p>
 * Today: I will finish the analysis and start design.
 * <p>
 * Blocking:
 * <p>
 * <b>Thursday 08/06/2017 </b>
 * <p>
 * Yesterday: Finished design and unit tests
 * <p>
 * Today: Start Implementation
 * <p>
 * Blocking:
 * <p>
 * <b>Friday 09/06/2017</b>
 * <p>
 * Yesterday: Done half of the implementation
 * <p>
 * Today: Finish implementation
 * <p>
 * Blocking:
 * <p>
 * <b>Monday 12/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 *
 *
 *
 * @author Diogo Guedes 1150613@isep.ipp.pt
 */
package lapr4.red.s2.lang.n1150613.FunctionWizard;
