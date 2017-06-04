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
import java.util.Objects;

/**
 * A temporary variable in a formula.
 * @author Diana Silva - 1151088@isep.ipp.pt
 */
public class TemporaryVariable implements Expression {
    //FIXME
    /** The unique version identifier used for serialization */
    //private static final long serialVersionUID = 7854180857828149859L;


    /**
     * The name of the temporary variable
     */
    private final String name;

    /**
     * The value of the temporary variable
     */
    private Value value;
    
    public TemporaryVariable(String name, Value value){
        this.name=name;
        this.value=value;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
    
    /**
     * Update the temporary variable value
     * @param value 
     */
    public void updateValue(Value value){
        this.value=value;
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
    public Object accept(ExpressionVisitor visitor) {
        return visitor.visitTemporaryVariable(this);
    }
    
    @Override
     public boolean equals(Object other){
        if (!(other instanceof TemporaryVariable))
           return false;
        
        TemporaryVariable otherVar = (TemporaryVariable)other;
        return otherVar.getName().equals(this.getName());


    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }
}