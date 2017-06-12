/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.persistence;

import lapr4.green.s2.core.n1150738.contacts.persistence.CompanyContactRepository;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    ExtensionSettings setSettings(ExtensionSettings settings);
    
    ContactRepository contacts();

    CompanyContactRepository companyContacts();
}
