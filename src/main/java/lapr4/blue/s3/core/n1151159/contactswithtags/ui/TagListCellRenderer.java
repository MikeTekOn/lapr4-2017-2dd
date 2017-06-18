package lapr4.blue.s3.core.n1151159.contactswithtags.ui;

import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a list cell renderer for tags.
 *
 * @author Ivo Ferro [1151159]
 */
public class TagListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            Tag tag = (Tag) value;
            value = tag.designation();
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }

}
