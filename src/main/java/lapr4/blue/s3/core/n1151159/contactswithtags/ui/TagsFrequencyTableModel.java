package lapr4.blue.s3.core.n1151159.contactswithtags.ui;

import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A table model for tags frequencies.
 */
public class TagsFrequencyTableModel extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private static final String[] COLUMNS_NAMES = {"Tag", "Frequency"};

    /**
     * The tags frequencies.
     */
    private final Map<Tag, Integer> elements;

    /**
     * Creates a new instance of tags frequency table model.
     *
     * @param elements the tags frequency
     */
    public TagsFrequencyTableModel(Map<Tag, Integer> elements) {
        this.elements = new LinkedHashMap<>(elements);
    }

    @Override
    public int getRowCount() {
        return elements.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS_NAMES.length;
    }

    @Override
    public String getColumnName(int i) {
        return COLUMNS_NAMES[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ((Tag) elements.keySet().toArray()[rowIndex]).designation();
            case 1:
                return elements.values().toArray()[rowIndex];
            default:
                return null;
        }
    }
}
