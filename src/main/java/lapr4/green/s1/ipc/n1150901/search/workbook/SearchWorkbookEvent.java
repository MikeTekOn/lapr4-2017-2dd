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
     * The workbook to be searched..
     */
    private final Workbook workbook;

    /**
     * The full constructor of the event.
     *
     * @param wb The workbook to be searched.
     */
    public SearchWorkbookEvent(Workbook wb) {
        workbook = wb;
    }

    /**
     * A getter of the workbook.
     *
     * @return It returns the workbook
     */
    public Workbook getWorkbook() {
        return workbook;
    }

}
