package lapr4.red.s1.core.n1150385.enabledisableextensions.ui;

import csheets.ext.Extension;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ricardo Catalao (1150385) on 6/1/17.
 */
public class ExtensionRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> jList, Object o, int i, boolean b, boolean b1) {
        Component c = super.getListCellRendererComponent(jList, o, i, b, b1);
        if(o instanceof Extension){
            setText(((Extension)o).getName());
        }
        return c;
    }
}
