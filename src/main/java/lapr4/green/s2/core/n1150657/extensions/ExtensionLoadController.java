/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150657.extensions;

import csheets.ext.Extension;
import csheets.ext.ExtensionManager;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * It represents the extension option controller.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ExtensionLoadController {

    /**
     * The extension manager.
     */
    private ExtensionManager extensionInstance = ExtensionManager.getInstance();;

    /**
     * The properties.
     */
    private Properties properties  = ExtensionManager.getInstance().getProperties();;

    /**
     * The extension option.
     */
    private ExtensionLoad extensionOption;

    /**
     * The extension option controller constructor.
     */
    public ExtensionLoadController() {
        this.extensionOption = new ExtensionLoad(properties, extensionInstance);
    }

    /**
     * It will load the extension to the CleanSheets.
     *
     * @param className The class name extension to be loaded.
     */
    public void load(String className) {
        extensionOption.load(className);
    }

    /**
     * It will give the number of extensions existed in the map.
     *
     * @return It returns an int with the number of extensions.
     */
    public int numberOfExtensions() {
        return extensionOption.numberOfExtensions();
    }

    /**
     * It will give the extension map.
     *
     * @return It returns a Map with the string key and a set extensions value.
     */
    public Map<String, Set<Extension>> getExtensions() {
        return extensionOption.getExtensionsMap();
    }

    /**
     * It will give the number of versions existing in a particulary extension.
     *
     * @param extensionName The extension name to search.
     * @return It returns an int with the number of versions.
     */
    public int numberVersionsByExtension(String extensionName) {
        return extensionOption.numberVersionsByExtension(extensionName);
    }

}
