/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.white.s1.core.n4567890.contacts.ui.UIExtensionContacts;

/**
 * An extension to support contacts.
 * 
 * @author Alexandre Braganca
 */
public class ContactsExtension extends Extension {

	/** The name of the extension */
	public static final String NAME = "Contacts";
        
        /**
        * The description of the extension
        */
        public static final String DESCRIPTION = "An extension to support contacts.";
        
        /**
        * The first version of the dependency trees extension.
        */
        public static final int VERSION = 1;

	/**
	 * Creates a new Example extension.
	 */
	public ContactsExtension() {
            super(NAME,VERSION,DESCRIPTION);
	}
	
	/**
	 * Returns the user interface extension of this extension 
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
        @Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionContacts(this, uiController);
	}
        
        @Override
        public String metadata() {
           return String.format("This is %s with version %d\n"
                   + "This extension has the follow description: %s\n"
                   + "This extension was made by Alexandre Braganca and it is in the package %s",
                   getName(), getVersion(), getDescription(),getClass().getName());
        }
}