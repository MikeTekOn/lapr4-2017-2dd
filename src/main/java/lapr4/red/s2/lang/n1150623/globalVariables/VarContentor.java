package lapr4.red.s2.lang.n1150623.globalVariables;

import csheets.core.formula.Expression;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaBaseVisitor;

import java.io.Serializable;
import java.util.*;


/**
 * @author Guilherme Ferreira 1150623 on 08/06/2017.
 * Variable is the newly created class for Global and Temporary usage of variables
 */
public class VarContentor extends BlueFormulaBaseVisitor<Expression> implements Serializable {

    /** The temporary variables that have been collected */
    private List<Variable> variables;


    public VarContentor(){
        variables = new ArrayList<>();
    }

    public List<Variable> variables(){
        return this.variables;
    }

    public Expression getExpressionOfVariable(String tempVarName){

        for(Variable v : variables){
            if (v.getName().equalsIgnoreCase(tempVarName)) {
                return v.getExpression();
            }
        }
        throw new IllegalArgumentException("Unknown temporary variable");
    }

    public boolean update(Variable tempVar){
        if(tempVar == null){
            throw new IllegalArgumentException();
        }
        if(variables.contains(tempVar)) {
            variables.remove(tempVar);
            return variables.add(tempVar);
        } else {
            return variables.add(tempVar);
        }
    }
}
