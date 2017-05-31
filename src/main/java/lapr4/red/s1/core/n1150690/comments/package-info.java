/**
 * Technical documentation regarding the user story Core02.2: Tooltip and User Associated to Comment.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 * <p>
 *
 * <h2>2. Requirement</h2>
 * Cleansheets should register the name of the user that creates comments and
 * each cell should support several comments. When the mouse pointer is hovering
 * above a cell and the cell has comments then these comments should be
 * displayed in a form similar to a "tooltip". The name of the author of each
 * comment should also appear in all displays of comments. Comments should be
 * persisted with the workbook.
 *
 * <p>
 * <b>Use Case "Tooltip and User Associated to Comment":</b> The user selects
 * the cell where he/she wants to enter a comment. The system displays the
 * current comment of that cell. The user enter the text of the comment (or
 * alters the existing one) or adds a new comment. The system saves the comment
 * of the cell. When the user's mouse pointer is hovering above a cell and the
 * cell has comments then the system will display these comments in a form
 * similar to a "tooltip".
 *
 * <h2>3. Analysis</h2>
 * <p>
 * This use case it's an extended version of the <b>Core02.1: Comments on
 * Cells</b> use case. In this
 * <a href="../../../../../comments/package-info.java"/>page</a> you can find
 * all the necessary documentation about the Core02.1 use case.
 * <p>
 * For this functionality in specific, we only need to added to the previous use
 * case more features like: the possibility to insert several comments in a
 * cell, associate them to the author and persist the comments with the
 * workbook. To identify the user in the comment, the software should retrieve
 * automaticly the system's username.
 * <p>
 * <h3>Domain Concepts Description</h3>
 * <p>
 * <b>Comment</b> - a frase that the user can add to the select cell.
 * <p>
 * <b>User</b> - the author of the comments.
 * <p>
 * <b>Worksheet</b> - a matrix of cells that can contain numeric or text data or
 * formulas.
 * <p>
 * <b>Workbook</b> - a collection of worksheets.
 *
 * <h3>3.1. Domain Model</h3>
 * <p>
 * Representation of the parcialy domain model, it only represents the
 * connections of the classes presented in this use case.
 * <img src="core02_02_analysis_domain_model.png" alt="image">
 * <p>
 * <h2>4. Design</h2>
 * <p>
 *
 * <h3>4.1. Functional Tests</h3>
 * <p>
 *
 * <h3>4.2. UC Realization</h3>
 * <p>
 *
 * <h3>4.3. Classes</h3>
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
 * Today: I start the analysis process.
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
 * @author Sofia Silva 1150690@isep.ipp.pt
 */
package lapr4.red.s1.core.n1150690.comments;
