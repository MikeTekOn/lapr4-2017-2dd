/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula.configurations;

import csheets.CleanSheets;
import csheets.ui.FileChooser;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
@SuppressWarnings("serial")
public class ConfigureExchangeRatesAction extends FocusOwnerAction{

     /**
     * The CleanSheets application
     */
    protected CleanSheets app;

    /**
     * The user interface controller
     */
    protected UIController uiController;

    public ConfigureExchangeRatesAction(CleanSheets app, UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Configurate Exchange Rates";
    }

    protected void defineProperties(){
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource("res/img/coin.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (focusOwner == null) {
            return;
        }
        new ConfigureExchangeRatesUI(uiController);
    }



}
