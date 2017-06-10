package lapr4.green.s2.core.n1150901.richCommentsAndHistory.application;

import csheets.ui.ctrl.UIController;
import java.util.List;
import java.util.Map;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.application.AddCommentsWithUserController;
import lapr4.red.s1.core.n1150690.comments.domain.User;

/**
 * @author Miguel Silva - 1150901
 */
public class CommentsWithHistoryController extends AddCommentsWithUserController {

    /**
     * The user interface controller.
     */
    private final UIController uiController;

    /**
     * Creates a new conditional style controller.
     *
     * @param uiController the user interface controller
     */
    public CommentsWithHistoryController(UIController uiController) {
        super(uiController);
        this.uiController = uiController;
    }

    /**
     * Returns the comment's history of a cell.
     *
     * @return The comment's history of the cell passed as a parameter
     */
    public Map<User, Map<String, List<String>>> history() {
        return cell.history();
    }
}
