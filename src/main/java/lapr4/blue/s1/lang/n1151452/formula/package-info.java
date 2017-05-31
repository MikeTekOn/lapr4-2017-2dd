/**
 * Technical documentation regarding the user story Lang01.1: Block of Instructions.
 * <p>
 * <b>Area Leader: yes</b>
 * <p>
 * <h2>1. Notes:</h2>
 * <p><ol>
 * <li><b>This feature increment and this documentation are work in progress! It was already partially elaborated.</b>
 * <li><b>Spent significant time analyzing the already implemented code base for this feature.</b>
 * <li>The code base implemented "on top" of the cleansheets core, does not follow the core pattern. <b>Should we refactor to obey core pattern? A: YES</b>
 * <li>The class {@link org.antlr.v4.runtime.ANTLRInputStream} is deprecated, but we decided to continue to use it because the related documentation
 * states that "If you use both the the deprecated and the new streams, you will see a nontrivial performance degradation", and since the cleansheets core is not update we will maintain the use.
 * </ol>
 * <p>
 * <p>
 * <h2>2. Requirements</h2>
 * <p><ul>
 * <li>Add the possibility of writing blocks (or sequences) of instructions. A block must be delimited by curly braces and its instructions must be separated by ";".
 * <li>The instructions of a block are executed sequentially and the block "result" is the result of the last statement of the block. For example, the formula "= {1 + 2; sum (A1:A10); B3 + 4 }"
 * must result in the sequential execution of all expressions and the result is the value of the last expression "B3 + 4".
 * <li>Add the assign operator (its symbol is ":="). This operator assigns to its left the result of the right expression. At the moment the left of the assign operator can only be a cell reference.
 * <li>Add a FOR loop statement (should also be implemented based on instruction blocks). For example, the formula <pre>{@code "= FOR {A1: = 1 ; A1 < 10; A2: = A2 + A1; A1: = A1 + 1 }"}</pre>
 * executes a for loop in which: the first expression is the initialization, the second term is the boundary condition, all other expressions are performed for each iteration of the loop.
 * </ul><p>
 * <p>
 * <h2>3. Analysis</h2>
 * <p>
 * <b>Attention:</b>
 * In the class {@link csheets.core.formula.util.ExpressionVisitor} it was necessary to add a new method to support visiting the new type of operator "naryoperator" (it did not exist in the original code of Cleansheets).
 * This will add an "uncommon" dependency between the core code and the lapr4 code!<p>
 * <p><ol>
 * <li>This use case regards essentially the formula compiler and executer. It does not seem to have any impact in other areas of the application.
 * <li>Define new <i>ANTLR</i> rule to capture a for statement (assign &amp; block already implemented).
 * <li>The entry point for the execution of the formula compiler is the method setContent of the interface Cell.
 * <li>The setContent method basically does three things:<p>
 * <ol>
 * <li>storeContent(content);    = this compiles the content and produces a Formula object and updates cell dependencies based on the Formula
 * <li>fireContentChanged();     = this notifies listenners about content changes
 * <li>reevaluate();             = this executes the Formula and notifies listenners about value changes
 * </ol></li>
 * <li>The recognized operators are being loaded from the language.props (cleansheets core package), except for the new implementations of <b>sequence operator</b>
 * and <b>assignment operator</b> that are being load directly in {@link lapr4.gray.s1.lang.n3456789.formula.lang.Language}. <b>Should we refactor to obey core pattern? A: Yes, next point.</b>
 * <li>Refactor partially implemented feature to obey core structure (see below detail of Language class).
 * <li>The new operator FORLOOP (that should be based on the {@link lapr4.gray.s1.lang.n3456789.formula.lang.SequenceOperator}). In more detail, for should be customized sequence block, where
 * the first expression should be the <b>initializer</b> and the second <b>boundary condition</b> the remaining expressions behaves the same way as a sequence, but looping until the boundary condition is met.
 * </ol><p>
 * <p>
 * <h3>3.1. Analysis details</h3>
 * <p>
 * - <b>3.1.1. storeContent</b><p>
 * -- Executes formula=FormulaCompiler.getInstance().compile(content)  = this will select the instance of ExpressionCompiler based on the "starter char"<p>
 * -- compile<p>
 * --- will execute the FormulaParser (generated from ANTLR) to obtain the parser tree.<p>
 * --- the parser tree is then traversed and an Expression is generated.
 * <p>
 * <p>
 * - <b>3.1.2. How the parser and executer work together</b><p>
 * -- Antlr will generate a parser tree. The parser tree includes nodes for grammar elements that are marked with ""^ as a suffix. The other elements in the same rule will become child elements of the node. The elements that are marked with the "!" suffix are not included.<p>
 * -- For instance, the following grammar rule:<p>
 * -- L_CURLY_BRACKET^ comparison ( SEMI! comparison )* R_CURLY_BRACKET <p>
 * -- will generate a node for the L_CURLY_BRACKET and all other elements will be child nodes. The SEMI terminal will not be include in the child elements.
 * <p>
 * <p>
 * - <b>3.1.3. Converting parser tree to Expressions</b><p>
 * -- Each ExpressionCompiler (ExcelExpressionCompiler or any other) will traverse the nodes of the parser tree and generate an Expression for each node. At the end the result is a tree of expressions.<p>
 * -- The next diagram illustrates the Expression interface and its hierarchy.<p>
 * -- For the block the idea is to have a new class to support n-ary operations.<p>
 * -- For the FOR it should be based on the same n-ary operator, but the 2 first expression having special functionality.<p>
 * -- For the assignment the idea is to implement it as a binary operator.<p>
 * <p>
 * <p>
 * - <b>3.1.4. For loop statement</b><p>
 * -- Should have a initializer (based on a assigned operator expression)<p>
 * -- Should have a boundary condition (boolean expression)<p>
 * -- All <i>n</i> sequential expressions should be preformed until the boundary condition is met.<p>
 * -- The idea is to have a new class to extends the n-ary operator.<p>
 * <p>
 * <p>
 * - <b>3.1.5 Load language props</b><p>
 * -- One important aspect is how operators are dynamically being loaded based on the <b>language.props</b>. The class {@link csheets.core.formula.lang.Language} creates the related operator
 * class based on the entries of the props file. For this feature and to not alter core code, we will implement a new Language class that instantiates the core Language singleton class
 * and adds the extra operators. A brief pseudocode example:
 * <pre>
 * {@code
 * 	private Language() {
 *
 * 	   baseInstance = core.Language.getInstance();	// Base Instance
 *
 * 	   // Load Props file to newInstance
 * 	   InputStream stream = Language.class.getResourceAsStream(PROPERTIES_FILENAME);
 * 	   load -> newInstance
 *
 * 	}
 *
 * 	(...)
 *
 * 	getUnaryOperator() {
 *
 * 	    use base instance
 * 	}
 *
 * 	getNaryOperator() {
 *
 * 	    use new instance
 * 	}
 *
 * 	(...)
 *
 * }
 * </pre>
 * <p>
 * <h3>3.2. Domain Model (<i>Excerpt</i>)</h3>
 * <p>
 * <img src="formula_domain_model.png" alt="image"><p>
 * <p>
 * <h3>3.3. Open Questions</h3>
 * <p><ol>
 * <li>Should any of the looped expressions be the final result in the cell?
 * <b>A: not answered yet.</b>
 * <li>What should happen if the user inserts an unreachable boundary condition?
 * <b>A: not answered yet.</b>
 * </ol><p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <br>
 * <h2>8. Work Log</h2>
 * <p>
 * <b>Tuesday [30/05/2017]</b><p>
 * I worked on:<p>
 * Yesterday
 * <p><ol>
 * <li>Set up my workstation.
 * </ol><p>
 * Today
 * <p><ol>
 * <li>Analise the code base and the partially implemented FI.
 * <li>Elaborate the analysis of the FI.
 * </ol><p>
 * Blocking:
 * <p>
 * -nothing-
 * <p>
 * <p>
 * <b>Wednesday [31/05/2017]</b><p>
 * I worked on:<p>
 * Yesterday
 * <p><ol>
 * <li>Analise the code base and the partially implemented FI.
 * <li>Elaborate the analysis of the FI.
 * </ol><p>
 * Today
 * <p><ol>
 * <li>Clarify requirments with product owner and update analysis.
 * <li>Elaborate tests.
 * </ol><p>
 * Blocking:
 * <p>
 * -nothing-
 * <p>
 *
 * @author Daniel Gon&ccedil;alves [1151452@isep.ipp.pt]
 * on 30/05/17.
 */
package lapr4.blue.s1.lang.n1151452.formula;