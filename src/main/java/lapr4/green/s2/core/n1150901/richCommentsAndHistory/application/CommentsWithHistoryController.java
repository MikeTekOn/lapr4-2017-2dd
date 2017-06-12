package lapr4.green.s2.core.n1150901.richCommentsAndHistory.application;

import csheets.ui.ctrl.UIController;
import eapli.util.Strings;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
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
     * @param word Part of the word to search.
     * @return Returns a map with the list of active comments found and comments
     * in history.
     */
    public Map<Integer, List<String>> searchPartOfWord(String word) {
        if (Strings.isNullOrEmpty(word) || Strings.isNullOrWhiteSpace(word)) {
            throw new IllegalArgumentException("You have to enter something to search!");
        }
        Map<Integer, List<String>> map = new HashMap<>();
        List<String> comments = new ArrayList<>();
        List<String> history = new ArrayList<>();
        boolean found = false;  

        for (List<String> commList : comments().values()) {
            for (String comm : commList) {
                if (comm.startsWith(word)) {
                    found = true;
                    comments.add(comm);
                }
            }
        }

        for (Map<String, List<String>> historyMap : history().values()) {
            for (List<String> historyList : historyMap.values()) {
                for (String s : historyList) {
                    if (s.startsWith(word)) {
                        history.add(s);
                    }
                }
            }
        }
        
        if (!found){
            map.put(0, comments);
        }
        
        map.put(1, comments);
        map.put(2, history);
        return map;
    }
}
