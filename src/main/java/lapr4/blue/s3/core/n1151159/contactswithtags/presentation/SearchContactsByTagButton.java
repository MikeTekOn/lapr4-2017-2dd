package lapr4.blue.s3.core.n1151159.contactswithtags.presentation;

import csheets.ui.ctrl.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A button to search contacts by a tag regex.
 */
public class SearchContactsByTagButton extends JButton {

    /**
     * Creates a new instance of search contacts by tag button.
     *
     * @param uiController the user interface controller
     */
    public SearchContactsByTagButton(UIController uiController) {
        setText("Search By Tag");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SearchContactsByTagDialog searchContactsByTagDialog =
                        new SearchContactsByTagDialog(null, uiController);
                searchContactsByTagDialog.setVisible(true);
            }
        });
    }
}
