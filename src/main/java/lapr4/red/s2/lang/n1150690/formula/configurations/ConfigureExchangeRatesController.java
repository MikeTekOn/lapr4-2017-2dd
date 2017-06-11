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
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lapr4.red.s2.lang.n1150385.beanshell.utils.Pair;
import lapr4.red.s2.lang.n1150690.formula.MonetaryConvertion;

/**
 * A controller for configurate the exchange rates.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ConfigureExchangeRatesController {

    /**
     * The name of the file that contains the exchange rates.
     */
    private static final String PROPERTIES_FILE = "res/exchangeRates.props";

    /**
     * The list with the exchange names.
     */
    private List<String> exchangeNames;

    /**
     * The list with the exchange rates and names.
     */
    private List<Pair<String, String>> rates;

    /**
     * Creates a new configurations controller.
     */
    public ConfigureExchangeRatesController() {
        fillExchangeRatesName();
        rates = new ArrayList<>();
    }

    /**
     * Fills the list of the exchange names with the respestive names.
     */
    private void fillExchangeRatesName() {
        exchangeNames = new ArrayList<>();
        exchangeNames.add("EuroToDollar");
        exchangeNames.add("EuroToPound");
        exchangeNames.add("DollarToEuro");
        exchangeNames.add("DollarToPound");
        exchangeNames.add("PoundToEuro");
        exchangeNames.add("PoundToDollar");
    }

    /**
     * Returns the exchange rates saved on exchangeRates.props.
     *
     * @return the exchange rates.
     */
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
            ex.printStackTrace();
        }
        return rates;
    }

    /**
     * Changes a exchange rate for the value inserted by the user.
     *
     * @param exchangeRateName the exchange name
     * @param newValue the new exchange rate
     * @throws IOException
     */
    public String changeExchangeRate(String exchangeRateName, double newValue) throws IOException, URISyntaxException {
        InputStreamReader in = new InputStreamReader(CleanSheets.class.getResourceAsStream(PROPERTIES_FILE));
        URL resource = CleanSheets.class.getResource(PROPERTIES_FILE);
        URI e = resource.toURI();
        String path = e.getPath();
        Files.updatePropertyValue(exchangeRateName, String.valueOf(newValue), in, path);
        return updatesExchangeRate(exchangeRateName, newValue);
    }

    /**
     * Updates the exchange rate dependent on the exchange rate the user has
     * changed.
     *
     * @param exchangeRateName the exchange name inserted by the user
     * @param newValue the new exchange rate inserted by the user
     * @return the new value of the dependet exchange rate
     * @throws IOException
     */
    private String updatesExchangeRate(String exchangeRateName, double newValue) throws IOException, URISyntaxException {
        String[] coinNames = exchangeRateName.split("To");
        String dependentExchangeName = coinNames[1].trim() + "To" + coinNames[0].trim();

        MonetaryConvertion money = new MonetaryConvertion();
        BigDecimal dependentExchangeRate = money.dependentExchangeRate(new BigDecimal(newValue));

        InputStreamReader in = new InputStreamReader(CleanSheets.class.getResourceAsStream(PROPERTIES_FILE));
        URL resource = CleanSheets.class.getResource(PROPERTIES_FILE);
        URI e = resource.toURI();
        String path = e.getPath();
        Files.updatePropertyValue(dependentExchangeName, String.valueOf(dependentExchangeRate), in, path);

        return String.valueOf(dependentExchangeRate);
    }
}
