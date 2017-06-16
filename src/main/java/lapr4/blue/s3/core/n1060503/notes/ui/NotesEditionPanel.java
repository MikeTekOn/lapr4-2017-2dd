/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Represents the panel of notes edition
 * @author Pedro Fernandes
 */
public class NotesEditionPanel extends JPanel{
    
    /**
     * The name for the border.
     */
    private static final String BORDER_NAME = "Notes";
    
    /**
     * The UI Controller.
     */
    private final UIController theController;
    
    /**
     * The extension.
     */
    private final NotesEditionExtension theExtension;
    
    public NotesEditionPanel(UIController uiController, Extension extension){
        super(new BorderLayout());
        theController = uiController;
        theExtension = (NotesEditionExtension) extension;
        buildPanel();
        changeComponentName(NotesEditionExtension.NAME);
    }
    
    /**
     * Private method to change the name of the component. It calls a component
     * method to change it.
     *
     * @param name The name to change it
     */
    private void changeComponentName(String name) {
        setName(name);
    }
    
    /**
     * Private method to build all the panel.
     */
    private void buildPanel() {
        JPanel panel = new JPanel();
        buildBorder(panel);
        add(panel, BorderLayout.NORTH);
    }
    
    /**
     * Private method to nuild the border for the chat.
     *
     * @param panel The chat panel to add the border.
     */
    private void buildBorder(JPanel panel) {
        TitledBorder border = BorderFactory.createTitledBorder(BORDER_NAME);
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(border);
    }
    
}
