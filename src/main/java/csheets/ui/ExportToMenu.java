package csheets.ui;

import csheets.CleanSheets;
import csheets.SpreadsheetAppEvent;
import csheets.SpreadsheetAppListener;
import csheets.ui.ctrl.ActionManager;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 *
 * @author Diogo Santos
 */
@SuppressWarnings("serial")
public class ExportToMenu extends JMenu implements SpreadsheetAppListener {

    private final CleanSheets app;
    private final UIController uiController;
    private final ActionManager actionManager;

    public ExportToMenu(CleanSheets app, UIController uiController, ActionManager actionManager) {
        super("Export to");

        // Stores members
        this.app = app;
        this.uiController = uiController;
        this.actionManager = actionManager;

        // Configures menu
        app.addSpreadsheetAppListener(this);
        setMnemonic(KeyEvent.VK_E);
        setIcon(new ImageIcon(CleanSheets.class.getResource("res/img/reopen.gif")));

        add(actionManager.getAction("PDF"));
        add(new ExportToXmlSubMenu(actionManager));
    }

    public void workbookCreated(SpreadsheetAppEvent event) {
    }

    public void workbookLoaded(SpreadsheetAppEvent event) {
    }

    public void workbookUnloaded(SpreadsheetAppEvent event) {
    }

    public void workbookSaved(SpreadsheetAppEvent event) {
    }

}
