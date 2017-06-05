package lapr4.green.s1.ipc.n1150738.securecomm.ui;

import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientWorker;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by henri on 6/5/2017.
 */
public class TextAreaCellRenderer extends JScrollPane implements TableCellRenderer
{
    JTextArea textarea;
    TextAreaOutputStream stream = null;
    private OutgoingCommsTableModel model;

    public TextAreaCellRenderer(OutgoingCommsTableModel model) {
        this.model = model;
        textarea = new JTextArea("...");
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        getViewport().add(textarea);
    }


    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column)
    {
        if(stream == null){
            stream = new TextAreaOutputStream(textarea);
            CommTCPClientWorker wk = (CommTCPClientWorker) model.getWorker(row);
            wk.getTransmissionContext().wiretapOutput().attach(stream);
        }
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
            textarea.setForeground(table.getSelectionForeground());
            textarea.setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
            textarea.setForeground(table.getForeground());
            textarea.setBackground(table.getBackground());
        }

        textarea.setCaretPosition(0);
        return this;
    }
}