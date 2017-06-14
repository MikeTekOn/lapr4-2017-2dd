/**
 * Technical documentation regarding the user story Core06.1: Insert Image.
 * 
 * <p>
 * <b>Scrum Master: - no</b>
 *
 * <p>
 * <b>Area Leader: - no</b>
 *
 * <h2>2. Requirement</h2>
 * The extension should include an option to insert an image. The inserted image should become associated
 * with the active/selected cell. It should also exist a new sidebar window to display the images that are
 * associated with the current cell (in a manner similar to how comments work). A cell can have several
 * associated images. The sidebar should have an option (button) to remove/delete images. The workbook
 * should only save links to the files that contain the images.
 * 
 * <p>
 * <b>Use Case "Insert Image":</b> The user selects the cell where he/she wants to enter an image. The system displays the current images of that cell.
 * The user inserts the image (or removes an existing one). The system saves the image of the cell.
 * 
 *  
 * <h2>3. Analysis </h2>
 * Since the images on cells will be supported in a new extension to cleansheets we need to study how extensions are loaded by cleansheets and how they work.
 * The first sequence diagram in the section <a href="../../../../overview-summary.html#arranque_da_aplicacao">Application Startup</a> tells us that extensions must be subclasses of the Extension abstract class and need to be registered in special files.
 * The Extension class has a method called getUIExtension that should be implemented and return an instance of a class that is a subclass of UIExtension.
 * In this subclass of UIExtension there is a method (getSideBar) that returns the sidebar for the extension. A sidebar is a JPanel.
 * 
 * <p>
 *  <h3>First "analysis" sequence diagram</h3>
 * <p>
 * <b>Use Case "Insert Image":</b>
 * The user selects a cell. The system displays a sidebar window with images associated to that cell.
 * The user adds/removes an image to that cell. The cell images will update accordingly to the change made.
 * <p>
 * <img src="core06_01_analysis_ssd.png" alt="image">
 * 
 * <p>
 * From the previous diagram we see that we need to add a new "attribute" to a cell: "image".
 * Therefore, at this point, we need to study how to add this new attribute to the class/interface "cell". This is the core technical problem regarding this issue.
 * <h3>Analysis of Core Technical Problem</h3>
 * We can see a class diagram of the domain model of the application <a href="../../../../overview-summary.html#modelo_de_dominio">here</a>
 * From the domain model we see that there is a Cell interface. This defines the interface of the cells. We also see that there is a class CellImpl that must implement the Cell interface.
 * If we open the {@link csheets.core.Cell} code we see that the interface is defined as: <code>public interface Cell extends Comparable &lt;Cell&gt;, Extensible&lt;Cell&gt;, Serializable</code>. Because of the <code>Extensible</code> it seams that a cell can be extended.
 * If we further investigate the hierarchy of {@link csheets.core.Cell} we see that it has a subclass {@link csheets.ext.CellExtension} which has a subclass {@link csheets.ext.style.StylableCell}. {@link csheets.ext.style.StylableCell} seems to be an example of how to extend cells.
 * Therefore, we will assume that it is possible to extend cells and start to implement tests for this use case. 
 * <p>
 * The <a href="http://en.wikipedia.org/wiki/Delegation_pattern">delegation design pattern</a> is used in the cell extension mechanism of cleansheets. The following class diagram depicts the relations between classes in the "Cell" hierarchy.
 * <p>
 * <img src="core02_01_analysis_cell_delegate.png" alt="image"> 
 * 
 * <p>
 * One important aspect is how extensions are dynamically created and returned. The <code>Extensible</code> interface has only one method, <code>getExtension</code>. Any class, to be extensible, must return a specific extension by its name. The default (and base) implementation for the <code>Cell</code> interface, the class <code>CellImpl</code>, implements the method in the following manner:
 * <pre>
 * {@code 
 * 	public Cell getExtension(String name) {
 *		// Looks for an existing cell extension
 *		CellExtension extension = extensions.get(name);
 *		if (extension == null) {
 *			// Creates a new cell extension
 *			Extension x = ExtensionManager.getInstance().getExtension(name);
 *			if (x != null) {
 *				extension = x.extend(this);
 *				if (extension != null)
 *					extensions.put(name, extension);
 *			}
 *		}
 *		return extension;
 *	}
 * }
 * </pre>
 * As we can see from the code, if we are requesting a extension that is not already present in the cell, it is applied at the moment and then returned.
 * The extension class (that implements the <code>Extension</code> interface) what will do is to create a new instance of its cell extension class (this will be the <b>delegator</b> in the pattern).
 * The constructor receives the instance of the cell to extend (the <b>delegate</b> in the pattern). For instance, <code>StylableCell</code> (the delegator) will delegate to <code>CellImpl</code> all the method invocations regarding methods of the <code>Cell</code> interface.
 * Obviously, methods specific to <code>StylableCell</code> must be implemented by it.
 * Therefore, to implement a cell that can have images associates we need to implement a class similar to <code>StylableCell</code>, the <code>ImageableCell</code> for instance.
 * 
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday 13/06/2017</b>
 * <p>
 * Our team distributed the functionalities to be worked on this
 * sprint and I started and finished the analysis.
 * <p>
 *
 * @author Eric Amaral 1141570@isep.ipp.pt
 */
package lapr4.blue.s3.core.n1141570.insertImage;
