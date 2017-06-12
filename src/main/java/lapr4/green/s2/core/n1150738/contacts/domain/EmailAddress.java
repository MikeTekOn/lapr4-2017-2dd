package lapr4.green.s2.core.n1150738.contacts.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * Value Object that represents a EmailAddress.
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
@Embeddable
public class EmailAddress implements ValueObject{

    /**
     * Regex to validate email. Source: http://emailregex.com/
     */
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    /**
     * The profession of the Email
     */
    private String email;

    /**
     * Constructor.
     *
     * Constructs the profession object given a string representing a EmailAddress
     * @param email the representation of the EmailAddress
     */
    public EmailAddress(String email){
        if(eapli.util.Strings.isNullOrEmpty(email)){
            throw new IllegalArgumentException("Invalid Profession!");
        }
        this.email = email;
    }

    /**
     * Constructor for ORM
     */
    protected EmailAddress(){
        //ORM
    }

    public static boolean validateEmail(String email){
        return email.matches(EMAIL_REGEX);
    }

    @Override
    public String toString(){
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailAddress that = (EmailAddress) o;

        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}

