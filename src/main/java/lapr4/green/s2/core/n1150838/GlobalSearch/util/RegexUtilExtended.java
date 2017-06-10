/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.util;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.io.File;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;
import lapr4.green.s2.core.n1150838.GlobalSearch.domain.CellInfoDTO;
import lapr4.green.s2.core.n1150838.GlobalSearch.domain.Filter;
import lapr4.red.s1.core.n1150613.workbookSearch.RegexUtil;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;

/**
 *
 * @author nunopinto
 */
public class RegexUtilExtended extends RegexUtil implements Runnable {

    private UIController extension;
    private Filter filter;

    public RegexUtilExtended(Filter filter, String regex, UIController extension) {
        super(regex);
        this.extension = extension;
        this.filter = filter;
    }

    @Override
    public void run() {

        searchWorkbooks();

    }

    public void searchWorkbooks() {
        int workbookCount = 0;
        for (Workbook workbook : extension.getActiveWorkbooks()) {
            int spreadsheetNumber = 0;
            workbookCount++;
            File workbookFile = extension.getFile(workbook);
            String workbookName;
            if (workbookFile == null) {
                workbookName = "Untitled-" + workbookCount;
            } else {
                workbookName = workbookFile.getName();
            }
            for (Spreadsheet spreadsheet : workbook) {
                spreadsheetNumber++;
                for (Cell cell : spreadsheet) {
                    if (validateFilters(cell, filter.getTypesToInclude(), filter.getFormulasToInclude())) {
                        if (checkIfMatches(cell)) {

                            GlobalSearchPublisher.getInstance().notifyObservers(new CellInfoDTO(cell, spreadsheetNumber, workbook, workbookName));
                        }
                    }
                }
            }
        }

    }

    /*
      The call to the method to check if the cellContent matches with the
      regular expression
     
      @param cellContent content of the cell
      @return true if it matches or false if it doesn't
     */
    public boolean checkIfMatches(Cell cell) {

        p = Pattern.compile(regex);
        m = p.matcher(cell.getContent());
        boolean test1 = m.matches();
        boolean test2 = false;
        if (cell.getFormula() != null) {
            m = p.matcher(cell.getValue().toString());
            test2 = m.matches();
        }
        if (!filter.isIncludeComments()) {
            return test1 ? true : (test2 ? true : false);
        }
        boolean test3 = false;

        for (List<String> comments : ((CommentableCellWithMultipleUsers) cell.getExtension(CommentsExtension.NAME)).comments().values()) {
            for (String comment : comments) {
                m = p.matcher(comment);
                test3 = m.matches();
                if (test3 == true) {
                    return true;
                }
            }
        }

        return test1 ? true : (test2 ? true : false);

    }

    public boolean containsFormulas(Cell cell, List<String> formulas) {
        for (String formula : formulas) {
            if (cell.getFormula().toString().equals(formula.substring(1))) {
                return true;
            }
        }
        return false;
    }

    public boolean validateFilters(Cell cell, List<String> types, List<String> formulas) {
        if (types.isEmpty() && formulas.isEmpty()) {
            return true;
        } else if (validateType(cell, types) || validateFormulas(cell, formulas)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateType(Cell cell, List<String> types) {
        for (String type : types) {
            if (cell.getValue().getType().toString().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateFormulas(Cell cell, List<String> formulas) {
        for (String formula : formulas) {

            if (cell.getFormula() != null && containsFormulas(cell, formulas)) {
                return true;
            }
        }

        return false;
    }

}
