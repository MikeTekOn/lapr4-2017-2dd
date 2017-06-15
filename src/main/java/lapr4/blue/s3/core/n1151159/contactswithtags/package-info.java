/**
 * <p>Technical documentation regarding the user story <b>Core10.1.3: Sharing's Automatic Update</b>.</p>
 *
 * <p><b>Scrum Master: No</b></p>
 * <p><b>Team Leader: Yes</b></p>
 * <br>
 *
 *
 *
 *
 * <h2>1. Notes</h2>
 *
 * <p>This functional increments is related to Core10.1.1 and Core10.1.2</p>
 * <br>
 *
 *
 *
 *
 * <h2>2. Requirements</h2>
 *
 * <p>It should be possible to associate contacts with tags.</p>
 * <p>
 *     A new windows must be created to search contacts based on tags. This search must be performed with a regular
 *     expression.
 * </p>
 * <p>
 *     There should be a window with a list of tags that is automatically sorted (descending) based on the frequency of
 *     the tags utilization.
 * </p>
 * <br>
 *
 *
 *
 *
 * <h2>3. Analysis</h2>
 *
 * <br>
 * <h3>3.1. Open Questions</h3>
 * <p>Meeting with the product owner at <b>13/06/2017</b>:</p>
 * <br>
 * <p><b>Q:</b> How many tags can a contact have?</p>
 * <p><b>A:</b> A contact can have many tags.</p>
 * <br>
 * <br>
 * <p><b>Q:</b> Where and when do we associate tags?</p>
 * <p><b>A:</b> When the user is being created or edited.</p>
 * <br>
 * <p><b>Q:</b> Where is the search window located at?</p>
 * <p>
 *     <b>A:</b> There should be a search button in the same side bar that opens a dialog, since there is a lot of side
 *     bars already.
 * </p>
 * <br>
 * <p><b>Q:</b> Where is the sorted window to be presented?</p>
 * <p><b>A:</b> Anywhere, as long as we can visualize the contacts simultaneous.</p>
 * <br>
 *
 * <h3>3.2. Glossary</h3>
 * <p>Tag: An alphanumeric word that can be associated to person contacts or company contacts.</p>
 * <br>
 *
 * <h3>3.3. Domain Model</h3>
 * <p>The new concept of tags is going to be contained on both aggregates.</p>
 * <p>It is a value object since it doesn't have it's own identity and we don't care about it's life cycle.</p>
 * <p>
 *     Also, in this business, there is no relation between the tags. A tag with the same name can be used in different
 *     contexts.
 * </p>
 * <img src="domain_model.png" alt="Domain model placeholder.">
 * <br>
 *
 * <h3>3.4. Division</h3>
 * <p>This use case can be divided into small functionalities:</p>
 * <ul>
 *     <li>Associate tags to contacts when creating/editing a contact;</li>
 *     <li>Search contacts based on tags using a regular expression. A contact can be edited by clicking;</li>
 *     <li>Display tags with occurrences sorting by the occurrences in descending order.</li>
 * </ul>
 *
 * <h3>3.4.1. Associate tags with contacts</h3>
 * <img src="associate_tags_with_contacts_ssd.png" alt="Associate tags with contacts placeholder.">
 *
 * <h3>3.4.2. Search contacts by tag</h3>
 * <img src="search_contacts_by_tag_ssd.png" alt="Search contacts by tag placeholder.">
 *
 * <h3>3.4.3. Display tags with occurrences</h3>
 * <img src="display_tags_with_occurrences_ssd.png" alt="Display tags with occurrences placeholder.">
 *
 *
 * <h2>9. Work Log</h2>
 *
 * <h3>Tuesday 13/06/2017</h3>
 *
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Closing the sprint</li>
 *     <li>Presentation of the IPC functional area</li>
 *     <li>Analysis of previous core use cases implementations.</li>
 *     <li>Division and brainstorming about common points of the functional increments for this sprint.</li>
 *     <li>Studying the requirements of this use case.</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Functional increment analysis.</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 *
 *
 *
 *
 * @author Ivo Ferro [1151159]
 */
package lapr4.blue.s3.core.n1151159.contactswithtags;