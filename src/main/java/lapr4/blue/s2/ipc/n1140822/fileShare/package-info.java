/**
 * Technical documentation regarding the user story IPC 08.1: File Sharing.
 * 
 *
 * <b>Scrum Master: no</b>
 * 
 * <p>
 * <b>Area Leader: no</b>
 * 
 * <h2>1. Notes</h2>
 * 
 * 
 * <p>
 *
 * <h2>2. Requirement</h2>
 * It is required to specify the shared folder and the downloaded folder (output and input respectively). The shared files of instance one are to be available and appear in lists in other instances of cleansheets.
 * This configuration should be persistent. It is necessary to keep the list updated automatically (the list of downloaded files and available files).
 * The list should have the name, size, source and status (up to date, in progress, etc).
 * 
 * <p>
 * <b>Use Case "File Share":</b>  The user activates the file share extension. The system sends the file names to the list of hosts available in the instance. If the current instance receives a request for a file, it sends the file to the requester.
 * 
 *  
 * <h2>3. Analysis</h2>
 * This use case uses the existing infrastructure designed for connections. This basis uses threads to establish connections so it doesnt impact the other aspects of the application. In this document the focus will be in establishing these connections and the definition of protocols for the file sharing extension.
 * Similarly, the use of DTO's is needed since that is defined in the current infrastructure. Each DTO has its own handler, which processes the received bytes in the fashion it wants, constructing dedicated objects of that DTO type. This approach is very modular since it allows the addition of new DTOs without impacting the current existing ones.
 * Everytime a DTO is received, if there is no handler mapped to it, it will be ignored, as it should. This is effective since we wont process these bytes without purpose.
 * That being said, we will need to define DTOs for the file name lists. In the design section we will define how this DTO will be treated. Another DTO we will need to implement is the file itself.
 * 
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the previously described use case. This is will serve as a draft for the design phase.
 * <p>
 * <img src="analysis_sd.png" alt="image"> 
 * <p>
 * 
 * This design depicts two users and therefore two systems. It shows a transaction between these two systems, where the first one sends the available files to one of the active connections (in the real implementation it should send to all active connections).
 * That being said, the second user selects a file to download. It requests the first user for a file. The system receives that request and responds with the file. Then the system_2 will read this file and make it available for user_2.
 * <h3>Analysis of Core Technical Problem</h3>
 * <p>
 * The problem here is the method that will be used to send the file names and files. Normally, a list of file names to download, could be sent over UDP using the broadcast domain. 
 * In this specific scenario, i think the best approach is sending these over TCP, with the respective explanation below.
 * It is requested that a layer of control over these files exists - it is necessary to know if the files are up to date or download is in progress etc. By opening a connection to each discovered host (which we can access using the implemented comm structure) we have complete control over each connection and can send the file updates over these.
 * This approach also sets the basis for the second functional increment of this use case, where permanent downloads will be implemented.
 * As all things, this approach has its downsides - it can be demanding on the network structure since you open a connection for each instance of the application which wants to share files.
 * But, once this connection is made, it will stay available for the rest of the application execution, which means it will be only opened once per execution.
 * The other side of the problem is the DTOs needed for this. We will need a DTO dedicated only to file lists (which will include a list of the files names,sizes and owners). The other DTO we will need is the FileDTO which will be the container for the file we want to transfer.
 * That being said, the new concepts we will introduce into the system are depicted below:
 * <p>
 * <p>
 *  FileShareController
 * <p>
 * <p>
 *  FileListDTO
 * <p>
 * <p>
 *  FileDTO
 * <p>
 * 
 * <h2>4. Design</h2>
 *
 * <h3>4.1. Functional Tests</h3>
 * The domain of this functional increment has already been tested, as such, there will be no new tests implemented.
 *  <p>
 * Unit test 1 - 
 *  <p>
 * <h3>4.2. UC Realization</h3>
 * To realize this functional increment, we will need the classes referred in the analysis section and the already implemented ones.
 * The already existing infrastructure documentation can be found in - 
 * The sequence diagram below will depict the transactions showed in the SSD in the analysis section containing all needed classes for the implementation.
 * <p>
 * Sequence Diagram 1 - File sharing between two instances
 * <p>
 * <p>
 * <img src="design.png" alt="image">
 * <p>
 * From this sequence diagram it can be seen
 * <h3>4.3. Classes</h3>
 * 
 * <p>
 * <img src="class_diagram.png" alt="image"> 
 * <p>
 * 
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * 
 * This is an extension, so all extension related patterns are already explained in other documents.
 * In regards to the macros, their specification can be found in lapr4.blue.s1.lang.n1151159.macros.
 * 
 * 
 * <h2>5. Implementation</h2>
 * 
 * Added the planned classes.
 * Added junit testing to guarantee domain rules are enforced.
 * Implemented controller class.
 * Integrated macros with beanshell
 * Integrated beanshell with cleansheets
 * <p>
 * 
 * 
 * <h2>6. Integration/Demonstration</h2>
 * 
 * When integrating, it was verified the changes were not showing appropriatly on the user interface. This led to changes in the implementation which are now reflected in this document.
 * 
 * <h2>7. Final Remarks</h2>
 * Future possible changes and improvements: Total domain interaction as depicted in the CleanSheets API UC, improve reading method to allow method implementations on different lines.
 * <p>
 * As an extra this use case already allows for many scripts to run, and allows unprotected acess (for now) to domain objects.
 * 
 * 
 * <h2>8. Work Log</h2> 
 * 
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. -nothing-
 * <p>
 * Today
 * <p>
 * 1. Analysis of the use case and cleansheets application.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Analysis of the use case and cleansheets application.
 * <p>
 * Today
 * <p>
 * Finishing the analysis and starting the design.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
* <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finishing the analysis and starting the design.
 * <p>
 * Today
 * <p>
 * 1.  Finishing the design and started the domain junit testing.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1.Finishing the design and started the domain junit testing.
 * <p>
 *  Finished the junit tests and started the implementation.
 * <p>
 * 1. ...
 * <p>
 * Blocking:
* <p>
 * 1. -nothing-
 * <p>
* <b>Friday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finished the junit tests and started the implementation.
 * <p>
 * Today
 * <p>
 * 1. Continued the implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1.  Continued the implementation.
 * <p>
 * Today
 * <p>
 * 1. Finished the implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <h2>9. Self Assessment</h2> 
 * 
 * 
 * 
 */
package lapr4.blue.s2.ipc.n1140822.fileShare;
