package lapr4.green.s2.core.n1150738.contacts.domain;

import org.junit.Test;

/**
 * A Unit Test class to test Profession.
 * @see Profession
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class ProfessionTest {

    /**
     * A method that tests constructing a Profession with null value
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureProfessionIsNonNull(){
        Profession n = new Profession(null);
    }

    /**
     * A method that tests constructing a Profession with empty value
     */
    @Test (expected = IllegalArgumentException.class)
    public void ensureProfessionIsNotEmpty(){
        Profession n = new Profession("");
    }
}