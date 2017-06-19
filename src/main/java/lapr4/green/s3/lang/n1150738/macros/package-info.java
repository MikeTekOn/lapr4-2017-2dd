/**
 * <h2>Technical documentation regarding the user story Lang05.3- Macros with Parameters.</h2>
 * <p>
 * <b>JIRA UserStory id: LAPR4E17DD-65</b>
 * <p>
 * Author: Henrique Oliveira [1150738@isep.ipp.pt]
 * <p>
 * <p>
 * <b>Requirement</b><p>
 * Statement:<p>
 * Macros should now have parameters. The syntax for macros should now include an header that should include the name
 * of the macro and its parameters (all parameters should have a distinct name). The parameters should be considered
 * only input parameters. However, it should be possible to freely reference parameters inside the macro. That is to
 * say that, inside a macro, parameters should be used like variables. Macros should support local variables that exist
 * only in the context of a macro. This local variables should have a syntax similar to the one described in Lang02.1
 * for the temporary variables of formulas. The invocation of macros must now include the values for its parameters.
 * <p>
 * <p>
 * <p>
 * <p>
 * <b>Analysis</b><p>
 * <p>
 *      This user story is divided into 2 parts. Macros with parameters and Macros with Local Variables.<p>
 * <i><b>Part I: Macros with Local Variables</b></i><p>
 * &nbsp;&nbsp;&nbsp;&nbsp;<i><u>What already exists:</u></i><p>
 *     Similar to this problem we have user story Lang02.1 Temporary Vars in which the formula compiler has a list of
 *     temporary variables and pushes its value accordingly
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;<i><u>What needs to be done:</u></i><p>
 *     Add grammar for the local variables that are in the form '_'IDENTIFIER, and add the given visitation action to
 *     get and set its value.
 *
 * <p>
 *  &nbsp;&nbsp;&nbsp;&nbsp;<i><u>Existing Classes:</u></i><p>
 *     {@link lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable}<p>
 *     {@link lapr4.blue.s1.lang.n1151159.macros.Macro}<p>
 *     {@link lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompiler}<p>
 *     {@link lapr4.blue.s1.lang.n1151159.macros.compiler.MacroEvalVisitor}<p>
 *     {@link lapr4.blue.s1.lang.n1151452.formula.compiler.ExcelExpressionCompiler}<p>
 *     {@link lapr4.blue.s1.lang.n1151452.formula.compiler.FormulaEvalVisitor}<p>
 *     {@link lapr4.red.s2.lang.n1150623.globalVariables.VarContentor}<p>
 *<p
 *  &nbsp;&nbsp;&nbsp;&nbsp;<i><u>Concept Classes:</u></i><p>
 *      A new version of the Macro grammar and compiler (MacroCompiler, MacroEvalVisitor)
 *
 * <p>
 * <img src="us05.3_concept_classes.png" alt="image">
 * <p>
 * <p>
 *
 * <i><b>Part II: Macros with Parameters</b></i><p>
 * &nbsp;&nbsp;&nbsp;&nbsp;<i><u>What already exists:</u></i><p>
 *     Similar to this problem we have formulas and also function calls but in this case we need to give the paramters
 *     variable like behaviour so they could be referenced anywhere. <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;<i><u>Problems:</u></i><p>
 *     Parameter recognition may conflict with cell or other identifiers recognition and because time is of the essence
 *     Reference to parameters will be done in the following syntax: "${paramName}" <p> (to discuss with product owner later)
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;<i><u>Concept Classes:</u></i><p>
 *      Continuation of the new version of the Macro grammar and compiler (MacroCompiler, MacroEvalVisitor, ParameterReference)
 *      For parameter domain rules(Parameter, ParameterList, ParameterDefinition)
 * <p>
 * <img src="us05.3_concept_classes2.png" alt="image">
 * <p>
 * <p>
 * <b>Tests</b>
 * <p>
 * <i><b>Tests Part I: Macros with Local Variables</b></i><p>
 * <ul>
 *     <li>LocalVariableTest::ensureNonDeclaredVariableCannotBeReferenced()</li>
 *     <li>LocalVariableTest::ensureLocalVariableIsAssignable()</li>
 *     <li>LocalVariableTest::ensureLocalVariableHoldsValue()</li>
 *     <li>LocalVariableTest::ensureLocalVariableWorksOnNestedExpressions()</li>
 * </ul>
 * <p>
 * <i><b>Tests Part II: Macros with Parameters</b></i><p>
 * <ul>
 *     <li>Macro2EvalVisitor::ensureCompilerDetectsCellReference()</li>
 *    <li>Macro2EvalVisitor::ensureCompilerRejectsInvalidCellReference()</li>
 *    <li>Macro2EvalVisitor::ensureCompilerRejectsInvalidParameterReference()</li>
 *    <li>Macro2EvalVisitor::ensureCompilerRecognizesCorrectParameterReference()</li>
 *    <li>MacroWithParameters::ensureMacroWithoutHeaderCantCompile()</li>
 *    <li>MacroWithParameters::ensureMacroCanHaveZeroParameters()</li>
 *    <li>MacroWithParameters::ensureMacroHasParameters()</li>
 *    <li>MacroWithParameters::ensureMacroParametersCanBeReferenced()</li>
 *    <li>MacroWithParameters::ensureMacroNameIsCaptured()</li>
 *    <li>MacroInterpreter::ensureMInterpreterEvaluatesSimpleValues()</li>
 *    <li>MacroInterpreter::ensureMInterpreterEvaluatesBinaryOperations()</li>
 *    <li>MacroInterpreter::ensureMInterpreterEvaluatesFunctionCalls()</li>
 *    <li>MacroInterpreter::ensureMInterpreterEvaluatesLocals()</li>
 *    <li>MacroInterpreter::ensureMInterpreterEvaluatesParametersOnFormulas()</li>
 *    <li>MacroInterpreter::ensureMInterpreterEvaluatesParametersOnBinaryOperations()</li>
 *    <li>MacroInterpreter::ensureMInterpreterEvaluatesParametersOnUnaryOperations()</li>
 *    <li>MacroInterpreter::ensureMInterpreterEvaluatesParametersOnComplexScenario()</li>
 * </ul>
 * <p>
 * <p>
 * <p>
 * <b>Design</b><p>
 * <i><b> Design Part I: Macros with Local Variables</b></i><p>
 * Changes on Grammar: Add token for local variable name on rule atom and assignment <p>
 * Changes on Evaluator: <p>
 * &nbsp;&nbsp; On visitAssignement create or update localVar on VarContentor of locals <p>
 * &nbsp;&nbsp; On visitAtom return value of the variable <p>
 *
 * <pre>
 * {@code
 *      atom
            : macro_invoked
            |	function_call
            |	reference
            |	literal
            |	LPAR comparison RPAR
            |	block
            |	assignment
            |   shellscript
            |   LOCAL_VARIABLE
            ;

        LOCAL_VARIABLE
            : UNDERSCORE LETTER (DIGIT|LETTER)*
            ;
 * }
 * </pre>
 *
 * <img src="us05.3_eval_visitor_locals.png" alt="image"> <p>
 * <img src="us05.3_eval_visitor_locals2.png" alt="image">
 *
 * <i><b> Design Part II: Macros with Parameters</b></i><p>
 * Changes on Grammar: Add header with macro name and parameter list and rule for parameter reference <p>
 * Changes on Evaluator: <p>
 * &nbsp;&nbsp; On visitHeader and visitParameterlist create ParameterDefinition and extract macro name <p>
 * &nbsp;&nbsp; On visitAtom check if parameter exists in definition and return ParameterReference <p>
 *
 * <pre>
 * {@code
 *      macro
            : NEWLINE* header (SEMI (~NEWLINE)* NEWLINE+| expression)* (SEMI (~NEWLINE)* NEWLINE+ | EQ? comparison) NEWLINE* EOF
            ;

        header
            :  'macro' IDENTIFIER LPAR parameters RPAR NEWLINE+
            ;

        parameters
            :
            | IDENTIFIER
            | parameters COMMA IDENTIFIER
            ;

        parameters_val_list
            :
            | literal
            | parameters_val_list COMMA literal
        ;

        atom
            : macro_invoked
            |	function_call
            |   PARAMETER_REFERENCE
            |	reference
            |	literal
            |	LPAR comparison RPAR
            |	block
            |	assignment
            |   shellscript
            |   LOCAL_VARIABLE
            ;

        macro_invoked : LPAR_SQ IDENTIFIER LPAR parameters_val_list RPAR RPAR_SQ;

        IDENTIFIER
            :IDENTIFIER_FRAG
            ;

        fragment IDENTIFIER_FRAG
            : LETTER (LETTER|DIGIT)*
            ;

        PARAMETER_REFERENCE
            :   '$''{'IDENTIFIER_FRAG'}'
            ;
 * }
 * </pre>
 *
 * <img src="us05.3_eval_visitor_params.png" alt="image">
 *
 * <p>
 * <p>
 * To evaluate the macro with the runtime parameters the Visitor Pattern was used in the form of the MacroInterpreter
 * that knows how to visit each expression and "treat" their values and subexpressions.
 * <p>
 * <p>
 * <img src="us05.3_eval_macro_invocation.png" alt="image">
 *
 * <p>
 * <p>
 * <b>UI</b>
 * As for the UI nothing was changed except the compiler that is used being now the newest built for this use case.
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
package lapr4.green.s3.lang.n1150738.macros;

