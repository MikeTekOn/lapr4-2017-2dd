package lapr4.green.s3.lang.n1150738.macros;

import csheets.core.Value;
import csheets.core.formula.Expression;
import lapr4.blue.s1.lang.n1151159.macros.Macro;
import lapr4.green.s3.lang.n1150738.macros.compiler.MacroInterpreter;

import java.util.List;

/**
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class MacroWithParameters extends Macro {


    /**
     * The name of the macro
     */
    private final String name;

    /**
     * The parameters of the macro
     */
    private ParameterDefinition parameterDefinition;


    public MacroWithParameters(List<Expression> expressions, String name, ParameterDefinition parameterDefinition) {
        super(expressions);
        this.name = name;
        this.parameterDefinition = parameterDefinition;
    }

    public String getName() {
        return name;
    }

    public ParameterDefinition getParameterDefinition() {
        return parameterDefinition;
    }


    public Value executeMacro(ParameterList list) throws InvalidParameterListException {
        this.parameterDefinition.validateParameterList(list); //throws exception
        MacroInterpreter interpreter = new MacroInterpreter(this, list);
        return (Value)this.accept(interpreter);
    }
}
