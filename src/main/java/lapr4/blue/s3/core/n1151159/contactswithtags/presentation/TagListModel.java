package lapr4.blue.s3.core.n1151159.contactswithtags.presentation;

import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;

import javax.swing.*;
import java.util.List;

/**
 * Represents a tag list model.
 *
 * @author Ivo Ferro [1151159]
 */
public class TagListModel extends AbstractListModel<Tag> {

    private final List<Tag> elements;

    public TagListModel(List<Tag> elements) {
        this.elements = elements;
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    @Override
    public Tag getElementAt(int i) {
        return elements.get(i);
    }
}
