package lapr4.blue.s1.lang.n1151088.temporaryVariables;

import csheets.core.formula.Expression;
import csheets.core.formula.util.AbstractExpressionVisitor;
import csheets.core.formula.util.ExpressionVisitor;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperation;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An expression visitor that collects the temporary variables from an expression.
 *
 * @author Diana Silva (1151088@isep.ipp.pt)
 *         on 02/06/2017
 */
public class TemporaryVarContentor extends AbstractExpressionVisitor {

    /**
     * The temporary variables that have been collected
     */
    private SortedSet<TemporaryVariable> temp_contentor;


    public TemporaryVarContentor() {
    }

    /**
     * Traverses the given expression and returns the temporary variables that were found.
     *
     * @param expression the expression from which to fetch temporary variables
     * @return the temporary variables that have been fetched
     */
    public SortedSet<TemporaryVariable> getTemporaryVariables(Expression expression) {
        this.temp_contentor = new TreeSet<TemporaryVariable>();
        expression.accept((ExpressionVisitor) this);
        return temp_contentor;
    }

    @Override
    public Object visitTemporaryVariable(TemporaryVariable tempVar) {
        if (!this.temp_contentor.contains(tempVar))
            temp_contentor.add(tempVar);
        return tempVar;
    }

    @Override
    public Object visitNaryOperation(NaryOperation operation) {
        Expression[] operands = operation.getOperands();

        for (Expression exp : operands) {
            exp.accept(this);
        }

        return operation;
    }
}
