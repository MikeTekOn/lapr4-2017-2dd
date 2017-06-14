package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui;

import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl.ControllerPreviewWorkbook;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;

/**
 *
 * @author Diana Silva
 */
public class RangeDialog extends JFrame{
    
    /**
     * The user interface controller
     */
    private UIController uiController;
    private Workbook workbook;
    
    JRadioButton radioButtonColumn;
    JRadioButton radioButtonRow ;
    JTextField txtFieldFirstCell;
    JTextField txtFieldLastCell;
    boolean column;
    CellRange cellRange;
    JTable tablePreview;

    public RangeDialog(UIController uiController, Workbook workbook, JTable tablePreview){
        this.uiController=uiController;
        this.workbook=workbook;
        this.tablePreview=tablePreview;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createComponents();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }      
                   
    /**
     * Creates the components of the frame
     */
    private void createComponents() {
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(3,1));
   
        panel.add(buildPanelRange());
        panel.add(buildPanelRadioButtons());
        panel.add( buildPanelButtons());

        add(panel);
    }
    
    private JPanel buildPanelRange(){
        JPanel subPanelRange = new JPanel();

        JLabel labelFirstCell = new JLabel("First Cell:");
        txtFieldFirstCell = new JTextField(5);

        JLabel labelLastCell = new JLabel("Last Cell:");
        txtFieldLastCell = new JTextField(5);

        subPanelRange.add(labelFirstCell);
        subPanelRange.add(txtFieldFirstCell);
        subPanelRange.add(labelLastCell);
        subPanelRange.add(txtFieldLastCell);
        return subPanelRange;
    }
    
    private JPanel buildPanelRadioButtons(){
        JPanel subPanelRadioButtons = new JPanel();

        JLabel labelFirstLine = new JLabel("The preview search for cells:");
        radioButtonColumn = new JRadioButton("Column-by-Column ");
        radioButtonRow = new JRadioButton("Row-by-Row ");
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonColumn);
        group.add(radioButtonRow);

        subPanelRadioButtons.add(labelFirstLine);
        subPanelRadioButtons.add(radioButtonColumn);
        subPanelRadioButtons.add(radioButtonRow);
        
        return subPanelRadioButtons;
    }
    
    private JPanel buildPanelButtons(){
        JPanel subPanelButtons = new JPanel();

        JButton buttonConfirm = new JButton("Confirm");
        buttonConfirm.addActionListener((ActionEvent e) -> {
            try {
               
                column = false;
                if (!radioButtonColumn.isSelected() && !radioButtonRow.isSelected()) {
                    throw new IllegalArgumentException("Choose a format option for the first line of the file!");
                } else if (radioButtonColumn.isSelected()) {
                    column = true;
                }

                /* CELL RANGE */
                String addressStrFirstCell = txtFieldFirstCell.getText();
                String addressStrLastCell = txtFieldLastCell.getText();

                cellRange = new CellRange(addressStrFirstCell, addressStrLastCell, uiController);
              
                
                         tablePreview.removeAll();
                        
                        PreviewSpreadSheetTableModel model=new PreviewSpreadSheetTableModel(this.workbook.getSpreadsheet(0), uiController);
                        tablePreview.setModel(model);
                
                dispose();
                
            } catch (IllegalArgumentException ex) {
                ex.getMessage();
            }
        });

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener((ActionEvent e) -> {
            dispose();
        });

        subPanelButtons.add(buttonConfirm);
        subPanelButtons.add(buttonCancel);
        return subPanelButtons;
    }
    
    public boolean selectedRadio(){
        return column;
    }
    
    public CellRange selectedRange(){
        return cellRange;
    }
}
