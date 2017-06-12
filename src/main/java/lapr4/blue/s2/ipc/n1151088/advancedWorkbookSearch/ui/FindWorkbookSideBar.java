package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui;

import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl.ControllerFindWorkbooks;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl.ControllerPreviewWorkbook;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FileDTO;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FindWorkbooksPublisher;
import lapr4.green.s1.ipc.n1150838.findworkbooks.ui.WorkbookList;

/**
 * @author nunopinto
 * @author Diana Silva [1151088@isep.ipp.pt]
 *         update on 10/06/2017 due to preview funcionality
 */
public class FindWorkbookSideBar extends JPanel implements Observer {
protected ActionListener[] buttonListeners;
    ControllerFindWorkbooks findController;
    ControllerPreviewWorkbook previewController;
    private UIController findExtension;
    private WorkbookList modeloWorkbook;
    private JList listWorkbook;
    private JTextField listField;
    
    private PreviewSpreadSheetTableModel previewTableModel;
    private JTable tablePreview;
    
    public FindWorkbookSideBar(UIController previewExtension){
        this.findExtension=previewExtension;
        FindWorkbooksPublisher.getInstance().addObserver(this);
 
        buildModels();
        buildPanel();
    }
    
    private void buildPanel(){
        setLayout(new GridLayout(2, 1));
        add(searchPanel());
        add(previewPanel());  
    }
    
    private void buildModels(){
        modeloWorkbook = new WorkbookList(new ArrayList());
        listWorkbook = new JList(modeloWorkbook);
        previewTableModel=new PreviewSpreadSheetTableModel(
         
                buildPreviewWorkbookDefault().getSpreadsheet(0), findExtension);
        tablePreview=new JTable(previewTableModel);
   
    }
    
    private JPanel previewPanel(){
        JPanel previewPanel=new JPanel();
        
        JPanel p=new JPanel();
        JLabel labelInicial = new JLabel("<html>Preview workbook</html>");
        previewPanel.add(labelInicial);
        
        p.add(tablePreview);
        previewPanel.add(p);
        previewPanel.setPreferredSize(new Dimension(100,40));
        return previewPanel;
    }
    
    private JPanel searchPanel(){
       JPanel p=new JPanel();
       p.setLayout(new BorderLayout());
        listWorkbook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 2 && index >= 0) {

                    // Double-click detected
                    modeloWorkbook.setSelectedItem(((String) modeloWorkbook.getElementAt(index)));
                    FileDTO dto = modeloWorkbook.getSelectedItem();

                    try {
                        Workbook b =findController.load(dto.getFilePath());
                       
                        findExtension.setActiveWorkbook(b);
               
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "File is corrupted or failed to load!");
                    }

                }
              
                if(evt.getClickCount()==1 && index>=0){
                    
                    // One-click detected
                    modeloWorkbook.setSelectedItem(((String) modeloWorkbook.getElementAt(index)));
                  
                    FileDTO dto = modeloWorkbook.getSelectedItem();
                    
                    
                    try {
                        Workbook wb =findController.load(dto.getFilePath());
                        
                        RangeDialog j=new RangeDialog(findExtension, wb, tablePreview);
                        if(previewController!=null)previewController.stopPreview();
                        
      
                  
                    } catch (IOException | ClassNotFoundException ex) {
                         JOptionPane.showMessageDialog(new JFrame(), "It wasn´t possible to generate the preview!");
                    }  
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
    
    private Workbook buildPreviewWorkbookDefault(){
        
        String[][] content={{""}};
        Workbook b= new Workbook(this.findExtension);
        b.addSpreadsheet(content);
        return b;
    }
    
    /**
     * creates the button to search for files
     * @return 
     */
    private JPanel buttonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(labelField());
        buttonsPanel.add(fieldTextField());
        buttonsPanel.add(mainButton());
         buttonsPanel.setPreferredSize(new Dimension(100,85));
        return buttonsPanel;
    }
    /**
     * creates the field inser path
     * @return 
     */
    private JLabel labelField() {
        JLabel label = new JLabel("<html>Insert path</html>");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField fieldTextField() {
        listField = new JTextField(10);
        return listField;
    }
    /**
     * creates the main button and the behavior off the method if a user clicks to search for files.
     * @return 
     */
    private JButton mainButton() {
        JButton mainButton = new JButton("Search");
        mainButton.addActionListener((ActionEvent e) -> {
            try {
                if(findController!=null)findController.stopSearch();
                modeloWorkbook.removeAll();
                previewTableModel.removeAll();
                findController = new ControllerFindWorkbooks(listField.getText());
                findController.searchFiles();
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Insert a valid path!");
            }
        });
        return mainButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof FileDTO){
            FileDTO workbook = (FileDTO) arg;
            modeloWorkbook.addElement(workbook);
        }
        if(arg instanceof Workbook){
             tablePreview.removeAll();
             Workbook workbook=(Workbook) arg;
            previewTableModel=new PreviewSpreadSheetTableModel(workbook.getSpreadsheet(0), findExtension);
            tablePreview.setModel(previewTableModel);
        }
        
    }

}