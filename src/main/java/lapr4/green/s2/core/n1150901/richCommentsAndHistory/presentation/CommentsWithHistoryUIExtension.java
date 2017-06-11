package lapr4.green.s2.core.n1150901.richCommentsAndHistory.presentation;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

import javax.swing.*;

/**
 * This class implements the UI interface extension for the comment formatting.
 * A UI interface extension must extend the UIExtension abstract class.
 *
 * @author Miguel Silva - 1150901
 * @see UIExtension
 */
public class CommentsWithHistoryUIExtension extends UIExtension {

    /**
     * A side bar that provides editing of comments.
     */
    private JComponent sideBar;

    public CommentsWithHistoryUIExtension(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns an icon to display with the extension's name.
     *
     * @return Return an icon with style.
     */
    @Override
    public Icon getIcon() {
        return null;
    }

    /**
     * Returns a side bar that provides editing of comments.
     *
     * @return Returns a side bar.
     */
    @Override
    public JComponent getSideBar() {
        if (sideBar == null) {
            sideBar = new CommentsWithHistoryUI(uiController);
        }
        return sideBar;
    }

}
