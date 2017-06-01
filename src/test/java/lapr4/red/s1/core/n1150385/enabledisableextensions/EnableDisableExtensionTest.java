package lapr4.red.s1.core.n1150385.enabledisableextensions;

import csheets.ext.Extension;
import csheets.ext.ExtensionManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pyska on 6/1/17.
 */
public class EnableDisableExtensionTest {

    @Test
    public void testCanEnableAndDisableExtensions() {
        Extension[] extensions = ExtensionManager.getInstance().getExtensions();
        if(extensions.length > 0){
            int index = (int) Math.random() * extensions.length;
            ExtensionManager.getInstance().disableExtension(extensions[index].getName());
            Assert.assertEquals(null, ExtensionManager.getInstance().getExtension(extensions[index].getName()));
            ExtensionManager.getInstance().enableExtension(extensions[index].getName());
            Assert.assertNotEquals(null, ExtensionManager.getInstance().getExtension(extensions[index].getName()));
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "ExtensionManager did not load any extensions, so could not be tested.");
        }
    }
}
