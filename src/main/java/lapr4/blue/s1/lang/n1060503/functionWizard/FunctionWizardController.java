/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1060503.functionWizard;

import csheets.core.formula.Function;
import csheets.core.formula.FunctionParameter;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.lang.Language;
import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the function wizard controller (Sprint 1 - LANG.04)
 * @author Pedro Fernandes
 */
public class FunctionWizardController {
    
    /** The user interface controller */
    private final UIController uiController;
    
    private static final String EQUAL = "=";
    private static final String LEFT_PAR = "(";
    private static final String RIGHT_PAR = ")";
    private static final String SEMICOLON = ";";
    
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
     * gets description of Function
     * key is the name
     * value the description
     * @param identifier of function
     * @return the description of Function
     * @throws UnknownElementException to be caught
     */
    public Map<String,String> getDescription(String identifier) throws UnknownElementException{
        Map<String,String> function = new HashMap<>();
        for(FunctionParameter fp :Language.getInstance().getFunction(identifier).getParameters()){
            function.put(fp.getName(), fp.getDescription());
        }
        return function;
    }
    
    /**
     * get syntax and build the expressation for function wizard
     * @param identifier of function
     * @return get syntax and build the expressation for function wizard
     * @throws UnknownElementException to be caught
     */
    public String getSyntax(String identifier) throws UnknownElementException{
        String aux = EQUAL + identifier + LEFT_PAR;
        int count = 0;
        for (FunctionParameter fs : Language.getInstance().getFunction(identifier).getParameters()){
            if(count == 0){
                aux += fs.getName();
            }else{
                aux += SEMICOLON + fs.getName();
            }            
            count++;
        }
        aux += RIGHT_PAR;
        return aux;
    }
    
    /**
     * insert the syntax of the function in formula bar
     * @param syntax syntax of the function
     * @throws csheets.core.formula.compiler.FormulaCompilationException to be caught
     */
    public void insertSyntaxFormulaBar(String syntax) throws FormulaCompilationException{
            uiController.getActiveCell().setContent(syntax);
    }
    
}
