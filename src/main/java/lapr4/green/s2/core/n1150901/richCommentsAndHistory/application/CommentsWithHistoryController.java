package lapr4.green.s2.core.n1150901.richCommentsAndHistory.application;

import csheets.ui.ctrl.UIController;
import eapli.util.Strings;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JList;
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
     * Changes to the cell that the user is adding comments
     *
     * @param cell the active cell
     */
    @Override
    public void changeActiveCell(CommentableCellWithMultipleUsers cell) {
        super.changeActiveCell(cell);
    }

    /**
     * Returns the comment's history of a cell.
     *
     * @return The comment's history of the cell passed as a parameter
     */
    public Map<User, Map<String, List<String>>> history() {
        return cell.history();
    }
    
        /**
     * Search a partial word in the comments and the history of the comments,
     * and if found, set the background to green.
     *
     * @param color The color to set to.
     * @param flag The flag that implies a change in the background of JList.
     * @param panelHistory The JList of the history.
     * @param word Part of the word to search.
     * @param panelComments The JList of the comments.
     */
    public void searchPartOfWord(Color color, int flag, JList panelHistory, JList panelComments, String word) {
        if (Strings.isNullOrEmpty(word) || Strings.isNullOrWhiteSpace(word)) {
            throw new IllegalArgumentException("You have to enter something to search!");
        }

        for (List<String> commList : comments().values()) {
            for (String comm : commList) {
                if (comm.contains(word)) {
                    color = Color.green;
                    flag = 3;
                    panelComments.repaint();
                }
            }
        }

        for (Map<String, List<String>> historyMap : history().values()) {
            for (List<String> historyList : historyMap.values()) {
                for (String s : historyList) {
                    if (s.contains(word)) {
                        color = Color.green;
                        flag = 3;
                        panelHistory.repaint();
                    }
                }
            }
        }
    }
}
