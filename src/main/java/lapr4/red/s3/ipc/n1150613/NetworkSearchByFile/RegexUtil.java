/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author Diogo Guedes
 */
public class RegexUtil {

    protected String content;
    protected String name;
    protected Pattern p;
    protected Matcher m;

    public RegexUtil(String name, String content) {
        this.content = content;
        this.name = name;
    }

    /*
      The call to the method to check if the regular expression is valid
      @return true if valid or false if not
     */
    public boolean isRegexValid() {
        boolean test = true;
        try {
            Pattern.compile(content);
            Pattern.compile(name);
        } catch (PatternSyntaxException exception) {
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
    public boolean checkIfNameMatches(String filename) {

        p = Pattern.compile(name);
        m = p.matcher(filename);
        return (m.matches());
    }

    private boolean checkContent(String cont) {
        p = Pattern.compile(content);
        m = p.matcher(cont);
        return (m.matches());
    }

    /*
      The call to the method to search through the whole woorkbook and match
      it's contents with the inserted regular expression.
     
      @param w Active workbook
     
      @return desired cells information in String array
     */
    public boolean checkIfContentMatches(Workbook w) {
        String check;
        boolean is = true;

        if (!isRegexValid()) {
            System.out.println("Invalid regex");
            return false;
        }

        for (Spreadsheet s : w) {
            for (Cell c : s) {
                check = c.getValue().toString();
                if (!checkContent(check)) {
                    is = false;
                }
            }
        }

        return is;
    }
}
