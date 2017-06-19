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
 *      This user story is divided into 2 parts. Macros with parameters and Macros with Local Variables.
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
 *
 *  &nbsp;&nbsp;&nbsp;&nbsp;<i><u>Concept Classes:</u></i><p>
 *      A new version of the Macro grammar and compiler (MacroCompiler, MacroEvalVisitor)
 *
 * <p>
 * <img src="us05.3_concept_classes.png" alt="image">
 * <p>
 * <p>
 * <b>Tests</b>
 * <p>
 * <i><b>Part I: Macros with Local Variables</b></i><p>
 * <ul>
 *     <li>LocalVariableTest::ensureNonDeclaredVariableCannotBeReferenced()</li>
 *     <li>LocalVariableTest::ensureLocalVariableIsAssignable()</li>
 *     <li>LocalVariableTest::ensureLocalVariableHoldsValue()</li>
 *     <li>LocalVariableTest::ensureLocalVariableWorksOnNestedExpressions()</li>
 * </ul>
 * <p>
 * <ul>
 *     <li>LocalVariableTest::ensureNonDeclaredVariableCannotBeReferenced()</li>
 *     <li>LocalVariableTest::ensureLocalVariableIsAssignable()</li>
 *     <li>LocalVariableTest::ensureLocalVariableHoldsValue()</li>
 *     <li>LocalVariableTest::ensureLocalVariableWorksOnNestedExpressions()</li>
 * </ul>
 * <p>
 * <p>
 * <p>
 * <b>Design</b><p>
 * <i><b>Part I: Macros with Local Variables</b></i><p>
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
 * <img src="us05.3_eval_visitor_locals.png" alt="image">
 * <img src="us05.3_eval_visitor_locals2.png" alt="image">
 *
 *
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
package lapr4.green.s3.lang.n1150738.macros;

