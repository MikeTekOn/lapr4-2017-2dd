package lapr4.green.s3.lang.n1150738.macros.application;

import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import lapr4.blue.s1.lang.n1151159.macros.MacroController;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.green.s3.lang.n1150738.macros.MacroWithParameters;
import lapr4.green.s3.lang.n1150738.macros.Parameter;
import lapr4.green.s3.lang.n1150738.macros.ParameterDefinition;
import lapr4.green.s3.lang.n1150738.macros.ParameterList;
import lapr4.green.s3.lang.n1150738.macros.compiler.Macro2Compiler;
import lapr4.green.s3.lang.n1150738.macros.compiler.MacroInterpreter;

import java.util.ArrayList;

/**
 * Created by henri on 6/19/2017.
 */
public class Macro2Controller extends MacroController {

    private MacroWithParameters m;

    public MacroWithParameters compileMacro(String code, UIController uiController, Spreadsheet s) throws MacroCompilationException {
        this.m = Macro2Compiler.getInstance().compile(s, uiController, code);
        return m;
    }


    public boolean validateParameterList(String s) {
        int n = 0;
        if (s != null) {
            String[] tokens = s.split(",");
            n = tokens.length;
        }
        return n == m.getParameterDefinition().count();
    }

    private ParameterList buildParameterList(String s) {
        ParameterList list = new ParameterList();
        if (s != null) {
            String[] tokens = s.split(",");
            for (int i = 0; i < tokens.length; i++) {
                list.addParameter(new Parameter(m.getParameterDefinition().getParameter(i), Value.parseValue(tokens[i])));
            }
        }

        return list;
    }


    public Value executeMacro(Spreadsheet spreadsheet, UIController uiController, String macroText, String macroName, String params) throws MacroCompilationException, IllegalValueTypeException {
        if (macroList == null) {
            macroList = new ArrayList<>();
        }
        Expression macro = Macro2Compiler.getInstance().compile(spreadsheet, uiController, macroText, macroName, macroList);
        if (macro == null) {
            return null;
        }
        return (Value) m.accept(new MacroInterpreter(m, buildParameterList(params)));
    }


    public void emptyList() {
        macroList = null;
    }
}
