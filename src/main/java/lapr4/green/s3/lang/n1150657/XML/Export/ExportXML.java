/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import eapli.util.Strings;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import lapr4.s1.export.ExportStrategy;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroList;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroWithName;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Eric with improvements of Sofia Gonçalves (1150657)
 */
public class ExportXML implements ExportStrategy {

    private static final String TITLE_ELEMENT = "title";
    private static final String TITLE_ROW = "row";
    private static final String TITLE_COLUMN = "column";
    private static final String TITLE_USER = "user";

    /**
     * The path for the save export.
     */
    private String path;

    /**
     * The map that will contain the oficial string of the tag name, and the
     * string that the user (if its the case) selected.
     */
    private Map<String, String> tagNamesList;

    /**
     * The map that contains the spreadsheet and a list of active cells.
     */
    private Map<Spreadsheet, List<Cell>> cellMap;

    private MacroList macroList;
    private VarContentor globalVariables;

    /**
     * The constructor for the exportXML. It will initialize the variables.
     *
     * @param path
     * @param tagNamesMap
     * @param macroList
     * @param globalVariables
     */
    public ExportXML(String path, Map<String, String> tagNamesMap, MacroList macroList, VarContentor globalVariables) {
        this.path = path;
        this.tagNamesList = tagNamesMap;
        this.macroList = macroList;
        this.globalVariables = globalVariables;
    }

    /**
     * Defines the selected cells.
     *
     * @param cellMap the cell map.
     */
    public void selectRange(Map<Spreadsheet, List<Cell>> cellMap) {
        this.cellMap = cellMap;
    }

