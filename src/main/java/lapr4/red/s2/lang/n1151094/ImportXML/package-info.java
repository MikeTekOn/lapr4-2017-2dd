/**
 * Technical documentation regarding the user story Lang08.2 - Import XML.
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
 * It should be possible to import data from an XML file (this operation is the "inverse" of the previous FI).
 * <p>
 * Depending on the contents of the XML file, the data from the file can replace the contents of a workbook, a worksheet or a range of a worksheet.
 * <p>
 * This option should appear in the File menu.
 *
 * <p>
 * <b>Use Case "Import XML":</b>
 *<ul>
 *      <li>The user starts XML file import. </li>
 *      <li>The system opens a window for the user to choose the file.</li>
 *      <li>The user chooses the file.</li>
 *      <li>The system validates the file extension(xml).</li>
 *      <li>The user confirms the file extension.</li>
 *      <li>The system imports XML and shows a success message.</li>
 * </ul>
 *
 *
 * <h2>3. Analysis</h2>
 *
 * In this second sprint I started by analysing the Export XML Use case.
 * <p>
 * Verified that when the spreadsheet is exported to an XML file, only the value of the cells is saved, not their formatting style or formulas.
 * <p>
 * This problem needs to be fixed and then implemented in the XML importation too.
 * <p>
 * I decided to use the Strategy Pattern because in the future there may be importations and exportations to different file formats.
 * <p>
 * To implement this pattern I will create three new classes:
 * <ol>
 *      <li> ImportStrategy that's an interface to define the type of file importation ; </li>
 *      <li> ImportContext that contains a reference to an ImportStrategy instance; </li>
 *      <li> ImportXML that implements the <b>ImportStrategy</b> interface so it will implement the method importData, declared in the interface. </li>
 * </ol>
 *  By using this pattern it is avoided code duplication and also it makes the app not dependent of a specific class.
 * <p>
 * 
 * I will use native Java libraries for the XML importation, the base code is the following:
 *
 * <pre>
 * {@code
 *
 *  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); -
 *  DocumentBuilder db = dbf.newDocumentBuilder();
 *  Document doc =db.parse(file);
 *  Element n = doc.getDocumentElement();
 *  NodeList nl = n.getChildNodes(); 
 *  Node an, an2;
 * }
 * </pre>
 * <b>For more information about this pattern visit the following website :</b> https://www.tutorialspoint.com/design_pattern/strategy_pattern.h
 *
 * <h2>4. Design</h2>
 *
 * <h3><b>Sequence Diagrams</b></h3>
 * <p>
 * <h3>Short Sequence Diagram</h3>
 * <img src="ssd.png" alt="image">
 * <p>
 * 
 * <p>
 * <h3>Detailed Sequence Diagram</h3>
 * <p>
 *
 * <p>
 * 
 * <b>Class Diagram</b>
 * 
 * <p>
 * 
 * <h2>8. Work Log</h2>
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. nothing
 * <p>
 * Today
 * <p>
 *
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
 *
 * <p>
 * Today
 * <p>
 *
 * <p>
 * Blocking:
 * <p>
 * 1. nothing
 * <p>
 *
 * @author Eduangelo Ferreira 1151094
 */
package lapr4.red.s2.lang.n1151094.ImportXML;
