/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150901.richCommentsAndHistory.presentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class ComplexCellRenderer extends JLabel implements ListCellRenderer {

    public ComplexCellRenderer() {
        setOpaque(true);
    }

    @Override
    @SuppressWarnings("empty-statement")
    public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        setText(value.toString());
        Color color = new Color(20, 20, 20);
        if (isSelected) {
            setFont((Font) value);
        } else if (value instanceof Color) {
            setForeground(color);
        }
        return this;
    }
}
