/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150613.FunctionWizard.application;

import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1060503.functionWizard.FunctionWizardController;

/**
 * Represents the function wizard controller (Sprint 2 - LANG.04)
 *
 * @author Diogo Guedes
 */
public class IntermediateFunctionWizardController extends FunctionWizardController {

    /**
     * intermediate function wizard controller
     *
     * @param ctrl interface controller
     */
    public IntermediateFunctionWizardController(UIController ctrl) {
        super(ctrl);
    }

    /**
     * Returns the result of the function with the given parameters
     *
     * @param parameters inserted parameters
     * @param syntax identifier of the function
     * @return result result of the function
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     */
    public String calculateResult(String parameters, String syntax) throws FormulaCompilationException {
        String result = calculateFunction(parameters, syntax);

        uiController.getActiveCell().setContent(result);
        result = uiController.getActiveCell().getValue().toString();

        return result;
    }

    /**
     * Returns the expression of the function with the given parameters
     *
     * @param parameters inserted parameters
     * @param syntax identifier of the function
     * @return result result of the function
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     */
    private String calculateFunction(String parameters, String syntax) {
        String result;
        String start = syntax.substring(0, syntax.indexOf("(") + 1);

        start = start + parameters + ")";
        result = start;
        return result;
    }

}
