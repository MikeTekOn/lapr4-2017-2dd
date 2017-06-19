/**
 * <p>Technical documentation regarding the user story <b>Core10.1.3: Contacts with tags</b>.</p>
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
 *     expression. The user can press the contact to edit it.
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
 * <p><b>Tag:</b> An alphanumeric word that can be associated to person contacts or company contacts.</p>
 * <p><b>Contactable:</b> The ability of being contactable. All contactables must be Taggables.</p>
 * <p><b>Taggable:</b> The ability of being taggable.</p>
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
 * <br>
 *
 *
 *
 *
 * <h2>4. Tests</h2>
 *
 * <h3>4.1. Unit Tests</h3>
 * <ul>
 *     <li>ensureTagNameIsAlphanumeric</li>
 *     <li>ensureTagNameIsEqualToAnotherTagWithTheSameName</li>
 * </ul>
 *
 * <h3>4.2. Functional Tests</h3>
 *
 * <h4>4.2.1. ensureTagIsAddedToContact</h4>
 * <ol>
 *     <li>
 *         Create a new contact
 *         <ul>
 *             <li>On the tags field, add the tag ISEP</li>
 *             <li>All other fields can be filled with any valid information</li>
 *         </ul>
 *     </li>
 *     <li>
 *         Edit the contact
 *         <ul>
 *             <li>On the editing window, check if the tag ISEP is associated with the contact.</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * <h4>4.2.2. ensureTagsAreFoundWithTagSearch</h4>
 * <ol>
 *     <li>
 *         Create 3 contacts with the names Contact1, Contact2 and Contact3
 *         <ol>
 *             <li>Assign the tag Student2014 to the Contact1</li>
 *             <li>Assign the tag Student2015 to the Contact2</li>
 *             <li>Assign the tag Student2016 to the Contact3</li>
 *         </ol>
 *     </li>
 *     <li>Press the "Search by tag" button</li>
 *     <li>
 *         In the search field enter the text "Student201[46]" and press search
 *         <ul>
 *             <li>Make sure it appears the Contact1 and Contact3 in the results list</li>
 *         </ul>
 *     </li>
 *     <li>
 *         Change the search text by "Student2015" and press the "Search by tag" button again
 *         <ul>
 *             <li>Make sure it appears the Contact2 in the results list</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * <h4>4.2.3. ensureOccurrencesAreUpdated</h4>
 * <ol>
 *     <li>Create one contact with the tags ISEP and DEI</li>
 *     <li>Check in the tags frequency window that both tags appear with one occurrence</li>
 *     <li>Create another contact with the tag ISEP</li>
 *     <li>Check that the tags frequency window that the tag ISEP appear in first place with two occurrences</li>
 *     <li>Remove the tag ISEP from the first contact</li>
 *     <li>Check that the tag frequency for ISEP updated for 1 occurrence</li>
 * </ol>
 * <br>
 *
 *
 *
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Associate tags to contacts</h3>
 * <p>Refer to {@link lapr4.white.s1.core.n4567890.contacts} for adding/editing tags to contacts.</p>
 * <p>The only change is that the CRM user will insert the tag designation.</p>
 *
 * <h3>5.2. Search contacts by tags</h3>
 * <h4>5.2.1. Sequence Diagram</h4>
 * <img src="search_contacts_by_tag_sd.png" alt="Search contacts by tag SD placeholder">
 * <h4>5.2.2. Class Diagram</h4>
 * <img src="search_contacts_by_tag_cd.png" alt="Search contacts by tag CD placeholder">
 * <p>
 *     Since not all database systems have the ability to query the database filtering with a regular expression, this
 *     logic is made in the java side. Also, as the contact and company contact are not the same aggregate (defined in
 *     the previous iterations) we join them in a list of contactables to filter them with a domain service. The tag
 *     domain service was created, because there is not any domain class that naturally matches the functionality
 *     purpose.
 * </p>
 *
 * <h3>5.3. Display tags with occurrences</h3>
 * <h4>5.3.1. Sequence Diagram</h4>
 * <img src="display_tags_with_occurrences_sd.png" alt="Display tags with occurrences SD placeholder">
 * <h4>5.3.2. Class Diagram</h4>
 * <img src="display_tags_with_occurrences_cd.png" alt="Display tags with occurrences CD placeholder">
 * <br>
 *
 * <h3>5.4. Design Patterns</h3>
 * <p>Good practices and domain-driven design approach were the key to succeed in this functional increment.</p>
 * <p>All good practices dictated by SOLID and GRASP was followed, to make the software maintainable.</p>
 * <p>As an example, the repository pattern was used to gather the data of the contacts.</p>
 * <br>
 *
 *
 *
 *
 * <h2>6. Implementation</h2>
 * <p>There is 4 packages in this functional increment, achieving a good layered system:</p>
 * <ul>
 *     <li>
 *         <b>domain:</b> Contains all the domain classes. That is, the classes that really matters for the business.
 *     </li>
 *     <li>
 *         <b>application:</b> The applicational layer, containing the controller to make the bridge between the user
 *         interface (presentation) and the business logic (domain).
 *     </li>
 *     <li>
 *         <b>presentation:</b> The package for user interface related classes. Gathers all the items for the
 *         presentation of the functionality.
 *     </li>
 *     <li>
 *         <b>util:</b> The utilitary package containing utilitary classes with utilitary methods.
 *     </li>
 * </ul>
 * <p>
 *     Since there is a big impact extending the domain classes to add functionalities, some changes were made directly
 *     in the domain classes presented in the packages of the previous core elements. Mainly, the classes Contact and
 *     CompanyContact now have a list of tags and implements the interface Contactable.
 * </p>
 * <br>
 *
 *
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * <p>
 *     It was necessary to integrate this FI with the previous FI of this feature. Some domain changes were made in the
 *     already existing classes, otherwise the impact in the project would be really big.
 * </p>
 * <p>I was very active and I successfully collaborate with the team members.</p>
 * <br>
 *
 *
 *
 *
 * <h2>8. Final Remarks</h2>
 * <p>
 *     I added and extra to the tag regex search functionality, in which we can filter the search with only personal
 *     contacts, only company contact, or both.
 * </p>
 * <p>As a possible future extra for this feature, it should be possible to add tags to events and to notes.</p>
 * <br>
 *
 *
 *
 *
 * <h2>9. Work Log</h2>
 *
 * <h3>Tuesday 13/06/2017</h3>
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Closing the sprint.</li>
 *     <li>Presentation of the IPC functional area.</li>
 *     <li>Analysis of previous core use cases implementations.</li>
 *     <li>Division and brainstorming about common points of the functional increments for this sprint.</li>
 *     <li>Studying the requirements of this use case.</li>
 * </ol>
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Spring review &amp; retrospective.</li>
 *     <li>Brainstorming about what was done in core.</li>
 *     <li>Start the functional increment analysis.</li>
 * </ol>
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 * <br>
 *
 * <h3>Wednesday 14/06/2017</h3>
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Started the functional increment analysis</li>
 * </ol>
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Finish the functional increment analysis</li>
 * </ol>
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 * <br>
 *
 * <h3>Thursday 15/06/2017</h3>
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Finished the functional increment analysis</li>
 * </ol>
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Functional increment tests.</li>
 *     <li>Functional increment design.</li>
 *     <li>Start the implementation.</li>
 * </ol>
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 * <br>
 *
 * <h3>Friday 16/06/2017</h3>
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Tests</li>
 *     <li>Design</li>
 * </ol>
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Implementation</li>
 * </ol>
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 * <br>
 *
 * <h3>Saturday 17/06/2017</h3>
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Implementation</li>
 * </ol>
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Implementation</li>
 *     <li>Helping teammates</li>
 * </ol>
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 * <br>
 *
 * <h3>Sunday 18/06/2017</h3>
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Implementation</li>
 *     <li>Helping teammates</li>
 * </ol>
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Finish Implementation</li>
 *     <li>Make some extras to the use case (filter the search by regex)</li>
 *     <li>Helping teammates</li>
 *     <li>Update design</li>
 * </ol>
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing.</li>
 * </ol>
 * <br>
 *
 *
 *
 *
 * @author Ivo Ferro [1151159]
 */
package lapr4.blue.s3.core.n1151159.contactswithtags;