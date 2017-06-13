/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
    public BigDecimal convertTo(String valueToConvert, BigDecimal exchangeFactor) {
        BigDecimal value = new BigDecimal(valueToConvert);
        return value.multiply(exchangeFactor);
    }
    
    /**
     * 
     * @param exchangeValue
     * @return 
     */
    public BigDecimal dependentExchangeRate(BigDecimal exchangeValue){
        BigDecimal one = new BigDecimal(1);
        BigDecimal result = one.divide(exchangeValue, 5, RoundingMode.HALF_UP);
        return result;
    }

}
