package lapr4.green.s2.core.n1150738.contacts.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

import javax.persistence.Embeddable;

/**
 * Value Object that represents a PhoneNumber.
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
@Embeddable
public class PhoneNumber implements ValueObject{

    /**
     * The profession of the Email
     */
    private String phone;

    /**
     * Constructor.
     *
     * Constructs the profession object given a string representing a PhoneNumber
     * @param phone the representation of the PhoneNumber
     */
    public PhoneNumber(String phone){
        if(Strings.isNullOrEmpty(phone)){
            throw new IllegalArgumentException("Invalid Profession!");
        }
        this.phone = phone;
    }

    /**
     * Constructor for ORM
     */
    protected PhoneNumber(){
        //ORM
    }

    @Override
    public String toString(){
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        return phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }
}

