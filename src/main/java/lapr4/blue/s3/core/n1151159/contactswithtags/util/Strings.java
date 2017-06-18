package lapr4.blue.s3.core.n1151159.contactswithtags.util;

/**
 * Utility class for string manipulation.
 *
 * @author Ivo Ferro [1151159]
 */
public class Strings {

    /**
     * Checks if a given string is alphanumeric.
     *
     * @param str string to test
     * @return true if the string is alphanumeric, false otherwise
     */
    public static boolean isAlphanumeric(String str) {
        return str.matches("[A-Za-z0-9]+");
    }
}
