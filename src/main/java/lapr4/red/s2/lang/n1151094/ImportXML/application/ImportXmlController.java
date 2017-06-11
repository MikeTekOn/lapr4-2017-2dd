/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1151094.ImportXML.application;

import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import java.io.File;
import lapr4.red.s2.lang.n1151094.ImportXML.ImportContext;
import lapr4.red.s2.lang.n1151094.ImportXML.ImportXml;

/**
 *
 * @author Eduangelo Ferreira - 1151094
 */
public class ImportXmlController implements Controller {

    private final ImportXml xml;
    private final ImportContext importContext;

    /**
     * 
     * @param uiController uiController
     */
    public ImportXmlController(UIController uiController) {

        this.xml = new ImportXml(uiController);
        this.importContext = new ImportContext(xml);

    }

    /**
     * This method is to select the file path. That will then be used to read
     * the file.
     *
     * @param path path
     */
    public void selectPath(String path) {
        this.xml.selectPath(path);
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

    /**
     * This feature allows you to validate the file extension
     *
     * @param file file
     * @return true or false
     */
    public boolean validateExtension(File file) {
        return this.xml.validateExtension(file);
    }

}
