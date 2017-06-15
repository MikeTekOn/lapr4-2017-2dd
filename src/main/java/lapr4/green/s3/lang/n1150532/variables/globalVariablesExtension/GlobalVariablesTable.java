package lapr4.green.s3.lang.n1150532.variables.globalVariablesExtension;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;

/**
 * It provides a table with the global variables information.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class GlobalVariablesTable extends JPanel implements Observer {

    /**
     * The table itself.
     */
    private JTable table;

    /**
     * The controller of the table.
     */
    private final GlobalVariablesTableController theController;

    /**
     * The constructor of the table UI.
     */
    public GlobalVariablesTable() {
        super();
        theController = new GlobalVariablesTableController();
        createUserInterface();
    }

    /**
     * It builds the graphical user interface of the table.
     */
    private void createUserInterface() {
        final int bottomUpPadding = 5;
        final int sidesPadding = 20;
        final Dimension size = new Dimension(400, 125);
        setLayout(new GridLayout(1, 1));
        table = new JTable(theController);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(false);
        table.setPreferredScrollableViewportSize(size);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(bottomUpPadding, sidesPadding, bottomUpPadding, sidesPadding));
        add(scrollPane);
    }

    /**
     * It builds the table's content with the variables within the container.
     *
     * @param variables The container of the global variables.
     */
    public void buildTableContent(VarContentor variables) {
        theController.setContent(variables);
    }

    /**
     * It clears the table.
     */
    public void clear() {
        theController.clear();
    }

    /**
     * It listens to changes in the global variables in order to update the
     * table.
     *
     * @param o The container of the global variables
     * @param arg The added or altered global variable information.
     */
    @Override
    public void update(Observable o, Object arg) {
        theController.addRow((GlobalVariableInformationDTO) arg);
    }

}
