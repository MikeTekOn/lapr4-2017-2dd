package lapr4.blue.s3.core.n1151159.contactswithtags.ui;

import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Contactable;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A table model for contactable collections.
 *
 * @author Ivo Ferro [1151159]
 */
public class ContactableTableModel extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private static final String[] COLUMNS_NAMES = {"Name", "Number", "Type", "Tags"};

    /**
     * The elements list.
     */
    private List<Contactable> elements;

    /**
     * Creates a new contactable table model receiving their elements.
     *
     * @param elements the contactable elements
     */
    public ContactableTableModel(List<Contactable> elements) {
        this.elements = elements;
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
                return elements.get(rowIndex).contactName();
            case 1:
                return elements.get(rowIndex).contactNumber();
            case 2:
                return elements.get(rowIndex).contactType();
            case 3:
                String tagsText = "";
                List<Tag> tags = new ArrayList<>(elements.get(rowIndex).getTags());
                for (int i = 0; i < tags.size(); i++) {
                    if (i == 0) {
                        tagsText = tags.get(i).designation();
                    } else {
                        tagsText = tagsText + ", " + tags.get(i).designation();
                    }
                }
                return tagsText;
            default:
                return null;
        }
    }
}
