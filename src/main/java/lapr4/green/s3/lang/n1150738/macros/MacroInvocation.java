package lapr4.green.s3.lang.n1150738.macros;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.util.ExpressionVisitor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by henri on 6/19/2017.
 */
public class MacroInvocation implements Expression {

    private final MacroWithParameters macro;
    private final ParameterList params;

    public MacroInvocation(MacroWithParameters macro, ParameterList params){
        try{
            macro.getParameterDefinition().validateParameterList(params);
        } catch ( InvalidParameterListException ex) {
            throw new IllegalArgumentException("Invalid Parameter List");
        }
        this.macro = macro;
        this.params = params;
    }


    @Override
    public Value evaluate() throws IllegalValueTypeException {
        try {
            return macro.executeMacro(params);
        } catch (InvalidParameterListException ex) {
            Logger.getLogger(MacroInvocation.class.getName()).log(Level.FINEST, "Invalid Parameter List on evaluate. (This should never be reached)", ex);
        }
        return new Value();
    }


    @Override
    public Object accept(ExpressionVisitor visitor) {
        return null;
    }
}
