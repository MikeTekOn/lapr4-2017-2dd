package lapr4.blue.s1.lang.n1151031.formulastools.ui;

import csheets.core.Cell;
import csheets.ext.style.StyleExtension;
import csheets.ext.style.ui.BorderChooser;
import csheets.ext.style.ui.FontChooser;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import csheets.ui.sheet.SpreadsheetTable;
import lapr4.blue.s1.lang.n1151031.formulastools.ConditionStylableCell;
import lapr4.blue.s1.lang.n1151031.formulastools.ConditionStylableCellListener;
import lapr4.blue.s1.lang.n1151031.formulastools.ConditionalStyleExtension;
import lapr4.blue.s1.lang.n1151031.formulastools.UserStyle;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Tiago Correia - 1151031@isep.ipp.pt
 * Edited by João Cardoso - 1150943
 *  - Moved UserStyle from Panel to respetive cells
 */
public class ConditionalStylePanel extends JPanel implements SelectionListener, ConditionStylableCellListener {

    /**
     * The assertion controller
     */
    private ConditionalStyleController controller;

    /**
     * The condition formatable cell currently being displayed in the panel
     */
    private ConditionStylableCell cell;

    /**
     * The selected cells user's selected styling options.
     */
    private UserStyle currentUserStyle;

    /**
     * The condition field in which the condition of the cell should be
     * inserted.
     */
    private JTextField conditionField = new JTextField();

    /**
     * Created by João Cardoso - 1150943
     */
    private JLabel cellsChanged=null;

