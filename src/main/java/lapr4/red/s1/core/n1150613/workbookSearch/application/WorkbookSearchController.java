/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch.application;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lapr4.red.s1.core.n1150613.workbookSearch.RegexUtil;

/**
 *
 * @author Diogo Guedes
 */
public class WorkbookSearchController {

    /**
     * Contentor to return desired cells information
     */
    private List<String> info;

    /**
     * Utilities from Regex
     */
    private RegexUtil util;
    private UIController ctrl;

    /**
     * Active workbook at the moment
     */
    private Workbook w;

    public WorkbookSearchController(UIController ctrl) {
        info = new ArrayList<>();
        this.ctrl = ctrl;

    }

    /**
     * The call to the method to search through the whole woorkbook and match
     * it's contents with the inserted regular expression.
     *
     * @param regex regular expression inserted by user
     * @return desired cells information in String array
     *
     */
    public List<String> checkifRegexMatches(String regex) {

        util = new RegexUtil(regex);
        w = ctrl.getActiveWorkbook();

        return util.checkifRegexMatches(w);
    }

}
