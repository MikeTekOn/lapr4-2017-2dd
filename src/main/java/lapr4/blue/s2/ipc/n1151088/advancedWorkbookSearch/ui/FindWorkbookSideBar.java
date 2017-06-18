package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui;

import csheets.CleanSheets;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl.ControllerFindWorkbooks;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl.ControllerPreviewWorkbook;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FileDTO;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FindWorkbooksPublisher;
import lapr4.green.s1.ipc.n1150838.findworkbooks.ui.WorkbookList;
import lapr4.red.s1.core.n1150451.exportPDF.presentation.ExportToPDFUI;
import lapr4.red.s3.ipc.n1150451.multipleRealtimeWorkbookSearch.comms.CommUDPServer;
import lapr4.red.s3.ipc.n1150451.multipleRealtimeWorkbookSearch.comms.FilePathDTO;
import lapr4.red.s3.ipc.n1150451.multipleRealtimeWorkbookSearch.comms.HandlerFilePathDTO;

/**
 * @author nunopinto
 * @author Diana Silva [1151088@isep.ipp.pt] update on 10/06/2017 due to preview
 * funcionality
 */
public class FindWorkbookSideBar extends JPanel implements Observer {

    protected ActionListener[] buttonListeners;
    public ControllerFindWorkbooks findController;
    ControllerPreviewWorkbook previewController;
    private UIController findExtension;
    private WorkbookList modeloWorkbook;
    private JList listWorkbook;
    private JTextField pathField;
    private JTextField regexField;

    private int currentIndexSelected;
    /**
     * Variable used to indicate the Thread if its an active search or not.
     */
    public boolean isThreadActive = false;

    private JTable tablePreview;
    public JButton searchButton;
    public boolean flagSucessClick = true;

    /**
     * IMPLEMENTED BY DIOGO SANTOS 1150451 This HashMap represents the Cache.
     *
     */
    private HashMap<String, PreviewSpreadSheetTableModel> cache = new HashMap<>();

    /**
     * @param previewExtension
     */
    public FindWorkbookSideBar(UIController previewExtension) {
        this.findExtension = previewExtension;
        FindWorkbooksPublisher.getInstance().addObserver(this);

        buildModels();
        add(buildSearchPreviewPanels());
    }

