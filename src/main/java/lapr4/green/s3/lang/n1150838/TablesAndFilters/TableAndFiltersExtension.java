/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters;

import csheets.core.Cell;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.presentation.TableAndFiltersUIExtension;

/**
 *
 * @author nunopinto
 */
public class TableAndFiltersExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Tables and Filters";
    
     /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "An extension to support talbes and filters.\n"
            + "An extension must extend the Extension abstract class.\n"
            + "The class that implements the Extension is the \"bootstrap\" of the extension.";
    
    /**
     * The first version of the conditional style extension.
     */
    public static final int VERSION = 1;
    public UIController uiController;

    /**
     * Creates a new Example extension.
     * @param uiController
     */
    public TableAndFiltersExtension(UIController uiController){
        super(NAME, VERSION, DESCRIPTION);
        this.uiController = uiController;
    }
    
    //FIXME, it exists for the loading. It neads a constructor without parameters
    public TableAndFiltersExtension(){
        super(NAME, VERSION, DESCRIPTION);
    }
    
    /**
     * Returns the user interface extension of this extension
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        return new TableAndFiltersUIExtension(this,uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Tiago Correia in Sprint 1 and it is in the package %s",
                getName(), getVersion(), getDescription(),getClass().getName());
    }
}
