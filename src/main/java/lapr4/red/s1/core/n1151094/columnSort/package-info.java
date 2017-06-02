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
 * The user selects any cell of the column he wants to order. Reaches the top bar and selects "Edit" option -> "Sort" -> "Column Sort" and then he can select ascending or descending sort depending oh what kind of order he desires. The system orders the cell values.
 *
 * <h2>3. Analysis</h2>
 * 
 * The first step I took was to analyse the code that was already done to understand how their funcionalities were organized.
 * After that I understood that, it was needed to get the active spreadsheet in UIController and the selected cells. 
 * In order to get the amount of lines of the selected column is used the method getRowCount() that belongs to Spreadsheet. 
 * This value will be necessary to determine the size of the Value vector that will be used to store the values of the selected cells.
 * During code analysis, found that the class Value implements the compareTo method that will be able order cells with different data types. For the order itself I used the Selection Sort algorithm, so that it wouldn't be needed an auxiliar array during ordination so it will consume less memory.
 * 
 4. Design</h2>
 * <p>
 *
 * <h3>4.1. Functional Tests</h3>
 * <p>
 
 * <p>
 * see:
 *
 * <p>

 * <p>
 *
 * <h3>4.2. UC Realization</h3>
 *
 * <p>
 *
 * <h3>4.3. Classes</h3>
 * <p>

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
 * 
 * <h3>R7. Rubric Design and Implement: </h3>
 *
 *
 * @author Eduangelo Ferreira 1151094
 */

 package lapr4.red.s1.core.n1151094.columnSort;