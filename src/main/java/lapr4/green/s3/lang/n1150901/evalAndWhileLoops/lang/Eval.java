/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150901.evalAndWhileLoops.lang;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Function;
import csheets.core.formula.FunctionParameter;

/**
 * A function that compiles the formula contained in the only parameter and
 * executes the resulting expression.
 *
 * @author Miguel Silva - 1150901
 */
public class Eval implements Function {

    /**
     * Parameters: function, function range, condition and condition range
     */
    public static final FunctionParameter[] parameters = new FunctionParameter[]{
        new FunctionParameter(Value.Type.TEXT, "String", false,
        "The text to be evaluated and executed."),};

    /**
     * Creates a new instance of the Eval function.
     */
    public Eval() {
    }

    @Override
    public String getIdentifier() {
        return "EVAL";
    }

    @Override
    public Value applyTo(Expression[] arguments) throws IllegalValueTypeException {

        for (Expression expression : arguments) {
            Value value = expression.evaluate();
            if (value.getType() == Value.Type.NUMERIC) {
//                sum += value.toDouble();
            } else if (value.getType() == Value.Type.MATRIX) {
                for (Value[] vector : value.toMatrix()) {
                    for (Value item : vector) {
                        if (item.getType() == Value.Type.NUMERIC) {
//                            sum += item.toDouble();
                        } else {
                            throw new IllegalValueTypeException(item, Value.Type.NUMERIC);
                        }
                    }
                }
            } else {
                throw new IllegalValueTypeException(value, Value.Type.NUMERIC);
            }
        }
        return new Value(/*sum*/);
    }

    @Override
    public FunctionParameter[] getParameters() {
        return parameters;
    }

    @Override
    public boolean isVarArg() {
        return false;
    }
}
