/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula.configurations;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import java.awt.Component;
import lapr4.red.s2.lang.n1150385.beanshell.utils.Pair;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A panel for changing exchange rates.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ConfigurateExchangeRatesUI extends JDialog {

    /**
     * The width of the JDialog.
     */
    private static final int WIDTH = 500;

    /**
     * The height of the JDialog.
     */
    private static final int HEIGHT = 400;

    /**
     * The controller of this use case.
     */
    private ConfigurateExchangeRatesController controller;

    /**
     * The user interface controller.
     */
    private UIController uiController;

    /**
     * The list with the several JTextField's needed to the configurations.
     */
    private List<JTextField> fields;

    /**
     * Creates a new panel for the configurations.
     *
     * @param uiController the user interface controller
     */
    public ConfigurateExchangeRatesUI(UIController uiController) {
        this.uiController = uiController;
        this.controller = new ConfigurateExchangeRatesController();
        this.fields = new ArrayList<>();
        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createComponents();
        setFocusable(true);
        setTitle("Configurate Exchange Rates");
        setLocationRelativeTo(null);
        super.setVisible(true);
    }

    /**
     * Initiates the components.
     */
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
        panel.add(createTablePanel(), grid);

        grid.fill = GridBagConstraints.EAST;
        grid.anchor = GridBagConstraints.CENTER;
        super.add(panel);
    }

    /**
     * Creates a JPanel with the JTextFields to edit the configurations.
     *
     * @return the created JPanel.
     */
    private JPanel createTablePanel() {
        Icon usaIcon = new ImageIcon(CleanSheets.class.getResource("res/img/usa.png"));
        Icon ukIcon = new ImageIcon(CleanSheets.class.getResource("res/img/uk.png"));
        Icon euroIcon = new ImageIcon(CleanSheets.class.getResource("res/img/euro.png"));

        JPanel p = new JPanel(new GridLayout(4, 4));
        p.setPreferredSize(new Dimension(300, 100));
        List<Pair<String, String>> exchangeRates = controller.getExchangeRates();

        p.add(new JLabel(""));
        p.add(new JLabel(euroIcon));
        p.add(new JLabel(usaIcon));
        p.add(new JLabel(ukIcon));

        for (Pair<String, String> pair : exchangeRates) {
            JTextField j = new JTextField(pair.getValue());
            j.setName(pair.getKey());
            j.setBackground(this.getBackground());
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    String valueChanged = j.getText();
                    String exchange = j.getName();
                    String valueToUpdate = "";
                    try {
                        valueToUpdate = controller.changeExchangeRate(exchange, Double.parseDouble(valueChanged.toString()));
                    } catch (IOException | URISyntaxException ex) {
                        Logger.getLogger(ConfigurateExchangeRatesUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String[] coinNames = exchange.split("To");
                    String dependentExchangeName = coinNames[1].trim() + "To" + coinNames[0].trim();
                    for (JTextField jt : fields) {
                        if (jt.getName().equals(dependentExchangeName)) {
                            jt.setText(valueToUpdate);
                            break;
                        }
                    }
                }
            });;
            fields.add(j);
            p.add(j);
        }
        
        p.add(new JLabel(euroIcon));
        p.add(new JLabel("<html><font color=red>1.0000</font></html>"));
        p.add(fields.get(0));
        p.add(fields.get(1));
        p.add(new JLabel(usaIcon));
        p.add(fields.get(2));
        p.add(new JLabel("<html><font color=red>1.0000</font></html>"));
        p.add(fields.get(3));
        p.add(new JLabel(ukIcon));
        p.add(fields.get(4));
        p.add(fields.get(5));
        p.add(new JLabel("<html><font color=red>1.0000</font></html>"));
        return p;
    }

}
