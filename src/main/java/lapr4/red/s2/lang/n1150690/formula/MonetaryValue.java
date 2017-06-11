/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.util.ExpressionVisitor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class MonetaryValue implements Expression {

    /**
     * The expressions of a macro.
     */
    private List<Expression> expressions;

    private BigDecimal value;

    public MonetaryValue(BigDecimal value) {
        this.value = value;
        this.expressions = new ArrayList<>();
    }
    
    public void addExpressions(List<Expression> expressions){
        this.expressions.addAll(expressions);
    }

    /**
     * Returns the value of the monetary value.
     *
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        Value value = null;
        for (Expression exp : expressions) {
            value = exp.evaluate();
        }
        return value;
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        return visitor.visitMonetaryValue(this);
    }

}
