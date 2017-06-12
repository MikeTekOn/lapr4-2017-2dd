/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150738.contacts.persistence.jpa;

/**
 * @author alexandrebraganca
 */

import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyName;
import lapr4.green.s2.core.n1150738.contacts.persistence.CompanyContactRepository;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.persistence.jpa.CrmJpaRepositoryBase;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println(n);
        Map<String, Object> params = new HashMap();
        params.put("namecpn", n);
        return matchOne("e.companyName = :namecpn", params);

    }

    @Override
    public boolean remove(CompanyContact c) throws DataIntegrityViolationException {
        try {
            delete(c);
        } catch (Exception ex) {
            throw new DataIntegrityViolationException(ex);
        }
        return true;
    }

}
