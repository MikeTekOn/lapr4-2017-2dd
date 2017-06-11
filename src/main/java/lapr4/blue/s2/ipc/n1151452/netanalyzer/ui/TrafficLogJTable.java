package lapr4.blue.s2.ipc.n1151452.netanalyzer.ui;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficEvent;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficType;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficLogListener;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * Represent a UI Table to show the traffic logs
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public class TrafficLogJTable extends JPanel implements TrafficLogListener {

    /**
     * The table
     */
    private JTable table;

    /**
     * The table model
     */
    private final TrafficLogTableModel tableModel;

    /**
     * The constructor of the table UI
     */
    TrafficLogJTable() {
        super();
        tableModel = new TrafficLogTableModel();
        add(createPanel());
    }

    /**
     * Creates the panel with the table
     */
    private JScrollPane createPanel() {
        final int bottomUpPadding = 5;
        final int sidesPadding = 20;
        final Dimension size = new Dimension(400, 125);

        setLayout(new GridLayout(1, 1));
        table = new Table(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(false);
        table.setPreferredScrollableViewportSize(size);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(bottomUpPadding, sidesPadding, bottomUpPadding, sidesPadding));

        return scrollPane;
    }

    /**
     * Adds a new log to the Table
     *
     * @param log the traffic log to add
     */
    private void insertRow(TrafficEvent log) {
        tableModel.addRow(log);
    }

    /**
     * It removes all logs from the list
     */
    public void clear() {
        tableModel.clear();
    }

    @Override
    public void fireNewTrafficEvent(TrafficEvent event) {

        insertRow(event);
    }

    /**
     * Custom JTable
     */
    private class Table extends JTable {

        private Table(TrafficLogTableModel tableModel) {
            super(tableModel);
        }

        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

            JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);

            if (getValueAt(row, 0).toString().contains("[u]")) {
                component.setBackground(Color.decode("#FCE48B"));
            } else {
                component.setBackground(Color.WHITE);
            }
            return component;
        }
    }

    /**
     * Test mode
     */
    private boolean testMode = false;

    /**
     * Set the graph in test mode
     */
    public void testModeON() {

        testMode = true;
        Tester tester = new Tester();
        tester.start();
    }

    /**
     * Set the graph in normal mode
     */
    public void testModeOFF() {

        testMode = false;
    }

    /**
     * Thread to populate dummy data
     */
    private class Tester extends Thread {

        /**
         * test data
         */
        private LinkedList<TrafficEvent> testList;

        /**
         * Builds a populate tester
         */
        private Tester() {

            InetAddress address = null;
            try {
                address = InetAddress.getByName("192.168.1.1");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            testList = new LinkedList<>();
            testList.add(new TrafficEvent(null, TrafficType.INCOMING, address, 8888, 35000L, true));
            testList.add(new TrafficEvent(null, TrafficType.INCOMING, address, 8889, 6500L, false));
            testList.add(new TrafficEvent(null, TrafficType.OUTGOING, address, 8887, 3500000L, true));
            testList.add(new TrafficEvent(null, TrafficType.OUTGOING, address, 8883, 65L, false));
            testList.add(new TrafficEvent(null, TrafficType.OUTGOING, address, 7883, 13450L, false));
            testList.add(new TrafficEvent(null, TrafficType.INCOMING, address, 1881, 21983L, false));
            testList.add(new TrafficEvent(null, TrafficType.INCOMING, address, 8853, 1242365L, true));
            testList.add(new TrafficEvent(null, TrafficType.OUTGOING, address, 1276, 3333L, true));
        }

        @Override
        public void run() {

            long max = testList.size() - 1, min = 0;
            double range = Math.abs(max - min);

            while (testMode) {

                insertRow(testList.get((int) (Math.random() * range + min)));
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
