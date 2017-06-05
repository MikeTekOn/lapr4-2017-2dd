package lapr4.green.s1.ipc.n1150738.securecomm.ui;

import csheets.ui.ctrl.UIController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by henri on 6/2/2017.
 */
public class IncomingOutgoingCommsUI extends JDialog {

    protected UIController uiController;

    public IncomingOutgoingCommsUI( UIController uiController) {
        super((Frame) null, "Incoming Outgoing Connections", true);
        this.uiController = uiController;

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
        jpanel.add(new JLabel("Outgoing"));

        OutgoingCommsTableModel outModel = new OutgoingCommsTableModel();
        JTable table = new JTable(outModel);
        table.setMinimumSize(new Dimension(500,250));
        JScrollPane  scrollPane = new JScrollPane(table);

        jpanel.setMinimumSize(new Dimension(500,250));
        jpanel.add(scrollPane);

        jpanel.add(new JLabel("Incomming"));

        IncommingCommsTableModel inModel = new IncommingCommsTableModel();
        JTable table2 = new JTable(inModel);
        table2.setMinimumSize(new Dimension(500,250));
        JScrollPane  scrollPane2 = new JScrollPane(table2);
        jpanel.add(scrollPane2);


        add(jpanel);
        setPreferredSize(new Dimension(600,300));
        setMinimumSize(new Dimension(600,300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
