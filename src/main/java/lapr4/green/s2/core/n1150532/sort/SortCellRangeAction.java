package lapr4.green.s2.core.n1150532.sort;

import csheets.CleanSheets;
import csheets.core.Cell;
import csheets.ui.ctrl.FocusOwnerAction;
import java.awt.event.ActionEvent;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * The action of sorting the selected cells' row by comparing one of the
 * columns.
 *
 * @author Manuel Meireles (1150532)
 */
public class SortCellRangeAction extends FocusOwnerAction {

    /**
     * The name of the action.
     */
    private static final String NAME = "Range of cells...";

    /**
     * A getter of the action's name.
     *
     * @return It returns the actions name.
     */
    @Override
    protected String getName() {
        return NAME;
    }

    /**
     * It defines a small icon to be shown along with the action name.
     */
    @Override
    protected void defineProperties() {
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource("res/img/insert_column.gif")));
    }

    /**
     * It gets the selected cells within the spreadsheet table and opens a
     * pop-up menu with sorting options.
     *
     * @param e Not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Cell[][] selectedCells = focusOwner.getSelectedCells();
        if (selectedCells.length == 0) {
            JOptionPane.showMessageDialog(null, "There are no selected cells to sort.", "Unable to sort", JOptionPane.WARNING_MESSAGE);
        } else if (selectedCells.length < 2) {
            JOptionPane.showMessageDialog(null, "There are not enough rows selected to sort.", "Unable to sort", JOptionPane.WARNING_MESSAGE);
        } else {
            new SortCellRangeUI(selectedCells);
        }
    }

}
