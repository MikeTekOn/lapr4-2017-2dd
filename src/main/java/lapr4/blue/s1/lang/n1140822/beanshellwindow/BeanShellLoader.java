/**
 * Package location for 1140822's code related to beanshell integration.
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

import csheets.ui.ctrl.UIController;
import lapr4.red.s2.lang.n1150385.beanshell.Instruction;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Ricardo Catalao (1150385)
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class BeanShellLoader {

    /**
     * String that has the script name information to build - the script.
     */
    private String script;

    /**
     * The newly built beanshell instance.
     */
    private BeanShellInstance instance;

    public BeanShellLoader() {

    }

    /**
     * Creates the class using the given script.
     *
     * @param script the script
     * @return the class instance
     */
    public BeanShellInstance create(String script, UIController controller, VarContentor tempVarContentor) {
        this.script = script;
        LinkedList<Instruction> code = new LinkedList<>();
        StringBuilder builder = null;
        Scanner scan = new Scanner(script);
        int macroBlock = 0;
        while (scan.hasNextLine()) {
            String codeLine = scan.nextLine().trim();
            if (codeLine.equals("macro_start")) {
                if(macroBlock == 0){
                    builder = new StringBuilder();
                }else{
                    builder.append(codeLine);
                    builder.append('\n');
                }
                macroBlock++;
            }else if (codeLine.equals("macro_end")) {
                if(macroBlock != 0){
                    macroBlock--;
                    if(macroBlock == 0){
                        code.add(new Instruction(builder.toString(), Instruction.Type.MACRO));
                    }else{
                        builder.append(codeLine);
                        builder.append('\n');
                    }
                }
            } else {
                if(macroBlock == 0){
                    code.add(new Instruction(codeLine, Instruction.Type.BEANSHELL));
                }else{
                    builder.append(codeLine);
                    builder.append('\n');
                }
            }
        }
        if (code.isEmpty()) {
            throw new IllegalStateException("Cannot create script without any code.");
        }
        instance = new BeanShellInstance(code, controller, tempVarContentor);
        return instance;
    }

}
