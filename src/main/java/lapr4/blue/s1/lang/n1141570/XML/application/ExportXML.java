package lapr4.blue.s1.lang.n1141570.XML.application;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import lapr4.s1.export.ExportStrategy;
import org.w3c.dom.Attr;

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
    private static final String TAG_WORKBOOK_NAME = "workbook";
    private static final String TAG_SPREADSHEET_NAME = "spreadsheet";
    private static final String TAG_CELL_NAME = "cell";

    private String path;
    private List<Cell> cellsList;
    private List<String> tagNamesList;
    private Map<Spreadsheet, List<Cell>> cellMap;

    public ExportXML() {
    }

    /**
     * Defines the selected range of cells.
     *
     * @param cellsList the selected list of cells.
     */
    public void selectRange(Map<Spreadsheet, List<Cell>> cellMap) {
        this.cellMap = cellMap;
    }

    /**
     * Configures tag names by attributing the received list of names to the tag
     * names.
     *
     * @param tagNamesList the received list of tag names.
     */
    public void configureTagNames(List<String> tagNamesList) {
        if (!tagNamesList.isEmpty()) {
            this.tagNamesList = tagNamesList;
        } else {
            this.tagNamesList.add(ROOT_VALUE, TAG_WORKBOOK_NAME);
            this.tagNamesList.add(ELEMENT1_VALUE, TAG_SPREADSHEET_NAME);
            this.tagNamesList.add(ELEMENT2_VALUE, TAG_CELL_NAME);
        }
    }

    /**
     * Receives the selected path where to save the xml file.
     *
     * @param path the chosen path.
     */
    public void selectPath(String path) {
        this.path = path;
    }

    /**
     * Exports the data to xml file.
     *
     * @return returns true if exports, false otherwise.
     */
    @Override
    public boolean export() {
        boolean exported = false;

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            docBuilder = docFactory.newDocumentBuilder();

            //root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(tagNamesList.get(ROOT_VALUE)); //basically to give the proper name do the root tag
            doc.appendChild(rootElement);

            for (Spreadsheet spreadsheet : cellMap.keySet()) {
                //worksheet elements
                Element spreadsheetElement = doc.createElement(tagNamesList.get(ELEMENT1_VALUE));
                rootElement.appendChild(spreadsheetElement);

                // set attribute to spreadsheet element
                Attr attr = doc.createAttribute("title");
                attr.setValue(spreadsheet.getTitle());
                spreadsheetElement.setAttributeNode(attr);

                for (Cell currentCell : cellMap.get(spreadsheet)) {
                    //cell elements
                    Element cellElement = doc.createElement(tagNamesList.get(ELEMENT2_VALUE));
                    if (!currentCell.getValue().toString().equalsIgnoreCase("")) {
                        cellElement.appendChild(doc.createTextNode((currentCell.getValue()).toString()));
                        spreadsheetElement.appendChild(cellElement);
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", "3");

            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(this.path));
            transformer.transform(source, result);

            exported = true;

        } catch (TransformerException | ParserConfigurationException ex) {
            Logger.getLogger(ExportXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exported;
    }

}
