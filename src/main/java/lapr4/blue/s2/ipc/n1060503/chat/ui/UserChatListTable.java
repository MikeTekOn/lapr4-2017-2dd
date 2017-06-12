package lapr4.blue.s2.ipc.n1060503.chat.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import lapr4.blue.s2.ipc.n1060503.chat.connection.UserChatDTO;

/**
 * It provides a table with connections information.
 *
 * @author Pedro Fernandes
 */
public class UserChatListTable extends JPanel implements Observer {

    /**
     * The table itself.
     */
    private JTable table;

    /**
     * The controller of the table.
     */
    private final ChatParticipantsModel model;

    /**
     * The constructor of the table UI.
     */
    public UserChatListTable() {
        super();
        model = new ChatParticipantsModel();
        createUserInterface();
    }

    /**
     * It builds the graphical user interface of the table.
     */
    private void createUserInterface() {
        final int bottomUpPadding = 5;
        final int sidesPadding = 20;
        final Dimension size = new Dimension(400, 200);

        setLayout(new GridLayout(1, 1));
        table = new JTable(model) {
            @Override
            public Class getColumnClass(int column) {
                return (column == 0) ? Icon.class : String.class;
            }
        };
        table.setAutoCreateRowSorter(true);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        ImageRenderer imgRender = new ImageRenderer();
        imgRender.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(imgRender);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRender);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRender);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(false);
        table.setPreferredScrollableViewportSize(size);
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(bottomUpPadding, sidesPadding, bottomUpPadding, sidesPadding));
        add(scrollPane);
    }

    class ImageRenderer extends DefaultTableCellRenderer {

        JLabel lbl = new JLabel();
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            lbl.setIcon((Icon) value);
            return lbl;
        }
    }

    /**
     * It adds a new connection to the Table.
     *
     * @param ucp The connection to be added.
     */
    public void insertRow(UserChatDTO ucp) {
        model.addRow(ucp);
    }

    /**
     * It removes all connections from the list.
     */
    public void clear() {
        model.clear();
    }

    /**
     * It provides the connection matching the selected row.
     *
     * @return It returns the connection associated with the selected row.
     */
    public UserChatDTO getSelectedRowFile() {
        return model.getUserChatDTO(table.getSelectedRow());
    }

    /**
     * It adds a new selection listener to the table.
     *
     * @param listener The listener to be added.
     */
    public void addListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * It listens to new connections to be inserted on the table.
     *
     * @param o The observable item.
     * @param arg The event from which to extract the connection id.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof UserChatDTO) {
            insertRow((UserChatDTO) arg);
        }
    }

}
