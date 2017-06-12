/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150738.contacts;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.green.s2.core.n1150738.contacts.ui.UIExtensionCompanyContacts;
import lapr4.white.s1.core.n4567890.contacts.ui.UIExtensionContacts;

/**
 * An extension to support contacts.
 * 
 * @author Alexandre Braganca
 */
public class CompanyContactsExtension extends Extension {

	/** The name of the extension */
	public static final String NAME = "Company Contacts";

	/**
	 * Creates a new Example extension.
	 */
	public CompanyContactsExtension() {
            super(NAME, 1, "Company Contacts");
	}
	
	/**
	 * Returns the user interface extension of this extension 
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
        @Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionCompanyContacts(this, uiController);
	}

	/**
	 * If gives the metadata information about the extension.
	 *
	 * @return It returns a string with the metadata.
	 */
	@Override
	public String metadata() {
		return String.format("This is %s with version %d\n"
						+ "This extension has the follow description: %s\n"
						+ "This extension was made by Henrique Oliveira in Sprint 2 and it is in the package %s",
				getName(), getVersion(), getDescription(),getClass().getName());
	}
}