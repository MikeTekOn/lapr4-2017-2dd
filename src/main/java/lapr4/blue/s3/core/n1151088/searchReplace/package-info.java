/**
 * Technical documentation regarding the user story Core07.3: Search and Replace
 * <p>
 * JIRA issue: LAPR4E17DD-23
 * </p>
* <p>
 * <b>Scrum Master: no</b>
 * </p>
 * <p>
 * <b>Area Leader: no</b>
 *</p>
 * <h2>1. Notes</h2>
 *
 * <p>This functional increment is the continuation of the <b>Core07.2: Global Search</b>
 * 2017/06/13- started analysing the user story
 * </p>
 * 
 * <h2>2. Requirements</h2>
 * <p>
 * The extension should now include a new option "Search and Replace". This new
 * window should be similar to the search window but with an area to enter the 
 * replacing text. When search and replace is launched, when a match is found, 
 * the window should display "what" and where the match has occurred and how it
 * will become after the replace. The user should then confirm the replacement 
 * or select next (to continue the search). The window should include a button 
 * to apply the replacing to all the matches without asking each time. Similarly 
 * to the search only option, this option should also have parameters to refine
 * the search, for instance, what type of cells to include in the search (or if 
 * it should include formulas or comments).
 * </p>
 *
 * <b>Use Case "Search and Replace":</b>
 * 
 * <img src="core07_3_ssd.png" alt="image">
 * 
 * <b>Open Questions</b>
 * <p>When the window display "what", in case of a sentence/coment, how it
 * should be displayed?
 *   Answer: It doesn´t needed to be included all content but maybe the adjacent
 * text.
 * </p>
 * 
 * <h3>3. Analysis</h3>
 * 
 * <img src="core_07_03_ssd.png" alt="image">
 * 
 * <h2>8. Work Log</h2> 
 *
 * <b>Monday</b>
 * <ol>
 *      <li>Analyzing the Core07.2: Global Search functional (last sprint that 
 * will be incremented)</li>
 * </ol>
 *
 * <b>Tuesday</b>
 *  <ol>
 *     <li>Studying the requirements</li>
 *     <li>New sprint planning</li>
 * </ol> 
 *
 * <b>Wednesday</b>
 * <ol>
 *     <li>Elaborate the anaylsis.</li>
 * </ol>
 * 
 * @author Diana Silva - 1151088@isep.ipp.pt - 2DD - 2016/17
 */

package lapr4.blue.s3.core.n1151088.searchReplace;
