package lapr4.blue.s1.lang.n1151031.formulastools;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.Expression;
import csheets.core.formula.Reference;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.util.ReferenceFetcher;
import csheets.ext.CellExtension;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class ConditionStylableCell extends CellExtension {

    /**
     * The unique version identifier used for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The cell's user-specified condition
     */
    private String userCondition;

    private UserStyle userStyle;

    private SortedSet<Cell> dependents;

    /**
     * The listeners registered to receive events from the condition stylable
     * cell
     */
    private transient List<ConditionStylableCellListener> listeners = new ArrayList<ConditionStylableCellListener>();

    public ConditionStylableCell(Cell cell) {
        super(cell, ConditionalStyleExtension.NAME);

        // user styles new userstyle()
        dependents = new TreeSet<Cell>();
    }

    /**
     * Get the cell's user condition.
     *
     * @return The user supplied a condition for the cell or <code>null</code>
     * if no user supplied condition exists.
     */
    public String getUserCondition() {
        return userCondition;
    }

    /**
     * Returns whether the cell has a condition.
     *
     * @return true if the cell has a condition, false otherwise
     */
    public boolean hasCondition() {
        return userCondition != null;
    }

    /**
     * Sets the user-specified condition for the cell.
     *
     * @param condition the user-specified condition
     */
    public void setUserCondition(String condition) throws RuntimeException {
        this.userCondition = condition;
        // Notifies listeners
        fireConditionChanged();
    }

    @Override
    public void valueChanged(Cell cell) throws RuntimeException {
        assert getDelegate() != null;

        Expression expression = null;
        if (getUserCondition() != null) {
            try {
                expression = ConditionalStyleCompiler.getInstance().compile(getDelegate(), getUserCondition());
                SortedSet<Reference> references = (new ReferenceFetcher()).getReferences(expression);

                if (getDelegate() == cell) {
                    // Registers as dependent with each new precedent
                    for (Reference reference : references) {
                        for (Cell dependent : reference.getCells()) {
                            if (!getDelegate().equals(dependent)) {
                                dependents.add(dependent);
                                dependent.addCellListener(this);
                            }
                        }
                    }
                }
            } catch (FormulaCompilationException e) {
                e.printStackTrace();
                throw new IllegalConditionException("The entered condition is not valid!");
            }
            try {
                StylableCell stylableCell = (StylableCell) getDelegate().getExtension(StyleExtension.NAME);
                assert expression != null;
                if (expression.evaluate().toBoolean()) {
                    stylableCell.setFont(this.userStyle.getTrueStyleFont());
                    stylableCell.setForegroundColor(this.userStyle.getTrueStyleForegroundColor());
                    stylableCell.setBackgroundColor(this.userStyle.getTrueStyleBackgroundColor());
                    stylableCell.setBorder(this.userStyle.getTrueStyleBorder());
                } else {
                    stylableCell.setFont(this.userStyle.getFalseStyleFont());
                    stylableCell.setForegroundColor(this.userStyle.getFalseStyleForegroundColor());
                    stylableCell.setBackgroundColor(this.userStyle.getFalseStyleBackgroundColor());
                    stylableCell.setBorder(this.userStyle.getFalseStyleBorder());
                }
            } catch (IllegalValueTypeException e) {
                e.printStackTrace();
                throw new IllegalConditionException("Invalid cell value: " + e.toString());
            }
        }
    }

    /**
     * Sets the user defined style options.
     *
     * @param userStyle the user defined style
     */
    public void setStyle(UserStyle userStyle) {
        this.userStyle = userStyle;
    }

    /**
     * Registers the given listener on the cell.
     *
     * @param listener the listener to be added
     */
    public void addConditionStylableCellListener(ConditionStylableCellListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes the given listener from the cell.
     *
     * @param listener the listener to be removed
     */
    public void removeConditionalStylableCellListener(ConditionStylableCellListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies all registered listeners that the cell's condition changed.
     */
    protected void fireConditionChanged() throws RuntimeException {
        for (ConditionStylableCellListener listener : listeners) {
            listener.conditionChanged(this);
        }

        for (Cell dependent : dependents) {
            dependent.removeCellListener(this);
        }
        dependents.clear();
        valueChanged(getDelegate());
    }

    /**
     * Customizes serialization, by recreating the listener list.
     *
     * @param stream the object input stream from which the object is to be read
     * @throws IOException            If any of the usual Input/Output related exceptions
     *                                occur
     * @throws ClassNotFoundException If the class of a serialized object cannot
     *                                be found.
     */
    private void readObject(java.io.ObjectInputStream stream)
            throws java.io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
        listeners = new ArrayList<ConditionStylableCellListener>();
    }
}
