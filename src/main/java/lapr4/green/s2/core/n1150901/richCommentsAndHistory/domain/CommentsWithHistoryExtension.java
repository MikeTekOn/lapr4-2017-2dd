/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150901.richCommentsAndHistory.domain;

import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.presentation.CommentsWithHistoryUIExtension;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class CommentsWithHistoryExtension extends CommentsExtension {

    /**
     * Creates a new Example extension.
     */
    public CommentsWithHistoryExtension() {
        super();
        setVersion(super.getVersion()+1);
    }

    /**
     * Returns the user interface extension of this extension
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        return new CommentsWithHistoryUIExtension(this, uiController);
    }
    

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Miguel Silva in Sprint 2 and it is in the package %s\n"
                + "This extension now has an history",
                getName(), getVersion(), getDescription(), getClass().getName());
    }    
}
