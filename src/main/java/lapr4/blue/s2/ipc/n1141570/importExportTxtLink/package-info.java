/**
 * Technical documentation regarding the user story ICP04.2: Import/Export Text Link.
 * <p>
 * <p>
 * <b>Scrum Master: - no</b>
 *
 * <p>
 * <b>Area Leader: - no</b>
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
 * To do this, I think the best option is whenever the user imports it automatically links to the file and when someone changes
 * the file it will update the spreadsheet linked, only if the user does not want this anymore he can click an option
 * to unlink the import! For the export it would be more or less the same, when the user exports to a file it would be
 * automatically synchronized with the use of a thread so it would not block the cleansheets application. So when the user
 * updates the the spreadsheet it would update the file, when he does not want this to happen he just have to click 
 * in a option that says unlink the export! If he tries to unlink and it is already unlinked it is going to say that he has to export first,
 * and in the case of the import it would say he has to import first.
 * <p>
 * After the analysis on the functional increment, a research was made about threads and  in order to understand better
 * its usage in terms of Java code. With that, the basic code that will be used
 * will be:
 *
 * <pre>
 * {@code
 * Thread thread = new Thread(this);
 * threadId = Thread.currentThread().getId();
 * thread.start();
 * public void run();
 * Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
 * 
 * }
 * </pre>
 * 
 * <p>
 * Regarding the application functionality itself, new actions
 * should be created in ImportExportMenu for this new requirement, with new options
 * "Unlink import" and "Unlink export". If the user
 * press the option to unlink and there is no file linked, it should appear a pop up window telling
 * the user that he should firstly import or export using the functional already done in FI IPC04.1.
 * <p>
 * 
 * In terms of the interaction between the user and the software, it was
 * developed a System Sequence Diagram to aid in its better interpretation.
 * <p>
 * Also, since there will be no new domain concepts, that will not be unit
 * tests.
 * The only test is a functional that is when the user imports and he wants to confirm that is linked
 * when he alters the file it should appear the change in the spreadsheet. And for the export should happens the opposite.
 * <p>
 * <img src="ipc04_02_analysis_ssd.png" alt="image">
 * <p>
 * <p>
 * <img src="ipc04_02_analysis_ssd_1.png" alt="image">
 * <p>
 * 
 * <h2>4. Design</h2>
 * <P>
 * <h3>4.1. Unit Tests</h3>
 * <p>
 * Regarding the unit tests, as this functional increment is a continuation of the FI IPC04.1. it was already done 
 * the unit tests.
 * <p>
 * <h3>4.2. Functional Tests</h3>
 * <p>
 * Regarding the functional tests, a test should be made to ensure that the
 * data added in text file is the same added in the spreadsheet previously linked when was imported the file.
 * And a test should be made to ensure that the data added in the spreadsheet is the same add in the 
 * text file previously linked when was exported.
 * <p>
 * <h3>4.3. UC Realization</h3>
 * Sequence Diagram
 * <p>
 * <img src="ipc04_02_design_export.png" alt="image">
 * <p>
 * <img src="ipc04_02_design_import.png" alt="image">
 * <p>
 * <img src="ipc04_02_design_unlink_export.png" alt="image">
 * <p>
 * <img src="ipc04_02_design_unlink_import.png" alt="image">
 * <p>
 * <h3>4.4. Classes</h3>
 * <h3>4.5. Design Patterns and Best Practices</h3>
 * <p>
 * As we know, we have to link the source with the destination and we want to continue working on in other spreadsheet 
 * of the application cleansheets, we opt to use threads as a best practice.
 * Multithreading benefits a program in various ways:
 * Multithreaded GUI (graphical user interface) based programs remain responsive to users while performing other tasks, 
 * such as for instance, creating macros while importing data from an updated file.
 * Threaded programs typically finish faster than their nonthreaded counterparts. This is especially true of threads running
 * on a multiprocessor machine, where each thread has its own processor.
 * Java accomplishes multithreading through its java.lang.thread class. Each Thread object describes a single thread of execution.
 *
 * <p>
 * <h2>5. Implementation</h2>
 * <p>
 * Files are a common source or destination of data in Java applications. It can be used FileInputStream or a FileReader depending
 * on whether you want to read the file as binary or textual data. If you need to write a file to other destination
 * you can use a FileOutputStream or a FileWriter on whether you need to write binary data or characters.
 * <h2>6. Integration/Demonstration</h2>
 * <p>
 *
 * <h2>7. Final Remarks</h2>
 * <p>
 * There is nothing to say.
 * 
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday 06/06/2017</b>
 * <p>
 * Our team distributed the functionalities to be worked on this
 * sprint.
 * <p>
 * Yesterday: I started the tests and design and finished the analysis process.
 * <p>
 * <b>Thursday 08/06/2017<b>
 * <p>
 * Finished the tests and design.
 * Started the implementation.
 * <p>
 * <b>Friday 09/06/2017<b>
 * <p>
 * Continuing implementation.
 * <p>
 * <b>Saturday 10/06/2017<b>
 * <p>
 * Continuing implementation.
 * <p>
 * <b>Sunday 11/06/2017<b>
 * <p>
 * Today: some updates to implementation and some updates to analysis and design.
 *
 * @author Eric Amaral 1141570@isep.ipp.pt
 */
package lapr4.blue.s2.ipc.n1141570.importExportTxtLink;
