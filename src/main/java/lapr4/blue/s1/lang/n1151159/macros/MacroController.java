package lapr4.blue.s1.lang.n1151159.macros;

import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.formula.Expression;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompiler;

/**
 * Represents a controller to perform operations related to macros.
 *
 * @author Ivo Ferro
 */
public class MacroController {

    /**
     * Executes a given macro.
     *
     * @param spreadsheet the spreadsheet where the macro is going to be executed
     * @param macroText   the macro text
     * @return value of the last executed expression
     * @throws MacroCompilationException macro compilation exception
     * @throws IllegalValueTypeException illegal value type exception
     */
    public Value executeMacro(Spreadsheet spreadsheet, String macroText) throws MacroCompilationException, IllegalValueTypeException {
        Expression macro = MacroCompiler.getInstance().compile(spreadsheet, macroText);
        return macro.evaluate();
    }
}
