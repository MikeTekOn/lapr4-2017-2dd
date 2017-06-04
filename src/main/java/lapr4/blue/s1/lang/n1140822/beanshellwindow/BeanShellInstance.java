/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

import bsh.EvalError;
import bsh.Interpreter;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151159.macros.MacroController;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import org.bouncycastle.util.Strings;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class BeanShellInstance {

    /**
     * The current interpreter.
     */
    private Interpreter bshInterpreter;
    /**
     * Lines of code in the script.
     */
    private LinkedList<String> code;

    /**
     * Macros in the script.
     */
    private LinkedList<String> macro;
    /**
     * Map with the results. The string part of the map is not used at the moment, but can be checked in the future if needed.
     */
    private Map<String, Object> results;

    private UIController controller;

    /**
     * The macro controller.
     */
    private MacroController macroController = new MacroController();

    public BeanShellInstance(LinkedList<String> code, LinkedList<String> macro, UIController controller) {
        this.bshInterpreter = new Interpreter();
        this.code = code;
        this.macro = macro;
        results = new LinkedHashMap<>();
        this.controller = controller;
    }

    /**
     * Executes a set of instructions. If the instructions evaluation returns a
     * value it puts it on a map. Note that non mathematical operations will
     * execute as domain code but object will still be null.
     * Macros are extraneous to bean shell  interpreter - they are not evaluated by it
     *
     * @throws EvalError if instruction is not valid piece of code
     */
    public Map<String, Object> executeScript() throws EvalError, MacroCompilationException, IllegalValueTypeException {
        String appendedMacro = "";
        //@Renato Oliveira - Whoever is implementing 2nd part of this uc, this is how you pass variables inside bean shell. when you have the variable inside it, you can use it as you would in java
        bshInterpreter.set("uiController", controller);
        for (String codeLine : code) {
            Object evaluation = this.bshInterpreter.eval(codeLine);
            //evaluation can be null, this happens when you DONT evaluate a NON macro mathematical expression (if you alocate a variable inside bean shell while not using macros) and evaluate a command instead like, print
            if (evaluation != null) {
                results.put(codeLine, evaluation);
            }
        }
        //macros dont need to be evaluated in a line fashion, can be appended to a string and evaluated in bulk.
        for (String macro : macro) {
            appendedMacro+=macro+Strings.lineSeparator();
        }
        if (!appendedMacro.equals("")) {
            Value value = macroController.executeMacro(controller.getActiveSpreadsheet(), appendedMacro);
            results.put(appendedMacro, value.toString());
        }
        return results;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof BeanShellInstance) || otherObject == null) {
            return false;
        }
        LinkedList<String> other = ((BeanShellInstance) otherObject).code;
        if (this.code.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < code.size(); i++) {
            if (!code.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.code);
        return hash;
    }
}
