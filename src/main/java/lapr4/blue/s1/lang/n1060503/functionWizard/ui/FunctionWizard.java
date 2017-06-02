/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1060503.functionWizard.ui;

import csheets.ui.ctrl.UIController;
import javax.swing.JButton;

/**
 *
 * @author Pedro Fernandes
 */
public class FunctionWizard extends JButton{
    
    /** The user interface controller */
    private UIController uiController;
    
    public FunctionWizard(UIController uIController){
        this.uiController = uiController;
        
        setAction(new FunctionWizardAction(uIController));
    }
    
}
