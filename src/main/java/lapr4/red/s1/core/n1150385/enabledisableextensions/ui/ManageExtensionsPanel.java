package lapr4.red.s1.core.n1150385.enabledisableextensions.ui;

import csheets.ext.Extension;
import csheets.ext.ExtensionManager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ricardo Catalao (1150385) on 6/1/17.
 */
public class ManageExtensionsPanel extends JDialog {

    /**
     * Default constructor. Creates a JDialog and disables the parent window.
     *
     * @param parent
     */
    public ManageExtensionsPanel(JFrame parent){
        super(parent, true);
        buildPanels();
        setTitle("Enable/Disable Extensions");
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Creates the window elements
     */
    private void buildPanels(){
        ExtensionModel enabledExtensionsModel = new ExtensionModel(ExtensionManager.getInstance().getExtensions());
        JList<Extension> enabledExtensionJList = new JList<>(enabledExtensionsModel);
        enabledExtensionJList.setCellRenderer(new ExtensionRenderer());

        JScrollPane enabledExtensionsPanel = new JScrollPane();
        enabledExtensionsPanel.setViewportView(enabledExtensionJList);
        enabledExtensionsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Enabled"),
                BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
        enabledExtensionsPanel.setBackground(Color.WHITE);

        ExtensionModel disabledExtensionsModel = new ExtensionModel(ExtensionManager.getInstance().getDisabledExtensions());
        JList<Extension> disabledExtensionJList = new JList<>(disabledExtensionsModel);
        disabledExtensionJList.setCellRenderer(new ExtensionRenderer());

        JScrollPane disabledExtensionsPanel = new JScrollPane();
        disabledExtensionsPanel.setViewportView(disabledExtensionJList);
        disabledExtensionsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Disabled"),
                BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
        disabledExtensionsPanel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topPanel.add(enabledExtensionsPanel);
        topPanel.add(disabledExtensionsPanel);
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setPreferredSize(new Dimension(300, 300));

        JButton btnEnableExtensions = new JButton("Enable");
        btnEnableExtensions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(Extension extension : disabledExtensionJList.getSelectedValuesList()){
                    disabledExtensionsModel.removeExtension(extension);
                    enabledExtensionsModel.addExtension(extension);
                    ExtensionManager.getInstance().enableExtension(extension.getName());
                }
            }
        });

        JButton btnDisableExtensions = new JButton("Disable");
        btnDisableExtensions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(Extension extension : enabledExtensionJList.getSelectedValuesList()){
                    enabledExtensionsModel.removeExtension(extension);
                    disabledExtensionsModel.addExtension(extension);
                    ExtensionManager.getInstance().disableExtension(extension.getName());
                }
            }
        });

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.add(btnDisableExtensions);
        bottomPanel.add(btnEnableExtensions);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        bottomPanel.setBackground(Color.WHITE);

        Container pane = getContentPane();
        pane.setPreferredSize(new Dimension(400, 300));
        pane.setLayout(new BorderLayout());
        pane.add(topPanel, BorderLayout.CENTER);
        pane.add(bottomPanel, BorderLayout.SOUTH);
    }
}