    /**
     * Creates a new condition style panel.
     *
     * @param uiController the user interface controller
     */
    public ConditionalStylePanel(UIController uiController) {


        // Configures panel
        super(new BorderLayout());
        setName(ConditionalStyleExtension.NAME);

        // Creates controller
        controller = new ConditionalStyleController(uiController, this);
        uiController.addSelectionListener(this);

        currentUserStyle = new UserStyle();


        JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel conditionLabel = new JLabel("Condition:");
        conditionField.setPreferredSize(new Dimension(150, 25)); // width, height
        conditionPanel.add(conditionLabel);
        conditionPanel.add(conditionField);

        //Style Selection Label Panel
        JPanel selectStylePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel selectStyleLabel = new JLabel("Style Selection");
        selectStylePanel.add(selectStyleLabel);

        //True style Panel
        JPanel trueStylePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel trueLabel = new JLabel("True:");
        trueStylePanel.add(trueLabel);
        trueStylePanel.add(createTrueFontButton());
        trueStylePanel.add(createTrueForegroundButton());
        trueStylePanel.add(createTrueBackgroundButton());
        trueStylePanel.add(createTrueBordersButton());

        //False style Panel
        JPanel falseStylePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel falseLabel = new JLabel("False:");
        falseStylePanel.add(falseLabel);
        falseStylePanel.add(createFalseFontButton());
        falseStylePanel.add(createFalseForegroundButton());
        falseStylePanel.add(createFalseBackgroundButton());
        falseStylePanel.add(createFalseBordersButton());

        //buttons Panel (true and false style panels)
        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(trueStylePanel, BorderLayout.CENTER);
        buttonsPanel.add(falseStylePanel, BorderLayout.SOUTH);

        //reset panel
        JPanel resetPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel resetLabel = new JLabel("Reset:");
        resetPanel.add(resetLabel);
        resetPanel.add(createResetButton());

        // Apply changes panel
        cellsChanged = new JLabel();
        JPanel applyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel applyLabel = new JLabel("Apply Conditional Formatting:");
        JButton applyConditionButton = new JButton("Apply Conditions");

        //Condition Panel - Created by João Cardoso - 1150943
        applyConditionButton.addActionListener(new addConditionsToSelectionAction());
        applyPanel.add(cellsChanged);
        applyPanel.add(applyLabel);
        applyPanel.add(applyConditionButton);

        JPanel stylesPanel = new JPanel(new BorderLayout());
        stylesPanel.add(selectStylePanel, BorderLayout.NORTH);
        stylesPanel.add(buttonsPanel, BorderLayout.CENTER);
        stylesPanel.add(resetPanel, BorderLayout.SOUTH);

        // Lays out comment components
        JPanel conditionalStylePanel = new JPanel();
        conditionalStylePanel.setLayout(new BorderLayout());
        conditionalStylePanel.setPreferredSize(new Dimension(100, 200));
        conditionalStylePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE)); // width, height
        conditionalStylePanel.add(conditionPanel, BorderLayout.CENTER);
        conditionalStylePanel.add(stylesPanel, BorderLayout.SOUTH);

        // Adds borders
        TitledBorder border = BorderFactory.createTitledBorder("Conditional Formatting");
        border.setTitleJustification(TitledBorder.CENTER);
        conditionalStylePanel.setBorder(border);

        // Adds panels
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(conditionalStylePanel, BorderLayout.NORTH);
        add(northPanel, BorderLayout.NORTH);
        add(applyPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the Font button for the true condition style
     *
     * @return the Font button
     */
    private JButton createTrueFontButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/font.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lets user select a font
                Font font = FontChooser.showDialog(
                        null,
                        "Choose Font",
                        currentUserStyle.getTrueStyleFont());
                currentUserStyle.setTrueStyleFont(font);
                styleChanged();

            }
        });
        return button;
    }

    /**
     * Creates the Foreground button for the true condition style
     *
     * @return the Foreground button
     */
    private JButton createTrueForegroundButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/color_fg.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lets user select color
                Color color = JColorChooser.showDialog(
                        null,
                        "Choose Foreground Color",
                        currentUserStyle.getTrueStyleForegroundColor());
                currentUserStyle.setTrueStyleForegroundColor(color);
                styleChanged();
            }
        });
        return button;
    }

    /**
     * Creates the Background button for the true condition style
     *
     * @return the Background button
     */
    private JButton createTrueBackgroundButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/color_bg.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(
                        null,
                        "Choose Background Color",
                        currentUserStyle.getTrueStyleBackgroundColor());
                currentUserStyle.setTrueStyleBackgroundColor(color);
                styleChanged();
            }
        });
        return button;
    }

    /**
     * Creates the Border button for the true condition style
     *
     * @return the Border button
     */
    private JButton createTrueBordersButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/border.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lets user select a border
                Border border = BorderChooser.showDialog(
                        null,
                        "Choose Border",
                        currentUserStyle.getTrueStyleBorder());
                currentUserStyle.setTrueStyleBorder(border);
                styleChanged();
            }
        });
        return button;
    }

    /**
     * Creates the Font button for the false condition style
     *
     * @return the Font button
     */
    private JButton createFalseFontButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/font.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lets user select a font
                Font font = FontChooser.showDialog(
                        null,
                        "Choose Font",
                        currentUserStyle.getFalseStyleFont());
                currentUserStyle.setFalseStyleFont(font);
                styleChanged();

            }
        });
        return button;
    }

    /**
     * Creates the Foreground button for the false condition style
     *
     * @return the Foreground button
     */
    private JButton createFalseForegroundButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/color_fg.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lets user select color
                Color color = JColorChooser.showDialog(
                        null,
                        "Choose Foreground Color",
                        currentUserStyle.getFalseStyleForegroundColor());
                currentUserStyle.setFalseStyleForegroundColor(color);
                styleChanged();

            }
        });
        return button;
    }

    /**
     * Creates the Background button for the false condition style
     *
     * @return the Background button
     */
    private JButton createFalseBackgroundButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/color_bg.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(
                        null,
                        "Choose Background Color",
                        currentUserStyle.getFalseStyleBackgroundColor());
                currentUserStyle.setFalseStyleBackgroundColor(color);
                styleChanged();
            }
        });
        return button;
    }

    /**
     * Creates the Border button for the false condition style
     *
     * @return the Border button
     */
    private JButton createFalseBordersButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/border.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lets user select a border
                Border border = BorderChooser.showDialog(
                        null,
                        "Choose Border",
                        currentUserStyle.getFalseStyleBorder());
                currentUserStyle.setFalseStyleBorder(border);
                styleChanged();
            }
        });
        return button;
    }

    /**
     * Creates the Reset button for the condition style
     *
     * @return the Reset button
     */
    private JButton createResetButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/reset.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.resetStyle();
                styleChanged();
            }
        });
        return button;
    }

    public void setConditionText(String text) {
        conditionField.setText(text);
    }

    /**
     * Updates the condition field
     *
     * @param event the selection event that was fired
     */
    public void selectionChanged(SelectionEvent event) {
        Cell cell = event.getCell();

        if (cell == null) {
            return;
        }

        if (cell.getExtension(ConditionalStyleExtension.NAME) == null) {
            return;
        }
        if (cell != null) {
            ConditionStylableCell activeCell = (ConditionStylableCell) cell.getExtension(ConditionalStyleExtension.NAME);
            currentUserStyle = activeCell.userStyle();
            activeCell.addConditionStylableCellListener(this);
            conditionChanged(activeCell);
        } else {
            conditionField.setText("");
        }
        // Stops listening to previous active cell
        if (event.getPreviousCell() != null) {
            ((ConditionStylableCell) event.getPreviousCell().getExtension(ConditionalStyleExtension.NAME))
                    .removeConditionalStylableCellListener(this);
        }
    }

    /**
     * Applies the user style change.
     */
    private void styleChanged() {
        if (cell != null && cell.hasCondition()) {
            controller.setCondition(conditionField.getText().trim());
        }
    }

    /**
     * Updates the condition field when the condition of the active cell is
     * changed.
     *
     * @param cell the cell whose condition changed
     */
    @Override
    public void conditionChanged(ConditionStylableCell cell) {
        // Stores the cell for use when applying conditions
        this.cell = cell;
        // The controller must decide what to do...
        controller.cellSelected(cell);
    }

    protected class addConditionsToSelectionAction extends FocusOwnerAction implements ActionListener{

        @Override
        protected String getName() {
            return "Selected cells";
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList<Cell>selectedCells = focusOwner.getSelectedCellsList();
            int size = selectedCells.size();
            System.out.println(size);
            if(controller.setSelectedCells(selectedCells)){
                try {
                    if (conditionField.getText().isEmpty()) {
                        controller.setCondition("");
                    }
                    controller.setCondition(conditionField.getText().trim());
                    controller.setStyle(currentUserStyle);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Cells from ");
                stringBuilder.append(selectedCells.get(0).getAddress().toString());
                stringBuilder.append(" to ");
                stringBuilder.append(selectedCells.get(size-1).getAddress().toString());
                stringBuilder.append(" conditions were sucessfully updated");
                cellsChanged.setText(stringBuilder.toString());
            }else{
                cellsChanged.setText("Error updating cells conditions");
            }
        }
    }

}
