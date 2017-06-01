package lapr4.red.s1.core.n1150385.enabledisableextensions;

import csheets.CleanSheets;
import csheets.ui.ctrl.BaseAction;
import lapr4.red.s1.core.n1150385.enabledisableextensions.ui.ManageExtensionsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Ricardo Catalao (1150385) on 6/1/17.
 */
public class ManageExtensionsAction extends BaseAction {

    /** The parent JFrame */
    private JFrame parent;

    /**
     * Default constructor
     *
     * @param parent the parent JFrame that created this action
     */
    public ManageExtensionsAction(JFrame parent){
        this.parent = parent;
    }

    @Override
    protected String getName() {
        return "Manage Extensions";
    }

    protected void defineProperties(){
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource("res/img/extensionManager.gif")));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new ManageExtensionsPanel(parent);
    }
}
