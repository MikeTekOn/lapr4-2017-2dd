/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150613.FunctionWizard.ui;

import csheets.ui.ctrl.UIController;
import javax.swing.JButton;

/**
 * Represent the button of Function Wizard
 *
 * @author Pedro Fernandes
 */
public class IntermediateFunctionWizard extends JButton {

    /**
     * The user interface controller
     */
    private UIController uiController;

    /**
     * Inicialize the Action of Function Wizard
     *
     * @param uiController interface controller
     */
    public IntermediateFunctionWizard(UIController uiController) {
        this.uiController = uiController;

        setAction(new IntermediateFunctionWizardAction(uiController));
    }

}
