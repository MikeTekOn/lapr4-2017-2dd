/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch.application;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lapr4.red.s1.core.n1150613.workbookSearch.RegexUtil;

/**
 *
 * @author Diogo Guedes
 */
public class WorkbookSearchController {

    private List<String> info;
    private RegexUtil util;
    private Workbook w;
    private Spreadsheet s;
    private Cell c;

    public WorkbookSearchController(Workbook w) {
        this.w = w;
        info = new ArrayList<>();
    }

    public List<String> checkifRegexMatches(String regex) {
        util = new RegexUtil(regex);

        Iterator<Spreadsheet> it = w.iterator();
        Iterator<Cell> itCell;

        while (it.hasNext()) {
            s = it.next();
            itCell = s.iterator();
            while (itCell.hasNext()) {
                c = itCell.next();

                if (util.checkIfMatches(c.getContent())) {
                    info.add(c.getContent() + " " + c.getAddress().getColumn() + " " + c.getAddress().getRow());
                }
            }
        }

        return info;
    }

}
