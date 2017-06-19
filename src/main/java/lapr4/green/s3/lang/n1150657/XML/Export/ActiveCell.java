/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export;

import csheets.core.Cell;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import eapli.util.Strings;
import java.awt.Color;
import java.text.Format;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroList;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;

/**
 *
 * @author Sofia
 */
public class ActiveCell {

    private Cell cell;

    private StylableCell stylableCell;

    ;
    public ActiveCell(Cell cell) {
        this.cell = cell;
        this.stylableCell = (StylableCell) cell.getExtension(StyleExtension.NAME);

    }

    public boolean hasContent() {
        return !Strings.isNullOrEmpty(cell.getContent()) && !Strings.isNullOrWhiteSpace(cell.getContent());
    }

    public boolean hasGlobalVariables(VarContentor list) {
        return !list.variables().isEmpty();
    }

    public boolean hasMacros(MacroList list) {
        return !list.getMacroList().isEmpty();
    }

    public boolean hasBackgroundColor() {
        Color cellColor = stylableCell.getBackgroundColor();
        boolean expression = cellColor.getRed() != 255 ||
                cellColor.getBlue() != 255 ||
                cellColor.getGreen() != 255 ||
                cellColor.getAlpha() != 255;
        return expression;
    }

    public boolean hasFormat() {
        Format cellFormat = stylableCell.getFormat();
        return (cellFormat != null);
    }

    public boolean hasBorder() {
        Border cellBorder = stylableCell.getBorder();
        return (cellBorder instanceof MatteBorder);
    }
}
