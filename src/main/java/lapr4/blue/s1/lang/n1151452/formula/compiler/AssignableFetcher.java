package lapr4.blue.s1.lang.n1151452.formula.compiler;

import csheets.core.formula.BinaryOperation;
import csheets.core.formula.Expression;
import csheets.core.formula.Reference;
import csheets.core.formula.lang.CellReference;
import csheets.core.formula.util.AbstractExpressionVisitor;
import csheets.core.formula.util.ReferenceFetcher;
import lapr4.blue.s1.lang.n1151452.formula.lang.Assigner;

import java.util.SortedSet;
import java.util.TreeSet;


/**
 * A expression visitor that collects only nonAssignableRefs that are not assignable
 * <p>
 * (Note: This is to avoid a cell that assigns a value to another cell becomes a
 * dependent of that cell and entering a infinite loop)
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 03/06/17.
 */
public class AssignableFetcher extends AbstractExpressionVisitor {

    /**
     * The assignable nonAssignableRefs that have been fetched
     */
    private SortedSet<Reference> nonAssignableRefs;

    /**
     * Creates a new assignable reference fetcher.
     */
    public AssignableFetcher() {
    }

    /**
     * Traverses the given expression and returns only the non-assignable nonAssignableRefs that were found.
     *
     * @param expression the expression from which to fetch nonAssignableRefs
     * @return the non-assignable nonAssignableRefs that have been fetched
     */
    public SortedSet<Reference> getNonAssignableReferences(Expression expression) {

        nonAssignableRefs = new TreeSet<>();
        expression.accept(this); // Fetches & fills non-assignable references set

        // Fetch all references of the expression
        SortedSet<Reference> references = new ReferenceFetcher().getReferences(expression);

        // If there is no assignable references return the whole list
        if (nonAssignableRefs.size() == 0) return references;

        // If not, remove non-assignable
        SortedSet<Reference> filteredReferences = new TreeSet<>();

        for (Reference ref : references) {
            boolean contains = false;
            for (Reference nonAssignable : nonAssignableRefs) {

                if (ref.compareTo(nonAssignable) < 0) { // Means it is equal (As per core implemented compareTo)
                    contains = true;
                    break;
                }
            }
            // If it doesn't contain add
            if (!contains) filteredReferences.add(ref);
        }

        return filteredReferences;
    }

    @Override
    public Object visitBinaryOperation(BinaryOperation operation) {

        if (operation.getOperator() instanceof Assigner) {

            assert operation.getLeftOperand() instanceof CellReference; // Already verified (Can not happen)

            nonAssignableRefs.add((CellReference) operation.getLeftOperand());
            nonAssignableRefs.addAll((new ReferenceFetcher()).getReferences(operation.getRightOperand()));
        }

        return operation;
    }
}
