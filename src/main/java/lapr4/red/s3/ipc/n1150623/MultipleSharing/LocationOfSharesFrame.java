package lapr4.red.s3.ipc.n1150623.MultipleSharing;

import csheets.core.Spreadsheet;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Guilherme Ferreira 1150623 on 16/06/2017.
 */
public class LocationOfSharesFrame extends JFrame implements ActionListener {

    private static final int MAX_ROWS_LENGTH = 3;
    private static final int MAX_COLUMNS_LENGTH = 2;
    //DISPLAYED INFO
    private String mainLabelContent = "Where do you want to insert the cells that are being shared?";



    private final static String ok_action = "okAction";
    private final static String cancel_action = "CancelAction";

    private ShareOptionsController controller;

    public LocationOfSharesFrame(ShareOptionsController controller){
        super("Location of shares");
        this.controller = controller;
        iniComponents();
        setVisible(true);
    }

    private void iniComponents() {

        JPanel main = createMainPanel();
        JPanel endButtons = createEndButtons();
        this.setLayout(new BorderLayout());
        controller.getRange();
        JLabel lab = new JLabel("Must be a matrix of: "+controller.getRange());
        add(lab, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);
        add(endButtons, BorderLayout.PAGE_END);
        pack();
    }

    private JPanel createEndButtons() {
        JPanel buttons = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        okButton.setActionCommand(LocationOfSharesFrame.ok_action);
        cancelButton.setActionCommand(LocationOfSharesFrame.cancel_action);

        buttons.setLayout(new FlowLayout());
        buttons.add(okButton);
        buttons.add(cancelButton);
        return buttons;
    }

    JTextField start_Column_text;
    JTextField final_Column_text;
    JTextField start_row_text;
    JTextField final_row_text;


    private JPanel createMainPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        //Inicial Label
        Label information = new Label(mainLabelContent);
        p.add(information, BorderLayout.NORTH);

        //Range Selection
        JPanel aux = new JPanel();
        JPanel aux2 = new JPanel();
        aux.setLayout(new GridLayout(2,2));
        aux2.setLayout(new GridLayout(2,2));
        aux.setBorder(new TitledBorder("From"));
        aux2.setBorder(new TitledBorder("To"));

        JLabel start_row = new JLabel("ROW");
        JLabel start_column = new JLabel("COLUMN");
        start_row_text = new JTextField("");
        start_row_text.setColumns(MAX_ROWS_LENGTH);
        start_Column_text = new JTextField("");
        start_Column_text.setColumns(MAX_COLUMNS_LENGTH);

        //From
        aux.add(start_column);
        aux.add(start_Column_text);
        aux.add(start_row);
        aux.add(start_row_text);

        JLabel final_row = new JLabel("ROW");
        JLabel final_column = new JLabel("COLUMN");
        final_row_text = new JTextField("");
        final_row_text.setColumns(MAX_ROWS_LENGTH);
        final_Column_text = new JTextField("");
        final_Column_text.setColumns(MAX_COLUMNS_LENGTH);

        //To
        aux2.add(final_column);
        aux2.add(final_Column_text);
        aux2.add(final_row);
        aux2.add(final_row_text);

        p.add(aux, BorderLayout.NORTH);
        p.add(aux2, BorderLayout.SOUTH);
        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case LocationOfSharesFrame.ok_action:
                if(controller.setStartAddress(start_Column_text.getText().trim(), start_row_text.getText().trim())){
                    if(controller.setFinalAddress(final_Column_text.getText().trim(), final_row_text.getText().trim())){
                        if(controller.doAlterations()){
                            dispose();
                        }else{
                            error("Range");
                        }
                    }else{
                        error("Final Address");
                    }
                }else{
                    error("Initial Address");
                }
                break;

            case LocationOfSharesFrame.cancel_action:
                dispose();
                break;
        }
    }


    private void error(String s){
        JOptionPane.showMessageDialog(null, "ERROR", s + " is invalid!", JOptionPane.ERROR_MESSAGE);
    }
}
