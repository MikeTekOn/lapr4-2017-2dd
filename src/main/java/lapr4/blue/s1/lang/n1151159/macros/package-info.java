/**
 * Technical documentation regarding the user story Lang05.1: Macro Window
 *
 * <p><b>Scrum Master: no</b></p>
 *
 * <p><b>Area Leader: no</b></p>
 *
 *
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 *     The functional increment <b>Lang07 - Java Scripting</b> is to be
 *     integrated on this one. The user interface is common.
 * </p>
 *
 *
 *
 * <h2>2. Requirement</h2>
 *
 * <p>
 *     The user should be able to create and run macros. The macro should be associated with with the current active
 *     workbook. For the first iteration is not required to persist the macro.
 * </p>
 *
 * <p>
 *     A macro is a sequence of instructions that are executed sequential.
 *     The formulas are the same that are used in the cells. It can also contain comments.
 * </p>
 *
 * <p>Although the formulas starts with an '=' in the cells,  for the macros it can be omitted.</p>
 *
 * <p>A comment starts with ';', and this line should be ignored for processing.</p>
 *
 * <p>
 *     <b>Use Case "Macro Window":</b> The user selects the menu Extensions &gt; Macros &gt; Edit/Execute Macros. The
 *     system displays the macros window. The user enters the code for the macro and selects the native CleanSheet
 *     language. The system displays the result of the macro (last written instruction). The user presses the run button
 *     and the macro is executed.
 * </p>
 *
 *
 *
 * <h2>3. Analysis</h2>
 *
 * <p>
 *     <b>Attention:</b> In the class <b>{@link csheets.core.formula.util.ExpressionVisitor}</b> it was necessary to add
 *     a new method to support visiting the new type of operator "macro" (it did not exist in the original code of
 *     Cleansheets). This will add an "uncommon" dependency between the core code and the lapr4 code!
 * </p>
 *
 *
 *
 * <p><b>Clarifications</b></p>
 *
 * <p><b>Q: </b>What kind of expressions should a macro support? Just formulas or expressions in general?</p>
 * <p><b>A: </b>Expression in general.</p>
 *
 * <p>
 *     As we need to create a new dedicated extension for macros, it was necessary to analyse how the extensions work.
 *     For that, we can refer to the explanation presented in the package <b>{@link  csheets.ext.simple}</b>. The
 *     principles used in this functional increment are the same.
 * </p>
 *
 * <p>
 *     A new language shall be designed to support the macros.
 *     This language should define a recursive grammar that recognize all Cleansheet's formulas and comments.
 *     For that, the grammar previous implemented will be used as a base. Since the macros grammar might be latter
 *     changed and it's independent from the already existing grammar, it's going to be implemented on a new grammar
 *     file. As consequence, a new visitor for this grammar must be implemented.
 * </p>
 *
 * <p>
 *     The analysis presented on the package <b>{@link  lapr4.gray.s1.lang.n3456789.formula}</b>, applies to this
 *     functional increment. The idea explained about how the parsers works together is applied here.
 * </p>
 *
 * <p>The changes are mainly related to the Macros class. It can be previewed in the following diagram:</p>
 *
 * <img src="domain_model_macros.png" alt="image">
 *
 *
 *
 * <p>
 *     What's new compared to the previous implemented grammar is the <b>recursive call of the expression rule</b> and
 *     the rule to <b>ignore comments</b>.
 * </p>
 *
 *
 *
 * <h2>8. Work Log</h2>
 *
 * <p><b>Tuesday</b></p>
 *
 * <p>Yesterday I've worked on:</p>
 * <ol>
 *     <li>Project analysis.</li>
 *     <li>Studying the requirements.</li>
 *     <li>Brainstorming about the work mode.</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Functional increment analysis.</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing</li>
 * </ol>
 *
 *
 *
 * <p><b>Wednesday</b></p>
 *
 * <p><b>Yesterday I've worked on:</b></p>
 * <ol>
 *     <li>Analysis how the grammars are implemented.</li>
 *     <li>Analysis how the expression are called and the visitor pattern.</li>
 *     <li>Improved the analysis for this use case.</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 *
 * <ol>
 *     <li>Conclude the use case analysis.</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing</li>
 * </ol>
 *
 * @author Ivo Ferro
 */
package lapr4.blue.s1.lang.n1151159.macros;