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
import csheets.core.formula.Expression;
import csheets.core.formula.util.ExpressionVisitor;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable;
import lapr4.blue.s1.lang.n1151159.macros.MacroController;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.red.s2.lang.n1150385.beanshell.BeanShellAsyncRunner;
import lapr4.red.s2.lang.n1150385.beanshell.Instruction;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * @author Ricardo Catalão (1150385)
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class BeanShellInstance implements Expression {

    /**
     * The current interpreter.
     */
    private Interpreter bshInterpreter;

    /**
     * Lines of code in the script.
     */
    private LinkedList<Instruction> code;

    /**
     * Map with the results. The string part of the map is not used at the moment, but can be checked in the future if needed.
     */
    private Map<String, Object> results;

    private UIController controller;

    private VarContentor tempVarContainer;

    private boolean asynchronous = false;

    /**
     * The macro controller.
     */
    private MacroController macroController = new MacroController();

    public BeanShellInstance(LinkedList<Instruction> instructions, UIController controller, VarContentor tempVarContentor) {
        this.bshInterpreter = new Interpreter();
        this.code = instructions;
        results = new LinkedHashMap<>();
        this.controller = controller;
        this.tempVarContainer = tempVarContentor;
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
        if(asynchronous){
            new Thread(new BeanShellAsyncRunner(this)).start();
            return new LinkedHashMap<>();
        }else{
            bshInterpreter.set("uiController", controller);
            if(tempVarContainer != null){
                for(Variable var : tempVarContainer.variables()){
                    bshInterpreter.set(var.getName(), var.getExpression().evaluate().toAny());
                }
            }

            for (Instruction instruction : code) {
                switch(instruction.getType()){
                    case BEANSHELL:
                        Object evaluation = this.bshInterpreter.eval(instruction.getInstruction());
                        if (evaluation != null)
                            results.put(instruction.getInstruction(), evaluation);
                        break;
                    case MACRO:
                        Value value = macroController.executeMacro(controller.getActiveSpreadsheet(), controller, instruction.getInstruction());
                        results.put(instruction.getInstruction(), value.toString());
                        break;
                }
            }
            return results;
        }
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof BeanShellInstance) || otherObject == null) {
            return false;
        }
        LinkedList<Instruction> other = ((BeanShellInstance) otherObject).code;
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

    public boolean setAsynchronous(){
        this.asynchronous = true;
        return this.asynchronous;
    }

    public boolean setSynchronous(){
        this.asynchronous = false;
        return this.asynchronous;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        BeanShellResult result = null;
        try {
            result = new BeanShellResult(executeScript());
            if(result != null && result.lastResult() != null){
                return new Value(result.lastResult());
            }else{
                return null;
            }
        } catch (EvalError evalError) {
            evalError.printStackTrace();
        } catch (MacroCompilationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        return null;
    }
}
