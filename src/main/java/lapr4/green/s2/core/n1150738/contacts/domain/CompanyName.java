package lapr4.green.s2.core.n1150738.contacts.domain;

import eapli.framework.domain.ValueObject;

/**
 * Value Object that represents a non null or empty Name.
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class CompanyName implements ValueObject{

    /**
     * The value of the CompanyName
     */
    private String value;

    /**
     * Constructor.
     *
     * Constructs the value object given a string representing a CompanyName
     * @param name the representation of the name
     */
    public CompanyName(String name){
        if(eapli.util.Strings.isNullOrEmpty(name)){
            throw new IllegalArgumentException("Invalid Name!");
        }
        this.value = value;
    }

    /**
     * Constructor for ORM
     */
    protected CompanyName(){
        //ORM
    }

    @Override
    public String toString(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyName companyName = (CompanyName) o;

        return value.equals(companyName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
