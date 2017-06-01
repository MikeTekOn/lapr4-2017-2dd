package lapr4.red.s1.core.n1150385.enabledisableextensions;

import java.util.EventListener;

/** A listener interface for receiving notifications on events occurring in the extensions list
 * Created by Ricardo Catalao (1150385) on 5/31/17.
 */
public interface ExtensionStateListener extends EventListener {

    /**
     * Invoked by UIController to indicate interested parties that an
     * extension has been enabled or disabled
     * @param event
     */
    public void extensionStateChanged(ExtensionEvent event);

}