    @Override
    public boolean export() {
        boolean exported = false;
        Element commentElement = null, contentElement = null;

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root element
            Document doc = docBuilder.newDocument();
            Element rootElement = createElementsByTreeORder(doc, TreeOrder.ROOT.nameRoot());
            doc.appendChild(rootElement);

            for (Spreadsheet spreadsheet : cellMap.keySet()) {

                //worksheet elements
                Element spreadsheetElement = createElementsByTreeORder(doc, TreeOrder.ELEMENT_ROOT1.nameRoot());
                rootElement.appendChild(spreadsheetElement);

                // set attribute(title) to spreadsheet element
                Attr attr = doc.createAttribute(TITLE_ELEMENT);
                attr.setValue(spreadsheet.getTitle());
                spreadsheetElement.setAttributeNode(attr);

                for (Cell currentCell : cellMap.get(spreadsheet)) {
                    boolean hasComment = false;
                    boolean hasBackground = false;
                    boolean hasBorder = false;
                    ActiveCell activeCell = new ActiveCell(currentCell);
                    //cell elements
                    Element cellElement = createElementsByTreeORder(doc, TreeOrder.SUB_ELEMENT_ROOT1.nameRoot());

                    // set attribute(row) to cell element
                    Attr row_attr = doc.createAttribute(TITLE_ROW);
                    row_attr.setValue(String.valueOf(currentCell.getAddress().translateRowByIndex()));
                    cellElement.setAttributeNode(row_attr);

                    // set attribute(column) to cell element
                    Attr column_attr = doc.createAttribute(TITLE_COLUMN);
                    column_attr.setValue(currentCell.getAddress().translateColumnByIndex());
                    cellElement.setAttributeNode(column_attr);

                    //CONTENT
                    if (activeCell.hasContent()) {
                        //Content
                        contentElement = createElementsByTreeORder(doc, TreeOrder.SUB_ELEMENT_CONTENT.nameRoot());
                        contentElement.appendChild(doc.createTextNode((currentCell.getContent())));
                        cellElement.appendChild(contentElement);
                    }
                    //ALLIGMENT?

                    //STYLE
                    StylableCell stylableCell = (StylableCell) currentCell.getExtension(StyleExtension.NAME);
                    Color cellColor = stylableCell.getBackgroundColor();
                    Border cellBorder = stylableCell.getBorder();
                    Font celFont = stylableCell.getFont();
                    Color cellForegroundColor = stylableCell.getForegroundColor();
                    Format cellFormat = stylableCell.getFormat();

                    if (!cellColor.equals(StylableCell.BACKGROUND)) {
                        Element colorElement = doc.createElement("color");

                        Attr alphaAttr = doc.createAttribute("alpha");
                        alphaAttr.setValue(Integer.toString(cellColor.getAlpha()));
                        colorElement.setAttributeNode(alphaAttr);

                        Attr blueAttr = doc.createAttribute("blue");
                        blueAttr.setValue(Integer.toString(cellColor.getBlue()));
                        colorElement.setAttributeNode(blueAttr);

                        Attr greenAttr = doc.createAttribute("green");
                        greenAttr.setValue(Integer.toString(cellColor.getGreen()));
                        colorElement.setAttributeNode(greenAttr);

                        Attr redAttr = doc.createAttribute("red");
                        redAttr.setValue(Integer.toString(cellColor.getRed()));
                        colorElement.setAttributeNode(redAttr);

                        cellElement.appendChild(colorElement);
                        hasBackground = true;
                    }

                    //if doesnt have an empty border, it adds
                    //if (!cellBorder.getClass().equals(EmptyBorder.class)) {
                    if (cellBorder instanceof MatteBorder) {
                        Element borderElement = doc.createElement("border");
                        MatteBorder matte = (MatteBorder) cellBorder;
                        Insets borderInsets = matte.getBorderInsets();
                        Color borderColor = matte.getMatteColor();

                        Element insetsElement = doc.createElement("elements");
                        Attr topAttr = doc.createAttribute("top");
                        topAttr.setValue(Integer.toString(borderInsets.top));
                        Attr bottomAtr = doc.createAttribute("bottom");
                        bottomAtr.setValue(Integer.toString(borderInsets.bottom));
                        Attr leftAttr = doc.createAttribute("left");
                        leftAttr.setValue(Integer.toString(borderInsets.left));
                        Attr rightAttr = doc.createAttribute("right");
                        rightAttr.setValue(Integer.toString(borderInsets.right));

                        insetsElement.setAttributeNode(topAttr);
                        insetsElement.setAttributeNode(leftAttr);
                        insetsElement.setAttributeNode(bottomAtr);
                        insetsElement.setAttributeNode(rightAttr);

                        Element colorElement = doc.createElement("color");

                        Attr alphaAttr = doc.createAttribute("alpha");
                        alphaAttr.setValue(Integer.toString(borderColor.getAlpha()));
                        colorElement.setAttributeNode(alphaAttr);

                        Attr blueAttr = doc.createAttribute("blue");
                        blueAttr.setValue(Integer.toString(borderColor.getBlue()));
                        colorElement.setAttributeNode(blueAttr);

                        Attr greenAttr = doc.createAttribute("green");
                        greenAttr.setValue(Integer.toString(borderColor.getGreen()));
                        colorElement.setAttributeNode(greenAttr);

                        Attr redAttr = doc.createAttribute("red");
                        redAttr.setValue(Integer.toString(borderColor.getRed()));
                        colorElement.setAttributeNode(redAttr);

                        borderElement.appendChild(insetsElement);
                        borderElement.appendChild(colorElement);
                        cellElement.appendChild(borderElement);

                        hasBorder = true;
                    }

                    //}
                    if (activeCell.hasContent()) {
                        Element fontElement = doc.createElement("font");
                        //fontElement.appendChild(doc.createTextNode(celFont.toString()));
                        Attr nameFontAttr = doc.createAttribute("name");
                        nameFontAttr.setValue(celFont.getName());

                        Attr styleFontAttr = doc.createAttribute("style");
                        //PLAIN, BOLD, ITALIC, or BOLD+ITALIC.

                        String styleString = "plain";
                        int style = celFont.getStyle();
                        //FIX ME, out in another place
                        switch (style) {
                            case 0:
                                styleString = "plain";
                                break;
                            case 1:
                                styleString = "bold";
                                break;
                            case 2:
                                styleString = "italic";
                                break;
                            case 3:
                                styleString = "bold+italic";
                                break;
                            default:
                                break;
                        }

                        styleFontAttr.setValue(styleString);

                        Attr sizeFontAttr = doc.createAttribute("size");
                        sizeFontAttr.setValue(String.valueOf(celFont.getSize()));

                        fontElement.setAttributeNode(nameFontAttr);
                        fontElement.setAttributeNode(styleFontAttr);
                        fontElement.setAttributeNode(sizeFontAttr);

                        cellElement.appendChild(fontElement);

                        Element foregroundElement = doc.createElement("foreground");

                        Attr alphaAttr = doc.createAttribute("alpha");
                        alphaAttr.setValue(Integer.toString(cellForegroundColor.getAlpha()));
                        foregroundElement.setAttributeNode(alphaAttr);

                        Attr blueAttr = doc.createAttribute("blue");
                        blueAttr.setValue(Integer.toString(cellForegroundColor.getBlue()));
                        foregroundElement.setAttributeNode(blueAttr);

                        Attr greenAttr = doc.createAttribute("green");
                        greenAttr.setValue(Integer.toString(cellForegroundColor.getGreen()));
                        foregroundElement.setAttributeNode(greenAttr);

                        Attr redAttr = doc.createAttribute("red");
                        redAttr.setValue(Integer.toString(cellForegroundColor.getRed()));
                        foregroundElement.setAttributeNode(redAttr);

                        cellElement.appendChild(foregroundElement);
                    }

                    if (cellFormat != null) {
                        Element formatElement = doc.createElement("format");
                        if (cellFormat instanceof SimpleDateFormat) {
                            SimpleDateFormat date = (SimpleDateFormat) cellFormat;
                            formatElement.appendChild(doc.createTextNode(date.toPattern()));
                            cellElement.appendChild(formatElement);
                        } else if (cellFormat instanceof NumberFormat) {
                            //fix me
                        }
                    }

                    //Comment
                    try {
                        CommentableCellWithMultipleUsers cellComment = (CommentableCellWithMultipleUsers) currentCell.getExtension(CommentsExtension.NAME);
                        commentElement = doc.createElement(TreeOrder.SUB_ELEMENT_COMMENT.nameRoot().getElementName());
                        for (Map.Entry<User, List<String>> l : cellComment.comments().entrySet()) {
                            Attr userAttr = doc.createAttribute(TITLE_USER);
                            userAttr.setValue(l.getKey().name()); //user name
                            commentElement.setAttributeNode(userAttr);

                            //comment per se
                            for (String s : l.getValue()) {
                                Element commentText = doc.createElement("content");
                                commentText.appendChild(doc.createTextNode((s)));
                                commentElement.appendChild(commentText);
                            }

                        }
                        if (!cellComment.comments().isEmpty()) {
                            hasComment = true;
                            cellElement.appendChild(commentElement);
                        }
                    } catch (NullPointerException e) {
                        //do nothing, there's no comment in cell
                    }

                    if (hasComment || activeCell.hasContent() || hasBackground || hasBorder) {
                        spreadsheetElement.appendChild(cellElement);
                    }

                }

                //Global variable
                for (Variable var : globalVariables.variables()) {
                    Element variableElement = doc.createElement("global_variable");
                    Attr varAttr = doc.createAttribute("name");
                    varAttr.setValue(var.getName()); //user name
                    variableElement.setAttributeNode(varAttr);
                }
                
                //Macro
                for (MacroWithName macro : macroList.getMacroList()) {
                    Element macroElement = doc.createElement("macro");
                    Attr macroAttr = doc.createAttribute("name");
                    macroAttr.setValue(macro.getName()); //user name
                    macroElement.setAttributeNode(macroAttr);
                    
                    Element code = doc.createElement("code");
                    code.appendChild(doc.createTextNode(macro.getMacroCode()));
                    macroElement.appendChild(code);
                    
                    spreadsheetElement.appendChild(macroElement);
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

    /**
     * It gets the element. If in the map the name is empty, the default is
     * chosen
     *
     * @param doc
     * @return
     */
    private Element createElementsByTreeORder(Document doc, ElementCleansheet element) {
        String name = tagNamesList.get(element.getElementName());
        if (!Strings.isNullOrWhiteSpace(name)) {
            return doc.createElement(name);
        }
        return doc.createElement(element.getElementName());
    }

}
