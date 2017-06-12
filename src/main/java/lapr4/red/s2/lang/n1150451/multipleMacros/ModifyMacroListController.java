/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros;

import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroList;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroWithName;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.util.List;

/**
 *
 * @author Diogo Santos
 */
public class ModifyMacroListController {

    private MacroList macroList;

    public List<MacroWithName> getMacroList(Workbook w, UIController uiC) {
        macroList = w.getMacroList();
        macroList.setUIController(uiC);
        return macroList.getMacroList();
    }

    public boolean addMacro(String name, String macroCode, Spreadsheet s) {
        return macroList.addMacro(name, macroCode, s);
    }
    
    public boolean deleteMacro(String name){
        return macroList.removeMacro(name);
    }
    
    public boolean updateMacro(String name, String newName, String newMacroCode, Spreadsheet s) {
        return macroList.updateMacro(name, newName, newMacroCode, s);
    }
}
