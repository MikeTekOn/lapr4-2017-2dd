/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150738.contacts.persistence.jpa;

/**
 *
 * @author alexandrebraganca
 */

import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyName;
import lapr4.green.s2.core.n1150738.contacts.persistence.CompanyContactRepository;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.persistence.jpa.CrmJpaRepositoryBase;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class JpaCompanyContactRepository extends CrmJpaRepositoryBase<CompanyContact, Long> implements CompanyContactRepository {

    public JpaCompanyContactRepository(ExtensionSettings settings) {
        super(settings);
    }


    @Override
    public CompanyContact findByCompanyName(CompanyName n) {
        return null;
    }
}
