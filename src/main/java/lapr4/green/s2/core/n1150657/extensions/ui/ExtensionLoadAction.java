/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150657.extensions.ui;

import csheets.CleanSheets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JFrame;

/**
 * It represents the extension option action that is an Observable. It will
 * notify the CleanSheet that the load is complete.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ExtensionLoadAction extends Observable implements ActionListener {

    /**
     * The parent JFrame.
     */
    private JFrame parent;

    /**
     * The CleanSheets aplication.
     */
    private CleanSheets app;

    /**
     * The constructor for the Extension Option action. It will add the observer
     * (CleanSheet)
     *
     * @param frame The JFrame.
     * @param app The CleanSheets.
     */
    public ExtensionLoadAction(JFrame frame, CleanSheets app) {
        this.parent = frame;
        addObserver(app);
    }

    /**
     * It will notify the CleanSheet Aplication that the loading is complete and
     * dispose the parent frame.
     *
     * @param e The Action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        setChanged();
        notifyObservers(app);
        parent.dispose();
    }
}
