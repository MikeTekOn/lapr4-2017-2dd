/**
 * Technical documentation regarding the user story Core10.1.1: Contact Edition
 * <p>
 *
 * <h2>1. Notes</h2>
 *
 * This is the continuation of what was already done, when the project was recieved, for previous info consult the
 * package white.s1.core.n4567890.contacts
 * <p>
 *
 * <h2>2. Requirement</h2>
 *
 * There are no new requirements from the previous information, it will be needed to implement the feature to create, edit and remove events and
 *
 * <h2>3. Analysis</h2>
 *
 * <h3>3.1 User Stories</h3>
 *
 * <b>US1.</b> del>As a CRM User I want to be able to manage Contacts.</del> - Done<br>
 *     <del>Story Acceptance Criteria 1: No contact name duplicates - Done<br>
 *     Story Acceptance Criteria 2: No deletion of contacts with events </del>- Done<br>
 * <b>US2.</b> As a CRM User I want to add events to the agenda of Contacts.<br>
 * <b>US3.</b> As a CRM User I want to be notified when the due date of an event occurs.<br>
 *
 * <h3>3.2 Domain Model</h3>
 * <p>
 * <img src="contacts_domain_model.png" alt="image">
 *
 * <h3>3.3 Use Cases (Scenarios)</h3>
 *
 * <b>UC1.1 Create event (from US1)</b>
 *
 * Alternative and Exception Scenarios<br>
 * Exception 1: The specified due date is in the past. Repeat use case.
 *
 * <h3>3.4 Acceptance Tests</h3>
 *
 * <b>Exception 1</b><br>
 * <pre>
 * {@code
 * Given
 *   Contact with Name "John Doe"
 * When
 *   CRM User adds Contact with Name "John Doe"
 * Then
 *   System throws Exception
 * }
 * </pre>
 *
 * <h2>4. Design</h2>
 *
 * <b>Sequence Diagrams</b><p>
 * <p>
 * <img src="uc_create_event.png" alt="image">
 * <p>
 *
 * <b>Class Diagram</b>
 * <p>
 * <img src="contacts_class_diagram.png" alt="image">
 *
 * @author João Cardoso - 1150943
 */
 package lapr4.red.s1.core.n1150943.contacts;