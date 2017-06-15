/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class Filter implements Serializable {
    
    private List<String> formulas;
    
    public Filter(List<String> formulas){
        
        this.formulas=formulas;
        
    }

    /**
     * @return the formulas
     */
    public List<String> getFormulas() {
        return formulas;
    }

    /**
     * @param formulas the formulas to set
     */
    public void setFormulas(List<String> formulas) {
        this.formulas = formulas;
    }
    
}
