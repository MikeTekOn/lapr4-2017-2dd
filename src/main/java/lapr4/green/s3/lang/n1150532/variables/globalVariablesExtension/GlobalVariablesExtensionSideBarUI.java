package lapr4.green.s3.lang.n1150532.variables.globalVariablesExtension;

import csheets.SpreadsheetAppEvent;
import csheets.SpreadsheetAppListener;
import csheets.core.Workbook;
import csheets.core.formula.compiler.ExpressionCompiler;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lapr4.blue.s1.lang.n1151452.formula.compiler.ExcelExpressionCompiler;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;

/**
 * The side bar of the Global Variables Extension. It provides the list of all
 * existing global variables and allows their edition.
 *
 * @author Manuel Meireles (1150532)
 */
public class GlobalVariablesExtensionSideBarUI extends JPanel implements SpreadsheetAppListener, SelectionListener {

    /**
     * The user interface controller.
     */
    private final UIController uiController;

    /**
     * The expressions compiler.
     */
    private final ExpressionCompiler compiler;

    /**
     * The global variables table.
     */
    private GlobalVariablesTable table;

    /**
     * The panel in which to show error messages to the user.
     */
    private JLabel outInformation;

    /**
     * The field in which to write the commands to be compiled.
     */
    private JTextField inCommands;

    /**
     * The button to run the compiler.
     */
    private JButton btRunCommand;

    /**
     * The full constructor of the side bar.
     *
     * @param theUIController The user interface controller to use.
     */
    public GlobalVariablesExtensionSideBarUI(UIController theUIController) {
        super();
        uiController = theUIController;
        compiler = new ExcelExpressionCompiler();
        createGraphicUserInterface();
        createInteractions();
    }

    /**
     * It builds the graphical user interface of the side bar.
     */
    private void createGraphicUserInterface() {
        setName(GlobalVariablesExtension.NAME);
        setLayout(new BorderLayout());
        add(createTopPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    /**
     * It builds the top panel. It contains a label.
     *
     * @return It returns the panel built.
     */
    private JPanel createTopPanel() {
        final String labelText = "Global Variables List";
        final Font font = new Font(Font.MONOSPACED, Font.BOLD, 11);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        panel.add(label);
        return panel;
    }

    /**
     * It builds the central panel. It contains the global variables table.
     *
     * @return It returns the panel built.
     */
    private JPanel createTablePanel() {
        table = new GlobalVariablesTable();
        return table;
    }

    /**
     * It builds the bottom panel. It contains the error messages, as well as
     * the command field and button.
     *
     * @return It returns the panel built.
     */
    private JPanel createBottomPanel() {
        final String labelText = "Command: ";
        final String btText = "Run";
        final int size = 6;
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outInformation = new JLabel(" ");
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel(labelText);
        inCommands = new JTextField(size);
        btRunCommand = new JButton(btText);
        p1.add(outInformation);
        p2.add(label);
        p2.add(inCommands);
        p2.add(btRunCommand);
        panel.add(p1);
        panel.add(p2);
        return panel;
    }

    /**
     * It adds the listeners to the components.
     */
    private void createInteractions() {
        btRunCommand.addActionListener(new RunAction());
        inCommands.addKeyListener(new CommandTypeAction());
    }

    /**
     * It encapsulates the written command from the inCommand in order to only
     * allow one variable set.
     *
     * @return It returns the expression in a string form.
     */
    private String buildExpressionString() {
        return "=(@" + inCommands.getText() + ")";
    }

    /**
     * It compiles the built expression. It shows any error that might occur in
     * the outInformation label or clears the inCommand text if successful.
     */
    private void runCommand() {
        try {
            compiler.compile(null, buildExpressionString(), uiController);
            inCommands.setText("");
        } catch (FormulaCompilationException ex) {
            outInformation.setText(ex.getMessage());
        }
    }

    /**
     * It listens for the creation of a workbook in order to change the side bar
     * to the new global variables set.
     *
     * @param event The event from which to extract the current workbook.
     */
    @Override
    public void workbookCreated(SpreadsheetAppEvent event) {
        VarContentor globalVariables = event.getWorkbook().globalVariables();
        table.buildTableContent(globalVariables);
        globalVariables.addObserver(table);
    }

    /**
     * It listens for the loading of a workbook in order to change the side bar
     * to the new global variables set.
     *
     * @param event The event from which to extract the current workbook.
     */
    @Override
    public void workbookLoaded(SpreadsheetAppEvent event) {
        // There is nothing to do.
    }

    /**
     * It clears the table since no workbook is active.
     *
     * @param event The event from which to extract the old workbook.
     */
    @Override
    public void workbookUnloaded(SpreadsheetAppEvent event) {
        // There is nothing to do.
    }

    /**
     * Not used.
     *
     * @param event Not used.
     */
    @Override
    public void workbookSaved(SpreadsheetAppEvent event) {
        // There is nothing to do.
    }

    @Override
    public void selectionChanged(SelectionEvent event) {
        Workbook oldWorkbook = event.getPreviousWorkbook();
        Workbook newWorkbook = event.getWorkbook();
        if(!oldWorkbook.equals(newWorkbook)){
            VarContentor globalVariables = oldWorkbook.globalVariables();
            globalVariables.deleteObserver(table);
            globalVariables = newWorkbook.globalVariables();
            table.buildTableContent(globalVariables);
            globalVariables.addObserver(table);
        }
    }

    /**
     * The run button action.
     */
    private class RunAction implements ActionListener {

        /**
         * It runs the command.
         *
         * @param e Not used.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            runCommand();
        }

    }

    /**
     * The command typing action.
     */
    private class CommandTypeAction implements KeyListener {

        /**
         * Not used.
         *
         * @param e Not used.
         */
        @Override
        public void keyTyped(KeyEvent e) {
            //Do nothing
        }

        /**
         * Not used.
         *
         * @param e Not used.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            //Do nothing
        }

        /**
         * It clears the outInformation and if the released key was the enter
         * key, it runs the command.
         *
         * @param e The key event.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            outInformation.setText(" ");
            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                runCommand();
            }
        }

    }
}
