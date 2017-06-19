/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class AbstractSyntaxTreeSelectionModel extends DefaultTreeSelectionModel {
    
    /**
     * Restricts that only leaf nodes can be selected from the tree
     * @param path - the existing tree paths
     * @return - the tree paths that end with leaf nodes
     */
    private TreePath[] restriction(TreePath[] path) {
        
        ArrayList<TreePath> listPath = new ArrayList<>();
        
        for(int i=0; i < path.length; i++) {
            if(((DefaultMutableTreeNode) path[i].getLastPathComponent()).isLeaf()) {
                listPath.add(path[i]);
            }
        }
        
        return listPath.toArray(path);
    }

    @Override
    public void setSelectionPaths(TreePath[] pPaths) {
        super.setSelectionPaths(restriction(pPaths));
    }

    @Override
    public void addSelectionPaths(TreePath[] paths) {
        super.addSelectionPaths(restriction(paths));
    }
}
