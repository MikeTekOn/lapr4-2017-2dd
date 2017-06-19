/**
 * Technical documentation regarding the user story Lang04.3 - Insert Formula Advanced Wizard.
 * <p>
 * 
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Requirement</h2>
 *
 * The wizard should now have an edit box where the formula is gradually 
 * constructed.
 * The user should be able to edit the formula text freely. 
 * The functions or operators (and the values of its parameters/operands) 
 * selected from the list should now be inserted in the position of the cursor 
 * in the new editbox. 
 * The wizard should continue to have an area to display the evaluation of the 
 * formula (that should be produced dynamically, as the user edits the formula). 
 * The wizard should also have a new window that should display the structure of 
 * the formula expression like an abstract syntax tree (i.e., the structure 
 * resulting from the formula compilation). 
 * When the user clicks a tree element its respective text in the edit box
should appear highlighted.

 *
 * <p>
 *
 * <h2>2. Analysis</h2>
 * <p>
 *
 * This use case is identical to the previous one used in sprint two of this
 * user case, with some differences in the User Interface as well in some new
 * Controller methods. For this, there will be a need of extending the previous 
 * Controller and User Interface classes and overriding some methods so they can 
 * handle the new functionalities.
 *
 * <h3>Identified Problems:</h3>
 * 1. Displaying an edit box that can be dynamically changed by the insertion of 
 * functions/operators in the cursor position.<p>
 * 2. Displaying the result of the execution of the formula or a message 
 * explaining the problem as the formula is edited in the edit box. <p>
 * 3. Displaying an abstract syntax tree as the formula is edited in the edit 
 * box. <p>
 * 4. Highlighting the respective text in the edit box of a tree element when 
 * it's clicked. <p>
 * 5. Making the parameter description box from the previous iteration non 
 * editable. <p>
 * 
 * <h3>Proposed solution</h3>
 * 1. Determine the cursor position with a Java Swing JTextField component 
 * method. 
 * Retrieving the syntax of the selected functions/operators with a previous 
 * iteration method
 * with a minor change that the function starter character "=" may not be 
 * present as the functions/operators
 * can now be displayed in the middle or at the end of the edit box.
 * <p>
 * 2. Implement a document listener to know when the text box is updated and 
 * refresh the result of the formula through a controller already implemented 
 * from the previous iteration that will return the formula's result from a 
 * given expression. This method uses the compile method from 
 * ExcelExpressionCompiler to evaluate the formula's result.
 * <p>
 * 3. When the document listener previously detailed is triggered, the 
 * controller will also be notified that there's a need to build an abstract 
 * syntax tree from the given expression. The controller will then get the tree 
 * generated from the ExcelExpressionCompiler and the user interface will 
 * retrieve it to display it.
 * <p>
 * 4. The generated abstract syntax tree will need a selection listener so that 
 * whenever a leaf node from the tree is clicked, its respective text in the 
 * edit box is highlighted. Since the tree will contain grammar rules elements 
 * that are not present in the edit box text, but only in the grammar defined, 
 * these elements will not be selectable. <p>
 * 
 * A few notes for this solution:
 * <ul>
 * <li>Only leaf nodes are selectable/clickable.</li>
 * <li>The repetition of elements in the formula should not be a problem in the 
 * highlighting of the correct text element.</li>
 * </ul>
 * <p>
 *
 * Note: The operators need a different syntax since they are from the type
 * "=(parameter1 identifier parameter2) and not like functions
 * =identifier(parameter1;parameter2). For this there's a separation of the
 * different syntaxes for each one into syntax types.
 * <p>
 * 
 * The analysis diagram for the proposed solution:<p>
 * 
 * <img src="ssd_analysis.png" alt="image">
 * <p>
 *
 * <h2>3. Tests Plan</h2>
 * 
 * Only unit tests were developed for this iteration. 
 * Following this, there will be a brief description of each unit test 
 * developed: <p>
 * 
 * <h3>Number of occurrences of a character in a string:</h3>
 * This test was developed because when the abstract syntax tree is built in a 
 * Java Swing JTree type, the elements from the tree need to be inserted in the 
 * right way in the tree.
 * <p>
 * 
 * <h3>Management of leaf nodes:</h3>
 * This test was developed because there might be repeated characters in a 
 * formula, and as so, there must be a way to guarantee that when the user 
 * selects the tree element, the correct text element is highlighted.
 * 
 * <h3>Highlight position:</h3>
 * This test was developed to ensure that the correct element from the edit 
 * box is highlighted, as well as the previous described test.
 * <p>
 * 
 * <h2>4. Design</h2>
 * 
 * <h3>4.1. UC Realization</h3>
 * Sequence Diagram
 *
 * <p>
 * The following diagram details the basic function wizard functionalities:
 * <p>
 * <img src="sd_function_wizard.png" alt="image">
 * <p>
 * The following diagram details the new functionalities for the current
 * iteration of this use case:
 * <p>
 * <img src="sd_advanced_function_wizard.png" alt="image">
 * <p>
 *
 * <h3>4.2. Classes</h3>
 *
 * <p>
 * The following diagram details the basic relationship between classes:
 * <p>
 * <img src="cd_function_wizard.png" alt="image">
 * <p>
 * The following diagram details the relationship between classes for the
 * current iteration of this use case:
 * <p>
 * <img src="cd_advanced_function_wizard.png" alt="image">
 * <p>
 * The following diagram details the hierarchy of classes and the relationship
 * between new classes and old classes for the present use case:
 * <p>
 * <img src="cd_hierarchy.png" alt="image">
 * <p>
 *
 * 
 * <h2>5. Implementation</h2>
 * <p>
 *
 * The following classes and interfaces implement this use case.<p>
 * Package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard: <p>
 * {@link lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.AbstractSyntaxTreeSelectionModel}
 * <p>
 * Package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.application:<p>
 * {@link lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.application.AdvancedFunctionWizardController}
 * <p>
 * Package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.domain:<p>
 * {@link lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.domain.AbstractSyntaxTree}
 * <p>
 * Package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.presentation:<p>
 * {@link lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.presentation.AdvancedFunctionWizardUI}
 * <p>
 *
 *
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard;
