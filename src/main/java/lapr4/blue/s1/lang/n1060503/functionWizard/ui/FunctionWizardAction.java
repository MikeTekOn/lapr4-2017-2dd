/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1060503.functionWizard.ui;

import csheets.CleanSheets;
import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * represent the action for Function Wizard
 * @author Pedro Fernandes
 */
public class FunctionWizardAction extends FocusOwnerAction {
    
    /** The user interface controller */
    protected UIController uiController;
    
    private static final String PATH = "res/img/fx.png";
    
    /**
     * Creates a new action of Function Wizard
     * @param uiController interface controller
     */
    public FunctionWizardAction(UIController uiController) {
        this.uiController = uiController;
    }
    
    /**
     * name of the button
     * @return name of the button
     */
    @Override
    protected String getName() {
        return "";
    }
    
    /**
     * define all button properties
     */
    protected void defineProperties() {        
        setEnabled(true);	
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource(PATH)));
    }
    
    /**
     * build the Function Wizard window
     * @param e action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            final FunctionWizardUI functionWizardUI = new FunctionWizardUI(uiController);
        } catch (UnknownElementException ex) {
            JOptionPane.showMessageDialog(
                            null,
                            "Error! It was not possible open Function Wizard!",
                            "Function Wizard",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
