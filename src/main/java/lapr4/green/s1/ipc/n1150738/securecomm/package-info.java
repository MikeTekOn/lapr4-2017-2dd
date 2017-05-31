/**
 * Technical documentation regarding the user story IPC06.1: Secure Communication.
 * <p>
 *
 * <b>Requirement</b><p>
 * Statement:<p>
 * There should be a new mechanism to add secure communications (encrypted communications) between instances of Cleansheets.
 * It is not required at this moment that the cyher should be "professional", only that it should not be trivial to break it.
 * It should be possible to establish secure and unsecured communications with other instances.
 * Cleansheets should now have a new window that logs all the incoming and outgoing communications.
 * Therefore, when this window is visible it should be possible to see encrypted and unsecured data being exchanged. <p>
 * For testing proposes it should be possible for the user to send simple text messages either unsecured or encrypted.
 * <p>
 * <b>Notes:</b><p>
 * The entire connection must be secured. <p>
 * <p>
 * 
 *  
 * <b>Analysis</b><p>
 * <img src="us064_analysis.png" alt="image">
 * <p>
 * <b>Notes:</b><p>
 * The problem: This use case happens in the context of the transport medium of data through the connection. Where a "security layer"
 * is needed to encapsulate the sending and receiving of data. In order to secure the info being exchanged there is two possible scenarios. <p>
 *   Current Situation: A DTO is processed/constructed by a Handler that writes/reads it to/from an ObjectOutputStream/ObjectInputStream constructed on top of the socket's OutputStream/InputStream.
 *   Scenario 1: Encryption/decryption happens for each dto at handler level.
 *   -- Con1: The connection is not granted to be secured since there can still be secured objects and unsecured objects mixed being transmitted in the same connection.
 *   -- Con2: Excessive encryption/decryption logic and connection properties (secured or not) coupled to the handler.
 *   Scenario 2: Encryption/decryption happens at byte level where the ObjectOutputStream/ObjectOutputStream meats the socket's OutputStream/InputStream.
 *   -- Con1: Editing existing code.
 *   -- Pro1: The responsibility is solely part of the connection and the data is agnostic towards its transmission representation.
 *   -- Pro2: The connection can now be described as secured or not.
 *   -- Pro3: The system wont have as many parts knowing how to encrypt/decrypt data.
 *
 * <p>
 * Interface DataTransmissionContext defines the context of data transportation. <p>
 * Class BasicDataTransmissionContext: simply redirects the data being sent/received to the channel where it will ne transported/arrive. <p>
 * Class SecureDataTransmissionContext: inserts a layer of security between the data being sent/received and the channel where it will ne transported/arrive. <p>
 * <p>
 *
 *
 * <b>Tests</b><p>
 *
 * <b>Test1:</b> SendSimpleMessageUnsecuredTest<p>
 * <b>Test2:</b> SendSimpleMessageSecuredTest<p>
 * It Should be possible to send simple text messages encryped or not. <p></p>
 * See Package lapr4.green.s1.ipc.n1150738.securecomm:<p>
 * SendSimpleMessageTest<p>
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
package lapr4.green.s1.ipc.n1150738.securecomm;

