/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package csheets.ui;

import csheets.CleanSheets;
import csheets.core.Workbook;
import csheets.ui.ctrl.*;
import csheets.ui.ext.UIExtension;
import csheets.ui.sheet.AddressBox;
import csheets.ui.sheet.CellEditor;
import csheets.ui.sheet.WorkbookPane;
import lapr4.red.s1.core.n1150385.enabledisableextensions.ExtensionEvent;
import lapr4.red.s1.core.n1150385.enabledisableextensions.ExtensionStateListener;
import lapr4.red.s1.core.n1150385.enabledisableextensions.ManageExtensionsAction;
import lapr4.red.s1.core.n1150451.exportPDF.presentation.ExportToPDFAction;

import javax.swing.*;
import java.awt.*;
import lapr4.blue.s1.lang.n1141570.XML.ui.ExportSelectedCellsActionUI;
import lapr4.blue.s1.lang.n1141570.XML.ui.ExportSelectedColumnActionUI;
import lapr4.blue.s1.lang.n1141570.XML.ui.ExportSelectedRowActionUI;
import lapr4.blue.s1.lang.n1141570.XML.ui.ExportSelectedSpreadsheetActionUI;
import lapr4.blue.s1.lang.n1141570.XML.ui.ExportWorkBookActionUI;
import lapr4.red.s2.lang.n1150613.FunctionWizard.ui.IntermediateFunctionWizard;
import lapr4.red.s2.lang.n1150690.formula.configurations.ConfigurateExchangeRatesAction;

/**
 * The main frame of the GUI.
 *
 * @author Einar Pehrson
 */
@SuppressWarnings("serial")
public class Frame extends JFrame implements SelectionListener, ExtensionStateListener {

    /**
     * The base of the window title
     */
    public static final String TITLE = "CleanSheets";

    /**
     * The CleanSheets application
     */
    private CleanSheets app;

    private ActionManager actionManager;
    private UIController uiController;

    WorkbookPane workbookPane;
    CellEditor cellEditor;
    AddressBox addressBox;
    IntermediateFunctionWizard functionWizard;

