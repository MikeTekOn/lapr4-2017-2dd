/**
 * Technical documentation regarding the user story IPC03.1- Search in Another Instance.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 * <p>
 *
 * <h2>1. Notes</h2>
 * No notes to be reported.
 *
 * <h2>2. Requirement</h2>
 * <b>Statement:</b><p>
 * It should be possible to send a request for searching workbooks to another
 * instance of Cleansheets. The search should be based on the name of the
 * workbook (a pattern of the name). The search should only include workbooks
 * that are open in the remote instance of Cleansheets. The reply must inform if
 * the workbook was found or not. If the workbook was found then the reply must
 * also include a summary of the contents of the workbook. This summary should
 * include the name of the worksheets and the values of the first non-empty
 * cells of each worksheet.
 *
 * <h2>3. Analysis</h2>
 * <b>SSD:</b><p>
 * <img src="us081_ssd.png" alt="ssd">
 * <p>
 * <b>Analysis SD:</b><p>
 * <img src="us081_analysis.png" alt="analysis">
 * <p>
 * This is a very early analysis of what this user story is supposed to be.
 * It main contain incoherences that can be corrected when a further investigation
 * of the code is performed. The development of the design can also help with
 * eventual mistakes.
 *
 *
 * @author Miguel Silva (1150901)
 */
package lapr4.green.s1.ipc.n1150901.search.instance;
