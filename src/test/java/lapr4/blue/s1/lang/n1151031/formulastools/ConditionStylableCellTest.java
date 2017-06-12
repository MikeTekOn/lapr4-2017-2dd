package lapr4.blue.s1.lang.n1151031.formulastools;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class ConditionStylableCellTest {

    public ConditionStylableCell conditionStylableCell;

    private CleanSheets app;
    Cell cellA1;

    @Before
    public void setUp() throws Exception {

        // Try to create the CS application object
        CleanSheets.setFlag(true);
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
    }
/*
    @Test
    public void ensureConditionIsValidExpression() throws FormulaCompilationException {
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String assignment = "10";
        cellA1.setContent(assignment);
        conditionStylableCell = new ConditionStylableCell(cellA1, null);
        UserStyle userStyle = new UserStyle();
        conditionStylableCell.setStyle(userStyle);
        conditionStylableCell.setUserCondition("=A1>0");
    }

    @Test(expected = IllegalConditionException.class)
    public void ensureConditionStartsWithEqualSign() throws FormulaCompilationException {
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String assignment = "10";
        cellA1.setContent(assignment);
        conditionStylableCell = new ConditionStylableCell(cellA1, null);
        UserStyle userStyle = new UserStyle();
        conditionStylableCell.setStyle(userStyle);
        conditionStylableCell.setUserCondition("A1>0");
    }

    @Test(expected = IllegalConditionException.class)
    public void ensureCellCannotBeEmptyWhenConditionOverItself() throws FormulaCompilationException {
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        conditionStylableCell = new ConditionStylableCell(cellA1, null);
        UserStyle userStyle = new UserStyle();
        conditionStylableCell.setStyle(userStyle);
        String assignment = "";
        cellA1.setContent(assignment);
        conditionStylableCell.setUserCondition("=A1>0");
    }

    @Test
    public void ensureCellHasCondition() throws FormulaCompilationException {
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String assignment = "10";
        cellA1.setContent(assignment);
        conditionStylableCell = new ConditionStylableCell(cellA1, null);
        UserStyle userStyle = new UserStyle();
        conditionStylableCell.setStyle(userStyle);
        boolean hasCondition = conditionStylableCell.hasCondition();
        assertTrue(hasCondition == false);
        conditionStylableCell.setUserCondition("=A1>0");
        hasCondition = conditionStylableCell.hasCondition();
        assertTrue(hasCondition);
    }

    @Test(expected = IllegalConditionException.class)
    public void ensureConditionCannotBeText() throws FormulaCompilationException {
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String assignment = "10";
        cellA1.setContent(assignment);
        conditionStylableCell = new ConditionStylableCell(cellA1, null);
        UserStyle userStyle = new UserStyle();
        conditionStylableCell.setStyle(userStyle);
        conditionStylableCell.setUserCondition("abc");
    }

    @Test(expected = IllegalConditionException.class)
    public void ensureConditionCannotBeNumber() throws FormulaCompilationException {
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String assignment = "10";
        cellA1.setContent(assignment);
        conditionStylableCell = new ConditionStylableCell(cellA1, null);
        UserStyle userStyle = new UserStyle();
        conditionStylableCell.setStyle(userStyle);
        conditionStylableCell.setUserCondition("123");
    }
*/
}
