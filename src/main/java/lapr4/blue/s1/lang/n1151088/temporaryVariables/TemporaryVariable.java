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
    private Expression expression;
    
    public TemporaryVariable(String name, Expression expression){
        this.name=name;
        this.expression=expression;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        return this.expression.evaluate();
    }

    public String getName() {
        return this.name;
    }
    
    public Expression getExpression() {
        return this.expression;
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

    @Override
    public Object accept(ExpressionVisitor visitor) {
    
        return this.expression.accept(visitor);
    }
    
    @Override
    public String toString(){
        return this.getName() + this.expression.toString();
    }
}