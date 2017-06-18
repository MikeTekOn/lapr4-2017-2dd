/**
 * Technical documentation regarding the user story Ipc05.3: Chat Rooms
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * -In this section you should register important notes regarding your work during the sprint.
 * For instance, if you spend significant time helping a colleague or if you work in more than a feature.-
 *
 * <h2>2. Requirement</h2>
 * The chat extension should now support the concept of chat room. A chat room can have several participants. Messages
 * in a chat room are broadcasted to all its members. The user that creates a chat room becomes its owner. There are 2
 * types of rooms: private rooms and public rooms. Public rooms are announced to all instances of Cleansheets and each
 * user is free to become a member of a public room. A private room is not announced in the network. The owner should
 * send invites to other users to participate on the room. Each user is free to accept or reject the invitation. The
 * sidebar should now display also the chat rooms.
 *
 * <h2>3. Analysis</h2>
 *
 * Since chat rooms can be a bit complex, I'm separating it into 3 use-cases:
 *
 * <p>
 * <h3>Use Case "Create Chat Room":</h3>
 * <b>Normal execution:</b>
 * <br> - The user selects the "create chat room" button.
 * <br> - The system asks the name the user would like to give the chat room and whether it is public or private.
 * <br> - The user provides the information and confirms the creation of the chat room.
 * <br> - The chat room is created and the user is automatically added to the chat room.
 * <br>
 *
 * <h3>Use Case "Join Chat Room":</h3>
 * <b>Normal execution:</b>
 * <br> - The user selects the "find chat rooms" button.
 * <br> - The system sends a network broadcast requesting for chat rooms information.
 * <br> - Another thread listens to this requests and updates the user's list as requests are received.
 * <br> - The user selects one of the chat rooms presented and clicks the "join" button.
 * <br> - The user joins the chat room.
 * <br>
 *
 * <h3>Use Case "Send Private request":</h3>
 * <b>Requirements:</b>
 * <br> - The user must be part of a private chat room.
 * <p><b>Normal execution:</b>
 * <br> - The user selects the "Send invite" button.
 * <br> - The system presents the user with a list of available cleansheets instances in the network
 * <br> - The user selects an instance and clicks the "Invite" button.
 * <br> - The system sends an invitation to that cleansheets instance and waits for a response.
 * <br> - The user from the other cleansheets instance accepts the invitation
 * <br> - The system closes the invitation windows and the user from the other instance joins the chat room
 *
 * <p>
 * Since the user that creates a chat room becomes it's owner, he is also responsible for forwarding everyone's
 * messages. This is important in order for everyone to get everyone's messages because an user does not know the
 * current chat's members as fast as the server's owner, which could lead to inconsistencies.
 *
 * <h2>4. Design</h2>
 *
 * A chat room has an owner and members. Only the chat owner is responsible for responding to chat room's existence
 * requests (from someone who is trying to figure out which chat rooms are available to be joined).
 *
 * <h3>4.1. Functional Tests</h3>
 * Basically, from requirements and also analysis, we see that the core functionality of this use case is to be able to add an attribute to cells to be used to store a comment/text. We need to be able to set and get its value.
 * Following this approach we can start by coding a unit test that uses a subclass of <code>CellExtension</code> with a new attribute for user comments with the corresponding method accessors (set and get). A simple test can be to set this attribute with a simple string and to verify if the get method returns the same string.
 * As usual, in a test driven development approach tests normally fail in the beginning. The idea is that the tests will pass in the end.
 * <p>
 * see: <code>lapr4.white.s1.core.n1234567.comments.CommentableCellTest</code><p>
 *
 * <b>Attention: This test should be moved and refactored to Acceptance Tests so that it is in accordance with the 2017 edition guidelines.</b>
 *
 * <h3>4.2. UC Realization</h3>
 * To realize this user story we will need to create a subclass of Extension. We will also need to create a subclass of UIExtension. For the sidebar we need to implement a JPanel. In the code of the extension <code>csheets.ext.style</code> we can find examples that illustrate how to implement these technical requirements.
 * The following diagrams illustrate core aspects of the design of the solution for this use case.
 * <p>
 * <b>Note:</b> It is very important that in the final version of this technical documentation the elements depicted in these design diagrams exist in the code!
 *
 * <h3>Extension Setup</h3>
 * The following diagram shows the setup of the "comments" extension when cleansheets is run.
 * <p>
 * <img src="core02_01_design.png" alt="image">
 *
 *
 * <h3>User Selects a Cell</h3>
 * The following diagram illustrates what happens when the user selects a cell. The idea is that when this happens the extension must display in the sidebar the comment of that cell (if it exists).
 * <p>
 * <img src="core02_01_design2.png" alt="image">
 *
 * <h3>User Updates the Comment of a Cell</h3>
 * The following diagram illustrates what happens when the user updates the text of the comment of the current cell. To be noticed that this diagram does not depict the actual selection of a cell (that is illustrated in the previous diagram).
 * <p>
 * <img src="core02_01_design3.png" alt="image">
 *
 * <h3>4.3. Classes</h3>
 *
 * -Document the implementation with class diagrams illustrating the new and the modified classes-
 *
 * <h3>4.4. Design Patterns and Best Practices</h3>
 *
 * -Describe new or existing design patterns used in the issue-
 * <p>
 * -You can also add other artifacts to document the design, for instance, database models or updates to the domain model-
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
 * -Insert here a log of you daily work. This is in essence the log of your daily standup meetings.-
 * <p>
 * Example
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. -nothing-
 * <p>
 * Today
 * <p>
 * 1. Analysis of the...
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. ...
 * <p>
 * Today
 * <p>
 * 1. ...
 * <p>
 * Blocking:
 * <p>
 * 1. ...
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
 * @author Ricardo Catalão (1150385)
 */
package lapr4.red.s3.ipc.n1150385.groupchat;

