package lapr4.red.s1.core.n1150385.enabledisableextensions;

import java.util.EventObject;

/** An extension event is used to notify interested parties that an extension
 * has been added or removed from the UIController extension list
 * Created by Ricardo Catalao (1150385) on 5/31/17.
 */
public class ExtensionEvent extends EventObject {

    /**
     * Creates a new ExtensionEvent that simply notifies the listeners of a
     * change in the enabled extensions list
     * @param source
     */
    public ExtensionEvent(Object source) {
        super(source);
    }
}
