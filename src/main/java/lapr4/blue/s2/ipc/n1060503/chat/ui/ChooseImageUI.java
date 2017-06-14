/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.ui;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import lapr4.blue.s2.ipc.n1060503.chat.ChangeUserChatProfileController;

/**
 *
 * @author Pedro Fernandes
 */
public class ChooseImageUI extends JFileChooser{

    /**
     * file chooser
     */
    private JFileChooser fileChooser;
    
    private ChangeUserChatProfileController controller;
    
    public ChooseImageUI(ChangeUserChatProfileController controller) throws FileNotFoundException, IOException{    
        this.controller = controller;
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File("src/main/resources/lapr4/blue/s2/ipc/n1060503/chat/profile/res/img"));
        int resposta = fileChooser.showOpenDialog(ChooseImageUI.this);
        if (resposta == JFileChooser.APPROVE_OPTION) {
            ImageIcon imagem = new ImageIcon(this.fileChooser.getSelectedFile().getAbsolutePath());
            Image img = imagem.getImage();
            Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);            
            controller.changeIcon( new ImageIcon(newimg));
            JOptionPane.showMessageDialog(
                    null,
                    "Icon / Photo changed with success!",
                    "Change User Chat Profile",
                    JOptionPane.INFORMATION_MESSAGE);
        }        
    }
    
   
}
