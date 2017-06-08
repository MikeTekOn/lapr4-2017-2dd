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
 *
 * @author Diogo Guedes
 */
public class IntermediateFunctionWizardController extends FunctionWizardController {

    public IntermediateFunctionWizardController(UIController ctrl) {
        super(ctrl);
    }

    public String calculateResult(String parameters, String syntax) throws FormulaCompilationException {
        String result = calculateFunction(parameters, syntax);

        System.out.println(result);
        uiController.getActiveCell().setContent(result);
        result = uiController.getActiveCell().getValue().toString();
        System.out.println(result);
        return result;
    }

    private String calculateFunction(String parameters, String syntax) {
        String result;
        String start = syntax.substring(0, syntax.indexOf("(") + 1);

        start = start + parameters + ")";
        result = start;
        return result;
    }

}
