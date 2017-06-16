package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile.ui;

import csheets.ext.Extension;
import csheets.ext.ExtensionManager;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.red.s3.ipc.n1150613.NetworkSearchByFile.NetworkSearchAction;

/**
 * Creates the sidebar panel that has the search workbooks in the network
 * feature.
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class NetworkSearchPanel extends JPanel {

    /**
     * The search results table.
     */
    private NetworkSearchTable resultsTable;

    /**
     * The input field for the name to search.
     */
    private JTextField nameField;

    private JTextField contentField;

    public NetworkSearchPanel(UIController uiController, Extension extension) {
        super(new BorderLayout());
        setName(CommExtension.NAME);
        createComponents();
    }

    /**
     * Creates the components of the panel.
     */
    private void createComponents() {
        add(createMainPanel(), BorderLayout.NORTH);
        add(createBottomPanel(), BorderLayout.CENTER);
        
    }


    /**
     * Creates the main panel.
     *
     * @return the main panel
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createSearchResultsPanel(), BorderLayout.NORTH);
        return mainPanel;
    }

    /**
     * Creates the bottom panel where the user is able to insert the search
     * name.
     *
     * @return the bottom panel
     */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel textLabel = new JLabel("Name:");
        JLabel contentLabel = new JLabel("Content:");
        nameField = new JTextField();
        contentField = new JTextField();
        nameField.setPreferredSize(new Dimension(100, 25));
        contentField.setPreferredSize(new Dimension(100, 25));
        bottomPanel.add(textLabel);
        bottomPanel.add(nameField);
        bottomPanel.add(createSearchButton());
        return bottomPanel;
    }

    /**
     * Creates the results table.
     *
     * @return the table with the search results
     */
    private JPanel createSearchResultsPanel() {
        resultsTable = new NetworkSearchTable();
        return resultsTable;
    }

    /**
     * The search button.
     *
     * @return the search button
     */
    private JButton createSearchButton() {
        JButton button = new JButton("Search");
        button.setPreferredSize(new Dimension(75, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommExtension ce = (CommExtension) ExtensionManager.getInstance().getExtension(CommExtension.NAME);
                resultsTable.clear();
                (new NetworkSearchAction(resultsTable, ce.getUDPServerPortNumber(), nameField.getText())).actionPerformed(e);
            }
        });
        return button;
    }

}
