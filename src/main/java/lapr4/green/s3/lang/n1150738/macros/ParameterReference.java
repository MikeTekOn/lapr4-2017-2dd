package lapr4.green.s3.lang.n1150738.macros;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.util.ExpressionVisitor;
import eapli.util.Strings;

/**
 * Created by henri on 6/18/2017.
 */
public class ParameterReference implements Expression {

    private String paramName;

    public ParameterReference(String paramName){
        if(Strings.isNullOrEmpty(paramName)){
            throw new IllegalArgumentException("Invalid Parameter Reference");
        }
        this.paramName = paramName;
    }

    public String name() {
        return paramName;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        throw new UnsupportedOperationException("This method cannot be used");
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        return visitor.visitParameterReference(this);
    }
}
