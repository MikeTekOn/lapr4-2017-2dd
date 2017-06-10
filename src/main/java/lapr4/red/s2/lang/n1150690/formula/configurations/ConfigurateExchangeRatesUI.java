/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula.configurations;

import csheets.ui.ctrl.UIController;
import lapr4.red.s2.lang.n1150385.beanshell.utils.Pair;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ConfigurateExchangeRatesUI extends JDialog {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private ConfigurateExchangeRatesController controller;
    private UIController uiController;

    private JTable table;
    private DefaultTableModel model;

    public ConfigurateExchangeRatesUI(UIController uiController) {
        this.uiController = uiController;
        this.controller = new ConfigurateExchangeRatesController();
        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createComponents();
        setFocusable(true);
        setTitle("Configurate Exchange Rates");
        super.setVisible(true);
    }

    private void createComponents() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;

        grid.gridx = 0;
        grid.gridy = 0;
        JLabel l = new JLabel("<html><h2>Configurate Exchange Rates</h2></html>");
        l.setBackground(this.getBackground());
        panel.add(l, grid);

        //grid.anchor = GridBagConstraints.CENTER;
        grid.insets = new Insets(10, 0, 10, 0);
        grid.gridx = 0;
        grid.gridy = 1;
        createTablePanel();
        JScrollPane p = new JScrollPane(table);
        p.setPreferredSize(new Dimension(250, 85));
        panel.add(p, grid);

        grid.fill = GridBagConstraints.EAST;
        grid.anchor = GridBagConstraints.CENTER;
        super.add(panel);
    }

    private void createTablePanel() {
        //ImageIcon usaIcon = new ImageIcon(CleanSheets.class.getResource("res/img/usa.png"));
        //ImageIcon ukIcon = new ImageIcon(CleanSheets.class.getResource("res/img/uk.png"));
        //ImageIcon euroIcon = new ImageIcon(CleanSheets.class.getResource("res/img/euro.png"));

        List<Pair<String, String>> exchangeRates = controller.getExchangeRates();

        String[] columnNames = {"", "Euro", "Dollar", "Pound"};
        Object[][] data = new Object[3][4];
        data[0][0] = "Euro";
        data[0][1] = "<html><font color=red>1.0000</font></html>";
        data[0][2] = exchangeRates.get(0).getValue();
        data[0][3] = exchangeRates.get(1).getValue();
        data[1][0] = "Dollar";
        data[1][1] = exchangeRates.get(2).getValue();
        data[1][2] = "<html><font color=red>1.0000</font></html>";
        data[1][3] = exchangeRates.get(3).getValue();
        data[2][0] = "Pound";
        data[2][1] = exchangeRates.get(4).getValue();
        data[2][2] = exchangeRates.get(5).getValue();
        data[2][3] = "<html><font color=red>1.0000</font></html>";

        table = new JTable();
        model = new DefaultTableModel(data, columnNames);
        table.setModel(model);

        table.setRowHeight(0, 15);

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                int column = tme.getColumn();
                int row = tme.getFirstRow();
                //TableModel model = (TableModel)tme.getSource();
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row, column);
                String exchangeName = model.getValueAt(row, 0).toString();
                exchangeName += "To" + columnName;
                double dataToChange = 1 / Double.parseDouble(data.toString());
                table.setValueAt(String.valueOf(dataToChange), column, row);
                model.fireTableDataChanged();
                try {
                    String d = controller.changeExchangeRate(exchangeName, Double.parseDouble(data.toString()));
                } catch (IOException ex) {
                    Logger.getLogger(ConfigurateExchangeRatesUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
