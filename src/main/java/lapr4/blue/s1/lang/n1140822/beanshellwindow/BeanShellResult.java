/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

import java.util.Map;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class BeanShellResult {

    /**
     * Map with line of code and result object of that lines evaluation.
     */
    Map<String, Object> results;

    /**
     * Constructor for this class.
     * @param results a map with line of code and object of the evaluation of that line.
     */
    public BeanShellResult(Map<String, Object> results) {
        this.results = results;
    }
   
    public String lastResult()
    {
        return results.values().toArray()[results.values().size()-1].toString();
    }
}