    public JPanel buildSearchPreviewPanels() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        grid.gridx = 0;
        grid.gridy = 0;
        panel.add(searchPanel(), grid);
        grid.gridy = 1;
        panel.add(previewPanel(), grid);
        return panel;
    }

    private void buildModels() {
        modeloWorkbook = new WorkbookList(new ArrayList());
        listWorkbook = new JList(modeloWorkbook);
        PreviewSpreadSheetTableModel previewTableModel = new PreviewSpreadSheetTableModel(
                buildPreviewWorkbookDefault().getSpreadsheet(0), findExtension);
        tablePreview = new JTable(previewTableModel);

    }

    private JPanel previewPanel() {
        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        JLabel labelInicial = new JLabel("<html>Preview workbook</html>");
        previewPanel.add(labelInicial);

        p.add(tablePreview);
        previewPanel.add(p);
        return previewPanel;
    }

    private JPanel searchPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        listWorkbook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                currentIndexSelected = list.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 2 && currentIndexSelected >= 0) {

                    // Double-click detected
                    modeloWorkbook.setSelectedItem(((String) modeloWorkbook.getElementAt(currentIndexSelected)));

                    FileDTO dto = modeloWorkbook.getSelectedItem();

                    try {

                        Workbook wb = findController.load(dto.getFilePath());
                        findExtension.setActiveWorkbook(wb);

                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "File is corrupted or failed to load!");
                    }

                }

                if (evt.getClickCount() == 1 && currentIndexSelected >= 0) {

                    // One-click detected
                    modeloWorkbook.setSelectedItem(((String) modeloWorkbook.getElementAt(currentIndexSelected)));

                    FileDTO dto = modeloWorkbook.getSelectedItem();

                    //try {
                    Workbook wb = null;
                    try {
                        wb = findController.loadPrev(dto.getFilePath());
//                        RangeDialog j = new RangeDialog(findExtension, wb, tablePreview);
                        //if(previewController!=null)previewController.stopPreview();
                    } catch (IOException ex) {
                        Logger.getLogger(FindWorkbookSideBar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FindWorkbookSideBar.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        updatePreview(dto.getFilePath(), wb);
                    } catch (IllegalArgumentException ex) {
                        ex.getMessage();
                    }

                    //}
                }
            }
        });
        JScrollPane mainScroll = new JScrollPane(listWorkbook);
        JLabel labelInicial = new JLabel("<html>Search workbook</html>");
        p.add(labelInicial, BorderLayout.NORTH);
        p.add(mainScroll, BorderLayout.CENTER);
        p.add(buttonsPanel(), BorderLayout.SOUTH);
        return p;
    }

    /**
     * Added by Diogo Santos Deletes and updates the preview.
     *
     * @return
     */
    private void updatePreview(String dtoPath, Workbook wb) {
        tablePreview.removeAll();

        PreviewSpreadSheetTableModel model = cache.get(dtoPath);
        if (model == null) {
            model = new PreviewSpreadSheetTableModel(wb.getSpreadsheet(0), findExtension);
            cache.put(dtoPath, model);
            System.out.println("coloquei na cache");
        }
        tablePreview.setModel(model);

    }

    private Workbook buildPreviewWorkbookDefault() {

        String[][] content = {{""}};
        Workbook b = new Workbook(this.findExtension);
        b.addSpreadsheet(content);
        return b;
    }

    /**
     * creates the button to search for files
     *
     * @return
     */
    private JPanel buttonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        grid.gridx = 0;
        grid.gridy = 0;
        buttonsPanel.add(fieldsPanel(), grid);

        grid.gridy = 1;
        buttonsPanel.add(searchButton(), grid);
        //buttonsPanel.setPreferredSize(new Dimension(100, 85));
        return buttonsPanel;
    }

    private JPanel fieldsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        grid.gridx = 0;
        grid.gridy = 0;
        panel.add(createPathLabelFieldPanel(), grid);
        grid.gridy = 1;
        panel.add(createExpressionLabelFieldPanel(), grid);
        return panel;
    }

    private JPanel createPathLabelFieldPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Path:");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        pathField = new JTextField(10);
        panel.add(pathField);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        Image img = null;
        try {
            img = ImageIO.read(CleanSheets.class.getResource("res/img/open.gif"));
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDFUI.class.getName()).log(Level.FINE, null, ex);
        }
        Image newimg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        JButton button = new JButton();
        button.setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        button.setBorder(emptyBorder);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.showOpenDialog(getRootPane());
                if (chooser.getSelectedFile() != null) {
                    pathField.setText(chooser.getSelectedFile().getPath());
                }
            }
        });
        panel.add(button);
        return panel;
    }

    private JPanel createExpressionLabelFieldPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Reg. Expression:");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        regexField = new JTextField(10);
        panel.add(regexField);
        return panel;

    }

    /**
     * creates the main button and the behavior off the method if a user clicks
     * to search for files.
     *
     * @return
     */
    private JButton searchButton() {
        searchButton = new JButton("Search");

        searchButton.addActionListener((ActionEvent e) -> {
            if (pathField.getText().trim().isEmpty() || regexField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Please complete all fields!");
                flagSucessClick = false;
            } else {
                CommUDPServer srv=null;
                if (searchButton.getText().equalsIgnoreCase("search")) {
                     srv = new CommUDPServer();
                    srv.addHandler(FilePathDTO.class, new HandlerFilePathDTO());

                    try {

                        modeloWorkbook.removeAll();
                        cache.clear(); //CLEARS THE CACHE
                        findController = new ControllerFindWorkbooks(pathField.getText(), regexField.getText(), isThreadActive);

                        findController.searchFiles();
                        if (!srv.isAlive()) {
                            srv.start();
                        }
                        searchButton.setText("Stop");
                        flagSucessClick = true;
                        JOptionPane.showMessageDialog(new JFrame(), "The search has finished");
                        searchButton.setText("Search");
                        srv.interrupt();
                    } catch (IllegalStateException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Insert a valid path!");
                        flagSucessClick = false;

                    }
                } else {

                    findController.stopSearch();
                    searchButton.setText("Search");
                    srv.interrupt();

                }
            }
        });
        return searchButton;
    }

    public boolean checkFindControllerNull() {
        return findController == null;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof FileDTO) {
            FileDTO workbook = (FileDTO) arg;
            if (!modeloWorkbook.contains(workbook)) {
                modeloWorkbook.addElement(workbook);
            }
        }

        if (arg instanceof FilePathDTO) {
            FilePathDTO dto = (FilePathDTO) arg;
            if (modeloWorkbook.contains(dto)) {
                int idx = listWorkbook.getSelectedIndex();
                if (idx >= 0) {

                    Workbook wb = null;
                    try {
                        wb = findController.loadPrev(dto.getFilePath());
//                        RangeDialog j = new RangeDialog(findExtension, wb, tablePreview);
                        //if(previewController!=null)previewController.stopPreview();
                    } catch (IOException ex) {
                        Logger.getLogger(FindWorkbookSideBar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FindWorkbookSideBar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cache.remove(dto.getFilePath());
                    updatePreview(dto.getFilePath(), wb);
                    JOptionPane.showMessageDialog(this, dto.getFileName() + " was updated.");
                }
            }
        }

        if (arg instanceof Workbook) {
            tablePreview.removeAll();
            Workbook workbook = (Workbook) arg;

            PreviewSpreadSheetTableModel previewTableModel = new PreviewSpreadSheetTableModel(workbook.getSpreadsheet(0), findExtension);
            tablePreview.setModel(previewTableModel);
        }

    }

}
