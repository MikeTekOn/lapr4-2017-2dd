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
 * <b>Client Worker</b> is responsible for sending the request to a server. There will be a UDP client and a TCP client.
 * <p>
 * <b>Data Transfer Object</b> is responsible for transmitting the data through the network connection (also known as DTO).
 * <p>
 * <b>Handler</b> is responsible for interpreting the Data Transfer Object received and perform any action needed.
 * <p>
 * <img src="ipc_01_1_analysis2.png" alt="image"> 
 * 
 * <h2>Special Notes</h2>
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
 * <b>Connection Details Request</b>: A broadcast can be sent to the local network with another instance running. The client who broadcasted must receive the connection details from the other instance.
 * <p>
 * <b>Echo Request</b>: A simple echo request can be sent from one instance to another. The client shall connect to the server and request it an echo. The client must receive the echo response.
 * <p>
 * <b>Unit Testing</b>: The main methods involved must be tested individually to assure their result is as expected.
 * 
 * @author Manuel Meireles (1150532)
 */ 
package lapr4.green.s1.ipc.n1150532;
