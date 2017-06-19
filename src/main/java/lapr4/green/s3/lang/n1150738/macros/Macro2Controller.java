package lapr4.green.s3.lang.n1150738.macros;

import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompiler;
import lapr4.green.s3.lang.n1150738.macros.compiler.Macro2Compiler;

import java.util.ArrayList;

/**
 * Represents a controller to perform operations related to macros.
 *
 * @author Henrique Oliveira [1150738@isep.ip..pt]
 */
public class Macro2Controller {

    public static ArrayList<String> macroList;

    public Macro2Controller() {

    }

    public Value executeMacro(Spreadsheet spreadsheet, UIController uiController, String macroText) throws MacroCompilationException, IllegalValueTypeException {
        Expression macro = Macro2Compiler.getInstance().compile(spreadsheet, uiController, macroText);
        return macro.evaluate();
    }


}
