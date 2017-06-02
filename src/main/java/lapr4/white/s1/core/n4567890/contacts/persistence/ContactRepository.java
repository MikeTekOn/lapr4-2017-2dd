/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.persistence;

import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import eapli.framework.persistence.repositories.Repository;

import java.util.List;

/**
 * Created by nuno on 21/03/16.
 */
public interface ContactRepository extends Repository<Contact, Long> {

    boolean removeContact(Contact c) throws DataIntegrityViolationException;

    /**
     * Finds a list of contacts with a regexPattern that matches a regular expression (in regex)
     * @param regexPattern -
     * @return list of found contacts
     */
    List<Contact> findByRegex(String regexPattern);
}
