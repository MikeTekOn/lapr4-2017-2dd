/**
 * <h1>[LAPR4E17DD-192] Global Variables</h1>
 *
 * Technical documentation regarding the user story Lang02.2: Global Variables.
 * <p>
 * JIRA issue: LAPR4E17DD-192
 *
 * Team RED
 *
 * <p>

 * <b>Scrum Master: no</b>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 * 2017/06/07 - started analysing the user story
 *
 * <h2>2. Requirement</h2>
 * <p>
 * Add support for global variables:
 * -&gt; Global variables are variables that exist in the context of the workbook and are persisted with the workbook.
 * -&gt; The name of global variables must start with the "@" sign.
 * -&gt; When a variable is referred in a formula for the first time it is created.
 * -&gt; To set the value of a variable it must be used on the left of the assign operator (":=").
 *
 * <p>
 *
 *
 * <b>Use Case "Global Variables":</b>
 *
 * <ol>
 *  <li>The user introduces a formula containing global variable(s).</li>
 *  <li>The system saves de variable name and value, displaying also the formula result.</li>
 * </ol>
 *
 * <img src="lang02_2_ssd.png" alt="image">
 * <p>
 * <b>Open Questions</b>
 * <p>Should it be possible to introduce more than one global variable?
 *   Answer: Yes
 *
 * <p>A Workbook's global variable can be shared to other workbooks?
 *   Answer: ??
 *
 * <h2>3. Analysis</h2>
 *
 * <p>
 * It should be implemented a grammar for global variables.
 * While introducing a formula, the global variables must be recognized.
 * The Expression class is important in the context of this Use Case since a global variable can be an expression that a formula will execute.
 * This use case is related to an other use case from the "formulas" section: The attribution symbol ":=" has to be recognized in order to implement the declaration of Global variables.
 * The already recognized global variables should be considered in all expressions that uses them. A global variable can be assigned to a reference, a literal, a function, per example.
 * It will be necessary to modify the formula visitor, the formula parser, the formula listener, the formula base visitor, formula.g4 (ANTLR) and finally the formula lexer to include this new type of atribute in formula.
 * Blue team decided to create a "duplication" of existent code and rename with "blue" (p.e.: formulaParser is blueFormulaParser) and we will use this parser.
 *
 * <h3>3.1. Analysis details</h3>
 *
 * Since a global variable is similar to a temporary one, it should be possible to use the same bases for both.
 * We will try to generalize concept of Variable by aggregating both global and temporary variables [by 'aggregating', we mean that the concept  will be the same for both, there will be no distinction between them except for the context in witch they are called].
 *
 * The VarContentor was ignored in the last sprint and was only focused in using Temporary Variables, so we will try to implement the contentor in the right way, generalizing it to contain a [also] generic variable - not focused in any in specific.
 *
 *
 *
 * <b>3.1.1 storeContent</b>
 * <p>
 *      Executes <b><i>formula = FormulaCompiler.getInstance().compile(content)</i></b>   =&gt; this will select the instance of ExpressionCompiler based on the "starter char". In this case we have "@".
 *
 *      The global variables will be stored in a variable contentor in the WorkBook and will be persisted with it
 * <p>
 *
 * <b>3.1.2 Parser tree</b>
 *
 *  <p>
 *      ANTLR will generate a parser tree including nodes and then is converted and an Expression is generated.
 *      Grammar rules:
 *      <ul>
 *            <li>global variable start with @</li>
 *            <li>atributtion value with :=</li>
 *            <li>global variable name have a letter (after @) and then it can have letters/digits</li>
 *       </ul>
 *
 *  * <img src="domain_model_global_variables.png" alt="image">
 *
 * <h2>4. Design</h2>
 *
 * <h3>4.1. Tests</h3>
 * <b>Domain Tests</b>
 * <p>Language (ANTLR)
 * <ol>
 *   <li>startWithAt()</li>
 *   <li>haveLetterAfterAt()</li>
 *   <li>rejectNumberAfterAt()</li>
 *   <li>acceptDigitsAndLettersName()</li>
 * </ol>
 * <p>Functional Tests
 * <ol>
 *   <li>testBasicExpressionWithGlobal() -&gt; example "@Var:=1+2"</li>
 *   <li>testAssignmentOperatorWithGlobal() -&gt; example "@Var:=A1"</li>
 *   <li>testFunctionExpressionWithGlobal() -&gt; example "@Var:= sum(A2:A4)"</li>
 *    <li>testFormulaBlocksWithGlobal() -&gt; ex: "= {A=1+2; @Var:= 1+A ;"
 *    <li>testFormulaManyGlobalVariables() -&gt; ex: "={/@Var1:=2; @Var2:=3; @Var3:=@Var1+@Var2; A= @Var+3}"</li>
 *   <li>testItSelfCall() -&gt; ex:"={\@a:=1);(@a:=@a+1);_a }"</li>
 *   <li>formulaWithTemporaryVariable() -&gt; ex:"={(@Var1:=2);(@Var2:=1); MAX(@Var1, @Var2)}" </li>
 * </ol>
 *
 *  <b>Functional Tests </b>
 * <p>
 * <ol>
 * <li>Insert a global variable in different cells in different sheets and see if the it's recognized in both.</li>
 * </ol>
 *
 * <h3>4.2. UC Realization</h3>
 *
 * <img src="lang02_2_sd.png" alt="image">
 *
 * <h3>4.3. Classes</h3>
 *
 * <img src="lang02_2_cd.png" alt="image">
 *
 * <h3>4.4. Design Patterns and Best Practices</h3>
 *
 *  The cleansheets core implementation already uses the <b>visitor pattern</b> to interact with the ANTLR4 generated classes, as well
 * as the <b>decorator pattern</b> to design the expressions functionality.
 * <p>
 * We will continue the best practices and implement this use case using the same patterns.
 *
 * As we have seen above, we will need a class to save the name and expression of a variable and an other one to store information of the global variables in the workbook.
 * So, we will need classes similar to the TemporaryVarContentor and TemporaryVariable used in the previous sprint [lan02.1 - temporary variables].
 * Obviously they should be adapted and, if possible, it should be created a class capable of performing both behaviours.
 *
 * <p>
 *
 * <h2>5. Implementation</h2>
 * <p>
 *
 * As said in the Design, TemporaryVariable and TemporaryVarContentor are similar to the classes needed in this UC, as we need a "GlobalVariable" and a "GlobalVarContentor".
 * Since they would have the same behaviour, we can create a general Variable class and a general VarContentor class that will be used for both cases.
 * Of course there will be no mixture of both since the local were they will be saved and called will be different.
 *  -&gt; The global variable will be automatically saved in the workbook in a VarContentor
 *  -&gt; The local variables will saved in a VarContentor in FormulaEvalVisitor.
 *
 * They will be differentiated by how the name is composed: if it starts with '_' goes to the local variables contentor and if it's a '@' goes to the workbook's variable contentor.
 *
 *
 * So:
 *
 * The class Variable {@link lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable} was created to handle both variable's domain rules.
 * The VarContentor {@link lapr4.red.s2.lang.n1150623.globalVariables} allows to store the temporary or global variables.
 *
 * <h3>Resumed Description of Variable and VarContentor Classes</h3>
 table, th, td {  border: 1px solid black; }
 * <table summary="">
 *
 *     <tr>
 *         <th>
 *              <b>Class</b>
 *         </th>
 *         <th>
 *              <b>Description</b>
 *         </th>
 *     </tr>
 *     <tr>
 *         <td>
 *              <i>Variable</i>
 *         </td>
 *         <td>
 *              The Variable class stores a variable's information: it's name and expression;
 *              It can be changed  by other formulas or expressions.
 *              This class also serves for both Global and Temporary Variables;
 *         </td>
 *     </tr>
 *     <tr>
 *
 *         <td>
 *              <i>VarContentor</i>
 *         </td>
 *         <td>
 *              The VarContentor class stores a variables, either global or temporary.
 *              This class implements serializable and, if acts as a contentor for global variables, is saved together with thw workbook
 *         </td>
 *
 *
 *     </tr>
 *
 *
 * </table>
 *
 *
 *
 * <p>
 * As referred before this use case is closely related to Formula use case (@link lapr4.blue.s1.lang.n1151452.formula). So the blue team
 * created some class for shared use: the grammar (@lapr4\blue\s1\lang\n1151452\formula\compiler\BlueFormula.g4), FormulaEvalVisitor (@link lapr4.blue.s1.lang.n1151452.formula.compiler)
 * and related classes (p.e. BlueFormulaParser).
 *
 * <p>
 * To add the temporary variable support to formula it was necessary to do some modifications in some core classes: ExpressionBuilder,
 * ExpressionVisitor, AbstractExpressionVisitor. The temporary variable it´s an object that stores the variable name and the expression assigned.
 *
 *
 * @author Guilherme Ferreira 1150623
 *
 * */
package lapr4.red.s2.lang.n1150623.globalVariables;