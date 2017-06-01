/**
 * Technical documentation regarding the user story LANG08.1: Comments on Cells.
 *
 * <p>
 * <b>-Note: this is a template/example of the individual documentation that
 * each team member must produce each sprint. Suggestions on how to build this
 * documentation will appear between '-' like this one. You should remove these
 * suggestions in your own technical documentation. You may also define a
 * different template for your team to use with the agreement of your
 * supervisor-</b>
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>2. Requirement</h2>
 * It should be possible to export the contents of an workbook, worksheet or
 * part of an worksheet to an XML file. As we want to optimize as much as
 * possible the process the solution should not rely on any third party library.
 * Cleansheets should have a window to configure the XML tags to use for each
 * type of element. The export should only include the value of the cells. The
 * export menu option should appear in the File menu.
 *
 * <p>
 * <b>Use Case "Export XML":</b> The user selects the cell/s where he/she wants
 * to export or if no cell selected it will ask if she/he wants to export the
 * current worksheet selected, or if he/she wants to export the workbook. The
 * system displays where the user wants to save the xml file.
 *
 *
 * <h2>3. Analysis</h2>
 *
 * Since export to xml will be supported in a new extension to cleansheets we
 * need to study how extensions are loaded by cleansheets and how they work. The
 * first sequence diagram in the section
 * <a href="../../../../overview-summary.html#arranque_da_aplicacao">Application
 * Startup</a> tells us that extensions must be subclasses of the Extension
 * abstract class and need to be registered in special files. The Extension
 * class has a method called getUIExtension that should be implemented and
 * return an instance of a class that is a subclass of UIExtension. In this
 * subclass of UIExtension there is a method (getMenu) that returns the JMenu
 * for the extension.
 * <p>
 * As we want to optimize as much as possible the process of exporting to a xml
 * file, the solution should not rely on any third party library so we
 * discovered that Java supports 4 methods to parse XML out of the box: DOM
 * Parser/Builder, SAX Parser, StAx Reader/Writer, JAXB. We choose to use Java
 * Dom Parser as DOM provides many handy classes to create XML file easily.
 * Firstly we have to create a Document with DocumentBuilder class, define all
 * the XML content(node), attribute with Element class. In last we use
 * Transformer class to output the XML content to stream output as a file.
 *
 * <p>
 * After this previous analysis, we have discovered that is commonly used the
 * following code.
 * <pre>
 * {@code
 * DocumentBuilderFactory documentFactory;
 * DocumentBuilder documentBuilder;
 * Document doc;
 * Transformer transformer;
 * DomSource source = new DOMSource(doc);
 * StreamResult result = new StreamResult();
 * transformer.transform(sourcem result);
 * }
 * </pre>
 * <p>
 * Regarding the application functionality itself, a new extension should be
 * created in the File menu, with new option "Export to XML...". Clicking on it,
 * a pop-up window should appear allowing the user to select the "range" of the
 * export: the workbook, a specific worksheet or a range of cells. Independently
 * of the chosen option, a Chooser should appear allowing the user to select the
 * destination path.
 * <p>
 * Taking into consideration that the output is going to be about the same data
 * in the feature increment Core08.1 in the CORE area, and it is a good practice
 * to reduce the code duplicates, a pattern should be used to avoid it.
 *
 * <p>
 * About the interaction between the user and the software, it was developed a
 * System Sequence Diagram to support the interpretation.
 *
 * <p>
 * <img src="lang08_01_analysis_ssd.png" alt="image">
 * <p>
 * * <h2>4. Design</h2>
 * <p>
 *
 * <h3>4.1. Functional Tests</h3>
 * <p>
 * We didn't do any tests to the domain as this functionality does not need to
 * alter the domain code.
 * <p>
 * As it's initiated the functionality, the user chooses the range of cells
 * desired and the xml output should be the same has chosen.
 *
 * <h3>4.2. UC Realization</h3>
 * <p>
 * <img src="lang08_01_design_sd.png" alt="image">
 * <p>
 * <h3>4.3. Classes</h3>
 * <p>
 * <img src="lang08_01_design_class_diagram.png" alt="image">
 * <p>
 *
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * <p>
 * As mentioned in the Analysis section, a pattern should be used to avoid code
 * duplication between this functionality and the Pdf Exportation. So, together
 * with the Core08.1 responsible it was decided that a pattern developed by the
 * Gang of Four (GoF) was going to be used for this purpose, the <b>Strategy
 * Pattern</b>. In the Strategy Pattern, a class or an algorithm, can change
 * behaviour during the runtime of the software, being a good choice in this
 * situation, since the data to be exported is the same in both PDF and XML
 * exportation functionalities. To implement this pattern, the following "base
 * diagram" was used:
 * <p>
 * <img src="lang08_01_design_basePattern.png" alt="image">
 * <p>
 *
 * Considering the Strategy pattern:
 * <p>
 * <img src="lang08_01_design_pattern.png" alt="image">
 * <p>
 *
 * <p>
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
 * Our team distributed the functionalities to be worked on this sprint.
 * <p>
 * Today: I started the analysis process.
 * <p>
 * Blocking:---
 * <p>
 * <b>Wednesday 31/05/2017</b>
 * <p>
 * Continuing the analysis
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
 * @author eric amaral 1141570@isep.ipp.pt
 */
package lapr4.blue.s1.lang.n1141570.XML;
