/**
 * <p>Technical documentation regarding the user story Lang05.1: Macro Window.</p>
 *
 * <p><b>Scrum Master: no</b></p>
 * <p><b>Team Leader: yes</b></p>
 *
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
 * <p>A comment starts with ';' and it should be ignored for processing.</p>
 *
 * <p>
 *     <b>Use Case "Macro Window":</b> The user selects the menu Extensions &gt; Macros &gt; Edit/Execute Macros. The
 *     system displays the macros window. The user enters the code for the macro and selects the native CleanSheet
 *     language. The system displays the result of the macro (last written instruction). The user presses the run button
 *     and the macro is executed.
 * </p>
 *
 * <img src="communication_ssd.png" alt="image">
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
 * <p><b>Clarifications</b></p>
 *
 * <p><b>Q: </b>What kind of expressions should a macro support? Just formulas or expressions in general?</p>
 * <p><b>A: </b>Expression in general.</p>
 *
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
 * <p>
 *     What's new compared to the previous implemented grammar is the <b>recursive call of the expression rule</b> and
 *     the rule to <b>ignore comments</b>.
 * </p>
 *
 *
 *
 *
 * <h2>4. Tests</h2>
 * <p>Notes Tests related to the formulas can be found at <b>{@link lapr4.gray.s1.lang.n3456789.formula}</b></p>
 * <p>Also, formulas can be tested interactively using <b>{@link csheets.core.formula.compiler.Console}</b></p>
 *
 * <h3>4.1. Unit Tests</h3>
 * <ol>
 *     <li>ensureMacroResultReturnsLastExpression</li>
 *     <li>ensureMacroExecutesAllStatements</li>
 *     <li>ensureAssignmentChangesCell</li>
 * </ol>
 *
 * <h3>4.2. Functional Tests Plan</h3>
 * <p>5.1.1. Test the macro window and macro results:</p>
 * <ol>
 *     <li>Open the macro window</li>
 *     <li>
 *         Insert the following macro:
 *         <ul>
 *             <li>A1:=2+2</li>
 *             <li>5*5</li>
 *             <li>B2:=2*3</li>
 *             <li>;Sums the cell A1 with the cell B2</li>
 *             <li>B3:=sum(A1;B2)</li>
 *             <li>50-B3+1</li>
 *         </ul>
 *     </li>
 *     <li>The macro result window must have the output 41.</li>
 *     <li>The cell A1 must have the value 4.</li>
 *     <li>The cell B2 must have the value 6.</li>
 *     <li>The cell B3 must have the value 10.</li>
 * </ol>
 *
 *
 *
 *
 * <h2>5. Design</h2>
 *
 *
 * <h3>5.1. Macro Sequence Diagram</h3>
 *
 * <img src="macro_sd.png" alt="new_language_sd">
 *
 *
 * <h3>5.2. Macro Class Diagram</h3>
 *
 * <img src="macro_cd.png" alt="new_language_sd">
 *
 *
 * <h3>5.3. Design Patterns</h3>
 * <p>
 *     To transverse and compile the formulas, the <b>Visitor</b> design pattern is used, generating the
 *     <b>MacroBaseVisitor</b> using the grammar defined with ANTLR.
 * </p>
 * <p>The <b>Decorator</b> design pattern is also used to design the formulas implementations.</p>
 * <p>
 *     For the extensions to be loaded the <b>Inversion of Control (IOC) pattern</b> is used, as referred in the
 *     overview page of the javadoc.
 * </p>
 *
 *
 *
 *
 * <h2>6. Implementation</h2>
 *
 * <p>Refer to the classes present on this package for the implementation.</p>
 *
 * <p>
 *     The class {@link lapr4.blue.s1.lang.n1151159.macros.Macro} was created to handle the new grammar.
 *     It is an expression that has a list of other expressions. It has the responsibility of evaluate
 *     the expressions. The class MacroCompiler was created to compile the rules of the macro grammar.
 *     As well, it was necessary to create the MacroEvalVisitor to transverse the grammar and make the evaluations.
 * </p>
 *
 * <p>
 *     The functionality was integrated as an extension of the CleanSheets. The class
 *     {@link lapr4.blue.s1.lang.n1151159.macros.MacroExtension} is the entry point. It was necessary to add this path
 *     to the extensions.props file. At the package {@link lapr4.blue.s1.lang.n1151159.macros.ui} we can find the
 *     components of this extension.
 * </p>
 *
 *
 *
 *
 * <h2>6. Integration/Demonstration</h2>
 *
 * <p>
 *     It was necessary to integrate the beanshell use case with this use case. For that I've worked with the
 *     developer of the beanshell functional increment (Renato Oliveira) to make this integration.
 * </p>
 * <p>I was very active and I successfully collaborate with the team members.</p>
 *
 *
 *
 *
 * <h2>7. Final Remarks</h2>
 * <p>
 *     As an extra for this functional increment, a default macro was created. When the macros window is launched,
 *     the default macro script will be there, as an example for the user. The user can always select the clear button
 *     to clear the macro text area.
 * </p>
 * <p>
 *     As a possible extra for this feature, a list of useful macros could be made. The users could use this macros and
 *     edit them as they like. As an example of this macros, a table with grades could be created with the necessary
 *     calculations to obtain final grades.
 * </p>
 *
 *
 *
 *
 * <h2>8. Work Log</h2>
 *
 * <h3>Tuesday</h3>
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
 * <h3>Wednesday</h3>
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
 *
 * <h3>Thursday</h3>
 *
 * <p><b>Yesterday I've worked on:</b></p>
 * <ol>
 *     <li>Conclusion of analysis</li>
 *     <li>Tests plan</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 *
 * <ol>
 *     <li>Finish the tests plan</li>
 *     <li>Design</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing</li>
 * </ol>
 *
 *
 * <h3>Friday</h3>
 *
 * <p><b>Yesterday I've worked on:</b></p>
 * <ol>
 *     <li>Conclusion of the tests</li>
 *     <li>Conclusion of the design</li>
 *     <li>Start the Implementation</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Finish the implementation</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing</li>
 * </ol>
 *
 *
 * <h3>Saturday</h3>
 *
 * <p><b>Yesterday I've worked on:</b></p>
 * <ol>
 *     <li>Finishing the implementation</li>
 * </ol>
 *
 * <p><b>Today</b></p>
 * <ol>
 *     <li>Functional Tests</li>
 *     <li>Review the functional increment</li>
 *     <li>Helping teammates</li>
 * </ol>
 *
 * <p><b>Blocking:</b></p>
 * <ol>
 *     <li>Nothing</li>
 * </ol>
 *
 *
 *
 *
 * @author Ivo Ferro
 */
package lapr4.blue.s1.lang.n1151159.macros;
