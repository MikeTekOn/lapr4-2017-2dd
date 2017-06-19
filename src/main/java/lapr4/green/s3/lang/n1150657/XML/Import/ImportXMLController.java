/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Import;

import csheets.core.Cell;
import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import lapr4.green.s3.lang.n1150657.XML.Export.ExportComponent;
import lapr4.red.s2.lang.n1151094.ImportXML.ImportContext;
import org.xml.sax.SAXException;

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
     * @param selectedPath
     * @param isReplace
     */
    public ImportXMLController(UIController uiController,String selectedPath, boolean isReplace) {
        this.xml = new ImportXML(uiController,selectedPath,isReplace);
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
    
    public boolean importSelectedXml(Cell selectedCells[][]){
        this.xml.selectRange(selectedCells);
        return this.xml.importData();
    }
    
    public int countNumberSpreadSheet(String path){
        try {
            return this.xml.countNumberSpreadSheet(path);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new IllegalArgumentException();
        } 
    }
    
    public void setIsReplace(boolean isReplace){
        this.xml.setIsReplace(isReplace);
    }

}
