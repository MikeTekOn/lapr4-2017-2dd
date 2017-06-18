/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150613.FunctionWizard.application;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.UIController;
import java.util.List;
import javax.swing.JTextField;
import lapr4.blue.s1.lang.n1060503.functionWizard.FunctionWizardController;
import lapr4.blue.s1.lang.n1151452.formula.compiler.ExcelExpressionCompiler;
import lapr4.red.s2.lang.n1150613.FunctionWizard.FunctionUtils;

/**
 * Represents the function wizard controller (Sprint 2 - LANG.04)
 * 
 * @author Pedro Chilro [1150800@isep.ipp.pt] on 17/06/17.
 * @author Diogo Guedes
 */
public class IntermediateFunctionWizardController extends FunctionWizardController {

    private FunctionUtils func;
    private int type;
    
    /* Changed the access modifier of this variable */
    protected ExcelExpressionCompiler exc = new ExcelExpressionCompiler();

    /**
     * intermediate function wizard controller
     *
     * @param ctrl interface controller
     */
    public IntermediateFunctionWizardController(UIController ctrl) {
        super(ctrl);
        func = new FunctionUtils();
    }

    /**
     * Returns the result of the formula with the given expression
     *
     * @param expression inserted expression
     * @return result result of the function
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     * @throws csheets.core.IllegalValueTypeException
     */
    protected String compile(String expression) throws FormulaCompilationException, IllegalValueTypeException {

        Value eval = exc.compile(uiController.getActiveCell(), expression, uiController).evaluate();
        return eval.toString();

    }

    /**
     * Returns the result of the function with the given parameters
     *
     * @param parameters inserted parameters
     * @param syntax identifier of the function
     * @return result result of the function
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     * @throws csheets.core.IllegalValueTypeException
     */
    public String calculateResult(String parameters, String syntax) throws FormulaCompilationException, IllegalValueTypeException {
        String result;

        result = func.calculateResult(parameters, syntax);
        result = compile(result);
        
        return result;
    }

    /**
     * gets all identifiers of the functions and operators
     *
     * @return all functions
     */
    @Override
    public List getFunctions() {

        return func.getFunctions();
    }

    /**
     * get syntax and build the expressation for function wizard
     *
     * @param identifier of function
     * @param i
     * @return get syntax and build the expressation for function wizard
     * @throws UnknownElementException to be caught
     */
    public String getSyntax(String identifier, int i) throws UnknownElementException {

        return func.getSyntax(identifier, i);
    }

    public int getType() {
        return type;
    }
    
    /* This is a change made by 1150800@isep.ipp.pt */
    public void setUpdatableBox(JTextField editBox) {
        exc.setUpdatableBox(editBox);
    }
    /* -------------------------------------------- */

}
