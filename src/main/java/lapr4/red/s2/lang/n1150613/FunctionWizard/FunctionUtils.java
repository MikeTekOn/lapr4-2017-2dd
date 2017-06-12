/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150613.FunctionWizard;

import csheets.core.IllegalValueTypeException;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Function;
import csheets.core.formula.FunctionParameter;
import csheets.core.formula.UnaryOperator;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.lang.Language;
import csheets.core.formula.lang.UnknownElementException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diogo Guedes
 */
public class FunctionUtils {

    private int type;

    protected static final String EQUAL = "=";
    protected static final String LEFT_PAR = "(";
    protected static final String RIGHT_PAR = ")";
    protected static final String SEMICOLON = ";";

    public FunctionUtils() {

    }

    /**
     * get syntax and build the expressation for function wizard
     *
     * @param identifier of function
     * @param i
     * @return get syntax and build the expressation for function wizard
     * @throws UnknownElementException to be caught
     */
    public String getSyntax(String identifier, int i) throws UnknownElementException {
        type = i;
        String aux = "";
        if (type == 0) {
            aux = EQUAL + identifier + LEFT_PAR;
            int count = 0;
            for (FunctionParameter fs : Language.getInstance().getFunction(identifier).getParameters()) {
                if (count == 0) {
                    aux += fs.getName();
                } else {
                    aux += SEMICOLON + fs.getName();
                }
                count++;
            }
            aux += RIGHT_PAR;
        } else if (type == 1) {
            identifier = identifier.substring(identifier.indexOf(" ") + 1, identifier.length());
            aux = EQUAL + LEFT_PAR + "Parameter1" + identifier + "Parameter2" + RIGHT_PAR;
        }

        return aux;
    }

    /**
     * gets all identifiers of the functions and operators
     *
     * @return all functions
     */
    public List getFunctions() {
        ArrayList list = new ArrayList<>();
        for (Function f : Language.getInstance().getFunctions()) {
            list.add(f.getIdentifier());
        }
        for (UnaryOperator un : Language.getInstance().getUnaryOperators()) {
            list.add(un.getClass().getSimpleName() + " " + un.getIdentifier());
        }

        for (BinaryOperator bn : Language.getInstance().getBinaryOperators()) {
            list.add(bn.getClass().getSimpleName() + " " + bn.getIdentifier());
        }

        return list;
    }

    /**
     * Returns the result of the function with the given parameters
     *
     * @param parameters inserted parameters
     * @param syntax identifier of the function
     * @return result result of the function
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     * @throws csheets.core.IllegalValueTypeException
     */
    public String calculateResult(String parameters, String syntax) throws FormulaCompilationException, IllegalValueTypeException {
        String result = "";
        if (type == 0) {
            result = calculateFunction(parameters, syntax);
        }
        if (type == 1) {
            result = calculateBinaryOperator(parameters, syntax);
        }

        return result;
    }

    /**
     * Returns the expression of the function with the given parameters
     *
     * @param parameters inserted parameters
     * @param syntax identifier of the function
     * @return result result of the function
     *
     */
    private String calculateFunction(String parameters, String syntax) {
        String result;
        String start = syntax.substring(0, syntax.indexOf("(") + 1);

        start = start + parameters + ")";
        result = start;
        return result;
    }

    /**
     * Returns the expression of the operator with the given parameters
     *
     * @param parameters inserted parameters
     * @param syntax identifier of the function
     * @return result result of the function
     *
     */
    private String calculateBinaryOperator(String parameters, String syntax) {
        String result;
        String[] start = parameters.split(";");

        result = syntax.replace("Parameter1", start[0]);
        result = result.replace("Parameter2", start[1]);

        return result;
    }

}
