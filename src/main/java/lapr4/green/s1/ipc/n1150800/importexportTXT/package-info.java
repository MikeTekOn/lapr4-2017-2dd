/**
 * Technical documentation regarding the user story IPC04.1: Import/Export Text. 
 * 
 * <h1><u>Requirement</u></h1>
 * <h2>Statement:</h2><p>
 * It should be possible to export and import data to/from a text file. Each line in the text file represents a row of data. In each line a special character is used to separate columns. The user should be able to configure this character and also define if the first line of the text file should be treated as containing the header of the columns or as regular row.<p>
 * The user should also enter a range of cells to be used as source (export) or destination (import) for the operation.
 * 
 * <h2>System Sequence Diagram (SSD):</h2><p>
 * <img src="us084_ssd.png" alt="image"> 
 * 
 * <h2>Extensions:</h2>
 * The user may configure the file's column separator character.
 *  
 * 
 * 
 * <h1><u>Analysis</u></h1>
 * 
 * <h2>Concepts Description:</h2>
 * 
 * <ul>
 *  <li><b>Workbook:</b> A workbook can contain several spreadsheets.</li>
 *  <li><b>Spreadsheet:</b> A spreadsheet provides cell data and dependencies.</li>
 *  <li><b>Cell:</b> A cell is characterized by an address (column, row) in a spreadsheet.</li>
 * </ul>
 * 
 * <h2>Business Relations</h2>
 * <ul>
 *  <li><b>Workbook</b> - has <b>Spreadsheet</b></li>
 *  <li><b>Spreadsheet</b> - contains <b>Cell</b></li>
 *  <li><b>Cell</b> - characterized by <b>Address</b></li>
 * </ul>
 * 
 * <h2>Open Questions:</h2>
 * 
 * <ul>
 *  <li>Is the data written into the file from a spreadsheet or a whole workbook?
 *      <ul>   
 *          <li><b>Assumption:</b> <u>From a spreadsheet because the user chooses a cell range to manage the data.</u></li>
 *          <li><b>Answer:</b> <i>Check the assumption!</i></li>
 *      </ul>
 *  </li>
 *  <li>Is the data read from the file about a spreadsheet or a whole workbook?
 *      <ul>
 *          <li><b>Assumption: </b><u>From a spreadsheet because the user chooses a cell range to manage the data.</u></li>
 *          <li><b>Answer:</b> <i>Check the assumption!</i></li>
 *      </ul>
 *  </li>
 *  <li>Are there any restrictions into the choice of the separator character?
 *      <ul>
 *          <li><b>Assumption:</b> <u>No.</u></li>
 *          <li><b>Answer:</b> <i>Check the assumption!</i></li>
 *      </ul>
 *  </li>
 *  <li> Is the range of cells defined by a width x height value or by the upper left cell and bottom right cell?
 *      <ul>
 *          <li><b>Assumption:</b> <u>Upper left cell and bottom right cell.</u></li>
 *          <li><b>Answer:</b> <i>Check the assumption!</i></li>
 *      </ul>
 *  </li>
 * </ul>
 * 
 * <h2>Analysis Diagram (Import):</h2><p>
 * <img src="./importTXT/us084_analysis_import.png" alt="image">
 * 
 * <h2>Analysis Diagram (Export):</h2><p>
 * <img src="./exportTXT/us084_analysis_export.png" alt="image">
 * 
 * <h2>Notes:</h2>
 * 
 * No further notes.
 * 
 * 
 * 
 * <h1><u>Tests Planning</u></h1><p>
 * This should include not only unit tests (e.g., class-oriented tests) but also use case tests (e.g., like in the TDD approach). <p>
 * Basically, from requirements and also analysis, we see that the core functionality of this use case is to be able to import 
 * cells data to be stored in a text file and/or export data from it. For this, we need to be able to set and get the cells content.
 * As usual, in a test driven development approach tests normally fail in the beginning. The idea is that the tests will pass in the end. <p>
 * 
 * <b>Test1:</b> CellDataIsNotEmptyTest <p>
 * It should not be allowed to export empty cell data. 
 * This doesn't exclude the fact that a cell can be exported if it's empty.
 * All cells from a defined range can't be empty.
 * See Package lapr4.green.s1.n1150800.importexporttest<p>
 * CellDataIsNotEmptyTest<p>
 * 
 * 
 * 
 * <h1><u>Design</u></h1>
 * 
 * <h2>Sequence Diagram (Import):</h2><p>
 * <img src="./importTXT/us084_design_import.png" alt="image">
 * 
 * <h2>Sequence Diagram (Export):</h2><p>
 * TODO
 *  
 * 
 * 
 * <h1><u>Code</u></h1><p>
 * The following classes and interfaces implement this use case.<p>
 * Package lapr4.green.s1.ipc.n1150800.importexportTXT:<p>
 * {@link lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange}
 * <p>
 * Package lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ctrl:<p>
 * {@link lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ctrl.ImportDataController}
 * <p>
 * Package lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ui:<p>
 * {@link lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ui.ImportDataAction}
 * {@link lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ui.ImportDataUI}
 * 
 * @author Pedro Chilro (1150800)
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT;

