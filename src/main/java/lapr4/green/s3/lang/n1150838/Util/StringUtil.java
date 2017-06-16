/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.Util;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class StringUtil {
    
    public static String removeStartEndSpecialChars(String stringToRemove){
        String newString = stringToRemove.substring(1);
        return newString.substring(0, newString.length()-1);
    }
    
}
