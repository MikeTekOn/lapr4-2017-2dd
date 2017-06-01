/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1060503.functionWizard;

import csheets.core.formula.Function;
import csheets.core.formula.FunctionParameter;
import csheets.core.formula.lang.Language;
import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the function wizard (Sprint 1 - LANG.04)
 * @author Pedro Fernandes
 */
public class FunctionWizardController {
    
    /** The user interface controller */
    private UIController uiController;
    
    /**
     * function wizard controller
     * @param uIController interface controller
     */
    public FunctionWizardController(UIController uIController){
        this.uiController = uIController;
    }
    
    /**
     * gets all identifiers of the functions
     * @return all functions
     */
    public List getFunctions(){
        ArrayList list = new ArrayList<>();
        for (Function f : Language.getInstance().getFunctions()){
            list.add(f.getIdentifier());
        }
        return list;
    }
    
    /**
     * gets the syntax and description of Function
     * key is the name
     * value the description
     * @param identifier
     * @return
     * @throws UnknownElementException 
     */
    public Map<String,String> getFunction(String identifier) throws UnknownElementException{
        Map<String,String> function = new HashMap<>();
        for(FunctionParameter fp :Language.getInstance().getFunction(identifier).getParameters()){
            function.put(fp.getName(), fp.getDescription());
        }
        return function;
    }

    
    /**
     * insert the syntax of the function in formula bar
     */
    public void insertSyntaxFormulaBar(){
        
    }
}
