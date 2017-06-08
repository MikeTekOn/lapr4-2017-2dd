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
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;
import lapr4.green.s2.core.n1150838.GlobalSearch.domain.Filter;
import lapr4.red.s1.core.n1150613.workbookSearch.RegexUtil;

/**
 *
 * @author nunopinto
 */
public class RegexUtilExtended extends RegexUtil implements Runnable {

    private Stack<Workbook> workbooks;
    private Filter filter;

    public RegexUtilExtended(Filter filter, String regex, Stack<Workbook> workbooks) {
        super(regex);
        this.workbooks = workbooks;
        this.filter = filter;
    }

    @Override
    public void run() {

        if (filter.isIncludeComments()) {
            searchWorkbooksWithComments();
        } else {
            searchWorkbooksWithoutComments();
        }

    }

    public void searchWorkbooksWithComments() {

    }

    public void searchWorkbooksWithoutComments() {

        for (Workbook workbook : workbooks) {
            for (Spreadsheet spreadsheet : workbook) {
                for (Cell cell : spreadsheet) {
                    String cellContentFound;
                    String comentContentFound;
                    int spreadsheetNumber;
                    if(validateFilters(cell,filter.getTypesToInclude(),filter.getFormulasToInclude())){
                      
                        if(checkIfMatches(cell)){
                            System.out.println(cell);
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
        if(cell.getFormula()!= null){
         m = p.matcher(cell.getValue().toString());   
         test2 = m.matches();
        }
    
        return test1 ? true :  (test2 ? true : false);
    }
    
    public boolean containsFormulas(Cell cell,List<String> formulas){
        for (String formula : formulas) {
            if(cell.getFormula().toString().equals(formula.substring(1))) return true;
        }
        return false;
    }
    
    public boolean validateFilters(Cell cell,List<String> types, List<String> formulas){
        if(types.isEmpty() && formulas.isEmpty()){
            return true;
        }else if(validateType(cell,types) || validateFormulas(cell,formulas)){
            return true;
        }else{
            return false;
        }
    }

    public boolean validateType(Cell cell, List<String> types) {
        for (String type : types) {
            if(cell.getValue().getType().toString().equals(type)){
                return true;
            }
        }
        return false;
    }
    
    public boolean validateFormulas(Cell cell , List<String> formulas ){
        for (String formula : formulas) {
            
            if(cell.getFormula()!=null && containsFormulas(cell,formulas))
                    {
                return true;
            }
        }
            
        return false;
    }

}
