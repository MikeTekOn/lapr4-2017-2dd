/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.gray.s1.lang.n3456789.formula;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.compiler.FormulaCompiler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author alexandrebraganca
 */
public class FormulaLoopTest {
    
    CleanSheets app;
   
    @Before public void setUp() {
        
        // Try to create the CS application object
        CleanSheets.setFlag(true);
        app = new CleanSheets();
        
        // This will create a workbook with 3 sheets
        app.create();
        
    }
    
    @Test public void testBasicExpression() throws FormulaCompilationException {
        String content="={ 1+2; 2+4; 5-4 }";
        Cell cell=app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0,0));
                
        try {  
            // #1 test
            content="=1+2";

            cell.setContent(content);
            
            assertTrue(cell.getValue().toDouble()==3.0);
        } catch (IllegalValueTypeException ex) {
            Logger.getLogger(FormulaLoopTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test Blocks<br/>
     * "={ 1+2; sum(a1:a10); b3+4 }"
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     */
    @Test public void testFormulaBlocks() throws FormulaCompilationException {
        String content="={ 1+2; 2+4; 5-4 }";
        Cell cell=app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0,0));
                
        try {  
            // #1 test
            content="=1+2";

            cell.setContent(content);
            
            assertTrue(cell.getValue().toDouble()==3.0);
            
            // #2 test
            content="={ 1+2; 2+4; 5-2 }";

            cell.setContent(content);
            
            assertTrue(cell.getValue().toDouble()==3.0);
        } catch (IllegalValueTypeException ex) {
            Logger.getLogger(FormulaLoopTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test Assignment operator<br/>
     * "={ 1+2; sum(a1:a10); b3+4 }"
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     */
    @Test public void testAssignmentOperator() throws FormulaCompilationException, IllegalValueTypeException {
        String content="=(A2:=1+2)";
        Cell cell=app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0,0));
                 
        // #1 test
        cell.setContent(content);
            
        assertTrue(cell.getValue().toDouble()==3.0);

        Cell cell2=app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0,1));

        assertTrue(cell2.getValue().toDouble()==3.0);
            
        // #2 test
        //content="=(A2:=\"abc\" & \"d\")";
        // IMPORTANT: A assignment inside the formula to the same cell as the target of 
        // the formula will result in a circular reference!!!
        content="=(A3:=\"abc\" & \"d\")";
        cell.setContent(content);
            
        assertTrue(cell.getValue().toText().compareTo("abcd")==0);

        cell2=app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0,2));

        assertTrue(cell2.getValue().toText().compareTo("abcd")==0);
    }    
        
    @After public void cleanUp() {
        
    }    
}
