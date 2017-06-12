/**
 * Technical documentation regarding the user story IPC05.2 - Chat Participants.
 * <p>
 * JIRA issue: LAPR4E17DD-88
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
 * Before starting my use case, i had to adjust sprint1's use story for this
 * feature: IPC05.1 - Chat Send Message.
 * <p>
 * It had a problem, the first message was not sent, only from the second
 * message the comunication worked.
 *
 * <h2>2. Requirement</h2>
 * <p>
 * Cleansheets should now use the user name of the system as the basis for the
 * user profile of the chat.
 * <p>
 * The end user should be able to add an icon or a photo to its profile as well
 * as a nickname.
 * <p>
 * Each user should have a status (i.e., online or offline).
 * <p>
 * Cleansheets should automatically discover all users in the local network.
 * <p>
 * The sidebar window should now have the conversations organized by user.
 * <p>
 * The window should also display the status of the users and their icon and
 * nickname.
 * <p>
 * When a user state is offline it will not receive any messages from other
 * instances of Cleansheets.
 * <p>
 * Profile configuration and message history should be persistent.
 *
 *
 * <h2>3. Analysis</h2>
 *
 * <p>
 * Three phases of analysis of this use case were identified:
 * <ol>
 * <li> st phase: create / upload chat user profile. </li>
 * <li> nd phase: search the users and allow conversation with only those who
 * are online.</li>
 * <li> rd phase: persistence the history of messages by user.</li>
 * </ol>
 *
 * <p>
 * It was decided to create a user profile of the default chat where it will
 * have:
 * <p>
 * - username
 * <p>
 * - nickname
 * <p>
 * - image (icon / photo)
 * <p>
 * - status (online or offline)
 * <p>
 * At any time the user can change all the attributes except the username, since
 * it is the name of the system.
 *
 * <p>
 * Regarding the icons it was established that there was only one by default. If
 * the user wants to change to another icon or photo, you can upload it.
 *
 * <h3>1st Phase: Change User Chat Profile:</h3>
 * <p>
 * <img src="ssd_user_chat_profile.png" alt="image">
 *
 * <ol>
 * <li> The user select change profile configuration </li>
 * <li> The system show user chat info </li>
 * <li> The user change info  </li>
 * <li> The system confirm submission and reports operation success </li>
 * </ol>
 *
 * <h3>2nd Phase: Chat Participants</h3>
 *
 * <p>
 * <img src="ssd_chat_participants.png" alt="image">
 *
 * <ol>
 * <li> The user select chat tab </li>
 * <li> The system show all users connected (online and offline) </li>
 * <li> The user choose the user that want to communicate  </li>
 * <li> The system show the conversation window </li>
 * <li> The user send message </li>
 * <li> The system show receved message </li>
 * </ol>
 *
 *
 * <p>
 * Questions / Answers:
 * <ol>
 * <li> Is the chat user profile in any way related to the contacts? No.</li>
 * <li> Are there any restrictions regarding nickname? No.</li>
 * <li> What is the default state? Online</li>
 * <li> When the user is offline and when change to online, get the messages
 * sent or block completely? Block and don´t receive any message</li>
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
 * UserChatProfile:
 * <ol>
 * <li> ensureUsernameIsEqualSystem </li>
 * <li> ensureNicknameIsNotNull </li>
 * <li> ensureNicknameIsNotEmpty </li>
 * <li> ensureUserChatHasOnlineStatusByDefault </li>
 * <li> ensureUserChatCanChangeStatus </li>
 * <li> ensureUserChatStatusIsValid </li>
 * <li> ensureUserChatIconPathIsValid </li>
 * <li> ensureIsOK </li>
 * </ol>
 *
 *
 * <h3>4.2. Functional Tests</h3>
 *
 * <p>
 * Comunicate with other chat user:
 *
 * <ol>
 * <li> Select View Menu Option, Sidebar option, check Network, Chat
 * Participants and Chat </li>
 * <li> Choose Network Tab </li>
 * <li> Click Activate button </li>
 * <li> Choose Chat Participants Tab </li>
 * <li> Click Turn On / Search button </li>
 * <li> Will appear all users of chat </li>
 * <li> Choose one that have status ONLINE </li>
 * <li> Click New Message </li>
 * <li> Send Message </li>
 * </ol>
 *
 * <p>
 * Received Messages:
 *
 * <ol>
 * <li> A Popup Window will appear notifying </li>
 * <li> Choose Chat Tab </li>
 * <li> Click on Host Name </li>
 * <li> A window with history will appear </li>
 * <li> write response and click send </li>
 * </ol>
 *
 * <p>
 * Change Chat User Profile:
 *
 * <ol>
 * <li> Select  </li>
 * <li> Choose Chat Participants Tab </li>
 * <li> Click on Change Profile </li>
 * <li> A window with icon, username, nickname and status will appear </li>
 * <li> Change path of image, nickname and status </li>
 * <li> A popup window will confirm that the modifications are successfully
 * changed </li>
 * </ol>
 *
 * <h3>4.3. UC Realization</h3>
 *
 * <h3>Change User Chat Profile:</h3>
 * <p>
 * <img src="sd_user_chat_profile.png" alt="image">
 *
 *
 *
 * <h3>4.4. Classes</h3>
 *
 * <p>
 * Class Diagram: Change User Chat Profile
 * <p>
 * <img src="cd_user_chat_profile.png" alt="image">
 *
 * <p>
 * Class Diagram: Chat Participants
 * <p>
 * <img src="cd_chat_participants_ext.png" alt="image">
 *
 *
 * <h2>5. Implementation</h2>
 *
 * <p>
 * Send UserChatDTO (UDP)
 *
 * <pre>
 * {@code
 *  public class ChatParticipantsAction extends BaseAction{
 *      ...
 *      public void actionPerformed(ActionEvent e) {
 *          UserChatDTO request = new UserChatDTO(ucp);
 *          CommUDPClient worker = new CommUDPClient( request, portNumber, TIMEOUT);
 *          HandlerUserChatDTO handler = new HandlerUserChatDTO();
 *          handler.addObserver(table);
 *          worker.addHandler(ConnectionDetailsResponseDTO.class, handler);
 *          worker.start();
 *          ((HandlerUserChatDTO) CommUDPServer.getServer().getHandler(request.getClass())).addObserver(table);
 *      }
 * }
 * }
 * </pre>
 *
 * <p>
 * SearchUserAction
 * <pre>
 * {@code
 *  public class ChatParticipantsPanel extends JPanel{
 *      ...
 *    private class SearchUserAction implements ActionListener {
 *      public void actionPerformed(ActionEvent e) {
 *          try{
 *              table.clear();
 *              (new ChatParticipantsAction(table, 15310, ucp)).actionPerformed(e);
 *              btSearch.setEnabled(false);
 *          } catch (NullPointerException n){
 *              JOptionPane.showMessageDialog(null, "Go to Network Tab and click Activate");
 *          }
 *      }
 *
 *    }
 *  }
 * }
 * </pre>
 *
 * <p>
 * ConnectAction
 * <pre>
 * {@code
 *  public class ChatParticipantsPanel extends JPanel{
 *      ...
 *      private class ConnectAction implements ActionListener {
 *          public void actionPerformed(ActionEvent e) {
 *              if(table.getSelectedRowFile() instanceof UserChatDTO){
 *                  if(table.getSelectedRowFile().getStatus().equals("OFFLINE")){
 *                      JOptionPane.showMessageDialog(null, table.getSelectedRowFile().getUserChatProfileNickname()
 *                                  +" is OFFLINE","Error",JOptionPane.INFORMATION_MESSAGE);
 *                  }else{
 *                      ConnectionID connection = table.getSelectedRowFile().getConnectionID();
 *                      if (connection != null) {
 *                          ControllerConnection.setChatController(connection);
 *                          (new ConnectToPeerAction(connection)).actionPerformed(e);
 *                          (new ChatAction(connection, theController)).actionPerformed(e);
 *                      }
 *                  }
 *              }else{
 *                  JOptionPane.showMessageDialog(null, "You must select a line!","Error",JOptionPane.ERROR_MESSAGE);
 *              }
 *          }
 *      }
 *  }
 * }
 * </pre>
 *
 *
 * <p>
 * ConnectAction
 * <pre>
 * {@code
 *  public class ChatParticipantsPanel extends JPanel{
 *      ...
 *      private class ChangeProfileAction implements ActionListener {
 *          public void actionPerformed(ActionEvent e) {
 *              try {
 *                  final ChangeUserChatProfileUI cucp = new ChangeUserChatProfileUI(controllerChangeUser);
 *              } catch (IOException ex) {
 *                  Logger.getLogger(ChatParticipantsPanel.class.getName()).log(Level.SEVERE, null, ex);
 *              }
 *          }
 *      }
 *  }    
 * }
 * </pre>
 *
 * <h2>6. Work Log</h2>
 *
 * <b>Monday</b>
 * <p>
 * I was assigned: IPC05.2 - Chat Participants.
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Analisys of previsous iteration of Sprint 1 implementation and documentation.
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Fix error on Sprint 1.
 * <p>
 * Started analisys of this use case.
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Analisys of this use case, unit test and start implementation
 *
 * <p>
 * <b>Friday</b>
 * <p>
 * add analysis, design, unit test and implemented several classes
 *
 * <p>
 * <b>Saturday</b>
 * <p>
 * continue implementation
 *
 * <p>
 * <b>Sunday</b>
 * <p>
 * Fix erros
 *
 * @author Pedro Fernandes 1060503 - 2DD - 2016/17
 */
package lapr4.blue.s2.ipc.n1060503.chat;
