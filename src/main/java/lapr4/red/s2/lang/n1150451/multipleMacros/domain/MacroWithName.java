/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros.domain;

import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.util.ExpressionVisitor;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import csheets.ui.ctrl.UIController;
import java.util.Objects;
import lapr4.blue.s1.lang.n1151159.macros.Macro;
import lapr4.blue.s1.lang.n1151159.macros.MacroController;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.red.s2.lang.n1150451.multipleMacros.application.MultipleMacrosWithNameController;

/**
 *
 * @author Diogo Santos
 */
public class MacroWithName implements Expression, Serializable {

    private String name;
    private String macroCode;
    private Spreadsheet s;
    private transient UIController uiController;

    public MacroWithName(String name, String macroCode, Spreadsheet s, UIController uiController) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        this.macroCode = macroCode;
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Code can't be empty");
        }
        this.name = name;
        this.s = s;
        this.uiController = uiController;
    }

    public String getName() {
        return name;
    }

    public String getMacroCode() {
        return macroCode;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        MultipleMacrosWithNameController c = new MultipleMacrosWithNameController();
        try {
            return c.executeMacro(s, uiController, macroCode, name);
        } catch (MacroCompilationException ex) {
            Logger.getLogger(MacroWithName.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MacroWithName other = (MacroWithName) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
