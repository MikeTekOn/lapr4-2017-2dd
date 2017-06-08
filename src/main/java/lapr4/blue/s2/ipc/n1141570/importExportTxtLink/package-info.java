/**
 * Technical documentation regarding the user story ICP04.2: Import/Export Text Link.
 * <p>
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
 * The process of creating a link is simular to the one described in IPC04.1 but the import or export should
 * be always active (until it is removed by the user). Being active means that the process will be repeated
 * automatically when the source of the data is updated. This should happen for imports and exports.
 * <p>
 *
 * <h2>3. Analysis</h2>
 * <p>
 * Since Import/Export Text Link will be supported in a new extension to cleansheets we
 * need to study how extensions are loaded by cleansheets and how they work. The
 * first sequence diagram in the section
 * <a href="../../../../overview-summary.html#arranque_da_aplicacao">Application
 * Startup</a> tells us that extensions must be subclasses of the Extension
 * abstract class and need to be registered in special files. The Extension
 * class has a method called getUIExtension that should be implemented and
 * return an instance of a class that is a subclass of UIExtension. In this
 * subclass of UIExtension there is a method (getMenu) that returns the JMenu
 * for the extension.
 * 
 * <p>
 * As we want to optimize as much as possible the process of importing/exporting to txt
 * file, we were asked to do an extra feature that is supposed to update the file or the spreadsheet selected.
 * To do this, we think the best option is to whenever the user updates the spreadsheet it should create a new thread 
 * and update the linked file, and if the file is updated and it is still linked,
 * the spreadsheet linked to the file should be updated creating a new thread so it cannot block the clean sheets.
 * This process will be repeated automatically when the source of the data is updated, in the case of the txt file to spreadsheet when
 * the file saved date is different than the previous one,
 * unless the user unchecks the "Link" checkbox option.
 *
 * <p>
 * After the analysis on the functional increment, a research was made about threads and  in order to understand better
 * its usage in terms of Java code. With that, the basic code that will be used
 * will be:
 *
 * <pre>
 * {@code
 * Thread thread = new Thread();
 * Thread newThread = new Thread(MyRunnable());
 * public void run();
 * public void start();
 * }
 * </pre>
 * 
 * <p>
 * Regarding the application functionality itself, a new check box
 * should be created in ImportExportMenu for this new requirement, with new option
 * "Link". It should appear as checked because it is supposed that this feature is always active (as 
 * it was decided after discussing with the client). If the user
 * deactivate it and he wants to activate it again, it should appear a pop up window telling
 * the user that he should firstly import or export using the functional already done in IPC04.1.
 * <p>
 * 
 * In terms of the interaction between the user and the software, it was
 * developed a System Sequence Diagram to aid in its better interpretation.
 * <p>
 * Also, since there will be no new domain concepts, that will not be unit
 * tests.
 * <p>
 * <img src="core08_01_analysis_ssd.png" alt="image">
 * <p>
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday 06/06/2017</b>
 * <p>
 * Yesterday: our team distributed the funcionalities to be worked on this
 * sprint.
 * <p>
 * Today: I started and finished the analysis process.
 * <p>
 * Blocking:---
 * <p>
 
 * <h2>9. Self Assessment</h2>
 *
 * <h3>R3. Rubric Requirements Fulfilment: </h3>
 *
 * <h3>R6. Rubric Requirements Analysis: </h3>
 *
 * <h3>R7. Rubric Design and Implement: </h3>
 *
 *
 * @author Eric Amaral 1141570@isep.ipp.pt
 */
package lapr4.blue.s2.ipc.n1141570.importExportTxtLink;
