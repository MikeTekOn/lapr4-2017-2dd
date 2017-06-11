/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150657.extensions.ui;

import csheets.CleanSheets;
import csheets.ext.Extension;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.green.s1.ipc.n1150657.chat.ext.ChatExtension;
import lapr4.green.s1.ipc.n1150901.search.workbook.SearchWorkbookExtension;
import lapr4.green.s2.core.n1150657.extensions.ExtensionFactory;
import lapr4.green.s2.core.n1150657.extensions.ExtensionLoadController;

/**
 * It represents the frame for the extension option.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ExtensionLoadFrame extends JFrame {

    /**
     * Double clicking efect (number)
     */
    private static final int CLICKING_NUM = 2;

    /**
     * the name of the frame.
     */
    private static final String FRAME_NAME = "Load Extensions";

    /**
     * The name of joptionpane message.
     */
    private static final String MESSAGE_TITLE = "Extension metadata";

    /**
     * The name of the select button.
     */
    private static final String BUTTON_SELECT_NAME = "Select";

    /**
     * The name of the load button.
     */
    private static final String BUTTON_LOAD_NAME = "Load";

    /**
     * The name of the jcheckbox of select all.
     */
    private static final String JCHECKBOX_SELECT_ALL_NAME = "Select All";

    /**
     * The size dimension 1.
     */
    private static final int DIMENSION1 = 600;

    /**
     * The size dimension 2.
     */
    private static final int DIMENSION2 = 100;

    /**
     * The select button.
     */
    private JButton buttonSelect;
    /**
     * The load button.
     */
    private JButton buttonLoad;

    /**
     * The JCheckBox default, that means, if some extensions were choosen, this
     * as to be activated.
     */
    private JCheckBox communicationBoxForDefaultOption;

    /**
     * A map that will have a jcheckbox and a map connected that will have a
     * jlist and the extensions class names (different versions).
     */
    private Map<JCheckBox, Map<JList, ArrayList<String>>> group;

    /**
     * The controller.
     */
    private ExtensionLoadController controller;

    /**
     * The CleanSheets aplication.
     */
    private CleanSheets app;

    /**
     * The constructor for the frame.
     *
     * @param app The CleanSheet aplication.
     */
    public ExtensionLoadFrame(CleanSheets app) {
        super(FRAME_NAME);
        this.app = app;
        this.controller = new ExtensionLoadController();
        setUpFrame();
        createComponents();
        setVisible(true);
    }

    /**
     * It will set up the frame. Will have a minimum size and will be resizable.
     */
    private void setUpFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(DIMENSION2, DIMENSION1));
        setMinimumSize(new Dimension(DIMENSION1, DIMENSION2));
        setLocationRelativeTo(null);
        setResizable(true);
    }

    /**
     * It wil create the main components for the frame.
     */
    private void createComponents() {
        add(mainPanel());
        add(createPanelButtons(), BorderLayout.SOUTH);
    }

    /**
     * The main panel.
     *
     * @return It will return a JScrollPane.
     */
    private JScrollPane mainPanel() {
        Map<String, Set<Extension>> extensions = controller.getExtensions();
        int numberExtension = controller.numberOfExtensions();

        JPanel panel = new JPanel(new GridLayout(numberExtension + 2, 2));
        group = new HashMap<>();

        for (Map.Entry<String, Set<Extension>> aux : extensions.entrySet()) {
            JCheckBox box = new JCheckBox(aux.getKey());
            //It will save the box for the communication
            if (aux.getKey().equals(CommExtension.NAME)) {
                communicationBoxForDefaultOption = box;
            }
            int numberVersions = controller.numberVersionsByExtension(aux.getKey());
            String[] versions = new String[numberVersions];
            ArrayList<String> classNames = new ArrayList<>();
            int j = 0;
            for (Extension e : aux.getValue()) {
                versions[j] = String.format("Version %d | %s", e.getVersion(), e.getName());
                classNames.add(e.getClass().getName());
                j++;
            }
            JList jlist = new JList(versions);
            jlist.setSelectedIndex(0); //default choice
            box.setSelected(true);
            Map<JList, ArrayList<String>> auxMap = new HashMap<>();
            auxMap.put(jlist, classNames);
            group.put(box, auxMap);
            panel.add(box);
            panel.add(jlist);
        }

        setActionListenerAll();

        //It adds separators (2) to separate the options for the select all
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));

        JCheckBox boxInitital = new JCheckBox(JCHECKBOX_SELECT_ALL_NAME);
        boxInitital.setSelected(true);
        boxInitital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox box : group.keySet()) {
                    JList boxList = (JList) group.get(box).keySet().toArray()[0];
                    if (boxInitital.isSelected()) {
                        box.setSelected(true);
                        boxList.setSelectedIndex(0);
                    } else {
                        box.setSelected(false);
                        boxList.clearSelection();
                    }

                }

            }
        });
        panel.add(boxInitital);

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(panel);

        return scroll;
    }

    /**
     * It creates the buttons panel.
     *
     * @return It returns a jpanel with the buttons.
     */
    private JPanel createPanelButtons() {
        int allignment = FlowLayout.CENTER;
        JPanel panel = new JPanel(new GridLayout(1, 3));
        JPanel p1 = new JPanel(new FlowLayout(allignment));
        buttonSelect = new JButton(BUTTON_SELECT_NAME);
        buttonLoad = new JButton(BUTTON_LOAD_NAME);
        buttonSelect.addActionListener(new ExtensionLoadAction(this, app));
        buttonSelect.setEnabled(false);
        buttonLoad.addActionListener(new LoadAction());
        p1.add(buttonSelect);
        p1.add(buttonLoad);
        panel.add(p1);
        return panel;
    }

    /**
     * It set the action listener for all the components of the map list
     */
    private void setActionListenerAll() {
        //add action listener to checkbox
        for (Map.Entry<JCheckBox, Map<JList, ArrayList<String>>> g : group.entrySet()) {
            g.getKey().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox b = (JCheckBox) e.getSource();
                    //toArray()[0] because that only exist one list
                    JList listSelected = (JList) g.getValue().keySet().toArray()[0];
                    if (!b.isSelected()) {
                        listSelected.clearSelection();
                    } else {
                        listSelected.setSelectedIndex(0);
                    }
                    /*Default option, if chat extension is select, verify if Communication is to
                    Since Chat only works with communication and search workbook*/
                    String boxText = b.getText();
                    if (boxText.equals(ChatExtension.CHAT_NAME) || boxText.equals(SearchWorkbookExtension.NAME)) {
                        if (b.isSelected()) {
                            communicationBoxForDefaultOption.setSelected(true);
                            ((JList) group.get(communicationBoxForDefaultOption).keySet().toArray()[0]).setSelectedIndex(0);
                        }
                    }
                }
            });
            //add action listener to jlist
            for (Map.Entry<JList, ArrayList<String>> map : g.getValue().entrySet()) {
                map.getKey().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JList list = (JList) e.getSource();
                        if (e.getClickCount() == CLICKING_NUM) {
                            int index = list.getSelectedIndex();
                            String className = map.getValue().get(index);
                            Extension extensionAction = null;
                            try {
                                extensionAction = (Extension) Class.forName(className).newInstance();
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exc) {
                                //do nothing
                            }
                            String metadata = "";
                            try {
                                metadata = ExtensionFactory.getExtension(extensionAction.getName(), extensionAction.getVersion()).metadata();
                            } catch (IllegalArgumentException exce) {
                                metadata = exce.getMessage();
                            }
                            JOptionPane.showMessageDialog(null, metadata, MESSAGE_TITLE, JOptionPane.INFORMATION_MESSAGE);
                            //if the jcheckbox isn't selected, the selection of double clicking is removed
                            if (!g.getKey().isSelected()) {
                                g.getKey().setSelected(false);
                                list.clearSelection();
                            }
                        }
                    }
                });
            }
        }
    }
    
    /**
     * Private inner class that represents the load action.
     */
    private class LoadAction implements ActionListener {

        /**
         * The action for the load action. It will load the extensions and
         * disable the button.
         *
         * @param e The action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Map.Entry<JCheckBox, Map<JList, ArrayList<String>>> list : group.entrySet()) {
                if (list.getKey().isSelected()) {
                    JList listSelected = (JList) list.getValue().keySet().toArray()[0];
                    int index = listSelected.getSelectedIndex();
                    controller.load(list.getValue().get(listSelected).get(index));
                }
            }
            controller.load("lapr4.blue.s1.lang.n1151031.formulastools.ConditionalStyleExtension");//FIXME JoaoCardoso problem resolved
            buttonSelect.setEnabled(true);
            buttonLoad.setEnabled(false);
        }

    }
}
