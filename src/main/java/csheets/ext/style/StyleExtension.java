/*
 * Copyright (c) 2005 Einar Pehrson
 *
 * This file is part of
 * CleanSheets Extension for Style
 *
 * CleanSheets Extension for Style is free software; you can
 * redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 *
 * CleanSheets Extension for Style is distributed in the hope that
 * it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets Extension for Style; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 */
package csheets.ext.style;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.Extension;
import csheets.ext.style.ui.StyleUIExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * The extension for style.
 *
 * @author Einar Pehrson
 */
public class StyleExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Style";
    
    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "The extension for style.";
    
    /**
     * The first version of the style extension.
     */
    public static final int VERSION = 1;

    /**
     * Creates a new style extension.
     */
    public StyleExtension() {
        super(NAME, VERSION, DESCRIPTION);
    }

    /**
     * Makes the given spreadsheet stylable.
     * @param spreadsheet the spreadsheet to extend
     * @return a stylable spreadsheet
     */
    public StylableSpreadsheet extend(Spreadsheet spreadsheet) {
        return new StylableSpreadsheet(spreadsheet);
    }

    /**
     * Makes the given cell stylable.
     * @param cell the cell to extend
     * @return a stylable cell
     */
    public StylableCell extend(Cell cell) {
        return new StylableCell(cell);
    }

    /**
     * Returns a user interface extension for style.
     * @param uiController the user interface controller
     * @return a user interface extension for style
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new StyleUIExtension(this, uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Einar Pehrson and it is in the package %s",
                getName(), getVersion(), getDescription(),getClass().getName());
    }
}
