/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1140925.fileShare;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João
 */
public class ShareFile implements Serializable {

    private String name;

    private int size;

    public ShareFile() {

    }

    public ShareFile(String name, int size) {

        this.name = name;
        this.size = size;

    }

    /**
     * return the information of the share file
     *
     * @return textual representation
     */
    @Override
    public String toString() {
        return "Name: "+this.name + " Size: " + this.size + "KBytes";
    }

}
