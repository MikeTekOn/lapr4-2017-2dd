/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Import;

import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import lapr4.red.s2.lang.n1151094.ImportXML.ImportContext;

/**
 *
 * @author Sofia
 */
public class ImportXMLController implements Controller {

    private final ImportXML xml;
    private final ImportContext importContext;
    /**
     * 
     * @param uiController uiController
     */
    public ImportXMLController(UIController uiController,String selectedPath) {
        this.xml = new ImportXML(uiController,selectedPath);
        this.importContext = new ImportContext(xml);

    }

    /**
     * This method executes the strategy which in turn executes the method
     * importData
     *
     * @return true or false
     */
    public boolean importXml() {

        return this.importContext.executeStrategy();
    }

}
