/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.domain;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Diogo Santos
 */
public class WorkbookHandler {
    
    private final Workbook w;
    
    public WorkbookHandler(Workbook w) {
        this.w = w;
    }
    
    public List<Cell> getListCellsWorkBook() {
        List<Cell> list = new ArrayList<>();
        Iterator<Spreadsheet> it = w.iterator();
        while (it.hasNext()) {
            list.addAll(getListCellsSpreadSheet(it.next()));
            
        }
        return list;
    }
    
    public List<Cell> getListCellsSpreadSheet(Spreadsheet s) {
        List<Cell> list = new ArrayList<>();
        int maxColumn = Address.HIGHEST_CHAR - Address.HIGHEST_CHAR;
        for (int i = 0; i < maxColumn; i++) {
            list.addAll(Arrays.asList(s.getColumn(i)));
        }
        return list;
    }
}
