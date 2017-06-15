/**
 * Technical documentation regarding the user story IPC02.3 - Multiple Realtime Workbook Search.
 * <p>
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- yes</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 *
 * <h2>2. Requirement</h2>
 * It should now be possible to have several search windows. Each search window
 * should have an option, for instance a check button, to indicate if the search
 * is an active search. An active search will be a search that keeps updating
 * the contents of the list that displays the results. The preview for each file
 * should now be cached so that Cleansheets only produces the preview the first
 * time the user request it or when the contents of the file change.
 *
 * <p>
 *
 * <h2>3. Analysis</h2>
 * <p>
 * Having in mind the previous interation, it was fully completed and it is
 * fully funciontaly. With that, it is mandatory to undestand a few points:
 *
 * <h3>3.1. How will the software run searches in different windows?</h3>
 *
 * In order to "divide" the search work by diferent windows, a thread system
 * should be implemented. Each thread should be responsible for a search.
 *
 * <p>
 *
 * <h3>3.2. How will the information be presented?</h3>
 *
 * In order to use the thread system in a more efficient way,a tab system will
 * be used. The main idea is, if a search is an active search, the tab font
 * color will be red, else it will be black. This takes us to the next
 * question...
 *
 * <h3>3.3. How will the system process an active search in comparison with a
 * non active on?</h3>
 * <p>
 * If the search is active, this means that will continuously search for files
 * in the file system. Having that in mind, a non active search will only search
 * when the user, for example, clicks a button and then it stops when the user
 * exits the tab or clicks in the button that now says stop button. On the
 * contrary, an active search keeps searching even if the user changes tab.
 * <h2>4. Design</h2>
 *
 *
 * <h3>4.1. Functional Tests</h3>
 * 
 * <h3>4.2. UC Realization</h3>
 * 
 * <h3>4.3. Classes</h3>
 * 
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * <p>
 * <h2>5. Implementation</h2>
 * 
 * <h2>6. Integration/Demonstration</h2>
 * 
 * <h2>7. Final Remarks</h2>
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday 06/06/2017</b>
 * <p>
 * Yesterday: our team distributed the funcionalities to be worked on this
 * sprint.
 * <p>
 * Today: I started the analysis process.
 * <p>
 * Blocking:---
 * <p>
 * <b>Wednesday 07/06/2017</b>
 * <p>
 * Yesterday: I ifinshed the analysis for the first part of the UC.
 * <p>
 * Today: Finish the design, tests and if possible the implementation for the
 * first part
 * <p>
 * Blocking:
 * <p>
 * <b>Thursday 1/06/2017 </b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Friday 2/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 * <b>Monday 5/06/2017</b>
 * <p>
 * Yesterday:
 * <p>
 * Today:
 * <p>
 * Blocking:
 * <p>
 *
 * <h2>9. Self Assessment</h2>
 *
 * <h3>R3. Rubric Requirements Fulfilment: </h3>
 *
 * <h3>R6. Rubric Requirements Analysis: </h3>
 *
 * <h3>R7. Rubric Design and Implement: </h3>
 *
 *
 * @author Diogo Santos 1150451@isep.ipp.pt
 */
package lapr4.red.s3.ipc.n1150451.multipleRealtimeWorkbookSearch;
