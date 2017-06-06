/**
 * Technical documentation regarding the user story <b>CORE03.2: Sort Range of
 * Cells</b>.
 *
 * <h1>Requirements</h1>
 *
 * Sort a range of cells. A range of cells is a rectangular area delimited by an
 * upper left corner and a lower right corner. The sorting is based on a column
 * of the range. It should be possible to select the order: ascending or
 * descending. Interaction with the user should be based on a popup menu. It
 * should be possible to sort data of the following types: numeric, text or
 * date.
 *
 * <h1>Analysis</h1>
 *
 * <h2>What is old?</h2>
 * <ul>
 * <li>The previous Feature Increment
 * ({@link lapr4.red.s1.core.n1151094.columnSort.package-info}) already sorted a
 * whole column.</li>
 * <li>It could sort {@link csheets.core.Value.Type#NUMERIC} and/or
 * {@link csheets.core.Value.Type#TEXT} {@link csheets.core.Value}s.</li>
 * <li>It used Merge Sort Algorithm.</li>
 * <li>It sorted in both
 * {@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController#sortAscending}
 * or
 * {@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController#sortDescending}
 * order.</li>
 * <li>It used two menu options
 * ({@link lapr4.red.s1.core.n1151094.columnSort.presentation.ColumnSortUI}),
 * one for each sorting order.</li>
 * <li>It used the selected {@link csheets.core.Cell} to determine the column to
 * sort.</li>
 * <li>It exchanges the {@link csheets.core.CellImpl#content} only.
 * </ul>
 *
 * <h2>What needs fixing?</h2>
 * <ul>
 * <li>The Merge Sort Algorithm could be generic. It should not be a specific
 * responsibility of the
 * {@link lapr4.red.s1.core.n1151094.columnSort.ColumnSort}.</li>
 * <li>The algorithm contains hard-coded values (a.k.a. "Magic Numbers" -
 * {@link lapr4.red.s1.core.n1151094.columnSort.ColumnSort#merge}) used by the
 * {@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController} ({@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController#DEFAULT_ASCENDING}
 * and
 * {@link lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController#DEFAULT_DESCENDING}).</li>
 * <li>The cell exchanges lose all other cell information.
 * </ul>
 *
 * <h2>What is new?</h2>
 * <ul>
 * <li>This Feature Increment must sort rows by comparing the values of one
 * column.</li>
 * <li>It must only sort a specific number of columns within each row.</li>
 * <li>It must only sort a specific number of rows within each column.</li>
 * <li>It must also sort {@link java.util.Date} type
 * {@link csheets.core.Value}s.</li>
 * <li>It must use a pop-up window.</li>
 * </ul>
 *
 * <h2>What can be improved?</h2>
 * <ul>
 * <li>The user may choose the sorting algorithm.</li>
 * <li>The cells being sorted should be locked for further changes until the
 * task is finished.</li>
 * <li>The sorting could also compare boolean types</li>
 * </ul>
 *
 * <h2>Concepts</h2>
 * <b>Cell Range</b>: It represents a selection of multiple columns and rows of
 * the spreadsheet.
 * <p/>
 * <b>Range Row</b>: It represents all the cells within the same row and the
 * selected cell range.
 * <p/>
 * <b>Range Column</b>: It represents all the cells within the same column and
 * the selected cell range.
 * <p/>
 * <b>Sorting Column</b>: It represents the selected column to be compared
 * within the sorting operation.
 * <p/>
 * <b>Value Type</b>: It represents the value content. It can be a numeric,
 * textual, date or boolean content.
 * <p/>
 * <b>Value Type Hierarchy</b>: It represents which value type is greater than
 * the others for comparing different value types.
 *
 * <h2>Identified issues</h2>
 * <b>Values Comparison</b>: The textual values are sorted by the alphabetic
 * order. The numeric values are sorted by their mathematical greatness. The
 * date values are sorted by chronological order. The true issue emerges when
 * comparing different value types. In this case, the value types hierarchy is
 * considered.
 * <p/>
 * <b>Value Types Hierarchy</b>: The value types comparison is currently
 * performed by enumeration order. However, should it be allowed the user to be
 * able define which value type is greater than the other? This will require
 * different comparison methods to suit all possible combinations.
 * <p/>
 * <b>Exchanging Cells</b>: The cells has more information than the content
 * itself, like formatting and precedents &#47; dependents. That information
 * must not only be kept as it should be updated when the sorting is performed.
 *
 * @author Manuel Meireles (1150532)
 */
package lapr4.green.s2.core.n1150532.sort;

import csheets.core.Value;
