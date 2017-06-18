/**
 * Technical documentation regarding the user story Core 03.3: Auto sorting.
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
 * It is required that once we select a range of cells, they are to be sorted
 * automatically. This is, persist the sorting after each change of the value of
 * the cells that are in the given range. A visual mark is to be showed so the
 * user can know for sure which range of cells is being auto sorted, the column
 * that defines the sorting and the order of the sorting. If the user clicks in
 * a column that is withing the range of sorting, this column should become the
 * pivot for sorting. If the user clicks on the column and this is already the
 * pivot, the sorting order should be changed.
 *
 * <p>
 * <b>Use Case "Auto sorting":</b> The user selects a range , sorting algorithm
 * and pivot column for the sorting. The sorting stays active and is invoked
 * everytime a cell within the range is changed.
 *
 *
 * <h2>3. Analysis</h2>
 * The base functions of sorting are already implemented. In this FI is not
 * needed to implement anything regarding sorting algorithms and such. However,
 * it is needed to auto sort a selected range of columns. It must be possible to
 * have various ranges auto sorting their values. Because of this and other
 * reasons, it is impossible to use a singleton - the information will be
 * different for each range and sorting algorithm. In this case, the best
 * approach would be to use threads and observer pattern. The threads, will not
 * be active threads, meaning they will will not be processing data at all
 * times. They will be waiting for an event thrown by the observable. This
 * observable will be the cells themselves. When a value is changed in any cell
 * (event for this is already implemented), they will notify all observers.
 * Since this notification is broadcasted through all observers, all threads
 * sorting will receive it. However, we will send the address of the event
 * source to these threads, and each will know if the given cell is within the
 * sorting range. If it is, it shall sort them again using the information
 * stored in these threads.
 *
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the
 * previously described use case. This is will serve as a draft for the design
 * phase.
 * <p>
 * <img src="analysis_sd.png" alt="image">
 * <p>
 *
 * We can see here that the user doesnt have much interaction with the system,
 * only when defining the sorting type, and indirectly when changing cell values
 * or pivot columns.
 * <h3>Analysis of Core Technical Problem</h3>
 * <p>
 * From the analysis, we can reach some conclusions. First, we will need a
 * dedicated thread for each sorting. This is because each sorting has its own
 * information like range, algorithm etc. Normally this approach would not be
 * viable, because each thread takes processing power. This is solved by putting
 * them in waiting state and only waking them up when they capture an event
 * thrown by a observable object. Other problem is, for instance, if we change
 * the sorting order. We would need to update a boolean variable within the
 * thread itself. Manipulating this from the outside is bad practice. We will do
 * all variable updates within the update method of the observer thread. We've
 * been talking about observers and observables, but havn't defined yet which
 * domain object has those functions. Because the uiController has all the
 * spreadsheet and cells changed events, it should be the observed one. This is
 * because, one it fires for instance the cell value changed event, it shall
 * also notify all observers. The observers are going to be these sorting
 * threads created. A single thread, doesnt have all the comparators and
 * algorithm used built by its factories, like the controller has them
 * aggregated. Instead , since it is dedicated to a single sorting, it will have
 * but one slot for each, meaning, one sorting algorithm. As said before, this
 * can be changed in runtime by event capture. Another problem is, these events
 * arent all triggered by cell changed, but also by column clicks. If we click a
 * column and this column is within the given range of any sorting threads, this
 * column should become the new pivot. For this, we have to add an action
 * listener to the table header of cleansheets. When this is clicked, it should
 * it should notify the sorting thread. For this, we will implement another listener
 * and register it in the uiController. This listener will only be dedicated to header
 * table actions.
 * <p>
 * - Classes added -
 * <p>
 * AutoSortingThread
 * <p>
 * <p>
 * SortingFocusOwner
 * <p>
 * <p>
 *
 * <h2>4. Design</h2>
 *
 * After the initial analysis and research, it was understood that we dont need
 * to use the observer pattern, at least for cells. We already have a interface
 * called CellListener. By implementing this and registering this on a
 * spreadsheet, everytime a value is changed, it will execute the overriden
 * methods of all cell listener implementations. Note thats functionality basis
 * is the same as using the observer pattern, except it is already implemented
 * and structured. But with this approach, another problem exists. The current
 * sorting method itself, when changing the cells, also fires the value changed
 * event (when a cells content is changed). If this doesn't cause a stack
 * overflow exception, the sorting will surely fail anyway. In order to prevent
 * this cycle, we need to implement a method that sets content but doesnt fire
 * this event. This event must only be called within the sorting method which is
 * already implemented. This method is not without its flaws. If there is any
 * formulas depending on these cells values, since some events might not be
 * broadcast, these formulas results might be unreliable. To implement this
 * fully, more time is needed to change the core application - we should be able
 * to tell from ui interaction cell changed events and other types of cell
 * changing events. In this case, the events we're interested are the ui
 * interaction cell changed events, since these are the ones in which we would
 * use to call the sort method.
 * <p>
 * We also need to ensure , since it should be able to have more than one sorting areas active,
 * that each thread only operates within the selected range. This means that each operation of this FI
 * - clicking on the column to change order, clicking on other column to change pivot - will only do this
 * if the selected range is the one which was initialized in the dedicated thread.
 * To achieve this objective, we need to know in runtime, which cells are selected. For this we will create
 * an inner class which implements FocusOwner. This interface, gives us access to the selected cells
 * within cleansheets. We need only to match these cells with our own cell structures, and should it match,
 * perform the required operations.
 * <p>
 * <p>
 * After all these operations, we will need to provide visual cues to ensure the user knows
 * which column is the pivot one, which area (or areas ) are being sorted, and the sorting order.
 * I have made the decision to not use the decorator pattern, and instead use the stylable cell extension.
 * We will paint borders around the sorting area, and paint the pivot column with different colors, depending
 * on the sorting order.
 * <p>
 * <h3>4.1. Functional Tests</h3>
 *
 * <p>
 * Unit test 1 -
 * <p>
 * <h3>4.2. UC Realization</h3>
 * To realize this functional increment, we will need the classes referred in
 * the analysis section and the already implemented ones. The sequence diagram below
 * will depict the transactions showed in the SSD in the analysis section
 * containing all needed classes for the implementation.
 * <p>
 * Sequence Diagram 1 - Creating a automatic sorting area
 * <p>
 * <p>
 * <img src="design.png" alt="image">
 * <p>
 *
 * <p>
 * Sequence Diagram 2 - Changing pivot Column
 * <p>
 * <p>
 * <img src="design_2.png" alt="image">
 * <p>
 * <h3>4.3. Classes</h3>
 *
 * <p>
 * <img src="cd.png" alt="image">
 * <p>
 *
 * <h3>4.4. Design Patterns and Best Practices</h3>
 *
 *
 * <h2>5. Implementation</h2>
 *
 * Added the planned classes. Added junit testing to guarantee domain rules are
 * enforced.
 *
 * <p>
 *
 *
 * <h2>6. Integration/Demonstration</h2>
 *
 * Integration with the previous functional increment was easy, since it was well developed and documented
 *
 * <h2>7. Final Remarks</h2>
 *
 * <p>
 *
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
 * 1. Analysis of the use case
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis of the use case
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
 * 1. Finishing the design and started the domain junit testing.
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
 * Finished the junit tests and started the implementation.
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
 * 1. Continued the implementation.
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
package lapr4.blue.s3.core.n1140822.autoSorting;
