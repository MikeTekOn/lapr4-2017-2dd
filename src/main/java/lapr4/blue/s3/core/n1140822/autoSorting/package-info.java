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
 * It is required that once we select a range of cells, they are to be sorted automatically. This is, persist the sorting after each change of the value of the cells that are in the given range.
 * A visual mark is to be showed so the user can know for sure which range of cells is being auto sorted, the column that defines the sorting and the order of the sorting.
 * If the user clicks in a column that is withing the range of sorting, this column should become the pivot for sorting. If the user clicks on the column and this is already the pivot, the sorting order should be changed.
 * 
 * <p>
 * <b>Use Case "Auto sorting":</b>  The user selects a range , sorting algorithm and pivot column for the sorting. The sorting stays active and is invoked everytime a cell within the range is changed.
 * 
 *  
 * <h2>3. Analysis</h2>
 *  The base functions of sorting are already implemented. In this FI is not needed to implement anything regarding sorting algorithms and such. However, it is needed to auto sort a selected range of columns. It must be possible to have various ranges auto sorting their values.
 *  Because of this and other reasons, it is impossible to use a singleton  - the information will be different for each range and sorting algorithm. In this case, the best approach would be to use threads and observer pattern.
 *  The threads, will not be active threads, meaning they will will not be processing data at all times. They will be waiting for an event thrown by the observable. This observable will be the cells themselves. When a value is changed in any cell (event for this is already implemented), 
 * they will notify all observers. Since this notification is broadcasted through all observers, all threads sorting will receive it. However, we will send the address of the event source to these threads, and each will know if the given cell is within the sorting range. 
 *  If it is, it shall sort them again using the information stored in these threads.
 * 
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the previously described use case. This is will serve as a draft for the design phase.
 * <p>
 * <img src="analysis_sd.png" alt="image"> 
 * <p>
 * 
 *  We can see here that the user doesnt have much interaction with the system, only when defining the sorting type, and indirectly when changing cell values or pivot columns.
 * <h3>Analysis of Core Technical Problem</h3>
 * <p>
 * From the analysis, we can reach some conclusions. First, we will need a dedicated thread for each sorting. This is because each sorting has its own information like range, algorithm etc. Normally this approach would not be viable, because each thread takes processing power.
 * This is solved by putting them in waiting state and only waking them up when they capture an event thrown by a observable object. 
 * Other problem is, for instance, if we change the sorting order. We would need to update a boolean variable within the thread itself. Manipulating this from the outside is bad practice. We will do all variable updates within the update method of the observer thread.
 * We've been talking about observers and observables, but havn't defined yet which domain object has those functions. Because the uiController has all the spreadsheet and cells changed events, it should be the observed one. This is because, one it fires for instance the 
 * cell value changed event, it shall also notify all observers. The observers are going to be these sorting threads created.  
 * A single thread, doesnt have all the comparators and algorithm used built by its factories, like the controller has them aggregated. Instead , since it is dedicated to a single sorting, it will have but one slot for each, meaning, one sorting algorithm. As said before, this can be changed in runtime by event capture.
 * Another problem is, these events arent all triggered by cell changed, but also by column clicks. If we click a column and this column is within the given range of any sorting threads, this column should become the new pivot.
 * For this, we have to add an action listener to the table header of cleansheets. When this is clicked, it should notify observers, an event which should be caught by the active sorting threads and changing the sorting column.
 * <p>
 * <p>
 *  FileShareController
 * <p>
 * <p>
 * 
 * <h2>4. Design</h2>
 * After the initial analysis and research, it was understood that we dont need to use the observer pattern, at least for cells. We already have a interface called CellListener. By implementing this and registering this on a spreadsheet, everytime a value is changed, it will execute the overriden methods of all cell listener implementations.
 * Note thats functionality basis is the same as using the observer pattern, except it is already implemented and structured.
 * <h3>4.1. Functional Tests</h3>
 * The domain of this functional increment has already been tested. There is not much room for testing since our implementations ignore local requests.
 *  <p>
 *  Unit test 1 -  testHandlerFileList
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
 * From this sequence diagram it can be seen that we need a method to update the list of shared files (we get the dto, but how can we use the packet we read to update our user interface?).
 * Other problem we have is putting the source of the file in the input list. The method to reconstruct a file, is retrieving an array of bytes and writing those bytes into a path. Now, how can we, when we download a file, know who sent it? To achieve this, we will need to add metadata 
 *  to the downloaded file. Java already offers data structures to do this. Note that when reading the file (for instance , when we launch the application and see our downloaded files) we will also need to read that metadata.
 * <p>
 *  About the persisted download and shared folder settings, cleansheets already has some kind of local file settings persistence, the properties class and implementation. It has some default properties which are loaded when opening cleansheets.
 *  There is also an implementation of a class called NamedProperties. We will need to add our configuration settings to these data structures and save them when we exit a running instance, so they persist when opening. Properties has a map which we put the key (the name of the setting) and the value. 
 *  After we update these values, when we close the instance it will automatically save them. The loading is quite easy too. It loads the defaults first, and then it overwrites the settings with the user saved ones.
 * <p>
 * It was also necessary to see if a downloaded file is outdated or up to date. Normally, this would just be more metadata added to the file and read while we fill the download folder. The tricky part is, we'd need to send this data not when the user downloads a file, but when instance one
 * broadcasts its file names. This doesnt work because we only send the file names, we dont even have the file structure constructed yet. Since the download of files was not expected to be implemented in this sprint (although it will be implemented), the details of this functionality are a bit fuzzy.
 * As such, and since the downloads will be implemented, the file state will be compared using the size of the file (exactly to the bit, its sure to work in most cases). If a file you have downloaded is also being broadcast, if their sizes differ, your downloaded file will be out of date. 
 * <p>
  *<p>
 * <h3>4.3. Classes</h3>
 * 
 * <p>
 * <img src="class_diagram.png" alt="image"> 
 * <p>
 * 
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * In this use case, we use the observer pattern. Everytime we receive a file list from another client's broadcast, we notify the observers (which in this case is a list of available files). Note that observer must be implemented and observable extended in respective data structures.
 * We also use the Singleton pattern, for the configuration of the download and shared folders and to acess tcp and udp servers.
 * 
 * <h2>5. Implementation</h2>
 * 
 * Added the planned classes.
 * Added junit testing to guarantee domain rules are enforced.
 * Implemented the dto classes
 * Implemented the dto handlers
 * Added handlers into the needed servers
 * Added ui implementation
 *
 * <p>
 * 
 * 
 * <h2>6. Integration/Demonstration</h2>
 * 
 * Integration with the current networking structure was very harsh, there was a need to study this structure and respect the domain rules imposed by it, as such, when implementing this FI we tried to ensure these rules were enforced.
 * 
 * <h2>7. Final Remarks</h2>
 * Future possible changes and improvements: Like depicted in the second FI. Progress bar, permanent downloads
 * <p>
 * As an extra this use case already allows for downloads. It already tells the user if the file is up to date or outdated.
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
package lapr4.blue.s3.core.n1140822.autoSorting;