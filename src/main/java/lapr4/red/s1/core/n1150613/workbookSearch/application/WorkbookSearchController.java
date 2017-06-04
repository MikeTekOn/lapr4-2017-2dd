/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch.application;

import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;

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
     *
     * @return desired cells information in String array
     *
     */
    public List<String> searchInWorkbook() {

        w = ctrl.getActiveWorkbook();

        return util.searchInWorkbook(w);
    }

    /**
     * The call to the method to check if regex is valid
     *
     * @param regex desired regular expression
     * @return true if valid or false if not
     */
    public boolean checkIfValid(String regex) {
        util = new RegexUtil(regex);
        return util.isRegexValid();
    }

}
