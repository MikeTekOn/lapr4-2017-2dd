package lapr4.blue.s2.ipc.n1151452.netanalyzer.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.NetAnalyzerExtension;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficCounter;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficLogger;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * This class implements the UI interface extension for the network analyzer extension.
 * A UI interface extension must extend the UIExtension abstract class.
 *
 * @author Einar Pehrson
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 * @see csheets.ui.ext.UIExtension
 */
public class UIExtensionNetAnalyzer extends UIExtension {

    /**
     * The icon to display with the extension's name
     */
    private Icon icon;

    /**
     * A panel in which a precedents tree and a dependents tree is displayed
     */
    private JComponent sideBar;

    /**
     * Creates a new user interface extension
     *
     * @param extension    the extension for which components are provided
     * @param uiController the user interface controller
     */
    public UIExtensionNetAnalyzer(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns an icon to display with the extension's name
     *
     * @return an icon with a graph
     */
    public Icon getIcon() {
        if (icon == null)
            icon = new ImageIcon(
                    UIExtensionNetAnalyzer.class.getResource("graph.png"));
        return icon;
    }

    /**
     * Returns a panel in which a log table and a network analyzer graph
     *
     * @return a panel with two dependency trees
     */
    public JComponent getSideBar() {
        if (sideBar == null) {
            sideBar = new JPanel(new GridLayout(2, 1));
            sideBar.setName(NetAnalyzerExtension.NAME);

            // Set watchdogs
            TrafficCounter.getInstance().turnOn();
            TrafficLogger.getInstance().turnOn();

            // Creates components
            // Panels
            final JFXPanel fxPanel = new JFXPanel();
            final TrafficLogJTable logTable = new TrafficLogJTable();
            TrafficLogger.getInstance().addTrafficLogListener(logTable);

            // Adds borders
            TitledBorder border = BorderFactory.createTitledBorder("Traffic Log");
            border.setTitleJustification(TitledBorder.CENTER);
            logTable.setBorder(border);
            border = BorderFactory.createTitledBorder("Traffic Graph");
            border.setTitleJustification(TitledBorder.CENTER);
            fxPanel.setBorder(border);

            // Creates side bar
            sideBar.add(logTable);
            sideBar.add(fxPanel);

            // Instantiate javafx scene
            Platform.runLater(() -> initFX(fxPanel));

            //********************//
            //logTable.testModeON();
            //********************//
        }
        return sideBar;
    }

    /**
     * JavaFx adapter to instantiate the javafx chart
     *
     * @param fxPanel the JFXPanel where to load
     */
    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        NetAnalyzerGraph graph = new NetAnalyzerGraph();

        //********************//
        //graph.testModeON();
        //********************//

        // Subscribes the graph to listen to events
        TrafficCounter.getInstance().addTrafficCounterListener(graph);
        // Set scene
        Scene scene = new Scene(graph);
        fxPanel.setScene(scene);
    }
}
