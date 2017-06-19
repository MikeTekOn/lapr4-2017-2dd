/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.domain;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaLexer;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaParser;
import lapr4.blue.s1.lang.n1151452.formula.compiler.ExcelExpressionCompiler;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class AbstractSyntaxTreeTest {
    
    private BlueFormulaParser parser;
    private ParseTree parseTree;
    private JTextField editBox;
    private String formula;
    
    private AbstractSyntaxTree ast;
    private JTree tree;
    
    @Before
    public void setUp() {
        formula = "=FACT(3+3)";
        
        ANTLRInputStream input = new ANTLRInputStream(formula);
        BlueFormulaLexer lexer = new BlueFormulaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new BlueFormulaParser(tokens);
        ExcelExpressionCompiler.FormulaErrorListener formulaErrorListener = new ExcelExpressionCompiler.FormulaErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(formulaErrorListener);
        parseTree = parser.expression();
        
        editBox = new JTextField();
        editBox.setText(formula);
        
        ast = new AbstractSyntaxTree(parser, parseTree, editBox);
        tree = ast.buildSyntaxTree();
    }
    
    @After
    public void cleanUp() {
    }

    /**
     * Test of occurrencesOfACharInAString method, of class AbstractSyntaxTree.
     */
    @Test
    public void testOccurrencesOfACharInAString() {
        System.out.println("occurrencesOfACharInAString");
        char c = '3';
        int expResult = 2;
        int result = ast.occurrencesOfACharInAString(formula, c);
        assertEquals(expResult, result);
    }

    /**
     * Test of manageLeafNodes method, of class AbstractSyntaxTree.
     */
    @Test
    public void testManageLeafNodes() {
        System.out.println("manageLeafNodes");
        
        ArrayList<String> myLeafNodes = new ArrayList<>();
        myLeafNodes.add("=");
        myLeafNodes.add("FACT");
        myLeafNodes.add("(");
        myLeafNodes.add("3");
        myLeafNodes.add("+");
        myLeafNodes.add("3");
        myLeafNodes.add(")");
        myLeafNodes.add("<EOF>");
        
        ArrayList<DefaultMutableTreeNode> leafNodes = ast.leafNodes();
        ArrayList<String> stringLeafNodes = new ArrayList<>();
        
        for (DefaultMutableTreeNode leafNode : leafNodes) {
            stringLeafNodes.add(leafNode.toString());
        }
        
        assertArrayEquals(myLeafNodes.toArray(), stringLeafNodes.toArray());
    }

    /**
     * Test of boxPositionToHighlight method, of class AbstractSyntaxTree.
     */
    @Test
    public void testBoxPositionToHighlight() {
        System.out.println("boxPositionToHighlight");
        ast.setSelectedNodeInfo("3");
        int occurrence = 1;
        ast.boxPositionToHighlight(occurrence);
        
        int expectedHighlightStart = 8;
        int expectedHighlightEnd = 8;
        
        assertEquals(expectedHighlightStart, ast.highlightStart());
        assertEquals(expectedHighlightEnd, ast.highlightEnd());
    }
    
}
