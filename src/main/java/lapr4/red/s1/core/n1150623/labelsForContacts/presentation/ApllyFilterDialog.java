package lapr4.red.s1.core.n1150623.labelsForContacts.presentation;

import javax.swing.*;
import java.awt.event.*;

public class ApllyFilterDialog extends JDialog {

    private JButton buttonOK;
    private JButton buttonCancel;

    private JTextField filterTextField;
    private JTextField regexExpressionInsertionTextField;

    private JRadioButton radioButton1;
    private JRadioButton radioButton2;

    private JPanel contentPane;
    private JPanel regexPanel;
    private JPanel filterPanel;


    public ApllyFilterDialog(String regex) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(regex);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regexPanel.setVisible(true);
                filterPanel.setVisible(false);
            }
        });

        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regexPanel.setVisible(false);
                filterPanel.setVisible(true);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        regexPanel.setVisible(false);
        filterPanel.setVisible(false);
    }

    private void onOK(String regexFinal) {

        String regex;
        if (radioButton1.isSelected()) {
            regex = regexExpressionInsertionTextField.getText().trim();
            if (regex.length() == 0) {
                regexFinal = ".*";
            } else {
                regexFinal = regex;
            }
        } else if (radioButton2.isSelected()) {
            regex = regexExpressionInsertionTextField.getText().trim();
            if (regex.length() == 0) {
                regexFinal = ".*"; //anything
            } else {
                regexFinal = regex; //anything that contains "regex" content
            }
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ApllyFilterDialog dialog = new ApllyFilterDialog("");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
