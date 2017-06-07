/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.util;

import csheets.core.Workbook;
import java.util.Stack;
import lapr4.red.s1.core.n1150613.workbookSearch.RegexUtil;

/**
 *
 * @author nunopinto
 */
public class RegexUtilExtended extends RegexUtil {
    
    private Stack<Workbook> workbooks;
    
    public RegexUtilExtended(String regex,Stack<Workbook> workbooks) {
        super(regex);
    }
    
}
