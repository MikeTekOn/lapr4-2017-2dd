/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.domain;

import lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.AbstractSyntaxTreeSelectionModel;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import lapr4.blue.s1.lang.n1151452.formula.compiler.BlueFormulaParser;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class AbstractSyntaxTree {

    /**
     * The parse tree.
     */
    private ParseTree parseTree;

    /**
     * The parser.
     */
    private BlueFormulaParser parser;

    /**
     * The tree's leaf nodes.
     */
    private ArrayList<DefaultMutableTreeNode> leafNodes;

    /**
     * The information contained in the selected node.
     */
    private String selectedNodeInfo;

    /**
     * The edit box.
     */
    private JTextField editBox;

    /**
     * The highlight starting position.
     */
    private int highlightStart;

    /**
     * The highlight ending position.
     */
    private int highlightEnd;

    /**
     * Builds an instance of syntax tree with the given parameters
     *
     * @param parser - the formula parser
     * @param parseTree - the parse tree
     * @param editBox - the edit box
     */
    public AbstractSyntaxTree(BlueFormulaParser parser, ParseTree parseTree, JTextField editBox) {
        if (parser == null || parseTree == null) {
            throw new IllegalArgumentException("The syntax tree cannot be generated");
        }

        this.parser = parser;
        this.parseTree = parseTree;
        this.editBox = editBox;
    }

    /**
     * Builds a syntax tree in a JTree swing format
     *
     * @return the JTree formatted syntax tree
     */
    public JTree buildSyntaxTree() {
        String[] tree = parseTree.toStringTree(parser).split(" ");
        DefaultMutableTreeNode root, lastNode;

        DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[tree.length];
        int lastNodeIndex = 0;

        // ROOT TREATMENT
        String rootName = tree[0].substring(1);
        root = new DefaultMutableTreeNode(rootName);
        nodes[lastNodeIndex] = root;
        lastNode = nodes[lastNodeIndex];

        for (int i = 1; i < tree.length; i++) {
            // REMAINING NODES TREATMENT
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(tree[i]);
            int lastCharPosition = tree[i].length() - 1;
            if (tree[i].charAt(0) != '(' && tree[i].charAt(lastCharPosition) != ')') {
                // NODE HAS NO CHILDREN AND DOESN'T CLOSE THE LAST NODE
                lastNode.add(newNode);
            } else if (tree[i].charAt(0) == '(') {
                if (tree[i].length() > 1) {
                    // NODE HAS CHILDREN AND DOESN'T CLOSE THE LAST NODE
                    newNode = new DefaultMutableTreeNode(tree[i].substring(1));
                    lastNode.add(newNode);
                    lastNodeIndex++;
                    nodes[lastNodeIndex] = newNode;
                    lastNode = nodes[lastNodeIndex];
                } else {
                    // NODE '(' HAS NO CHILDREN AND DOESN'T CLOSE THE LAST NODE
                    lastNode.add(newNode);
                }
            } else if (tree[i].charAt(lastCharPosition) == ')') {
                // NODE HAS NO CHILDREN AND CLOSES THE PREVIOUS NODE/NODES
                String nodeName;
                int nodesToBeClosed = 0;

                if (i == tree.length - 1) {
                    // END NODE
                    nodeName = tree[i].substring(0, lastCharPosition);

                } else {
                    // NOT END NODES
                    nodesToBeClosed = occurrencesOfACharInAString(tree[i], ')');
                    nodeName = tree[i].replace(')', ' ').trim();

                    if (nodeName.isEmpty()) {
                        nodeName = ")";
                        nodesToBeClosed--;
                    }
                }

                newNode = new DefaultMutableTreeNode(nodeName);
                lastNode.add(newNode);
                lastNodeIndex -= nodesToBeClosed;
                lastNode = nodes[lastNodeIndex];
            }

        }

        JTree newTree = new JTree(root);
        newTree.setSelectionModel(new AbstractSyntaxTreeSelectionModel());
        newTree.addTreeSelectionListener((TreeSelectionEvent e) -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) newTree.getLastSelectedPathComponent();

            if (node == null) {
                return;
            }

            int occurrence = 0;

            for (int i = 0; i < leafNodes.indexOf(node); i++) {
                if (leafNodes.get(i).toString().equals(node.toString())) {
                    occurrence++;
                }
            }

            selectedNodeInfo = node.toString();
            updateEditBox(occurrence);
        });

        expandAll(newTree);

        leafNodes = new ArrayList<>();
        manageLeafNodes((DefaultMutableTreeNode) newTree.getModel().getRoot());
        
        configureTreeIcons(newTree);
        
        return newTree;
    }

    /**
     * Counts the occurrences of a character in a string
     *
     * @param string - the string
     * @param c - the character to be found and counted
     * @return the number of occurrences the character is found
     */
    protected int occurrencesOfACharInAString(String string, char c) {
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == c) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Expands the collapsed paths from a JTree
     *
     * @param tree - the tree
     */
    private void expandAll(JTree tree) {
        int r = 0;
        
        while (r < tree.getRowCount()) {
            tree.expandRow(r);
            r++;
        }
    }

    /**
     * Manages all the leaf nodes from a tree through a Depth-First Search (DFS)
     * recursive algorithm
     *
     * @param node - the tree root
     */
    protected void manageLeafNodes(DefaultMutableTreeNode node) {
        int childCount = node.getChildCount();

        for (int i = 0; i < childCount; i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            if (child.isLeaf()) {
                leafNodes.add(child);
            } else if (child.getChildCount() > 0) {
                manageLeafNodes(child);
            }
        }
    }
    
    /**
     * Configures the nodes' icons from a tree
     * @param tree - the tree
     */
    private void configureTreeIcons(JTree tree) {
        Icon expandIcon = new ImageIcon(AbstractSyntaxTree.class.getResource("res/expand_icon.gif"));
        Icon collapseIcon = new ImageIcon(AbstractSyntaxTree.class.getResource("res/collapse_icon.gif"));
        Icon leafIcon = new ImageIcon(AbstractSyntaxTree.class.getResource("res/leaf_icon.gif"));
        
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        renderer.setLeafIcon(leafIcon);
        renderer.setClosedIcon(expandIcon);
        renderer.setOpenIcon(collapseIcon);
    }

    /**
     * Provides the information contained in the selected node
     *
     * @return the information of the selected node
     */
    public String selectedNodeInfo() {
        return selectedNodeInfo;
    }
    
    /**
     * Sets the selected node information
     * @param nodeInfo the node information to set
     */
    protected void setSelectedNodeInfo(String nodeInfo) {
        selectedNodeInfo = nodeInfo;
    }
    
    /**
     * Provides the leaf nodes from the syntax tree
     * @return the leaf nodes from the syntax tree
     */
    protected ArrayList<DefaultMutableTreeNode> leafNodes() {
        return leafNodes;
    }

    /**
     * Updates the edit box, highlighting the token selected from the syntax
     * tree.
     *
     * @param occurrence - number of occurrences of the token before the current
     * token
     */
    private void updateEditBox(int occurrence) {
        boxPositionToHighlight(occurrence);

        editBox.requestFocus();
        if (highlightStart > -1) {
            editBox.setSelectionStart(highlightStart);
            editBox.setSelectionEnd(highlightEnd + 1);
            editBox.setSelectionColor(Color.black);
            editBox.setSelectedTextColor(Color.white);
            editBox.repaint();
        }
    }

    /**
     * Gets the selected token's starting and ending positions in the edit box.
     *
     * @param occurrence - number of occurrences of the token before the current
     * token
     */
    protected void boxPositionToHighlight(int occurrence) {
        int lastOccurrence, lastOccurrenceStart = -1;

        for (int i = 0; i < occurrence; i++) {
            lastOccurrence = editBox.getText().indexOf(selectedNodeInfo, lastOccurrenceStart + 1);
            lastOccurrenceStart = lastOccurrence;
        }

        highlightStart = editBox.getText().indexOf(selectedNodeInfo, lastOccurrenceStart + 1);
        highlightEnd = highlightStart + selectedNodeInfo.length() - 1;
    }
    
    /**
     * Provides the highlight starting position
     * @return the highlight starting position
     */
    protected int highlightStart() {
        return highlightStart;
    }
    
    /**
     * Provides the highlight ending position
     * @return the highlight ending position
     */
    protected int highlightEnd() {
        return highlightEnd;
    }
}
