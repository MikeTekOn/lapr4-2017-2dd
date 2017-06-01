/**
 * Technical documentation regarding the user story Core10.1.1: Contact Edition
 * <p>
 *
 * <h2>1. Notes</h2>
 *
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- yes</b>
 *
 * This is the continuation of what was already done, when the project was recieved, for previous info consult the
 * package white.s1.core.n4567890.contacts
 * <p>
 *
 * <h2>2. Requirement</h2>
 *
 * It will be needed to implement the feature to create, edit and remove events as well as add photograph to contact
 *
 * <h2>3. Analysis</h2>
 *
 * <h3>3.1 User Stories</h3>
 *
 * <b>US1.</b> del>As a CRM User I want to be able to manage Contacts.</del> - Done<br>
 *     <del>Story Acceptance Criteria 1: No contacts name duplicates - Done<br>
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
 * Exception 2: The event description is empty. Repeat the use case.
 *
 * The code that was already done, had the process of adding a new event to a contact inside the same controller that's used in the contact creation process.
 * This "aggregation" of these two different processes (create contact, add event to a contact) is wrong in my opinion, because the user should be able to create events and edit them after creating the contact.
 * The <a href="https://en.wikipedia.org/wiki/Single_responsibility_principle">Single Responsability Principle</a> is then used.
 * Because of this the functionalities should be separated, as I created a new Controller called AddEventController with all the specific information to perform this task.
 * <p>

 * <h3>3.4 Acceptance Tests</h3>
 *
 * <b>Exception 1</b><br>
 * <pre>
 * {@code
 * Given wrong date
 *   "
 * When
 *   CRM User adds event with due date "02-01-2017" or "30-02-2018"
 * Then
 *   System throws Exception
 * }
 *
 *  </pre>
 * <b>Exception 2</b><br>
 * <pre>
 * {@code
 * Given invalid description
 *   "
 * When
 *   CRM User adds event with an empty description
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
 * <h3>More detailed</h3>
 * <p>
 * <img src="core10_01_design1.png" alt="image">
 * <p>
 *
 * <b>Class Diagram</b>
 * <p>
 * <img src="contacts_class_diagram.png" alt="image">
 *
 * <h2>5. Work Log</h2>
 *
 * <p>
 * <b>Day 1 - 30/05/2017</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Use case analysis and requirements
 * <p>
 * Today
 * <p>
 * 1. Continuation of the analysis of the problem and design, as well as some Unit tests implementation
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Day 2 - 31/05/2017</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. UC analysis and design and implementation of unit tests
 * <p>
 * Today
 * <p>
 * 1. UC analysis and design conclusion and start implementation and functional tests
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 *
 * <h2>6. Self Assessment</h2>
 *
 *
 *
 * @author João Cardoso - 1150943
 */
 package lapr4.red.s1.core.n1150943.contacts;