/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

// @author Diogo Guedes
public class RegexUtil {

    private List<String> info;
    private String regex;
    private Pattern p;
    private Matcher m;

    public RegexUtil(String regex) {
        this.regex = regex;
        info = new ArrayList();
    }

    /*
      The call to the method to check if the regular expression is valid
     
      @return true if valid or false if not
     */
    public boolean isRegexValid() {
        boolean test = true;
        try {
            Pattern.compile(regex);
        } catch (PatternSyntaxException exception) {
            System.out.println("Invalid!");
            test = false;
        }
        return test;
    }

    /*
      The call to the method to check if the cellContent matches with the
      regular expression
     
      @param cellContent content of the cell
      @return true if it matches or false if it doesn't
     */
    public boolean checkIfMatches(String cellContent) {

        p = Pattern.compile(regex);
        m = p.matcher(cellContent);
        return (m.matches());
    }

    /*
      The call to the method to search through the whole woorkbook and match
      it's contents with the inserted regular expression.
     
      @param w Active workbook
     
      @return desired cells information in String array
     */
    public List<String> checkifRegexMatches(Workbook w) {
        Spreadsheet s;
        Cell c;
        String content;
        int cont = 0;

        if (!isRegexValid()) {
            return null;
        }

        Iterator<Spreadsheet> it = w.iterator();
        Iterator<Cell> itCell;

        while (it.hasNext()) {
            s = it.next();
            cont++;
            itCell = s.iterator();
            while (itCell.hasNext()) {
                c = itCell.next();
                content = c.getValue().toString();
                if (checkIfMatches(content)) {
                    content = content.substring(0, Math.min(content.length(), 10));
                    info.add(content + " Spreadsheet:" + cont + " Adress:" + c.getAddress().toString());
                }
            }
        }

        return info;
    }

}
