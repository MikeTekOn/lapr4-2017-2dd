/**
 * Technical documentation regarding the user story IPC02.1:  Find Workbooks. 
 * <p>
 * 
 * <b>Attention: This feature increment and this documentation are work in progress! You should question what is already done!</b><p>
 *
 * <b>Requirement</b><p>
 * Statement:<p>
 * The extension should add a new window (sidebar) to search for workbook files in the local file system.
 * The user should be able to enter the name of a directory of the file system to be used as the root of the search.
 * The search should include this directory and all its contents (including subdirectories).
 * The results of the search should appear in a list (as files are found).
 * It should be possible for the user to open a workbook from this list by double clicking in it
 * The search can be based solely on the file name extension. For instance, find the files with .cls extension.
 * Notes:<p>
 * After a discussion with the client he clarified that the file name must be a path
 * 
 *  
 * <b>Analysis</b><p>
 * 
 * <b>System Sequence Diagram (SSD)</b><p>
 * 
 * <img src="us02.1_ssd.png" alt="image"> <p>
 * 
 * <b>Notes:</b><p>
 * Class Directory: This class is responsible for validate and save the directory entered by the user. This class also has implemented a search and load  method for cls files that will execute on a diferent thread to no block the UI.<p>
 * Class UIFindWorkbooksExtension: This class is responsible to build the side bar that the user will be able to use and choose a workbook to open.This class will be also a observer to know when a file was found and update the main window <p>
 * Class ControllerFindWorkbooks: This class is responsible for controlling the flow of the use case.This controller also has methods to control the thread created by the search method<p>
 * Class FindWorkbooksPublisher: This class is a singleton that has the function to warn all the observers that a new cls file was found. <p>
 * 
 * <b>Rules:</b><p>
 * A Directory should have a valid directory <p>
 * 
 * <b>Open Questions:</b><p>
 * Should the directory still searching for workbooks after one is choose?
 * Assumption: YES<p>
 * 
 * 
 * <b>Tests</b><p>
 *
 * <b>UnitTest:</b> ensureDirectoryPathIsValid<p>
 * The directory only should be created with a valid path. Expect a exception<p>
 * 
 * <b>Test2:</b> ShareCellTest<p>
 * Test the initial connection regarding the sharing of the contents of a range of cells. Should we move/refactor this test to Acceptance Test?<p>
 * See Package lapr4.black.s1.ipc.n2345678.comm.sharecells:<p>
 * ShareCellsTest<p>
 * 
 * 
 * 
 * <b>Design</b><p>
 * First draft regarding the design.<p>
 * Will start by illustrating a scenario regarding the use case flow.<p>
 * 
 * <img src="design_01.png" alt="image"> 
 * <p>
 * 
 *Second SD illustrates the behavior of the UIFindWorkbooksExtension as observer.<p>
 * 
 * <img src="design_02.png" alt="image"> 
 * <p>
 *  
 * <b>Code</b><p>
 * The following classes and interfaces implement this use case.<p>
 * Package lapr4.green.s1.ipc.n1150838.findworkbooks:<p>
 * {@link lapr4.green.s1.ipc.n1150838.findworkbooks.Directory}
 * {@link lapr4.green.s1.ipc.n1150838.findworkbooks.FileDTO}
 * {@link lapr4.green.s1.ipc.n1150838.findworkbooks.FindWorkbooksPublisher}
 * <p>
 * Package lapr4.green.s1.ipc.n1150838.findworkbooks.ctrl:<p>
 * {@link lapr4.green.s1.ipc.n1150838.findworkbooks.ctrl.ControllerFindWorkbooks}
 * <p>
 * Package lapr4.green.s1.ipc.n1150838.findworkbooks.ext:<p>
 * {@link lapr4.green.s1.ipc.n1150838.findworkbooks.ext.ExtensionFindWorkbooks}
 * <p>
 *  * Package lapr4.green.s1.ipc.n1150838.findworkbooks.ui:<p>
 * {@link lapr4.green.s1.ipc.n1150838.findworkbooks.ui.FindWorkbooksSideBar}
 * {@link lapr4.green.s1.ipc.n1150838.findworkbooks.ui.UIFindWorkbooksExtension}
 * {@link lapr4.green.s1.ipc.n1150838.findworkbooks.ui.WorkbookList}
 * <p>
 * 
 * 
 * @author 1150838 Nuno Pinto
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks;

