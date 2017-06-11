/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros.application;

import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import lapr4.blue.s1.lang.n1151159.macros.MacroController;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompiler;

/**
 *
 * @author Diogo Santos
 */
public class MultipleMacrosWithNameController extends MacroController {


    /**
     * Executes a given macro.
     *
     * @param spreadsheet the spreadsheet where the macro is going to be
     * executed
     * @param macroText the macro text
     * @return value of the last executed expression
     * @throws MacroCompilationException macro compilation exception
     * @throws IllegalValueTypeException illegal value type exception
     */
    public Value executeMacro(Spreadsheet spreadsheet, UIController uiController, String macroText, String macroName) throws MacroCompilationException, IllegalValueTypeException {
        if (macroList == null) {
            macroList = new ArrayList<>();
        }
        Expression macro = MacroCompiler.getInstance().compile(spreadsheet, uiController, macroText, macroName, macroList);
        if (macro == null) {
            return null;
        }
        return macro.evaluate();
    }
    public void emptyList(){
        macroList=null;
    }
}
