/**
* Technical documentation regarding the user story Core03.1 Column Sort
* <p>
*
* <b>Scrum Master: -(yes/no)- no</b>
* <p>
* <b>Area Leader: -(yes/no)- no</b>
*
* <h2>1. Notes</h2>
* -Notes about the sprint's work.-
* <p>
*
* <h2>2. Requirement</h2>
*
* - Sort the contents of a column of cells. It should be possible to select the order: ascending or descending.
* - The interaction with the user can be based solely on menu options. 
* - It should be possible to sort columns with numeric and/or text contents.
*
* <p>
* <b>Use Case "Column Sort":</b>
*
* The user selects any cell of the column he wants to order. Reaches the top
* bar and selects "Edit" option -&gt; "Sort" -&gt; "Column Sort" and then he can
* select ascending or descending sort depending oh what kind of order he
* desires. The system orders the cell values.
*
* <h2>3. Analysis</h2>
*
* The first step I took was to analyse the code that was already done to understand how their funcionalities were organized.
* After that I understood that, it was needed to get the active spreadsheet in UIController and the selected cells.
* In order to get the amount of lines of the selected column is used the method getRowCount() that belongs to Spreadsheet.
* This value will be necessary to determine the size of the Value vector that will be used to store the values of the selected cells. 
* During code analysis, found that the class Value implements the compareTo method that will be able order cells with different data types. 
* For the order itself I used the Merge Sort algorithm, so compared to other algorithms of conquest and division, such as Quick Sort Merge, it has the same complexity, but compared to the most basic  algorithms of ordering by comparison and exchange (bubble sort, Insertion sort and selection).
* Merge is faster and more efficient, when a large amount of data is used, for small inputs the most basic comparison ordering algorithms are pro-efficient. 
* Compared to QuickSort, the average complexity is the same, but the worst case complexity O(n log(n)) is much better than QuickSort's O(n²) 
* 
* <h2>4. Design</h2>
* <p>
*
* <h3><b>Functional Tests</b></h3>
* <p>
* We can see from the requisites and also the analysis that, the main functionality of this use-case is to be able to sort the contents of a column of cells in an ascending or descending order.
* <p>
* see: lapr4.red.s1.core.n1151094.columnSort
* <p>
* To start the use-case, the user needs to select a column from the currently active spreadsheeet that will be converted to an array that can be passed as an argument to the ColumnSort object, but if the array is null it is not possible to sort.
* In case it is different, it is possible to sort the contents of the cells of the column, be it ascending or descending order.
* <p>
* <h3><b>Sequence Diagrams</b></h3>
* The following sequence diagram illustrates what happens when the user orders
* a column.
* <p>
* <img src="core03._01_design.png" alt="image">
* 
* <h3><b>Classes</b></h3>
* <p>
* These are the classes related to this use case:
* <p>
* <img src="class_diagram.png" alt="image">
*
* <h2>8. Work Log</h2>
*
*  <p>
* <b>Tuesday</b>
* <p>
* Yesterday I worked on:
* <p>
* 1. nothing
* <p>
* Today
* <p>
* 1. Analysis of column sort
* <p>
* Blocking:
* <p>
* 1. nothing
* <p>
* <p>
* <b>Wednesday</b>
* <p>
* Yesterday I worked on:
* <p>
* 1. Seeing how the code works
* <p>
* Today
* <p>
* 1. Design and some offline code experimentation
* <p>
* Blocking:
* <p>
* 1. nothing
 * <p>
*
* @author Eduangelo Ferreira 1151094
*/
package lapr4.red.s1.core.n1151094.columnSort;
