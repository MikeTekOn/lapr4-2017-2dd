/**
 * Technical documentation regarding the user story Core10.4.1- Notes Edition.
 * <p>
 * JIRA issue: LAPR4E17DD-39
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
 * As there were no use cases to be continued, it was defined that I would start this on Sprint3.
 *
 * <h2>2. Requirement</h2>
 * <p>
 * It should be possible to create, edit and remove text notes associated with contacts.
 * Each contact can have one or more notes.
 * There should be a sidebar to list the textual notes of a contact.
 * A text note should be entered as multiline text in which the first line is interpreted as the title of the text note.
 * The timestamp of the creation of the note should be also associated with the text note.
 * Cleansheets should maintain the history of modifications for each text note.
 * When a text note is selected Cleansheets should display its history.
 * For each version, Cleansheets should display the timestamp and the first line (i.e., the title).
 *
 *
 * <h2>3. Analysis</h2>
 *
 * <p>
 * From what was asked in the statement and confirmed with the product onwer, I verified that a 
 * contact would have one or more notes and that these would only make sense if there were, and only if, the contact also existed.
 * Therefore, I chose to incorporate the notes into the existing "Contact" aggregate.
 * <p>
 * Another of the conclusions was that each note could be edited, but in this case the term editable means creating a new version of the note.
 * If the issue of the versions did not exist, I would opt for a ValueObject, but as we have to stay with the history I chose to create "Note" as an entity.
 * For the contents of the "Note" - "NoteContent" - I define as value object because it will be immutable.
 * <p>
 * Another conclusion was on the removal of notes. These would also have to be recorded in the database.
 * I would define that it would have a boolean attribute to differentiate the "removed" status from those that are active.
 *
 * <p>
 * Questions / Answers:
 * <ol>
 * <li> The notes will be used for another purpose or, only and only, for the contacts?? Only contacts and don´t make sense exist without contacts.</li>
 * <li> Remove note mean to delete from database? No, it will be needed to persist the history.</li>
 * <li> When editing a note, is a new version created? Yes. </li>
 * </ol>
 *
 * <h3>Short Sequence Diagram:</h3>
 * <p>
 * <img src=".png" alt="image">
 *
 * <ol>
 * <li> The user  </li>
 * <li> The system  </li>
 * <li> The user  </li>
 * <li> The system confirm submission and reports operation success </li>
 * </ol>
 *
 *
 * <h2>4. Design</h2>
 *
 * <h3>4.1. Domain Tests</h3>
 *
 * <p>
 * The unit test implemented that are necessary to check all domain concepts
 * are:
 * <p>
 * NoteContent:
 * <ol>
 * <li> ensureNoteContentOk </li>
 * <li> ensureContentIsNotNull </li>
 * <li> ensureContentIsNotEmpty </li>
 * </ol>
 * 
 * Note:
 * <ol>
 * <li> ensureAddNoteContentIsOk </li>
 * <li> ensureTitleIsNotNull </li>
 * <li> ensureTitleIsNotEmpty </li>
 * <li> ensureContentIsNotNull </li>
 * <li> ensureContentIsNotEmpty </li>
 * <li> ensureRemoveNoteContentIsValid </li>
 * </ol>
 * 
 * NotesList:
 * <ol>
 * <li> testAddNoteIsOk </li>
 * <li> testAddNoteIsNotOk </li>
 * <li> testEditNoteIsOk </li>
 * <li> testEditNoteIsNotOk </li>
 * <li> testRemoveNoteIsOk (2 assertEquals included)</li>
 * </ol>
 *
 *
 * <h3>4.2. Functional Tests</h3>
 *
 * <p>
 * blabla:
 *
 * <ol>
 * <li>  </li>
 * <li>  </li>
 * <li>  </li>
 * <li>  </li>
 * <li>  </li>
 * </ol>
 * 
 *
 * <h3>4.3. UC Realization</h3>
 *
 * <h3>Notes Edition:</h3>
 * <p>
 * <img src=".png" alt="image">
 *
 *
 *
 * <h3>4.4. Classes</h3>
 *
 * <p>
 * Class Diagram: A
 * <p>
 * <img src=".png" alt="image">
 *
 * <p>
 * Class Diagram: B
 * <p>
 * <img src=".png" alt="image">
 *
 *
 * <h2>5. Implementation</h2>
 *
 *
 * <p>
 * nome do que o metodo faz
 * <pre>
 * {@code
 *  public class  extends JPanel{
 *      ...

 *  }    
 * }
 * </pre>
 *
 * <h2>6. Work Log</h2>
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Analisys of previsous iteration of Sprint 1 implementation and documentation. Start analysis
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Continued Analysis and create domain unit tests
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Started implementing domain classes and unit tests
 *
 * <p>
 * <b>Friday</b>
 * <p>
 * 
 *
 * <p>
 * <b>Saturday</b>
 * <p>
 * 
 *
 * <p>
 * <b>Sunday</b>
 * <p>
 * 
 *
 * @author Pedro Fernandes 1060503 - 2DD - 2016/17
 */

package lapr4.blue.s3.core.n1060503.notes;
