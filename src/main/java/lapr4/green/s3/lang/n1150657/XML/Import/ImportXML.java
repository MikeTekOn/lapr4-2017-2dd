/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Import;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.border.MatteBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroWithName;
import lapr4.red.s2.lang.n1151094.ImportXML.ImportStrategy;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

/**
 *
 * @author Eduangelo Ferreira - 1151094 and Sofia
 */
public class ImportXML implements ImportStrategy {

    /**
     * Variable path responsible for saving the path of the user-specified file
     */
    private String path;

    /**
     * Default column name
     */
    private static final String DEFAULT_COLUMN = "column";

    /**
     * Default row name
     */
    private static final String DEFAULT_ROW = "row";

    /**
     * Default title of spreadsheet
     */
    private static final String DEFAULT_TITLE = "title"; //associar nomes a um sitio global

    /**
     * Controller responsible for controlling Frame actions
     */
    private final UIController uiController;

    /**
     * This constructor receives as parameter the UIController. It is
     * responsible for instantiating the Object.
     *
     * @param uiController of ImportXml
     * @param selectedPath
     */
    public ImportXML(UIController uiController, String selectedPath) {
        this.uiController = uiController;
        this.path = selectedPath;
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
                if (an.getNodeName().equals("spreadsheet")) {
                    count = alterWorkbook(an, work, count);
                }

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
            //System.out.println("aqui");
            NodeList listCellOfSpreadSheet = spreadsheet.getChildNodes();
            NamedNodeMap attr = spreadsheet.getAttributes();

            String title = attr.getNamedItem("title").getTextContent();
            work.getSpreadsheet(count).setTitle(title);

            for (int j = 0; j < listCellOfSpreadSheet.getLength(); j++) {
                an2 = listCellOfSpreadSheet.item(j);
                if (an2.getNodeType() == Node.ELEMENT_NODE && an2.getNodeName().equals("cell")) {
                    NamedNodeMap attributes = an2.getAttributes();
                    //cell
                    String column = attributes.getNamedItem(DEFAULT_COLUMN).getTextContent();
                    String row = attributes.getNamedItem(DEFAULT_ROW).getTextContent();
                    //cell address
                    Address addr = new Address(column, row);
                    NodeList cellChilds = an2.getChildNodes();
                    for (int i = 0; i < cellChilds.getLength(); i++) {
                        Node child = cellChilds.item(i);
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            Cell cell = work.getSpreadsheet(count).getCell(addr);
                            //content
                            if (child.getNodeName().equals("content")) {
                                String content = child.getTextContent();
                                cell.setContent(content);
                            }

                            StylableCell stylableCell = (StylableCell) cell.getExtension(StyleExtension.NAME);
                            //color
                            if (child.getNodeName().equals("color")) {
                                Color newColor = getColor(child);
                                stylableCell.setBackgroundColor(newColor);
                            }
                            //border
                            if (child.getNodeName().equals("border")) {
                                NodeList borderChilds = child.getChildNodes();
                                int top = 0, bottom = 0, left = 0, right = 0;
                                Color c = null;
                                for (int b = 0; b < borderChilds.getLength(); b++) {
                                    Node borderChild = borderChilds.item(b);
                                    switch (borderChild.getNodeName()) {
                                        case "elements":
                                            NamedNodeMap insetsAttr = borderChild.getAttributes();
                                            top = Integer.parseInt(insetsAttr.getNamedItem("top").getTextContent());
                                            bottom = Integer.parseInt(insetsAttr.getNamedItem("bottom").getTextContent());
                                            left = Integer.parseInt(insetsAttr.getNamedItem("left").getTextContent());
                                            right = Integer.parseInt(insetsAttr.getNamedItem("right").getTextContent());
                                            break;
                                        case "color":
                                            c = getColor(borderChild);
                                            break;
                                        default:
                                            break;
                                    }
                                }

                                //int top, int left, int bottom, int right
                                MatteBorder matteBorder = new MatteBorder(top, left, bottom, right, c);
                                stylableCell.setBorder(matteBorder);
                            }

                            //font
                            if (child.getNodeName().equals("font")) {
                                //default
                                int style = 0;

                                NamedNodeMap styleAtr = child.getAttributes();
                                String styleStrin = styleAtr.getNamedItem("style").getTextContent();
                                switch (styleStrin) {
                                    case "plain":
                                        style = Font.PLAIN;
                                        break;
                                    case "bold":
                                        style = Font.BOLD;
                                        break;
                                    case "italic":
                                        style = Font.ITALIC;
                                        break;
                                    case "bold+italic":
                                        style = Font.ITALIC + Font.BOLD;
                                        break;
                                    default:
                                        break;
                                }
                                String name = styleAtr.getNamedItem("name").getTextContent();
                                int size = Integer.parseInt(styleAtr.getNamedItem("size").getTextContent());

                                Font font = new Font(name, style, size);
                                stylableCell.setFont(font);
                            }

                            //foreground
                            if (child.getNodeName().equals("foreground")) {
                                Color newColor = getColor(child);
                                stylableCell.setForegroundColor(newColor);
                            }

                            //format
                            if (child.getNodeName().equals("format")) {
                                String format = child.getTextContent();
                                SimpleDateFormat date = new SimpleDateFormat(format);
                                stylableCell.setFormat(date);
                            }

                            //comment
                            if (child.getNodeName().equals("comment")) {
//                                NamedNodeMap attrChild = child.getAttributes();
//                                String user = attrChild.getNamedItem("user").getTextContent();
//                                User u = new User(user);
//                                NodeList commentChild = child.getChildNodes();

//                                for (int k = 0; k < commentChild.getLength(); k++) {
//                                    Node childComment = commentChild.item(k);
//                                    if (childComment.getNodeName().equals("content")) {
//                                        //content
//                                        String userContent = childComment.getTextContent();
//                                        CommentsWithHistoryController c = new CommentsWithHistoryController(uiController);
//                                        CommentableCellWithMultipleUsers comment = new CommentableCellWithMultipleUsers(cell);
//
//                                        CommentsWithHistoryUIExtension ce = null;
//                                        for (int x = 0; x < uiController.getExtensions().length; x++) {
//                                            if (uiController.getExtensions()[x] instanceof CommentsWithHistoryUIExtension) {
//                                                ce = (CommentsWithHistoryUIExtension) uiController.getExtensions()[x];
//                                            }
//                                        }
//                                        CommentsWithHistoryUI ui = (CommentsWithHistoryUI) ce.getSideBar();
//
//                                        try {
//                                            //System.out.println(user + ":" + userContent);
//                                            //uiController.setWorkbookModified(cell.getSpreadsheet().getWorkbook());
//                                            comment.addUsersComment(userContent, u);
//                                            ui.addComment(u.name(), userContent);
//                                            //c.changeActiveCell(comment);
//                                        } catch (IllegalArgumentException e) {
//                                            //do nothing
//                                        }
//                                    }
//
//                                }
                            }
                        }
                    }
                } else if (an2.getNodeType() == Node.ELEMENT_NODE && an2.getNodeName().equals("macro")) {
                    NamedNodeMap attributes = an2.getAttributes();
                    String macroName = attributes.getNamedItem("name").getTextContent();
                    NodeList macroChilds = an2.getChildNodes();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < macroChilds.getLength(); i++) {
                        Node macroCode = macroChilds.item(i);
                        if(macroCode.getNodeName().equals("code")){
                            stringBuilder.append(macroCode.getTextContent());
                        }
                    }
                    MacroWithName m = new MacroWithName(macroName, stringBuilder.toString(), work.getSpreadsheet(count), uiController);
                    work.getMacroList().addMacro(m);
                } else if (an2.getNodeType() == Node.ELEMENT_NODE && an2.getNodeName().equals("global_variables")) {
                    //todo
                }
            }
            count++;

        }
        return count;
    }

    private Color getColor(Node color) {
        int red = 0, green = 0, blue = 0, alpha = 255;

        NamedNodeMap attributes = color.getAttributes();
        String redString = attributes.getNamedItem("red").getTextContent();
        String greenString = attributes.getNamedItem("green").getTextContent();
        String blueString = attributes.getNamedItem("blue").getTextContent();
        String alphaString = attributes.getNamedItem("alpha").getTextContent();

        try {
            red = Integer.parseInt(redString);
            green = Integer.parseInt(greenString);
            blue = Integer.parseInt(blueString);
            alpha = Integer.parseInt(alphaString);

        } catch (NumberFormatException e) {
            //System.out.println("excecao");
        }

        Color newColor = new Color(red, green, blue, alpha);
        return newColor;
    }

}