    /**
     * Creates a new frame.
     *
     * @param app the CleanSheets application
     */
    public Frame(CleanSheets app) {
        // Stores members and creates controllers
        this.app = app;
        uiController = new UIController(app);

        // Creates action manager
        FileChooser chooser = null;
        try {
            chooser = new FileChooser(this, app.getUserProperties());
        } catch (java.security.AccessControlException ace) {
        }
        actionManager = new ActionManager(app, uiController, chooser);

        // Registers file actions
        actionManager.registerAction("new", new NewAction(app));
        actionManager.registerAction("open", new OpenAction(app, uiController, chooser));
        actionManager.registerAction("close", new CloseAction(app, uiController, chooser));
        actionManager.registerAction("closeall", new CloseAllAction(app, uiController, chooser));
        actionManager.registerAction("save", new SaveAction(app, uiController, chooser));
        actionManager.registerAction("saveas", new SaveAsAction(app, uiController, chooser));
        actionManager.registerAction("PDF", new ExportToPDFAction(app, uiController, chooser));
        actionManager.registerAction("exit", new ExitAction(app, uiController, chooser));
        actionManager.registerAction("print", new PrintAction());

        // Register actions of XML exportation submenu
        actionManager.registerAction("exportworkbook", new ExportWorkBookActionUI(uiController));
        actionManager.registerAction("exportspreadsheet", new ExportSelectedSpreadsheetActionUI(uiController));
        actionManager.registerAction("exportselectedrow", new ExportSelectedRowActionUI(uiController));
        actionManager.registerAction("exportselectedcolumn", new ExportSelectedColumnActionUI(uiController));
        actionManager.registerAction("exportselectedcells", new ExportSelectedCellsActionUI(uiController));

        // Registers edit actions
        actionManager.registerAction("undo", new UndoAction());
        actionManager.registerAction("redo", new RedoAction());
        actionManager.registerAction("cut", new CutAction());
        actionManager.registerAction("copy", new CopyAction());
        actionManager.registerAction("paste", new PasteAction());
        actionManager.registerAction("clear", new ClearAction());
        actionManager.registerAction("selectall", new SelectAllAction());
        actionManager.registerAction("search", new SearchAction());
        actionManager.registerAction("prefs", new PreferencesAction());

        // Registers spreadsheet actions
        actionManager.registerAction("addsheet", new AddSpreadsheetAction(uiController));
        actionManager.registerAction("removesheet", new RemoveSpreadsheetAction(uiController));
        actionManager.registerAction("renamesheet", new RenameSpreadsheetAction(uiController));
        actionManager.registerAction("insertcolumn", new InsertColumnAction());
        actionManager.registerAction("removecolumn", new RemoveColumnAction());
        actionManager.registerAction("insertrow", new InsertRowAction());
        actionManager.registerAction("removerow", new RemoveRowAction());

        // Registers help actions
        actionManager.registerAction("help", new HelpAction());
        actionManager.registerAction("license", new LicenseAction());
        actionManager.registerAction("about", new AboutAction());

        // Register extension managing actions
        actionManager.registerAction("manageExtensions", new ManageExtensionsAction(this));

        //Register configurations of exchange rates
        actionManager.registerAction("configurateExchangeRates", new ConfigurateExchangeRatesAction(app, uiController));

        // Creates spreadsheet components
        workbookPane = new WorkbookPane(uiController, actionManager);
        cellEditor = new CellEditor(uiController);
        addressBox = new AddressBox(uiController);
        functionWizard = new IntermediateFunctionWizard(uiController);

        registerListeners();
        recreatePanels();
        pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void recreatePanels() {
        // Creates tool bars
        JPanel toolBarPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        toolBarPanel.add(new StandardToolBar(actionManager));
        for (UIExtension extension : uiController.getExtensions()) {
            JToolBar extToolBar = extension.getToolBar();
            if (extToolBar != null) {
                toolBarPanel.add(extToolBar);
            }
        }

        // Creates and lays out top panel
        JPanel cellFunctionPanel = new JPanel(new BorderLayout());
        cellFunctionPanel.add(functionWizard, BorderLayout.WEST);
        cellFunctionPanel.add(cellEditor, BorderLayout.CENTER);
        JPanel cellPanel = new JPanel(new BorderLayout());
        cellPanel.add(addressBox, BorderLayout.WEST);
        cellPanel.add(cellFunctionPanel, BorderLayout.CENTER);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(toolBarPanel, BorderLayout.NORTH);
        topPanel.add(cellPanel, BorderLayout.SOUTH);

        // Creates and lays out side bar components
        JTabbedPane sideBar = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        sideBar.setPreferredSize(new Dimension(170, 480));
        Font font = sideBar.getFont();
        sideBar.setFont(new Font(font.getFamily(), Font.PLAIN, font.getSize() - 1));
        for (UIExtension extension : uiController.getExtensions()) {
            JComponent extBar = extension.getSideBar();
            if (extBar != null) {
                sideBar.insertTab(extension.getExtension().getName(), extension.getIcon(),
                        extBar, null, sideBar.getTabCount());
            }
        }

        // Lays out split pane
        workbookPane.setMinimumSize(new Dimension(300, 100));
        sideBar.setMinimumSize(new Dimension(140, 100));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                workbookPane, sideBar);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(1.0);

        // Configures layout and adds panels
        Container pane = getContentPane();
        pane.removeAll();
        pane.setPreferredSize(new Dimension(640, 480));
        pane.setLayout(new BorderLayout());
        pane.add(topPanel, BorderLayout.NORTH);
        pane.add(splitPane, BorderLayout.CENTER);
        setJMenuBar(new MenuBar(app, actionManager, uiController));

        // Configures appearance
        setTitle(TITLE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                CleanSheets.class.getResource("res/img/sheet.gif")));
    }

    private void registerListeners() {
        // Registers listeners
        uiController.addSelectionListener(this);
        uiController.addExtensionListener(this);
        addWindowListener(new WindowClosingHandler(this,
                actionManager.getAction("exit")));
    }

    /**
     * Updates the title of the window when a new active workbook is selected.
     *
     * @param event the selection event that was fired
     */
    public void selectionChanged(SelectionEvent event) {
        Workbook workbook = event.getWorkbook();
        if (workbook != null) {
            setVisible(true);
            if (app.isWorkbookStored(workbook)) {
                setTitle(TITLE + " - " + app.getFile(workbook).getName());
            } else {
                setTitle(TITLE + " - Untitled");
            }
        } else {
            setTitle(TITLE);
        }
    }

    @Override
    public void extensionStateChanged(ExtensionEvent event) {
        // Recreates tool bars
        JPanel toolBarPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        toolBarPanel.add(new StandardToolBar(actionManager));
        for (UIExtension extension : uiController.getExtensions()) {
            JToolBar extToolBar = extension.getToolBar();
            if (extToolBar != null) {
                toolBarPanel.add(extToolBar);
            }
        }
        this.recreatePanels();
        this.repaint();
    }

    /**
     * A utility for creating a Frame on the AWT event dispatching thread.
     *
     * @author Einar Pehrson
     */
    public static class Creator implements Runnable {

        /**
         * The component that was created
         */
        private Frame frame;

        /**
         * The CleanSheets application
         */
        private CleanSheets app;

        /**
         * Creates a new frame creator.
         *
         * @param app the CleanSheets application
         */
        public Creator(CleanSheets app) {
            this.app = app;
        }

        /**
         * Creates a component on the AWT event dispatching thread.
         *
         * @return the component that was created
         */
        public Frame createAndWait() {
            try {
                EventQueue.invokeAndWait(this);
            } catch (InterruptedException e) {
                return null;
            } catch (java.lang.reflect.InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
            return frame;
        }

        /**
         * Asks the event queue to create a component at a later time. (Included
         * for conformity.)
         */
        public void createLater() {
            EventQueue.invokeLater(this);
        }

        public void run() {
            frame = new Frame(app);
        }
    }
}
