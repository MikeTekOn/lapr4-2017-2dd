/**
 * Technical documentation regarding the user story Core08.1: Basic PDF Export
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 *
 * <h2>2. Requirement</h2>
 * It should be possible to export to PDF an entire workbook, a worksheet or a
 * range of cells. The contents should include only the values of the cells (and
 * not its formulas, for instance). The user should be able to select the
 * content to be exported and also if the document should have a table of
 * contents with links to the sections or not. If select, sections/chapters
 * should be generated for each worksheet of the workbook.
 *
 * <p>
 *
 * <h2>3. Analysis</h2>
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
 * // https://mvnrepository.com/artifact/com.itextpdf/root compile group:
 * 'com.itextpdf', name: 'root', version: '7.0.2'
 * }
 * </pre>
 * <p>
 *
 * After the analysis on the installation guide, a research was made in the
 * Tool's documentation in order to understand better the implementation and the
 * its usage in terms of Java code. With that, the basic code that will be used
 * will be:
 *
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
 *
 * <p>
 * Between lines 4 and 5, the document elements that are going to appear in the
 * final output should be added by using the "add" method of Document.
 * <p>
 * Regarding the application functionality itself, a new Menu (and not an
 * extesion) should be created for this new requirement, with new option "Export
 * to PDF...". Clicking on it, a pop-up window should appear allowing the user
 * to select the "range" of the export: the workbook, a specific worksheet or a
 * range of cells. Independently of the chosen option, a Chooser should appear
 * allowing the user to select the destination path.
 *
 * In terms of the interfaction between the user and the software, it was
 * developed an System Sequence Siagram to aid in its better interpretation.
 * <p>
 * <img src="core08_01_analysis_ssd.png" alt="image">
 * <p>
 * <h2>4. Design</h2>
 * <p>
 *
 * <h3>4.1. Functional Tests</h3>
 * <p>
 *
 * <h3>4.2. UC Realization</h3>
 * <p>
 *
 * <h3>4.3. Classes</h3>
 * <p>
 *
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
 * <p>
 * <b>Tuesday 30/05/2017</b>
 * <p>
 * Yesterday: our team distributed the funcionalities to be worked on this
 * sprint.
 * <p>
 * Today: I started the analysis process.
 * <p>
 * Blocking:---
 * <p>
 * <b>Wednesday 31/05/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Thursday 1/06/2017 </b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Friday 2/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Monday 5/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 *
 * <h2>9. Self Assessment</h2>
 *
 * <h3>R3. Rubric Requirements Fulfilment: </h3>
 *
 * <h3>R6. Rubric Requirements Analysis: </h3>
 *
 * <h3>R7. Rubric Design and Implement: </h3>
 *
 *
 * @author Diogo Santos 1150451@isep.ipp.pt
 */
package lapr4.red.s1.core.n1150451.exportPDF;
