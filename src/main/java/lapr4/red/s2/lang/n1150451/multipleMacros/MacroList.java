/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros;

import java.util.HashSet;
import java.util.*;
import java.util.Set;
import lapr4.blue.s1.lang.n1151159.macros.Macro;

/**
 *
 * @author Diogo Santos
 */
public class MacroList {

    List<Macro> macroList;

    public MacroList() {
        macroList = new ArrayList<Macro>();
    }

    public List<Macro> getMacroList() {
        return macroList;
    }

    public boolean addMacro(Macro m) {
        if (m == null) {
            throw new IllegalArgumentException("Macro can't be null");
        }
        
        if (!checkExistence(m)) {
            return macroList.add(m);
        }
        return false;

    }

    public boolean removeMacro(Macro m) {
        return macroList.remove(m);
    }

    public boolean updateMacro(Macro previousMacro, Macro newMacro) {
        if (removeMacro(previousMacro)) {
            return addMacro(newMacro);
        }
        return false;
    }

    private boolean checkExistence(Macro m) {
        for (int i = 0; i < macroList.size(); i++) {
            if (m.equals(macroList.get(i))) {
                return true;
            }
        }
        return false;
    }
}
