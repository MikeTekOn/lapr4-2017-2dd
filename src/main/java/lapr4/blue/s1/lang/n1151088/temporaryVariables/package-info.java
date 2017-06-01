/**
 * Technical documentation regarding the user story Lang02.1: Temporary Variables.
 * <p>
 * JIRA issue: LAPR4E17DD-54
 * <p>

 * <b>Scrum Master: no</b>
 * <b>Area Leader: no</b>
 * 
 * <h2>1. Notes</h2>
 * 
 * 2017/05/30 - started analysing the user story
 * 
 * <h2>2. Requirement</h2>
 * <p>
 * Add support for temporary variables. The name of temporary variables must start with the '_' sign.
 * When a variable is referred in a formula for the first time it is created. 
 * To set the value of a variable it must be used on the left of the assign operator (':='). 
 * Temporary variables are variables that only exist in the context of the execution of a formula.
 * Therefore, it is possible for several formulas to use temporary variables with the same name and they will be different instances. 
 * <p>
 * 
 * 
 * <b>Use Case "Temporary Variables":</b>
 * 
 * <ol>
 *  <li>The user introduces a formula containing temporary variable(s).</li>
 *  <li>The system displays the formula result</li>
 * </ol>
 * 
 * <img src="lang02.1_ssd.png" alt="image">
 * <p>
 * <b>Open Questions</b>
 * <p>Should it be possible to introduce more than one temporary variable?
 *   Answer: Yes
 * 
 * <h2>3. Analysis</h2>
 * 
 * <p>
 * It should be implemented a grammar for temporary variables.
 * Introducing a formula the temporary variables must be reconized. The Expression class important for the context of this use case due to a temporary variable be an expression that formula will execute on.
 * This use case is related to another use cases from Formulas section. For example the atribution ":=" it´s also referred in Block of Instructions.
 * The recognized temporary variables should be considered in several formulas. 
 * 
 * <h3>3.1. Analysis details</h3>
 * 
 * <b>3.1.1 storeContent</b>
 * <p>
 *      Executes formula=FormulaCompiler.getInstance().compile(contenxt) = this will select the instance of ExpressionCompiler
 *      based on the "starter char". In this case we have "_".
 * 
 * <p>
 * 
 * <b>3.1.2 Parser tree</b>
 * 
 *  <p>
 *      Antlr will generate a parser tree including nodes and then is converted and an Expression is generated.
 *      Grammar rules:
*      <ul>
*            <li>temporary variable start with _</li>
*            <li>atributtion value with :=</li>
*            <li>temporary variable name have a letter (after _) and then we can have letters/digits</li>
*       </ul>
* 
*  * <img src="domain_model_temporary_variables.png" alt="image">
 * 
 * <h2>4. Design</h2>
 * 
 * <h3>4.1. Tests</h3>
 * <b>Domain Tests</b>
 * <ol>
 *   <li>startWithUnderscore()</li>
 *   <li>haveLetterAfterUnderscore()</li>
 *   <li>rejectNumberAfterUnderscore()</li>
 *   <li>acceptDigitsAndLettersName()</li>
 * </ol>
 * 
 * <b>Functional Tests</b>
 * <ol>
 *   <li>addTemporaryVariableToContentorFirstTime()</li>
 *   <li>addTemporaryVariableToContentorAlreadyExists</li>
 *   <li>testBasicExpressionWithTemporary() -&gt; example "_Var:=1+2"</li>
 *   <li>testAssignmentOperatorWithTemporary() -&gt; example "_Var:=A1"</li>
 *   <li>testFunctionExpressionWithTemporary() -&gt; example "_Var:= sum(A2:A4)"</li>
*    <li>testFormulaBlocksWithTemporary() -&gt; ex: "= {A=1+2; _Var:= 1+A ;"
*   <li>testFormulaManyTemporaryVariables() -&gt; ex: "={_Var1:=2; _Var2:=3; _Var3:=_Var1+_Var2; A= _Var+3]"
*   <li>formulaWithTemporaryVariable()</li>
 * </ol> 
 * 
 * <h3>4.2. UC Realization</h3>
 * 
 * <img src="lang02.1_sd.png" alt="image">
 * 
 * <h3>4.3. Classes</h3>
 * 
 * <img src="lang02_1cd.png" alt="image">
 * 
 * <h3>4.4. Design Patterns and Best Practices</h3>
 * 
 * 
 * 
 * <h2>5. Implementation</h2>
 * 
 * <h2>6. Integration/Demonstration</h2>
 * 
 * <h2>7. Final Remarks</h2>
 * 
 * 
 * <h2>8. Work Log</h2> 
 * 
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 *  <ol>
 *     <li>Project´s manual analysis.</li>
 *      <li>Studying the requirements</li>
 *      <li>Analyzing the already implemented code base (Formulas, Expressions)</li>
 * </ol>
 * 
 * <b>Today</b>
 *  <ol>
 *     <li>Functional increment analysis.</li>
 *      <li>Elaborate the analysis</li>
 * </ol> 
 * <h2>9. Self Assessment</h2> 
 * <h3>R3. Rubric Requirements Fulfilment: 3</h3>
 * <h3>R6. Rubric Requirements Analysis: 4</h3>
 * <h3>R7. Rubric Design and Implement: 2</h3>
 * 
 * @author Diana Silva - 1151088@isep.ipp.pt - 2DD - 2016/17
 */

package lapr4.blue.s1.lang.n1151088.temporaryVariables;
