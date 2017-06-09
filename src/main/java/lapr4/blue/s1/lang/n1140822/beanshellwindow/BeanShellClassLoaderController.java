/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

import bsh.EvalError;
import csheets.core.IllegalValueTypeException;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;

import java.io.FileNotFoundException;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class BeanShellClassLoaderController {

    /**
     * Creates and executes a script using the various implemented bean shell
     * classes.
     *
     * @param scriptName the name of the script
     * @param controller the UIcontroller - to persist changes in the visible
     * workbook
     * @return the bean shell result class instance
     */
    public BeanShellResult createAndExecuteScript(String scriptName, UIController controller) throws FileNotFoundException, EvalError, MacroCompilationException, IllegalValueTypeException {
        BeanShellLoader loader = new BeanShellLoader();
        BeanShellResult result = null;
        BeanShellInstance instance = loader.create(scriptName, controller, null);
        result = new BeanShellResult(instance.executeScript());
        return result;
    }
}
