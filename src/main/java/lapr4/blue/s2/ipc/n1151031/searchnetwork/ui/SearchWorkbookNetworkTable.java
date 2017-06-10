package lapr4.blue.s2.ipc.n1151031.searchnetwork.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.SearchResult;

/**
 * The table with the results of the workbook's network search.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class SearchWorkbookNetworkTable extends JPanel implements Observer {

    /**
     * The table itself.
     */
    private JTable table;

    /**
     * The controller of the table.
     */
    private final SearchWorkbookNetworkTableController controller;

    /**
     * The constructor of the table UI.
     */
    public SearchWorkbookNetworkTable() {
        super();
        controller = new SearchWorkbookNetworkTableController();
        createUserInterface();
    }

    /**
     * Builds the graphical user interface of the table.
     */
    private void createUserInterface() {
        final int bottomUpPadding = 5;
        final int sidesPadding = 20;
        final Dimension size = new Dimension(400, 125);

        setLayout(new GridLayout(1, 1));
        table = new JTable(controller);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(false);
        table.setPreferredScrollableViewportSize(size);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(bottomUpPadding, sidesPadding, bottomUpPadding, sidesPadding));
        add(scrollPane);
    }

    /**
     * Adds a new search result to the Table.
     *
     * @param result the result added
     */
    public void insertRow(SearchResult result) {
        controller.addRow(result);
    }

    /**
     * Removes all the search results from the list.
     */
    public void clear() {
        controller.clear();
    }

    /**
     * Adds a new selection listener to the table.
     *
     * @param listener The listener to be added.
     */
    public void addListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * Listens to new connections to be inserted on the table.
     *
     * @param o The observable item.
     * @param arg The event from which to extract the connection id.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof SearchResult) {
            insertRow((SearchResult) arg);
        }
    }

}
