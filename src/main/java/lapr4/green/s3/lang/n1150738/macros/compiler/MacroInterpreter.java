package lapr4.green.s3.lang.n1150738.macros.compiler;


import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.*;
import csheets.core.formula.lang.CellReference;
import csheets.core.formula.util.ExpressionVisitor;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable;
import lapr4.blue.s1.lang.n1151159.macros.Macro;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperation;
import lapr4.green.s3.lang.n1150738.macros.MacroWithParameters;
import lapr4.green.s3.lang.n1150738.macros.ParameterList;
import lapr4.green.s3.lang.n1150738.macros.ParameterReference;
import lapr4.red.s2.lang.n1150690.formula.MonetaryValue;

/**
 * A custom interpreter for macros
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class MacroInterpreter implements ExpressionVisitor {

    private MacroWithParameters macro;

    private ParameterList paramList;

    public MacroInterpreter(MacroWithParameters macro, ParameterList paramList) {
        this.macro = macro;
        this.paramList = paramList;
    }

    @Override
    public Value visitLiteral(Literal literal) {
        return literal.evaluate();
    }


    @Override
    public Value visitUnaryOperation(UnaryOperation operation) {
        try {
            Expression operand = operation.getOperand();
            if(operand instanceof ParameterReference){
                operand = new Literal((Value)operand.accept(this));
            }
            return operation.getOperator().applyTo(operand);
        } catch (IllegalValueTypeException ex) {
            return new Value();
        }

    }

    @Override
    public Value visitBinaryOperation(BinaryOperation operation) {
        try {
            Expression[] operands = {operation.getRightOperand(),operation.getLeftOperand()};
            for (int i = 0; i < operands.length; i++) {
                if (operands[i] instanceof ParameterReference) {
                    //The reference need to be replaced with its real value
                    operands[i] = new Literal((Value) operands[i].accept(this));

                } else if(! (operands[i] instanceof CellReference)){
                    operands[i] = new Literal((Value) operands[i].accept(this));
                }
            }
            return operation.getOperator().applyTo(operands[1], operands[0]);
        } catch (IllegalValueTypeException ex) {
            return new Value();
        }
    }

    @Override
    public Value visitNaryOperation(NaryOperation operation) {
        try {
            Expression[] operands = operation.getOperands();
            for (int i = 0; i < operands.length; i++) {
                if (operands[i] instanceof ParameterReference) {
                    //The reference need to be replaced with its real value
                    operands[i] = new Literal((Value) operands[i].accept(this));
                } else if(! (operands[i] instanceof CellReference)){
                    operands[i] = new Literal((Value) operands[i].accept(this));
                }
            }
            return operation.getOperator().applyTo(operands); //override evaluate()
        } catch (IllegalValueTypeException ex) {
            return new Value();
        }
    }

    @Override
    public Value visitReference(Reference reference) {
        return reference.evaluate();
    }

    @Override
    public Value visitFunctionCall(FunctionCall call) {
        try {

            Expression[] funcArgs = call.getArguments();
            for (int i = 0; i < funcArgs.length; i++) {
                if (funcArgs[i] instanceof ParameterReference) {
                    //The reference need to be replaced with its real value
                    funcArgs[i] = new Literal((Value) funcArgs[i].accept(this));
                }else if(! (funcArgs[i] instanceof CellReference)){
                    funcArgs[i] = new Literal((Value) funcArgs[i].accept(this));
                }
            }
            return call.getFunction().applyTo(funcArgs); //override evaluate()
        } catch (IllegalValueTypeException ex) {
            return new Value();
        }
    }

    @Override
    public Value visitMacro(Macro macro) {
        Value value = null;
        for (Expression exp : macro.expressions()) {
            value = (Value) exp.accept(this);
        }
        return value;
    }

    @Override
    public Value visitTemporaryVariable(Variable tempVar) {
        try {
            Value ret = tempVar.evaluate();
            return ret;
        } catch (IllegalValueTypeException ex) {
            return new Value();
        }
    }

    @Override
    public Value visitMonetaryValue(MonetaryValue money) {
        try {
            Value ret = money.evaluate();
            return ret;
        } catch (IllegalValueTypeException ex) {
            return new Value();
        }
    }

    @Override
    public Value visitParameterReference(ParameterReference parameterReference) {
        if (macro.getParameterDefinition().contains(parameterReference.name())) {
            return paramList.getParameterValue(parameterReference.name());
        }
        return null;
    }


}
