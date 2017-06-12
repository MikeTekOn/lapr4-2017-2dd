/**
 * Technical documentation regarding the user story IPC08.2: PDF Style Export. 
 * 
 * <h1><u>1. Requirement</u></h1>
 * <h2>1.1 Statement:</h2><p>
 * The generated PDF should now mimic as far as possible the screen style of the exported contents. For instance, the formatting of the cells in the PDF should be similar to the screen. <p>
 * It should be also possible to configure the type of lines to use for cell delimitation, the type of line and colour. <p>
 * This is to be applied when rendering all cells in the PDF. Note that this is different from the style used for cells borders in the screen.
 * 
 * 
 * <h1><u>2. Analysis</u></h1>
 * 
 * <p>
 * Since Java doesn't natively support the exportation to PDF's in a more common
 * and with an output that resembles what PDF's are for, it was decided that it
 * was going to be needed a tool to do that generation in a more apropriate way.
 * Having in mind the final PDF output and the suggestions given in the LAPR4
 * manual, the <b>iText</b> tool would be used. To do so, a dependency, stated
 * in their website needs to be added so that Gradle (and Maven) can automaticly
 * download the required ".jar"'s:
 * 
 * <pre>
 * {@code
 * https://mvnrepository.com/artifact/com.itextpdf/root compile group:
 * 'com.itextpdf', name: 'root', version: '7.0.2'
 * }
 * </pre>
 * 
 * <p>
 * Regarding the application functionality itself, a new Menu (and not an
 * extension) should be created for this new requirement, with a new option
 * "Export to PDF". Clicking on it, a pop-up window should appear allowing
 * the user to select the "range" of the export: the workbook, a specific
 * worksheet or a range of cells and, more specific to this iteration, the user
 * should select the type of border line he wants for the output document, 
 * along with its color. Independently of the chosen option, a File Chooser 
 * should appear allowing the user to select the destination path.
 * 
 * <h2>2.1 Concepts Description:</h2>
 * 
 * <ul>
 *  <li><b>Workbook:</b> A workbook can contain several spreadsheets.</li>
 *  <li><b>Spreadsheet:</b> A spreadsheet provides cell data and dependencies.</li>
 *  <li><b>Cell:</b> A cell is characterized by an address (column, row) in a spreadsheet.</li>
 * </ul>
 * 
 * <h2>2.2 Business Relations</h2>
 * <ul>
 *  <li><b>Workbook</b> - has <b>Spreadsheet</b></li>
 *  <li><b>Spreadsheet</b> - contains <b>Cell</b></li>
 *  <li><b>Cell</b> - characterized by <b>Address</b></li>
 * </ul>
 * 
 * <h2>2.3 Open Questions:</h2>
 * 
 * <ul>
 *  <li> The cell formats to be mimicked include which styles?
 *      <ul>
 *          <li><b>Assumption:</b>
 *              <p><u>Text:</u> bold, italic, centered, aligned left/right, font and foreground color.</p>
 *              <p><u>Cell:</u> border and background color.</p>
 *          </li><p>
 *          <li><b>Answer:</b> <u>Check the assumption.</u></li>
 *      </ul>
 *  </li>
 *  <p>
 *  <li> Should there be an option to export while keeping the cell styles along with the previous option to export only the content of the cells?
 *      <ul>
 *          <li><b>Answer:</b> There's only one option and the cell styling is an extension of the previous iteration.</li>
 *      </ul>
 *  </li>
 * </ul>
 * 
 * <h2>2.4 Analysis Diagram (SSD):</h2><p>
 * 
 * In terms of the interaction between the user and the software, it was
 * developed an System Sequence Diagram to aid in its better interpretation.
 * <p>
 * 
 * <img src="us025_analysis_ssd.png" alt="image">
 * 
 * 
 * 
 * <h1><u>3. Tests Planning</u></h1><p>
 * This should include not only unit tests (e.g., class-oriented tests) but also use case tests (e.g., like in the TDD approach). <p>
 * Basically, from the requirements and also analysis, we see that the core functionality of this use case is to be able to export data from a workbook, spreadsheet and/or cells while keeping the cell styles.<p>
 * For this, we need to be able to get the content of the cells and the style in which they're formatted.
 * As usual, in a test driven development approach tests normally fail in the beginning. The idea is that the tests will pass in the end. <p>
 * 
 * <h2>3.1 Unitary Tests</h2>
 * 
 * <b>Ensure the border line is chosen</b>: This test consists in veryfing
 * that the border line option presented in the user interface when the use
 * case functionality option is selected, should be chosen. In order to be
 * reliable, the test is expecting an exception thrown because it's given
 * no border line option to the strategy class method.
 * 
 * <h2>3.2 Functional Tests</h>
 * 
 * <b>Getting the stylable cells from a workbook</b>: This test consists in
 * verifying that the cells style of a given workbook are returned when the
 * method being tested is called. In order to be reliable, it were created
 * a workbook and it was given some cells from it a few styles, like, cell
 * background, cell foreground, font style, font size, etc.<p>
 * 
 * </b>Getting the stylable cells from a spreadsheet</b>: This test consists in
 * verifying that the cells style of a given spreadsheet are returned when the
 * method being tested is called. In order to be reliable, it were created
 * a spreadsheet and it was given some cells from it a few styles, like, cell
 * background, cell foreground, font style, font size, etc.<p>
 * 
 * </b>Getting the stylable cells from a cell range</b>: This test consists in
 * verifying that the cells style of a given spreadsheet are returned when the
 * method being tested is called. In order to be reliable, it were created
 * a spreadsheet, and a cell range in a string format and it was given the cells 
 * from the range a few styles, like, cell background, cell foreground, 
 * font style, font size, etc.
 * 
 * 
 * <h1><u>4. Design</u></h1>
 * 
 * <h2>4.1 UC Realization</h2>
 * 
 * <h3>4.1.1 Sequence Diagram</h3><p>
 * <img src="us025_design_sd.png" alt="image">
 * 
 * <h3>4.1.2 Workbook Handler Diagram</h3><p>
 * 
 * For the present use case iteration, the only difference from its previous 
 * iteration is the fact that now there must exist persistence of not only the
 * content of a workbook's cells but there must also exist a functionality that
 * keeps and transfers the style of a cell into a PDF file.<p>
 * 
 * In a design approach, this only imposes a change in the way the workbook 
 * handler will manage the export functionality.
 * 
 * <img src="us025_design_workbook_handler_sd.png" alt="image">
 * 
 * <h2>4.2 Classes</h2><p>
 * 
 * <h3>4.2.1 Class Diagram</h3><p>
 * 
 * Analysing the proposed sequence diagram, the following class diagram was
 * developed:
 * <p>
 * <img src="us025_design_cd.png" alt="image">
 * 
 * <h3>4.3 Strategy Pattern</h3><p>
 * 
 * In the Strategy Pattern, a class or an
 * algorithm, can change behavior during the runtime of the software, being a
 * good choice in this situation, since the data to be exported is the same in
 * both PDF (with or without cell styling) and XML exportation funcionalities. To implement this pattern, the
 * following "base diagram" was used:
 * <p>
 * <img src="us025_design_pattern.png" alt="image">
 * 
 * 
 * 
 * <h1><u>5. Code</u></h1><p>
 * The following classes and interfaces implement this use case.<p>
 * Package lapr4.green.s2.core.n1150800.PDFStyle.application:<p>
 * {@link lapr4.green.s2.core.n1150800.PDFStyle.application.ExportStylePDFController}
 * <p>
 * Package lapr4.green.s2.core.n1150800.PDFStyle.domain:<p>
 * {@link lapr4.green.s2.core.n1150800.PDFStyle.domain.ExportStylePDF}
 * {@link lapr4.green.s2.core.n1150800.PDFStyle.domain.ExportStylePDFThread}
 * {@link lapr4.green.s2.core.n1150800.PDFStyle.domain.WorkbookWithStylesHandler}
 * <p>
 * Package lapr4.green.s2.core.n1150800.PDFStyle.presentation:<p>
 * {@link lapr4.green.s2.core.n1150800.PDFStyle.presentation.ExportStylePDFUI}
 * <p>
 * 
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
package lapr4.green.s2.core.n1150800.PDFStyle;

