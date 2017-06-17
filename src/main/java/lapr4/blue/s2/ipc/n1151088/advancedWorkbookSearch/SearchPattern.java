/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lapr4.red.s1.core.n1150613.workbookSearch.RegexUtil;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class SearchPattern {

    private static final String PATTERN_EXTENSION = "cls";

    private static final String PATTERN_STARTER = "^\\p{Sc}";

    private static final String PATTERN_NUMBER = "[0-9]";
    private static final String PATTERN_LETTER = "[A-Za-z]";
    private static final String PATTERN_CONTENT = "[PATTERN_NUMBER|PATTERN_LETTER]*";
    private Pattern p;
    private Matcher m;

    private String regex;

    public SearchPattern(String regex) {
        this.regex = regex;
    }

    public boolean acceptExp(String filename) {
        String tokens[] = filename.split("\\.");

        if (tokens.length == 0 || tokens.length == 1) {
            return false;
        }

        if (filename.isEmpty()) {
            return false;
        }

        if (filename.matches(PATTERN_STARTER)) {
            return false;
        }

        String t = tokens[tokens.length - 1];
        String t0 = tokens[0];

        if (!t.matches(PATTERN_EXTENSION)) {
            return false;
        }

        if (filename.charAt(0) == '$') {
            return false;
        }

        return true;
    }

    public boolean checkIfMatches(String filename) {
        String tokens[] = filename.split("\\.");

        String content = ".*" + regex.toLowerCase() + ".*";
        p = Pattern.compile(content);
        m = p.matcher(tokens[0].toLowerCase());
        boolean contain = (m.matches());
        return contain;
    }
}
