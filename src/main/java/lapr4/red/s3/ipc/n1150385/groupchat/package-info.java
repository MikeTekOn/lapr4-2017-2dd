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
 * <h2>5. Work Log</h2>
 *
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
 * 1. Analysis of the use-case
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis of the various classes of the use-cases
 * <p>
 * Today
 * <p>
 * 1. Started implementation of the use-case
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Starting the implementation of the use-case
 * <p>
 * Today
 * <p>
 * 1. Continued the implementation of the use-case
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Continuing the implementation of the use-case
 * <p>
 * Today
 * <p>
 * 1. Finished the implementation of the use-case
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 *
 * @author Ricardo Catalão (1150385)
 */
package lapr4.red.s3.ipc.n1150385.groupchat;

