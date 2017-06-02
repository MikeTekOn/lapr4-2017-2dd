/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

import bsh.EvalError;
import bsh.Interpreter;
import csheets.ui.ctrl.UIController;
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
     * Map with the results.
     */
    private Map<String, Object> results;

    UIController controller;

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
     *
     * @throws EvalError if instruction is not valid piece of code
     */
    public Map<String, Object> executeScript() throws EvalError {
        bshInterpreter.set("uiController", controller);
        for (String codeLine : code) {
            Object evaluation = this.bshInterpreter.eval(codeLine);
            if (evaluation != null) {
                results.put(codeLine, evaluation);
            }
        }
        for (String string : macro) {
                //TODO: call ivo's controllers
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
