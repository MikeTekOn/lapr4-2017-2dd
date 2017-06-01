/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1060503.functionWizard.ui;

import csheets.CleanSheets;
import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Pedro Fernandes
 */
public class FunctionWizardAction extends FocusOwnerAction {
    
    /** The user interface controller */
    protected UIController uiController;
    
    private static final String PATH = "res/img/fx.png";
    

    public FunctionWizardAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "";
    }
    
    protected void defineProperties() {        
        setEnabled(true);	
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource(PATH)));
    }

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
