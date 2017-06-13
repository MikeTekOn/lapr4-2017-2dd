/**
 * Technical documentation regarding the user story Core01.2- Auto-description of Extensions.
 * <p>
 * <b>Scrum Master: - no</b>
 * </p>
 * <p>
 * <b>Area Leader: - no</b>
 * </p>
 *
 * <h2>1. Requirement</h2>
 * <ul>
 * <li>The extensions should have metadata associated.</li>
 * <li>The extensions should have a version number, a name and a description.</li>
 * <li>Before loading the extensions, the cleansheet should display to the user the metadata.</li>
 * <li>Cancel the loading of and extension should be an option.</li>
 * <li>If there's more than one version of the extension, it should be possible to download the extension selected version.</li>
 * </ul>
 *
 * <h2>2. Analysis</h2>
 * <b>Use Case "Auto-description of Extensions":</b>
 * <p>
 * When the user starts the cleansheet, an window will display the existing extensions. The extension will have (now) a metadata associated and a version, name and description.<p>
 * The user will select which extensions he want do load and one version of it (if there are more than one).
 * </p>
 * <p>
 * After understanding how the loading of extensions properties work and the extension it self, the ideia would be
 * loading the properties of the extensions first, show to the user the information and after he chooses what extensions he want or not, 
 * the extensions are loaded.
 * </p>
 * <p>
 * <img src="auto_description_extensions_analysis.png" alt="image">
 * </p>
 * 
 * <p>
 * Some details were noticed. The loading of extensions are a user choice, but some are dependent of other. 
 * The Chat extension {@link lapr4.green.s1.ipc.n1150657.chat.ext.ChatExtension} and the search work network
 * {@link lapr4.red.s1.core.n1150613.workbookSearch.SearchExtension} (the extensions that i'm aware of) need the communication extension to work,
 * so when it selects that extension, the network is selected automatically.
 * </p>
 * <p>
 * The metadata requested is represented in a String. All extensions had one, and when the call for the specific Extension is call
 * in the ExtensionFactory, the respective metadata is returned so the user can see the specific data for each extension.
 * </p>
 * <p>
 * One import thing to mention is how the versions are made. The first verion of the extension is created and is extended by the abstract class Extension.
 * When a extension is modified and created a new one, this last will extend the previous extension modifying only the version (remaining the name and description).
 * That means that are diferent versions for extensions that have the same name.
 * </p>
 * <b>2.1 Questions</b>
 * 1. The loading of the extensions should be before the Cleansheet running or during?<p>
 * <b><i>Assumptions: </i></b>Before.
 * <p>
 * <b><i>Answer:</i></b>
 * <p>
 *
 * <h2>3. Tests</h2><p>
 * This should include the functional tests, applying Test-Driven
 * Development, and unit tests.
 * <p>
 * </p>
 * <h3>3.1 Unit Tests:</h3>
 * There are 2 unit tests:
 * <p>
 * <ul>
 * <li>ExtensionFactoryTest</li>
 * <li>ExtensionLoadTest</li>
 * </ul>
 * <p>
 *
 * <h2>4. Design</h2>
 * After the cleanshet starts, instead of loading the extension properties and loading the extensions into the the app,
 * he creates a new frame with the extensions the user wants, only then is it loaded.
 *  
 * <p>
 * <img src="auto_description_extensions_design1.png" alt="image">
 * </p>
 * 
 * <p>
 * <img src="auto_description_extensions_design2.png" alt="image">
 * </p>
 *
 * <h2>5. Code</h2>
 * 
 */
package lapr4.green.s2.core.n1150657.extensions;
