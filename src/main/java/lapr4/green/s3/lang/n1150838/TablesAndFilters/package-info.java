/**
 * Technical documentation regarding the user story <b>LANG03.3 Tables and
 * Filters</b>.
 *
 * <h1>Requirements</h1>
 *
 * <p>
 * Add a new extension to support the concept of 'tables'. A table is
 * essentially a range of cells. The first row of this range of cells can be
 * used as header of the table columns (the contents of these cells become the
 * name of the columns). Once a table is defined it should be possible to filter
 * its contents by using formulas. A formula that is used as a filter of a table
 * is applied to each row of the table. If the result is true, the row is
 * visible, if the result is false, the row should become invisible. To
 * facilitate the writing of such formulas a new special variable should be
 * added to formulas. This new variable should be an array variable that
 * represents the value of the columns of the table for the current row. Lets
 * consider, for instance, that the new variable is called '_col'. For example,
 * it should be possible to use '_col[2]' to get the value of column 2 for the
 * current row. It should also be possible to use the name of the column instead
 * of the index. For instance, if the header of column 2 is 'cidade' it should
 * be possible the get the value of this column for the current row by using
 * '_col[“cidade“]'. An example of a filter for a table could be:
 * '=or(_col[“idade“]>10; _col[3]<123)'.
 *
 * </p> <p>
 * This extension should add a new sidebar window that should be used to edit
 * tables and its filters.
 * </p>
 *
 * <h2>Questions Discussed With Client</h2>
 *
 * <p>
 * <b>Should the array be 0-indexed or 1-indexed?</b> The array should be
 * 1-indexed, i.e. the first element in the array is at index 1.
 * </p>
 * <p>
 * <b>Should the table header have any special format (default) after it has
 * been defined?</b> No automatic formatting is required for the table header
 * </p>
 * <p>
 *
 * <h1>Analysis</h1>
 *
 * <h2>Concepts</h2>
 * <p>
 * <b>Table</b>: A table is a set of cells in a selected range. The range should
 * have more than 1 row. The first row is always the header row and the others
 * the data rows. A table belongs to a spreadsheet.
 * </p>
 * <p>
 * <b>Row</b>: A row is a set of cells aligned horizontally
 * </p>
 * <p>
 * <b>Header Row</b>: A header row is a set of cells aligned horizontally and is
 * always the first row of the table.
 * </p>
 * <p>
 * <b>Data Row</b>: A data row is a set of cells aligned horizontally . Every
 * row of the table is a data row except the first one. The data row is where
 * the filters will be aplied.
 * </p>
 * <p>
 * <b>Filter</b>: A filter is a formula that will be applied to each data row of
 * the table. If the result is false the table will be set invisible otherwise
 * the data row will stay visible.
 * </p>
 * <p>
 * <b>Variable:</b>: A variable should start with &COL and with an index will
 * reference a column.
 * </p>
 * <p>
 * <b>Index:</b>: A index should be included a variable in order to reference a
 * column. For instance a index referencing the first column of a table will be
 * represented like : &COL[1] or &COL["COL HEADER NAME"]
 * </p>
 *
 *
 * <h2>Identified issues</h2>
 *
 * <p>
 * <b>Table Creation</b>: A table must be created only by selecting a cell range
 * in the spreadsheet in which it must contain more than one row. After that
 * filters can be applied by inserting into his field the desired filter.
 * </p>
 * <p>
 * <b>Filters Insertion</b>: The filters must be formulas that will be applied
 * to each row of the table. Filters may contain variables that refer
 * cells.Whenever a filter is inserted the sidebar updates all lines according
 * to the result of the indicated formula.
 * </p>
 * <p>
 * <b>Hide cells</b>: A requirement requested by the client is that when a
 * filter is applied and a cell does not comply with the filter indicated it
 * becomes invisible. As there is no functionality that puts cells invisible
 * when a line does not meet a filter, its foreground color is changed to be
 * equal to the background color thus giving the desired invisible effect.
 * </p>
 * <p>
 * <b>Variable Creation</b>: An variable must start with &COL . The variables
 * can be used as an array by simply using the index reference.
 * </p>
 * <p>
 * <b>Variable Indexing</b>: The indexing reference is made by using the numeric
 * index between square brackets. Despite computer science being fond of using
 * 0-indexed arrays, the client indicates that the first element within the
 * array is at index number one. The index can also be the column name, this
 * name can be found on the table header. If a invalid index is inserted by the
 * user the application will inform that the formula is poorly constructed
 * </p>
 * <p>
 * <b>Filters Removal</b>: Whenever a filter is removed the sidebar updates all
 * data rows so that the ones that are invisible become visible
 * </p>
 *
 * <h3>Notes:</h3>
 * A table only can be defined if the given range doesnt contain another defined
 * table.
 *
 * <h1>Tests</h1>
 *
 * <h2>Acceptance Tests</h2>
 *
 * <p>
 * <b>Sum Arrays</b>: A global variable must be declared and different values
 * must be inserted at random indexes. Then, on a specific cell, a temporary
 * variable must be declared and it must be defined the same number of values at
 * random indexes. Afterwards, all values must be added and the result shown be
 * equal to the expected value.
 * </p>
 * <p>
 * <b>Build Sentence</b>: A global variable must be declared and a word of a
 * sentence must be inserted in the array in order to form a sentence. Then,
 * select the cells to show the whole sentence (a word for a cell). At last,
 * change the word in the spreadsheet and see the side bar being updated.
 * </p>
 *
 * <h2>Other</h2>
 *
 * <p>
 * <b>Ensure lower indexes than the default are not allowed</b>: The client
 * states that the default index (the first element of the array) is 1. It makes
 * sense not to allow any lower index than the default one. In case it tries to
 * insert at a lower index, an exception should be launched.
 * </p>
 * <p>
 * <b>Ensure non sequential indexes are allowed</b>: The client states that the
 * array is dynamic and the indexes used may not be sequential, i.e. the user
 * may use index number 2 and then number 5. This should be performed without
 * any issue.
 * </p>
 * <p>
 * <b>Ensure variables hold different types of values</b>: The same variable may
 * have different types of values within its array, i.e. the index number 1
 * contains a number and index number 2 contains a string. This should be
 * performed without any issue.
 * </p>
 * <p>
 * <b>Ensure default value is returned for non assigned indexes</b>: The client
 * requested that the default value should be provided when the index the user
 * is trying to get has not been set. This should be performed without any
 * issue.
 * </p>
 * <p>
 * <b>Unit Testing</b>: The main methods created must be unit tested.
 * </p>
 *
 * <h1>Design</h1>
 *
 * <h2>Grammar</h2>
 *
 * <p>
 * In order to support the variable '&COL' to reference columns the grammar was
 * changed. Added an array to cell references As we can see below:
 * </p>
 * <blockquote>
 * <pre>
 * {@code
 * reference
 * :CELL_REF ( ( COLON ) CELL_REF )? | CELL | array
 * ;
 *
 * array:
 * (ARRAY_NAME)(index|string_index) 
 * ;
 *
 * ARRAY_NAME:
 * '&''COL'
 * ;
 * }
 * </pre>
 * </blockquote>
 * <p>
 * The existing grammar already handles the index with numbers. In order to
 * handle the index reference with columns name and '&COL' variable i added the
 * following grammar.
 * </p>
 * <blockquote>
 * <pre>
 * {@code
 * reference
 * :CELL_REF ( ( COLON ) CELL_REF )? | CELL | array
 * ;
 *
 * array:
 * (ARRAY_NAME)(index|string_index) ;
 *
 * ARRAY_NAME:
 * '&''COL'
 * ;
 * }
 * </pre>
 * </blockquote>
 * <p>
 * Now both variables have the ability to contain or not a reference to the
 * index. The index should always start with an opening square bracket followed
 * by a positive integer and then a closing square bracket. The positive integer
 * is a number that only contains digits and at least one, as well as the
 * leftmost digit is not equal to zero.
 * </p>
 *
 * <h2>Visits</h2>
 *
 * <p>
 * The existing visit methodology handles both temporary and global variables.
 * However, now that must be changed so it checks and handles the index
 * reference. Since we added a new rule to handle the index reference, it could
 * be treated at that visit method. However, the existing implementation
 * provides an easier and more suitable solution: handle the index at the
 * variable visit. Since the index belongs to the variable and the variable's
 * name and index are provided at this time, it makes sense that this rule
 * examines the tokens, splits the name from the index and saves &#47; gets the
 * matching expression.
 * </p>
 *
 * <h2>Variable</h2>
 *
 * <p>
 * The variable concept is more than just a name and an expression now. This
 * requires the {@link lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable}
 * to be altered. Although a completely new and different implementation is
 * needed (as discussed above), the current proposal is to maintain the original
 * concept as far as possible.
 * </p>
 * <p>
 * The variable have indexes now, which means an array. The implementation could
 * then be performed by using an array. However, the size of the array is
 * unknown and dynamical, i.e. it may change during runtime. So a
 * {@link java.util.List} could be used. The client also states that the indexes
 * may not be straight sequential (there might be gaps). Moreover, the first
 * element is at index number one and not zero. For this approach, a
 * {@link java.util.Map} whose key is the index and the value the expression
 * seems more suitable.
 * </p>
 * <p>
 * The variables hold expressions which are capable of handling all different
 * value types. This solves the multi-type array issue since each position holds
 * its own expression.
 * </p>
 *
 * <h2>VarContentor</h2>
 *
 * <p>
 * The above said also requires the
 * {@link lapr4.red.s2.lang.n1150623.globalVariables.VarContentor} to change
 * some of its methods, like the
 * {@link lapr4.red.s2.lang.n1150623.globalVariables.VarContentor#getExpressionOfVariable}
 * and {@link lapr4.red.s2.lang.n1150623.globalVariables.VarContentor#update}.
 * These will now require the index in which to operate. By default the first
 * element should be used as previously discussed.
 * </p>
 *
 * <h2>Compiler</h2>
 *
 * The side bar will receive user input to change the content of a global
 * variable. That user input shall then be compiled. For that purpose, the
 * {@link lapr4.blue.s1.lang.n1151452.formula.compiler.ExcelExpressionCompiler}
 * can be used.
 *
 * <h2>Update</h2>
 *
 * <p>
 * In order to update the side bar with the changes to global variables and
 * following the existing implementation, the
 * {@link lapr4.red.s2.lang.n1150623.globalVariables.VarContentor} can be an
 * {@link java.util.Observable} container which will be monitored by the side
 * bar. Although the VarContentor already extends another class, that extension
 * is not being used anywhere and it has no apparent reason to exist. So it can
 * be replaced.
 * </p>
 * <p>
 * On the other hand, the side bar can notify the workbook to update its cells.
 * It is a rather radical strategy but it seems like the only possible one,
 * since the global variables have no track of their precedents and dependents.
 * It is never enough to mention that this is only a bandage to attempt to
 * partially solve the issue. The best solution would be to refactor the whole
 * implementation and concepts. Regardless, since the client does not explicitly
 * require the cells to be updated on this Feature Increment, this solution will
 * not be implemented neither.
 * </p>
 * <p>
 * Plus, the side bar should also implement the
 * {@link csheets.SpreadsheetAppListener}, as well as the
 * {@link csheets.ui.ctrl.SelectionListener}, in order to monitor the workbook
 * activity to update the side bar.
 * </p>
 *
 * <h2>Alternatives</h2>
 *
 * <p>
 * Besides all the stated possibilities and advised changes, another alternative
 * (to the current implementation), would be to simply change the grammar as
 * stated. This would then create a global variable to each of the indexes
 * inserted and it would work normally. However, in business terms, this is not
 * what it was required and it would represent a short sight for future
 * maintenance.
 * </p>
 *
 * @author Nuno Pinto (1150838)
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters;
