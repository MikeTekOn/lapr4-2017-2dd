package lapr4.blue.s3.core.n1151159.contactswithtags.presentation;

import java.util.LinkedList;
import java.util.List;

/**
 * A watch dog to seek tags updates and inform the registered listeners.
 * The singleton design pattern is used to achieve this goals.
 *
 * @author Ivo Ferro [115115]
 */
public class TagsChangesWatchDog {

    /**
     * The singleton instance.
     */
    private static final TagsChangesWatchDog INSTANCE = new TagsChangesWatchDog();

    /**
     * The tag frequency listeners.
     */
    private final List<TagFrequencyPanel> tagFrequencyListeners;

    /**
     * Creates the instance of TagsChangesWatchDog.
     * Private constructor to prevent the creation of more instances.
     */
    private TagsChangesWatchDog() {
        tagFrequencyListeners = new LinkedList<>();
    }

    /**
     * Gets the singleton instance of this class.
     *
     * @return singleton instance of this class
     */
    public static TagsChangesWatchDog getInstance() {
        return INSTANCE;
    }

    /**
     * Adds a tag frequency listener.
     *
     * @param listener tag frequency listener
     */
    public void addTagFrequencyListener(TagFrequencyPanel listener) {
        this.tagFrequencyListeners.add(listener);
    }

    /**
     * Notifies the registered listeners.
     */
    public void notifyListeners() {
        for (TagFrequencyPanel listener : tagFrequencyListeners) {
            listener.actionPerformed(null);
        }
    }
}
