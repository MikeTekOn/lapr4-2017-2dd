/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.persistence.jpa;

/**
 *
 * @author alexandrebraganca
 */
import lapr4.green.s2.core.n1150738.contacts.persistence.CompanyContactRepository;
import lapr4.green.s2.core.n1150738.contacts.persistence.jpa.JpaCompanyContactRepository;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.RepositoryFactory;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    private ExtensionSettings settings=null;
    
    public ExtensionSettings setSettings(ExtensionSettings settings) {
        return this.settings=settings;
    }

    @Override
    public ContactRepository contacts() {
        return new JpaContactRepository(this.settings);
    }

    @Override
    public CompanyContactRepository companyContacts() {
        return new JpaCompanyContactRepository(this.settings);
    }


}
