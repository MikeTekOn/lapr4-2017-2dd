package lapr4.blue.s2.ipc.n1060503.chat.ui;

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
import lapr4.blue.s2.ipc.n1060503.chat.profile.UserChatProfile;

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
        final Dimension size = new Dimension(400, 125);

        setLayout(new GridLayout(1, 1));
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(false);
        table.setPreferredScrollableViewportSize(size);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(bottomUpPadding, sidesPadding, bottomUpPadding, sidesPadding));
        add(scrollPane);
    }

    /**
     * It adds a new connection to the Table.
     *
     * @param ucp The connection to be added.
     */
    public void insertRow(UserChatProfile ucp) {
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
    public UserChatProfile getSelectedRowFile() {
        return model.provideConnection(table.getSelectedRow());
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
        if (arg instanceof UserChatProfile) {
            insertRow((UserChatProfile) arg);
        }
    }

}
