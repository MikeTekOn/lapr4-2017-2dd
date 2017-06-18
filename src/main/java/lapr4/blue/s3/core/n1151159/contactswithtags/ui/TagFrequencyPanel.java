package lapr4.blue.s3.core.n1151159.contactswithtags.ui;

import csheets.ui.ctrl.UIController;
import lapr4.blue.s3.core.n1151159.contactswithtags.application.TagController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The panel to show the tags frequencies.
 */
public class TagFrequencyPanel extends JPanel implements ActionListener {

    /**
     * The tag controller.
     */
    private final TagController controller;

    /**
     * The tags frequency table.
     */
    private JTable tagsFrequencyTable;

    /**
     * Creates a new instance of tag frequency panel.
     *
     * @param uiController the user interface controller
     */
    public TagFrequencyPanel(UIController uiController) {
        controller = new TagController(uiController.getUserProperties());

        createComponents();
        actionPerformed(null);
    }

    /**
     * Creates the user interface components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel();

        componentsPanel.add(createTablePanel());

        add(componentsPanel);
    }

    /**
     * Creates a new table panel.
     *
     * @return table panel
     */
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel();

        tagsFrequencyTable = new JTable(new TagsFrequencyTableModel(new HashMap<>()));
        JScrollPane scrollPane = new JScrollPane(tagsFrequencyTable);
        scrollPane.setPreferredSize(new Dimension(300, 80));

        tablePanel.add(scrollPane);

        return tablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableModel tableModel = new TagsFrequencyTableModel(controller.getTagFrequency());
        tagsFrequencyTable.setModel(tableModel);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            tagsFrequencyTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
        tagsFrequencyTable.setRowSorter(sorter);

        java.util.List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }
}
