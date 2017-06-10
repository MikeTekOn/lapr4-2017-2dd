/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula.configurations;

import csheets.CleanSheets;
import eapli.util.Files;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.util.Pair;
import lapr4.red.s2.lang.n1150690.formula.MonetaryConvertion;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ConfigurateExchangeRatesController {

    private static final String PROPERTIES_FILE = "res/exchangeRates.props";
    private static final int NUMBER_OF_RATES = 6;
    private List<String> exchangeNames;
    private List<Pair<String, String>> rates;

    public ConfigurateExchangeRatesController() {
        fillExchangeRatesName();
        rates = new ArrayList<>();
    }

    private void fillExchangeRatesName() {
        exchangeNames = new ArrayList<>();
        exchangeNames.add("EuroToDollar");
        exchangeNames.add("EuroToPound");
        exchangeNames.add("DollarToEuro");
        exchangeNames.add("DollarToPound");
        exchangeNames.add("PoundToEuro");
        exchangeNames.add("PoundToDollar");
    }

    public List<Pair<String, String>> getExchangeRates() {
        InputStream in = CleanSheets.class.getResourceAsStream(PROPERTIES_FILE);
        Properties properties = new Properties();
        try {
            properties.load(in);
            in.close();
            for (String name : exchangeNames) {
                String value = properties.getProperty(name);
                Pair<String, String> newPair = new Pair(name, value);
                rates.add(newPair);
            }

        } catch (IOException ex) {
        }
 return rates;
    }

    /**
     *
     * @param exchangeRateName
     * @param newValue
     * @throws IOException
     */
    public String changeExchangeRate(String exchangeRateName, double newValue) throws IOException {
        URL u = CleanSheets.class.getResource(PROPERTIES_FILE);
        Files.updatePropertyValue(exchangeRateName, String.valueOf(newValue), u);
        return updatesExchangeRate(exchangeRateName, newValue);
    }

    /**
     *
     * @param exchangeRateName
     * @param newValue
     * @return
     * @throws IOException
     */
    private String updatesExchangeRate(String exchangeRateName, double newValue) throws IOException {
        String[] coinNames = exchangeRateName.split("To");
        String dependentExchangeName = coinNames[2].trim() + "To" + coinNames[0].trim();

        MonetaryConvertion money = new MonetaryConvertion();
        BigDecimal dependentExchangeRate = money.dependentExchangeRate(new BigDecimal(newValue));
        URL u = CleanSheets.class.getResource(PROPERTIES_FILE);
        Files.updatePropertyValue(dependentExchangeName, String.valueOf(dependentExchangeRate), u);

        return String.valueOf(dependentExchangeRate);
    }
}
