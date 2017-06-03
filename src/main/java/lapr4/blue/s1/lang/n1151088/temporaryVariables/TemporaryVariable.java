/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1151088.temporaryVariables;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.util.ExpressionVisitor;

/**
 * A temporary variable in a formula.
 * @author Diana Silva - 1151088@isep.ipp.pt
 */
public class TemporaryVariable implements Expression {
    
    //FIXME
    /** The unique version identifier used for serialization */
    private static final long serialVersionUID = 7854180857828149859L;

    
    /** The name of the temporary variable     */
    private String name;
    
    /** The value of the temporary variable */
    private Value value;
    
    public TemporaryVariable(String name){
        this.name=name;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        return this.value;
    }
    
    public String getName(){
        return this.name;
    }
    
    @Override
    public String toString() {
            if (value.getType() == Value.Type.TEXT
             || value.getType() == Value.Type.DATE)
                    return "\"" + value.toString() + "\"";
            else
                    return value.toString();
    }

    @Override
    public Object accept(ExpressionVisitor  visitor) {
         return visitor.visitTemporaryVariable(this);
    }
}
