/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1151094.columnSort.presentation;

import csheets.CleanSheets;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import lapr4.red.s1.core.n1151094.columnSort.application.ColumnSortController;

/**
 *
 * @author Eduangelo Ferreira - 1151094 Esta classe é uma JMenu
 */
@SuppressWarnings("serial")
public class ColumnSortUI extends JMenu {

    /**
     * The user interface controller
     */
    private final UIController uiController;
    private final ColumnSortController sortColumnController;

    /**
     * Este construtor recebe como parametro a UIController para depois, poder
     * executar as ordenação seja pela qual ordem for.
     *
     * @param uiController UIController
     */
    public ColumnSortUI(UIController uiController) {
        super("Column Sort");

        this.uiController = uiController;
        sortColumnController = new ColumnSortController();

        setMnemonic(KeyEvent.VK_S);
        setIcon(new ImageIcon(CleanSheets.class.getResource("res/img/sort_bottom.png")));

        add(new AscendingAction());
        add(new DescendingAction());

    }

    @SuppressWarnings("serial")
    protected class AscendingAction extends FocusOwnerAction {

        public AscendingAction() {

        }

        @Override
        protected void defineProperties() {
            setEnabled(true);
            putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource("res/img/ascending_bottom.png")));
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (focusOwner == null) {
                return;
            }
            try {
                //Estes metodos permite fazer executar o processo de ordenação 
                sortColumnController.selectCellsOfColumn(uiController.getActiveSpreadsheet(), focusOwner);
                sortColumnController.sortAscending();
            } catch (IllegalValueTypeException | FormulaCompilationException | ParseException ex) {
                Logger.getLogger(ColumnSortUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        protected String getName() {
            return "Ascending";
        }
    }

    @SuppressWarnings("serial")
    protected class DescendingAction extends FocusOwnerAction {

        public DescendingAction() {
        }

        @Override
        protected void defineProperties() {
            setEnabled(true);
            putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource("res/img/descending_bottom.png")));
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (focusOwner == null) {
                return;
            }

            try {
                //Estes metodos permite fazer executar o processo de ordenação 
                sortColumnController.selectCellsOfColumn(uiController.getActiveSpreadsheet(), focusOwner);
                sortColumnController.sortDescending();

            } catch (IllegalValueTypeException | FormulaCompilationException | ParseException ex) {
                Logger.getLogger(ColumnSortUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        protected String getName() {
            return "Descending";
        }
    }

}
