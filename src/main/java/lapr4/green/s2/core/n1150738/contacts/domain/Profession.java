package lapr4.green.s2.core.n1150738.contacts.domain;

import eapli.framework.domain.ValueObject;

/**
 * Value Object that represents a non null or empty Profession.
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class Profession implements ValueObject{

    /**
     * The profession of the Profession
     */
    private String profession;

    /**
     * Constructor.
     *
     * Constructs the profession object given a string representing a Profession
     * @param name the representation of the Profession
     */
    public Profession(String name){
        if(eapli.util.Strings.isNullOrEmpty(name)){
            throw new IllegalArgumentException("Invalid Profession!");
        }
        this.profession = name;
    }

    /**
     * Constructor for ORM
     */
    protected Profession(){
        //ORM
    }

    @Override
    public String toString(){
        return profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profession that = (Profession) o;

        return profession.equals(that.profession);
    }

    @Override
    public int hashCode() {
        return profession.hashCode();
    }
}

