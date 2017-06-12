/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.domain;

import java.util.List;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class Filter {
    /**
     * The types to include in the search
     */
    private List<String> typesToInclude;
    /**
     * The formulas to include in the search
     */
    private List<String> formulasToInclude;
    /**
     * True if are to include comments in the search
     */
    private boolean includeComments;
    
    /**
     * The filter constructor
     * @param typesToInclude
     * @param formulasToInclude
     * @param includeComments
     */
    public Filter(List<String> typesToInclude,List<String> formulasToInclude,boolean includeComments){
        
        if(typesToInclude == null || formulasToInclude==null ){
            throw new IllegalStateException();
        }
        this.typesToInclude=typesToInclude;
        this.formulasToInclude=formulasToInclude;
        this.includeComments=includeComments;
    }

    /**
     * @return the typesToInclude
     */
    public List<String> getTypesToInclude() {
        return typesToInclude;
    }

    /**
     * @return the formulasToInclude
     */
    public List<String> getFormulasToInclude() {
        return formulasToInclude;
    }

    /**
     * @return the includeComments
     */
    public boolean isIncludeComments() {
        return includeComments;
    }
}
