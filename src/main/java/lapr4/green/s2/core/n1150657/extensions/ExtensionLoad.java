/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150657.extensions;

import csheets.ext.Extension;
import csheets.ext.ExtensionManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * It represents a extension option that will have the information of extensions
 * to be selected, the properties and a Extension Manager.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ExtensionLoad {

    /**
     * The map extensions that will have the extension name and a set of
     * extensions of different versions.
     */
    private Map<String, Set<Extension>> extensions = new HashMap<>();

    /**
     * The properties.
     */
    private final Properties properties;

    /**
     * The extension Manager.
     */
    private final ExtensionManager extensionInstance;

    /**
     * The extension option constructor that will initialize with the properties
     * and extension manager.
     *
     * @param properties The properties.
     * @param extensionInstance The extension manager.
     */
    public ExtensionLoad(Properties properties, ExtensionManager extensionInstance) {
        this.extensionInstance = extensionInstance;
        this.properties = properties;
        addExtensionNameToMap();
    }

    /**
     * It will add the extensions to the map. It will search from the properties
     * map that will give the class name. From that, the class is reach and from
     * that the extension. If the name of the extension doesn't exist in the
     * map, it will had if it does exist it adds in the set.
     *
     */
    private void addExtensionNameToMap() {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String name = (String) entry.getKey();
            if (!name.equals("lapr4.blue.s1.lang.n1151031.formulastools.ConditionalStyleExtension")) {//FIXME JoaoCardoso problem resolved
                Class c;
                Extension extension = null;
                try {
                    c = Class.forName(name);
                    extension = (Extension) c.newInstance();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    //do nothing
                    break;
                }
                String extensionName = extension.getName();
                if (extensions.containsKey(extensionName)) {
                    extensions.get(extension.getName()).add(extension);
                } else {
                    Set<Extension> aux = new HashSet<>();
                    aux.add(extension);
                    extensions.put(extensionName, aux);
                }
            }
        }
    }

    /**
     * It will give the extension map.
     *
     * @return It returns a Map with the string key and a set extensions value.
     */
    public Map<String, Set<Extension>> getExtensionsMap() {
        return extensions;
    }

    /**
     * It will give the number of extensions existed in the map.
     *
     * @return It returns an int with the number of extensions.
     */
    public int numberOfExtensions() {
        return extensions.size();
    }

    /**
     * It will give the number of versions existing in a particulary extension.
     *
     * @param extensionName The extension name to search.
     * @return It returns an int with the number of versions.
     */
    public int numberVersionsByExtension(String extensionName) {
        if (extensions.get(extensionName) != null) {
            return extensions.get(extensionName).size();
        } else {
            return 0;
        }
    }

    /**
     * It will load the class name.
     *
     * @param className The class name to be loaded.
     */
    public void load(String className) {
        extensionInstance.load(className);
    }

}
