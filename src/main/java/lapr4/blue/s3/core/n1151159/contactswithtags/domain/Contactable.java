package lapr4.blue.s3.core.n1151159.contactswithtags.domain;

/**
 * Adds the ability to be contactable. Retrieves information about the contact.
 *
 * @author Ivo Ferro [1151159]
 */
public interface Contactable extends Taggable {

    /**
     * Retrieves the name of the contact class.
     *
     * @return the name of the contact
     */
    String contactName();

    /**
     * Retrieves the number of the contact.
     *
     * @return number of the contact
     */
    String contactNumber();

    /**
     * Retrieves the type of the contact (e.g. Personal Contact, Company Contact...).
     *
     * @return the type of the contact
     */
    String contactType();
}
