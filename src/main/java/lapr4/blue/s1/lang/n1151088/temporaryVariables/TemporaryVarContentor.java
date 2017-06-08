package lapr4.blue.s1.lang.n1151088.temporaryVariables;

import csheets.core.formula.Expression;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaBaseVisitor;


/**
 * An expression visitor that collects the temporary variables from an expression.
 *
 * @author Ricardo Catalão (1150385)
 * @author Diana Silva (1151088@isep.ipp.pt)
 *         on 02/06/2017
 */
public class TemporaryVarContentor extends BlueFormulaBaseVisitor<Expression> {
    
    /** The temporary variables that have been collected */
    private Set<TemporaryVariable> contentorTempVar;
    
    public TemporaryVarContentor(){ 
        contentorTempVar=new HashSet<>();
    }

   public Expression getExpressionTemporaryVariable(String tempVarName){
        Iterator it=contentorTempVar.iterator();
        TemporaryVariable tempVar;
        while(it.hasNext()){
            tempVar=(TemporaryVariable)it.next();
            if (tempVar.getName().equalsIgnoreCase(tempVarName)) {
                return tempVar.getExpression();
            }
        }
       throw new IllegalArgumentException("Unknown temporary variable");
   }
   
   public boolean update(TemporaryVariable tempVar){
       
       if(contentorTempVar.contains(tempVar)) {
            contentorTempVar.remove(tempVar);
            return contentorTempVar.add(tempVar);
        } else {
            return contentorTempVar.add(tempVar);
        }
   }

   public Set<TemporaryVariable> getVariablesSet(){
       return contentorTempVar;
   }
}