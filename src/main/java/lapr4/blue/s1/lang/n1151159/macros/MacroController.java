package lapr4.blue.s1.lang.n1151159.macros;

import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompiler;

/**
 * Represents a controller to perform operations related to macros.
 *
 * @author Ivo Ferro
 */
public class MacroController {

    public static ArrayList<String> macroList;

    public MacroController() {

    }

        public Value executeMacro(Spreadsheet spreadsheet, UIController uiController, String macroText) throws MacroCompilationException, IllegalValueTypeException {
        Expression macro = MacroCompiler.getInstance().compile(spreadsheet, uiController, macroText);
        return macro.evaluate();
    }
    


    /**
     * Gets the text of a default macro that calculate the average of 3 grades.
     *
     * @return default macro text
     */
    public String getDefaultMacro() {
        StringBuilder defaultMacro = new StringBuilder();
        defaultMacro.append("macro default()");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append(";Default Macro to calculate the average of 3 grades");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append(";Create the header:");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append("(A1 := \"Grade 1\")");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append("(B1 := \"Grade 2\")");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append("(C1 := \"Grade 3\")");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append("(E1 := \"Average\")");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append(";Fills the grades:");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append("(A2 := 18)");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append("(B2 := 15)");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append("(C2 := 20)");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append(";Calculates the average:");
        defaultMacro.append(System.lineSeparator());
        defaultMacro.append("(E2 := average(A2:C2))");

        return defaultMacro.toString();
    }
}
