/**
 * Technical documentation regarding the user story Core01.1: Enable and Disable Extensions.
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 *
 * <h2>2. Requirement</h2>
 * It should exist a new window that allows to enable and disable extensions of Cleansheets. A disabled extension means that 'all' its functionalities are disabled.
 *
 * <p>
 * <b>Use Case "Enable/Disable Extension":</b> The user selects in the <b>Extensions->Enable/Disable Extensions</b> menu and a window appears that shows a table
 * with every extension available listed in one column, and in the other column, whether or not it is active. The user selects one or more extensions and clicks
 * the <b>activate</b> or the <b>deactivate</b> button. The selected extensions are then activated or deactivated, depending on which button was pressed. The user
 * may close the window or repeat the use-case.
 *
 *
 * <h2>3. Analysis</h2>
 * In order to activate and deactivate extensions, we need to understand how the extensions are loaded by cleansheets and how they work. Since, for some
 * reason, I can't see the sequence diagrams in the section <b>Application Startup</b>, I'll look at the source code for this. Looking at the CleanSheets.java
 * file, we can see in the constructor that it loads all the extensions by calling the <i>ExtensionManager.getInstance()</i>. Studying the ExtensionManager
 * class, I see it loads the extensions in the constructor (therefore only once when cleansheets starts) using  a special file where the extensions are registered.
 *
 * Also, by looking at the UIController class, we can see that it loads every extension using <i>ExtensionManager.getInstance()</i> and then those extensions
 * will be available to anyone interested in them via the <i>getExtensions()</i> method. My proposed solution up to now is to have a list of EventListeners
 * where every class that needs this extensions will register, and whenever this list of extensions is updated, all of this classes will be notified in order
 * for them to update themselves.
 *
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the previously described use case. We call this diagram an "analysis" use case realization
 * because it functions like a draft that we can do during analysis or early design in order to get a previous approach to the design. For that reason we mark
 * the elements of the diagram with the stereotype "analysis" that states that the element is not a design element and, therefore, does not exists as such in the
 * code of the application (at least at the moment that this diagram was created).
 *
 * <p>
 * <img src="comments_extension_uc_realization1.png" alt="image">
 * <p>
 *
 * As we can see on the diagram, every component that needs these extensions will need to register themselves as Listeners of the UIController and act accordingly
 * when a change in the list is made. Of course, if an extension is already active, we don't want to just erase it and add it again, for it may have information
 * regarding the current state of something. Therefore, two different events will be sent to the listeners. One which states that an extension was enabled, and another
 * that it was disabled.
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
 * 1. Analysis of the extension mechanism
 * <p>
 * Blocking:
 * <p>
 * 1. nothing
 * <p>
 */
package lapr4.red.s1.core.n1150385.activateext;
