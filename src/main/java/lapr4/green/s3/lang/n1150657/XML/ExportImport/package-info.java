/**
 * Technical documentation regarding the user story LANG08.3- Full XML Import/Export.
 * <p>
 * <b>Scrum Master: - yes</b>
 * </p>
 * <p>
 * <b>Area Leader: - yes</b>
 * </p>
 * <h2>1. Requirement</h2>
 * The export/import xml now has to include all the persisting elements of CleanSheets.
 *  <ul>
 *      <li>Macros</li>
 *      <li>Formatting styles</li>
 *      <li>Comments</li>
 *      <li>Global Variables</li>
 *  </ul>
 *  The import should now ask the user if the file contents should replace or append the existing workbook contents.
 * <p>
 *
 * <h2>2. Analysis</h2>
 * <p>
 * To understand how Export/Import works it was necessary to study all the previous work.
 * This analysis will be devide in two parts. The Export and Import.
 * <h3>2.1 Export Analysis</h3>
 * The previous implementation show us that the export only do the content (See lapr4.blue.s1.lang.n1141570.XML).
 * In the same way, the export will be made (with the tags reference) with the addition of all the persistent elements.
 * The tag will now have 9 options for the use to make. Workbook, spreadsheet and cell that already existed,
 * plus the content (of the cell), the font (style), the foreground (style), the macro, the global variables and comments.
 * It exists an object {@link lapr4.green.s3.lang.n1150657.XML.Export.ElementCleansheet} now created that represents an enum with all
 * default tags name. Another object exists {@link lapr4.green.s3.lang.n1150657.XML.Export.TagsName} that will represent the object
 * that will contain the information of the tags name. This means that if the user chooses a new tag, it will be 
 * associated with the default tag name (that the list will be initialize and added with the user choice). 
 * If nothing is chosen, the default tag is set. Some issues were found relative with
 * the default tags name and the UI, that now are fixed. This is saved in UIController.
 * <p>
 * The XML representation is still:
 * </p>
 * <p>- workbook</p>
 * <p>-- spreadsheet (with a title attribute)</p>
 * <p>--- cell (with the column and row attribute)</p>
 * Now it has in the cell:
 * <ul>
 * <li>content (if it's the case, that represents the content of the cell)</li>
 * <li>color (if it's the case, that represents the color of the cell)</li>
 * <li>font (if it's the case, that represents the font of the content of cell)</li>
 * <li>foreground (if it's the case, that represents the color of the font of the cell)</li>
 * <li>format (if it's the case, that represents the format of the cell)</li>
 * <li>comment (if it's the case, that represents the comment of the cell)</li>
 * <ul><li>the comment has an atribute userand one or several contents</li></ul>
 * <ul><li>the color, font,border and foreground has attributes that represents
 * the colors (alpha,blue,green,red)</li></ul>
 * <ul><li>the border has four attributes that represents wich of the side of the cell 
 * has a border (top,left,right and bottom)</li></ul>
 * </ul>
 * In the same level that the spreadsheet:
 * <ul>
 * <li>macro (if it is the case that represent the macros that exist in the cleansheet)</li>
 * <ul><li>macro has an attribute name with a code representation</li></ul>
 * <li>global variables (if it is the case that represents the global variables
 * that exist in the cleanshet)</li>
 * </ul>
 * <p>
 * One import thing to know in this part is how to get the persisting elements in the Cleansheets.
 * All the cells has a style. Doing a cast with {@link csheets.ext.style.StylableCell}, we can get the styles
 * from it. Since all cells have a default style, in the exportation it is chosen 
 * the styles that aren't by default that means, if the user chooses a different color, it is exported. 
 * <li>The default color is all the elements in 255 (nothing), that means if there's one element
 * different than 255, is has a color</li>
 * <li>The default border is an EmptyBorder. When it has one, a MatteBorder is built, that means
 * it only is exported when is a MatteBorder</li>
 * <li>All elements that are associated with the content (font and foreground are added)
 * if it exists a content</li>
 * <li>The default format is that it doesn't exist, that means it is olny exported if
 * exist a format</li>
 * </p>
 * <p>
 * The macro elements, global variables and comments are elements that we get in each Object that
 * represents it. The Macro we see get the list in the workbook ({@link lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroList})
 * and each {@link lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroWithName} that exists, we get all the information
 * The global variables has the same logical {@link lapr4.red.s2.lang.n1150623.globalVariables.VarContent} has 
 * the list with {@link lapr4.green.s3.lang.n1150532.variables.Variable}. The comments
 * are casting by {@link lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers} 
 * that extends the CommentableCell. If the cast has success, that means the cell has a comment. Having the comment
 * the exporte has it.
 * </p>
 * <p>
 * The {@link lapr4.green.s3.lang.n1150657.XML.Export.ExportXMLController} is an object that has the responsability to get the
 * range of cells selected. This implementation was added since the previous version it was the ImportXML that had that responsibility.
 * </p>
 * 
 * <img src="export_xml_analysis.png" alt="image">
 * 
 * <h3>2.2 Import Analysis</h3>
 * </p>
 * The importation is the opposite of the exportating except how we get the objects such has
 * styles. 
 * The importation is made by reading the file, and searching for the elements and its childs.
 * With the tags name (made by each UiController) we search for the elements and create them (cells) or
 * add them (macro, comment and global variables).
 * <p>
 * <p>
 * The new feature (replace or append) the new cleansheet differs on the getting the workbook.
 * It is replace, a new workbook is created and defined in the cleansheet. If append is chosen
 * the workbook is not created but obtained by the active workbook (the workbook that is active).
 * </p>
 * <p>
 * Since the use case was not complete, the importation by spreadsheet and the sleected cells was
 * built. The spreadsheet is chosen by the order of the spreadsheet, that means
 * if in the file importation has 3 spreadsheets the user chooses 1 or 2 or 3 and it is added
 * on the respective spreadsheet. The selected cells are obtained by the select cells in the spreadsheet
 * by the FocusOwnerAction.
 * </p>
 * 
 * <p>
 * <img src="import_xml_analysis.png" alt="image"> 
 * </p>
 * <p>
 * Strategy base pattern:
 * <img src="strategy_pattern.png" alt="image"> 
 * </p>
 * 
 * <b>2.1 Questions</b>
 * <li>Ihe user should choose the new tags? </li>
 * <ul>Answear: Yes</ul>
 *
 * <h2>3. Tests</h2>
 *
 * <h3>3.1 Functional Tests:</h3>
 * 
 * <h3>3.2 Unit Tests:</h3>
 *
 * <h2>4. Design</h2>
 * <p>
 * <img src="export_xml_sd.png" alt="image"> 
 * </p>
 * <p>
 * <img src="import_xml_sd.png" alt="image"> 
 * </p>
 * 
 * <h2>5. Code</h2>
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.ActiveCell}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.ElementCleansheet}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.ExportComponent}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.ExportXML}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.ExportXMLController}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.Path}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.TagsName}
 * 
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportSelectedCellsActionUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportSelectedColumnActionUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportSelectedRowActionUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportSelectedSpreadsheetActionUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportWorkBookActionUI}
 * 
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.UI.ExportSelectedCellsUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.UI.ExportSelectedColumnUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.UI.ExportSelectedRowUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.UI.ExportSelectedSpreadsheetUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.UI.ExportSelectedWorkbookUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.UI.ExportXMLMenuUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Export.UI.TagNamesInputDialogUI}
 * 
 * {@link lapr4.green.s3.lang.n1150657.XML.Import.ImportXML}
 * {@link lapr4.green.s3.lang.n1150657.XML.Import.ImportXMLController}
 * 
 * {@link lapr4.green.s3.lang.n1150657.XML.Import.Action.ImportSelectedCellsActionUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Import.Action.ImportSelectedSpreadsheetActionUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Import.Action.ImportSelectedWorkbookActionUI}
 * 
 * {@link lapr4.green.s3.lang.n1150657.XML.Import.UI.ImportSelectedCellsUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Import.UI.ImportSelectedSpreadsheetUI}
 * {@link lapr4.green.s3.lang.n1150657.XML.Import.UI.ImportSelectedWorkbookUI}
 */
package lapr4.green.s3.lang.n1150657.XML.ExportImport;
