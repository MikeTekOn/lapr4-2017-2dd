/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package csheets.ext;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import java.io.Serializable;
import lapr4.red.s1.core.n1150385.enabledisableextensions.ExtensionEvent;
import lapr4.red.s1.core.n1150385.enabledisableextensions.ExtensionStateListener;

/**
 * An interface for extensions to the CleanSheets application.
 * @author Einar Pehrson
 */
public abstract class Extension implements Comparable<Extension>, ExtensionStateListener {

	/** The name of the extension */
	private final String name;
        
        /**The version of the extension*/
        private int version;
        
        /**The description of the extension*/
        private final String description;

	/** The base key to use for properties of the extension */
	private final String basePropKey;

	/** Whether or not the extension is enabled */
	private boolean enabled = true;

	/**
	 * Creates a new extension.
	 * @param name the name of the extension
        * @param version
        * @param description
	 */
	public Extension(String name, int version, String description) {
		this.name = name;
                this.version = version;
                this.description = description;

		// Builds UI extension base property key
		String basePropKey = "";
		for (String token : name.toLowerCase().split(" "))
			basePropKey += token;
		this.basePropKey = basePropKey + ".ui.";
	}

	/**
	 * Returns the name of the extension.
	 * @return the name of the extension
	 */
	public final String getName() {
		return name;
	}
        
        /**
         * Returns the version of the extension.
         * @return It returns an int with the version.
         */ 
        public int getVersion(){
            return version;
        }
        
        /**
         * Return the description of the extension.
         * @return It returns a string with the description.
         */
        public String getDescription(){
            return description;
        }
        
        
	/**
	 * Returns the base key to use for properties of the UI extension.
	 * @return the base key to use for properties of the UI extension
	 */
	public final String getPropertyKey() {
		return basePropKey;
	}

	/**
	 * Compares this extension with the given extension for order.
	 * Ordering is done by the extensions' names.
	 * @param extension the extension to compared to
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 */
	public final int compareTo(Extension extension) {
		return name.compareTo(extension.name);
	}

	/**
	 * Returns an extension of the given spreadsheet.
	 * @param spreadsheet the spreadsheet to extend
	 * @return a spreadsheet extension, or null if none is provided
	 */
	public SpreadsheetExtension extend(Spreadsheet spreadsheet) {
		return null;
	}

	/**
	 * Returns an extension of the given cell.
	 * @param cell the cell to extend
	 * @return a cell extension, or null if none is provided
	 */
	public CellExtension extend(Cell cell) {
		return null;
	}

	/**
	 * Returns the user interface extension of this extension.
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
	public UIExtension getUIExtension(UIController uiController) {
		return null;
	}

	/**
	 * Returns if this extension is enabled
	 * @return if this extension is enabled
	 */
	public boolean isEnabled() { return enabled; }

	/**
	 * This method is called whenever the extension is disabled.
	 */
	private void extensionDisabled() { enabled = false; }

	/**
	 * This method is called whenever this extension is enabled. An extension
	 * is considered enabled when it is loaded into the program, so, in order
	 * to be enabled, it first needs to be disabled.
	 */
	private void extensionEnabled() { enabled = true; }

	@Override
	public void extensionStateChanged(ExtensionEvent event) {
		if(enabled && ExtensionManager.getInstance().getExtension(name) == null){
			extensionDisabled();
		}else if(!enabled && ExtensionManager.getInstance().getExtension(name) != null){
			extensionEnabled();
		}
	}

        /**
         * Sets the version to a new one.
         * @param version The new version to change.
         */
        public void setVersion(int version){
            this.version = version;
        }

        /**
         * If gives the metadata information about the extension.
         * @return It returns a string with the metadata.
         */
        public abstract String metadata();
        
}