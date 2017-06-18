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
 *
 * <h3>3.1 - The navigation window content</h3>
 * As stated in the requirements, the navigation window should have a tree-like
 * vision of the CleanSheets app contents. We will have a root element which
 * could be a simple "Workbooks" string. When expanded, we will see the list of
 * active workbooks represented by their name (the File name). When a Workbook
 * is expanded, a list of its Spreadsheets will be visible. For every
 * Spreadsheet, we will have the non empty cells: the cells that have some kind
 * of content on it, formula or value. When expanding a cell, the formula and/or
 * value of the cell will be visible and that will be the last node of the tree.
 *
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
 * <h3>3.2 - What can we use to implement a tree-like navigation window?</h3>
 * After studying the project, we found some possible similarities with how the
 * dependencies are implemented and what we must develop. Like the navigation
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
 * <h3>3.3 - JTree Java class</h3>
 * Java has a Jtree class that allows the display of hierarchical data. From the
 * documentation, we see that a JTree object does not actually contain data, it
 * simply provides a view of that data. Like any non-trivial Swing component,
 * the tree gets data by querying its data model.
 *
 * We can see in more detail how a JTree should be used here:
 * <a href="https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html">Java
 * Documentation - How to Use Trees</a>
 *
 * <h3>3.4 - Modeling the Data</h3>
 * Before using the JTree classes to create the visual part of the navigation
 * tree, we need to model the data that will be shown. Considering that, we will
 * need to create multiple Nodes. For instance, the dependencies tree uses only
 * one type of node (CellNode) because the tree has cells only. In this case, we
 * need to have different types of nodes. The first approach is to create a
 * generic Node:
 * <p>
 * "NavigationNode" - this will be an abstract class and will have a generic
 * Object as attribute, the TreeModel used, which will possibly be the
 * DefaultTreeModel and the methods to get the object and populate the tree. It
 * will also have the abstract method addChildren() that will be implemented on
 * the classes that extend NavigationNode.
 * <p>
 * Then we can start designing the model based on that node. Multiple types of
 * nodes will be needed:
 * <ul>
 * <li>RootNode - extends the NavigationNode and is the top of the tree. Has a
 * UIController so it has access to the active workbooks and is able to create a
 * WorkbookNode for every active workbook, using the addChildren().</li>
 * <li>WorkbookNode - extends the NavigationNode and has a Workbook. It knows
 * the list of spreadsheets of that workbook and is able to create multiple
 * SpreadsheetNodes based on that.</li>
 * <li>SpreadsheetNode - extends the NavigationNode and has access to all the
 * cells of a spreadsheet. It is possible to check which cells have content and
 * create FilledCellNodes of each one of those non empty cells.</li>
 * <li>FilledCellNode - extends the NavigationNode and has a cell. It can create
 * a FormulaNode and a ValueNode depending on the contents of the cell.</li>
 * <li>FormulaNode - extends NavigationNode and is a final leaf of the
 * tree.</li>
 * <li>ValueNode - extends NavigationNode and is a final leaf of the tree.</li>
 * </ul>
 *
 * <h3>3.5 - Connecting the Data and the Tree visual representation</h3>
 * To connect the data and the visual representation of the navigation tree we
 * need a JTree class:
 * <p>
 * NavigationTree - it will extend JTree and create a tree representation with
 * the default tree model. It will configure the tree based on its listeners and
 * renderers. It will also create a RootNode and populate it, which will result
 * in the creation of all the nodes in the tree.
 *
 * <h3>3.6 - Updating the tree after changes in the content</h3>
 * The first approach in order to implement an update to the tree everytime its
 * contents change (adding values to cells, new workbooks or spreadsheets) will
 * be adding a cell listener to the NavigationTree class. This way we can
 * implement the selectionListener() method to recreate the tree everytime the
 * focus on a cell changes. By doing this, everytime a cell changes, we can
 * "update" the contents of the tree because it will be created again with the
 * updated information. However, this might not be the best solution and will
 * bring some problems that will need to solved: the tree might collapse
 * everytime we change the focus of a cell and we will be recreating the tree
 * too many times without it being needed.
 *
 * <h2>4. Design</h2>
 *
 * After the initial analysis and research, we are able to create a simple class
 * diagram to ilustrate the problem's solution.
 *
 * <h3>4.1 - Class Diagram:</h3>
 * <img src="core04_1_dm.png" alt="image">
 *
 * We can also document in a sequence diagram how the navigation tree will be
 * created and all the interactions between the nodes as the tree is generated.
 *
 * <h3>4.2 - Sequence Diagram of the tree creation:</h3>
 * <img src="core04_1_design1.png" alt="image">
 *
 * <h2>5. Tests</h2>
 * <ol>
 * <li>ensureWorkbookNodeHasWorkbook
 * <li>ensureSpreadsheetListIsInCorrectOrder
 * <li>ensureActiveWorkbookListIsInCorrectOrder
 * <li>ensureCellListIsInCorrectOrder
 * </ol>
 *
 * <h3>5.1. Functional Tests</h3>
 * <ol>
 * <li>Open the navigation window and check its content.</li>
 * <li>Create a new Workbook and check if the navigation window is updated.</li>
 * <li>Save the Workbook and check if the name is updated</li>
 * <li>Fill a cell with a value and a formula and check if the navigation window
 * was updated.</li>
 * <li>Double click on a element and check if the focus changed to that
 * element.</li>
 * </ol>
 *
 * <h2>6. Implementation Details</h2>
 *
 * After the analysis and the design process, we still faced some expected
 * problems during the implementation phase. Those will be explained in more
 * detail next.
 *
 * <h3>6.1 - Accessing an element by double clicking on it</h3>
 * One of the requirements was being able to focus on a specific element by
 * double clicking on it in the navigation window. To implement that
 * functionality, a new class was created:
 * <p>
 * CustomDoubleClickNavigator - this class extends MouseAdapter and implements a
 * mouse event everytime an element is clicked more than once. Then it gets the
 * path and the corresponding node of that path. After having the node, we are
 * able to check of which instance class that node is and with that information
 * we are able to call the respective UIController setActive() method:
 * "setActiveWorkbook()", "setActiveSpreadsheet()" or "setActiveCell()".
 *
 * <h3>6.2 - Saving the navigation tree state</h3>
 * As expected during the analysis and design research, in order to keep the
 * navigation tree always updated, we decided to recreate the tree everytime the
 * method selectionChanged() is called. This will ensure a new tree is built
 * everytime a new value or formula is inserted, a new Workbook or Spreadsheet
 * are created or even when a Workbook is saved with a different name, etc.
 * <p>
 * However, by doing this, when the tree was created, it always had the default
 * collapsed state. To keep the state of the tree and the expanded nodes, it was
 * decided to create a new class:
 * <p>
 * TreeState - this class uses the Singleton Pattern and its purpose is to save
 * the tree state and set that state when a new tree is created. After reading
 * the documentation of the JTree class we knew that every node has a path. As
 * the tree expands, that path is changed. By saving that path for every row of
 * the tree, we are able to save the state of the tree.
 * <p>
 * By adding the singleton instance to the NavigationTree, then we only need to
 * call the getExpansionState() method before the tree creation and the
 * setExpansionState() right after the new tree is created.
 *
 * <h3>6.3 - Maintaining the navigation tree nodes in the correct order</h3>
 * Like explained before, since the tree is generated multiple times to ensure
 * its content is always updated, it was important to choose the right
 * collections to keep the information ordered. By using a non sorted structure,
 * each time the tree was created, the nodes could be placed in any order,
 * making the functionality less user-friendly. To solve that problem, a TreeSet
 * structure was used. This way, the lists of workbooks, spreadsheets and cells
 * are always ordered by implementing a custom comparator when the TreeSet is
 * created.
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
 * <li>Nothing.</li>
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
 * <li>Nothing.</li>
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
 * <li>Nothing.</li>
 * </ul>
 * <p>
 * <b>Friday</b>
 * <p>
 * Yesterday I worked on:
 * <ul>
 * <li>1. Finished use case analysis.</li>
 * <li>2. Finished defining tests and the design process.</li>
 * </ul>
 * <p>
 * Today
 * <ul>
 * <li>1. Start use case implementation.</li>
 * </ul>
 * <p>
 * Blocking:
 * <ul>
 * <li>Nothing.</li>
 * </ul>
 * <p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on:
 * <ul>
 * <li>1. Started use case implementation.</li>
 * </ul>
 * <p>
 * Today
 * <ul>
 * <li>1. Finished use case implementation.</li>
 * </ul>
 * <p>
 * Blocking:
 * <ul>
 * <li>Nothing</li>
 * </ul>
 * <p>
 * <b>Sunday</b>
 * <p>
 * Yesterday I worked on:
 * <ul>
 * <li>1. Finished the use case implementation.</li>
 * </ul>
 * <p>
 * Today
 * <ul>
 * <li>Updated documentation and review use case details.</li>
 * </ul>
 * <p>
 * Blocking:
 * <ul>
 * <li>Nothing.</li>
 * </ul>
 * <p>
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
package lapr4.blue.s3.core.n1151031.navigation;
