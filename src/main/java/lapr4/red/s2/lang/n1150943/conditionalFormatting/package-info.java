/**
 * Technical documentation regarding the user story LANG03.2 - Conditional Formatting of Ranges
 * <p>
 *
 * <h2>1. Notes</h2>
 *
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 *
 * <p>
 *
 * <h2>2. Requirement</h2>
 *
 * In this use case I will extend the functionality to add conditional formatting to cells. Currently it is only possible to add conditional formatting to each cell individually.
 * <p>
 * The extension will add the possibility to apply conditional formatting to multiple cells at the same time. Another improve is that the conditions may be relative to the cell itself with the use of a variable called "!CELL"
 *
 * <h2>3. Analysis</h2>
 * The previous and existing implementation of conditional formatting has a problem that needs to be solved, a "UserStyle" that is a style for a certain condition needs to be associated to the cell that has the condition.
 * <p>
 * In the current implementation this "style" is associated to all conditions, so any true condition will have the same style and so will do all false conditions.
 * <p>
 * I think that moving the style to each Cell will solve the problem.
 * <p>
 * The final step will be to implement the feature to add the possibility to use a variable called "#CELL" that will add the possibility for the user be able to create conditions for multiple cells, but that are affected by each of them individually.
 * <p>
 *  To implement the usage of the variable "!CELL" to invoke each cell in the conditions it is needed to add it to the already implemented grammar and in the class FormulaEvalVisitor verify if it appears in the condition. In this case it is converted to the address of the cell being visited at the moment.
 * <p>
 * As this use case will be an extension to what was already done and because I will have to make several changes in the already implemented code, most part of my work won't be on my package.
 * <p>
 * Although I will always put the tag "Edited/Created/Implemented by João Cardoso - 1150943" in the classes/methods I work so it is easier to find my changes in the code.
 * <h3>3.1 User Case Tasks</h3>
 *
 * <b>1.</b>As a User I want to be able to add different conditional formatting styles to different cells (Correction of the previous UC)<br>
 *     Story Acceptance Criteria 1: One conditional formatting in one cell shouldn't affect any other cell<br>
 * <b>2.</b> As a User I want to select a range of cells and add the same conditional formatting to each of them<br>
 * <b>3.</b> As a User I want to add a conditional formatting to a cell or to a range of cells where the condition is associated with each cell itself (For Example: Add the condition _cell>0 to the cells A1 and A2, if the cell A1 has the value 1 and A2 has the value -1, the cells should have different styles because the _cell values are different, one is bigger that zero and the other is not).<br>
 *
 * <h3>3.2 Domain Model</h3>
 * <p>
 * <img src="" alt="image">
 *
 * <h3>3.3 Use Case Scenarios / Functional Tests</h3>
 * <p>
 * Normal Behaviour Scenario 1 - The user selects cell 1, adds the conditon A to that cell with the "blue background style" when the condition is true, selects cell 2 and adds condition B to that cell with the style "green background" when the condition is true.
 * If conditions A and B are true, cell 1 should be blue, and cell 2 green.
 * <p>
 * Normal Behaviour Scenario 2 - The user selects cells from A1 to A8, adds the condition "=B3=2" to that range of cells with the style that changes the font color to white.
 * User changes the value of cell B3 to 2, all the cells from A1 to A8 should have their text color changed to white.
 * <p>
 * Normal Behaviour Scenario 3 - The user selects again cells A1 to A8, adds the condition "=_cell>=10" true to set background green and false to set background red.
 * Cells values are: A1 - 5; A2 - 15; A3 - 10; A4 - 9; A5 - 1; A6 - 20; A7 - 17; A8 - 13.
 * Cells A1, A4 and A5 should have red background, A2, A3, A6, A7 and A8 should have green background.
 *
 * <b>UC3.2</b>
 *
 * Alternative and Exception Scenarios<br>
 * Exception 1: - The user selects an invalid condition
 * The system shows an alert and doesn't save the condition in the cell
 * <p>
 * <h3>3.4 Acceptance Tests</h3>
 *
 * <b>Exception 1</b><br>
 * <pre>
 * {@code
    The user selects the condition =aa>32
 *  The system shows the warning alert and sets the selected cells' conditions to null.
 * }
 * <h2>4. Design</h2>
 *
 * <b>Sequence Diagrams</b><p>
 * <p>
 *     <h3>Short Sequence Diagram</h3>
 * <img src="ssd.png" alt="image">
 * <p>
 *
 * <h3>Detailed Sequence Diagram</h3>
 * <p>
 * <img src="design.png" alt="image">
 * <p>
 * <b>Class Diagram</b>
 * <p>
 * <img src="class_diag.png" alt="image">
 *
 * <h2>5. Work Log</h2>
 *
 * <p>
 * <b>Day 1 - 06/06/2017</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Sprint 1 Demonstration, Sprint 1 Retrospective and Sprint 2 preparation
 * <p>
 * Today
 * <p>
 * 1. Work on UC pre-analysis
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 *
 * <b>Day 2 - 07/06/2017</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Use case analysis and understanding the code that was already implemented in sprint 1
 * <p>
 * Today
 * <p>
 * 1. Work on UC pre-design and correct previous implementation errors
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <h2>6. Self Assessment</h2>
 * <p>
 *
 * <b>Day 3 - 08/06/2017</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Use case analysis, design and implementation
 * <p>
 * Today
 * <p>
 * 1. Complete design and implementation and check if there are any necessary unit tests to implement
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <h2>6. Self Assessment</h2>
 *
 * <b>Day 4 - 09/06/2017</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Use case implementation and corrected documentation
 * <p>
 * Today
 * <p>
 * 1. Finish use case implementation, tried to find possible unit tests but all I found was already implemented in sprint 1
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <h2>6. Self Assessment</h2>*
 /**
 * Created by Joao Cardoso - 1150943 on 06-06-2017.
 */
package lapr4.red.s2.lang.n1150943.conditionalFormatting;
