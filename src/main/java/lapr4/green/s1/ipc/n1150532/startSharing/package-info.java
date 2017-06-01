/**
 * Technical documentation regarding the user story <b>IPC01.1: Start Sharing</b>. 
 * 
 * <h1>Requirements</h1>
 * 
 * It should be possible to establish a connection with other instance of Cleansheets in the local network. It should be possible to send the contents of a range of cells to another instance of Cleansheets. The other instance should display the received contents in the same cell address as the original cells.
 * <p>
 * It should be possible to configure the port to be used for network connections. It should be possible to find other instances of Cleansheets available in the local network. These instances should be listed in a new window (sidebar window). The user should be able to select one of the discovered instances to connect to when establishing the connection to send the contents of the range of cells. At the moment it is only required to send the value of the cells.
 * 
 * <h1>Analysis</h1>
 * 
 * This use case requires a generic communication platform for the application, which will be used by other use cases, as well as a cell sharing feature, within the local network.
 * 
 * <h2>Communication</h2>
 * 
 * For the application to communicate, it is needed to broadcast a request for other applications to provide their connection details, i.e. the port number in which to send the connection request.
 * This implies that the application will also need a server to respond to such requests.
 * The client must then receive all the replies from the different server.
 * Since it does not know how many to expect, a timeout can be set in order for it to terminate after a while without replies.
 * For this purpose, the User Datagram Protocol (UDP) seems to be adequate.
 * <p>
 * Afterwards, a reliable connection is required to transfer data between the applications.
 * A client for each connection request is needed, as well as a server to handle the requests.
 * The server should be dedicated to accept the requests.
 * The request processing should be delegated to a worker which will use handlers to manage any kind of data.
 * For this purpose, the Transmission Control Protocol (TCP) seems to be adequate.
 * <p>
 * The handler may vary depending on the object to be transfered. This allow for a common communication base classes.
 * <p>
 * The requirements also request the application to allow the port number to be configured. The changes to the configuration should be persistent.
 * 
 * <h2>Cell Sharing</h2>
 * 
 * A user may select another application within the local network to connect to.
 * Afterwards, it may select a range of {@link csheets.core.Cell}s whose {@link csheets.core.Address} and {@link csheets.core.Value} are to be sent.
 * The application which receives the cells should place the value at the correct address (for the time being).
 * <p>
 * <b>Main flow</b>
 * <p>
 * <img src="ipc_01_1_analysis1.png" alt="image"> 
 *
 * <h2>Concepts</h2>
 * 
 * <b>Server</b> is responsible for receiving requests and assigned a worker to that client. There will be a UDP server and a TCP server.
 * <p>
 * <b>Server Worker</b> is responsible for interpreting a client's request and send the response.
 * <p>
 * <b>Client Worker</b> is responsible for sending the request to a server and interpreting the response. There will be a UDP client and a TCP client.
 * <p>
 * <b>Data Transfer Object</b> is responsible for transmitting the data through the network connection (also known as DTO).
 * <p>
 * <b>Handler</b> is responsible for interpreting the Data Transfer Object received and perform any action needed.
 * <p>
 * <img src="ipc_01_1_analysis2.png" alt="image"> 
 * 
 * <h2>Special Notes</h2>
 * 
 * This user story is related to all the other IPC use cases, since it provides the communication base for them.
 * 
 * The user story <b>IPC06</b> is strongly related to this one since that requires an encryption technique.
 * More information can be found there.
 * 
 * <h1>Tests</h1>
 * 
 * <h2>Acceptance Tests</h2>
 * 
 * <b>Configure port numbers</b>: The user changes the configuration and restarts the application. The user tries to change it again and the values are set to his last change.
 * <p>
 * <b>Find peers in local network</b>: One instance should be able to broadcast connection details request and find all other connected instances within the local network.
 * <p>
 * <b>Share a range of cells</b>: One instance should be able to send a range of cells to the other instance, which in turn should show them at the right address and the correct value. For the time being, it is only necessary to transfer the value itself.
 * 
 * <h2>Other</h2>
 * 
 * <b>UDP Echo Request</b>: A broadcast can be sent to the local network. The instance's server must receive the echo request and the instance's client the server's response.
 * <p>
 * <b>TCP Echo Request</b>: A simple echo request can be sent from one instance to another (simulated). The client shall connect to the server and request it an echo. The client must receive the echo response.
 * <p>
 * <b>Unit Testing</b>: The main methods involved must be tested individually to assure their result is as expected.
 * <p>
 * <b>Attention!</b> The port numbers used in the tests must be different since they may run in parallel.
 * 
 * <h1>Design</h1>
 * 
 * <h2>Servers</h2>
 * 
 * Both UDP and TCP servers should be unique. An instance only needs one server of a kind at any given time.
 * In order to ensure this, the Singleton Pattern can be used.
 * 
 * <h2>Port Number</h2>
 * 
 * The client requests the port number to be configurable.
 * This seems like a variation point among different clients and along the software's life time.
 * For these reasons, the Protected Variation Pattern can be applied.
 * Although it could be implemented with the Strategy Pattern, since it is not suppose to change during runtime, it seems a little to excessive.
 * A suitable solution is to define the port number at the properties of the application.
 * This provides the versatility needed to change the value when needed at a low effort cost. 
 * 
 * <h2>Handlers</h2>
 * 
 * The handlers are intended to deal with any DTO they might receive.
 * The workers must know which handler to use to each DTO.
 * Since the right handler to use is only known at runtime and it changes accordingly to the DTO received, a suitable solution seems to be the Strategy Pattern.
 * This pattern can be implemented by using a map of handlers, whose key would be the DTO class and the value the matching handler.
 * In case there is no matching handler, the received DTO will be ignored, i.e. no action will be performed.
 * 
 * <h2>Find Peers</h2>
 * 
 * The UDP client shall send a broadcast with a Connection Details Request DTO.
 * All the other instances UDP servers will respond with their IP address and port number in which to send TCP connections within a Connection Details Response DTO.
 * The UDP client will process the replies for a brief defined time, since it is impossible to know how many replies there will be.
 * Each reply should trigger the user interface to add that peer.
 * Note that, since the datagram will be sent to the broadcast, it will receive its own response.
 * This one must be ignored.
 * <p>
 * <b>Client Side</b>
 * <p>
 * <img src="ipc_01_1_design_udp_client.png" alt="image"> 
 * <p>
 * <b>Server Side</b>
 * <p>
 * <img src="ipc_01_1_design_udp_server.png" alt="image"> 
 * 
 * <h2>Connect Two Instances</h2>
 * 
 * This instance will use a client worker to send a Connection Request DTO to the other instance.
 * The server of that instance will respond with a Connection Response DTO informing the success of the operation.
 * Also, when the server receives the request, it will check if that instance already has a client to communicate back.
 * If not, it will use a client to request it.
 * <p>
 * <b>Client Side</b>
 * <p>
 * <img src="ipc_01_1_design_tcp_client.png" alt="image"> 
 * <p>
 * <b>Server Side</b>
 * <p>
 * <img src="ipc_01_1_design_tcp_server.png" alt="image"> 
 * 
 * <h2>Maintain TPC Connections</h2>
 * 
 * The connections made are to be maintained in order to use it for multiple purposes.
 * It will require the extension to keep track of their clients since they are the bridge to the other instances.
 * This implies a map, whose key is the peer IP address and the value its dedicated worker.
 * It must also allow access to the worker output in order to send new DTO at any given time.
 * This implies a output stream getter within the worker.
 * 
 * <h2>Terminate TCP Connections</h2>
 * 
 * The client will send a Close Connection Request DTO to the server.
 * The server will then check if its instance also has a client running.
 * If so, its client will also send a Close Connection Request DTO.
 * Afterwards, the server responds with a Connection Response DTO to the client and closes the connection.
 * The client receives the answer and closes its own connection.
 * 
 * <h2>Share Cells</h2>
 * 
 * For the time being only the value of the cell is to be transmitted, as well as its address in order to place it in the right location.
 * The client sends a Cell Sharing DTO which the server will use to insert the values at the right addresses.
 * 
 * @author Manuel Meireles (1150532)
 */ 
package lapr4.green.s1.ipc.n1150532.startSharing;
