/**
 * Technical documentation regarding the user story Core08.3 : Complete PDF Export.
 * <p>
 * <b>JIRA ISSUE: LAPR4E17DD-26</b><p>
 * <b>Scrum Master: no</b><p>
 * <b>Area Leader: no</b>
 * <p>
 * <h2>1. Notes:</h2>
 * <p><ol>
 * <li><b>I analysed the related FIs to elaborate an integrated solution if possible.</b>
 * <li><b>Explored the <a href="http://itextsupport.com/apidocs/itext5/latest/">itext5 PDF API</a> capabilities.</b>
 * </ol>
 * <p>
 * <p>
 * <h2>2. Requirements</h2>
 * <p><ul>
 * <li>The following contents should be exported: the source of styles, formulas, comments, images and macros.</li>
 * <li>The user should be given the possibility to select the type of contents to include in the PDF.</li>
 * <li>The PDF includes sections that represent the visual aspect of the exported worksheets
 * (as far as possible in a similar manner to how they are displayed on screen) and also new sections to include
 * the contents that do not appear in the cells like, for instance, macros, comments or images.</li>
 * <li>In each of these sections the contents should make references to the cells that are related to them (if they are related to cells).</li>
 * </ul><p>
 * <i>2.1. SSD</i>
 * <p>
 * <img src="complete_pdf_export_ssd.png" alt="complete pdf export SSD"><p>
 * <p>
 * <p>
 * <h2>3. Analysis</h2>
 * <p>
 * <b>Attention:</b>
 * I will refactor some classes from the FI [Core08.1/.2] to integrate this solution.
 * <p><ul>
 * <li>A <b>PdfExport</b> should only have the responsibility to build the PDF document. [<b>Single Responsibility Principle</b>]</li>
 * <li>To help separate responsibilities we should build the PDF documents using the API approach "Building with blocks", where
 * the author encourages developers to use {@link com.itextpdf.text.Element} objects to create blocks of data and then putting them together to build the PDF.
 * (e.g. {@link com.itextpdf.text.pdf.PdfPCell}, {@link com.itextpdf.text.pdf.PdfPTable}, etc.).</li>
 * <li>A <b>PdfExportableCell</b> should be responsible of parsing a cell and its extensions into a pdf block.</li>
 * <li>A <b>PdfExportableSpreadsheet</b> should be responsible of interpreting a spreadsheet and it's style extension.</li>
 * <li>A <b>PdfExportableWorkbook</b> should be responsible to extract macros and spreadsheets.</li>
 * <li>To prevent creation exposure of a <b>PdfExportableCell</b> we will make a factory class that owns the creation logic
 * (will associate the cell data with a width and height from the spreadsheet). [<b>Factory Pattern</b>]</li>
 * <li>Due to the complex construction of the <b>PdfExport</b> we will use a builder, with a fluent interface, to build "step-by-step" the PdfExport object. [<b>Factory Pattern</b>]</li>
 * <li>This solution will integrate the already in use {@link lapr4.s1.export.ExportStrategy}.</li>
 * <li>To transport the user input options to the PdfExportBuilder we will use a <b>PdfOptionsDTO</b> object, preventing business
 * logic to be transferred to the presentation layer. [<b>DTO Pattern</b>]</li>
 * </ul><p>
 * <p>
 * <h3>3.1. Analysis details</h3>
 * <p>
 * - <b>3.1.1. itext5 pdf API</b><p>
 * <ol>
 * <li>The basic code to make a PDF document is:
 * <pre>
 * {@code
 *
 * OutputStream fos = new FileOutputStream(dest); //"dest" being the output's
 * path
 * PdfWriter writer = new PdfWriter(fos);
 * PdfDocument pdf = new
 * PdfDocument(writer);
 * Document document = new
 * Document(pdf);
 * document.close();
 * }
 * </pre>
 * </li>
 * <li>We will mainly use {@link com.itextpdf.text.pdf.PdfPTable}, {@link com.itextpdf.text.pdf.PdfPCell} & {@link com.itextpdf.text.Anchor} elements. </li>
 * </ol>
 * <p>
 * - <b>3.1.2. PdfExportableCellFactory</b><p>
 * <ol>
 * <li>The spreadsheet will inform the factory of the cells width and height.</li>
 * <li>The factory will receive an array of Cells to create a list of PdfExportableCell.</li>
 * <li>The factory will associate the correct height and width to each <b>PdfExportableCell</b> it creates.</li>
 * </ol>
 * <i>3.1.2.1. PdfExportableCellFactory Diagram</i>
 * <p>
 * <img src="PdfExportableCellFactory_SD.png" alt="PDF Exportable Cell Factory SD.png"><p>
 * <p>
 * - <b>3.1.3. PDFExport Builder</b><p>
 * <ol>
 * <li>The application layer will "tell" this builder class the options the user selected and the builder will know how to build
 * a PdfExport object using these options.</li>
 * <li>This way we can encapsulate the business logic inside the builder and not transferring business logic to the application layer.</li>
 * </ol>
 * <i>3.1.3.1. PDFExport Builder Diagram</i>
 * <p>
 * <img src="PdfExportBuilder_SD.png" alt="Pdf Export Builder SD"><p>
 * <p>
 * <p>
 * <h3>3.2.3. Domain Model (<i>Excerpt</i>)</h3>
 * <p>
 * <img src="complete_pdf_export_dm.png" alt="Complete PDF Export DM"><p>
 * <p>
 * <h3>3.3. Open Questions</h3>
 * <p><ol>
 * <li>The images are not implemented yet, they are being implemented in parallel with this FI, should we include them in PDF Document?
 * <p>
 * <b>A: If possible in the sprint time frame.</b>
 * </ol><p>
 * <p>
 * <p>
 * <h2>4. Tests</h2>
 * <p>
 * <p>
 * <h3>4.1. Unit Tests</h3>
 * <p><ol>
 * <li></li>
 * </ol><p>
 * <h3>4.2. Acceptance Tests</h3>
 * <p><ol>
 * <li></li>
 * </ol>
 * <p>
 * <h3>4.3. Functional Tests</h3>
 * <p><ol>
 * <li></li>
 * </ol>
 * <p>
 * <p>
 * <h2>5. Design</h2>
 * </ol>
 * <p>
 * <h2>6. Integration/Demonstration</h2>
 * <p>
 * <p>
 * <h2>7. Final Remarks</h2>
 * <p>
 * <p>
 * <br>
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday [13/06/2017]</b><p>
 * I worked on:<p>
 * Yesterday
 * <p><ol>
 * <li>Prepare previous sprint demo [IPC area].
 * </ol><p>
 * Today
 * <p><ol>
 * <li>Clarify requirements with product owner.
 * <li>Analise the code base and the related FIs.
 * <li>Study the itext5 pdf API to explore the capabilities.
 * </ol><p>
 * Blocking:
 * <p>
 * -nothing-
 * <p>
 * <p>
 * <b>Wednesday [14/06/2017]</b><p>
 * I worked on:<p>
 * Yesterday
 * <p><ol>
 * <li>Analise the code base and the related FIs.
 * <li>Study the itext5 pdf API to explore the capabilities.
 * </ol><p>
 * Today
 * <p><ol>
 * <li>Clarify requirements with Core Product Owner/Supervisor.
 * <li>Elaborate Analysis.
 * </ol><p>
 * Blocking:
 * <p>
 * -nothing-
 * <p>
 * <b>Thursday [15/06/2017]</b><p>
 * I worked on:<p>
 * Yesterday
 * <p><ol>
 * <li>Clarify requirements with Core Product Owner/Supervisor.
 * <li>Elaborate Analysis.
 * </ol><p>
 * Today
 * <p><ol>
 * <li>Complete Analysis.</li>
 * <li>Elaborate tests.</li>
 * <li>Start Design</li>
 * </ol><p>
 * Blocking:
 * <p>
 * -nothing-
 * <p>
 *
 * @author Daniel Gon&ccedil;alves [1151452@isep.ipp.pt]
 * on 13/06/17.
 */
package lapr4.blue.s3.core.n1151452.pdfexport;