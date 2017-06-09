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

    private List<String> typesToInclude;
    private List<String> formulasToInclude;
    private boolean includeComments;
    
    public Filter(List<String> typesToInclude,List<String> formulasToInclude,boolean includeComments){
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
