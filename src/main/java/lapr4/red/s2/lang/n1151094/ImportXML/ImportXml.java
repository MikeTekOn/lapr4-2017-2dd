/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1151094.ImportXML;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * This class is responsible for importing an xml file
 *
 * @author Eduangelo Ferreira - 1151094
 */
public class ImportXml implements ImportStrategy {

    //Variable path responsible for saving the path of the user-specified file
    private String path;
    //Default column name
    private static final String DEFAULT_COLUMN = "column";
    //Default row name
    private static final String DEFAULT_ROW = "row";
    //Default title of spreadsheet
    private static final String DEFAULT_TITLE = "title";
    //Controller responsible for controlling Frame actions
    private final UIController uiController;

    /**
     * This constructor receives as parameter the UIController. It is
     * responsible for instantiating the Object.
     *
     * @param uiController of ImportXml
     */
    public ImportXml(UIController uiController) {
        this.uiController = uiController;

    }

    /**
     * This method is to select the file path. That will then be used to read
     * the file.
     *
     * @param path path
     */
    public void selectPath(String path) {
        this.path = path;
    }

    /**
     * This method is responsible for importing the xml file
     *
     * @return true
     */
    @Override
    public boolean importData() {

        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = dBuilder.parse(new File(path));
            Element elementWorkbook = doc.getDocumentElement();
            NodeList listSpreadSheet = elementWorkbook.getChildNodes();
            Node an;

            int contador = countSpreadSheet(listSpreadSheet), count = 0;
            Workbook work = new Workbook(contador, null);

            for (int i = 0; i < listSpreadSheet.getLength(); i++) {
                an = listSpreadSheet.item(i);
                count = alterWorkbook(an, work, count);
            }
            uiController.setActiveWorkbook(work);

        } catch (ParserConfigurationException | SAXException | IOException | FormulaCompilationException ex) {
            ex.getMessage();
            return false;
        }

        return true;

    }

    /**
     * This method is responsible for the file spreadsheet
     *
     * @param listSpreadSheet listSpreadsheet
     * @return count
     */
    private int countSpreadSheet(NodeList listSpreadSheet) {
        int count = 0;
        Node an;
        for (int i = 0; i < listSpreadSheet.getLength(); i++) {
            an = listSpreadSheet.item(i);
            if (an.getNodeType() == Node.ELEMENT_NODE) {
                count++;
            }
        }

        return count;
    }

    /**
     * This method allows you to change the spreadsheet values ​​of a workbook
     *
     * @param spreadsheet spreadsheet
     * @param work workbook
     * @param count count
     * @return count
     * @throws FormulaCompilationException
     */
    private int alterWorkbook(Node spreadsheet, Workbook work, int count) throws FormulaCompilationException {

        Node an2;

        if (spreadsheet.getNodeType() == Node.ELEMENT_NODE) {
            NodeList listCellOfSpreadSheet = spreadsheet.getChildNodes();
            NamedNodeMap attr = spreadsheet.getAttributes();

            for (int j = 0; j < listCellOfSpreadSheet.getLength(); j++) {
                an2 = listCellOfSpreadSheet.item(j);
                if (an2.getNodeType() == Node.ELEMENT_NODE) {
                    NamedNodeMap attributes = an2.getAttributes();
                    String column = attributes.getNamedItem(DEFAULT_COLUMN).getTextContent();
                    String row = attributes.getNamedItem(DEFAULT_ROW).getTextContent();
                    Address addr = new Address(Integer.parseInt(column), Integer.parseInt(row));
                    String content = an2.getTextContent();
                    CellDTO cellDto = new CellDTO(addr, content);
                    String title = attr.getNamedItem(DEFAULT_TITLE).getTextContent();
                    work.getSpreadsheet(count).setTitle(title);
                    Cell cell = work.getSpreadsheet(count).getCell(cellDto.getAddress());
                    cell.setContent(cellDto.getContent());
                }
            }
            count++;

        }
        return count;
    }

    /**
     * This feature allows you to validate the file extension
     *
     * @param file file
     * @return true or false
     */
    public boolean validateExtension(File file) {
        return file.getName().endsWith(".xml");
    }
}
