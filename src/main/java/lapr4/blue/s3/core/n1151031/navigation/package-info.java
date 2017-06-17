/**
 * Technical documentation regarding the user story core04.1: Navigation Window
 * <p>
 * <b>Scrum Master: - no</b>
 * <p>
 * <b>Area Leader: - no</b>
 *
 * <h2>1. Notes</h2>
 *
 *
 * <h2>2. Requirement</h2>
 * Cleansheets should have a navigation window (sidebar). The navigation should
 * display the following contents: workbooks; spreadsheets; non-empty cells;
 * formulas; values. A double click in one of the elements should update the
 * mouse focus to show that element. The contents of the navigator should be
 * automatically updated.
 *
 * <p>
 * <b>Use Case "Navigation Window":</b>
 * Use case general flow: the user chooses an element of the navigator. The
 * system expands the navigator to show the content of the previous element. The
 * user double clicks an element. The system focus on the selected element.
 * <p>
 *
 * <img src="core04_1_ssd.png" alt="image">
 *
 * <h2>3. Analysis</h2>
 *
 * To implement this use case, we have to study the requirements in more detail.
 * It is stated that a navigation window should be implemented, so we have to
 * develop a new extension for Cleansheets. That extension will implement a new
 * sidebar and that sidebar will have the tree-like vision of the contents of
 * Cleansheets.
 * <p>
 *
 * After discussing with the client, we are able to draw a simple model that
 * represents the navigation tree that we will implement:
 *
 * <h3>Navigator example model:</h3>
 * <pre>
 * Workbooks
 *     |    workbook1
 *     |    workbook2
 *     |    workbook3
 *     |        |    spreadsheet1
 *     |        |    spreadsheet2
 *     |        |         |      A4
 *     |        |         |      B5
 *     |        |         |      D4
 *     |        |         |       |    Formula
 *     |        |         |       |       |    =A4+10
 *     |        |         |       |     Value
 *     |        |         |       |       |    15
 *     |        |         |      E8
 *     |        |    spreadsheet3
 *     |        |    spreadsheet4
 *     |    workbook4
 *     |
 * </pre>
 *
 * <h3>What can we use to implement a tree-like navigation window?</h3>
 * After studying the project, we found some possible similarities with how the
 * dependencies are implemented and what we mush develop. Like the navigation
 * window, the dependencies between cells are also implemented in a tree-like
 * navigation window. The selected cell is a node and when clicked, it displays
 * all the other cells that are dependent of it. Also, when double clicked, the
 * focus goes to that cell.
 * <p>
 *
 * In this use case, we will not work only with cells but also with workbooks,
 * spreadsheets, values and formulas, but we can get a general idea by learning
 * how the dependencies were implemented.
 *
 * <h3>JTree</h3>
 * Java has a Jtree class that allows the display of hierarchical data. From the
 * documentation, we see that a JTree object does not actually contain data, it
 * simply provides a view of that data. Like any non-trivial Swing component,
 * the tree gets data by querying its data model.
 *
 * We can see in more detail how a JTree should be used here:
 * <a href="https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html"></a>
 *
 * <h2>4. Design</h2>
 *
 * <img src="core04_1_dm.png" alt="image">
 * 
 * <img src="core04_1_design1.png" alt="image">
 *
 *
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <ul>
 * <li>1. Client presentation of the previous sprint</li>
 * </ul>
 * <p>
 * Today
 * <ul>
 * <li>1. The team will finish the client presentations.</li>
 * <li>2. Select the next use case to work on.</li>
 * </ul>
 * <p>
 * Blocking:
 * <ul>
 * <li>1. Nothing.</li>
 * </ul>
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <ul>
 * <li>1. The team finished the client presentation.</li>
 * <li>2. Selected the next use case to work on.</li>
 * </ul>
 * Today
 * <ul>
 * <li>1. Start use case analysis.</li>
 * </ul>
 * <p>
 * Blocking:
 * <ul>
 * <li>1. Nothing.</li>
 * </ul>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <ul>
 * <li>1. Started use case analysis.</li>
 * </ul>
 * <p>
 * Today
 * <ul>
 * <li>1. Finish use case analysis.</li>
 * <li>2. Start defining tests and design process.</li>
 * </ul>
 * <p>
 * Blocking:
 * <ul>
 * <li>1. Nothing.</li>
 * </ul>
 * <p>
 * <b>Friday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * ---
 * <p>
 * Today
 * <p>
 * ---
 * <p>
 * Blocking:
 * <p>
 * 1. ---
 * <p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * ---
 * <p>
 * Today
 * <p>
 * ---
 * <p>
 * Blocking:
 * <p>
 * 1. ---
 * <p>
 * <b>Sunday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. ---
 * <p>
 * Today
 * <p>
 * 1. ---
 * <p>
 * Blocking:
 * <p>
 * 1. ---
 * <p>
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
package lapr4.blue.s3.core.n1151031.navigation;
