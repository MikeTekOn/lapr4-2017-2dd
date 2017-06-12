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
import eapli.util.Strings;

import java.io.Serializable;
import java.util.Objects;

/**
 * A temporary variable in a formula.
 * @author Diana Silva - 1151088@isep.ipp.pt
 *
 *
 * @author Guilherme Ferreira 1150623 - Made changes to class so it would be a subclass of Variable [on 08/06/2017]
 */
public class Variable implements Expression, Serializable {

    /**expression of variable*/
    Expression expression;

    /**Name of variable*/
    String name;


    //FIXME
    /** The unique version identifier used for serialization */
    //private static final long serialVersionUID = 7854180857828149859L;


    public Variable(String name, Expression expression){

        if(Strings.isNullOrEmpty(name) | expression == null){
            throw new IllegalArgumentException("Name cant be empty or null and expression can't be null");
        }
        this.name = name;
        this.expression = expression;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        return expression.evaluate();
    }

    public String getName() {
        return name;
    }
    
    public Expression getExpression() {
        return expression;
    }
    
    @Override
     public boolean equals(Object other){
        if (!(other instanceof Variable))
           return false;
        
        Variable otherVar = (Variable)other;
        return otherVar.getName().equals(this.getName());


    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(name);
        return hash;
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
    
        return expression.accept(visitor);
    }
    
    @Override
    public String toString(){
        return this.getName() + expression.toString();
    }
}