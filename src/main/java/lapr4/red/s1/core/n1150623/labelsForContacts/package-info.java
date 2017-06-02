/**
 * Technical documentation regarding the user story Core02.1: Comments on Cells.
 * 
 * <p>
 * <b>-Note: this is a template/example of the individual documentation that each team member must produce each sprint. Suggestions on how to build this documentation will appear between '-' like this one. You should remove these suggestions in your own technical documentation. You may also define a different template for your team to use with the agreement of your supervisor-</b>
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- yes</b>
 *
 * <h2>1. Notes</h2>
 *
 * -Notes about the week's work.-
 * <p>
 * -In this section you should register important notes regarding your work during the sprint.
 * For instance, if you spend significant time helping a colleague or if you work in more than a feature.-
 *
 * <h2>2. Requirement</h2>
 *
 * It should be able to generate labels for contacts by:
 * - Creating a PDF document which each page has a label of one contact.
 * - Labels must include at least the name, photograph, addresses, emails and telephone numbers of the contact.
 * - The user should also have the option to select if the events of the contacts should be included in the PDF , and in this case the user must enter the date interval that is used to select the events.
 * - It should be possible to select the contacts using regular expression on the name of the contact and/or selecting a
 * specific town or city.
 *
 * <p>
 * <b>Use Case "Labels for Contacts":</b>
 * The user opens the contacts page and chooses to export contact information to pdf.
 * The system asks the user about the possibility of including the contact's event list.
 * The user selects his preference - if he wants to add them, we will be asked by the system to tell the begging and end dates for filtering the events.
 * The system converts information about contacts to pdf according to the option chosen by the user.
 * 
 *  
 * <h2>3. Analysis</h2>
 *
 * To export the contact's information, there is a need to know how to access it, how it's persisted and where.
 * The sequence diagrams of the use case 10.1 that is responsible for contact information and related events persistence, tells us that:
 *
 * <h3>Create and Persist Contact [Core-10.1]</h3>
 * <p>
 * <img src="uc_create_contact.png" alt="image">
 * <p>
 *
 * <h3>Create and Persist Event [Core-10.1]</h3>
 * <p>
 * <img src="uc_create_event.png" alt="image">
 * <p>
 *
 *
 * All information can be found in the repositories and can easily be accessed.
 * Only the requested information will be taken from the repositories.
 * Now the problem lays on getting the exportation to pdf done and to solve that problem, as it's said in the manual, we will use the external library <b><i>"iText"</i></b> (added to the dependencies on build.gradle).
 *
 *
 * <h2>4. Design</h2>
 *
 * <h3>4.1. Unit Tests</h3>
 *
 * Following the analysis and seeing the requirements mentioned int the manual, the principal functionality of this use case is to correctly export information in system to a .pdf file.
 * We can also verify if the filters for choosing contacts are working and the selection events to add is functioning properly.
 * It only remains to verify if the value of the exportation result has all the information needed.
 *
 * <p>
 *
 * <h3>4.2. UC Realization</h3>
 *
 * To realize this user story we will need to create a class that can transform information into a well structured .pdf file. We will also need to create a Controller class that shall pull from the repositories all the information needed. We also will have to add a JButton to the existing contact and events window that will initiate this Use Case.
 * The following diagrams illustrate core aspects of the design of the solution for this use case.
 * <p>
 * 
 *
 * <h3>User Selects Contact Information Exportation</h3>
 *
 * The following diagram illustrates what happens when the user selects, in the contact's page, the exportation button, witch send de contact information and the even list of each one of them to a PDF file. The idea is that when this happens the data will be retrieved from the repositories and is send to a parser to be converted into a pdf file.
 * (The classes related to the application of a strategy pattern for exportation are omitted since there will be a diagram for only that.)
 * <p>
 *
 * <img src="sd_uc_labels_for_contacts.png" alt="image">
 *
 * <h3>4.3. Classes</h3>
 *  <p>
 *      These are the classes related to this use case:
 *  <p>
 *  <img src="class_diagram.png" alt="image">
 * 
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * 
 * - Since the goal of this Use case is to export information, we concluded that it should be possible, in the future, to easily implement more export options. For now we will only do the exportation to a PDF file, but, for instance, at some point, there may be a need to pass that information to a XML file or CSV or TXT.
 *
 * <p>
 *
 * Basically we will implement a pattern that will facilitate the implementation of new export options in the future, if needed - <i>Strategy</i> Pattern.
 * This a generalized way of implementing this pattern:
 *
 * <h4>Create and Persist Event [Core-10.1]</h3>
 * <p>
 * <img src="generic_strategy_implementation.png" alt="image">
 * <p>
 *
 *     In the sequence diagram of the use case was omitted the exportation part related to the use of this pattern since it was explained here. There was no need do complicate the diagram in order to expose the entire structure.
 * <p>
 *
 * <h2>5. Implementation</h2>
 * 
 * -Reference the code elements that where updated or added-
 * <p>
 * -Also refer all other artifacts that are related to the implementation and where used in this issue. As far as possible you should use links to the commits of your work-
 * 
 * <h2>6. Integration/Demonstration</h2>
 * 
 * -In this section document your contribution and efforts to the integration of your work with the work of the other elements of the team and also your work regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 * 
 * <h2>7. Final Remarks</h2>
 * 
 * -In this section present your views regarding alternatives, extra work and future work on the issue.-
 * <p>
 * As an extra this use case also implements a small cell visual decorator if the cell has a comment. This "feature" is not documented in this page.
 * 
 * 
 * <h2>8. Work Log</h2> 
 *
 * <p>
 * Example
 * <p>
 * <b>Tuesday</b>
 * <p>
 * I worked on:
 * <p>
 * 1. Running the application;
 * 2. Analysing the existing code;
 * <p>
 * Today
 * <p>
 *
 * 1. Began and concluded the Analysis of this Use Case
 * <p>
 * Blocking:
 * <p>
 * 1. None
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. This Use Case Analysis
 * 2. Reviewed de code
 * <p>
 * Today
 * <p>
 * 1. I'll make the design of the Use Case
 * <p>
 * Blocking:
 * <p>
 * 1. None
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. The Design of this use case
 * <p>
 * Today
 * <p>
 * 1. I'll finish the design and do some functional tests.
 * <p>
 * Blocking:
 * <p>
 * 1. None
 *
 *
 *
 * <h2>9. Self Assessment</h2> 
 * 
 * -Insert here your self-assessment of the work during this sprint regarding Rubrics R3, R6 and R7.-
 * 
 * <h3>R3. Rubric Requirements Fulfilment: 3</h3>
 * 
 * 3- some defects. The student did fulfil all the requirements and also did justify the eventual options related to the interpretation/analysis of the problem.
 * 
 * <h3>R6. Rubric Requirements Analysis: 4</h3>
 * 
 * 4- correct. There is a robust and very complete analysis of the problem with well chosen technical artifacts (diagrams, grammars, etc.) for its documentation and without errors.
 * 
 * <h3>R7. Rubric Design and Implement: 2</h3>
 * 
 * 2- many defects. The code follows good practices although some design patterns should have been applied. The technical documentation covers the majority of the solution although it may have some errors. However the appropriate type of technical artifacts for documenting design are present and the ideia behind the solution is understandable. Code does not "goes against" the design options of the original code of the project. Unit tests seem to cover a significant amount of functionalities (e.g., more than 50%) but there was not test first approach.
 * 
 * @author 1150623 Guilherme Ferriera
 */
package lapr4.red.s1.core.n1150623.labelsForContacts;

