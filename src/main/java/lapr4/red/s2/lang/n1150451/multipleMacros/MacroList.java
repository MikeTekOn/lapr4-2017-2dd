/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros;

import csheets.core.Spreadsheet;
import java.io.Serializable;
import java.util.*;

import csheets.ui.ctrl.UIController;

/**
 *
 * @author Diogo Santos
 */
public class MacroList implements Serializable {

    List<MacroWithName> macroList;
    private transient UIController uiController;

    public MacroList() {
        macroList = new ArrayList<MacroWithName>();

    }

    public MacroList(UIController uiController) {
        macroList = new ArrayList<MacroWithName>();
        this.uiController = uiController;
    }

    public List<MacroWithName> getMacroList() {
        return macroList;
    }

    public boolean addMacro(String name, String code, Spreadsheet s) {
        return addMacro(new MacroWithName(name, code, s, uiController));
    }

    public boolean addMacro(MacroWithName m) {
        if (m == null) {
            throw new IllegalArgumentException("Macro can't be null");
        }

        if (!checkExistence(m)) {
            return macroList.add(m);
        }
        return false;

    }

    public boolean removeMacro(String m) {
        return macroList.remove(getMacroByName(m));
    }

    public boolean updateMacro(String previousMacroName, String name, String code, Spreadsheet s) {
        if (removeMacro(previousMacroName)) {
            return addMacro(name, code, s);
        }
        return false;
    }

    private boolean checkExistence(MacroWithName m) {
        for (int i = 0; i < macroList.size(); i++) {
            if (m.equals(macroList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public MacroWithName getMacroByName(String name) {
        for (int i = 0; i < macroList.size(); i++) {
            if (name.equals(macroList.get(i).getName())) {
                return macroList.get(i);
            }
        }
        return null;
    }

    void setUIController(UIController uiC) {
        this.uiController=uiC;
    }
}
