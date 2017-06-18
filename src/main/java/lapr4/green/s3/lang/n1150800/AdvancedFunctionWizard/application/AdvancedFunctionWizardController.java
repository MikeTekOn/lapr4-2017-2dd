/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.application;

import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import javax.swing.JTextField;
import javax.swing.JTree;
import lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.domain.AbstractSyntaxTree;
import lapr4.red.s2.lang.n1150613.FunctionWizard.application.IntermediateFunctionWizardController;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class AdvancedFunctionWizardController extends IntermediateFunctionWizardController {
    
    /**
     * Builds an instance of the Controller with the given parameters
     * @param ctrl - the user interface controller
     */
    public AdvancedFunctionWizardController(UIController ctrl) {
        super(ctrl);
    }
    
    /**
     * Returns the result of the function with the given parameters
     * @param syntax - identifier of the function
     * @return the result of the function
     * @throws FormulaCompilationException
     * @throws IllegalValueTypeException 
     */
    public String calculateResult(String syntax) throws FormulaCompilationException, IllegalValueTypeException {
        return compile(syntax);
    }
    
    /**
     * Provides the Abstract Syntax Tree
     * @return the abstract syntax tree
     */
    public AbstractSyntaxTree abstractSyntaxTree() {
        return exc.abstractSyntaxTree();
    }
    
    /**
     * Provides the Abstract Syntax Tree in a JTree Swing format
     * @return the abstract syntax tree
     */
    public JTree syntaxTree() {
        return exc.abstractSyntaxTree().buildSyntaxTree();
    }

    /**
     * Provides the updatable edit box to the abstract syntax tree
     * @param editBox - the updatable edit box
     */
    public void updatableEditBox(JTextField editBox) {
        setUpdatableBox(editBox);
    }
    
}
