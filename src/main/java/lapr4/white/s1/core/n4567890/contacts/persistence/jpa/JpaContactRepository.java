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

import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by nuno on 20/03/16.
 */
class JpaContactRepository extends CrmJpaRepositoryBase<Contact, Long> implements ContactRepository {

    JpaContactRepository(ExtensionSettings settings) {
        super(settings);
    }

    @Override
    public boolean removeContact(Contact c) throws DataIntegrityViolationException {
        try {
            delete(c);
        }
        catch (Exception ex) {
            throw new DataIntegrityViolationException(ex);
        }
        return true;
    }

    @Override
    public List<Contact> findByRegex(String regexPattern){
        Iterator<Contact> it = findAll().iterator();
        List<Contact> foundContacts = new ArrayList<>();
        Pattern p = Pattern.compile(regexPattern);
        Contact c = null;
        for(; it.hasNext(); c = it.next()){
            Matcher m = p.matcher(c.name());
            if(m.matches()){
                foundContacts.add(c);
            }
        }

        return foundContacts;
    }

    @Override
    public Iterable<Contact> allRelatedToCompany(CompanyContact c) {
        System.out.println(c);
        Map<String, Object> params = new HashMap();
        params.put("company", c);
        return match("e.companyContact = :company", params);
    }
}
