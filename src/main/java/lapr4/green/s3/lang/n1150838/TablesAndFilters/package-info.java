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
 * '=or(_col[“idade“]&gt;10; _col[3]&lt;123)'.
 *
 * </p>
 * <p>
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
 * <b>Variable:</b>: A variable should start with &amp;COL and with an index
 * will reference a column.
 * </p>
 * <p>
 * <b>Index:</b>: A index should be included a variable in order to reference a
 * column. For instance a index referencing the first column of a table will be
 * represented like : &amp;COL[1] or &amp;COL["COL HEADER NAME"]
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
 * <b>Variable Creation</b>: An variable must start with &amp;COL . The
 * variables can be used as an array by simply using the index reference.
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
 * <p>
 * An index is a number if it does not start and end with quotation marks.
 * </p>
 *
 * <h1>Tests</h1>
 *
 * <h2>Acceptance Tests</h2>
 *
 * <p>
 * <b>Ensure Data Row is valid</b>: A data row should be initialized with a
 * valid cell set. A valid cell set must have at least 1 cell.
 * </p>
 * <p>
 * <b>Ensure Header Row is valid</b>: A data row should be initialized with a
 * valid cell set. A valid cell set must have at least 1 cell.
 * </p>
 * <p>
 * <b>Ensure Table is valid</b>: A table show be initialized with a valid row
 * set. A valid row set must have at least 2 rows.
 * </p>
 *
 * <h2>Other</h2>
 *
 * <p>
 * <b>Define table test</b>: This test ensure that a table is created with no
 * issues and all his possible returns are also tested.
 * </p>
 * <p>
 * <b>Is available to define test</b>: Test the method that checks if a given
 * set of cells do not belong to any table.
 * </p>
 * <p>
 * <b>Is defined test</b>: Tests the method that verifys if a given cell belongs
 * to a table
 * </p>
 * <p>
 * <b>Active tables test</b>:Tests the method that gets all the tables in the
 * active spreadsheet
 * </p>
 * <p>
 * <b>Remove table test</b>:Tests the method that removes a given table
 * </p>
 * <p>
 * <b>Verify formula test</b>:Tests the method that checks if the filter entered
 * by the user is valid and if it is valid returns the rows of the table that do
 * not respect the filter
 * </p>
 * <p>
 * <b>Build table test</b>:Tests the method that build a table and all his rows
 * from a given set off cells.
 * </p>
 * <h1>Design</h1>
 *
 * <h2>Grammar</h2>
 *
 * <p>
 * In order to support the variable '&amp;COL' to reference columns the grammar
 * was changed. Added an array to cell references As we can see below:
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
 * handle the index reference with columns name and '&amp;COL' variable added
 * the following grammar.
 * </p>
 * <blockquote>
 * <pre>
 * {@code
 * index:
 * INDEX
 * ;
 *
 * string_index:
 * '[' STRING ']'
 * ;
 * INDEX:
 * L_RIGHT_PAR POSITIVE_DIGIT (DIGIT)* R_RIGHT_PAR
 * ;
 *
 * ARRAY_NAME:
 * '&''COL'
 * ;
 * }
 * </pre>
 * </blockquote>
 * <p>
 * Now the grammar can has the hability to support cells reference by column.
 * </p>
 *
 * <h2>Visits</h2>
 *
 * <p>
 * It was necessary to change the visitReference method so that it supports
 * column references. As the visit is made per data row whenever it is called it
 * checks if the inserted index is valid and if it is it will get the cell of
 * the data row that corresponds that index. If the index is a string it will
 * check if there is any cell in the header row that contains that name and if
 * it does returns the number of the index corresponding to the column otherwise
 * a InvalidIndexException is thrown.
 * </p>
 * <h2>Compiler</h2>
 *
 * The side bar will receive user input to change the table filter . That user
 * input shall then be compiled. For that purpose, the
 * {@link lapr4.blue.s1.lang.n1151452.formula.compiler.ExcelExpressionCompiler}
 * will be used.
 *
 * <h2>Update</h2>
 * <p>
 * It is necessary to implement the editlistenner in order to know when a cell
 * is modified and after that check if new cell value verifys the filter . Also
 * it is necessary to implement the selectionlistenner to know when the
 * spreadsheet is changed in order to refresh the table list. The
 * {@link csheets.ui.ctrl.FocusOwnerAction} will be extended to get the selected
 * cells to define a table.
 * </p>
 * <p>
 * Implemented listeners: {@link csheets.ui.ctrl.EditListener}, as well as the
 * {@link csheets.ui.ctrl.SelectionListener}, in order to monitor the workbook
 * activity to update the side bar.
 * </p>
 * <h2>UC Realization</h2>
 * <img src="core03_03_design_sd.png" alt="image">
 * <p>
 * </p>
 *
 * @author Nuno Pinto (1150838)
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters;
