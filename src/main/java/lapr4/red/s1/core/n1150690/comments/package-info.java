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
 * similar to a "tooltip". Comments should be persisted with the workbook.
 *
 * <h2>3. Analysis</h2>
 * <p>
 * This use case it's an extended version of the <b>Core02.1: Comments on
 * Cells</b> use case. In this
 * <a href="../../../../../comments/package-info.java">page</a> you can find all
 * the necessary documentation about the Core02.1 use case.
 * <p>
 * For this functionality in specific, we only need to added to the previous use
 * case more features like: the possibility to insert several comments in a
 * cell, associate them to the author and persist the comments with the
 * workbook. As the workbook when it is generated, contains spreadsheets and
 * spreadsheets containing cells, it is only necessary that they keep a list of
 * comments to be able to be persisted in the workbook.. To identify the user in
 * the comment, the software should retrieve automaticly the system's username
 * by using this function: <code>System.getProperty("user.name")</code>.
 * <p>
 * Regarding the specification to display the comments in the form of a
 * "tooltip", if they exist, it is a feature that concerns only the user
 * interface.
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
 * From requirements and also analysis, we see that the core functionality of
 * this use case is to be able to associate the system's username to the comment
 * and allow a cell to have several comments. A new class is going to be created
 * with the name "CommentableCellWithMulipleUsers". This class will extend the
 * CommentableCell, since this has the basic functions releted with the
 * comments.
 * <p>
 * see:
 * <code>lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsersTest</code>
 * <p>
 * We will need to add a class named <code>User</code> to save the system's
 * username, in order to associate the author to the comment. Following this, we
 * will not need to create a test class for ensure the name isn't null because
 * the function presented in analysis section will always return a string.
 * <p>
 *
 * <h3>4.2. UC Realization</h3>
 * <p>
 * <b>Note: As the diagrams of the use case Core02.1 they were already created,
 * the following diagrams are an adpatation of them.</b>
 * <p>
 * <h3>User Selects a Cell</h3>
 * The following diagram illustrates what happens when the user selects a cell.
 * The idea is that when this happens the extension must display in the sidebar
 * the comments of that cell (if they exists).
 * <p>
 * <img src="core02_02_design1.png" alt="image">
 *
 * <h3>User Updates the Comment of a Cell or Adds a New One</h3>
 * The following diagram illustrates what happens when the user updates the text
 * of the comment of the current cell or adds a new comment. To be noticed that
 * this diagram does not depict the actual selection of a cell (that is
 * illustrated in the previous diagram).
 * <p>
 * <img src="core02_02_design2.png" alt="image">
 *
 * <h3>4.3. Classes</h3>
 * <p>
 * <img src="core02_02_design_class_diagram.png" alt="image">
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * <p>
 *
 * <h2>5. Implementation</h2>
 * <p>
 * We were told to try if possible to extend the existing classes to add
 * features.
 * <p>
 * So I created a class called CommentableCellWithMultipleUsers that extends the
 * CommentableCell class, which will have the necessary methods and attributes
 * for this use case, related to the business domain.
 * <p>
 * From my point of view, it would not make sense to extend the controller or
 * the user interface of the previous use case since you would not need to use
 * any method.
 * <p>
 * In order to realize the functionality of the tooltip in cells, I had to add
 * lines of code in the CellRenderer class, add two methods to the CellDecorator
 * class, and create a class that extended the CommentedCellDecorator class and
 * override the methods added to the CellDecorator class to be able to run the
 * use case.
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
 * Today: I will start the analysis process.
 * <p>
 * Blocking:---
 * <p>
 * <b>Wednesday 31/05/2017</b>
 * <p>
 * Yesterday: I finished the analysis process.
 * <p>
 * Today: I will start the design process and want, if possible, starts the
 * implementation of the unit tests.
 * <p>
 * Blocking:---
 * <p>
 * <b>Thursday 1/06/2017 </b>
 * <p>
 * Yesterday: I finished the design and the unit tests.
 * <p>
 * Today: I will start the implementation process.
 * <p>
 * Blocking:---
 * <p>
 * <b>Friday 2/06/2017</b>
 * <p>
 * Yesterday: I created the controller and ui classes
 * <p>
 * Today: I will finished, if possible the use case.
 * <p>
 * Blocking:--
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
