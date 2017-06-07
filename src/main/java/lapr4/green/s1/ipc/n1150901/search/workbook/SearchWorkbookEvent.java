/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.workbook;

import csheets.core.Workbook;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class SearchWorkbookEvent {

    /**
     * The workbook to be searched.
     */
    private final String workbookName;

    /**
     * The full constructor of the event.
     *
     * @param wbName The workbook name to be searched.
     */
    public SearchWorkbookEvent(String wbName) {
        workbookName = wbName;
    }

    /**
     * A getter of the workbook name.
     *
     * @return It returns the workbook name.
     */
    public String getWorkbookName() {
        return workbookName;
    }

}
