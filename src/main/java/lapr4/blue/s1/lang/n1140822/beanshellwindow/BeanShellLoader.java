/**
 * Package location for 1140822's code related to beanshell integration.
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

import csheets.ui.ctrl.UIController;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class BeanShellLoader {

    /**
     * String that has the script name information to build - the script.
     */
    private String scriptName;

    /**
     * The newly built beanshell instance.
     */
    private BeanShellInstance instance;

    public BeanShellLoader() {

    }

    /**
     * Creates the class using the given script.
     *
     * @param scriptName the script
     * @return the class instance
     * @throws java.io.FileNotFoundException if script file is not found
     */
    public BeanShellInstance create(String scriptName, UIController controller) throws FileNotFoundException {
        this.scriptName = scriptName;
        LinkedList<String> code = new LinkedList<>();
        LinkedList<String> macro = new LinkedList<>();
        Scanner scan = new Scanner(new File(scriptName));
        boolean macroFlag = false;
        while (scan.hasNextLine()) {
            String codeLine = scan.nextLine();
            if (codeLine.equals("macro_start")) {
                macroFlag = true;
            }
            if (codeLine.equals("macro_end")) {
                macroFlag = false;
            }
            if (!macroFlag) {
                code.add(codeLine);
            } else {
                macro.add(codeLine);
            }
        }
        if (code.isEmpty()) {
            throw new IllegalStateException("Cannot create script without any code.");
        }
        instance = new BeanShellInstance(code, macro,controller);
        return instance;
    }

}
