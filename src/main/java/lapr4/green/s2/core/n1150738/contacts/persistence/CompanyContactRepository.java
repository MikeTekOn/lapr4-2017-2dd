package lapr4.green.s2.core.n1150738.contacts.persistence;

import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.Repository;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyName;

/**
 * Represents the behaviour of a Repository of CompanyContacts.
 * @see CompanyContact
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public interface CompanyContactRepository  extends Repository<CompanyContact, Long> {

    public CompanyContact findByCompanyName(CompanyName n);

    public boolean remove(CompanyContact c) throws DataIntegrityViolationException;
}
