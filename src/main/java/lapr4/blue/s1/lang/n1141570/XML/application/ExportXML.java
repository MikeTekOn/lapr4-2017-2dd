package lapr4.blue.s1.lang.n1141570.XML.application;

import csheets.core.Cell;
import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import lapr4.s1.export.ExportStrategy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Eric
 */
public class ExportXML implements ExportStrategy {

    private static final int ROOT_VALUE = 0;
    private static final int ELEMENT1_VALUE = 1;
    private static final int ELEMENT2_VALUE = 2;
    
    private String path;
    private List<Cell> cellsList;
    private List<String> tagNamesList;

    String tagWorkbookName = "workbook";
    String tagSpreadsheetName = "spreadsheet";
    String tagCellName = "cell";

    public ExportXML() {
    }

    /**
     * Defines the selected range of cells.
     * @param cellsList the selected list of cells.
     */
    public void selectRange(List<Cell> cellsList) {
        this.cellsList = cellsList;
    }

    /**
     * Configures tag names by attributing the received list of names to the tag names.
     * @param tagNamesList the received list of tag names.
     */
    public void configureTagNames(List<String> tagNamesList) {
        if (tagNamesList.isEmpty()) {
            this.tagNamesList.add(ROOT_VALUE, this.tagWorkbookName);
            this.tagNamesList.add(ELEMENT1_VALUE, this.tagSpreadsheetName);
            this.tagNamesList.add(ELEMENT2_VALUE, this.tagCellName);
        }
        this.tagNamesList = tagNamesList;
    }

    /**
     * Receives the selected path where to save the xml file.
     * @param path the chosen path.
     */
    public void selectPath(String path) {
        this.path = path;
    }

    /**
     * Exports the data to xml file.
     * @return returns true if exports, false otherwise.
     */
    @Override
    public boolean export() {
        boolean success = true;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;

            docBuilder = docFactory.newDocumentBuilder();

            //root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(tagNamesList.get(ROOT_VALUE)); //basically to give the proper name do the root tag
            doc.appendChild(rootElement);

            //worksheet element
            Element spreadsheetElement = doc.createElement(tagNamesList.get(ELEMENT1_VALUE));
            rootElement.appendChild(spreadsheetElement);

            //cell element
            Element cellElement = doc.createElement(tagNamesList.get(ELEMENT2_VALUE));
            spreadsheetElement.appendChild(cellElement);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(this.path));

            transformer.transform(source, result);
        } catch (ParserConfigurationException pCexp) {
            pCexp.printStackTrace();
            success = false;
        } catch (TransformerException tfExp) {
            tfExp.printStackTrace();
            success = false;
        }

        return success;
    }

}
