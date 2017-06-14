package lapr4.red.s2.lang.n1150623.globalVariables;

import csheets.core.formula.Expression;

import java.io.Serializable;
import java.util.*;
import lapr4.green.s3.lang.n1150532.variables.Variable;


/**
 * @author Guilherme Ferreira 1150623 on 08/06/2017.
 * Variable is the newly created class for Global and Temporary usage of variables
 * 
 * 
 * @author Manuel Meireles (1150532):
 * <ul>
 * <li>I've removed the unused BlueFormulaBaseVisitor<Expression>
 * extension.</li>
 * <li>I've added the Observable extension.</li>
 * <li>I've created new methods for "getExpressionOfVariable" and "update" in
 * order to handle the indexes and the observable pattern (the old ones should
 * be disregarded from this point on).</li>
 * <li>I've changed the Variable class (from
 * {@link lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable} to
 * {@link lapr4.green.s3.lang.n1150532.variables.Variable}).</li>
 * </ul>
 */
public class VarContentor extends Observable implements Serializable {

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
                return v.getExpressionAt(Variable.DEFAULT_INDEX);
            }
        }
        throw new IllegalArgumentException("Unknown temporary variable");
    }

    /**
     * The method provides the expression stored at a specific index of the
     * variable. It follows the previous implementation logic.
     * 
     * @param tempVarName The variable's name in which to search.
     * @param index The index of the variable's array from which to extract the
     * expression.
     * @return It returns the expression stored at the specific index of the
     * variable.
     */
    public Expression getExpressionOfVariable(String tempVarName, int index){
        for(Variable v : variables){
            if (v.getName().equalsIgnoreCase(tempVarName)) {
                return v.getExpressionAt(index);
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

    /**
     * It stores the expression at the variable's index. If the variable does
     * not exist yet, it creates a new variable and adds it to the container.
     * 
     * @param tempVarName The name of the variable.
     * @param index The index in which to store the expression.
     * @param theExpression The expression to be stored.
     */
    public void update(String tempVarName, int index, Expression theExpression){
        Variable theVariable = null;
        for(Variable v : variables){
            if (v.getName().equalsIgnoreCase(tempVarName)) {
                theVariable = v;
            }
        }
        if(theVariable==null){
            theVariable = new Variable(tempVarName);
            variables.add(theVariable);
        }
        theVariable.setExpressionAt(index, theExpression);
    }

}
