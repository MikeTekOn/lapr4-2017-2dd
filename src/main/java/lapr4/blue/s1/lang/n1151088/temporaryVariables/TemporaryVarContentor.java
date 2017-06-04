package lapr4.blue.s1.lang.n1151088.temporaryVariables;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.util.AbstractExpressionVisitor;
import csheets.core.formula.util.ExpressionVisitor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An expression visitor that collects the temporary variables from an expression.
 *
 * @author Diana Silva (1151088@isep.ipp.pt)
 *         on 02/06/2017
 */
public class TemporaryVarContentor extends AbstractExpressionVisitor{
    
    /** The temporary variables that have been collected */
    private HashMap<String,TemporaryVariable> temp_contentor;
   
    
    public TemporaryVarContentor(){ }
    
    /**
    * Traverses the given expression and returns the temporary variables that were found.
    * @param expression the expression from which to fetch temporary variables
    * @return the temporary variables that have been fetched
    */
   public HashMap getTemporaryVariables() {
        return temp_contentor;
   }
   
   /**
    * Visit a parse tree produced by {@link FormulaParser}.
    * 
    * @param tempVar temporary variable to visit
     * @return the visitor result
    */
   @Override
   public Object visitTemporaryVariable(TemporaryVariable tempVar){
       if(!this.temp_contentor.containsKey(tempVar.getName()))  
          temp_contentor.put(tempVar.getName(), tempVar);
      
       else{
        
           try {
               changeValue(tempVar);
           } catch (IllegalValueTypeException ex) {
               Logger.getLogger(TemporaryVarContentor.class.getName()).log(Level.SEVERE, null, ex);
           }
       
       }
       return tempVar;
   }
   
   private void changeValue(TemporaryVariable tempVar) throws IllegalValueTypeException{
       Set set=temp_contentor.entrySet();
       Iterator it=set.iterator();
       while(it.hasNext()){
           TemporaryVariable temp=(TemporaryVariable)it.next();
           if(tempVar.equals(temp)){
               temp.updateValue(tempVar.evaluate());
           }
       }
   }
}