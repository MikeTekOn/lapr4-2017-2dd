/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Import;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
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
import java.util.Map;
import javax.swing.border.MatteBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.application.CommentsWithHistoryController;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.presentation.CommentsWithHistoryUI;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.presentation.CommentsWithHistoryUIExtension;
import lapr4.green.s3.lang.n1150532.variables.Variable;
import lapr4.green.s3.lang.n1150657.XML.Export.ActiveCell;
import lapr4.green.s3.lang.n1150657.XML.Export.ElementCleansheet;
import lapr4.green.s3.lang.n1150657.XML.Export.TagsName;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroWithName;
import lapr4.red.s2.lang.n1151094.ImportXML.ImportStrategy;
import org.xml.sax.SAXException;
import org.w3c.dom.*;
import lapr4.red.s1.core.n1150690.comments.domain.User;

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

    private Cell selectedCells[][];

    private boolean isReplace;

    private static final int DEFAULT_CHOICE = -1;

    private static int spreadsheetChoice = DEFAULT_CHOICE;
    
    private String spreasheetTag;
    private String workbookTag;
    private String cellTag;
    private String contentTag;
    private String fontTag;
    private String foregroundTag;
    private String macroTag;
    private String variablesTag;
    private String commentTag;

    /**
     * This constructor receives as parameter the UIController. It is
     * responsible for instantiating the Object.
     *
     * @param uiController of ImportXml
     * @param selectedPath
     */
    public ImportXML(UIController uiController, String selectedPath, boolean isReplace) {
        this.uiController = uiController;
        this.path = selectedPath;
        this.isReplace = isReplace;
        this.selectedCells = null;
        setNamesTags();
        
    }
    
    private void setNamesTags(){
        Map<String, String> tags = uiController.getTagsName().getMapWithTags();
        spreasheetTag = tags.get(ElementCleansheet.SPREADSHEET.getElementName()).equals(TagsName.DEFAULT_USER_TAG_NAME) ? ElementCleansheet.SPREADSHEET.getElementName() : 
                tags.get(ElementCleansheet.SPREADSHEET.getElementName());
        workbookTag = tags.get(ElementCleansheet.WORKBOOK.getElementName()).equals(TagsName.DEFAULT_USER_TAG_NAME) ? ElementCleansheet.WORKBOOK.getElementName() : 
                tags.get(ElementCleansheet.WORKBOOK.getElementName());
        cellTag = tags.get(ElementCleansheet.CELL.getElementName()).equals(TagsName.DEFAULT_USER_TAG_NAME) ? ElementCleansheet.CELL.getElementName() : 
                tags.get(ElementCleansheet.CELL.getElementName());
        
        contentTag = tags.get(ElementCleansheet.CONTENT.getElementName()).equals(TagsName.DEFAULT_USER_TAG_NAME) ? ElementCleansheet.CONTENT.getElementName() : 
                tags.get(ElementCleansheet.CONTENT.getElementName());
        fontTag = tags.get(ElementCleansheet.FONT.getElementName()).equals(TagsName.DEFAULT_USER_TAG_NAME) ? ElementCleansheet.FONT.getElementName() : 
                tags.get(ElementCleansheet.FONT.getElementName());
        foregroundTag = tags.get(ElementCleansheet.FOREGROUNG.getElementName()).equals(TagsName.DEFAULT_USER_TAG_NAME) ? ElementCleansheet.FOREGROUNG.getElementName() : 
                tags.get(ElementCleansheet.FOREGROUNG.getElementName());
        
        macroTag = tags.get(ElementCleansheet.MACRO.getElementName()).equals(TagsName.DEFAULT_USER_TAG_NAME) ? ElementCleansheet.MACRO.getElementName() : 
                tags.get(ElementCleansheet.MACRO.getElementName());
        variablesTag = tags.get(ElementCleansheet.GLOBAL_VARIABLE.getElementName()).equals(TagsName.DEFAULT_USER_TAG_NAME) ? ElementCleansheet.GLOBAL_VARIABLE.getElementName() : 
                tags.get(ElementCleansheet.GLOBAL_VARIABLE.getElementName());
        commentTag = tags.get(ElementCleansheet.COMMENT.getElementName()).equals(TagsName.DEFAULT_USER_TAG_NAME) ? ElementCleansheet.COMMENT.getElementName() : 
                tags.get(ElementCleansheet.COMMENT.getElementName());
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
            if (!isReplace) {
                work = uiController.getActiveWorkbook();
            }
            if (spreadsheetChoice == DEFAULT_CHOICE && selectedCells == null) {
                for (int i = 0; i < listSpreadSheet.getLength(); i++) {
                    an = listSpreadSheet.item(i);
                    if (an.getNodeName().equals(spreasheetTag)) {
                        count = alterWorkbook(an, work, count);
                    }

                }
            } else if (spreadsheetChoice != DEFAULT_CHOICE && selectedCells == null) {
                for (int i = 0; i < listSpreadSheet.getLength(); i++) {
                    an = listSpreadSheet.item(i);
                    if (an.getNodeName().equals(spreasheetTag)) {
                        if (count == spreadsheetChoice) {
                            alterWorkbook(an, work, count);
                            break;
                        }
                        count++;
                    }
                }
            } else if (selectedCells != null) {
                for (int i = 0; i < listSpreadSheet.getLength(); i++) {
                    an = listSpreadSheet.item(i);
                    if (an.getNodeName().equals(spreasheetTag)) {
                        if (an.getAttributes().getNamedItem(DEFAULT_TITLE).getTextContent().equals(uiController.getActiveSpreadsheet().getTitle())) {
                            alterWorkbook(an, work, count);
                            break;
                        }
                        count++;
                    }
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
        int firstRowORder = 0;

        if (spreadsheet.getNodeType() == Node.ELEMENT_NODE) {
            NodeList listCellOfSpreadSheet = spreadsheet.getChildNodes();
            NamedNodeMap attr = spreadsheet.getAttributes();
            Spreadsheet s = work.getSpreadsheet(count);
            if (!isReplace) {
                firstRowORder = append(s);
            }
            if (isReplace) {
                String title = attr.getNamedItem(DEFAULT_TITLE).getTextContent();
                s.setTitle(title);
            }

            for (int j = 0; j < listCellOfSpreadSheet.getLength(); j++) {
                an2 = listCellOfSpreadSheet.item(j);
                if (an2.getNodeType() == Node.ELEMENT_NODE && an2.getNodeName().equals(cellTag)) {
                    NamedNodeMap attributes = an2.getAttributes();
                    //cell
                    String column = attributes.getNamedItem(DEFAULT_COLUMN).getTextContent();
                    String row = attributes.getNamedItem(DEFAULT_ROW).getTextContent();
                    //cell address
                    Address addr = new Address(column, row);
                    if (!isReplace) {
                        int newColumn = addr.getColumn();
                        int newRow = addr.getRow() + firstRowORder;
                        addr = new Address(newColumn, newRow);
                    }
                    if (selectedCells != null) {
                        int rowMax = selectedCells.length - 1;
                        int columnMax = selectedCells[rowMax].length - 1;
                        Cell last = null;
                        try {
                            last = selectedCells[rowMax][columnMax];
                        } catch (ArrayIndexOutOfBoundsException e) {

                        }
                        if (addr.getColumn() > last.getAddress().getColumn()
                                || addr.getRow() > last.getAddress().getRow()) {
                            break;
                        }
                    }

                    NodeList cellChilds = an2.getChildNodes();
                    for (int i = 0; i < cellChilds.getLength(); i++) {
                        Node child = cellChilds.item(i);
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            Cell cell = work.getSpreadsheet(count).getCell(addr);
                            //content
                            if (child.getNodeName().equals(contentTag)) {
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
                            if (child.getNodeName().equals(fontTag)) {
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
                            if (child.getNodeName().equals(foregroundTag)) {
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
                            if (child.getNodeName().equals(commentTag)) {

                                CommentsWithHistoryUIExtension ce = null;
                                for (int x = 0; x < uiController.getExtensions().length; x++) {
                                    if (uiController.getExtensions()[x] instanceof CommentsWithHistoryUIExtension) {
                                        ce = (CommentsWithHistoryUIExtension) uiController.getExtensions()[x];
                                    }
                                }
                                CommentsWithHistoryUI ui = (CommentsWithHistoryUI) ce.getSideBar();
                                CommentsWithHistoryController controller = ui.controller();

                                CommentableCellWithMultipleUsers commentable = new CommentableCellWithMultipleUsers(cell);

                                NamedNodeMap attrChild = child.getAttributes();
                                String user = attrChild.getNamedItem("user").getTextContent();

                                User u = new User(user);
                                NodeList commentChild = child.getChildNodes();
                                for (int k = 0; k < commentChild.getLength(); k++) {
                                    Node childComment = commentChild.item(k);
                                    if (childComment.getNodeName().equals("content") && childComment.getNodeType() == Node.ELEMENT_NODE) {
                                        String userContent = childComment.getTextContent();
                                        commentable.addUsersComment(userContent, u);
                                        controller.changeActiveCell(commentable);
                                        ui.commentChanged(commentable);
                                    }
                                }
                            }
                        }
                    }
                } else if (an2.getNodeType() == Node.ELEMENT_NODE && an2.getNodeName().equals(macroTag)) {
                    NamedNodeMap attributes = an2.getAttributes();
                    String macroName = attributes.getNamedItem("name").getTextContent();
                    NodeList macroChilds = an2.getChildNodes();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < macroChilds.getLength(); i++) {
                        Node macroCode = macroChilds.item(i);
                        if (macroCode.getNodeName().equals("code")) {
                            stringBuilder.append(macroCode.getTextContent());
                        }
                    }
                    MacroWithName m = new MacroWithName(macroName, stringBuilder.toString(), work.getSpreadsheet(count), uiController);
                    work.getMacroList().addMacro(m);
                } else if (an2.getNodeType() == Node.ELEMENT_NODE && an2.getNodeName().equals(variablesTag)) {
                    NamedNodeMap attributes = an2.getAttributes();
                    String varName = attributes.getNamedItem("name").getTextContent();
                    Variable v = new Variable(varName);
                    work.globalVariables().variables().add(v);
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

    private int append(Spreadsheet spread) {
        Address ad = new Address(0, 0);
        int greaterRow = 0;
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 52; j++) {
                Cell c = spread.getCell(i, j);
                ActiveCell activeCell = new ActiveCell(c);
                if (activeCell.hasContent() || activeCell.hasBackgroundColor()
                        || activeCell.hasBorder() || activeCell.hasFormat()) {
                    ad = c.getAddress();
                    if (greaterRow < ad.getRow()) {
                        greaterRow = ad.getRow();
                    }
                }
            }
        }

        return greaterRow + 1;
    }

    public int countNumberSpreadSheet(String pathName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        Document doc = dBuilder.parse(new File(pathName));
        Element elementWorkbook = doc.getDocumentElement();
        NodeList listSpreadSheet = elementWorkbook.getChildNodes();

        return countSpreadSheet(listSpreadSheet);
    }

    public void setIsReplace(boolean isReplace) {
        this.isReplace = isReplace;
    }

    public static void choiceSpreadSheet(int spreadChoice) {
        ImportXML.spreadsheetChoice = spreadChoice;
    }

    /**
     * Defines the selected cells.
     *
     */
    public void selectRange(Cell selectedCells[][]) {
        this.selectedCells = selectedCells;
    }
}
