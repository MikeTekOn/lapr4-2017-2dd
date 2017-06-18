/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sofia Gonçalves (1150657)
 */
public class TagsName {

    public static final String DEFAULT_USER_TAG_NAME = "";

    private Map<String, String> tagNamesMap;

    public TagsName() {
        this.tagNamesMap= new HashMap<>();
        initializeMap();
    }

    /**
     * It initializes the map. First element is the default name for the map and
     * the second is empty.
     */
    private void initializeMap() {
        for (int i = 0; i < ElementCleansheet.sizeOf; i++) {
            tagNamesMap.put(ElementCleansheet.values()[i].getElementName(), DEFAULT_USER_TAG_NAME);
        }
    }

    /**
     * It replaces the empty value for the user tag name.
     *
     * @param defaultTagName
     * @param userTagName
     */
    public void addTagUserName(String defaultTagName, String userTagName) {
        this.tagNamesMap.replace(defaultTagName, userTagName);
    }
    
    public Map<String, String> getMapWithTags(){
        return this.tagNamesMap;
    }
    
    
//    /**
//     * Configures tag names by attributing the received list of names to the tag
//     * names.
//     *
//     */
//    public List<String> configureTagNames() {
//        List<String> result = new ArrayList<>();
//        for (Map.Entry<String, String> list : tagNamesMap.entrySet()) {
//            String defaultTagName = list.getKey();
//            String tagNameByUser = list.getValue();
//            if (tagNameByUser.isEmpty()) {
//                result.add(defaultTagName);
//            } else {
//                result.add(tagNameByUser);
//            }
//        }
//        return result;
//    }

}
