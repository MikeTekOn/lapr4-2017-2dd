package lapr4.blue.s1.lang.n1151031.formulastools;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Reference;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.util.ReferenceFetcher;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;

import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class ConditionStylableCell extends StylableCell {

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

    private UIController uiController;
    
    /**
     * The listeners registered to receive events from the condition stylable
     * cell
     */
    private transient List<ConditionStylableCellListener> listeners = new ArrayList<ConditionStylableCellListener>();

    public ConditionStylableCell(Cell cell, UIController uiController) {
        super(cell);
        userStyle=new UserStyle();
        dependents = new TreeSet<Cell>();
        this.uiController = uiController;
    }

    public UserStyle userStyle(){ return userStyle; }

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
            if (cell.getValue().isOfType(Value.Type.TEXT)) {
                StylableCell stylableCell = (StylableCell) getDelegate().getExtension(StyleExtension.NAME);
                return;
            }
            try {
                expression = ConditionalStyleCompiler.getInstance().compile(getDelegate(), getUserCondition(), uiController);
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
                ConditionStylableCell c = (ConditionStylableCell) cell.getExtension(ConditionalStyleExtension.NAME);
                c.setUserCondition(null);
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
                throw new IllegalConditionException("Invalid cell value: " + e.toString());
            }        
        }
//        else{
//            StylableCell stylableCell = (StylableCell) getDelegate().getExtension(StyleExtension.NAME);
//            stylableCell.resetStyle();
//        }
    }

    /**
     * Implemented by João Cardoso - 1150943
     * Restores the Cell Stye
     */
    public void restoreDefaultStyle() {
        userStyle= new UserStyle();
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
     * @throws IOException If any of the usual Input/Output related exceptions
     * occur
     * @throws ClassNotFoundException If the class of a serialized object cannot
     * be found.
     */
    private void readObject(java.io.ObjectInputStream stream)
            throws java.io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
        listeners = new ArrayList<ConditionStylableCellListener>();
    }

    public void setTrueStyleFont(Font trueStyleFont) {
        userStyle.setTrueStyleFont(trueStyleFont);
    }

    public void setTrueStyleForegroundColor(Color trueStyleForegroundColor) {
        userStyle.setTrueStyleForegroundColor(trueStyleForegroundColor);
    }
    public void setTrueStyleBackgroundColor(Color trueStyleBackgroundColor) {
        userStyle.setTrueStyleBackgroundColor(trueStyleBackgroundColor);
    }

    public void setTrueStyleBorder(Border trueStyleBorder) {
        userStyle.setTrueStyleBorder(trueStyleBorder);
    }

    public void setFalseStyleFont(Font falseStyleFont) {
        userStyle.setFalseStyleFont(falseStyleFont);
    }

    public void setFalseStyleBorder(Border falseStyleBorder) {
        userStyle.setFalseStyleBorder(falseStyleBorder);
    }

    public void setFalseStyleBackgroundColor(Color falseStyleBackgroundColor) {
        userStyle.setFalseStyleBackgroundColor(falseStyleBackgroundColor);
    }

    public void setFalseStyleForegroundColor(Color falseStyleForegroundColor) {
        userStyle.setFalseStyleForegroundColor(falseStyleForegroundColor);
    }

    public void setStyle(UserStyle style) {
        this.userStyle = style;
    }
}
