/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class MonetaryConvertionTest {
    
    public MonetaryConvertionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of convertTo method, of class MonetaryConvertion.
     */
    @Test
    public void testConvertionEuroToDollar() {
        BigDecimal expectedResult = new BigDecimal(5.6076);
        BigDecimal convertionFactor = new BigDecimal(1.12152);
        BigDecimal valueToConvert = new BigDecimal(5);
        assertEquals(expectedResult.doubleValue(), convertionFactor.multiply(valueToConvert).doubleValue(), 0.001);
    }
    
    /**
     * Test of convertTo method, of class MonetaryConvertion.
     */
    @Test
    public void testConvertionEuroToPound() {
        BigDecimal expectedResult = new BigDecimal(4.34265);
        BigDecimal convertionFactor = new BigDecimal(0.86853);
        BigDecimal valueToConvert = new BigDecimal(5);
        assertEquals(expectedResult.doubleValue(), convertionFactor.multiply(valueToConvert).doubleValue(), 0.001);
    }
    
    /**
     * Test of convertTo method, of class MonetaryConvertion.
     */
    @Test
    public void testConvertionDollarToEuro() {
        BigDecimal expectedResult = new BigDecimal(4.4559);
        BigDecimal convertionFactor = new BigDecimal(0.89118);
        BigDecimal valueToConvert = new BigDecimal(5);
        assertEquals(expectedResult.doubleValue(), convertionFactor.multiply(valueToConvert).doubleValue(), 0.001);
    }
    
    /**
     * Test of convertTo method, of class MonetaryConvertion.
     */
    @Test
    public void testConvertionDollarToPound() {
        BigDecimal expectedResult = new BigDecimal(3.8702);
        BigDecimal convertionFactor = new BigDecimal(0.77404);
        BigDecimal valueToConvert = new BigDecimal(5);
        assertEquals(expectedResult.doubleValue(), convertionFactor.multiply(valueToConvert).doubleValue(), 0.001);
    }
    
    /**
     * Test of convertTo method, of class MonetaryConvertion.
     */
    @Test
    public void testConvertionPoundToDollar() {
        BigDecimal expectedResult = new BigDecimal(6.45985);
        BigDecimal convertionFactor = new BigDecimal(1.29197);
        BigDecimal valueToConvert = new BigDecimal(5);
        assertEquals(expectedResult.doubleValue(), convertionFactor.multiply(valueToConvert).doubleValue(), 0.001);
    }
    
    /**
     * Test of convertTo method, of class MonetaryConvertion.
     */
    @Test
    public void testConvertionPoundToEuro() {
        BigDecimal expectedResult = new BigDecimal(5.75695);
        BigDecimal convertionFactor = new BigDecimal(1.15139);
        BigDecimal valueToConvert = new BigDecimal(5);
        assertEquals(expectedResult.doubleValue(), convertionFactor.multiply(valueToConvert).doubleValue(), 0.001);
    }
    
}
