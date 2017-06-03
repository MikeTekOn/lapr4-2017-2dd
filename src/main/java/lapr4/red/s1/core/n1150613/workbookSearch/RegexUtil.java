/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author Diogo Guedes
 */
public class RegexUtil {

    private String regExpression;
    private Pattern p;
    private Matcher m;

    public RegexUtil(String regex) {
        this.regExpression = regex;
    }

    /**
     * The call to the method to check if the regular expression is valid
     *
     * @return true if valid or false if not
     */
    public boolean isRegexValid() {
        boolean test = true;
        try {
            Pattern.compile(regExpression);
            System.out.println("Valid");
        } catch (PatternSyntaxException exception) {
            System.out.println("Invalid!");
            test = false;
        }
        return test;
    }

    /**
     * The call to the method to check if the cellContent matches with the
     * regular expression
     *
     * @param cellContent content of the cell
     * @return true if it matches or false if it doesn't
     */
    public boolean checkIfMatches(String cellContent) {

        p = Pattern.compile(regExpression);
        m = p.matcher(cellContent);
        return (m.matches());
    }

}
