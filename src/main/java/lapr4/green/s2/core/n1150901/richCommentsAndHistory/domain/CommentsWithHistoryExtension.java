/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150901.richCommentsAndHistory.domain;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.presentation.CommentsWithHistoryUIExtension;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class CommentsWithHistoryExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "History";

    /**
     * Creates a new Example extension.
     */
    public CommentsWithHistoryExtension() {
        super(NAME);
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
}
