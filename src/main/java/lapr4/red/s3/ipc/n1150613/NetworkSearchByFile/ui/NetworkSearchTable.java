package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.SearchResults;

/**
 * The table with the results of the workbook's network search.
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class NetworkSearchTable extends JPanel implements Observer {

    /**
     * The table itself.
     */
    private JTable table;

    /**
     * The controller of the table.
     */
    private final NetworkSearchTableController controller;

    /**
     * The constructor of the table UI.
     */
    public NetworkSearchTable() {
        super();
        controller = new NetworkSearchTableController();
        createUserInterface();
    }

    /**
     * Builds the graphical user interface of the table.
     */
    private void createUserInterface() {
        final int bottomUpPadding = 5;
        final int sidesPadding = 20;
        final Dimension size = new Dimension(400, 250);

        setLayout(new GridLayout(1, 1));
        table = new JTable(controller);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(false);
        table.setPreferredScrollableViewportSize(size);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(bottomUpPadding, sidesPadding, bottomUpPadding, sidesPadding));
        add(scrollPane);

        //table customization
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setMaxWidth(40);
        table.getColumnModel().getColumn(3).setMinWidth(100);
    }

    /**
     * Adds a new search result to the Table.
     *
     * @param result the result added
     */
    public void insertRow(SearchResults result) {
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
        if (arg instanceof SearchResults) {
            insertRow((SearchResults) arg);
        }
    }

}
