/**
 * Technical documentation regarding the user story <b>CORE03.2: Sort Range of
 * Cells</b>.
 *
 * <h1>Requirements</h1>
 *
 * <p>
 * Sort a range of cells. A range of cells is a rectangular area delimited by an
 * upper left corner and a lower right corner. The sorting is based on a column
 * of the range. It should be possible to select the order: ascending or
 * descending. Interaction with the user should be based on a popup menu. It
 * should be possible to sort data of the following types: numeric, text or
 * date.
 * </p>
 *
 * <h1>Analysis</h1>
 *
 * <h2>What is old?</h2>
 * <ul>
 * <li>The previous Feature Increment already sorted a whole column.</li>
 * <li>It could sort {@link csheets.core.Value.Type#NUMERIC} and/or
 * {@link csheets.core.Value.Type#TEXT} {@link csheets.core.Value}s.</li>
 * <li>It used Merge Sort Algorithm
 * ({@link lapr4.red.s1.core.n1151094.columnSort.ColumnSort#sort}).</li>
 * <li>It sorted in both
 * {@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController#sortAscending}
 * or
 * {@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController#sortDescending}
 * order.</li>
 * <li>It used two menu options
 * ({@link lapr4.red.s1.core.n1151094.columnSort.presentation.ColumnSortUI}),
 * one for each sorting order.</li>
 * <li>It used the selected cell
 * ({@link csheets.ui.sheet.SpreadsheetTable#getSelectedCell} and
 * {@link csheets.core.Cell}) to determine the column to sort.</li>
 * <li>It exchanges the {@link csheets.core.CellImpl#content} only.
 * </ul>
 *
 * <h2>What needs fixing?</h2>
 * <ul>
 * <li>The Merge Sort Algorithm could be more generic. It should not be a
 * specific responsibility of the
 * {@link lapr4.red.s1.core.n1151094.columnSort.ColumnSort}.</li>
 * <li>The algorithm contains hard-coded values (a.k.a. "Magic Numbers" -
 * {@link lapr4.red.s1.core.n1151094.columnSort.ColumnSort#merge}) used by the
 * {@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController} ({@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController#DEFAULT_ASCENDING}
 * and
 * {@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController#DEFAULT_DESCENDING}).</li>
 * <li>The cell exchanges lose all other cell information.</li>
 * <li>The value parse ({@link csheets.core.Value#parseValue}) on an empty
 * string results in a {@link csheets.core.Value.Type#DATE} instead of an
 * {@link csheets.core.Value.Type#UNDEFINED}.</li>
 * </ul>
 *
 * <h2>What is new?</h2>
 * <ul>
 * <li>This Feature Increment must sort rows by comparing the values of one
 * column.</li>
 * <li>It must only sort a specific number of columns within each row.</li>
 * <li>It must only sort a specific number of rows within the columns.</li>
 * <li>It must also sort {@link csheets.core.Value.Type#DATE} type
 * {@link csheets.core.Value}s.</li>
 * <li>It must use a pop-up window.</li>
 * </ul>
 *
 * <h2>What can be improved?</h2>
 * <ul>
 * <li>The user may choose the sorting algorithm.</li>
 * <li>The cells being sorted should be locked for further changes until the
 * task is finished.</li>
 * <li>The sorting could also compare
 * {@link csheets.core.Value.Type#BOOLEAN}, {@link csheets.core.Value.Type#ERROR}, {@link csheets.core.Value.Type#MATRIX}
 * and {@link csheets.core.Value.Type#UNDEFINED}.</li>
 * </ul>
 *
 * <h2>Concepts</h2>
 * <p>
 * <b>Cell Range</b>: It represents a selection of multiple columns and rows of
 * the spreadsheet.
 * </p>
 * <p>
 * <b>Range Row</b>: It represents all the cells within the same row and the
 * selected cell range.
 * </p>
 * <p>
 * <b>Range Column</b>: It represents all the cells within the same column and
 * the selected cell range.
 * </p>
 * <p>
 * <b>Sorting Column</b>: It represents the selected column to be compared
 * within the sorting operation.
 * </p>
 * <p>
 * <b>Value Type</b>: It represents the value content. It can be a numeric,
 * textual or date content.
 * </p>
 * <p>
 * <b>Value Type Hierarchy</b>: It represents which value type is greater than
 * the others for comparing different value types.
 * </p>
 *
 * <h2>Identified issues</h2>
 *
 * <p>
 * <b>Values Comparison</b>: How are the values itself compared? The textual
 * values are sorted by the alphabetic order; The numeric values are sorted by
 * their mathematical greatness; The date values are sorted by chronological
 * order. The true issue emerges when comparing different value types. In this
 * case, the value types hierarchy is considered.
 * </p>
 * <p>
 * <b>Value Types Hierarchy</b>: The value types comparison is currently
 * performed by enumeration order. However, should it be allowed the user to be
 * able define which value type is greater than the other? This will require
 * different comparison methods to suit all possible combinations.
 * </p>
 * <p>
 * <b>Exchanging Cells</b>: The cell has more information than the content
 * itself, like formatting and precedents &#47; dependents. That information
 * must not only be kept as it should be updated when the sorting is performed.
 * However, the original implementation of the application makes this operation
 * extremely hard and complex. After many hours of studying the issue, a
 * feasible solution was not reached. The major problem is the reference sharing
 * system which is bullet-proofed within the classes. This issue has been
 * discussed with the both prof. ATB and PRP and it was settled that only the
 * values itself must be exchanged.
 * </p>
 * <p>
 * <b>Exchanging Rows</b>: The range row should be swapped based on the
 * comparison of the cell from the sorting column. How will the algorithm
 * perform this if only a cell is compared? Plus, the cell objects can not be
 * moved, only their content can be exchanged.
 * </p>
 *
 * <h1>Tests</h1>
 *
 * <h2>Acceptance Tests</h2>
 *
 * <p>
 * <b>Sort a range of cells in ascending order</b>: This test consists in
 * verifying that given a range of cells, the sorting column, the sorting
 * algorithm and the value types hierarchy, the output cells are correctly
 * placed in ascending order. In order to be reliable, the given cells must
 * contain all sortable value types more than once and mixed along.
 * </p>
 * <p>
 * <b>Sort a range of cells in descending order</b>: This test consists in
 * verifying that given a range of cells, the sorting column, the sorting
 * algorithm and the value types hierarchy, the output cells are correctly
 * placed in descending order. In order to be reliable, the given cells must
 * contain all sortable value types more than once and mixed along.
 * </p>
 *
 * <h2>Other</h2>
 *
 * <p>
 * <b>Sorting Algorithms</b>: Each sorting algorithm should have its own tests.
 * The test consists in providing an unsorted array of elements and check if the
 * output matches the expected order. Both ascending and descending orders
 * should be tested.
 * </p>
 * <p>
 * <b>Comparison Methods</b>: Each comparison method should have its own tests.
 * The test consists in providing two cells and check if the comparison result
 * is the expected one. All possible combinations should be tested.
 * </p>
 *
 * <h1>Design</h1>
 *
 * <h2>Generic Sorting Algorithm</h2>
 * <p>
 * There are many sorting algorithms already studied and examined. We will use
 * both Bubble and Quick Sort due to their good overall complexity. These
 * algorithms should be generic in order to allow the reuse of its code for all
 * kinds of sorting. A suitable DTO can be used to provide the algorithm the
 * right data at the right time. This approach is specially useful since what is
 * being sorted are not the objects themselves but the values they possess. This
 * makes the Merge Sort not functional, since it requires an auxiliary array to
 * place the sorted elements. Also, since the user may change the comparison
 * results by altering the value types hierarchies, a suitable comparator can be
 * provided to the algorithm. The algorithms can be defined through the
 * following pseudo-code:
 * </p>
 * <b>Bubble Sort</b>
 * <blockquote>
 * <pre>
 * {@code
 * Algorithm void bubbleSort (T v[], int n) {
 *      swap = true
 *      for( i = 0 ; ( i < n-1 && swap ) ; i++ ){
 *          swap = false
 *          for ( j = n-1 ; j > i ; j-- ){
 *              if( v[j-1] > v[j] ){
 *                  swap( v[i] , v[j-1] )
 *                  swap = true
 *              }
 *          }
 *      }
 *  }
 * }
 * </pre>
 * </blockquote>
 * <b>Quick Sort</b>
 * <blockquote>
 * <pre>
 * {@code
 * Algorithm void quickSort (T S[], int left, int right) {
 *      pivot = S[ ( left + righ t) / 2 ]
 *      i = left
 *      j = right
 *      while ( i <= j ){
 *          while ( S[i] < pivot )
 *              i++
 *          while ( S[j] > pivot )
 *              j--
 *          if ( i <= j ){
 *              swap( S[i] , S[j] )
 *              i++
 *              j--
 *          }
 *          if ( left < j )
 *              quickSort( S , left , j )
 *          if ( right > i )
 *              quickSort( S , i , right )
 *      }
 *  }
 * }
 * </pre>
 * </blockquote>
 *
 * <h2>Comparator</h2>
 *
 * <p>
 * A {@link java.util.Comparator} can be provided to the sorting algorithm for
 * it to assess the greatness of the objects that it is sorting. This allows the
 * same type of objects to have different comparison evaluations depending on
 * the provided comparator. Due to the ascending and descending order demand and
 * since the comparison itself is the same (the result is inverted) a boolean
 * can be provided to the comparator to change accordingly.
 * </p>
 * <p>
 * In our case
 * ({@link lapr4.green.s2.core.n1150532.sort.comparators.RangeRowDTOComparator}).
 * The used comparator uses the {@link csheets.core.Value#compareTo} method when
 * both have the same {@link csheets.core.Value.Type}. Otherwise, it uses a
 * {@link lapr4.green.s2.core.n1150532.sort.comparators.TypeComparator} whose
 * specific implementation was chosen by the user. This is useful to permit the
 * user to define the value types hierarchy.
 * </p>
 * <p>
 * One last note to mention that the comparator should provide a description.
 * </p>
 *
 * <h2>Manage Available Algorithms and Comparators</h2>
 *
 * <p>
 * In order to provide all the available algorithms and comparators to the user,
 * a factory must be created (one for algorithms and another to comparators). It
 * will be their responsibility to know which classes to instantiate and provide
 * (Factory Pattern).
 * </p>
 * <p>
 * These objects will be presented to the user who will then select one of them.
 * The chosen object will then be used to perform the operations. This is
 * another easy but camouflaged way of using the Strategy Pattern.
 * </p>
 *
 * <h2>Sortable Data Transmission Object</h2>
 *
 * <p>
 * The proposed DTO must be able to provide the comparing element, as well as
 * the ability to swap its encapsulated data with the one of another DTO. In
 * this specific case, the DTO holds the range row and provides the comparing
 * cell's value. The swap will consist in exchanging the content of each and
 * every cell of the range rows.
 * </p>
 *
 * <h2>Block Cell Range</h2>
 *
 * <p>
 * For this operation, listeners could be used to ensure that only those
 * specific cells are blocked. However, that solution implies that the remaining
 * cells does not affect the cells being sorted, which is not true since these
 * can contain references to those. Plus, the listeners represent a higher
 * system burden which can easily be replaced by a
 * {@link javax.swing.JDialog#modal} activation on the pop-up menu.
 * </p>
 *
 * <h2>Parse Value</h2>
 *
 * <p>
 * The value parsing method must start by checking if the string is empty and if
 * it is, it should return a default Value, i.e. a value with empty content and
 * undefined type.
 * </p>
 *
 * <h2>Sequence Diagram</h2>
 *
 * <img src="core_03_2_design_1.png" alt="image">
 *
 * <h2>Alternatives</h2>
 *
 * <p>
 * An alternative, rather simple, would be to duplicate the selected cells
 * matrix. Then, it could be sorted by simply exchanging the rows order within
 * the matrix. At last, the content of the sorted matrix could be set on the
 * content of the original matrix. Although this is a simpler alternative, the
 * prof. PRP specifically asked for it not to be used.
 * </p>
 *
 * @author Manuel Meireles (1150532)
 */
package lapr4.green.s2.core.n1150532.sort;

import csheets.core.Value;
