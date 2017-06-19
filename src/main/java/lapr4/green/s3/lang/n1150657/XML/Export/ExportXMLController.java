/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export;

import csheets.core.Cell;
import csheets.ui.ctrl.UIController;
import lapr4.s1.export.ExportContext;
/**
 *
 * @author Eric with improvements of Sofia Gonçalves (1150657)
 */
public class ExportXMLController {
    
    private String selectedPath;
    
    private ExportComponent exportComponent;
    
    private ExportContext strategy;
    
    private UIController uiController;

    public ExportXMLController(UIController uiController,String selectedPath) {
        this.selectedPath = selectedPath;
        this.exportComponent = new ExportComponent(uiController);
        this.uiController = uiController;
    }
    
    public void addTagName(String tag, String name){
        uiController.addTagName(tag, name);
    }
    
    /**
     * Exports a workbook.
     *
     * @return true if exported, false otherwise.
     */
    public boolean exportWorkbook() {
        ExportXML exporterXML = new ExportXML(selectedPath, uiController.getTagsName().getMapWithTags(), exportComponent.getMacroListByActiveworkbook(), exportComponent.getGlobalVariablesByActiveWorkbook());
        this.strategy = new ExportContext(exporterXML);
        exporterXML.selectRange(exportComponent.mapWithActiveCellsByWorkbook());
        return strategy.executeStrategy();
    }
    
    /**
     * Exports a spreadsheet.
     * @return true if exports spreadsheet, false otherwise.
     */
    public boolean exportSpreadsheet() {
        ExportXML exporterXML = new ExportXML(selectedPath, uiController.getTagsName().getMapWithTags(), exportComponent.getMacroListByActiveworkbook(), exportComponent.getGlobalVariablesByActiveWorkbook());
        this.strategy = new ExportContext(exporterXML);
        exporterXML.selectRange(exportComponent.mapWithActiveCellsBySpreadcheet());
        return strategy.executeStrategy();
    }
    
    /**
     * Exports a selected cell.
     * @param selectedCells
     * @return true if exported, false otherwise.
     */
    public boolean exportSelectedCell(Cell selectedCells[][]) {
        ExportXML exporterXML = new ExportXML(selectedPath, uiController.getTagsName().getMapWithTags(), exportComponent.getMacroListByActiveworkbook(), exportComponent.getGlobalVariablesByActiveWorkbook());
        this.strategy = new ExportContext(exporterXML);
        exporterXML.selectRange(exportComponent.mapWithActiveCell(selectedCells));
        return strategy.executeStrategy();
    }
    
    /**
     * Exports the selected column.
     * @param selectedColumn the selected column.
     * @return true if exported, false otherwise.
     */
    public boolean exportSelectedColumn(int selectedColumn) {
        ExportXML exporterXML = new ExportXML(selectedPath, uiController.getTagsName().getMapWithTags(), exportComponent.getMacroListByActiveworkbook(), exportComponent.getGlobalVariablesByActiveWorkbook());
        this.strategy = new ExportContext(exporterXML);
        exporterXML.selectRange(exportComponent.mapWithActiveCellByColumn(selectedColumn));
        return strategy.executeStrategy();
    }
    
    /**
     * Export the selected row.
     * @param selectedRow the selected row to export.
     * @return true if exports, false otherwise.
     */
    public boolean exportSelectedRow(int selectedRow) {
        ExportXML exporterXML = new ExportXML(selectedPath, uiController.getTagsName().getMapWithTags(), exportComponent.getMacroListByActiveworkbook(), exportComponent.getGlobalVariablesByActiveWorkbook());
        this.strategy = new ExportContext(exporterXML);
        exporterXML.selectRange(exportComponent.mapWithActiveCellByRow(selectedRow));
        return strategy.executeStrategy();
    }

}
