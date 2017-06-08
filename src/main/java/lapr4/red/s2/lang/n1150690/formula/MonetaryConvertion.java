/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula;

import java.math.BigDecimal;

/**
 * A class to performe calculation related to monetary values.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class MonetaryConvertion {

    /**
     * Creates a new convertion.
     */
    public MonetaryConvertion() {
    }

    /**
     * Converts a monetary value in a new monetary value.
     *
     * @param valueToConvert the value to convert
     * @param exchangeFactor the factor to multiplicate the value
     * @return the new monetary value
     */
    public BigDecimal convertTo(double valueToConvert, BigDecimal exchangeFactor) {
        BigDecimal value = new BigDecimal(valueToConvert);
        return value.multiply(exchangeFactor);
    }

}
