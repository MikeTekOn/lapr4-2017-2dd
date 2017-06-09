package lapr4.red.s2.lang.n1150623.globalVariables;

import csheets.core.formula.Expression;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaBaseVisitor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * @author Guilherme Ferreira 1150623 on 08/06/2017.
 * Variable is the newly created class for Global and Temporary usage of variables
 */
public class VarContentor extends BlueFormulaBaseVisitor<Expression> implements Serializable {

    /** The temporary variables that have been collected */
    private Set<Variable> variables;


    public VarContentor(){
        variables = new HashSet<>();
    }

    public Set<Variable> variables(){
        return this.variables;
    }

    public Expression getExpressionOfVariable(String tempVarName){
        Iterator it=variables.iterator();
        Variable tempVar;
        while(it.hasNext()){
            tempVar=(Variable) it.next();
            if (tempVar.getName().equalsIgnoreCase(tempVarName)) {
                return tempVar.getExpression();
            }
        }
        throw new IllegalArgumentException("Unknown temporary variable");
    }

    public boolean update(Variable tempVar){
        if(variables.contains(tempVar)) {
            variables.remove(tempVar);
            return variables.add(tempVar);
        } else {
            return variables.add(tempVar);
        }
    }
}
