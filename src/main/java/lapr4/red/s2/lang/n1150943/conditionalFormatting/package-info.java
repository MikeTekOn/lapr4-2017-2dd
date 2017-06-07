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
 * In this use case I will extend the functionality to add conditional formatting to cells. Currently it is only possible to add confitional formatting to each cell individually.
 * <p>
 * The extension will add the possibility to apply conditional formatting to multiple cells at the same time. Another improve is that the conditions may be relative to the cell itself with the use of a variable called "_cell"
 *
 * <h2>3. Analysis</h2>
 * The previous and existing implementation of conditional formatting has a problem that needs to be solved, a "UserStyle" that is a style for a certain condition needs to be associated to the cell that has the condition.
 * In the current implementation this "style" is associated to all conditions, so any true condition will have the same style and so will do all false conditions.
 * I think that moving the style to each Cell will solve the problem.
 * <p>
 * After this problem is solved, I'll need to do some modifications on the functional UI, it must detect if there is one or more than one selected cells, and the panel should change accordingly.
 * If there is just one selected cell, it will show the current UI, if there are more it will show a new panel that will be created.
 * <p>
 * The final step will be to implement the feature to add the possibility to use a variable called "_cell" that will add the possibility for the user be able to create conditions for multiple cells, but that are affected by each of them individually.
 *
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
 * <h3>3.3 Use Case Scenarios</h3>
 * Normal Behaviour Scenario 1 - The user selects cell 1, adds the conditon A to that cell with the "blue background style" when the condition is true, selects cell 2 and adds condition B to that cell with the style "green background" when the condition is true.
 * If conditions A and B are true, cell 1 should be blue, and cell 2 green.
 *
 * Normal Behaviour Scenario 2 - The user selects cells from A1 to A8, adds the condition "=B3=2" to that range of cells with the style that changes the font color to white.
 * User changes the value of cell B3 to 2, all the cells from A1 to A8 should have their text color changed to white.
 *
 * Normal Behaviour Scenario 3 - The user selects again cells A1 to A8, adds the condition "=_cell>=10" true to set background green and false to set background red.
 * Cells values are: A1 - 5; A2 - 15; A3 - 10; A4 - 9; A5 - 1; A6 - 20; A7 - 17; A8 - 13.
 * Cells A1, A4 and A5 should have red background, A2, A3, A6, A7 and A8 should have green background.
 *
 * <b>UC3.2</b>
 *
 * Alternative and Exception Scenarios<br>
 * Exception 1: -
 *
 * <p>
 *
 *
 *
 * <h3>3.4 Acceptance Tests</h3>
 *
 * <b>Exception 1</b><br>
 * <pre>
 * {@code

 * }
 *
 *  </pre>
 * <b>Exception 2</b><br>
 * <pre>
 * {@code
 *
 * }
 * </pre>
 *
 * <h2>4. Design</h2>
 *
 * <b>Sequence Diagrams</b><p>
 * <p>
 * <img src="" alt="image">
 * <p>
 *
 * <h3></h3>
 * <p>
 * <img src="" alt="image">
 * <p>
 *
 * <h3></h3>
 * <p>
 * <img src="" alt="image">
 * <p>
 * <h4></h4>
 * <p>
 * <img src="" alt="image">
 * <p>
 * <b>Class Diagram</b>
 * <p>
 * <img src="" alt="image">
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
 * <h2>6. Self Assessment</h2>
 /**
 * Created by João Cardoso - 1150943 on 06-06-2017.
 */
package lapr4.red.s2.lang.n1150943.conditionalFormatting;
