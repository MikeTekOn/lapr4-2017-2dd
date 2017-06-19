package lapr4.green.s3.lang.n1150901.evalAndWhileLoops;

import csheets.CleanSheets;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Function;
import csheets.core.formula.UnaryOperator;
import csheets.core.formula.lang.UnknownElementException;
import lapr4.gray.s1.lang.n3456789.formula.NaryOperator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * A complementary factory classe to load core language plus newly defined
 * operators.
 *
 * @author Miguel Silva (1150901)
 * @author Einar Pehrson
 */
public class Language {

    /**
     * The core language singleton
     */
    private static final csheets.core.formula.lang.Language coreLanguage = csheets.core.formula.lang.Language.getInstance();

    /**
     * The previous language singleton
     */
    private static final lapr4.blue.s1.lang.n1151452.formula.lang.Language newLanguage = lapr4.blue.s1.lang.n1151452.formula.lang.Language.getInstance();

    /**
     * The new language singleton
     */
    private static final Language instance = new Language();

    /**
     * The name of the file in which the new language properties are stored
     */
    private static final String PROPS_FILENAME = "res/new_language_sprint3.props";

    /**
     * The new binary operators that are supported by the language
     */
    private List<BinaryOperator> newBinaryOperators = new ArrayList<>();

    /**
     * The new n-ary operators that are supported by the language
     */
    private List<NaryOperator> naryOperators = new ArrayList<>();

    /**
     * Protects the constructor of a Language (private)
     */
    private Language() {

        // Load new properties
        Properties languageProps = new Properties();
        InputStream inputStream = CleanSheets.class.getResourceAsStream(PROPS_FILENAME);
        if (inputStream != null) {
            try {
                languageProps.load(inputStream);
            } catch (IOException e) {
                System.err.println("An I/O error occurred when loading language"
                        + " properties file (" + PROPS_FILENAME + ").");
                return;
            } finally {
                try {

                    inputStream.close();
                } catch (IOException e) {
                    System.err.println("An I/O error occurred when loading language"
                            + " properties file (" + PROPS_FILENAME + ").");
                }
            }
            // Loads elements
            for (Object className : languageProps.keySet()) {

                // Loads class and instantiates element
                Class elementClass;
                Object element;
                try {
                    elementClass = Class.forName(getClass().getPackage()
                            .getName() + "." + className);

                    element = elementClass.newInstance();
                } catch (Exception e) {
                    // Skip this element, regardless of what went wrong
                    continue;
                }

                // Stores element
                if (BinaryOperator.class.isAssignableFrom(elementClass)) {
                    newBinaryOperators.add(BinaryOperator.class.cast(element));
                }
                if (NaryOperator.class.isAssignableFrom(elementClass)) {
                    naryOperators.add(NaryOperator.class.cast(element));
                }

            }
        } else {
            System.err.println("Could not find language properties file ("
                    + PROPS_FILENAME + ").");
        }
    }

    /**
     * Returns the Language (singleton instance).
     *
     * @return the singleton instance
     */
    public static Language getInstance() {
        return instance;
    }

    /**
     * Returns the unary operator with the given identifier.
     *
     * @param identifier identifier
     * @return the unary operator with the given identifier
     * @throws csheets.core.formula.lang.UnknownElementException throws
     */
    public UnaryOperator getUnaryOperator(String identifier) throws UnknownElementException {

        return coreLanguage.getUnaryOperator(identifier);
    }

    /**
     * Returns the binary operator with the given identifier.
     *
     * @param identifier identifier
     * @return the binary operator with the given identifier
     * @throws csheets.core.formula.lang.UnknownElementException throws
     */
    public BinaryOperator getBinaryOperator(String identifier) throws UnknownElementException {

        //noinspection Duplicates
        try {
            return coreLanguage.getBinaryOperator(identifier);

        } catch (UnknownElementException e) {

            try {
                return newLanguage.getBinaryOperator(identifier);

            } catch (UnknownElementException ex) {

                for (BinaryOperator operator : newBinaryOperators) {

                    if (identifier.equalsIgnoreCase(operator.getIdentifier())) {
                        return operator;
                    }
                }
                throw new UnknownElementException(identifier);
            }
        }
    }

    /**
     * Returns the n-ary operator with the given identifier.
     *
     * @param identifier identifier
     * @return the binary operator with the given identifier
     * @throws csheets.core.formula.lang.UnknownElementException throws
     */
    public NaryOperator getNaryOperator(String identifier) throws UnknownElementException {

        try {
            return newLanguage.getNaryOperator(identifier);

        } catch (UnknownElementException e) {
            for (NaryOperator operator : naryOperators) {

                if (identifier.equalsIgnoreCase(operator.getIdentifier())) {
                    return operator;
                }
            }
            throw new UnknownElementException(identifier);
        }
    }

    /**
     * Returns the function with the given identifier.
     *
     * @param identifier identifier
     * @return the function with the given identifier
     * @throws csheets.core.formula.lang.UnknownElementException throws
     */
    public Function getFunction(String identifier) throws UnknownElementException {

        return coreLanguage.getFunction(identifier);
    }

    /**
     * Returns whether there is a function with the given identifier.
     *
     * @param identifier identifier
     * @return whether there is a function with the given identifier
     */
    public boolean hasFunction(String identifier) {

        return coreLanguage.hasFunction(identifier);
    }

    /**
     * Returns the functions that are supported by the syntax.
     *
     * @return the functions that are supported by the syntax
     */
    public Function[] getFunctions() {

        return coreLanguage.getFunctions();
    }
}
