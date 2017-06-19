/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros;

import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.green.s3.lang.n1150738.macros.MacroWithParameters;
import lapr4.green.s3.lang.n1150738.macros.application.Macro2Controller;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroList;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroWithName;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.util.List;

/**
 *
 * @author Diogo Santos
 * @author Henrique Oliveira
 */
public class ModifyMacroListController {

    private Macro2Controller macro2Controller;
    private MacroList macroList;
    private UIController uiC;

    public List<MacroWithName> getMacroList(Workbook w, UIController uiC) {
        macro2Controller = new Macro2Controller();
        macroList = w.getMacroList();
        this.uiC = uiC;
        macroList.setUIController(uiC);
        return macroList.getMacroList();
    }

    public boolean addMacro(String name, String macroCode, Spreadsheet s) throws MacroCompilationException {
        MacroWithParameters m = macro2Controller.compileMacro(macroCode, uiC, s);
        return macroList.addMacro(m.getName(), macroCode, s);
    }
    
    public boolean deleteMacro(String name){
        return macroList.removeMacro(name);
    }
    
    public boolean updateMacro(String name, String newName, String newMacroCode, Spreadsheet s) throws MacroCompilationException {
        MacroWithParameters m = macro2Controller.compileMacro(newMacroCode, uiC, s);
        return macroList.updateMacro(name, m.getName(), newMacroCode, s);
    }
}
