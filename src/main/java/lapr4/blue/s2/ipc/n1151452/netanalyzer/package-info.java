 /**
 * Technical documentation regarding the user story IPC06.2 Network Analyzer.
 * <p>
 * <b>JIRA ISSUE: LAPR4E17DD-91</b><p>
 * <b>Scrum Master: yes</b><p>
 * <b>Area Leader: yes</b>
 * <p>
 * <h2>1. Notes:</h2>
 * <p><ol>
 * <li><b>I analysed the related FIs to elaborate an integrated solution.</b>
 * <li><b>The logger of the FI [IPC06.1] wasn't completely implement so I will integrate it in this FI.</b>
 * </ol>
 * <p>
 * <p>
 * <h2>2. Requirements</h2>
 * <p><ul>
 * <li>Cleansheets should now have a new sidebar window that displays a real time chart of all incoming and outgoing network traffic.
 * <li>There should be four columns: unsecure incoming; secure incoming; unsecure outgoing; secure outgoing.
 * <li>The chart should automatically adjust the units used: bytes; kilobytes; megabytes and gigabytes.
 * <li>The unit used should be the one that results in a chart that is more adjusted to the size of the window.
 * </ul><p>
 * <p>
 * <h2>3. Analysis</h2>
 * <p>
 * <b>Attention:</b>
 * I will refactor some classes from the FI [IPC01.1] to integrate this solution.
 * <p><ol>
 * <li>Every income or outcome traffic, when reading or writing to the streams, shall publish a traffic event to log &amp; count the traffic.
 * <li>The app should have a watchdog for each business rule (Log &amp; Count) that are listening for any traffic event.
 * <li>These watchdogs will intermediate the published events with the UI.
 * <li>Due to the multithreading environment, we should make the watchdogs thread-safe, that is, synchronize the access to its properties.
 * <li>The UI will be listening for any updates received from the watchdogs.
 * <li>There should be an util helper class to assist the unit conversion.
 * <li>The secured/unsecured traffic will be sent using a transmission strategy to abstract the responsibility.
 * <li>To display the received traffic data we will use a javaFX line chart <a href="http://docs.oracle.com/javafx/2/charts/line-chart.htm"></a>
 * <li>To integrate with the swing UI we will use a javaFXPanel {@link javafx.embed.swing.JFXPanel}.
 * <li>The UI extension will follow the already implemented pattern (please consult {@link lapr4.white.s1.core.n1234567.comments}).
 * </ol><p>
 * <p>
 * <h3>3.1. Analysis details</h3>
 * <p>
 * - <b>3.1.1. input/output streams</b><p>
 * <ol>
 *     <li>Using the decorator pattern we will override the read/write methods to execute the "wrapped" streams read/write and
 *     to create a new thread to publish a traffic event. This will help monitor all incoming &amp; outgoing traffic.
 *     <img src="stream_decorator_pattern.png" alt="stream decorator"><p>
 *     <li>To integrate with the already in use {@link java.io.ObjectOutputStream} &amp; {@link java.io.ObjectInputStream}, that do not return
 *     the streamed bytes we will need a separate util helper method to convert the streams in a {@link java.io.ByteArrayInputStream} &amp;
 *     {@link java.io.ByteArrayOutputStream}, respectively, and count the written bytes.
 *     <pre>
 *      {@code
 *          // Example not tested
 *          public static Long length(Object obj) throws IOException {
 *
 *               try(ByteArrayOutputStream bos = new ByteArrayOutputStream()){
 *
 *                   try(ObjectOutputStream oos = new ObjectOutputStream(bos)){
 *
 *                       oos.writeObject(obj);
 *                   }
 *                   return bos.size();
 *               }
 *           }
 *      }
 *      </pre>
 * </ol>
 * <p>
 * - <b>3.1.2. Traffic Event Publisher</b><p>
 *     <ol>
 *         <li>Will run on a different thread</li>
 *         <li>Will have a simple responsibility to build &amp; publish a traffic event</li>
 *         <li>Different instances could co-exist at the same time (Make observers thread safe)</li>
 *     </ol>
 * <p>
 * - <b>3.1.3. Traffic Logger [WatchDog]</b><p>
 *     <ol>
 *         <li>Will follow the singleton pattern. To guarantee it is always listening for traffic events.</li>
 *         <li>Will have a queue of published traffic events and a consumer thread that will consume the list and send it his listeners.</li>
 *         <li>The update() method will produce (add) traffic events to the queue.</li>
 *         <li>To make it a thread-safe object, I will implement a similar solution to the <b>producer - (priority consumer) problem</b></li>
 *         <li>To assist with the mutex access to the queue we will use a {@link java.util.concurrent.ConcurrentLinkedQueue} class.</li>
 *     </ol>
  *     <b>NOTE:</b> I choose a {@link java.util.concurrent.ConcurrentLinkedQueue} instead of a {@link java.util.concurrent.LinkedBlockingQueue} because
  *     of the "wait-free" approach that deals with the multiple producers, for the consumer I will implement a lock where he waits if the queue is empty.<p>
 *      <i>3.1.3.1. Logger Activity Diagram</i>
 *      <p>
 *      <img src="traffic_log_flow.png" alt="traffic log flow"><p>
 * <p>
 * - <b>3.1.4. Traffic Counter [WatchDog]</b><p>
 *     <ol>
 *         <li>Will follow the singleton pattern. To guarantee it is always listening for traffic events.</li>
 *         <li>Will have 4 different counters (secured &amp; unsecured incoming &amp; secured &amp; unsecured outgoing).</li>
 *         <li>The update() method should also be synchronized.</li>
 *         <li>A timertask thread will flush the current counters and send the values to his listeners (when this method is called
 *         it should block any access to its counters).</li>
 *         <li>To assist with the mutex access to the shared attributes we will use an {@link java.util.concurrent.atomic.AtomicLong} class.</li>
 *     </ol>
 *      <i>3.1.4.1. Counter Activity Diagram</i>
 *      <p>
 *      <img src="traffic_count_flow.png" alt="traffic count flow"><p>
 * <p>
 * - <b>3.1.5. Transmission Strategy</b><p>
 *     <ol>
 *         <li>We will reuse and integrate the already in place transmission strategy develop by [ Henrique - 1150738@isep.ipp.pt ]</li>
 *     </ol>
 *      <i>3.1.5.1. Transmission Strategy Diagram</i>
 *      <p>
 *      <img src="transmission_strategy.png" alt="Transmission Strategy"><p>
 * <p>
  - <b>3.1.1. conversion to readable byte units</b><p>
  * <ol>
  *     <li>To convert the bytes when necessary I developed a byte to string formatter how reads a byte count (Long) and depending on
  *     the size it will return a more readable number with the appropriated unit (ex. B, kB, MB ...)
  *     <pre>
  *      {@code
  *          public static String readableByteCount(long bytes, boolean si) {

                 int unit = si ? 1000 : 1024;
                 if (bytes < unit) return bytes + " B";
                 int exp = (int) (Math.log(bytes) / Math.log(unit));
                 String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");

                return String.format("%.1f %sB", (bytes / Math.pow(unit, exp)), pre);
             }
  *      }
  *      </pre>
  * </ol>
  * <p>
 * <p>
 * <h3>3.2.3. Domain Model (<i>Excerpt</i>)</h3>
 * <p>
 * <img src="network_analyzer_dm.png" alt="Network Analyzer DM"><p>
 * <p>
 * <h3>3.3. Open Questions</h3>
 * <p><ol>
 * <li>What communication should be accounted?
 * <b>A: Only TCP.</b>
 * <li>Should the Graph adjust his units interval to be readable?
 * <b>A: Yes.</b>
 * </ol><p>
 * <p>
 * <p>
 * <h2>4. Tests</h2>
 * <p>
 * <p>
 * <h3>4.1. Unit Tests</h3>
 * <p><ol>
 * <li>TrafficEvent
  * <ul>
  *  <li>ensureAddressIsNotNull</li>
  *  <li>ensurePortIsNotNull</li>
  *  <li>ensureByteCountIsNotNull</li>
  *  <li>ensureByteCountIsNotNegative</li>
  * </ul>
 * </li>
 * <li>TrafficCount
  * <ul>
  *  <li>ensureSecuredIncomingCountIsNotNull</li>
  *  <li>ensureSecuredIncomingCountIsNotNegative</li>
  *  <li>ensureUnsecuredIncomingCountIsNotNull</li>
  *  <li>ensureUnsecuredIncomingCountIsNotNegative</li>
  *  <li>ensureSecuredOutgoingCountIsNotNull</li>
  *  <li>ensureSecuredOutgoingCountIsNotNegative</li>
  *  <li>ensureUnsecuredOutgoingCountIsNotNull</li>
  *  <li>ensureUnsecuredOutgoingCountIsNotNegative</li>
  * </ul>
  * </li>
 * <li>TrafficLogger
  * <ul>
  *  <li>ensureAllEventsAreConsumed</li>
  *  <li>ensureLoggerStoresAPublishedEvent</li>
  * </ul>
 * </li>
 * <li>TrafficCounter
  * <ul>
  *  <li>ensureOnlySecuredIncomingCounterIsIncremented</li>
  *  <li>ensureOnlyUnsecuredIncomingCounterIsIncremented</li>
  *  <li>ensureOnlySecuredOutgoingCountIsIncremented</li>
  *  <li>ensureOnlyUnsecuredOutgoingCounterIsIncremented</li>
  *  <li>ensureAfterFlushThatCountersAreResetToZero</li>
  * </ul>
 * </li>
 * <li>PublishTrafficEvent
  * <ul>
  *  <li>ensureEventIsPublished</li>
  * </ul>
 * </li>
 * <li>TrafficInputStream
  * <ul>
  *  <li>ensureAfterReadingThatAnEventIsPublished</li>
  * </ul>
 * </li>
 * <li>TrafficOutputStream
  * <ul>
  *  <li>ensureAfterWritingThatAnEventIsPublished</li>
  * </ul>
 * </li>
 * </ol><p>
  * <h3>4.2. Acceptance Tests</h3>
  * <p><ol>
  * <li>EnsureTrafficLoggerSendLogToListener
  * <ol>
  *  <li>Simulate a traffic event by reading an object through a TrafficInputStream</li>
  *  <li>Simulate a traffic event by writing an object through a TrafficOutputStream</li>
  *  <li>Verify if test subscriber received the event.</li>
  * </ol>
  * </li>
  * <li>EnsureTrafficCounterSendCountToListener
  * <ol>
  *  <li>Simulate a traffic event by reading an object through a TrafficInputStream</li>
  *  <li>Simulate a traffic event by writing an object through a TrafficOutputStream</li>
  *  <li>Verify if test subscriber received the event.</li>
  * </ol>
  * </li>
  * </ol>
  * <p>
 * <h3>4.3. Functional Tests</h3>
  * <p><ol>
  * <li>EnsureTrafficIsPlottedInGraph
  * <ol>
  *  <li>Simulate a traffic event by reading an object through a TrafficInputStream</li>
  *  <li>Simulate a traffic event by writing an object through a TrafficOutputStream</li>
  *  <li>Verify if graph has plotted any event.</li>
  *  <li>Verify log ui.</li>
  * </ol>
  * </li>
  * </ol>
 * <p>
 * <p>
 * <h2>5. Design</h2>
 * <p>
 * <h3>5.1. Publish a traffic event</h3>
  * <img src="publish_traffic_event_sd.png" alt="Publish Traffic Event SD">
 * <p>
 * <h3>5.2. Traffic Logger [Watchdog]</h3>
  * <img src="traffic_logger_sd.png" alt="Traffic Logger SD">
 * <p>
 * <h3>5.3. Traffic Counter [Watchdog]</h3>
  * <img src="traffic_counter_sd.png" alt="Traffic Counter SD">
 * <p>
  * <h3>5.4. Network Analyzer Extension Setup</h3>
  * <p>
  *  Following the examples from other extension below is the sequence diagram that demonstrates how to create an extension sidebar.
  *  <p>
  * <img src="extension_setup_sd.png" alt="Extension Setup SD">
  * <p>
 * <h3>5.5. Design Patterns and Best Practices</h3>
 * <p><ol>
  * <li>I used the <b>decorator pattern</b> to build the traffic input/output streams.</li>
  * <li>I used the singleton pattern to build the watchdogs (<b>carefully thought, before deciding to use a singleton</b>).</li>
  * <li>We used the <b>strategy pattern</b> to abstract the secure/unsecured transmission responsibility.</li>
 * </ol>
 * <p>
 * <h2>6. Integration/Demonstration</h2>
 * <p>
 * This FI was transverse to all IPC FI, because we had to change every output/input stream to use our custom stream, which sends
  * traffic messages.
 * <p>
 * <h2>7. Final Remarks</h2>
 * <p>
  *
 * <br>
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday [06/06/2017]</b><p>
 * I worked on:<p>
 * Yesterday
 * <p><ol>
 * <li>Prepare previous sprint demo [lang area].
 * </ol><p>
 * Today
 * <p><ol>
 * <li>Analise the code base and the partially implemented FI.
 * <li>Elaborate the analysis of the FI.
 * </ol><p>
 * Blocking:
 * <p>
 * -nothing-
 * <p>
 * <p>
 * <b>Wednesday [07/06/2017]</b><p>
 * I worked on:<p>
 * Yesterday
 * <p><ol>
 * <li>Analise the code base and the partially implemented FI.
 * <li>Elaborate the analysis of the FI.
 * </ol><p>
 * Today
 * <p><ol>
 * <li>Clarify requirments with product owner and update analysis.
 * <li>Elaborate tests.
 * </ol><p>
 * Blocking:
 * <p>
 * -nothing-
 * <p>
 * <b>Thursday [08/06/2017]</b><p>
  * I worked on:<p>
  * Yesterday
  * <p><ol>
  * <li>Clarify requirments with product owner and update analysis.
  * <li>Elaborate tests.
  * </ol><p>
  * Today
  * <p><ol>
  * <li>Complete Design.
  * </ol><p>
  * Blocking:
  * <p>
  * -nothing-
  * <p>
 * <b>Friday [09/06/2017]</b><p>
  * I worked on:<p>
  * Yesterday
  * <p><ol>
  * <li>Completed Design
  * </ol><p>
  * Today
  * <p><ol>
  * <li>Started implementing the domain concepts.
  * </ol><p>
  * Blocking:
  * <p>
  * -nothing-
  * <p>
 * <p>
 *
 * @author Daniel Gon&ccedil;alves [1151452@isep.ipp.pt]
 * on 30/05/17.
 */
 package lapr4.blue.s2.ipc.n1151452.netanalyzer;