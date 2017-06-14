/**
 * Technical documentation regarding the user story <b>LANG02.3: Arrays and
 * Variable Editor</b>.
 *
 * <h1>Requirements</h1>
 *
 * <p>
 * Add support for array variables (temporary and global). Any variable can be
 * an array. When accessing a variable only by its name it should be inferred
 * the position 1 of the array. To explicitly access a position in a array
 * variable the position index should be specified inside brackets (following
 * the name of the variable). For example, the formula "=@abc[2]:=123" will set
 * the position 2 of the global variable @abc to the value 123. Each position of
 * an array can be of a different type. For instance it should be possible to
 * have an array with numeric and alphanumeric values.
 * </p>
 * <p>
 * There should also be a window in the sidebar to display and edit the value of
 * global variables. The values that appear in this window should be
 * automatically updated when the variables are updated.
 * </p>
 *
 * <h2>Questions Discussed With Client</h2>
 *
 * <p>
 * <b>Should the array be 0-indexed or 1-indexed?</b> The array should be
 * 1-indexed, i.e. the first element in the array is at index 1.
 * </p>
 * <p>
 * <b>How should the array be created?</b> No formality is needed. All variables
 * are by default an array, i.e. a simple reference to the index is suffice to
 * use it.
 * </p>
 * <p>
 * <b>May the array have gaps in its indexes?</b> Yes. It is possible to assign
 * values to any positive index. If there is a value request to a
 * non-initialized index, it should be treated as a reference to an empty cell.
 * </p>
 *
 * <h1>Analysis</h1>
 *
 * <h2>What is old?</h2>
 * <ul>
 * <li>Support of temporary and global variables
 * ({@link lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable}).</li>
 * <li>The variables contain a name and an expression
 * ({@link lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable#name} and
 * {@link lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable#expression})</li>
 * <li>The assignment operations must be done within curly brackets.</li>
 * <li>The .</li>
 * <li>The compiler uses the BlueFormula grammar.</li>
 * <li>Global variables are held in a special container
 * ({@link lapr4.red.s2.lang.n1150623.globalVariables.VarContentor}).</li>
 * <li>Global variables container is held by the workbook and persisted along
 * with it ({@link csheets.core.Workbook#globalVariables}).</li>
 * </ul>
 *
 * <h2>What needs fixing?</h2>
 * <ul>
 * <li>The concurrent access to the global variables is not regarded.</li>
 * <li>The global variables does not contain the precedents and dependents to
 * update them.</li>
 * </ul>
 *
 * <h2>What is new?</h2>
 * <ul>
 * <li>Both temporary and global variables are arrays.</li>
 * <li>An array may have different value types in each of its positions.</li>
 * <li>Global variables are shown and can be edited in a side bar.</li>
 * <li>The global variables values within the spreadsheet and the side bar must
 * be synchronized.</li>
 * </ul>
 *
 * <h2>What can be improved?</h2>
 * <ul>
 * <li>An option to delete global variable indexes.</li>
 * <li>The persistence system of the global variables.</li>
 * <li>The concept of global variable.</li>
 * <li>The global variable container system.</li>
 * </ul>
 *
 * <h2>Concepts</h2>
 * <p>
 * <b>Temporary Variable</b>: These are variables whose name starts with the "_"
 * character and its scope is limited to the cell in which they are declared and
 * used.
 * </p>
 * <p>
 * <b>Global Variable</b>: These are variables whose name starts with the "@"
 * character and its scope is related to the workbook. They should be persisted
 * along with the workbook. The first reference to the global variable will
 * create it.
 * </p>
 * <p>
 * <b>Array</b>: The array is a group of expressions which share the same name
 * reference but have a unique index reference. Despite the traditional notion
 * of an array as a sequential container, these arrays have no guarantee that
 * the used indexes have no gaps between them. Also, these can be considered
 * dynamic arrays, since its size is undetermined and may change through use.
 * They are accessed by using the variable name followed by square brackets
 * around the index number. If the index is not explicitly mentioned, it will be
 * used the value at index 1. For instance, @myVar[1] is equivalent to @myVar.
 * </p>
 *
 * <h2>Identified issues</h2>
 *
 * <p>
 * <b>Array Creation</b>: All variables might be an array and no formal
 * declaration is needed to indicate that. The variables can be used as an array
 * by simply using the index reference. With this in mind, it can be said that
 * all variables are always arrays, even if only the first element of it is
 * used. Also, they have no predetermined size. The memory usage and time
 * complexity to manage these arrays must be carefully examined.
 * </p>
 * <p>
 * <b>Array Indexing</b>: The indexing reference is made by using the numeric
 * index between square brackets. Despite computer science being fond of using
 * 0-indexed arrays, the client indicates that the first element within the
 * array is at index number one. Also, an array is usually associated with a
 * straight sequence of values but the client allows for gaping in the existing
 * indexes. Only positive indexes are allowed.
 * </p>
 * <p>
 * <b>Multi-Types Array</b>: The array is usually associated with a type which
 * is shared among all its elements. In this case, this is not true. The client
 * requires that different indexes may hold different value types.
 * </p>
 * <p>
 * <b>Side Bar Updates</b>: The side bar must show all global variables values
 * and allow the user to edit them. Every time a value is updated at the side
 * bar, all the cells using that same global variable's value must be updated.
 * The contrary is also true, every time a global variable's value is updated at
 * the spreadsheet, the side bar value must be updated. This raises concurrency
 * issues examined in the next topic.
 * </p>
 * <p>
 * <b>Global Variables Concurrency</b>: The global variables can be edited in
 * many different cells, plus the side bar. The idea to reevaluate all the other
 * cells where the global variable is being used may lead to unexpected results.
 * For instance, if an existing cell made an assignment, whenever the global
 * variable is edited it would update the first cell in which there is an
 * assignment and it would reassign the old value. It could even lead to
 * infinite loops. Lets consider the cell A1 "=(@myVar:=3)", B1 "=@myVar+2". At
 * this point A1 will show "3" and B1 will show "5". Now lets add C1
 * "=(@myVar:=6)". If it would be the case to update all other cells in which it
 * is being used, the B1 would show "8" (it would work just fine), however on
 * updating A1 it would reassign @myVar to "3". Since there is a new change to
 * the global variable, a new update would be launched causing an infinite loop.
 * </p>
 * <p>
 * <b>Precedents and Dependents</b>: Even if the assignment issue could be
 * solved (for instance, ignore cells in which an assignment occurs) the
 * existing implementation does not take into consideration the precedents &#47;
 * dependents in order to allow the listeners to work. This was a responsibility
 * of the previous feature increment. In order to fix this, a completely new
 * implementation of the global variables would be needed, rather then simply
 * consider them the same as the temporary variable and keep the name and the
 * expression. At first thought, the global variable should follow a similar
 * implementation of the Cell. Regardless, the sprint time is too short to
 * perform both feature increments and Prof. JRT asked to perform the third
 * feature increment and explain this issue which jeopardises part of this
 * feature increment.
 * </p>
 * <p>
 * <b>Delete Variable Index</b>: This feature is not required despite it makes
 * sense. It could simply delete any variable index whose content would be set
 * to an empty String. However, this could only be perform at the side bar and
 * concurrency/listeners problems would arise (as seen before). This will not be
 * implemented.
 * </p>
 *
 * @author Manuel Meireles (1150532)
 */
package lapr4.green.s3.lang.n1150532.variables;
